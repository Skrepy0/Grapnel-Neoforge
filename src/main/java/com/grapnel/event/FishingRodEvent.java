package com.grapnel.event;

import com.grapnel.Config;
import com.grapnel.Grapnel;
import com.grapnel.command.FailingBuffer;
import com.grapnel.enchantments.ModEnchantHelper;
import com.grapnel.enchantments.ModEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class FishingRodEvent {
    // 存储玩家是否有摔落保护的映射
    private static final Map<UUID, Boolean> fallProtectionMap = new HashMap<>();
    // 存储玩家获得保护时的位置，用于检测是否已经移动
    private static final Map<UUID, Vec3> protectionStartPositions = new HashMap<>();

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = player.level();
        ItemStack stack = event.getItemStack();
        InteractionHand hand = event.getHand();

        // 检查是否是钓鱼竿
        if (stack.getItem() instanceof FishingRodItem) {
            // 如果玩家已经有鱼钩，说明是收杆操作
            if (player.fishing != null) {
                onFishingRodRetrieve(player, level, hand);
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Pre event) {
        event.getServer().getPlayerList().getPlayers().forEach(FishingRodEvent::checkPlayerLanding);
    }

    @SubscribeEvent
    public static void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        UUID playerId = player.getUUID();
        fallProtectionMap.remove(playerId);
        protectionStartPositions.remove(playerId);
    }

    private static void onFishingRodRetrieve(Player player, Level level, InteractionHand hand) {
        // 只在服务端执行
        if (level.isClientSide()) return;

        // 获取鱼钩实体
        if (player.fishing == null) return;

        FishingHook fishingHook = player.fishing;

        // 获取玩家和鱼漂位置
        Vec3 playerPos = player.position();
        Vec3 bobberPos = fishingHook.position();

        // 计算方向向量（从玩家指向鱼漂）
        Vec3 direction = new Vec3(
                bobberPos.x - playerPos.x,
                bobberPos.y - playerPos.y,
                bobberPos.z - playerPos.z
        );

        // 计算距离并限制最小距离
        double distance = Math.max(direction.length(), 1.0);

        // 标准化方向向量
        if (distance > 0) {
            Vec3 normalizedDirection = direction.scale(1 / distance);

            // 获取鱼竿上的附魔等级
            ItemStack stack = player.getItemInHand(hand);

            // 获取附魔等级
            int grapnelLevel = ModEnchantHelper.getEnchantmentLevel(stack, level, ModEnchantments.GRAPNEL);
            int unbreakingLevel = ModEnchantHelper.getEnchantmentLevel(stack, level, Enchantments.UNBREAKING);

            // 计算动量强度（基础值 + 附魔加成）
            double user_x = playerPos.x();
            double user_y = playerPos.y();
            double user_z = playerPos.z();

            double hook_x = bobberPos.x();
            double hook_y = bobberPos.y();
            double hook_z = bobberPos.z();

            double real_distance = Math.sqrt((user_x - hook_x) * (user_x - hook_x) + (user_y - hook_y) * (user_y - hook_y) + (user_z - hook_z) * (user_z - hook_z));
            double strength = grapnelLevel * 2.5;
            strength *= (real_distance / 15.0f);

            // 应用垂直动量 multiplier
            double verticalMultiplier = 1.0;

            // 给玩家添加速度
            player.push(
                    normalizedDirection.x * strength,
                    normalizedDirection.y * strength * verticalMultiplier,
                    normalizedDirection.z * strength
            );
            player.hurtMarked = true;

            Grapnel.LOGGER.info("Applied momentum to player: " + strength);

            // 设置摔落保护
            fallProtectionMap.put(player.getUUID(), true);
            // 记录保护开始时的位置
            protectionStartPositions.put(player.getUUID(), player.position());
            // 耐久损耗
            if (!player.isCreative()) {
                stack.setDamageValue(stack.getDamageValue() + getItemDamage(unbreakingLevel));
            }
        }
    }

    // 检查玩家是否落地
    private static void checkPlayerLanding(Player player) {
        if (!Config.getFailingBuffer()) return;
        if (player.level().isClientSide()) return;

        UUID playerId = player.getUUID();

        // 检查玩家是否有摔落保护
        boolean hasProtection = fallProtectionMap.getOrDefault(playerId, false);
        if (!hasProtection) return;

        // 获取保护开始时的位置
        Vec3 startPos = protectionStartPositions.get(playerId);
        if (startPos == null) {
            fallProtectionMap.put(playerId, false);
            return;
        }

        // 计算玩家移动的距离
        Vec3 currentPos = player.position();
        double distanceMoved = Math.sqrt(
                Math.pow(currentPos.x - startPos.x, 2) +
                        Math.pow(currentPos.y - startPos.y, 2) +
                        Math.pow(currentPos.z - startPos.z, 2)
        );

        // 只有当玩家移动了一定距离后，才开始检测落地
        // 这可以防止在收杆的瞬间就检测到"落地"
        if (distanceMoved < 0.1) {
            player.resetFallDistance();
            return;
        }

        // 简单的落地检测：玩家是否站在地面上
        if (player.onGround()) {
            // 玩家已经落地，移除保护
            player.resetFallDistance();
            fallProtectionMap.put(playerId, false);
            protectionStartPositions.remove(playerId);
            Grapnel.LOGGER.info("Removed fall protection for player: " + player.getName().getString());
        } else {
            // 玩家还在空中，保持保护并重置摔落距离
            player.resetFallDistance();
        }
    }

    private static int getItemDamage(int unbreakingLevel) {
        Random random = new Random();
        int rand = random.nextInt(100);
        if (unbreakingLevel == 0){
            if (rand <= 50) {
                return 0;
            }
            return 1;
        }
        else if (unbreakingLevel == 1) {
            if (rand <= 75) {
                return 0;
            }
            return 1;
        } else if (unbreakingLevel == 2) {
            if (rand <= 80) {
                return 0;
            }
            return 1;
        } else if (unbreakingLevel > 2) {
            if (rand <= 90) {
                return 0;
            }
            return 1;
        }
        return 1;
    }
}
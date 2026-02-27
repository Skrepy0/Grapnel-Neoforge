package com.grapnel.event;

import com.grapnel.Config;
import com.grapnel.Grapnel;
import com.grapnel.data.FishingHookData;
import com.grapnel.enchantments.ModEnchantHelper;
import com.grapnel.enchantments.ModEnchantments;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FishingRodEvent {
    private static class ProtectionData {
        boolean active;
        Vec3 startPos;

        ProtectionData(boolean active, Vec3 startPos) {
            this.active = active;
            this.startPos = startPos;
        }
    }

    private static final Map<UUID, ProtectionData> protectionMap = new HashMap<>();
    private static final double MIN_MOVE_DISTANCE_SQ = 0.01;

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        Level level = player.level();
        ItemStack stack = event.getItemStack();
        InteractionHand hand = event.getHand();
        if (stack.getItem() instanceof FishingRodItem && player.fishing != null) {
            // 检查是否满足触发条件
            boolean shouldTrigger = !Config.getGrapnelCheck() ||
                    player.fishing.getHookedIn() != null ||
                    player.fishing.onGround() ||
                    player.fishing.getData(FishingHookData.IS_HOOKED.get()).isHooked();

            if (shouldTrigger) {
                onFishingRodRetrieve(player, level, hand);
            }
        } else {
            player.getData(FishingHookData.LOCKED_INFO.get()).setIsLocked(false);
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Pre event) {
        // 只遍历有保护的玩家
        Iterator<Map.Entry<UUID, ProtectionData>> iterator = protectionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, ProtectionData> entry = iterator.next();
            Player player = event.getServer().getPlayerList().getPlayer(entry.getKey());
            if (player == null) {
                iterator.remove();
                continue;
            }
            checkPlayerLanding(player, entry.getValue(), iterator);
        }
    }

    @SubscribeEvent
    public static void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
        protectionMap.remove(event.getEntity().getUUID());
    }

    private static void onFishingRodRetrieve(Player player, Level level, InteractionHand hand) {
        if (level.isClientSide()) return;

        FishingHook fishingHook = player.fishing;
        if (fishingHook == null) return;

        Vec3 playerPos = player.position();
        Vec3 bobberPos = fishingHook.position();

        // 计算方向和距离
        Vec3 direction = bobberPos.subtract(playerPos);
        double distance = direction.length();
        if (distance < 1e-6) return; // 防止除以零

        // 标准化方向
        Vec3 normalized = direction.scale(1.0 / distance);

        // 获取附魔等级
        ItemStack stack = player.getItemInHand(hand);
        int grapnelLevel = ModEnchantHelper.getEnchantmentLevel(stack, level, ModEnchantments.GRAPNEL);
        int unbreakingLevel = ModEnchantHelper.getEnchantmentLevel(stack, level, Enchantments.UNBREAKING);

        // 计算拉拽强度
        double strength = grapnelLevel * 2.5 * (distance / 15.0);
        double verticalMultiplier = 1.0; // 可配置

        // 施加动量
        player.push(normalized.x * strength, normalized.y * strength * verticalMultiplier, normalized.z * strength);
        player.hurtMarked = true;

        Grapnel.LOGGER.debug("Applied momentum to player: {}", strength);

        // 设置摔落保护
        protectionMap.put(player.getUUID(), new ProtectionData(true, player.position()));

        // 耐久损耗
        if (!player.isCreative()) {
            stack.hurtAndBreak(getItemDamage(unbreakingLevel), player, player.getEquipmentSlotForItem(stack));
        }
    }

    private static void checkPlayerLanding(Player player, ProtectionData data, Iterator<?> iterator) {
        if (!Config.getFailingBuffer() || player.level().isClientSide()) {
            iterator.remove();
            return;
        }

        if (!data.active) return;

        Vec3 currentPos = player.position();
        double distanceSq = currentPos.distanceToSqr(data.startPos);

        // 如果还没移动，只重置摔落距离
        if (distanceSq < MIN_MOVE_DISTANCE_SQ) {
            player.resetFallDistance();
            return;
        }

        if (player.onGround()) {
            player.resetFallDistance();
            iterator.remove();
            Grapnel.LOGGER.debug("Removed fall protection for player: {}", player.getName().getString());
        } else {
            player.resetFallDistance();
        }
    }

    private static int getItemDamage(int unbreakingLevel) {
        if (unbreakingLevel <= 0) return 1;
        double probability = (-0.589) / (1 - 1.588 * Math.pow(Math.E, 0.1542 * unbreakingLevel));
        return ThreadLocalRandom.current().nextDouble() < probability ? 1 : 0;
    }
}
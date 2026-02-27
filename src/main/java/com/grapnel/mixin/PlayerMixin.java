package com.grapnel.mixin;

import com.grapnel.Config;
import com.grapnel.data.FishingHookData;
import com.grapnel.enchantments.ModEnchantHelper;
import com.grapnel.enchantments.ModEnchantments;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {

    @Unique
    private boolean checkFishingRod(Player player) {
        int flag = 0;
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.is(Items.FISHING_ROD)) {
                flag++;
                if (ModEnchantHelper.getEnchantmentLevel(stack, player.level(),ModEnchantments.SWING) > 0)flag++;
                break;
            }
        }
        return flag == 2;
    }

    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(CallbackInfo ci) {
        // 位置修正阈值
        final double POSITION_CLAMP_THRESHOLD = Config.getPositionClampThreshold();
        // 重力加速度 (MC标准重力)
        final double GRAVITY = 0.08;
        // 速度补偿系数
        final double ENERGY_COMPENSATION_FACTOR = Config.getEnergyCompensationFactor();
        // 初始保护期（Tick数）
        final int LOCK_PROTECTION_TICKS = Config.getLockProtectionTicks();
        Player player = (Player) (Object) this;

        // 获取锁定信息
        FishingHookData.LockedInfo lockedInfo = player.getData(FishingHookData.LOCKED_INFO.get());

        // 检查数据是否有效
        if (lockedInfo.isIsLocked() && player.fishing != null) {
            if (!checkFishingRod(player))return;
            // 递增锁定tick
            lockedInfo.setLockTick(lockedInfo.getLockTick() + 1);

            // 获取半径和锚点
            double radius = lockedInfo.getLockRadius();
            Vec3 center = lockedInfo.getHookPos();

            // 1. 获取玩家当前位置和速度
            Vec3 playerPos = player.position();
            Vec3 currentMotion = player.getDeltaMovement();

            // 2. 计算从锚点指向玩家的向量（径向向量）
            Vec3 directionToPlayer = playerPos.subtract(center);
            double distance = directionToPlayer.length();

            // 3. 只有当距离超过绳长时才进行约束
            if (distance > radius) {
                // --- 碰撞检测逻辑 (最高优先级) ---

                // 只有当玩家不在地面且不在液体中时才检测碰撞
                if (!player.onGround() && !player.isInLiquid()) {
                    boolean collided = false;

                    // 检测水平碰撞
                    if (player.horizontalCollision) {
                        collided = true;
                    }
                    // 检测垂直碰撞
                    if (player.verticalCollision) {
                        collided = true;
                    }

                    // 如果发生碰撞，直接将速度归零并返回
                    if (collided) {
                        player.setDeltaMovement(Vec3.ZERO);
                        return; // 直接返回，跳过后续的所有物理计算
                    }
                }

                // --- 物理核心：速度投影与修正 ---

                // A. 归一化径向向量（得到单位方向向量）
                Vec3 radialDir = directionToPlayer.normalize();

                // B. 计算当前速度在径向（绳子方向）上的投影（点积）
                double radialSpeed = currentMotion.dot(radialDir);

                // C. 只有当玩家正在远离锚点时，才需要消除这个方向的分速度
                if (radialSpeed > 0) {
                    // 计算径向速度向量
                    Vec3 radialVelocity = radialDir.scale(radialSpeed);

                    // 计算切向速度向量 = 总速度 - 径向速度
                    Vec3 tangentialVelocity = currentMotion.subtract(radialVelocity);

                    // --- 动能补偿逻辑 ---

                    // 初始保护期内不进行能量补偿
                    if (lockedInfo.getLockTick() > LOCK_PROTECTION_TICKS) {
                        // 1. 计算当前切向速度的大小
                        double currentSpeed = tangentialVelocity.length();

                        // 2. 计算高度差：锚点高度 - 玩家高度
                        double heightDiff = center.y - playerPos.y;

                        // 3. 根据能量守恒公式计算理论速度: v = sqrt(2 * g * h)
                        double targetSpeed = Math.sqrt(2 * GRAVITY * Math.max(0, heightDiff)) + 0.1;

                        // 4. 如果当前速度小于理论速度，则进行补偿
                        if (currentSpeed < targetSpeed * ENERGY_COMPENSATION_FACTOR&&currentSpeed>0.2) {
                            if (tangentialVelocity.lengthSqr() > 1.0E-7) {
                                // 使用 lerp 进行平滑加速
                                double newSpeed = Mth.lerp(currentSpeed, targetSpeed * ENERGY_COMPENSATION_FACTOR, 0.2);
                                player.setDeltaMovement(tangentialVelocity.normalize().scale(newSpeed));
                            }
                        } else {
                            // 如果速度足够，保持切向速度不变
                            player.setDeltaMovement(tangentialVelocity);
                        }
                    } else {
                        // 保护期内仅保持切向速度
                        player.setDeltaMovement(tangentialVelocity);
                    }
                } else {
                    // 玩家正在往回飞，保持原速度
                    player.setDeltaMovement(currentMotion);
                }

                // --- 位置修正 ---
                if (distance > radius + POSITION_CLAMP_THRESHOLD) {
                    Vec3 clampedPos = center.add(radialDir.scale(radius));
                    player.setPos(clampedPos.x, clampedPos.y, clampedPos.z);
                }
            }
        }
    }
}

package com.grapnel.mixin;

import com.grapnel.Config;
import com.grapnel.data.FishingHookData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHook.class)
public class FishingHookMixin {
    @Shadow
    private int life;
    @Unique
    private boolean grapnel$isHooked = false;
    @Unique
    private BlockPos grapnel$hookedPos = null; // 记录勾住的方块位置

    @Redirect(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;distanceToSqr(Lnet/minecraft/world/entity/Entity;)D"))
    private double shouldStopFishing(FishingHook hook, net.minecraft.world.entity.Entity entity) {
        double distance = hook.distanceToSqr(entity);
        if (entity instanceof Player player)
            return distance * grapnel$getRebate(player.getData(FishingHookData.TOUGHNESS_LEVEL.get()).getToughnessLevel());
        return distance;
    }

    @Unique
    private double grapnel$getRebate(int level) {
        if (level <= 0) return 1;
        return (-1.3675d) / (1 - 2.3737d * Math.pow(Math.E, 0.17535d * level));
    }

    @Inject(method = "onHitBlock", at = @At("HEAD"), cancellable = true)
    private void onHitBlock(BlockHitResult hitResult, CallbackInfo ci) {
        if (!Config.getGrabWall()) return;
        FishingHook hook = (FishingHook) (Object) this;
        ci.cancel();

        hook.setDeltaMovement(Vec3.ZERO);
        hook.setPos(hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
        this.life = 0;

        // 记录勾住的位置
        this.grapnel$isHooked = true;
        hook.getData(FishingHookData.IS_HOOKED.get()).setHooked(true);
        this.grapnel$hookedPos = BlockPos.containing(hitResult.getLocation());
    }

    // 在 tick 中只跳过移动部分，但保留其他逻辑
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTickStart(CallbackInfo ci) {
        FishingHook hook = (FishingHook) (Object) this;

        // 如果已勾住，检查支撑方块是否还在
        if (this.grapnel$isHooked && this.grapnel$hookedPos != null) {
            BlockState state = hook.level().getBlockState(this.grapnel$hookedPos);
            if (state.isAir()) {
                // 方块被破坏，脱离勾住状态
                this.grapnel$isHooked = false;
                hook.getData(FishingHookData.IS_HOOKED.get()).setHooked(false);
                this.grapnel$hookedPos = null;
                // 让鱼钩恢复原版行为，不再干预
            } else {
                // 仍然勾住，确保位置不变（防止其他模组干扰）
                hook.setPos(hook.getX(), hook.getY(), hook.getZ());
            }
        }
    }

    // 取消重力加速度的注入（如果鱼钩被勾住，则不应用重力）
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", ordinal = 0), cancellable = true)
    private void onGravity(CallbackInfo ci) {
        if (this.grapnel$isHooked) {
            ci.cancel(); // 阻止重力加速度
        }
    }

    // 取消移动的注入（如果鱼钩被勾住，则不移动）
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V"), cancellable = true)
    private void onMove(CallbackInfo ci) {
        if (this.grapnel$isHooked) {
            ci.cancel(); // 阻止移动
        }
    }
}
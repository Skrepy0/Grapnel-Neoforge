package com.grapnel.mixin;

import com.grapnel.data.FishingHookData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingHook.class)
public class FishingHookMixin {
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
}

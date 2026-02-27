package com.grapnel.mixin;

import com.grapnel.data.FishingHookData;
import com.grapnel.enchantments.ModEnchantHelper;
import com.grapnel.enchantments.ModEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public class FishingRodItemMixin {
    @Inject(method = "use", at = @At("HEAD"))
    private void use(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir){
        player.getData(FishingHookData.TOUGHNESS_LEVEL.get()).setToughnessLevel(ModEnchantHelper.getEnchantmentLevel(player.getItemInHand(hand),level, ModEnchantments.TOUGHNESS));

    }
}

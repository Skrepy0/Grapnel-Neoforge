package com.grapnel.datagen;

import com.grapnel.Grapnel;
import com.grapnel.enchantments.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.EnchantmentTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagsProvider extends EnchantmentTagsProvider {
    
    public ModEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.ON_MOB_SPAWN_EQUIPMENT)
                .addOptional(ModEnchantments.GRAPNEL);
        tag(EnchantmentTags.ON_RANDOM_LOOT)
                .addOptional(ModEnchantments.GRAPNEL)
                .addOptional(ModEnchantments.TOUGHNESS)
                .addOptional(ModEnchantments.SWING);
        tag(EnchantmentTags.ON_TRADED_EQUIPMENT)
                .addOptional(ModEnchantments.GRAPNEL)
                .addOptional(ModEnchantments.TOUGHNESS);
        tag(EnchantmentTags.TOOLTIP_ORDER)
                .addOptional(ModEnchantments.GRAPNEL);
        tag(EnchantmentTags.TRADEABLE)
                .addOptional(ModEnchantments.GRAPNEL)
                .addOptional(ModEnchantments.TOUGHNESS)
                .addOptional(ModEnchantments.SWING);

        tag(EnchantmentTags.TREASURE)
                .addOptional(ModEnchantments.GRAPNEL)
                .addOptional(ModEnchantments.TOUGHNESS)
                .addOptional(ModEnchantments.SWING);


        tag(EnchantmentTags.TRADES_PLAINS_COMMON)
                .addOptional(ModEnchantments.GRAPNEL)
                .addOptional(ModEnchantments.TOUGHNESS);

    }
}
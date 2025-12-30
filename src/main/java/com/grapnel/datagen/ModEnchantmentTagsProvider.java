package com.grapnel.datagen;

import com.grapnel.Grapnel;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagsProvider extends EnchantmentTagsProvider {
    public ModEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Grapnel.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.ON_MOB_SPAWN_EQUIPMENT)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Grapnel.MOD_ID, "grapnel"));
        tag(EnchantmentTags.ON_RANDOM_LOOT)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Grapnel.MOD_ID, "grapnel"));
        tag(EnchantmentTags.ON_TRADED_EQUIPMENT)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Grapnel.MOD_ID, "grapnel"));
        tag(EnchantmentTags.TOOLTIP_ORDER)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Grapnel.MOD_ID, "grapnel"));
        tag(EnchantmentTags.TRADEABLE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Grapnel.MOD_ID, "grapnel"));
        tag(EnchantmentTags.TREASURE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Grapnel.MOD_ID, "grapnel"));
        tag(EnchantmentTags.TRADES_PLAINS_COMMON)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Grapnel.MOD_ID, "grapnel"));
    }
}
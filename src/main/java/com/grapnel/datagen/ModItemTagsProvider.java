package com.grapnel.datagen;

import com.grapnel.Grapnel;
import com.grapnel.item.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.tags.TagEntry.tag;

// 修改继承的类：从 ItemTagsProvider 改为 TagsProvider<Item>
public class ModItemTagsProvider extends TagsProvider<@NotNull Item> {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ITEM, lookupProvider, Grapnel.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.getOrCreateRawBuilder(ModItemTags.GRAPNEL_AVAILABLE)
                .addElement(Identifier.parse("fishing_rod"));
        this.getOrCreateRawBuilder(ModItemTags.TOUGHNESS_AVAILABLE)
                .addElement(Identifier.parse("fishing_rod"));
        this.getOrCreateRawBuilder(ModItemTags.SWING_AVAILABLE)
                .addElement(Identifier.parse("fishing_rod"));
    }
}

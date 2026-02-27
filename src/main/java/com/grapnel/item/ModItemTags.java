package com.grapnel.item;

import com.grapnel.Grapnel;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> GRAPNEL_AVAILABLE = of("grapnel_available");
    public static final TagKey<Item> TOUGHNESS_AVAILABLE = of("toughness_available");
    public static final TagKey<Item> SWING_AVAILABLE = of("swing_available");

    public static TagKey<Item> of(String id) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Grapnel.MOD_ID, id));
    }

    public static void registerModItemTags() {
        Grapnel.LOGGER.info("Register Mod Item Tags");
    }

}

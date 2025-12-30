package com.grapnel.enchantments;

import com.grapnel.Grapnel;
import com.grapnel.item.ModItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;


public class ModEnchantments {
    public static final ResourceKey<Enchantment> GRAPNEL = of("grapnel");

    public static void bootstrap(BootstrapContext<Enchantment> registry) {
        HolderGetter<Enchantment> registryEntryLookup2 = registry.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> registryEntryLookup3 = registry.lookup(Registries.ITEM);
        register(registry, GRAPNEL, Enchantment.enchantment(Enchantment.definition(
                registryEntryLookup3.getOrThrow(ModItemTags.GRAPNEL_AVAILABLE),
                2, 1,
                Enchantment.dynamicCost(13, 5),
                Enchantment.dynamicCost(27, 10), 5,
                EquipmentSlotGroup.MAINHAND))
        );
    }

    public static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }

    public static ResourceKey<Enchantment> of(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Grapnel.MOD_ID, id));
    }
}

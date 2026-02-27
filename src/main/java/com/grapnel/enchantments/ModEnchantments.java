package com.grapnel.enchantments;

import com.grapnel.Grapnel;
import com.grapnel.item.ModItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class ModEnchantments {
    public static final ResourceKey<@NotNull Enchantment> GRAPNEL = of("grapnel");
    public static final ResourceKey<@NotNull Enchantment> TOUGHNESS = of("toughness");
    public static final ResourceKey<@NotNull Enchantment> SWING = of("swing");

    public static void bootstrap(BootstrapContext<@NotNull Enchantment> registry) {
        HolderGetter<@NotNull Enchantment> registryEntryLookup2 = registry.lookup(Registries.ENCHANTMENT);
        HolderGetter<@NotNull Item> registryEntryLookup3 = registry.lookup(Registries.ITEM);

        register(registry, GRAPNEL, Enchantment.enchantment(Enchantment.definition(
                registryEntryLookup3.getOrThrow(ModItemTags.GRAPNEL_AVAILABLE),
                2, 1,
                Enchantment.dynamicCost(13, 5),
                Enchantment.dynamicCost(27, 10), 5,
                new EquipmentSlotGroup[]{EquipmentSlotGroup.HAND}))
        );

        register(registry, TOUGHNESS, Enchantment.enchantment(Enchantment.definition(
                registryEntryLookup3.getOrThrow(ModItemTags.TOUGHNESS_AVAILABLE),
                3, 2,
                Enchantment.dynamicCost(11, 5),
                Enchantment.dynamicCost(25, 8), 4,
                new EquipmentSlotGroup[]{EquipmentSlotGroup.HAND}))
        );

        register(registry, SWING, Enchantment.enchantment(Enchantment.definition(
                registryEntryLookup3.getOrThrow(ModItemTags.SWING_AVAILABLE),
                2, 1,
                Enchantment.dynamicCost(12, 5),
                Enchantment.dynamicCost(26, 10), 5,
                new EquipmentSlotGroup[]{EquipmentSlotGroup.HAND}))
        );
    }

    private static void register(BootstrapContext<@NotNull Enchantment> context, ResourceKey<@NotNull Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.identifier()));
    }

    private static ResourceKey<@NotNull Enchantment> of(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(Grapnel.MOD_ID,name));
    }
}
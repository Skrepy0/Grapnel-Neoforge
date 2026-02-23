package com.grapnel.mixin;

import com.grapnel.Config;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(FishingRodItem.class)
public class ItemsMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstructed(net.minecraft.world.item.Item.Properties properties, CallbackInfo ci) {
        try {
            Field componentsField = Item.class.getDeclaredField("components");
            componentsField.setAccessible(true);

            DataComponentMap originalMap = (DataComponentMap) componentsField.get(this);

            DataComponentMap.Builder builder = DataComponentMap.builder();
            for (TypedDataComponent<?> entry : originalMap) {
                copyComponent(builder, entry);
            }

            int newDurability = 384;
            builder.set(DataComponents.MAX_DAMAGE, newDurability);

            DataComponentMap newMap = builder.build();
            componentsField.set(this, newMap);

            System.out.println("[FishingRodItem] Successfully changed max durability to " + newDurability);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("[FishingRodItem] Failed to modify durability: " + e.getMessage());
        }
    }

    private static <T> void copyComponent(DataComponentMap.Builder builder, TypedDataComponent<T> component) {
        builder.set(component.type(), component.value());
    }
}
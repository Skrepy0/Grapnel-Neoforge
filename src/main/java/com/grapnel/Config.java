package com.grapnel;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue FAILING_BUFFER = BUILDER
            .comment("玩家使用附魔有§6[抓钩]§r的钓竿后，下一次落地不受伤害")
            .define("failingBuffer", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean getFailingBuffer(){
        return FAILING_BUFFER.get();
    }

    public static void setFailingBuffer(boolean value){
        FAILING_BUFFER.set(value);
    }

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}

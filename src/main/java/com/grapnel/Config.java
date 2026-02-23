package com.grapnel;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.BooleanValue FAILING_BUFFER = BUILDER
            .define("fallingBuffer", false);
    private static final ModConfigSpec.BooleanValue GRAPNEL_CHECK = BUILDER
            .define("grapnelCheck", false);
    private static final ModConfigSpec.BooleanValue GRAB_WALL = BUILDER
            .define("grabWall", true);

    public static boolean getFailingBuffer() {
        return FAILING_BUFFER.get();
    }

    public static boolean getGrapnelCheck() {
        return GRAPNEL_CHECK.get();
    }

    public static boolean getGrabWall() {
        return GRAB_WALL.get();
    }


    public static void setFailingBuffer(boolean value) {
        FAILING_BUFFER.set(value);
    }

    public static void setGrapnelCheck(boolean value) {
        GRAPNEL_CHECK.set(value);
    }
    public static void setGrabWall(boolean value) {
        GRAB_WALL.set(value);
    }

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    static final ModConfigSpec SPEC = BUILDER.build();
}

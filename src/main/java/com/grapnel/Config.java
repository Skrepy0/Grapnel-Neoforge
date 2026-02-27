package com.grapnel;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.BooleanValue FAILING_BUFFER = BUILDER
            .define("fallingBuffer", false);
    private static final ModConfigSpec.BooleanValue GRAPNEL_CHECK = BUILDER
            .define("grapnelCheck", false);
    private static final ModConfigSpec.BooleanValue GRAB_WALL = BUILDER
            .define("grabWall", true);
    private static final ModConfigSpec.DoubleValue POSITION_CLAMP_THRESHOLD = BUILDER
            .defineInRange("positionClampThreshold", 0.2, 0.00000001, 1);
    private static final ModConfigSpec.DoubleValue ENERGY_COMPENSATION_FACTOR = BUILDER
            .defineInRange("energyCompensationFactor", 1.0, 0.00000001, 2.5);
    private static final ModConfigSpec.IntValue LOCK_PROTECTION_TICKS = BUILDER
            .defineInRange("lockProtectionTicks", 5, 0, 40);


    public static boolean getFailingBuffer() {
        return FAILING_BUFFER.get();
    }

    public static boolean getGrapnelCheck() {
        return GRAPNEL_CHECK.get();
    }

    public static boolean getGrabWall() {
        return GRAB_WALL.get();
    }

    public static double getPositionClampThreshold() {
        return POSITION_CLAMP_THRESHOLD.get();
    }

    public static double getEnergyCompensationFactor() {
        return ENERGY_COMPENSATION_FACTOR.get();
    }

    public static int getLockProtectionTicks() {
        return LOCK_PROTECTION_TICKS.get();
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

    public static void setPositionClampThreshold(double value) {
        POSITION_CLAMP_THRESHOLD.set(value);
    }

    public static void setEnergyCompensationFactor(double value) {
        ENERGY_COMPENSATION_FACTOR.set(value);
    }

    public static void setLockProtectionTicks(int value) {
        LOCK_PROTECTION_TICKS.set(value);
    }


    static final ModConfigSpec SPEC = BUILDER.build();
}

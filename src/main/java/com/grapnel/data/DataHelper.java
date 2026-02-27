package com.grapnel.data;

import com.mojang.datafixers.types.templates.Hook;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;

public class DataHelper {
    private DataHelper(){}
    public static void setIsLocked(Player player, boolean value) {
        FishingHookData.LockedInfo current = player.getData(FishingHookData.LOCKED_INFO.get());
        player.setData(FishingHookData.LOCKED_INFO.get(),
                new FishingHookData.LockedInfo(value, current.hookPos(), current.lockRadius(), current.lockTick()));
    }
    public static void setLockTick(Player player, int value) {
        FishingHookData.LockedInfo current = player.getData(FishingHookData.LOCKED_INFO.get());
        player.setData(FishingHookData.LOCKED_INFO.get(),
                new FishingHookData.LockedInfo(current.locked(), current.hookPos(), current.lockRadius(), value));
    }
    public static void setHooked(FishingHook hook, boolean value) {
        FishingHookData.IsHooked current = hook.getData(FishingHookData.IS_HOOKED.get());
        hook.setData(FishingHookData.IS_HOOKED.get(),
                new FishingHookData.IsHooked(value));

    }
}

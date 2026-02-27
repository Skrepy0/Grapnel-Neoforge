package com.grapnel.data;

import com.grapnel.Grapnel;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.function.Supplier;

public class FishingHookData {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Grapnel.MOD_ID);

    public static final Supplier<AttachmentType<ToughnessLevel>> TOUGHNESS_LEVEL =
            ATTACHMENT_TYPES.register("fishing_rod_toughness_level",
                    () -> AttachmentType.serializable(ToughnessLevel::new).build()); // 存储在玩家
    public static final Supplier<AttachmentType<IsHooked>> IS_HOOKED =
            ATTACHMENT_TYPES.register("is_hooked",
                    () -> AttachmentType.serializable(IsHooked::new).build()); // 存储在钩子
    public static final Supplier<AttachmentType<LockedInfo>> LOCKED_INFO = ATTACHMENT_TYPES.register("locked_info", () -> AttachmentType.serializable(LockedInfo::new).build());
    public static class ToughnessLevel implements INBTSerializable<CompoundTag> {

        private static final String TOUGHNESS_LEVEL = "ToughnessLevel";
        private int toughnessLevel = 0;

        public int getToughnessLevel() {
            return toughnessLevel;
        }

        public void setToughnessLevel(int toughnessLevel) {
            this.toughnessLevel = toughnessLevel;
        }

        @Override
        public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
            CompoundTag tag = new CompoundTag();
            tag.putInt(TOUGHNESS_LEVEL, toughnessLevel);
            return tag;
        }

        @Override
        public void deserializeNBT(HolderLookup.@NotNull Provider provider, CompoundTag compoundTag) {
            this.toughnessLevel = compoundTag.getInt(TOUGHNESS_LEVEL);
        }
    }

    public static ToughnessLevel getKineticLevel(Player player) {
        return player.getData(TOUGHNESS_LEVEL.get());
    }

    public static void setKineticLevel(Player player, int kineticLevel) {
        getKineticLevel(player).setToughnessLevel(kineticLevel);
    }

    public static class IsHooked implements INBTSerializable<CompoundTag> {
        private static final String IS_HOOKED = "IsHooked";
        private boolean isHooked = false;

        public boolean isHooked() {
            return isHooked;
        }

        public void setHooked(boolean hooked) {
            isHooked = hooked;
        }

        @Override
        public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean(IS_HOOKED, isHooked);
            return tag;
        }

        @Override
        public void deserializeNBT(HolderLookup.@NotNull Provider provider, CompoundTag compoundTag) {
            this.isHooked = compoundTag.getBoolean(IS_HOOKED);
        }
    }

    public static IsHooked getIsHooked(FishingHook hook) {
        return hook.getData(IS_HOOKED.get());
    }

    public static void setIsHooked(FishingHook hook, boolean isHooked) {
        getIsHooked(hook).setHooked(isHooked);
    }
    public static class LockedInfo implements INBTSerializable<CompoundTag>{
        private static final String LOCKED_INFO = "LockedPosition";
        private boolean isLocked = false;
        private static final String HOOK_POS = "HookPos";
        private Vec3 hookPos = Vec3.ZERO;
        private static final String LOCK_RADIUS = "LockRadius";
        private double lockRadius;
        private static final String LOCK_TICK = "LockTick";
        private int lockTick = 0;

        public int getLockTick() {
            return lockTick;
        }

        public void setLockTick(int lockTick) {
            this.lockTick = lockTick;
        }

        public void setInfo(boolean isLocked, Vec3 hookPos, double lockRadius) {
            this.isLocked = isLocked;
            this.hookPos = hookPos;
            this.lockRadius = lockRadius;
        }

        public double getLockRadius() {
            return lockRadius;
        }

        public void setLockRadius(double lockRadius) {
            this.lockRadius = lockRadius;
        }

        public Vec3 getHookPos() {
            return hookPos;
        }

        public void setHookPos(Vec3 hookPos) {
            this.hookPos = hookPos;
        }

        public boolean isIsLocked() {
            return isLocked;
        }

        public void setIsLocked(boolean isLocked) {
            this.isLocked = isLocked;
        }

        @Override
        public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
            CompoundTag tag = new CompoundTag();
            tag.putBoolean(LOCKED_INFO, isLocked);
            tag.putIntArray(HOOK_POS, new int[]{(int) hookPos.x, (int) hookPos.y, (int) hookPos.z});
            tag.putDouble(LOCK_RADIUS, lockRadius);
            return tag;
        }

        @Override
        public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
            isLocked = compoundTag.getBoolean(LOCKED_INFO);
            hookPos = new Vec3(compoundTag.getIntArray(HOOK_POS)[0], compoundTag.getIntArray(HOOK_POS)[1], compoundTag.getIntArray(HOOK_POS)[2]);
            lockRadius = compoundTag.getDouble(LOCK_RADIUS);
        }
    }
    public static LockedInfo getLockedInfo(Player player) {
        return player.getData(LOCKED_INFO.get());
    }
    public static void setLockedInfo(Player player, boolean lockedInfo) {
        getLockedInfo(player).setIsLocked(lockedInfo);
    }
}

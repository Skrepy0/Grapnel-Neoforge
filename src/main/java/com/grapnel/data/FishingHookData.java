package com.grapnel.data;

import com.grapnel.Grapnel;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
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
}

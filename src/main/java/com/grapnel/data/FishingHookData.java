package com.grapnel.data;

import com.grapnel.Grapnel;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class FishingHookData {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Grapnel.MOD_ID);

    // --- ToughnessLevel ---
    public record ToughnessLevel(int level) {
        public static final MapCodec<ToughnessLevel> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Codec.INT.fieldOf("level").forGetter(ToughnessLevel::level)
                ).apply(instance, ToughnessLevel::new)
        );
    }

    public static final Supplier<AttachmentType<ToughnessLevel>> TOUGHNESS_LEVEL =
            ATTACHMENT_TYPES.register("fishing_rod_toughness_level",
                    () -> AttachmentType.builder(() -> new ToughnessLevel(0))
                            .serialize(ToughnessLevel.CODEC)
                            .build());

    // --- IsHooked ---
    public record IsHooked(boolean hooked) {
        public static final MapCodec<IsHooked> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Codec.BOOL.fieldOf("hooked").forGetter(IsHooked::hooked)
                ).apply(instance, IsHooked::new)
        );
    }

    public static final Supplier<AttachmentType<IsHooked>> IS_HOOKED =
            ATTACHMENT_TYPES.register("is_hooked",
                    () -> AttachmentType.builder(() -> new IsHooked(false))
                            .serialize(IsHooked.CODEC)
                            .build());

    // --- LockedInfo ---
    public record LockedInfo(boolean locked, Vec3 hookPos, double lockRadius, int lockTick) {
        public static final MapCodec<LockedInfo> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Codec.BOOL.fieldOf("locked").forGetter(LockedInfo::locked),
                        Vec3.CODEC.fieldOf("hookPos").forGetter(LockedInfo::hookPos),
                        Codec.DOUBLE.fieldOf("lockRadius").forGetter(LockedInfo::lockRadius),
                        Codec.INT.fieldOf("lockTick").forGetter(LockedInfo::lockTick)
                ).apply(instance, LockedInfo::new)
        );
    }

    public static final Supplier<AttachmentType<LockedInfo>> LOCKED_INFO =
            ATTACHMENT_TYPES.register("locked_info",
                    () -> AttachmentType.builder(() -> new LockedInfo(false, Vec3.ZERO, 0, 0))
                            .serialize(LockedInfo.CODEC)
                            .build());

    // --- Helper methods (adapting to record immutability) ---

    public static ToughnessLevel getToughnessLevel(Player player) {
        return player.getData(TOUGHNESS_LEVEL.get());
    }

    public static void setToughnessLevel(Player player, int level) {
        player.setData(TOUGHNESS_LEVEL.get(), new ToughnessLevel(level));
    }

    public static IsHooked getIsHooked(FishingHook hook) {
        return hook.getData(IS_HOOKED.get());
    }

    public static void setIsHooked(FishingHook hook, boolean hooked) {
        hook.setData(IS_HOOKED.get(), new IsHooked(hooked));
    }

    public static LockedInfo getLockedInfo(Player player) {
        return player.getData(LOCKED_INFO.get());
    }

    public static void setLockedInfo(Player player, boolean locked, Vec3 pos, double radius) {
        // Preserve current lockTick when setting other fields
        LockedInfo current = getLockedInfo(player);
        player.setData(LOCKED_INFO.get(),
                new LockedInfo(locked, pos, radius, current.lockTick()));
    }

    public static void setLockTick(Player player, int tick) {
        LockedInfo current = getLockedInfo(player);
        player.setData(LOCKED_INFO.get(),
                new LockedInfo(current.locked(), current.hookPos(), current.lockRadius(), tick));
    }

    // Optional: convenience method to set all fields at once
    public static void setLockedInfoFull(Player player, boolean locked, Vec3 pos, double radius, int tick) {
        player.setData(LOCKED_INFO.get(), new LockedInfo(locked, pos, radius, tick));
    }
}
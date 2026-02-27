package com.grapnel;

import com.grapnel.datagen.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

// 1. 指定 Bus 为 MOD
@EventBusSubscriber(modid = Grapnel.MOD_ID)
public class GrapnelDataGen {

    // 2. 监听服务端数据生成事件
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookUpProvider = event.getLookupProvider();

        BlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(packOutput, lookUpProvider);

        generator.addProvider(true, blockTagsProvider);
        generator.addProvider(true, new ModENUSLanProvider(packOutput));
        generator.addProvider(true, new ModZHCNLanProvider(packOutput));
        generator.addProvider(true, new ModItemTagsProvider(packOutput, lookUpProvider));
        generator.addProvider(true, new ModEnchantmentTagsProvider(packOutput, lookUpProvider));
        generator.addProvider(true, new ModEnchantmentsProvider(packOutput, lookUpProvider));
    }

}

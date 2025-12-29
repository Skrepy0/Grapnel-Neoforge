package com.grapnel;

import com.grapnel.datagen.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Grapnel.MOD_ID)

public class GrapnelDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookUpProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        BlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(packOutput, lookUpProvider, existingFileHelper);
        generator.addProvider(event.includeClient(), new ModENUSLanProvider(packOutput));
        generator.addProvider(event.includeClient(), new ModZHCNLanProvider(packOutput));
        generator.addProvider(event.includeClient(), new ModEnchantmentTagsProvider(packOutput,lookUpProvider,existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemTagsProvider(packOutput,lookUpProvider,blockTagsProvider.contentsGetter(),existingFileHelper));
        generator.addProvider(event.includeClient(), new ModEnchantmentsProvider(packOutput, lookUpProvider));
    }
}

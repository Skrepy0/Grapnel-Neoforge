package com.grapnel.datagen;

import com.grapnel.Grapnel;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModENUSLanProvider extends LanguageProvider {
    public ModENUSLanProvider(PackOutput output) {
        super(output, Grapnel.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        add("enchantment.grapnel.grapnel", "Grapnel");
        add("command.grapnel.grapnel_settings.enableFailingBuffer.enable", "§6[FailingBuffer] §aEnabled§r");
        add("command.grapnel.grapnel_settings.enableFailingBuffer.disable", "§6[FailingBuffer] §cDisabled§r");
    }
}

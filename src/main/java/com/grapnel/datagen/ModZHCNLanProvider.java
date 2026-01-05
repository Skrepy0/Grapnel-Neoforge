package com.grapnel.datagen;

import com.grapnel.Grapnel;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModZHCNLanProvider extends LanguageProvider {
    public ModZHCNLanProvider(PackOutput output) {
        super(output, Grapnel.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add("enchantment.grapnel.grapnel", "抓钩");
        add("grapnel.configuration.failingBuffer", "§a摔落缓冲§r");

        add("command.grapnel.grapnel_settings.enableFailingBuffer.enable", "§a已启用§6[FailingBuffer]§r");
        add("command.grapnel.grapnel_settings.enableFailingBuffer.disable", "§c已禁用§6[FailingBuffer]§r");
    }
}

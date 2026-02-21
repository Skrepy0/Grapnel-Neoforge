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
        add("enchantment.grapnel.grapnel.desc", "使用附魔有§6[抓钩]§r的钓竿后，收杆时将玩家拉向鱼漂");
        add("enchantment.grapnel.toughness", "坚韧");
        add("enchantment.grapnel.toughness.desc", "增加钓竿的鱼线强度");
        add("grapnel.configuration.fallingBuffer", "§a摔落缓冲§r");
        add("grapnel.configuration.fallingBuffer.tooltip","玩家使用附魔有§6[抓钩]§r的钓竿后，下一次落地不受伤害");

        add("command.grapnel.show_value","配置[%s]目前为：%s");
        add("command.grapnel.grapnel_settings.enableFallingBuffer.enable", "§a已启用§6[FallingBuffer]§r");
        add("command.grapnel.grapnel_settings.enableFallingBuffer.disable", "§c已禁用§6[FallingBuffer]§r");
    }
}

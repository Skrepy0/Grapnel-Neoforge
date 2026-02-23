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
        add("grapnel.configuration.fallingBuffer", "摔落缓冲");
        add("grapnel.configuration.grapnelCheck", "抓钩检测");
        add("grapnel.configuration.grabWall", "钓竿抓墙");
        add("grapnel.configuration.grabWall.tooltip", "启用后，鱼竿可以勾住墙面");
        add("grapnel.configuration.grapnelCheck.tooltip", "启用后，钓竿勾住方块或实体时，收杆才触发§6[抓钩]§r附魔效果");
        add("grapnel.configuration.fallingBuffer.tooltip", "启用后，玩家使用附魔有§6[抓钩]§r的钓竿后，下一次落地不受伤害");

        add("command.grapnel.config.unchanged", "的状态§c未发生有效更改§r");
        add("command.grapnel.config.changed", "已被更改为");

        add("command.grapnel.show_value", "配置[%s]目前为：%s");
    }
}

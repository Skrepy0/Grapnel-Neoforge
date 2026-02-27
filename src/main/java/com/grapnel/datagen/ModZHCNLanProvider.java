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
        add("enchantment.grapnel.swing", "摆荡");
        add("enchantment.grapnel.swing.desc", "使用钓鱼竿抓墙后，鱼线对你有拉力，可以用鱼竿来摆荡");
        add("grapnel.configuration.fallingBuffer", "摔落缓冲");
        add("grapnel.configuration.grapnelCheck", "抓钩检测");
        add("grapnel.configuration.grabWall", "钓竿抓墙");
        add("grapnel.configuration.grabWall.tooltip", "启用后，鱼竿可以勾住墙面");
        add("grapnel.configuration.grapnelCheck.tooltip", "启用后，钓竿勾住方块或实体时，收杆才触发§6[抓钩]§r附魔效果");
        add("grapnel.configuration.fallingBuffer.tooltip", "启用后，玩家使用附魔有§6[抓钩]§r的钓竿后，下一次落地不受伤害");
        add("grapnel.configuration.positionClampThreshold", "位置修正阈值(§d摆荡§r附魔)");
        add("grapnel.configuration.positionClampThreshold.tooltip", "当玩家荡得太快超出绳长时，系统会强制把玩家拉回来。这个数值决定了允许你超出绳长多少格后才开始拉。数值越小，绳子越‘硬’；数值越大，绳子越‘松’。");
        add("grapnel.configuration.energyCompensationFactor", "能量补偿系数(§d摆荡§r附魔)");
        add("grapnel.configuration.energyCompensationFactor.tooltip", "补偿空气阻力造成的速度损失。数值越大，荡得越高；设为 §d1.0§r 时无阻力。");
        add("grapnel.configuration.lockProtectionTicks", "锁保护时间(§d摆荡§r附魔)");
        add("grapnel.configuration.lockProtectionTicks.tooltip", "锁定后不进行物理计算的缓冲时间，防止初始速度突变。防止玩家刚飞出去就被瞬间弹回或者卡住");

        add("command.grapnel.config.unchanged", "的状态§c未发生有效更改§r");
        add("command.grapnel.config.changed", "已被更改为");

        add("command.grapnel.show_value", "配置[%s]目前为：%s");
    }
}

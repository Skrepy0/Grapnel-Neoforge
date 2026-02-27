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
        // Enchantments
        add("enchantment.grapnel.grapnel", "Grapnel");
        add("enchantment.grapnel.grapnel.desc", "After using a fishing rod enchanted with §6[Grapnel]§r, reeling in pulls the player towards the bobber.");
        add("enchantment.grapnel.toughness", "Toughness");
        add("enchantment.grapnel.toughness.desc", "Increases fishing line strength, allowing you to cast farther and reducing the chance of the line breaking.");
        add("enchantment.grapnel.swing", "Swing");
        add("enchantment.grapnel.swing.desc", "Hook onto walls with your fishing rod to swing through the air.");
        // Configuration
        add("grapnel.configuration.fallingBuffer", "Fall Buffer§r");
        add("grapnel.configuration.grapnelCheck", "Grapnel Detection");
        add("grapnel.configuration.grabWall", "Grab Wall");
        add("grapnel.configuration.grabWall.tooltip", "When enabled, the fishing rod can hook onto walls.");
        add("grapnel.configuration.grapnelCheck.tooltip", "When enabled, the §6[Grapnel]§r enchantment only triggers when the hook is stuck in a block or entity.");
        add("grapnel.configuration.fallingBuffer.tooltip", "When enabled, the player takes no fall damage on the first landing after using a fishing rod enchanted with §6[Grapnel]§r.");
        add("grapnel.configuration.positionClampThreshold", "Position Clamp Threshold (§dSwing§r Enchant)");
        add("grapnel.configuration.positionClampThreshold.tooltip", "When swinging too fast and exceeding the rope length, the system forces the player back. This value determines how many blocks beyond the rope length you are allowed to go before being pulled back. Smaller values make the rope feel 'stiffer'; larger values make it feel 'looser'.");
        add("grapnel.configuration.energyCompensationFactor", "Energy Compensation Factor (§dSwing§r Enchant)");
        add("grapnel.configuration.energyCompensationFactor.tooltip", "Compensates for speed loss caused by air resistance. Higher values allow you to swing higher. Set to §d1.0§r for zero air resistance.");
        add("grapnel.configuration.lockProtectionTicks", "Lock Protection Ticks (§dSwing§r Enchant)");
        add("grapnel.configuration.lockProtectionTicks.tooltip", "Buffer time after locking where physics calculations are skipped to prevent sudden velocity changes. Prevents the player from being instantly snapped back or stuck right after launching.");

        // Commands
        add("command.grapnel.show_value", "Config [%s] is currently set to: %s");
        add("command.grapnel.config.changed", "has been changed to");
        add("command.grapnel.config.unchanged", " status §chas not been effectively changed§r");
        add("command.grapnel.grapnel_settings.enableFallingBuffer.enable", "§6[Fall Buffer] §aEnabled§r");
        add("command.grapnel.grapnel_settings.enableFallingBuffer.disable", "§6[Fall Buffer] §cDisabled§r");
    }
}
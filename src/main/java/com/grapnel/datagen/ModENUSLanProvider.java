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

        // Configuration
        add("grapnel.configuration.fallingBuffer", "Fall Buffer§r");
        add("grapnel.configuration.grapnelCheck", "Grapnel Detection");
        add("grapnel.configuration.grabWall", "Grab Wall");
        add("grapnel.configuration.grabWall.tooltip", "When enabled, the fishing rod can hook onto walls.");
        add("grapnel.configuration.grapnelCheck.tooltip", "When enabled, the §6[Grapnel]§r enchantment only triggers when the hook is stuck in a block or entity.");
        add("grapnel.configuration.fallingBuffer.tooltip", "When enabled, the player takes no fall damage on the first landing after using a fishing rod enchanted with §6[Grapnel]§r.");

        // Commands
        add("command.grapnel.show_value", "Config [%s] is currently set to: %s");
        add("command.grapnel.config.changed", "has been changed to");
        add("command.grapnel.config.unchanged", " status §chas not been effectively changed§r");
        add("command.grapnel.grapnel_settings.enableFallingBuffer.enable", "§6[Fall Buffer] §aEnabled§r");
        add("command.grapnel.grapnel_settings.enableFallingBuffer.disable", "§6[Fall Buffer] §cDisabled§r");
    }
}
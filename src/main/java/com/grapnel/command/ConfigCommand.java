package com.grapnel.command;

import com.grapnel.Config;
import com.grapnel.Grapnel;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static net.minecraft.network.chat.Component.translatable;

public class ConfigCommand {
    private static void showValue(CommandContext<CommandSourceStack> context, String configId, String configValue) {
        context.getSource().sendSuccess(() -> Component.literal(Component.translatable("command.grapnel.show_value").getString().formatted(configId, configValue)), false);
    }

    private static boolean boolConfig(CommandContext<CommandSourceStack> context, boolean preStatus, String configId) {
        boolean enabled = BoolArgumentType.getBool(context, "enabled");
        if (preStatus == enabled) {
            Component message =
                    Component.literal("§a[" + configId + "]§r")
                            .append(translatable(
                                    "command.grapnel.config.unchanged"));
            Grapnel.LOGGER.info(message.getString());
            context.getSource().sendSuccess(() -> message, false);
            return false;
        }
        Component message =
                Component.literal("§a[" + configId + "]§r")
                        .append(translatable(
                                "command.grapnel.config.changed"))
                        .append(enabled ? "§a[true]§r" : "§c[false]§r");
        Grapnel.LOGGER.info(message.getString());
        context.getSource().sendSuccess(() -> message, false);
        return true;
    }

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("grapnel_settings")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("enableFallingBuffer")
                        .executes(context -> {
                            showValue(context, "enableFallingBuffer", String.valueOf(Config.getFailingBuffer()));
                            return 1;
                        })
                        .then(Commands.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> {
                                    boolean preValue = Config.getFailingBuffer();
                                    if (boolConfig(context, preValue, "enableFallingBuffer")) {
                                        Config.setFailingBuffer(!preValue);
                                    }
                                    return 1;
                                })))
                .then(Commands.literal("grapnelCheck")
                        .executes(context -> {
                            showValue(context, "grapnelCheck", String.valueOf(Config.getGrapnelCheck()));
                            return 1;
                        })
                        .then(Commands.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> {
                                    boolean preValue = Config.getGrapnelCheck();
                                    if (boolConfig(context, preValue, "grapnelCheck")) {
                                        Config.setGrapnelCheck(!preValue);
                                    }
                                    return 1;
                                })))
                .then(Commands.literal("grabWall")
                        .executes(context -> {
                            showValue(context, "grabWall", String.valueOf(Config.getGrabWall()));
                            return 1;
                        })
                        .then(Commands.argument("enabled", BoolArgumentType.bool())
                                .executes(
                                        context -> {
                                            boolean preValue = Config.getGrabWall();
                                            if (boolConfig(context, preValue, "grabWall")) {
                                                Config.setGrabWall(!preValue);
                                            }
                                            return 1;
                                        }
                                ))));
    }
}

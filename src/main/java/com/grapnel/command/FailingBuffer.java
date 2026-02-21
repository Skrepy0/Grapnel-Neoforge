package com.grapnel.command;

import com.grapnel.Config;
import com.grapnel.Grapnel;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static com.grapnel.Config.setFailingBuffer;

public class FailingBuffer {

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("grapnel_settings")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("enableFallingBuffer")
                        .executes(context -> {
                            context.getSource().sendSuccess(() -> Component.literal(Component.translatable("command.grapnel.show_value").getString().formatted("FallingBuffer", Config.getFailingBuffer())), false);
                            return 1;
                        })
                        .then(Commands.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> {
                                    boolean enabled = BoolArgumentType.getBool(context, "enabled");
                                    setFailingBuffer(enabled);
                                    Component message = enabled ?
                                            Component.translatable("command.grapnel.grapnel_settings.enableFallingBuffer.enable") :
                                            Component.translatable("command.grapnel.grapnel_settings.enableFallingBuffer.disable");

                                    Grapnel.LOGGER.info(message.getString());
                                    context.getSource().sendSuccess(() -> message, false);
                                    return 1;
                                }))));
    }
}

package com.grapnel.command;

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
                .then(Commands.literal("enableFailingBuffer")
                        .then(Commands.argument("enabled", BoolArgumentType.bool())
                                .executes(context -> {
                                    boolean enabled = BoolArgumentType.getBool(context, "enabled");
                                    setFailingBuffer(enabled);
                                    Component message = enabled ?
                                            Component.translatable("command.grapnel.grapnel_settings.enableFailingBuffer.enable") :
                                            Component.translatable("command.grapnel.grapnel_settings.enableFailingBuffer.disable");

                                    Grapnel.LOGGER.info(message.getString());
                                    context.getSource().sendSuccess(() -> message, false);

                                    return 1;
                                }))));
    }
}

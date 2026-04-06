package com.maskedmatters.getmeout.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class GetMeOutClient implements ClientModInitializer {

    private static KeyMapping quitKeybind;

    @Override
    public void onInitializeClient() {

        quitKeybind = KeyBindingHelper.registerKeyBinding(
                new KeyMapping(
                        "key.getmeout.quit",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_PERIOD,
                        "key.categories.misc"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (quitKeybind.consumeClick()) {
                if (client.getConnection() != null) {
                    assert client.level != null;
                    client.level.disconnect();
                }
            }
        });
    }
}
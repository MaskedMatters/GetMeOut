package com.maskedmatters.getmeout;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class GetMeOutClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		KeyBinding quitKeybind = KeyBindingHelper.registerKeyBinding(
				new KeyBinding(
						"key.getmeout.quit",
						InputUtil.Type.KEYSYM,
						GLFW.GLFW_KEY_UNKNOWN,
						"category.getmeout"
				)
		);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (quitKeybind.wasPressed() && client.world != null) {
				client.world.disconnect(Text.literal("Disconnected!"));
				client.disconnectWithSavingScreen();
				client.setScreen(new TitleScreen());
			}
		});
	}
}
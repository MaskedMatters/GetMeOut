package com.maskedmatters.getmeout;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class GetMeOutClient implements ClientModInitializer {

	private static KeyMapping quitKeybind;
	private static final KeyMapping.Category GETMEOUT_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("getmeout", "main"));

	@Override
	public void onInitializeClient() {

		quitKeybind = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"key.getmeout.quit",
						InputConstants.Type.KEYSYM,
						GLFW.GLFW_KEY_PERIOD,
						GETMEOUT_CATEGORY
				)
		);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (quitKeybind.consumeClick()) {
				if (client.getConnection() != null) {
					client.disconnect(new TitleScreen(), false);
				}
			}
		});
	}
}
package com.maskedmatters.getmeout

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.TitleScreen
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW


object GetMeOutClient : ClientModInitializer {
	private lateinit var quitKeyBinding: KeyBinding

	override fun onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		quitKeyBinding = KeyBindingHelper.registerKeyBinding(
			KeyBinding(
				"key.getmeout.quit",  // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM,  // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_UNKNOWN,  // The keycode of the key
				"category.getmeout" // The translation key of the keybinding's category.
			)
		)

		ClientTickEvents.END_CLIENT_TICK.register {
			val client = MinecraftClient.getInstance()

			if (quitKeyBinding.wasPressed() && client.world != null) {
				// Pass the screen directly to disconnect, so it sets it internally.
				client.world?.disconnect(Text.literal("Disconnected"))
				client.disconnectWithSavingScreen()
				client.setScreen(TitleScreen())
			}
		}
	}
}
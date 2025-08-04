package com.maskedmatters.getmeout

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object GetMeOut : ModInitializer {
    private val logger = LoggerFactory.getLogger("get-me-out")

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("GetMeOut has loaded!")
	}
}
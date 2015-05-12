package com.goodformentertainment.canary.r2w;

import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

public class ReturnConfig {
	private final PropertiesFile cfg;
	
	public ReturnConfig(final ReturnPlugin plugin) {
		cfg = Configuration.getPluginConfig(plugin);
	}
	
	// public String getHubWorld() {
	// return cfg.getString("hubWorld", "default");
	// }
	//
	// public String getWorldName() {
	// return cfg.getString("worldName", "xis");
	// }
	//
	// public int getMaxSize() {
	// return cfg.getInt("maxSize", 100);
	// }
	//
	// public int getHeight() {
	// return cfg.getInt("islandHeight", 64);
	// }
}

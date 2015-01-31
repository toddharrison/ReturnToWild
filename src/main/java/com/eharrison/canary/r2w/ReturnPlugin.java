package com.eharrison.canary.r2w;

import net.canarymod.Canary;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;

public class ReturnPlugin extends Plugin {
	public static Logman LOG;
	
	private final ReturnConfig config;
	private final TemplateManager templateManager;
	private final ReturnCommand command;
	
	public ReturnPlugin() {
		ReturnPlugin.LOG = getLogman();
		
		config = new ReturnConfig(this);
		templateManager = new TemplateManager();
		command = new ReturnCommand(templateManager);
	}
	
	@Override
	public boolean enable() {
		boolean success = true;
		
		LOG.info("Enabling " + getName() + " Version " + getVersion());
		LOG.info("Authored by " + getAuthor());
		
		try {
			Canary.commands().registerCommands(command, this, false);
		} catch (final CommandDependencyException e) {
			LOG.error("Error registering commands: ", e);
			success = false;
		}
		
		return success;
	}
	
	@Override
	public void disable() {
		LOG.info("Disabling " + getName());
		Canary.commands().unregisterCommands(this);
		Canary.hooks().unregisterPluginListeners(this);
	}
}

package com.eharrison.canary.r2w;

import java.io.IOException;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.DimensionType;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

public class ReturnCommand implements CommandListener {
	private final TemplateManager templateManager;
	
	public ReturnCommand(final TemplateManager templateManager) {
		this.templateManager = templateManager;
	}
	
	@Command(aliases = {
			"r2w", "rtw", "rwild", "returntowild"
	}, description = "ReturnToWild commands", permissions = {
		"r2w.command"
	}, toolTip = "/r2w")
	public void rtwCommand(final MessageReceiver caller, final String[] parameters) {
		if (caller instanceof Player) {
			final Player player = (Player) caller;
			player.message("Hello world!");
		}
	}
	
	@Command(aliases = {
			"create", "c"
	}, parent = "r2w", helpLookup = "r2w create", description = "Create world template", permissions = {
		"r2w.command.create"
	}, toolTip = "/r2w create <world_name> <world_dimension>", min = 3, max = 3)
	public void createTemplate(final MessageReceiver caller, final String[] parameters)
			throws IOException {
		templateManager.createTemplate(parameters[1], DimensionType.fromName(parameters[2]));
	}
	
	@Command(aliases = {
			"remove", "r"
	}, parent = "r2w", helpLookup = "r2w create", description = "Remove world template", permissions = {
		"r2w.command.remove"
	}, toolTip = "/r2w remove <world_name> <world_dimension>", min = 3, max = 3)
	public void removeTemplate(final MessageReceiver caller, final String[] parameters)
			throws IOException {
		templateManager.removeTemplate(parameters[1], DimensionType.fromName(parameters[2]));
	}
}

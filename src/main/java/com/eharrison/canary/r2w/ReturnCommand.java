package com.eharrison.canary.r2w;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
			throws IOException, InterruptedException, ExecutionException {
		final Future<Boolean> future = templateManager.createTemplate(parameters[1],
				DimensionType.fromName(parameters[2]));
		if (future.get()) {
			ReturnPlugin.LOG.info("Completed creating template");
		} else {
			ReturnPlugin.LOG.info("Failed creating template");
		}
	}
	
	@Command(aliases = {
			"remove", "r"
	}, parent = "r2w", helpLookup = "r2w remove", description = "Remove world template", permissions = {
		"r2w.command.remove"
	}, toolTip = "/r2w remove <world_name> <world_dimension>", min = 3, max = 3)
	public void removeTemplate(final MessageReceiver caller, final String[] parameters)
			throws IOException, InterruptedException, ExecutionException {
		final Future<Boolean> future = templateManager.removeTemplate(parameters[1],
				DimensionType.fromName(parameters[2]));
		if (future.get()) {
			ReturnPlugin.LOG.info("Completed removing template");
		} else {
			ReturnPlugin.LOG.info("Failed removing template");
		}
	}
	
	@Command(aliases = {
			"restore", "r"
	}, parent = "r2w", helpLookup = "r2w restore", description = "Restore from template", permissions = {
		"r2w.command.restore"
	}, toolTip = "/r2w restore <world_name> <world_dimension> x1 y1 z1 x2 y2 z2", min = 9, max = 9)
	public void restore(final MessageReceiver caller, final String[] parameters)
			throws InterruptedException, ExecutionException {
		final int x1 = Integer.parseInt(parameters[3]);
		final int y1 = Integer.parseInt(parameters[4]);
		final int z1 = Integer.parseInt(parameters[5]);
		final int x2 = Integer.parseInt(parameters[6]);
		final int y2 = Integer.parseInt(parameters[7]);
		final int z2 = Integer.parseInt(parameters[8]);
		final Future<Boolean> future = templateManager.restore(parameters[1],
				DimensionType.fromName(parameters[2]), x1, y1, z1, x2, y2, z2);
		if (future.get()) {
			ReturnPlugin.LOG.info("Completed restoring template");
		} else {
			ReturnPlugin.LOG.info("Failed restoring template");
		}
	}
}

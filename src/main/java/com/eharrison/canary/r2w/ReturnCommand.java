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
		sendMessage(caller, "Hello world!");
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
			sendMessage(caller, "Completed creating template");
		} else {
			sendMessage(caller, "Failed creating template");
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
			sendMessage(caller, "Completed removing template");
		} else {
			sendMessage(caller, "Failed removing template");
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
			sendMessage(caller, "Completed restoring template");
		} else {
			sendMessage(caller, "Failed restoring template");
		}
	}
	
	@Command(aliases = {
			"update", "u"
	}, parent = "r2w", helpLookup = "r2w update", description = "Update the template", permissions = {
		"r2w.command.update"
	}, toolTip = "/r2w update <world_name> <world_dimension> x1 y1 z1 x2 y2 z2", min = 9, max = 9)
	public void update(final MessageReceiver caller, final String[] parameters)
			throws InterruptedException, ExecutionException {
		final int x1 = Integer.parseInt(parameters[3]);
		final int y1 = Integer.parseInt(parameters[4]);
		final int z1 = Integer.parseInt(parameters[5]);
		final int x2 = Integer.parseInt(parameters[6]);
		final int y2 = Integer.parseInt(parameters[7]);
		final int z2 = Integer.parseInt(parameters[8]);
		final Future<Boolean> future = templateManager.update(parameters[1],
				DimensionType.fromName(parameters[2]), x1, y1, z1, x2, y2, z2);
		if (future.get()) {
			sendMessage(caller, "Completed updating template");
		} else {
			sendMessage(caller, "Failed updating template");
		}
	}
	
	private void sendMessage(final MessageReceiver caller, final String message) {
		if (caller instanceof Player) {
			final Player player = (Player) caller;
			player.message(message);
		} else {
			ReturnPlugin.LOG.info(message);
		}
	}
}

package com.goodformentertainment.canary.r2w;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.canarymod.LineTracer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

public class ReturnCommand implements CommandListener {
	private final TemplateManager templateManager;
	
	public ReturnCommand(final TemplateManager templateManager) {
		this.templateManager = templateManager;
	}
	
	@Command(aliases = {
		"info"
	}, parent = "r2w", description = "Get Block info", permissions = {
		"r2w.info"
	}, toolTip = "/r2w info x y z", min = 4, max = 4)
	public void getBlockInfo(final MessageReceiver caller, final String[] parameters)
			throws InterruptedException, ExecutionException {
		if (caller instanceof Player) {
			final int x = Integer.parseInt(parameters[1]);
			final int y = Integer.parseInt(parameters[2]);
			final int z = Integer.parseInt(parameters[3]);
			
			final Player player = (Player) caller;
			final Block block = player.getWorld().getBlockAt(x, y, z);
			sendMessage(caller, "Type: " + block.getType().getId() + ":" + block.getType().getData());
			sendMessage(caller, "Data: " + block.getData());
			sendMessage(caller, "Properties: " + block.getProperties());
		}
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
		"create"
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
		"remove"
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
	}, toolTip = "/r2w restore [world_name world_dimension] [x1 y1 z1 [x2 y2 z2]]", min = 1, max = 9)
	public void restore(final MessageReceiver caller, final String[] parameters)
			throws InterruptedException, ExecutionException {
		boolean set = false;
		String world = null;
		String dimension = null;
		int x1 = 0;
		int y1 = 0;
		int z1 = 0;
		int x2 = 0;
		int y2 = 0;
		int z2 = 0;
		
		if (caller instanceof Player) {
			final Player player = (Player) caller;
			if (parameters.length == 1) {
				final Block block = getBlockLookingAt(player);
				world = block.getWorld().getName();
				dimension = block.getWorld().getType().getName();
				x1 = x2 = block.getX();
				y1 = y2 = block.getY();
				z1 = z2 = block.getZ();
				set = true;
			} else if (parameters.length == 4) {
				world = player.getWorld().getName();
				dimension = player.getWorld().getType().getName();
				x1 = x2 = Integer.parseInt(parameters[2]);
				y1 = y2 = Integer.parseInt(parameters[3]);
				z1 = z2 = Integer.parseInt(parameters[4]);
				set = true;
			} else if (parameters.length == 7) {
				world = player.getWorld().getName();
				dimension = player.getWorld().getType().getName();
				x1 = Integer.parseInt(parameters[2]);
				y1 = Integer.parseInt(parameters[3]);
				z1 = Integer.parseInt(parameters[4]);
				x2 = Integer.parseInt(parameters[5]);
				y2 = Integer.parseInt(parameters[6]);
				z2 = Integer.parseInt(parameters[7]);
				set = true;
			}
		}
		
		if (!set) {
			world = parameters[1];
			dimension = parameters[2];
			x1 = Integer.parseInt(parameters[3]);
			y1 = Integer.parseInt(parameters[4]);
			z1 = Integer.parseInt(parameters[5]);
			x2 = Integer.parseInt(parameters[6]);
			y2 = Integer.parseInt(parameters[7]);
			z2 = Integer.parseInt(parameters[8]);
		}
		
		final Future<Boolean> future = templateManager.restore(world,
				DimensionType.fromName(dimension), x1, y1, z1, x2, y2, z2);
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
	
	private Block getBlockLookingAt(final Player player) {
		final LineTracer lineTracer = new LineTracer(player);
		return lineTracer.getNextBlock();
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

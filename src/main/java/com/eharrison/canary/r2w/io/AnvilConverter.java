package com.eharrison.canary.r2w.io;

import java.util.HashMap;
import java.util.Map;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.properties.helpers.AnvilProperties;
import net.canarymod.api.world.blocks.properties.helpers.BannerProperties;
import net.canarymod.api.world.blocks.properties.helpers.BedProperties;
import net.canarymod.api.world.blocks.properties.helpers.BrewingStandProperties;
import net.canarymod.api.world.blocks.properties.helpers.ButtonProperties;
import net.canarymod.api.world.blocks.properties.helpers.CactusProperties;
import net.canarymod.api.world.blocks.properties.helpers.CakeProperties;
import net.canarymod.api.world.blocks.properties.helpers.CauldronProperties;
import net.canarymod.api.world.blocks.properties.helpers.ChestProperties;
import net.canarymod.api.world.blocks.properties.helpers.CocoaPlantProperties;
import net.canarymod.api.world.blocks.properties.helpers.CommandBlockProperties;
import net.canarymod.api.world.blocks.properties.helpers.CropsProperties;
import net.canarymod.api.world.blocks.properties.helpers.DaylightDetectorProperties;
import net.canarymod.api.world.blocks.properties.helpers.DirectionalBlockProperties;
import net.canarymod.api.world.blocks.properties.helpers.DispenserProperties;
import net.canarymod.api.world.blocks.properties.helpers.DoorProperties;
import net.canarymod.api.world.blocks.properties.helpers.DoublePlantProperties;
import net.canarymod.api.world.blocks.properties.helpers.DoubleStoneSlabProperties;
import net.canarymod.api.world.blocks.properties.helpers.EndPortalFrameProperties;
import net.canarymod.api.world.blocks.properties.helpers.EnderChestProperties;
import net.canarymod.api.world.blocks.properties.helpers.FarmlandProperties;
import net.canarymod.api.world.blocks.properties.helpers.FenceGateProperties;
import net.canarymod.api.world.blocks.properties.helpers.FireProperties;
import net.canarymod.api.world.blocks.properties.helpers.FurnaceProperties;
import net.canarymod.api.world.blocks.properties.helpers.HopperProperties;
import net.canarymod.api.world.blocks.properties.helpers.HugeMushroomProperties;
import net.canarymod.api.world.blocks.properties.helpers.JukeboxProperties;
import net.canarymod.api.world.blocks.properties.helpers.LadderProperties;
import net.canarymod.api.world.blocks.properties.helpers.LeavesProperties;
import net.canarymod.api.world.blocks.properties.helpers.LeverProperties;
import net.canarymod.api.world.blocks.properties.helpers.LiquidProperties;
import net.canarymod.api.world.blocks.properties.helpers.LogProperties;
import net.canarymod.api.world.blocks.properties.helpers.NetherWartProperties;
import net.canarymod.api.world.blocks.properties.helpers.PistonHeadProperties;
import net.canarymod.api.world.blocks.properties.helpers.PistonProperties;
import net.canarymod.api.world.blocks.properties.helpers.PortalProperties;
import net.canarymod.api.world.blocks.properties.helpers.PressurePlateProperties;
import net.canarymod.api.world.blocks.properties.helpers.RailPoweredProperties;
import net.canarymod.api.world.blocks.properties.helpers.RailProperties;
import net.canarymod.api.world.blocks.properties.helpers.RedstoneComparatorProperties;
import net.canarymod.api.world.blocks.properties.helpers.RedstoneRepeaterProperties;
import net.canarymod.api.world.blocks.properties.helpers.RedstoneWireProperties;
import net.canarymod.api.world.blocks.properties.helpers.ReedProperties;
import net.canarymod.api.world.blocks.properties.helpers.RotatedPillarProperties;
import net.canarymod.api.world.blocks.properties.helpers.SandProperties;
import net.canarymod.api.world.blocks.properties.helpers.SaplingProperties;
import net.canarymod.api.world.blocks.properties.helpers.SkullProperties;
import net.canarymod.api.world.blocks.properties.helpers.SlabProperties;
import net.canarymod.api.world.blocks.properties.helpers.SnowProperties;
import net.canarymod.api.world.blocks.properties.helpers.SpongeProperties;
import net.canarymod.api.world.blocks.properties.helpers.StairsProperties;
import net.canarymod.api.world.blocks.properties.helpers.StandingSignProperties;
import net.canarymod.api.world.blocks.properties.helpers.StemProperties;
import net.canarymod.api.world.blocks.properties.helpers.TNTProperties;
import net.canarymod.api.world.blocks.properties.helpers.TorchProperties;
import net.canarymod.api.world.blocks.properties.helpers.TrapDoorProperties;
import net.canarymod.api.world.blocks.properties.helpers.TripwireHookProperties;
import net.canarymod.api.world.blocks.properties.helpers.TripwireProperties;
import net.canarymod.api.world.blocks.properties.helpers.VineProperties;
import net.canarymod.api.world.blocks.properties.helpers.WallProperties;
import net.canarymod.api.world.blocks.properties.helpers.WallSignProperties;
import net.canarymod.api.world.blocks.properties.helpers.WeightedPressurePlateProperties;
import net.canarymod.api.world.blocks.properties.helpers.WoodProperties;
import net.canarymod.tasks.ServerTask;

public final class AnvilConverter {
	public static void convert(final Block block, final byte newType, final int newData) {
		// Derived properties
		// Convert unsigned byte to short
		final short id = (short) (newType & 0xFF);
		final int data;
		
		Integer stage = null;
		Integer level = null;
		LogProperties.Axis axis = null;
		BlockFace.Axis blockAxis = null;
		Boolean noDecay = null;
		Boolean checkDecay = null;
		BlockFace facing = null;
		Boolean triggered = null;
		Boolean occupied = null;
		BedProperties.Half bedHalf = null;
		DoorProperties.Half doorHalf = null;
		RailProperties.Direction railShape = null;
		Boolean powered = null;
		Boolean extended = null;
		Boolean seamless = null;
		SlabProperties.Half half = null;
		StairsProperties.Half stairHalf = null;
		Boolean explode = null;
		Integer age = null;
		Integer power = null;
		Integer moisture = null;
		Integer rotation = null;
		DoorProperties.HingePosition hinge = null;
		Boolean open = null;
		Integer layer = null;
		Boolean hasRecord = null;
		Integer bites = null;
		Integer delay = null;
		WoodProperties.Variant variant = null;
		Boolean south = null;
		Boolean west = null;
		Boolean north = null;
		Boolean east = null;
		Boolean eye = null;
		Boolean attached = null;
		Boolean suspended = null;
		Boolean disarmed = null;
		Boolean nodrop = null;
		Integer damage = null;
		RedstoneComparatorProperties.Mode mode = null;
		Boolean enabled = null;
		HugeMushroomProperties.Variant mushroomVariant = null;
		PistonHeadProperties.Type pistonType = null;
		LeverProperties.Orientation leverFacing = null;
		TrapDoorProperties.Half trapdoorHalf = null;
		BlockFace.Axis hayAxis = null;
		DoublePlantProperties.Variant plantVariant = null;
		DoublePlantProperties.Half plantHalf = null;
		Boolean wet = null;
		WallProperties.Variant wallVariant = null;
		SandProperties.Variant sandVariant = null;
		
		switch (id) {
			case 6: // minecraft:sapling
				
				variant = toValue(getBitFlag(newData, 0, 3), WoodProperties.Variant.OAK,
						WoodProperties.Variant.SPRUCE, WoodProperties.Variant.BIRCH,
						WoodProperties.Variant.JUNGLE, WoodProperties.Variant.ACACIA,
						WoodProperties.Variant.DARK_OAK);
				stage = getBitFlag(newData, 3, 1);
				
				block.setTypeId(id);
				SaplingProperties.applyType(block, variant);
				SaplingProperties.applyStage(block, stage);
				block.update();
				
				break;
			case 8: // minecraft:flowing_water
			case 10: // minecraft:flowing_lava
				
				// level = (Math.abs(newData - 16) + 7) % 16;
				level = newData;
				
				block.setTypeId(id);
				LiquidProperties.applyLevel(block, level);
				block.update();
				
				break;
			case 9: // minecraft:water
			case 11: // minecraft:lava
				
				block.setTypeId(id);
				block.update();
				
				break;
			case 12: // minecraft:sand
				
				sandVariant = toValue(newData, SandProperties.Variant.SAND, SandProperties.Variant.RED_SAND);
				
				block.setTypeId(id);
				SandProperties.applyVariant(block, sandVariant);
				block.update();
				
				break;
			case 17: // minecraft:log
				
				variant = toValue(getBitFlag(newData, 0, 2), WoodProperties.Variant.OAK,
						WoodProperties.Variant.SPRUCE, WoodProperties.Variant.BIRCH,
						WoodProperties.Variant.JUNGLE);
				axis = toValue(getBitFlag(newData, 2, 2), LogProperties.Axis.Y, LogProperties.Axis.X,
						LogProperties.Axis.Z, LogProperties.Axis.NONE);
				
				block.setTypeId(id);
				LogProperties.applyVariant(block, variant);
				LogProperties.applyAxis(block, axis);
				block.update();
				
				break;
			case 162: // minecraft:log2
				
				variant = toValue(getBitFlag(newData, 0, 2), WoodProperties.Variant.ACACIA,
						WoodProperties.Variant.DARK_OAK);
				axis = toValue(getBitFlag(newData, 2, 2), LogProperties.Axis.Y, LogProperties.Axis.X,
						LogProperties.Axis.Z, LogProperties.Axis.NONE);
				
				block.setTypeId(id);
				LogProperties.applyVariant(block, variant);
				LogProperties.applyAxis(block, axis);
				block.update();
				
				break;
			case 18: // minecraft:leaves
				
				variant = toValue(getBitFlag(newData, 0, 2), WoodProperties.Variant.OAK,
						WoodProperties.Variant.SPRUCE, WoodProperties.Variant.BIRCH,
						WoodProperties.Variant.JUNGLE);
				noDecay = toBoolean(getBitFlag(newData, 2, 1));
				checkDecay = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				LeavesProperties.applyVariant(block, variant);
				LeavesProperties.applyDecayable(block, noDecay);
				LeavesProperties.applyCheckDecay(block, checkDecay);
				block.update();
				
				break;
			case 161: // minecraft:leaves2
				
				variant = toValue(getBitFlag(newData, 0, 2), WoodProperties.Variant.ACACIA,
						WoodProperties.Variant.DARK_OAK);
				noDecay = toBoolean(getBitFlag(newData, 2, 1));
				checkDecay = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				LeavesProperties.applyVariant(block, variant);
				LeavesProperties.applyDecayable(block, noDecay);
				LeavesProperties.applyCheckDecay(block, checkDecay);
				block.update();
				
				break;
			case 19: // minecraft:sponge
				
				wet = toBoolean(newData);
				
				block.setTypeId(id);
				SpongeProperties.applyWet(block, wet);
				block.update();
				
				break;
			case 23: // minecraft:dispenser
			case 158: // minecraft:dropper
				
				facing = convertFacing(id, getBitFlag(newData, 0, 3));
				triggered = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				DispenserProperties.applyFacing(block, facing);
				DispenserProperties.applyTriggered(block, triggered);
				block.update();
				
				break;
			case 26: // minecraft:bed
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				occupied = toBoolean(getBitFlag(newData, 2, 1));
				bedHalf = toValue(getBitFlag(newData, 3, 1), BedProperties.Half.FOOT,
						BedProperties.Half.HEAD);
				
				// TODO (E/W not working)
				block.setTypeId(id);
				DirectionalBlockProperties.applyFacing(block, facing);
				BedProperties.applyOccupided(block, occupied);
				BedProperties.applyPart(block, bedHalf);
				block.update();
				
				break;
			case 27: // minecraft:golden_rail
			case 28: // minecraft:detector_rail
			case 157: // minecraft:activator_rail
				
				railShape = toValue(getBitFlag(newData, 0, 3), RailPoweredProperties.Direction.NORTH_SOUTH,
						RailPoweredProperties.Direction.EAST_WEST,
						RailPoweredProperties.Direction.ASCENDING_EAST,
						RailPoweredProperties.Direction.ASCENDING_WEST,
						RailPoweredProperties.Direction.ASCENDING_NORTH,
						RailPoweredProperties.Direction.ASCENDING_SOUTH);
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				RailProperties.applyShape(block, railShape);
				RailPoweredProperties.applyPowered(block, powered);
				block.update();
				
				break;
			case 29: // minecraft:sticky_piston
			case 33: // minecraft:piston
				
				facing = convertFacing(id, getBitFlag(newData, 0, 3));
				extended = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				PistonProperties.applyFacing(block, facing);
				PistonProperties.applyExtended(block, extended);
				block.update();
				
				break;
			case 34: // minecraft:piston_head
			case 36: // minecraft:piston_extension
				
				facing = convertFacing(id, getBitFlag(newData, 0, 3));
				pistonType = toValue(getBitFlag(newData, 3, 1), PistonHeadProperties.Type.DEFAULT,
						PistonHeadProperties.Type.STICKY);
				
				block.setTypeId(id);
				PistonHeadProperties.applyFacing(block, facing);
				PistonHeadProperties.applyType(block, pistonType);
				block.update();
				
				break;
			case 43: // minecraft:double_stone_slab
			case 181: // minecraft:double_stone_slab2
				
				data = getBitFlag(newData, 0, 3);
				seamless = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setType(BlockType.fromIdAndData(id, data));
				DoubleStoneSlabProperties.applySeamless(block, seamless);
				block.update();
				
				break;
			case 44: // minecraft:stone_slab
			case 182: // minecraft:stone_slab2
			case 126: // minecraft:wood_slab
				
				data = getBitFlag(newData, 0, 3);
				half = toValue(getBitFlag(newData, 3, 1), SlabProperties.Half.LOWER,
						SlabProperties.Half.UPPER);
				
				block.setType(BlockType.fromIdAndData(id, data));
				SlabProperties.applyHalf(block, half);
				block.update();
				
				break;
			case 46: // minecraft:tnt
				
				explode = toBoolean(newData);
				
				block.setTypeId(id);
				TNTProperties.applyExplode(block, explode);
				block.update();
				
				break;
			case 50: // minecraft:torch
			case 75: // minecraft:unlit_redstone_torch
			case 76: // minecraft:redstone_torch
				
				facing = convertFacing(id, newData);
				
				block.setTypeId(id);
				TorchProperties.applyFacing(block, facing);
				block.update();
				
				break;
			case 51: // minecraft:fire
				
				age = newData;
				
				block.setTypeId(id);
				FireProperties.applyAge(block, age);
				block.update();
				
				break;
			case 53: // minecraft:oak_stairs
			case 67: // minecraft:cobblestone_stairs
			case 108: // minecraft:brick_stairs
			case 109: // minecraft:stone_brick_stairs
			case 114: // minecraft:nether_brick_stairs
			case 128: // minecraft:sandstone_stairs
			case 134: // minecraft:spruce_stairs
			case 135: // minecraft:birch_stairs
			case 136: // minecraft:jungle_stairs
			case 156: // minecraft:quartz_stairs
			case 163: // minecraft:acacia_stairs
			case 164: // minecraft:dark_oak_stairs
			case 180: // minecraft:red_sandstone_stairs
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				stairHalf = toValue(getBitFlag(newData, 2, 2), StairsProperties.Half.LOWER,
						StairsProperties.Half.UPPER);
				
				block.setTypeId(id);
				StairsProperties.applyFacing(block, facing);
				StairsProperties.applyHalf(block, stairHalf);
				block.update();
				
				break;
			case 54: // minecraft:chest
			case 146: // minecraft:trapped_chest
				
				facing = convertFacing(id, newData);
				
				block.setTypeId(id);
				ChestProperties.applyFacing(block, facing);
				block.update();
				
				break;
			case 55: // minecraft:redstone_wire
				
				power = newData;
				
				block.setTypeId(id);
				RedstoneWireProperties.applyPower(block, power);
				block.update();
				
				break;
			case 59: // minecraft:wheat
			case 141: // minecraft:carrots
			case 142: // minecraft:potatoes
				
				age = newData;
				
				block.setTypeId(id);
				CropsProperties.applyAge(block, age);
				block.update();
				
				break;
			case 60: // minecraft:farmland
				
				moisture = newData;
				
				block.setTypeId(id);
				FarmlandProperties.applyMositure(block, moisture);
				block.update();
				
				break;
			case 61: // minecraft:furnace
			case 62: // minecraft:lit_furnace
				
				facing = convertFacing(id, newData);
				
				block.setTypeId(id);
				FurnaceProperties.applyFacing(block, facing);
				block.update();
				
				break;
			case 63: // minecraft:standing_sign
				
				rotation = newData;
				
				block.setTypeId(id);
				StandingSignProperties.applyRotation(block, rotation);
				block.update();
				
				break;
			case 64: // minecraft:wooden_door
			case 71: // minecraft:iron_door
			case 193: // minecraft:spruce_door
			case 194: // minecraft:birch_door
			case 195: // minecraft:jungle_door
			case 196: // minecraft:acacia_door
			case 197: // minecraft:dark_oak_door
				
				doorHalf = toValue(getBitFlag(newData, 0, 1), DoorProperties.Half.LOWER,
						DoorProperties.Half.UPPER);
				
				block.setTypeId(id);
				DoorProperties.applyHalf(block, doorHalf);
				// TODO half is not first
				switch (doorHalf) {
					case LOWER:
						
						open = toBoolean(getBitFlag(newData, 1, 1));
						facing = convertFacing(id, getBitFlag(newData, 2, 2));
						
						DoorProperties.applyOpen(block, open);
						DoorProperties.applyFacing(block, facing);
						
						break;
					case UPPER:
						
						hinge = toValue(getBitFlag(newData, 1, 1), DoorProperties.HingePosition.LEFT,
								DoorProperties.HingePosition.RIGHT);
						powered = toBoolean(getBitFlag(newData, 2, 1));
						
						DoorProperties.applyHinge(block, hinge);
						DoorProperties.applyPowered(block, powered);
						
						break;
				}
				block.update();
				
				break;
			case 65: // minecraft:ladder
				
				facing = convertFacing(id, newData);
				
				block.setTypeId(id);
				LadderProperties.applyFacing(block, facing);
				block.update();
				
				break;
			case 66: // minecraft:rail
				
				railShape = toValue(newData, RailProperties.Direction.NORTH_SOUTH,
						RailProperties.Direction.EAST_WEST, RailProperties.Direction.ASCENDING_EAST,
						RailProperties.Direction.ASCENDING_WEST, RailProperties.Direction.ASCENDING_NORTH,
						RailProperties.Direction.ASCENDING_SOUTH, RailProperties.Direction.SOUTH_EAST,
						RailProperties.Direction.SOUTH_WEST, RailProperties.Direction.NORTH_WEST,
						RailProperties.Direction.NORTH_EAST);
				
				block.setTypeId(id);
				RailProperties.applyShape(block, railShape);
				block.update();
				
				break;
			case 68: // minecraft:wall_sign
				
				facing = convertFacing(id, newData);
				
				block.setTypeId(id);
				WallSignProperties.applyFacing(block, facing);
				block.update();
				
				break;
			case 69: // minecraft:lever
				
				leverFacing = toValue(getBitFlag(newData, 0, 3), LeverProperties.Orientation.DOWN_X,
						LeverProperties.Orientation.EAST, LeverProperties.Orientation.WEST,
						LeverProperties.Orientation.SOUTH, LeverProperties.Orientation.NORTH,
						LeverProperties.Orientation.UP_Z, LeverProperties.Orientation.UP_X,
						LeverProperties.Orientation.DOWN_Z);
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				LeverProperties.applyFacing(block, leverFacing);
				LeverProperties.applyPowered(block, powered);
				block.update();
				
				break;
			case 70: // minecraft:stone_pressure_plate
			case 72: // minecraft:wooden_pressure_plate
				
				powered = toBoolean(newData);
				
				block.setTypeId(id);
				PressurePlateProperties.applyPowered(block, powered);
				block.update();
				
				break;
			case 77: // minecraft:stone_button
			case 143: // minecraft:wooden_button
				
				facing = convertFacing(id, getBitFlag(newData, 0, 3));
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				ButtonProperties.applyFacing(block, facing);
				ButtonProperties.applyPowered(block, powered);
				block.update();
				
				break;
			case 78: // minecraft:snow_layer
				
				layer = newData + 1;
				
				block.setTypeId(id);
				SnowProperties.applyLayers(block, layer);
				block.update();
				
				break;
			case 81: // minecraft:cactus
				
				age = newData;
				
				block.setTypeId(id);
				CactusProperties.applyAge(block, age);
				block.update();
				
				break;
			case 83: // minecraft:reeds
				
				age = newData;
				
				block.setTypeId(id);
				ReedProperties.applyAge(block, age);
				block.update();
				
				break;
			case 84: // minecraft:jukebox
				
				hasRecord = toBoolean(newData);
				
				block.setTypeId(id);
				JukeboxProperties.applyRecord(block, hasRecord);
				block.update();
				
				break;
			case 86: // minecraft:pumpkin
			case 91: // minecraft:lit_pumpkin
				
				facing = convertFacing(id, newData);
				
				block.setTypeId(id);
				DirectionalBlockProperties.applyFacing(block, facing);
				block.update();
				
				break;
			case 90: // minecraft:portal
				
				blockAxis = toValue(newData, null, BlockFace.Axis.Z, BlockFace.Axis.X);
				
				block.setTypeId(id);
				PortalProperties.applyAxis(block, blockAxis);
				block.update();
				
				break;
			case 92: // minecraft:cake
				
				bites = newData;
				
				block.setTypeId(id);
				CakeProperties.applyBites(block, bites);
				block.update();
				
				break;
			case 93: // minecraft:unpowered_repeater
			case 94: // minecraft:powered_repeater
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				delay = getBitFlag(newData, 2, 2) + 1;
				
				block.setTypeId(id);
				DirectionalBlockProperties.applyFacing(block, facing);
				RedstoneRepeaterProperties.applyDelay(block, delay);
				block.update();
				
				break;
			case 96: // minecraft:trapdoor
			case 167: // minecraft:iron_trapdoor
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				open = toBoolean(getBitFlag(newData, 2, 1));
				trapdoorHalf = toValue(getBitFlag(newData, 3, 1), TrapDoorProperties.Half.LOWER,
						TrapDoorProperties.Half.UPPER);
				
				block.setTypeId(id);
				TrapDoorProperties.applyFacing(block, facing);
				TrapDoorProperties.applyOpen(block, open);
				TrapDoorProperties.applyHalf(block, trapdoorHalf);
				block.update();
				
				break;
			case 99: // minecraft:brown_mushroom_block
			case 100: // minecraft:red_mushroom_block
				
				mushroomVariant = toValue(newData, HugeMushroomProperties.Variant.ALL_INSIDE,
						HugeMushroomProperties.Variant.NORTH_WEST, HugeMushroomProperties.Variant.NORTH,
						HugeMushroomProperties.Variant.NORTH_EAST, HugeMushroomProperties.Variant.WEST,
						HugeMushroomProperties.Variant.CENTER, HugeMushroomProperties.Variant.EAST,
						HugeMushroomProperties.Variant.SOUTH_WEST, HugeMushroomProperties.Variant.SOUTH,
						HugeMushroomProperties.Variant.SOUTH_EAST, HugeMushroomProperties.Variant.STEM, null,
						null, null, HugeMushroomProperties.Variant.ALL_OUTSIDE,
						HugeMushroomProperties.Variant.ALL_STEM);
				
				block.setTypeId(id);
				HugeMushroomProperties.applyVariant(block, mushroomVariant);
				block.update();
				
				break;
			case 104: // minecraft:pumpkin_stem
			case 105: // minecraft:melon_stem
				
				age = newData;
				
				block.setTypeId(id);
				StemProperties.applyAge(block, age);
				block.update();
				
				break;
			case 106: // minecraft:vine
				
				south = toBoolean(getBitFlag(newData, 0, 1));
				west = toBoolean(getBitFlag(newData, 1, 1));
				north = toBoolean(getBitFlag(newData, 2, 1));
				east = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				VineProperties.applySouth(block, south);
				VineProperties.applyWest(block, west);
				VineProperties.applyNorth(block, north);
				VineProperties.applyEast(block, east);
				block.update();
				
				break;
			case 107: // minecraft:fence_gate
			case 183: // minecraft:spruce_fence_gate
			case 184: // minecraft:birch_fence_gate
			case 185: // minecraft:jungle_fence_gate
			case 186: // minecraft:dark_oak_fence_gate
			case 187: // minecraft:acacia_fence_gate
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				open = toBoolean(getBitFlag(newData, 2, 1));
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				DirectionalBlockProperties.applyFacing(block, facing);
				FenceGateProperties.applyOpen(block, open);
				FenceGateProperties.applyPowered(block, powered);
				block.update();
				
				break;
			case 115: // minecraft:nether_wart
				
				age = newData;
				
				block.setTypeId(id);
				NetherWartProperties.applyAge(block, age);
				block.update();
				
				break;
			case 117: // minecraft:brewing_stand
				
				final boolean hasBottle0 = toBoolean(getBitFlag(newData, 0, 1));
				final boolean hasBottle1 = toBoolean(getBitFlag(newData, 1, 1));
				final boolean hasBottle2 = toBoolean(getBitFlag(newData, 2, 1));
				
				block.setTypeId(id);
				block.update();
				
				// TODO Error doing in one go
				final Block foo = block;
				Canary.getServer().addSynchronousTask(new ServerTask(Canary.getServer(), 0) {
					@Override
					public void run() {
						BrewingStandProperties.applyBottle0(foo, hasBottle0);
						BrewingStandProperties.applyBottle1(foo, hasBottle1);
						BrewingStandProperties.applyBottle2(foo, hasBottle2);
						block.update();
					}
					
				});
				// TaskManager.submitTask(new Runnable() {
				// @Override
				// public void run() {
				// BrewingStandProperties.applyBottle0(foo, hasBottle0);
				// BrewingStandProperties.applyBottle1(foo, hasBottle1);
				// BrewingStandProperties.applyBottle2(foo, hasBottle2);
				// block.update();
				// }
				// });
				
				break;
			case 118: // minecraft:cauldron
				
				level = newData;
				
				block.setTypeId(id);
				CauldronProperties.applyLevel(block, level);
				block.update();
				
				break;
			case 120: // minecraft:end_portal_frame
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				eye = toBoolean(getBitFlag(newData, 2, 1));
				
				block.setTypeId(id);
				EndPortalFrameProperties.applyFacing(block, facing);
				EndPortalFrameProperties.applyEye(block, eye);
				block.update();
				
				break;
			case 127: // minecraft:cocoa
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				age = getBitFlag(newData, 2, 2);
				
				block.setTypeId(id);
				DirectionalBlockProperties.applyFacing(block, facing);
				CocoaPlantProperties.applyAge(block, age);
				block.update();
				
				break;
			case 130: // minecraft:ender_chest
				
				facing = convertFacing(id, newData);
				
				block.setTypeId(id);
				EnderChestProperties.applyFacing(block, facing);
				block.update();
				
				break;
			case 131: // minecraft:tripwire_hook
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				attached = toBoolean(getBitFlag(newData, 2, 1));
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				TripwireHookProperties.applyFacing(block, facing);
				TripwireHookProperties.applyAttached(block, attached);
				TripwireHookProperties.applyPowered(block, powered);
				block.update();
				
				break;
			case 132: // minecraft:tripwire
				
				powered = toBoolean(getBitFlag(newData, 0, 1));
				suspended = toBoolean(getBitFlag(newData, 1, 1));
				attached = toBoolean(getBitFlag(newData, 2, 1));
				disarmed = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				TripwireProperties.applyPowered(block, powered);
				TripwireProperties.applySuspended(block, suspended);
				TripwireProperties.applyAttached(block, attached);
				TripwireProperties.applyDisarmed(block, disarmed);
				block.update();
				
				break;
			case 137: // minecraft:command_block
				
				triggered = toBoolean(newData);
				
				block.setTypeId(id);
				CommandBlockProperties.applyTriggered(block, triggered);
				block.update();
				
				break;
			case 139: // minecraft:cobblestone_wall
				
				wallVariant = toValue(newData, WallProperties.Variant.NORMAL, WallProperties.Variant.MOSSY);
				
				block.setTypeId(id);
				WallProperties.applyVariant(block, wallVariant);
				block.update();
				
				break;
			case 144: // minecraft:skull
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				nodrop = toBoolean(getBitFlag(newData, 2, 1));
				
				block.setTypeId(id);
				SkullProperties.applyFacing(block, facing);
				SkullProperties.applyNoDrop(block, nodrop);
				block.update();
				
				break;
			case 145: // minecraft:anvil
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				damage = getBitFlag(newData, 2, 2);
				
				block.setTypeId(id);
				AnvilProperties.applyFacing(block, facing);
				AnvilProperties.applyDamage(block, damage);
				block.update();
				
				break;
			case 147: // minecraft:light_weighted_pressure_plate
			case 148: // minecraft:heavy_weighted_pressure_plate
				
				power = newData;
				
				block.setTypeId(id);
				WeightedPressurePlateProperties.applyPower(block, power);
				block.update();
				
				break;
			case 149: // minecraft:unpowered_comparator
			case 150: // minecraft:powered_comparator
				
				facing = convertFacing(id, getBitFlag(newData, 0, 2));
				mode = toValue(getBitFlag(newData, 2, 1), RedstoneComparatorProperties.Mode.COMPARE,
						RedstoneComparatorProperties.Mode.SUBTRACT);
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				DirectionalBlockProperties.applyFacing(block, facing);
				RedstoneComparatorProperties.applyMode(block, mode);
				RedstoneComparatorProperties.applyPowered(block, powered);
				block.update();
				
				break;
			case 151: // minecraft:daylight_detector
			case 178: // minecraft:daylight_detector_inverted
				
				power = newData;
				
				block.setTypeId(id);
				DaylightDetectorProperties.applyPower(block, power);
				block.update();
				
				break;
			case 154: // minecraft:hopper
				
				facing = convertFacing(id, getBitFlag(newData, 0, 3));
				enabled = toBoolean(getBitFlag(newData, 3, 1));
				
				block.setTypeId(id);
				HopperProperties.applyFacing(block, facing);
				// TODO
				// HopperProperties.applyEnabled(block, enabled);
				block.update();
				
				break;
			case 170: // minecraft:hay_block
				
				hayAxis = toValue(getBitFlag(newData, 2, 2), BlockFace.Axis.Y, BlockFace.Axis.X,
						BlockFace.Axis.Z);
				
				block.setTypeId(id);
				RotatedPillarProperties.applyAxis(block, hayAxis);
				block.update();
				
				break;
			case 175: // minecraft:double_plant
				
				plantVariant = toValue(getBitFlag(newData, 0, 3), DoublePlantProperties.Variant.SUNFLOWER,
						DoublePlantProperties.Variant.SYRINGA, DoublePlantProperties.Variant.GRASS,
						DoublePlantProperties.Variant.FERN, DoublePlantProperties.Variant.ROSE,
						DoublePlantProperties.Variant.PAEONIA);
				plantHalf = toValue(getBitFlag(newData, 3, 1), DoublePlantProperties.Half.LOWER,
						DoublePlantProperties.Half.UPPER);
				
				// TODO
				block.setTypeId(id);
				DoublePlantProperties.applyVariant(block, plantVariant);
				DoublePlantProperties.applyHalf(block, plantHalf);
				block.update();
				
				break;
			case 176: // minecraft:standing_banner
				
				rotation = newData;
				
				block.setTypeId(id);
				BannerProperties.applyRotation(block, rotation);
				block.update();
				
				break;
			case 177: // minecraft:wall_banner
				
				facing = convertFacing(id, newData);
				
				block.setTypeId(id);
				BannerProperties.applyFacing(block, facing);
				block.update();
				
				break;
			default: // minecraft:*
				
				data = newData;
				
				// TODO check for null BlockType
				final BlockType type = BlockType.fromIdAndData(id, data);
				if (type != null) {
					block.setType(type);
					block.update();
				} else {
					// TODO log warning
					System.out.println("ERROR");
				}
				
		}
	}
	
	private static BlockFace convertFacing(final short id, final int facing) {
		// Convert unsigned byte to int
		final int iType = id & 0xFF;
		
		final BlockFace face;
		
		switch (iType) {
			case 23: // minecraft:dispenser
			case 158: // minecraft:dropper
				
				face = toValue(facing, BlockFace.BOTTOM, BlockFace.TOP, BlockFace.NORTH, BlockFace.SOUTH,
						BlockFace.WEST, BlockFace.EAST);
				
				break;
			case 26: // minecraft:bed
				
				face = toValue(facing, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST);
				
				break;
			case 29: // minecraft:sticky_piston
			case 33: // minecraft:piston
				
				face = toValue(facing, BlockFace.BOTTOM, BlockFace.TOP, BlockFace.NORTH, BlockFace.SOUTH,
						BlockFace.WEST, BlockFace.EAST);
				
				break;
			case 34: // minecraft:piston_head
			case 36: // minecraft:piston_extension
				
				face = toValue(facing, BlockFace.BOTTOM, BlockFace.TOP, BlockFace.NORTH, BlockFace.SOUTH,
						BlockFace.WEST, BlockFace.EAST);
				
				break;
			case 50: // minecraft:torch
			case 75: // minecraft:unlit_redstone_torch
			case 76: // minecraft:redstone_torch
				
				face = toValue(facing, null, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH,
						BlockFace.NORTH, BlockFace.TOP);
				
				break;
			case 53: // minecraft:oak_stairs
			case 67: // minecraft:cobblestone_stairs
			case 108: // minecraft:brick_stairs
			case 109: // minecraft:stone_brick_stairs
			case 114: // minecraft:nether_brick_stairs
			case 128: // minecraft:sandstone_stairs
			case 134: // minecraft:spruce_stairs
			case 135: // minecraft:birch_stairs
			case 136: // minecraft:jungle_stairs
			case 156: // minecraft:quartz_stairs
			case 163: // minecraft:acacia_stairs
			case 164: // minecraft:dark_oak_stairs
			case 180: // minecraft:red_sandstone_stairs
				
				face = toValue(facing, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH, BlockFace.NORTH);
				
				break;
			case 54: // minecraft:chest
			case 61: // minecraft:furnace
			case 62: // minecraft:lit_furnace
			case 65: // minecraft:ladder
			case 68: // minecraft:wall_sign
			case 130: // minecraft:ender_chest
			case 146: // minecraft:trapped_chest
				
				face = toValue(facing, null, null, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST,
						BlockFace.EAST);
				
				break;
			case 177: // minecraft:wall_banner
				
				// TODO
				face = toValue(facing, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.EAST);
				
				break;
			case 64: // minecraft:wooden_door
			case 71: // minecraft:iron_door
			case 193: // minecraft:spruce_door
			case 194: // minecraft:birch_door
			case 195: // minecraft:jungle_door
			case 196: // minecraft:acacia_door
			case 197: // minecraft:dark_oak_door
				
				face = toValue(facing, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH);
				
				break;
			case 77: // minecraft:stone_button
			case 143: // minecraft:wooden_button
				
				face = toValue(facing, BlockFace.BOTTOM, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH,
						BlockFace.NORTH, BlockFace.TOP);
				
				break;
			case 86: // minecraft:pumpkin
			case 91: // minecraft:lit_pumpkin
				
				// TODO: BlockFace.NONE
				face = toValue(facing, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST,
						BlockFace.UNKNOWN);
				
				break;
			case 93: // minecraft:unpowered_repeater
			case 94: // minecraft:powered_repeater
				
				face = toValue(facing, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST);
				
				break;
			case 96: // minecraft:trapdoor
			case 167: // minecraft:iron_trapdoor
				
				face = toValue(facing, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.EAST);
				
				break;
			case 107: // minecraft:fence_gate
			case 183: // minecraft:spruce_fence_gate
			case 184: // minecraft:birch_fence_gate
			case 185: // minecraft:jungle_fence_gate
			case 186: // minecraft:dark_oak_fence_gate
			case 187: // minecraft:acacia_fence_gate
				
				face = toValue(facing, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST);
				
				break;
			case 120: // minecraft:end_portal_frame
				
				face = toValue(facing, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST);
				
				break;
			case 127: // minecraft:coco
				
				face = toValue(facing, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST);
				
				break;
			case 131: // minecraft:tripwire_hook
				
				face = toValue(facing, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST);
				
				break;
			case 144: // minecraft:skull
				
				face = toValue(facing, BlockFace.BOTTOM, BlockFace.TOP, BlockFace.NORTH, BlockFace.SOUTH,
						BlockFace.WEST, BlockFace.EAST);
				
				break;
			case 145: // minecraft:anvil
				
				face = toValue(facing, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST);
				
				break;
			case 149: // minecraft:unpowered_comparator
			case 150: // minecraft:powered_comparator
				
				face = toValue(facing, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST);
				
				break;
			case 154: // minecraft:hopper
				
				face = toValue(facing, BlockFace.BOTTOM, null, BlockFace.NORTH, BlockFace.SOUTH,
						BlockFace.WEST, BlockFace.EAST);
				
				break;
			default:
				face = null;
		}
		
		return face;
	}
	
	@Deprecated
	public static Map<String, Object> convert(final byte newType, final int newData) {
		// Convert unsigned byte to int
		final int iType = newType & 0xFF;
		
		// Placeholder properties
		int id = 0;
		int data = 0;
		
		Integer stage = null;
		Integer level = null;
		String axis = null;
		Boolean noDecay = null;
		Boolean checkDecay = null;
		String facing = null;
		Boolean triggered = null;
		Integer occupied = null;
		Integer part = null;
		String shape = null;
		Boolean powered = null;
		Boolean extended = null;
		String type = null;
		Boolean seamless = null;
		String half = null;
		Boolean explode = null;
		Integer age = null;
		Integer power = null;
		Integer moisture = null;
		String rotation = null;
		String hinge = null;
		Boolean open = null;
		Integer layer = null;
		Boolean hasRecord = null;
		Integer bites = null;
		Integer delay = null;
		Integer variant = null;
		Boolean south = null;
		Boolean west = null;
		Boolean north = null;
		Boolean east = null;
		Boolean hasBottle0 = null;
		Boolean hasBottle1 = null;
		Boolean hasBottle2 = null;
		Boolean eye = null;
		Boolean attached = null;
		Boolean suspended = null;
		Boolean disarmed = null;
		Boolean nodrop = null;
		Integer damage = null;
		String mode = null;
		Boolean enabled = null;
		
		final int iHalf;
		
		switch (iType) {
			case 6: // minecraft:sapling
				
				id = iType;
				data = getBitFlag(newData, 0, 3);
				stage = getBitFlag(newData, 3, 1);
				
				break;
			case 8: // minecraft:flowing_water
			case 9: // minecraft:water
			case 10: // minecraft:flowing_lava
			case 11: // minecraft:lava
				
				id = iType;
				data = 0;
				level = (Math.abs(newData - 16) + 7) % 16;
				
				break;
			case 17: // minecraft:log
			case 162: // minecraft:log2
				
				id = iType;
				data = getBitFlag(newData, 0, 2);
				axis = toValue(getBitFlag(newData, 2, 2), "y", "z", "x", "none");
				
				break;
			case 18: // minecraft:leaves
			case 161: // minecraft:leaves2
				
				id = iType;
				data = getBitFlag(newData, 0, 2);
				noDecay = toBoolean(getBitFlag(newData, 2, 1));
				checkDecay = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 23: // minecraft:dispenser
			case 158: // minecraft:dropper
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 3), "down", "up", "north", "south", "west", "east");
				triggered = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 26: // minecraft:bed
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "south", "west", "north", "east");
				occupied = getBitFlag(newData, 2, 1);
				part = getBitFlag(newData, 3, 1);
				
				break;
			case 27: // minecraft:golden_rail
			case 28: // minecraft:detector_rail
			case 157: // minecraft:activator_rail
				
				id = iType;
				data = 0;
				shape = toValue(getBitFlag(newData, 0, 3), "north_south", "east_west", "ascending_east",
						"ascending_west", "ascending_north", "ascending_south");
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 29: // minecraft:sticky_piston
			case 33: // minecraft:piston
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 3), "down", "up", "north", "south", "west", "east");
				extended = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 34: // minecraft:piston_head
			case 36: // minecraft:piston_extension
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 3), "down", "up", "north", "south", "west", "east");
				type = toValue(getBitFlag(newData, 3, 1), "normal", "sticky");
				
				break;
			case 43: // minecraft:double_stone_slab
			case 181: // minecraft:double_stone_slab2
				
				id = iType;
				data = getBitFlag(newData, 0, 3);
				seamless = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 44: // minecraft:stone_slab
			case 182: // minecraft:stone_slab2
			case 126: // minecraft:wood_slab
				
				id = iType;
				data = getBitFlag(newData, 0, 3);
				half = toValue(getBitFlag(newData, 3, 1), "bottom", "top");
				
				break;
			case 46: // minecraft:tnt
				
				id = iType;
				data = 0;
				explode = toBoolean(newData);
				
				break;
			case 50: // minecraft:torch
			case 75: // minecraft:unlit_redstone_torch
			case 76: // minecraft:redstone_torch
				
				id = iType;
				data = 0;
				facing = toValue(newData, null, "east", "west", "south", "north", "up");
				
				break;
			case 51: // minecraft:fire
				
				id = iType;
				data = 0;
				age = newData;
				
				break;
			case 53: // minecraft:oak_stairs
			case 67: // minecraft:cobblestone_stairs
			case 108: // minecraft:brick_stairs
			case 109: // minecraft:stone_brick_stairs
			case 114: // minecraft:nether_brick_stairs
			case 128: // minecraft:sandstone_stairs
			case 134: // minecraft:spruce_stairs
			case 135: // minecraft:birch_stairs
			case 136: // minecraft:jungle_stairs
			case 156: // minecraft:quartz_stairs
			case 163: // minecraft:acacia_stairs
			case 164: // minecraft:dark_oak_stairs
			case 180: // minecraft:red_sandstone_stairs
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "east", "west", "south", "north");
				half = toValue(getBitFlag(newData, 2, 2), "bottom", "top");
				
				break;
			case 54: // minecraft:chest
			case 61: // minecraft:furnace
			case 62: // minecraft:lit_furnace
			case 65: // minecraft:ladder
			case 68: // minecraft:wall_sign
			case 130: // minecraft:ender_chest
			case 146: // minecraft:trapped_chest
			case 177: // minecraft:wall_banner
				
				id = iType;
				data = 0;
				facing = toValue(newData, null, null, "north", "south", "west", "east");
				
				break;
			case 55: // minecraft:redstone_wire
				
				id = iType;
				data = 0;
				power = newData;
				
				break;
			case 59: // minecraft:wheat
			case 141: // minecraft:carrots
			case 142: // minecraft:potatoes
				
				id = iType;
				data = 0;
				age = newData;
				
				break;
			case 60: // minecraft:farmland
				
				id = iType;
				data = 0;
				moisture = newData;
				
				break;
			case 63: // minecraft:standing_sign
			case 176: // minecraft:standing_banner
				
				id = iType;
				data = 0;
				rotation = toValue(newData, "south", "south-southwest", "southwest", "west-southwest",
						"west", "west-northwest", "northwest", "north-northwest", "north", "north-northeast",
						"northeast", "east-northeast", "east", "east-southeast", "southeast", "south-southeast");
				
				break;
			case 64: // minecraft:wooden_door
			case 71: // minecraft:iron_door
			case 193: // minecraft:spruce_door
			case 194: // minecraft:birch_door
			case 195: // minecraft:jungle_door
			case 196: // minecraft:acacia_door
			case 197: // minecraft:dark_oak_door
				
				id = iType;
				data = 0;
				iHalf = getBitFlag(newData, 0, 1);
				half = toValue(iHalf, "upper", "lower");
				switch (iHalf) {
					case 0:
						
						open = toBoolean(getBitFlag(newData, 1, 1));
						facing = toValue(getBitFlag(newData, 2, 2), "east", "south", "west", "north");
						
						break;
					case 1:
						
						hinge = toValue(getBitFlag(newData, 1, 1), "left", "right");
						powered = toBoolean(getBitFlag(newData, 2, 1));
						
						break;
				}
				
				break;
			case 66: // minecraft:rail
				
				id = iType;
				data = 0;
				shape = toValue(newData, "north_south", "east_west", "ascending_east", "ascending_west",
						"ascending_north", "ascending_south", "south_east", "south_west", "north_west",
						"north_east");
				
				break;
			case 69: // minecraft:lever
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 3), "down_x", "east", "west", "south", "north",
						"up_z", "up_x", "down_z");
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 70: // minecraft:stone_pressure_plate
			case 72: // minecraft:wooden_pressure_plate
				
				id = iType;
				data = 0;
				powered = toBoolean(newData);
				
				break;
			case 77: // minecraft:stone_button
			case 143: // minecraft:wooden_button
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 3), "down", "east", "west", "south", "north", "up");
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 78: // minecraft:snow_layer
				
				id = iType;
				data = 0;
				layer = newData + 1;
				
				break;
			case 81: // minecraft:cactus
			case 83: // minecraft:reeds
				
				id = iType;
				data = 0;
				age = newData;
				
				break;
			case 84: // minecraft:jukebox
				
				id = iType;
				data = 0;
				hasRecord = toBoolean(newData);
				
				break;
			case 86: // minecraft:pumpkin
			case 91: // minecraft:lit_pumpkin
				
				id = iType;
				data = 0;
				facing = toValue(newData, "south", "west", "north", "east", "none");
				
				break;
			case 90: // minecraft:portal
				
				id = iType;
				data = 0;
				axis = toValue(newData, "x", "z");
				
				break;
			case 92: // minecraft:cake
				
				id = iType;
				data = 0;
				bites = newData;
				
				break;
			case 93: // minecraft:unpowered_repeater
			case 94: // minecraft:unpowered_repeater
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "south", "west", "north", "east");
				delay = getBitFlag(newData, 2, 2);
				
				break;
			case 96: // minecraft:trapdoor
			case 167: // minecraft:iron_trapdoor
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "north", "south", "west", "east");
				open = toBoolean(getBitFlag(newData, 2, 1));
				half = toValue(getBitFlag(newData, 3, 1), "bottom", "top");
				
				break;
			case 99: // minecraft:brown_mushroom_block
			case 100: // minecraft:red_mushroom_block
				
				id = iType;
				data = 0;
				variant = newData;
				
				break;
			case 104: // minecraft:pumpkin_stem
			case 105: // minecraft:melon_stem
				
				id = iType;
				data = 0;
				age = newData;
				
				break;
			case 106: // minecraft:vine
				
				id = iType;
				data = 0;
				south = toBoolean(getBitFlag(newData, 0, 1));
				west = toBoolean(getBitFlag(newData, 1, 1));
				north = toBoolean(getBitFlag(newData, 2, 1));
				east = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 107: // minecraft:fence_gate
			case 183: // minecraft:spruce_fence_gate
			case 184: // minecraft:birch_fence_gate
			case 185: // minecraft:jungle_fence_gate
			case 186: // minecraft:dark_oak_fence_gate
			case 187: // minecraft:acacia_fence_gate
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "south", "west", "north", "east");
				open = toBoolean(getBitFlag(newData, 2, 1));
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 115: // minecraft:nether_wart
				
				id = iType;
				data = 0;
				age = newData;
				
				break;
			case 117: // minecraft:brewing_stand
				
				id = iType;
				data = 0;
				hasBottle0 = toBoolean(getBitFlag(newData, 0, 1));
				hasBottle1 = toBoolean(getBitFlag(newData, 1, 1));
				hasBottle2 = toBoolean(getBitFlag(newData, 2, 1));
				
				break;
			case 118: // minecraft:cauldron
				
				id = iType;
				data = 0;
				level = newData;
				
				break;
			case 120: // minecraft:end_portal_frame
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "south", "west", "north", "east");
				eye = toBoolean(getBitFlag(newData, 2, 1));
				
				break;
			case 127: // minecraft:coco
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "south", "west", "north", "east");
				age = getBitFlag(newData, 2, 2);
				
				break;
			case 131: // minecraft:tripwire_hook
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "south", "west", "north", "east");
				attached = toBoolean(getBitFlag(newData, 2, 1));
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 132: // minecraft:tripwire
				
				id = iType;
				data = 0;
				powered = toBoolean(getBitFlag(newData, 0, 1));
				suspended = toBoolean(getBitFlag(newData, 1, 1));
				attached = toBoolean(getBitFlag(newData, 2, 1));
				disarmed = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 137: // minecraft:command_block
				
				id = iType;
				data = 0;
				triggered = toBoolean(newData);
				
				break;
			case 144: // minecraft:skull
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "down", "up", "north", "south", "west", "east");
				nodrop = toBoolean(getBitFlag(newData, 2, 1));
				
				break;
			case 145: // minecraft:anvil
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "south", "west", "north", "east");
				damage = getBitFlag(newData, 2, 2);
				
				break;
			case 147: // minecraft:light_weighted_pressure_plate
			case 148: // minecraft:heavy_weighted_pressure_plate
				
				id = iType;
				data = 0;
				power = newData;
				
				break;
			case 149: // minecraft:unpowered_comparator
			case 150: // minecraft:powered_comparator
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 2), "south", "west", "north", "east");
				mode = toValue(getBitFlag(newData, 2, 1), "compare", "subtract");
				powered = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 151: // minecraft:daylight_detector
			case 178: // minecraft:daylight_detector_inverted
				
				id = iType;
				data = 0;
				power = newData;
				
				break;
			case 154: // minecraft:hopper
				
				id = iType;
				data = 0;
				facing = toValue(getBitFlag(newData, 0, 3), "down", null, "north", "south", "west", "east");
				enabled = toBoolean(getBitFlag(newData, 3, 1));
				
				break;
			case 170: // minecraft:hay_block
				
				id = iType;
				data = 0;
				switch (newData) {
					case 0:
						
						axis = "y";
						
						break;
					case 4:
						
						axis = "z";
						
						break;
					case 8:
						
						axis = "x";
						
						break;
				}
				
				break;
			default: // minecraft:*
				
				id = iType;
				data = newData;
				
		}
		
		// Setup parameters to return in Map
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("data", data);
		if (axis != null) {
			params.put("axis", axis);
		}
		if (noDecay != null) {
			params.put("decayable", !noDecay);
		}
		if (facing != null) {
			params.put("facing", facing);
		}
		if (half != null) {
			params.put("half", half);
		}
		if (stage != null) {
			params.put("stage", stage);
		}
		if (checkDecay != null) {
			params.put("check_decay", checkDecay);
		}
		if (triggered != null) {
			params.put("triggered", triggered);
		}
		if (occupied != null) {
			params.put("occupied", occupied);
		}
		if (part != null) {
			params.put("part", part);
		}
		if (shape != null) {
			params.put("shape", shape);
		}
		if (powered != null) {
			params.put("powered", powered);
		}
		if (level != null) {
			params.put("level", level);
		}
		if (extended != null) {
			params.put("extended", extended);
		}
		if (type != null) {
			params.put("type", type);
		}
		if (seamless != null) {
			params.put("seamless", seamless);
		}
		if (explode != null) {
			params.put("explode", explode);
		}
		if (age != null) {
			params.put("age", age);
		}
		if (power != null) {
			params.put("power", power);
		}
		if (moisture != null) {
			params.put("moisture", moisture);
		}
		if (rotation != null) {
			params.put("rotation", rotation);
		}
		if (hinge != null) {
			params.put("hinge", hinge);
		}
		if (open != null) {
			params.put("open", open);
		}
		if (layer != null) {
			params.put("layer", layer);
		}
		if (hasRecord != null) {
			params.put("has_record", hasRecord);
		}
		if (bites != null) {
			params.put("bites", bites);
		}
		if (delay != null) {
			params.put("delay", delay);
		}
		if (variant != null) {
			params.put("variant", variant);
		}
		if (south != null) {
			params.put("south", south);
		}
		if (west != null) {
			params.put("west", west);
		}
		if (north != null) {
			params.put("north", north);
		}
		if (east != null) {
			params.put("east", east);
		}
		if (hasBottle0 != null) {
			params.put("has_bottle_0", hasBottle0);
		}
		if (hasBottle1 != null) {
			params.put("has_bottle_1", hasBottle1);
		}
		if (hasBottle2 != null) {
			params.put("has_bottle_2", hasBottle2);
		}
		if (eye != null) {
			params.put("eye", eye);
		}
		if (attached != null) {
			params.put("attached", attached);
		}
		if (suspended != null) {
			params.put("suspended", suspended);
		}
		if (disarmed != null) {
			params.put("disarmed", disarmed);
		}
		if (nodrop != null) {
			params.put("nodrop", nodrop);
		}
		if (damage != null) {
			params.put("damage", damage);
		}
		if (mode != null) {
			params.put("mode", mode);
		}
		if (enabled != null) {
			params.put("enabled", enabled);
		}
		
		// System.out.println(params);
		
		return params;
	}
	
	private static Boolean toBoolean(final Integer value) {
		Boolean bool = null;
		if (value != null) {
			switch (value) {
				case 0:
					bool = false;
					break;
				case 1:
					bool = true;
					break;
			}
		}
		return bool;
	}
	
	@Deprecated
	public static String toString(final Integer index, final String... strings) {
		String str = null;
		if (index != null) {
			str = strings[index];
		}
		return str;
	}
	
	public static <T> T toValue(final Integer index, final T... values) {
		T t = null;
		if (index != null) {
			t = values[index];
		}
		return t;
	}
	
	private static int getBitFlag(final int val, final int startIndex, final int bitLength) {
		final int x = pow(2, bitLength) - 1;
		return val >> startIndex & x;
	}
	
	private static int pow(final int a, final int b) {
		if (b == 0) {
			return 1;
		}
		if (b == 1) {
			return a;
		}
		if ((b & 1) == 0) {
			return pow(a * a, b / 2);
		} else {
			return a * pow(a * a, b / 2);
		}
	}
}

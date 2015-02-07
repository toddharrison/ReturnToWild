package com.eharrison.canary.r2w;

import static com.eharrison.canary.r2w.RegionUtil.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import net.canarymod.Canary;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.minecraft.world.level.chunk.DataLayer;
import net.visualillusionsent.utils.TaskManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import com.eharrison.canary.r2w.io.RegionFile;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.NbtIo;

// /rtw restore default normal -10 67 25 -12 67 27
// /rtw restore default normal -10 50 -10 10 80 10
// /rtw restore default normal -8 54 9 -13 51 14

public class TemplateManager {
	private final Logger log;
	private final WorldManager worldManager;
	private final File worldsDir;
	private final File templatesDir;
	
	public TemplateManager() {
		this(ReturnPlugin.LOG, Canary.getServer().getWorldManager(), new File("worlds"), new File(
				"templates"));
	}
	
	public TemplateManager(final Logger log, final WorldManager worldManager, final File worldsDir,
			final File templatesDir) {
		this.log = log;
		this.worldManager = worldManager;
		this.worldsDir = worldsDir;
		this.templatesDir = templatesDir;
	}
	
	public Future<Boolean> createTemplate(final String name, final DimensionType type) {
		return TaskManager.submitTask(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				boolean success = true;
				
				// Unload the world
				boolean loaded = false;
				if (worldManager.worldIsLoaded(name, type)) {
					log.info("Unloading world");
					loaded = true;
					worldManager.getWorld(name, type, false).broadcastMessage(
							"Creating a world template, GET OUT!");
					worldManager.unloadWorld(name, type, true);
				}
				
				// Create the template directory
				final File templateDir = getTemplateDir(name, type);
				if (!templateDir.exists()) {
					success = templateDir.mkdirs();
				}
				log.info("Prepared template world directory");
				
				if (success) {
					// Copy the world region data into the template
					log.info("Copying region files");
					try {
						FileUtils.copyDirectory(new File(worldsDir, name + "/" + name + "_" + type.getName()
								+ "/region"), new File(templateDir, "region"));
					} catch (final IOException e) {
						log.error("Error copying region files", e);
					}
				}
				
				// Load the world if it was loaded to begin with
				if (loaded) {
					log.info("Loading world");
					worldManager.loadWorld(name, type);
				}
				
				log.info("Created template " + name + "_" + type.getName() + ": " + success);
				return success;
			}
		});
	}
	
	public Future<Boolean> removeTemplate(final String name, final DimensionType type) {
		return TaskManager.submitTask(new Callable<Boolean>() {
			@Override
			public Boolean call() throws IOException {
				boolean success = true;
				final File templateDir = getTemplateDir(name, type);
				if (templateDir.exists() && templateDir.canWrite()) {
					success = FileUtils.deleteQuietly(templateDir);
				}
				log.info("Removed template " + name + "_" + type.getName() + ": " + success);
				return success;
			}
		});
	}
	
	public Future<Boolean> restore(final String name, final DimensionType type, final int x1,
			final int y1, final int z1, final int x2, final int y2, final int z2) {
		return TaskManager.submitTask(new Callable<Boolean>() {
			@Override
			public Boolean call() throws IOException {
				boolean success = false;
				
				// Load the target world
				final World world = worldManager.getWorld(name, type, true);
				if (world != null) {
					success = true;
					
					// Determine the block bounds
					final int xMin = Math.min(x1, x2);
					final int xMax = Math.max(x1, x2);
					final int yMin = Math.max(Math.min(y1, y2), 0);
					final int yMax = Math.min(Math.max(y1, y2), 255);
					final int zMin = Math.min(z1, z2);
					final int zMax = Math.max(z1, z2);
					
					// Determine the included regions
					final int regionXMin = RegionUtil.getRegionForBlockCoordinate(xMin);
					final int regionXMax = RegionUtil.getRegionForBlockCoordinate(xMax);
					final int regionZMin = RegionUtil.getRegionForBlockCoordinate(zMin);
					final int regionZMax = RegionUtil.getRegionForBlockCoordinate(zMax);
					
					// For each region
					for (int regionX = regionXMin; regionX <= regionXMax; regionX++) {
						for (int regionZ = regionZMin; regionZ <= regionZMax; regionZ++) {
							// Load the appropriate region file
							final RegionFile region = loadRegionFile(name, type, regionX, regionZ);
							if (region != null) {
								log.info("Processing region: " + regionX + ":" + regionZ);
								restoreRegion(world, region, regionX, regionZ, xMin, yMin, zMin, xMax, yMax, zMax);
								
								// Close the region File
								region.close();
							}
						}
					}
				}
				
				return success;
			}
		});
	}
	
	public BlockData getTemplateBlock(final String name, final DimensionType type, final int x,
			final int y, final int z) throws IOException {
		// Determine the absolute chunk of the block
		final int chunkX = getChunkForBlockCoordinate(x);
		final int chunkZ = getChunkForBlockCoordinate(z);
		// System.out.println("Chunk: " + chunkX + ":" + chunkZ);
		
		// Determine the region of the chunk
		final int regionX = getRegionForChunkCoordinate(chunkX);
		final int regionZ = getRegionForChunkCoordinate(chunkZ);
		// System.out.println("Region: " + regionX + ":" + regionZ);
		
		// Load the appropriate region file
		final RegionFile region = loadRegionFile(name, type, regionX, regionZ);
		// System.out.println(region.printOffsets());
		
		// Determine the relative chunk in the region
		final int relChunkX = getRegionRelativeChunkCoordinate(chunkX);
		final int relChunkZ = getRegionRelativeChunkCoordinate(chunkZ);
		// System.out.println("Region Chunk: " + relChunkX + ":" + relChunkZ);
		
		// Load the chunk
		final DataInputStream dis = region.getChunkDataInputStream(relChunkX, relChunkZ);
		final CompoundTag level = NbtIo.read(dis);
		dis.close();
		final CompoundTag chunkData = level.getCompound("Level");
		
		// Get the height map
		// final int[] heightMap = chunkData.getIntArray("HeightMap");
		// System.out.println(printHeightMap(heightMap, 16));
		
		// Determine the section of the chunk
		final int sectionId = getSectionForBlockCoordinate(y);
		// System.out.println("Section: " + sectionId);
		
		// Find the section
		final ListTag<?> sections = chunkData.getList("Sections");
		final CompoundTag section = (CompoundTag) sections.get(sectionId);
		// System.out.println("Section: " + sectionId);
		// System.out.println(printSection(section));
		
		// Determine the relative block location
		final int relX = getChunkRelativeBlockCoordinate(x);
		final int relY = getSectionRelativeBlockCoordinate(y);
		final int relZ = getChunkRelativeBlockCoordinate(z);
		// System.out.println("Section Block: " + relX + ":" + relY + ":" + relZ);
		
		// Get the block type and data from the section
		final byte[] blocks = section.getByteArray("Blocks");
		final DataLayer dataValues = new DataLayer(section.getByteArray("Data"), 4);
		final int blockType = blocks[relY << 8 | relZ << 4 | relX];
		final int blockData = dataValues.get(relX, relY, relZ);
		
		// Close the region File
		region.close();
		
		return new BlockData(blockType, blockData);
	}
	
	private void restoreRegion(final World world, final RegionFile region, final int regionX,
			final int regionZ, final int xMin, final int yMin, final int zMin, final int xMax,
			final int yMax, final int zMax) throws IOException {
		if (world != null && region != null) {
			// Determine the included blocks
			final int regionBlockXMin = RegionUtil.getRegionBlockIntersection(regionX, xMin);
			final int regionBlockXMax = RegionUtil.getRegionBlockIntersection(regionX, xMax);
			final int regionBlockZMin = RegionUtil.getRegionBlockIntersection(regionZ, zMin);
			final int regionBlockZMax = RegionUtil.getRegionBlockIntersection(regionZ, zMax);
			
			// Determine the included chunks
			final int chunkXMin = RegionUtil.getChunkForBlockCoordinate(regionBlockXMin);
			final int chunkXMax = RegionUtil.getChunkForBlockCoordinate(regionBlockXMax);
			final int chunkZMin = RegionUtil.getChunkForBlockCoordinate(regionBlockZMin);
			final int chunkZMax = RegionUtil.getChunkForBlockCoordinate(regionBlockZMax);
			
			// For each chunk
			for (int chunkX = chunkXMin; chunkX <= chunkXMax; chunkX++) {
				for (int chunkZ = chunkZMin; chunkZ <= chunkZMax; chunkZ++) {
					// Get the relative location of the chunk
					final int relChunkX = RegionUtil.getRegionRelativeChunkCoordinate(chunkX);
					final int relChunkZ = RegionUtil.getRegionRelativeChunkCoordinate(chunkZ);
					if (region.hasChunk(relChunkX, relChunkZ)) {
						// Load the chunk
						final DataInputStream dis = region.getChunkDataInputStream(relChunkX, relChunkZ);
						final CompoundTag chunk = NbtIo.read(dis);
						dis.close();
						
						restoreChunk(world, chunk, chunkX, chunkZ, xMin, yMin, zMin, xMax, yMax, zMax);
					}
				}
			}
		}
	}
	
	private void restoreChunk(final World world, final CompoundTag chunk, final int chunkX,
			final int chunkZ, final int xMin, final int yMin, final int zMin, final int xMax,
			final int yMax, final int zMax) {
		if (chunk != null && !chunk.isEmpty()) {
			final CompoundTag level = chunk.getCompound("Level");
			log.info("Processing chunk: " + chunkX + ":" + chunkZ);
			
			// Determine the included sections
			final int sectionMin = RegionUtil.getSectionForBlockCoordinate(yMin);
			final int sectionMax = RegionUtil.getSectionForBlockCoordinate(yMax);
			
			// For each section
			final ListTag<?> sections = level.getList("Sections");
			for (int sectionY = sectionMin; sectionY <= sectionMax; sectionY++) {
				if (sectionY < sections.size()) {
					// Restore the section
					final CompoundTag section = (CompoundTag) sections.get(sectionY);
					restoreSection(world, chunkX, chunkZ, section, xMin, yMin, zMin, xMax, yMax, zMax);
				} else {
					// Fill the section with air
					fillSection(world, chunkX, chunkZ, sectionY, xMin, yMin, zMin, xMax, yMax, zMax,
							BlockType.Air);
				}
			}
		}
	}
	
	private void restoreSection(final World world, final int chunkX, final int chunkZ,
			final CompoundTag section, final int xMin, final int yMin, final int zMin, final int xMax,
			final int yMax, final int zMax) {
		if (section != null && !section.isEmpty()) {
			final int sectionY = section.getByte("Y");
			// log.info("Processing section: " + sectionY);
			
			// Determine the included blocks
			final int sectionBlockMin = RegionUtil.getSectionBlockIntersection(sectionY, yMin);
			final int sectionBlockMax = RegionUtil.getSectionBlockIntersection(sectionY, yMax);
			
			for (int blockY = sectionBlockMin; blockY <= sectionBlockMax; blockY++) {
				// Determine the included blocks
				final int blockXMin = RegionUtil.getChunkBlockIntersection(chunkX, xMin);
				final int blockXMax = RegionUtil.getChunkBlockIntersection(chunkX, xMax);
				final int blockZMin = RegionUtil.getChunkBlockIntersection(chunkZ, zMin);
				final int blockZMax = RegionUtil.getChunkBlockIntersection(chunkZ, zMax);
				
				// For each block
				for (int blockX = blockXMin; blockX <= blockXMax; blockX++) {
					for (int blockZ = blockZMin; blockZ <= blockZMax; blockZ++) {
						restoreBlock(world, section, blockX, blockY, blockZ);
					}
				}
			}
		}
	}
	
	private void fillSection(final World world, final int chunkX, final int chunkZ,
			final int sectionY, final int xMin, final int yMin, final int zMin, final int xMax,
			final int yMax, final int zMax, final BlockType blockType) {
		log.info("Filling section: " + sectionY);
		
		// Determine the included blocks
		final int sectionBlockMin = RegionUtil.getSectionBlockIntersection(sectionY, yMin);
		final int sectionBlockMax = RegionUtil.getSectionBlockIntersection(sectionY, yMax);
		
		for (int blockY = sectionBlockMin; blockY <= sectionBlockMax; blockY++) {
			// Determine the included blocks
			final int blockXMin = RegionUtil.getChunkBlockIntersection(chunkX, xMin);
			final int blockXMax = RegionUtil.getChunkBlockIntersection(chunkX, xMax);
			final int blockZMin = RegionUtil.getChunkBlockIntersection(chunkZ, zMin);
			final int blockZMax = RegionUtil.getChunkBlockIntersection(chunkZ, zMax);
			
			// For each block
			for (int blockX = blockXMin; blockX <= blockXMax; blockX++) {
				for (int blockZ = blockZMin; blockZ <= blockZMax; blockZ++) {
					// Set the block in the target world
					log.debug("Setting block: " + blockX + ":" + blockY + ":" + blockZ + " to "
							+ blockType.getId() + ":" + blockType.getData());
					final Block block = world.getBlockAt(blockX, blockY, blockZ);
					block.setType(blockType);
					block.update();
				}
			}
		}
	}
	
	private void restoreBlock(final World world, final CompoundTag section, final int blockX,
			final int blockY, final int blockZ) {
		if (world != null && section != null && !section.isEmpty()) {
			// log.info("Processing block: " + blockX + ":" + blockY + ":" + blockZ);
			
			// Determine the relative block location
			final int relX = getChunkRelativeBlockCoordinate(blockX);
			final int relY = getSectionRelativeBlockCoordinate(blockY);
			final int relZ = getChunkRelativeBlockCoordinate(blockZ);
			
			// Get the block type and data from the section
			final byte[] blocks = section.getByteArray("Blocks");
			final DataLayer dataValues = new DataLayer(section.getByteArray("Data"), 4);
			final short type = blocks[relY << 8 | relZ << 4 | relX];
			final short data = (short) dataValues.get(relX, relY, relZ);
			
			log.debug("Setting block: " + blockX + ":" + blockY + ":" + blockZ + " to " + type + ":"
					+ data);
			
			// Set the block in the target world
			final Block block = world.getBlockAt(blockX, blockY, blockZ);
			block.setType(BlockType.fromIdAndData(type, data));
			block.update();
		}
	}
	
	private File getTemplateDir(final String name, final DimensionType type) {
		final File worldDir = new File(templatesDir, name);
		return new File(worldDir, name + "_" + type.getName());
	}
	
	private RegionFile loadRegionFile(final String name, final DimensionType type, final int regionX,
			final int regionZ) throws IOException {
		RegionFile region = null;
		final String resourceName = "r." + regionX + "." + regionZ + ".mca";
		final File templateDir = getTemplateDir(name, type);
		final File regionDir = new File(templateDir, "region");
		final File regionFile = new File(regionDir, resourceName);
		if (regionFile.exists()) {
			region = new RegionFile(regionFile);
		}
		return region;
	}
	
	// private String printHeightMap(final int[] bytes, final int offset) {
	// final StringBuilder sb = new StringBuilder();
	// for (int i = 0; i < bytes.length; i++) {
	// sb.append(bytes[i]);
	// if ((i + 1) % offset == 0) {
	// sb.append("\n");
	// } else {
	// sb.append(" ");
	// }
	// }
	// return sb.toString();
	// }
	
	class BlockData {
		public int type;
		public int data;
		
		public BlockData(final int type, final int data) {
			this.type = type;
			this.data = data;
		}
		
		@Override
		public String toString() {
			return type + ":" + data;
		}
	}
}
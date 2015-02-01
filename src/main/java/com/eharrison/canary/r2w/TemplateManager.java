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
import net.minecraft.world.level.chunk.DataLayer;
import net.visualillusionsent.utils.TaskManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import com.eharrison.canary.r2w.io.RegionFile;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.NbtIo;

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
				final boolean success = true;
				
				// Load the target world
				// log.info("name: " + name + " " + type);
				final World world = worldManager.getWorld(name, type, true);
				
				// Determine the block bounds
				final int xMin = Math.min(x1, x2);
				final int xMax = Math.max(x1, x2);
				final int yMin = Math.min(y1, y2);
				final int yMax = Math.max(y1, y2);
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
						final RegionFile region = getRegionFile(name, type, regionX, regionZ);
						// final String regionName = regionX + ":" + regionZ;
						// log.info("Processing region: " + regionName);
						
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
								// Load the chunk
								final int relChunkX = RegionUtil.getRegionRelativeChunkCoordinate(chunkX);
								final int relChunkZ = RegionUtil.getRegionRelativeChunkCoordinate(chunkZ);
								final DataInputStream dis = region.getChunkDataInputStream(relChunkX, relChunkZ);
								final CompoundTag level = NbtIo.read(dis);
								dis.close();
								final CompoundTag chunkData = level.getCompound("Level");
								// final String chunkName = chunkX + ":" + chunkZ + " (" + relChunkX + ":" +
								// relChunkZ
								// + ")";
								// log.info("Processing chunk: " + chunkName);
								
								// Determine the included sections
								final int sectionMin = RegionUtil.getSectionForBlockCoordinate(yMin);
								final int sectionMax = RegionUtil.getSectionForBlockCoordinate(yMax);
								
								// For each section
								final ListTag<?> sections = chunkData.getList("Sections");
								for (int sectionId = sectionMin; sectionId <= sectionMax; sectionId++) {
									// Find the section
									final CompoundTag section = (CompoundTag) sections.get(sectionId);
									// log.info("Processing section: " + sectionId);
									
									// Determine the included blocks
									final int sectionBlockMin = RegionUtil.getSectionBlockIntersection(sectionId,
											yMin);
									final int sectionBlockMax = RegionUtil.getSectionBlockIntersection(sectionId,
											yMax);
									
									for (int blockY = sectionBlockMin; blockY <= sectionBlockMax; blockY++) {
										// Determine the included blocks
										final int blockXMin = RegionUtil.getChunkBlockIntersection(chunkX, xMin);
										final int blockXMax = RegionUtil.getChunkBlockIntersection(chunkX, xMax);
										final int blockZMin = RegionUtil.getChunkBlockIntersection(chunkZ, zMin);
										final int blockZMax = RegionUtil.getChunkBlockIntersection(chunkZ, zMax);
										
										// For each block
										for (int blockX = blockXMin; blockX <= blockXMax; blockX++) {
											for (int blockZ = blockZMin; blockZ <= blockZMax; blockZ++) {
												// final String blockName = blockX + ":" + blockY + ":" + blockZ;
												// log.info("Processing block: " + blockName);
												
												// Determine the relative block location
												final int relX = getChunkRelativeBlockCoordinate(blockX);
												final int relY = getSectionRelativeBlockCoordinate(blockY);
												final int relZ = getChunkRelativeBlockCoordinate(blockZ);
												
												// Get the block type and data from the section
												final byte[] blocks = section.getByteArray("Blocks");
												final DataLayer dataValues = new DataLayer(section.getByteArray("Data"), 4);
												final int blockType = blocks[relY << 8 | relZ << 4 | relX];
												final int blockData = dataValues.get(relX, relY, relZ);
												
												// Set the block in the target world
												world.setBlockAt(blockX, blockY, blockZ, (short) blockType,
														(short) blockData);
											}
										}
									}
								}
							}
						}
						
						// Close the region File
						region.close();
					}
				}
				
				return success;
			}
		});
	}
	
	public Block getTemplateBlock(final String name, final DimensionType type, final int x,
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
		final RegionFile region = getRegionFile(name, type, regionX, regionZ);
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
		
		return new Block(blockType, blockData);
	}
	
	private File getTemplateDir(final String name, final DimensionType type) {
		final File worldDir = new File(templatesDir, name);
		return new File(worldDir, name + "_" + type.getName());
	}
	
	private RegionFile getRegionFile(final String name, final DimensionType type, final int regionX,
			final int regionZ) throws IOException {
		final String resourceName = "r." + regionX + "." + regionZ + ".mca";
		final File templateDir = getTemplateDir(name, type);
		final File regionDir = new File(templateDir, "region");
		final File regionFile = new File(regionDir, resourceName);
		return new RegionFile(regionFile);
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
	
	class Block {
		public int type;
		public int data;
		
		public Block(final int type, final int data) {
			this.type = type;
			this.data = data;
		}
		
		@Override
		public String toString() {
			return type + ":" + data;
		}
	}
}

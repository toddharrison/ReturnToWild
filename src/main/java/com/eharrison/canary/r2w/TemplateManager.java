package com.eharrison.canary.r2w;

import static com.eharrison.canary.r2w.RegionUtil.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import net.canarymod.Canary;
import net.canarymod.api.factory.NBTFactory;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.ListTag;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.minecraft.world.level.chunk.DataLayer;
import net.visualillusionsent.utils.TaskManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import com.eharrison.canary.r2w.io.AnvilConverter;
import com.eharrison.canary.r2w.io.NbtIo;
import com.eharrison.canary.r2w.io.RegionFile;
// import com.mojang.nbt.CompoundTag;
// import com.mojang.nbt.ListTag;
// import com.mojang.nbt.NbtIo;
// import com.mojang.nbt.Tag;

// /rtw restore default normal -10 67 25 -12 67 27
// /rtw restore default normal -10 50 -10 10 80 10
// /rtw restore default normal -8 54 9 -13 51 14

public class TemplateManager {
	private final Logger log;
	private final WorldManager worldManager;
	private final NBTFactory nbtFactory;
	private final File worldsDir;
	private final File templatesDir;
	private final NbtIo nbtIo;
	
	public TemplateManager() {
		this(ReturnPlugin.LOG, Canary.getServer().getWorldManager(), Canary.factory().getNBTFactory(),
				new File("worlds"), new File("templates"));
	}
	
	public TemplateManager(final Logger log, final WorldManager worldManager,
			final NBTFactory nbtFactory, final File worldsDir, final File templatesDir) {
		this.log = log;
		this.worldManager = worldManager;
		this.nbtFactory = nbtFactory;
		this.worldsDir = worldsDir;
		this.templatesDir = templatesDir;
		nbtIo = new NbtIo(nbtFactory);
	}
	
	public Future<Boolean> createTemplate(final String name, final DimensionType type) {
		return TaskManager.submitTask(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				boolean success = true;
				
				// Unload the world
				boolean loaded = false;
				if (worldManager.worldIsLoaded(name, type)) {
					log.debug("Unloading world");
					loaded = true;
					worldManager.getWorld(name, type, false).broadcastMessage(
							"Creating a world template, You have to GET OUT!");
					// TODO: Add pause?
					worldManager.unloadWorld(name, type, true);
				}
				
				// Create the template directory
				final File templateDir = getTemplateDir(name, type);
				if (!templateDir.exists()) {
					success = templateDir.mkdirs();
				}
				log.debug("Prepared template world directory");
				
				if (success) {
					// Copy the world region data into the template
					log.debug("Copying region files");
					try {
						FileUtils.copyDirectory(new File(worldsDir, name + "/" + name + "_" + type.getName()
								+ "/region"), new File(templateDir, "region"));
					} catch (final IOException e) {
						log.error("Error copying region files", e);
					}
				}
				
				// Load the world if it was loaded to begin with
				if (loaded) {
					log.debug("Loading world");
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
	
	public Future<Boolean> update(final String name, final DimensionType type, final int x1,
			final int y1, final int z1, final int x2, final int y2, final int z2) {
		return TaskManager.submitTask(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
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
							RegionFile region = loadRegionFile(name, type, regionX, regionZ);
							if (region == null) {
								region = newRegionFile(name, type, regionX, regionZ);
							}
							
							log.debug("Processing region: " + regionX + ":" + regionZ);
							updateRegion(world, region, regionX, regionZ, xMin, yMin, zMin, xMax, yMax, zMax);
							
							// Close the region File
							region.close();
						}
					}
				}
				
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
								log.debug("Processing region: " + regionX + ":" + regionZ);
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
	
	public BlockType getTemplateBlock(final String name, final DimensionType type, final int x,
			final int y, final int z) throws IOException {
		// Determine the absolute chunk of the block
		final int chunkX = getChunkForBlockCoordinate(x);
		final int chunkZ = getChunkForBlockCoordinate(z);
		
		// Determine the region of the chunk
		final int regionX = getRegionForChunkCoordinate(chunkX);
		final int regionZ = getRegionForChunkCoordinate(chunkZ);
		
		// Load the appropriate region file
		final RegionFile region = loadRegionFile(name, type, regionX, regionZ);
		
		BlockType result = null;
		if (region != null) {
			// Determine the relative chunk in the region
			final int relChunkX = getRegionRelativeChunkCoordinate(chunkX);
			final int relChunkZ = getRegionRelativeChunkCoordinate(chunkZ);
			
			// Load the chunk
			final DataInputStream dis = region.getChunkDataInputStream(relChunkX, relChunkZ);
			if (dis != null) {
				final CompoundTag level = (CompoundTag) nbtIo.read(dis);
				dis.close();
				final CompoundTag chunkData = level.getCompoundTag("Level");
				
				// Get the height map
				// final int[] heightMap = chunkData.getIntArray("HeightMap");
				// System.out.println(printHeightMap(heightMap, 16));
				
				// Determine the section of the chunk
				final int sectionId = getSectionForBlockCoordinate(y);
				
				// Find the section
				final ListTag<?> sections = chunkData.getListTag("Sections");
				if (sections.size() > sectionId) {
					final CompoundTag section = (CompoundTag) sections.get(sectionId);
					
					// Determine the relative block location
					final int relX = getChunkRelativeBlockCoordinate(x);
					final int relY = getSectionRelativeBlockCoordinate(y);
					final int relZ = getChunkRelativeBlockCoordinate(z);
					
					// Get the block type and data from the section
					final byte[] blocks = section.getByteArray("Blocks");
					if (blocks.length > 0) {
						final DataLayer dataValues = new DataLayer(section.getByteArray("Data"), 4);
						final int blockType = blocks[relY << 8 | relZ << 4 | relX];
						final int blockData = dataValues.get(relX, relY, relZ);
						
						// -1 indicates not initialized by TemplateManager
						if (blockType != -1) {
							result = BlockType.fromIdAndData(blockType, blockData);
						}
					}
				}
			}
			
			// Close the region File
			region.close();
		}
		
		return result;
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
						final CompoundTag chunk = (CompoundTag) nbtIo.read(dis);
						System.out.println(chunk);
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
			final CompoundTag level = chunk.getCompoundTag("Level");
			log.debug("Processing chunk: " + chunkX + ":" + chunkZ);
			
			// Determine the included sections
			final int sectionMin = RegionUtil.getSectionForBlockCoordinate(yMin);
			final int sectionMax = RegionUtil.getSectionForBlockCoordinate(yMax);
			
			// For each section
			final ListTag<?> sections = level.getListTag("Sections");
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
			
			// Process BlockEntities
			final ListTag<?> blockEntities = level.getListTag("TileEntities");
			for (int i = 0; i < blockEntities.size(); i++) {
				final CompoundTag blockEntity = (CompoundTag) blockEntities.get(i);
				final int beX = blockEntity.getInt("x");
				final int beY = blockEntity.getInt("y");
				final int beZ = blockEntity.getInt("z");
				
				if (xMin <= beX && beX <= xMax && yMin <= beY && beY <= yMax && zMin <= beZ && beZ <= zMax) {
					log.debug(blockEntity.toString());
					
					final Block block = world.getBlockAt(beX, beY, beZ);
					block.getTileEntity().readFromTag(blockEntity);
				}
			}
			
			// TODO
			// // log.info(level.getAllTags());
			// // for (final Tag tag : level.getAllTags()) {
			// // log.info("Tag: " + tag.getName());
			// // }
			// final ListTag<? extends Tag> blockEntities = level.getList("TileEntities");
			// // log.info(blockEntities.toString());
			// log.info("BlockEntiteis: " + blockEntities.size());
			// final ListTag<? extends Tag> entities = level.getList("Entities");
			// log.info("Entities:      " + entities.size());
		}
	}
	
	private void restoreSection(final World world, final int chunkX, final int chunkZ,
			final CompoundTag section, final int xMin, final int yMin, final int zMin, final int xMax,
			final int yMax, final int zMax) {
		if (section != null && !section.isEmpty()) {
			final int sectionY = section.getByte("Y");
			log.debug("Processing section: " + sectionY);
			
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
		log.debug("Filling section: " + sectionY);
		
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
			// Determine the relative block location
			final int relX = getChunkRelativeBlockCoordinate(blockX);
			final int relY = getSectionRelativeBlockCoordinate(blockY);
			final int relZ = getChunkRelativeBlockCoordinate(blockZ);
			
			// Get the block type and data from the section
			final byte[] blocks = section.getByteArray("Blocks");
			final DataLayer dataValues = new DataLayer(section.getByteArray("Data"), 4);
			final int type = blocks[relY << 8 | relZ << 4 | relX] & 0xFF;
			final int data = dataValues.get(relX, relY, relZ);
			
			log.debug("Setting block: " + blockX + ":" + blockY + ":" + blockZ + " to " + type + ":"
					+ data);
			
			// Set the block in the target world
			final Block block = world.getBlockAt(blockX, blockY, blockZ);
			AnvilConverter.convert(block, (byte) type, data);
		}
	}
	
	private void updateRegion(final World world, final RegionFile region, final int regionX,
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
					
					// Load the chunk
					final DataInputStream dis = region.getChunkDataInputStream(relChunkX, relChunkZ);
					final CompoundTag chunk;
					if (dis == null) {
						chunk = nbtFactory.newCompoundTag("Chunk");
						final CompoundTag level = nbtFactory.newCompoundTag("Level");
						level.put("Sections", nbtFactory.newListTag());
						chunk.put("Level", level);
					} else {
						chunk = (CompoundTag) nbtIo.read(dis);
						dis.close();
					}
					
					// Update the chunk
					updateChunk(world, chunk, chunkX, chunkZ, xMin, yMin, zMin, xMax, yMax, zMax);
					
					// Write the chunk
					final DataOutputStream dos = region.getChunkDataOutputStream(relChunkX, relChunkZ);
					// TODO
					// nbtIo.write(chunk, dos);
					dos.close();
				}
			}
		}
	}
	
	private void updateChunk(final World world, final CompoundTag chunk, final int chunkX,
			final int chunkZ, final int xMin, final int yMin, final int zMin, final int xMax,
			final int yMax, final int zMax) {
		if (chunk != null && !chunk.isEmpty()) {
			final CompoundTag level = chunk.getCompoundTag("Level");
			log.debug("Processing chunk: " + chunkX + ":" + chunkZ);
			
			// Determine the included sections
			final int sectionMin = RegionUtil.getSectionForBlockCoordinate(yMin);
			final int sectionMax = RegionUtil.getSectionForBlockCoordinate(yMax);
			
			// For each section
			final ListTag<CompoundTag> sections = level.getListTag("Sections");
			for (int sectionY = sectionMin; sectionY <= sectionMax; sectionY++) {
				if (sectionY < sections.size()) {
					// Restore the section
					final CompoundTag section = sections.get(sectionY);
					updateSection(world, chunkX, chunkZ, section, xMin, yMin, zMin, xMax, yMax, zMax);
				} else {
					// Create the new sections, then update the correct one
					CompoundTag section = null;
					while (sectionY >= sections.size()) {
						section = nbtFactory.newCompoundTag("Section");
						section.put("Y", (byte) sectionY);
						final byte[] blocks = new byte[4096];
						Arrays.fill(blocks, (byte) -1);
						section.put("Blocks", blocks);
						final byte[] data = new byte[2048];
						Arrays.fill(data, (byte) -1);
						section.put("Data", data);
						sections.add(section);
					}
					updateSection(world, chunkX, chunkZ, section, xMin, yMin, zMin, xMax, yMax, zMax);
				}
			}
		}
	}
	
	private void updateSection(final World world, final int chunkX, final int chunkZ,
			final CompoundTag section, final int xMin, final int yMin, final int zMin, final int xMax,
			final int yMax, final int zMax) {
		if (section != null && !section.isEmpty()) {
			final int sectionY = section.getByte("Y");
			log.debug("Processing section: " + sectionY);
			
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
						updateBlock(world, section, blockX, blockY, blockZ);
					}
				}
			}
		}
	}
	
	private void updateBlock(final World world, final CompoundTag section, final int blockX,
			final int blockY, final int blockZ) {
		if (world != null && section != null && !section.isEmpty()) {
			// Determine the relative block location
			final int relX = getChunkRelativeBlockCoordinate(blockX);
			final int relY = getSectionRelativeBlockCoordinate(blockY);
			final int relZ = getChunkRelativeBlockCoordinate(blockZ);
			
			// Get the block in the target world
			final BlockType block = world.getBlockAt(blockX, blockY, blockZ).getType();
			
			log.debug("Setting template: " + blockX + ":" + blockY + ":" + blockZ + " to "
					+ block.getId() + ":" + block.getData());
			
			// Set the block type and data in the section
			byte[] blocks = section.getByteArray("Blocks");
			if (blocks.length == 0) {
				blocks = new byte[4096];
			}
			byte[] data = section.getByteArray("Data");
			if (data.length == 0) {
				data = new byte[2048];
			}
			final DataLayer dataValues = new DataLayer(data, 4);
			blocks[relY << 8 | relZ << 4 | relX] = (byte) block.getId();
			// TODO restore properties into anvil data value
			System.out.println("Setting block " + block + " to " + block.getData());
			dataValues.set(relX, relY, relZ, block.getData());
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
	
	private RegionFile newRegionFile(final String name, final DimensionType type, final int regionX,
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
}

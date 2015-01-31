package com.eharrison.canary.r2w;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Callable;

import net.canarymod.Canary;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.minecraft.world.level.chunk.DataLayer;
import net.visualillusionsent.utils.TaskManager;

import org.apache.commons.io.FileUtils;

import com.eharrison.canary.r2w.io.RegionFile;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.NbtIo;

public class TemplateManager {
	private final WorldManager worldManager;
	private final File templatesDir;
	
	public TemplateManager() {
		worldManager = Canary.getServer().getWorldManager();
		templatesDir = new File("templates");
	}
	
	public void createTemplate(final String name, final DimensionType type) {
		TaskManager.submitTask(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				boolean success = true;
				
				// Unload the world
				boolean loaded = false;
				if (worldManager.worldIsLoaded(name, type)) {
					ReturnPlugin.LOG.info("Unloading world");
					loaded = true;
					worldManager.getWorld(name, type, false).broadcastMessage(
							"Creating a world template, GET OUT!");
					worldManager.unloadWorld(name, type, true);
				}
				
				// Create the template directory
				final File worldDir = new File(templatesDir, name);
				final File dimDir = new File(worldDir, name + "_" + type.getName());
				if (!dimDir.exists()) {
					success = dimDir.mkdirs();
				}
				ReturnPlugin.LOG.info("Prepared template world directory");
				
				if (success) {
					// Copy the world region data into the template
					ReturnPlugin.LOG.info("Copying region files");
					try {
						FileUtils.copyDirectory(new File("worlds/" + name + "/" + name + "_" + type.getName()
								+ "/region"), new File(dimDir, "region"));
					} catch (final IOException e) {
						ReturnPlugin.LOG.error("Error copying region files", e);
					}
				}
				
				// Load the world if it was loaded to begin with
				if (loaded) {
					ReturnPlugin.LOG.info("Loading world");
					worldManager.loadWorld(name, type);
				}
				
				ReturnPlugin.LOG.info("Created template " + name + "_" + type.getName() + ": " + success);
				return success;
			}
		});
	}
	
	public void removeTemplate(final String name, final DimensionType type) {
		TaskManager.submitTask(new Callable<Boolean>() {
			@Override
			public Boolean call() throws IOException {
				boolean success = true;
				final File worldDir = new File(templatesDir, name);
				final File dimDir = new File(worldDir, name + "_" + type.getName());
				if (dimDir.exists() && dimDir.canWrite()) {
					success = FileUtils.deleteQuietly(dimDir);
				}
				ReturnPlugin.LOG.info("Removed template " + name + "_" + type.getName() + ": " + success);
				return success;
			}
		});
	}
	
	public boolean loadTemplate(final World world) {
		final String name = world.getName();
		final File worldDir = new File(templatesDir, name);
		final File dimDir = new File(worldDir, name + "_" + world.getType());
		
		return dimDir.exists();
	}
	
	public Block getTemplateBlock(final int x, final int y, final int z) throws URISyntaxException,
			IOException {
		// Determine the absolute chunk of the block
		final int chunkX = x >= 0 ? x / 16 : x / 16 - 1;
		final int chunkZ = z >= 0 ? z / 16 : z / 16 - 1;
		// System.out.println("Chunk: " + chunkX + ":" + chunkZ);
		
		// Determine the region of the chunk
		final int regionX = chunkX >= 0 ? chunkX / 32 : chunkX / 32 - 1;
		final int regionZ = chunkZ >= 0 ? chunkZ / 32 : chunkZ / 32 - 1;
		// System.out.println("Region: " + regionX + ":" + regionZ);
		
		// Load the appropriate region file
		final String resourceName = "r." + regionX + "." + regionZ + ".mca";
		final URL regionUrl = this.getClass().getClassLoader().getResource(resourceName);
		final File regionFile = new File(regionUrl.toURI());
		final RegionFile region = new RegionFile(regionFile);
		
		// Determine the relative chunk in the region
		final int relChunkX = (chunkX % 32 + 32) % 32;
		final int relChunkZ = (chunkZ % 32 + 32) % 32;
		// System.out.println("Region Chunk: " + relChunkX + ":" + relChunkZ);
		
		// Load the chunk
		final DataInputStream dis = region.getChunkDataInputStream(relChunkX, relChunkZ);
		final CompoundTag level = NbtIo.read(dis);
		dis.close();
		final CompoundTag chunkData = level.getCompound("Level");
		
		// Get the height map
		final int[] heightMap = chunkData.getIntArray("HeightMap");
		// System.out.println(printHeightMap(heightMap, 16));
		
		// Determine the section of the chunk
		final int sectionId = y / 16;
		// System.out.println("Section: " + sectionId);
		
		// Find the section
		final ListTag<?> sections = chunkData.getList("Sections");
		final CompoundTag section = (CompoundTag) sections.get(sectionId);
		// System.out.println("Section: " + sectionId);
		// System.out.println(printSection(section));
		
		// Determine the relative block location
		final int relX = (x % 16 + 16) % 16;
		final int relY = y % 16;
		final int relZ = (z % 16 + 16) % 16;
		// System.out.println("Section Block: " + relX + ":" + relY + ":" + relZ);
		
		// Get the block type and data from the section
		final byte[] blocks = section.getByteArray("Blocks");
		final DataLayer dataValues = new DataLayer(section.getByteArray("Data"), 4);
		final int type = blocks[relY << 8 | relZ << 4 | relX];
		final int data = dataValues.get(relX, relY, relZ);
		
		// Close the region File
		region.close();
		
		return new Block(type, data);
	}
	
	private class Block {
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

package com.eharrison.canary.r2w;

import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import net.minecraft.world.level.chunk.DataLayer;

import org.junit.Test;

import com.eharrison.canary.r2w.io.RegionFile;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.NbtIo;

public class ReturnTest {
	@Test
	public void test() throws Exception {
		assertEquals(57, getTemplateBlock(-142, 62, 278).type);
		assertEquals(57, getTemplateBlock(-142, 62, 277).type);
		assertEquals(57, getTemplateBlock(-142, 62, 276).type);
		assertEquals(57, getTemplateBlock(-142, 62, 275).type);
		assertEquals(57, getTemplateBlock(-141, 62, 277).type);
		assertEquals(57, getTemplateBlock(-143, 62, 277).type);
		assertEquals(2, getTemplateBlock(-144, 62, 277).type);
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
		System.out.println(printHeightMap(heightMap, 16));
		
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
	
	private String printHeightMap(final int[] bytes, final int offset) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(bytes[i]);
			if ((i + 1) % offset == 0) {
				sb.append("\n");
			} else {
				sb.append(" ");
			}
		}
		return sb.toString();
	}
	
	private String printSection(final CompoundTag section) {
		final StringBuilder sb = new StringBuilder();
		// for (int yBase = 0; yBase < 2 /* sections.size() */; yBase++) {
		// final CompoundTag section = (CompoundTag) sections.get(yBase);
		
		final byte[] blocks = section.getByteArray("Blocks");
		final DataLayer dataValues = new DataLayer(section.getByteArray("Data"), 4);
		
		// sb.append("Section: ");
		// sb.append(yBase);
		// sb.append(" (");
		// sb.append(yBase * 16);
		// sb.append(" to ");
		// sb.append((yBase + 1) * 16 - 1);
		// sb.append(")\n");
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					final byte block = blocks[y << 8 | z << 4 | x];
					final int data = dataValues.get(x, y, z);
					
					sb.append(block);
					sb.append(":");
					sb.append(data);
					sb.append(" ");
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		// }
		return sb.toString();
	}
	
	private String printHeightMap(final byte[] bytes, final int offset) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(bytes[i]);
			if ((i + 1) % offset == 0) {
				sb.append("\n");
			} else {
				sb.append(" ");
			}
		}
		return sb.toString();
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

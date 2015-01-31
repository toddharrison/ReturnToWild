package net.minecraft.world.level.chunk.storage;

/**
 * Copyright Mojang AB.
 * 
 * Don't do evil.
 */

import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.DataLayer;
import net.minecraft.world.level.chunk.OldDataLayer;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;

public class OldChunkStorage {
	
	private final static int DATALAYER_BITS = 7;
	
	@SuppressWarnings("unchecked")
	public static OldLevelChunk load(final CompoundTag tag) {
		final int x = tag.getInt("xPos");
		final int z = tag.getInt("zPos");
		
		final OldLevelChunk levelChunk = new OldLevelChunk(x, z);
		levelChunk.blocks = tag.getByteArray("Blocks");
		levelChunk.data = new OldDataLayer(tag.getByteArray("Data"), DATALAYER_BITS);
		levelChunk.skyLight = new OldDataLayer(tag.getByteArray("SkyLight"), DATALAYER_BITS);
		levelChunk.blockLight = new OldDataLayer(tag.getByteArray("BlockLight"), DATALAYER_BITS);
		levelChunk.heightmap = tag.getByteArray("HeightMap");
		levelChunk.terrainPopulated = tag.getBoolean("TerrainPopulated");
		levelChunk.entities = (ListTag<CompoundTag>) tag.getList("Entities");
		levelChunk.tileEntities = (ListTag<CompoundTag>) tag.getList("TileEntities");
		levelChunk.tileTicks = (ListTag<CompoundTag>) tag.getList("TileTicks");
		levelChunk.lastUpdated = tag.getLong("LastUpdate");
		
		return levelChunk;
	}
	
	public static void convertToAnvilFormat(final OldLevelChunk data, final CompoundTag tag,
			final BiomeSource biomeSource) {
		
		tag.putInt("xPos", data.x);
		tag.putInt("zPos", data.z);
		tag.putLong("LastUpdate", data.lastUpdated);
		final int[] newHeight = new int[data.heightmap.length];
		for (int i = 0; i < data.heightmap.length; i++) {
			newHeight[i] = data.heightmap[i];
		}
		tag.putIntArray("HeightMap", newHeight);
		tag.putBoolean("TerrainPopulated", data.terrainPopulated);
		
		final ListTag<CompoundTag> sectionTags = new ListTag<CompoundTag>("Sections");
		for (int yBase = 0; yBase < 128 / 16; yBase++) {
			
			// find non-air
			boolean allAir = true;
			for (int x = 0; x < 16 && allAir; x++) {
				for (int y = 0; y < 16 && allAir; y++) {
					for (int z = 0; z < 16; z++) {
						final int pos = x << 11 | z << 7 | y + (yBase << 4);
						final int block = data.blocks[pos];
						if (block != 0) {
							allAir = false;
							break;
						}
					}
				}
			}
			
			if (allAir) {
				continue;
			}
			
			// build section
			final byte[] blocks = new byte[16 * 16 * 16];
			final DataLayer dataValues = new DataLayer(blocks.length, 4);
			final DataLayer skyLight = new DataLayer(blocks.length, 4);
			final DataLayer blockLight = new DataLayer(blocks.length, 4);
			
			for (int x = 0; x < 16; x++) {
				for (int y = 0; y < 16; y++) {
					for (int z = 0; z < 16; z++) {
						final int pos = x << 11 | z << 7 | y + (yBase << 4);
						final int block = data.blocks[pos];
						
						blocks[y << 8 | z << 4 | x] = (byte) (block & 0xff);
						dataValues.set(x, y, z, data.data.get(x, y + (yBase << 4), z));
						skyLight.set(x, y, z, data.skyLight.get(x, y + (yBase << 4), z));
						blockLight.set(x, y, z, data.blockLight.get(x, y + (yBase << 4), z));
					}
				}
			}
			
			final CompoundTag sectionTag = new CompoundTag();
			
			sectionTag.putByte("Y", (byte) (yBase & 0xff));
			sectionTag.putByteArray("Blocks", blocks);
			sectionTag.putByteArray("Data", dataValues.data);
			sectionTag.putByteArray("SkyLight", skyLight.data);
			sectionTag.putByteArray("BlockLight", blockLight.data);
			
			sectionTags.add(sectionTag);
		}
		tag.put("Sections", sectionTags);
		
		// create biome array
		if (biomeSource != null) {
			final byte[] biomes = new byte[16 * 16];
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					biomes[z << 4 | x] = (byte) (biomeSource.getBiomeId(data.x << 4 | x, data.z << 4 | z) & 0xff);
				}
			}
			tag.putByteArray("Biomes", biomes);
		}
		
		tag.put("Entities", data.entities);
		
		tag.put("TileEntities", data.tileEntities);
		
		if (data.tileTicks != null) {
			tag.put("TileTicks", data.tileTicks);
		}
	}
	
	public static class OldLevelChunk {
		
		public long lastUpdated;
		public boolean lastSaveHadEntities;
		public boolean terrainPopulated;
		public byte[] heightmap;
		public OldDataLayer blockLight;
		public OldDataLayer skyLight;
		public OldDataLayer data;
		public byte[] blocks;
		
		public ListTag<CompoundTag> entities;
		public ListTag<CompoundTag> tileEntities;
		public ListTag<CompoundTag> tileTicks;
		
		public final int x;
		public final int z;
		
		public OldLevelChunk(final int x, final int z) {
			this.x = x;
			this.z = z;
		}
		
	}
	
}

package com.goodformentertainment.canary.r2w;

public final class RegionUtil {
	public static final int BLOCKS_IN_CHUNK = 16;
	public static final int CHUNKS_IN_REGION = 32;
	public static final int BLOCKS_IN_REGION = BLOCKS_IN_CHUNK * CHUNKS_IN_REGION;
	public static final int BLOCKS_IN_SECTION = 16;
	
	public static int getChunkForBlockCoordinate(final int blockCoordinate) {
		return blockCoordinate >= 0 ? blockCoordinate / BLOCKS_IN_CHUNK : (blockCoordinate + 1)
				/ BLOCKS_IN_CHUNK - 1;
	}
	
	public static int getRegionForChunkCoordinate(final int chunkCoordinate) {
		return chunkCoordinate >= 0 ? chunkCoordinate / CHUNKS_IN_REGION : (chunkCoordinate + 1)
				/ CHUNKS_IN_REGION - 1;
	}
	
	public static int getRegionForBlockCoordinate(final int blockCoordinate) {
		return blockCoordinate >= 0 ? blockCoordinate / BLOCKS_IN_REGION : (blockCoordinate + 1)
				/ BLOCKS_IN_REGION - 1;
	}
	
	public static int getSectionForBlockCoordinate(final int blockCoordinate) {
		// assert blockCoordinate >= 0 && blockCoordinate < 255;
		return blockCoordinate / BLOCKS_IN_SECTION;
	}
	
	public static int getRegionRelativeChunkCoordinate(final int chunkCoordinate) {
		return (chunkCoordinate % CHUNKS_IN_REGION + CHUNKS_IN_REGION) % CHUNKS_IN_REGION;
	}
	
	public static int getChunkRelativeBlockCoordinate(final int blockCoordinate) {
		return (blockCoordinate % BLOCKS_IN_CHUNK + BLOCKS_IN_CHUNK) % BLOCKS_IN_CHUNK;
	}
	
	public static int getSectionRelativeBlockCoordinate(final int blockCoordinate) {
		return blockCoordinate % BLOCKS_IN_SECTION;
	}
	
	public static int getRegionBlockIntersection(final int regionCoordinate, final int blockCoordinate) {
		final int regionBlockMin = regionCoordinate * BLOCKS_IN_REGION;
		final int regionBlockMax = regionBlockMin + BLOCKS_IN_REGION - 1;
		return Math.min(Math.max(blockCoordinate, regionBlockMin), regionBlockMax);
	}
	
	public static int getChunkBlockIntersection(final int chunkCoordinate, final int blockCoordinate) {
		final int chunkBlockMin = chunkCoordinate * BLOCKS_IN_CHUNK;
		final int chunkBlockMax = chunkBlockMin + BLOCKS_IN_CHUNK - 1;
		return Math.min(Math.max(blockCoordinate, chunkBlockMin), chunkBlockMax);
	}
	
	public static int getSectionBlockIntersection(final int section, final int blockCoordinate) {
		final int sectionBlockMin = section * BLOCKS_IN_SECTION;
		final int sectionBlockMax = sectionBlockMin + BLOCKS_IN_SECTION - 1;
		return Math.min(Math.max(blockCoordinate, sectionBlockMin), sectionBlockMax);
	}
}

package com.goodformentertainment.canary.r2w.io;

import java.io.ByteArrayOutputStream;

public class ChunkOutputStream extends ByteArrayOutputStream {
	private static final int BUFFER_SIZE = 8096; // initialize to 8KB
	
	private final RegionFile regionFile;
	private final int x, z;
	
	public ChunkOutputStream(final RegionFile regionFile, final int x, final int z) {
		super(BUFFER_SIZE);
		this.regionFile = regionFile;
		this.x = x;
		this.z = z;
	}
	
	/**
	 * lets chunk writing be multithreaded by not locking the whole file as a
	 * chunk is serializing -- only writes when serialization is over
	 */
	@Override
	public void close() {
		regionFile.write(x, z, buf, count);
	}
}

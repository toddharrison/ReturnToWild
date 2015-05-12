package com.goodformentertainment.canary.r2w.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class RegionFile {
	public static final String ANVIL_EXTENSION = ".mca";
	public static final String MCREGION_EXTENSION = ".mcr";
	
	private static final int VERSION_GZIP = 1;
	private static final int VERSION_DEFLATE = 2;
	
	private static final int SECTOR_BYTES = 4096;
	private static final int SECTOR_INTS = SECTOR_BYTES / 4;
	
	static final int CHUNK_HEADER_SIZE = 5;
	private static final byte emptySector[] = new byte[4096];
	
	private final File fileName;
	private final RandomAccessFile file;
	private final int offsets[];
	private final int chunkTimestamps[];
	private final ArrayList<Boolean> sectorFree;
	private int sizeDelta;
	private long lastModified = 0;
	
	public RegionFile(final File path) throws IOException {
		offsets = new int[SECTOR_INTS];
		chunkTimestamps = new int[SECTOR_INTS];
		
		fileName = path;
		debugln("REGION LOAD " + fileName);
		
		sizeDelta = 0;
		
		if (path.exists()) {
			lastModified = path.lastModified();
		}
		
		file = new RandomAccessFile(path, "rw");
		
		if (file.length() < SECTOR_BYTES) {
			/* we need to write the chunk offset table */
			for (int i = 0; i < SECTOR_INTS; ++i) {
				file.writeInt(0);
			}
			// write another sector for the timestamp info
			for (int i = 0; i < SECTOR_INTS; ++i) {
				file.writeInt(0);
			}
			
			sizeDelta += SECTOR_BYTES * 2;
		}
		
		if ((file.length() & 0xfff) != 0) {
			/* the file size is not a multiple of 4KB, grow it */
			for (int i = 0; i < (file.length() & 0xfff); ++i) {
				file.write((byte) 0);
			}
		}
		
		/* set up the available sector map */
		final int nSectors = (int) file.length() / SECTOR_BYTES;
		sectorFree = new ArrayList<Boolean>(nSectors);
		
		for (int i = 0; i < nSectors; ++i) {
			sectorFree.add(true);
		}
		
		sectorFree.set(0, false); // chunk offset table
		sectorFree.set(1, false); // for the last modified info
		
		file.seek(0);
		for (int i = 0; i < SECTOR_INTS; ++i) {
			final int offset = file.readInt();
			offsets[i] = offset;
			if (offset != 0 && (offset >> 8) + (offset & 0xFF) <= sectorFree.size()) {
				for (int sectorNum = 0; sectorNum < (offset & 0xFF); ++sectorNum) {
					sectorFree.set((offset >> 8) + sectorNum, false);
				}
			}
		}
		for (int i = 0; i < SECTOR_INTS; ++i) {
			final int lastModValue = file.readInt();
			chunkTimestamps[i] = lastModValue;
		}
	}
	
	/* the modification date of the region file when it was first opened */
	public long lastModified() {
		return lastModified;
	}
	
	/* gets how much the region file has grown since it was last checked */
	public synchronized int getSizeDelta() {
		final int ret = sizeDelta;
		sizeDelta = 0;
		return ret;
	}
	
	// various small debug printing helpers
	private void debug(final String in) {
		// System.out.print(in);
	}
	
	private void debugln(final String in) {
		debug(in + "\n");
	}
	
	private void debug(final String mode, final int x, final int z, final String in) {
		debug("REGION " + mode + " " + fileName.getName() + "[" + x + "," + z + "] = " + in);
	}
	
	private void debug(final String mode, final int x, final int z, final int count, final String in) {
		debug("REGION " + mode + " " + fileName.getName() + "[" + x + "," + z + "] " + count + "B = "
				+ in);
	}
	
	private void debugln(final String mode, final int x, final int z, final String in) {
		debug(mode, x, z, in + "\n");
	}
	
	/*
	 * gets an (uncompressed) stream representing the chunk data returns null if
	 * the chunk is not found or an error occurs
	 */
	public synchronized DataInputStream getChunkDataInputStream(final int x, final int z) {
		if (outOfBounds(x, z)) {
			debugln("READ", x, z, "out of bounds");
			return null;
		}
		
		try {
			final int offset = getOffset(x, z);
			if (offset == 0) {
				debugln("READ", x, z, "miss");
				return null;
			}
			
			final int sectorNumber = offset >> 8;
			final int numSectors = offset & 0xFF;
			
			if (sectorNumber + numSectors > sectorFree.size()) {
				debugln("READ", x, z, "invalid sector");
				return null;
			}
			
			file.seek(sectorNumber * SECTOR_BYTES);
			final int length = file.readInt();
			
			if (length > SECTOR_BYTES * numSectors) {
				debugln("READ", x, z, "invalid length: " + length + " > 4096 * " + numSectors);
				return null;
			}
			
			final byte version = file.readByte();
			if (version == VERSION_GZIP) {
				final byte[] data = new byte[length - 1];
				file.read(data);
				final DataInputStream ret = new DataInputStream(new BufferedInputStream(
						new GZIPInputStream(new ByteArrayInputStream(data))));
				debugln("READ", x, z, "found");
				return ret;
			} else if (version == VERSION_DEFLATE) {
				final byte[] data = new byte[length - 1];
				file.read(data);
				final DataInputStream ret = new DataInputStream(new BufferedInputStream(
						new InflaterInputStream(new ByteArrayInputStream(data))));
				debugln("READ", x, z, "found");
				return ret;
			}
			
			debugln("READ", x, z, "unknown version " + version);
			return null;
		} catch (final IOException e) {
			debugln("READ", x, z, "exception");
			return null;
		}
	}
	
	public DataOutputStream getChunkDataOutputStream(final int x, final int z) {
		if (outOfBounds(x, z)) {
			return null;
		}
		
		return new DataOutputStream(new DeflaterOutputStream(new ChunkOutputStream(this, x, z)));
	}
	
	/* write a chunk at (x,z) with length bytes of data to disk */
	protected synchronized void write(final int x, final int z, final byte[] data, final int length) {
		try {
			final int offset = getOffset(x, z);
			int sectorNumber = offset >> 8;
			final int sectorsAllocated = offset & 0xFF;
			final int sectorsNeeded = (length + CHUNK_HEADER_SIZE) / SECTOR_BYTES + 1;
			
			// maximum chunk size is 1MB
			if (sectorsNeeded >= 256) {
				return;
			}
			
			if (sectorNumber != 0 && sectorsAllocated == sectorsNeeded) {
				/* we can simply overwrite the old sectors */
				debug("SAVE", x, z, length, "rewrite");
				write(sectorNumber, data, length);
			} else {
				/* we need to allocate new sectors */
				
				/* mark the sectors previously used for this chunk as free */
				for (int i = 0; i < sectorsAllocated; ++i) {
					sectorFree.set(sectorNumber + i, true);
				}
				
				/* scan for a free space large enough to store this chunk */
				int runStart = sectorFree.indexOf(true);
				int runLength = 0;
				if (runStart != -1) {
					for (int i = runStart; i < sectorFree.size(); ++i) {
						if (runLength != 0) {
							if (sectorFree.get(i)) {
								runLength++;
							} else {
								runLength = 0;
							}
						} else if (sectorFree.get(i)) {
							runStart = i;
							runLength = 1;
						}
						if (runLength >= sectorsNeeded) {
							break;
						}
					}
				}
				
				if (runLength >= sectorsNeeded) {
					/* we found a free space large enough */
					debug("SAVE", x, z, length, "reuse");
					sectorNumber = runStart;
					setOffset(x, z, sectorNumber << 8 | sectorsNeeded);
					for (int i = 0; i < sectorsNeeded; ++i) {
						sectorFree.set(sectorNumber + i, false);
					}
					write(sectorNumber, data, length);
				} else {
					/*
					 * no free space large enough found -- we need to grow the
					 * file
					 */
					debug("SAVE", x, z, length, "grow");
					file.seek(file.length());
					sectorNumber = sectorFree.size();
					for (int i = 0; i < sectorsNeeded; ++i) {
						file.write(emptySector);
						sectorFree.add(false);
					}
					sizeDelta += SECTOR_BYTES * sectorsNeeded;
					
					write(sectorNumber, data, length);
					setOffset(x, z, sectorNumber << 8 | sectorsNeeded);
				}
			}
			setTimestamp(x, z, (int) (System.currentTimeMillis() / 1000L));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	/* write a chunk data to the region file at specified sector number */
	private void write(final int sectorNumber, final byte[] data, final int length)
			throws IOException {
		debugln(" " + sectorNumber);
		file.seek(sectorNumber * SECTOR_BYTES);
		file.writeInt(length + 1); // chunk length
		file.writeByte(VERSION_DEFLATE); // chunk version number
		file.write(data, 0, length); // chunk data
	}
	
	/* is this an invalid chunk coordinate? */
	public boolean outOfBounds(final int x, final int z) {
		return x < 0 || x >= 32 || z < 0 || z >= 32;
	}
	
	private int getOffset(final int x, final int z) {
		return offsets[x + z * 32];
	}
	
	public boolean hasChunk(final int x, final int z) {
		boolean hasChunk = false;
		if (!outOfBounds(x, z)) {
			hasChunk = getOffset(x, z) != 0;
		}
		return hasChunk;
	}
	
	private void setOffset(final int x, final int z, final int offset) throws IOException {
		offsets[x + z * 32] = offset;
		file.seek((x + z * 32) * 4);
		file.writeInt(offset);
	}
	
	private void setTimestamp(final int x, final int z, final int value) throws IOException {
		chunkTimestamps[x + z * 32] = value;
		file.seek(SECTOR_BYTES + (x + z * 32) * 4);
		file.writeInt(value);
	}
	
	public void close() throws IOException {
		file.close();
	}
	
	public String printOffsets() {
		final StringBuilder sb = new StringBuilder();
		sb.append("0:\t");
		for (int i = 0; i < offsets.length; i++) {
			sb.append(offsets[i]);
			if ((i + 1) % 32 == 0) {
				sb.append("\n");
				sb.append(i / 32 + 1 + ":\t");
			} else {
				sb.append("\t");
			}
		}
		return sb.toString();
	}
}

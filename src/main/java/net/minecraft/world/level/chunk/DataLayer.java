package net.minecraft.world.level.chunk;

public class DataLayer {
	public final byte[] data;
	private final int depthBits;
	private final int depthBitsPlusFour;
	
	public DataLayer(final int length, final int depthBits) {
		data = new byte[length >> 1];
		this.depthBits = depthBits;
		depthBitsPlusFour = depthBits + 4;
	}
	
	public DataLayer(final byte[] data, final int depthBits) {
		this.data = data;
		this.depthBits = depthBits;
		depthBitsPlusFour = depthBits + 4;
	}
	
	public int get(final int x, final int y, final int z) {
		final int pos = y << depthBitsPlusFour | z << depthBits | x;
		final int slot = pos >> 1;
		final int part = pos & 1;
		
		if (part == 0) {
			return data[slot] & 0xf;
		} else {
			return data[slot] >> 4 & 0xf;
		}
	}
	
	public void set(final int x, final int y, final int z, final int val) {
		final int pos = y << depthBitsPlusFour | z << depthBits | x;
		
		final int slot = pos >> 1;
		final int part = pos & 1;
		
		if (part == 0) {
			data[slot] = (byte) (data[slot] & 0xf0 | val & 0xf);
		} else {
			data[slot] = (byte) (data[slot] & 0x0f | (val & 0xf) << 4);
		}
	}
	
	public boolean isValid() {
		return data != null;
	}
	
	public void setAll(final int br) {
		final byte val = (byte) ((br | br << 4) & 0xff);
		for (int i = 0; i < data.length; i++) {
			data[i] = val;
		}
	}
}

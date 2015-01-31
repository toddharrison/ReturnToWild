package com.mojang.nbt;

/**
 * Copyright Mojang AB.
 * 
 * Don't do evil.
 */

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CompoundTag extends Tag {
	private final Map<String, Tag> tags = new HashMap<String, Tag>();
	
	public CompoundTag() {
		super("");
	}
	
	public CompoundTag(final String name) {
		super(name);
	}
	
	@Override
	void write(final DataOutput dos) throws IOException {
		for (final Tag tag : tags.values()) {
			Tag.writeNamedTag(tag, dos);
		}
		dos.writeByte(Tag.TAG_End);
	}
	
	@Override
	void load(final DataInput dis) throws IOException {
		tags.clear();
		Tag tag;
		while ((tag = Tag.readNamedTag(dis)).getId() != Tag.TAG_End) {
			tags.put(tag.getName(), tag);
		}
	}
	
	public Collection<Tag> getAllTags() {
		return tags.values();
	}
	
	@Override
	public byte getId() {
		return TAG_Compound;
	}
	
	public void put(final String name, final Tag tag) {
		tags.put(name, tag.setName(name));
	}
	
	public void putByte(final String name, final byte value) {
		tags.put(name, new ByteTag(name, value));
	}
	
	public void putShort(final String name, final short value) {
		tags.put(name, new ShortTag(name, value));
	}
	
	public void putInt(final String name, final int value) {
		tags.put(name, new IntTag(name, value));
	}
	
	public void putLong(final String name, final long value) {
		tags.put(name, new LongTag(name, value));
	}
	
	public void putFloat(final String name, final float value) {
		tags.put(name, new FloatTag(name, value));
	}
	
	public void putDouble(final String name, final double value) {
		tags.put(name, new DoubleTag(name, value));
	}
	
	public void putString(final String name, final String value) {
		tags.put(name, new StringTag(name, value));
	}
	
	public void putByteArray(final String name, final byte[] value) {
		tags.put(name, new ByteArrayTag(name, value));
	}
	
	public void putIntArray(final String name, final int[] value) {
		tags.put(name, new IntArrayTag(name, value));
	}
	
	public void putCompound(final String name, final CompoundTag value) {
		tags.put(name, value.setName(name));
	}
	
	public void putBoolean(final String string, final boolean val) {
		putByte(string, val ? (byte) 1 : 0);
	}
	
	public Tag get(final String name) {
		return tags.get(name);
	}
	
	public boolean contains(final String name) {
		return tags.containsKey(name);
	}
	
	public byte getByte(final String name) {
		if (!tags.containsKey(name)) {
			return (byte) 0;
		}
		return ((ByteTag) tags.get(name)).data;
	}
	
	public short getShort(final String name) {
		if (!tags.containsKey(name)) {
			return (short) 0;
		}
		return ((ShortTag) tags.get(name)).data;
	}
	
	public int getInt(final String name) {
		if (!tags.containsKey(name)) {
			return 0;
		}
		return ((IntTag) tags.get(name)).data;
	}
	
	public long getLong(final String name) {
		if (!tags.containsKey(name)) {
			return 0;
		}
		return ((LongTag) tags.get(name)).data;
	}
	
	public float getFloat(final String name) {
		if (!tags.containsKey(name)) {
			return 0;
		}
		return ((FloatTag) tags.get(name)).data;
	}
	
	public double getDouble(final String name) {
		if (!tags.containsKey(name)) {
			return 0;
		}
		return ((DoubleTag) tags.get(name)).data;
	}
	
	public String getString(final String name) {
		if (!tags.containsKey(name)) {
			return "";
		}
		return ((StringTag) tags.get(name)).data;
	}
	
	public byte[] getByteArray(final String name) {
		if (!tags.containsKey(name)) {
			return new byte[0];
		}
		return ((ByteArrayTag) tags.get(name)).data;
	}
	
	public int[] getIntArray(final String name) {
		if (!tags.containsKey(name)) {
			return new int[0];
		}
		return ((IntArrayTag) tags.get(name)).data;
	}
	
	public CompoundTag getCompound(final String name) {
		if (!tags.containsKey(name)) {
			return new CompoundTag(name);
		}
		return (CompoundTag) tags.get(name);
	}
	
	@SuppressWarnings("unchecked")
	public ListTag<? extends Tag> getList(final String name) {
		if (!tags.containsKey(name)) {
			return new ListTag<Tag>(name);
		}
		return (ListTag<? extends Tag>) tags.get(name);
	}
	
	public boolean getBoolean(final String string) {
		return getByte(string) != 0;
	}
	
	@Override
	public String toString() {
		return tags.keySet().toString();
		// return "" + tags.size() + " entries";
	}
	
	@Override
	public void print(String prefix, final PrintStream out) {
		super.print(prefix, out);
		out.println(prefix + "{");
		final String orgPrefix = prefix;
		prefix += "   ";
		for (final Tag tag : tags.values()) {
			tag.print(prefix, out);
		}
		out.println(orgPrefix + "}");
	}
	
	public boolean isEmpty() {
		return tags.isEmpty();
	}
	
	@Override
	public Tag copy() {
		final CompoundTag tag = new CompoundTag(getName());
		for (final String key : tags.keySet()) {
			tag.put(key, tags.get(key).copy());
		}
		return tag;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (super.equals(obj)) {
			final CompoundTag o = (CompoundTag) obj;
			return tags.entrySet().equals(o.tags.entrySet());
		}
		return false;
	}
}

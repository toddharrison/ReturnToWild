package com.eharrison.canary.r2w.io;

import java.io.DataInput;
import java.io.IOException;

import net.canarymod.api.factory.NBTFactory;
import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.ListTag;
import net.canarymod.api.nbt.NBTTagType;

public class NbtIo {
	private static final byte COMPOUND_TAG_END = 0;
	
	private final NBTFactory factory;
	
	public NbtIo(final NBTFactory factory) {
		this.factory = factory;
	}
	
	public BaseTag<?> read(final DataInput dis) throws IOException {
		final NBTTagType type = NBTTagType.getTypeFromId(dis.readByte());
		final String name = dis.readUTF();
		final BaseTag<?> tag = readTag(name, type, dis);
		// System.out.println(tag);
		return tag;
	}
	
	private BaseTag<?> readTag(final NBTTagType type, final DataInput dis) throws IOException {
		return readTag(null, type, dis);
	}
	
	private BaseTag<?> readTag(final String name, final NBTTagType type, final DataInput dis)
			throws IOException {
		switch (type) {
			case BYTE:
				return factory.newByteTag(dis.readByte());
			case SHORT:
				return factory.newShortTag(dis.readShort());
			case INT:
				return factory.newIntTag(dis.readInt());
			case LONG:
				return factory.newLongTag(dis.readLong());
			case FLOAT:
				return factory.newFloatTag(dis.readFloat());
			case DOUBLE:
				return factory.newDoubleTag(dis.readDouble());
			case BYTE_ARRAY:
				return factory.newByteArrayTag(readByteArray(dis));
			case STRING:
				return factory.newStringTag(dis.readUTF());
			case LIST:
				return readListTag(dis);
			case COMPOUND:
				return readCompoundTag(name, dis);
			case INT_ARRAY:
				return factory.newIntArrayTag(readIntArray(dis));
			default:
				throw new IllegalArgumentException("The specified TypeID is not supported: "
						+ type.ordinal());
		}
	}
	
	private ListTag<BaseTag<?>> readListTag(final DataInput dis) throws IOException {
		final NBTTagType listType = NBTTagType.getTypeFromId(dis.readByte());
		final int listLength = dis.readInt();
		final ListTag<BaseTag<?>> list = factory.newListTag();
		for (int i = 0; i < listLength; i++) {
			final BaseTag<?> tag = readTag(listType, dis);
			list.add(tag);
		}
		return list;
	}
	
	private CompoundTag readCompoundTag(final String name, final DataInput dis) throws IOException {
		final CompoundTag compound = factory.newCompoundTag(name);
		byte typeId = dis.readByte();
		while (typeId != COMPOUND_TAG_END) {
			final NBTTagType type = NBTTagType.getTypeFromId(typeId);
			final String childName = dis.readUTF();
			compound.put(childName, readTag(type, dis));
			typeId = dis.readByte();
		}
		return compound;
	}
	
	private byte[] readByteArray(final DataInput dis) throws IOException {
		final int length = dis.readInt();
		final byte[] data = new byte[length];
		dis.readFully(data);
		return data;
	}
	
	private int[] readIntArray(final DataInput dis) throws IOException {
		final int length = dis.readInt();
		final int[] data = new int[length];
		for (int i = 0; i < length; i++) {
			data[i] = dis.readInt();
		}
		return data;
	}
}

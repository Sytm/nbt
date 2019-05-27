package com.mojang.nbt;

/**
 * Copyright Mojang AB.
 * <p>
 * Don't do evil.
 */
import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class CompoundTag extends Tag {
	private Map<String, Tag> tags = new HashMap<>();

	public CompoundTag() {
		super("");
	}

	public CompoundTag(String name) {
		super(name);
	}

	void write(DataOutput dos) throws IOException {
		for (Tag tag : tags.values()) {
			Tag.writeNamedTag(tag, dos);
		}
		dos.writeByte(Tag.TAG_End);
	}

	void load(DataInput dis) throws IOException {
		tags.clear();
		Tag tag;
		while ((tag = Tag.readNamedTag(dis)).getId() != Tag.TAG_End) {
			tags.put(tag.getName(), tag);
		}
	}

	public Collection<Tag> getAllTags() {
		return tags.values();
	}

	public byte getId() {
		return TAG_Compound;
	}

	public void put(String name, Tag tag) {
		tags.put(name, tag.setName(name));
	}

	public ByteTag putByte(String name, byte value) {
		ByteTag byteTag = new ByteTag(name, value);
		tags.put(name, byteTag);
		return byteTag;
	}

	public ShortTag putShort(String name, short value) {
		ShortTag shortTag = new ShortTag(name, value);
		tags.put(name, shortTag);
		return shortTag;
	}

	public IntTag putInt(String name, int value) {
		IntTag intTag = new IntTag(name, value);
		tags.put(name, intTag);
		return intTag;
	}

	public LongTag putLong(String name, long value) {
		LongTag longTag = new LongTag(name, value);
		tags.put(name, longTag);
		return longTag;
	}

	public FloatTag putFloat(String name, float value) {
		FloatTag floatTag = new FloatTag(name, value);
		tags.put(name, floatTag);
		return floatTag;
	}

	public DoubleTag putDouble(String name, double value) {
		DoubleTag doubleTag = new DoubleTag(name, value);
		tags.put(name, doubleTag);
		return doubleTag;
	}

	public StringTag putString(String name, String value) {
		StringTag stringTag = new StringTag(name, value);
		tags.put(name, stringTag);
		return stringTag;
	}

	public ByteArrayTag putByteArray(String name, byte[] value) {
		ByteArrayTag byteArrayTag = new ByteArrayTag(name, value);
		tags.put(name, byteArrayTag);
		return byteArrayTag;
	}

	public IntArrayTag putIntArray(String name, int[] value) {
		IntArrayTag intArrayTag = new IntArrayTag(name, value);
		tags.put(name, intArrayTag);
		return intArrayTag;
	}

	public LongArrayTag putLongArray(String name, long[] value) {
		LongArrayTag longArrayTag = new LongArrayTag(name, value);
		tags.put(name, longArrayTag);
		return longArrayTag;
	}

	public ListTag putListTag(String name) {
		ListTag listTag = new ListTag(name);
		tags.put(name, listTag);
		return listTag;
	}

	public ListTag putListTag(String name, List<Tag> value) {
		ListTag listTag = new ListTag(name);
		value.forEach(listTag::add);
		tags.put(name, listTag);
		return listTag;
	}

	public CompoundTag putCompound(String name, CompoundTag value) {
		tags.put(name, value.setName(name));
		return value;
	}

	public CompoundTag putCompound(String name) {
		CompoundTag compoundTag = new CompoundTag(name);
		tags.put(name, compoundTag);
		return compoundTag;
	}

	public void putBoolean(String string, boolean val) {
		putByte(string, val ? (byte) 1 : 0);
	}

	public Tag get(String name) {
		return tags.get(name);
	}

	public boolean contains(String name) {
		return tags.containsKey(name);
	}

	public byte getByte(String name) {
		if (!tags.containsKey(name)) return (byte) 0;
		if (tags.get(name) instanceof ByteTag)
			return ((ByteTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "Byte");
	}

	public short getShort(String name) {
		if (!tags.containsKey(name)) return (short) 0;
		if (tags.get(name) instanceof ShortTag)
			return ((ShortTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "Short");
	}

	public int getInt(String name) {
		if (!tags.containsKey(name)) return 0;
		if (tags.get(name) instanceof IntTag)
			return ((IntTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "Int");
	}

	public long getLong(String name) {
		if (!tags.containsKey(name)) return (long) 0;
		if (tags.get(name) instanceof LongTag)
			return ((LongTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "Long");
	}

	public float getFloat(String name) {
		if (!tags.containsKey(name)) return (float) 0;
		if (tags.get(name) instanceof FloatTag)
			return ((FloatTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "Float");
	}

	public double getDouble(String name) {
		if (!tags.containsKey(name)) return (double) 0;
		if (tags.get(name) instanceof DoubleTag)
			return ((DoubleTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "Double");
	}

	public String getString(String name) {
		if (!tags.containsKey(name)) return "";
		if (tags.get(name) instanceof StringTag)
			return ((StringTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "String");
	}

	public byte[] getByteArray(String name) {
		if (!tags.containsKey(name)) return new byte[0];
		if (tags.get(name) instanceof ByteArrayTag)
			return ((ByteArrayTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "Byte_Array");
	}

	public int[] getIntArray(String name) {
		if (!tags.containsKey(name)) return new int[0];
		if (tags.get(name) instanceof IntArrayTag)
			return ((IntArrayTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "Int_Array");
	}

	public long[] getLongArray(String name) {
		if (!tags.containsKey(name)) return new long[0];
		if (tags.get(name) instanceof LongArrayTag)
			return ((LongArrayTag) tags.get(name)).data;
		else
			throw new NBTTagTypeMismatchException(name, "LongArray");
	}

	public CompoundTag getCompound(String name) {
		if (!tags.containsKey(name)) return new CompoundTag(name);
		if (tags.get(name) instanceof CompoundTag)
			return (CompoundTag) tags.get(name);
		else
			throw new NBTTagTypeMismatchException(name, "Compound");
	}

	public ListTag getList(String name) {
		if (!tags.containsKey(name)) return new ListTag(name);
		if (tags.get(name) instanceof ListTag)
			return (ListTag) tags.get(name);
		else
			throw new NBTTagTypeMismatchException(name, "List");
	}

	public boolean getBoolean(String string) {
		return getByte(string) != 0;
	}

	public String toString() {
		return "" + tags.size() + " entries";
	}

	public void print(String prefix, PrintStream out) {
		super.print(prefix, out);
		out.println(prefix + "{");
		String orgPrefix = prefix;
		prefix += "   ";
		for (Tag tag : tags.values()) {
			tag.print(prefix, out);
		}
		out.println(orgPrefix + "}");
	}

	public boolean isEmpty() {
		return tags.isEmpty();
	}

	public Tag copy() {
		CompoundTag tag = new CompoundTag(getName());
		for (String key : tags.keySet()) {
			tag.put(key, tags.get(key).copy());
		}
		return tag;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			CompoundTag o = (CompoundTag) obj;
			return tags.entrySet().equals(o.tags.entrySet());
		}
		return false;
	}
}

package de.md5lukas.nbt.tags;

/**
 * Copyright Mojang AB.
 * <p>
 * Don't do evil.
 */

import de.md5lukas.nbt.Tag;
import de.md5lukas.nbt.Tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

/**
 * This tag represents a long array
 */
public class LongArrayTag extends Tag {

	public long[] data;

	public LongArrayTag(String name) {
		super(name);
	}

	public LongArrayTag(String name, long[] data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(data.length);
		for (int i = 0; i < data.length; i++) {
			dos.writeLong(data[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		data = new long[length];
		for (int i = 0; i < length; i++) {
			data[i] = dis.readLong();
		}
	}

	@Override
	public byte getId() {
		return Tags.TAG_Long_Array;
	}

	@Override
	public String toString() {
		return "[" + data.length + " longs]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			LongArrayTag o = (LongArrayTag) obj;
			return ((data == null && o.data == null) || (data != null && Arrays.equals(data, o.data)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Long_Array";
	}

	@Override
	public Tag copy() {
		long[] cp = new long[data.length];
		System.arraycopy(data, 0, cp, 0, data.length);
		return new LongArrayTag(getName(), cp);
	}
}

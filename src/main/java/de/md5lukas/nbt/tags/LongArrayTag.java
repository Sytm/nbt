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

	private long[] value;

	public LongArrayTag(String name) {
		super(name);
	}

	public LongArrayTag(long[] value) {
		super(null);
		this.value = value;
	}

	public LongArrayTag(String name, long[] value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(value.length);
		for (int i = 0; i < value.length; i++) {
			dos.writeLong(value[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		value = new long[length];
		for (int i = 0; i < length; i++) {
			value[i] = dis.readLong();
		}
	}

	@Override
	public byte getId() {
		return Tags.TAG_Long_Array;
	}

	@Override
	public String toString() {
		return "[" + value.length + " longs]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			LongArrayTag o = (LongArrayTag) obj;
			return ((value == null && o.value == null) || (value != null && Arrays.equals(value, o.value)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Long_Array";
	}

	@Override
	public Tag copy() {
		long[] cp = new long[value.length];
		System.arraycopy(value, 0, cp, 0, value.length);
		return new LongArrayTag(getName(), cp);
	}

	public long[] value() {
		return value;
	}

	public void value(long[] value) {
		this.value = value;
	}
}

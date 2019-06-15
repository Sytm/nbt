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

/**
 * This tag represents a single long
 */
public class LongTag extends Tag {

	private long value;

	public LongTag(String name) {
		super(name);
	}

	public LongTag(long value) {
		super(null);
		this.value = value;
	}

	public LongTag(String name, long value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeLong(value);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		value = dis.readLong();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Long;
	}

	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public Tag copy() {
		return new LongTag(getName(), value);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			LongTag o = (LongTag) obj;
			return value == o.value;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Long";
	}

	public long value() {
		return value;
	}

	public void value(long value) {
		this.value = value;
	}
}

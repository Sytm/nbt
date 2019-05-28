package de.md5lukas.nbt.tags;

/**
 * Copyright Mojang AB.
 * 
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

	public long data;

	public LongTag(String name) {
		super(name);
	}

	public LongTag(String name, long data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeLong(data);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		data = dis.readLong();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Long;
	}

	@Override
	public String toString() {
		return "" + data;
	}

	@Override
	public Tag copy() {
		return new LongTag(getName(), data);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			LongTag o = (LongTag) obj;
			return data == o.data;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Long";
	}

}

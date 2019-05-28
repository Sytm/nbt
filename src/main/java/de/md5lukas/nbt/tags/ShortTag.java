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
 * This tag represents a single short
 */
public class ShortTag extends Tag {

	public short data;

	public ShortTag(String name) {
		super(name);
	}

	public ShortTag(String name, short data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeShort(data);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		data = dis.readShort();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Short;
	}

	@Override
	public String toString() {
		return "" + data;
	}

	@Override
	public Tag copy() {
		return new ShortTag(getName(), data);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ShortTag o = (ShortTag) obj;
			return data == o.data;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Short";
	}

}

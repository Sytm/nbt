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
 * This tag represents a single byte
 */
public class ByteTag extends Tag {

	public byte data;

	public ByteTag(String name) {
		super(name);
	}

	public ByteTag(String name, byte data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeByte(data);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		data = dis.readByte();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Byte;
	}

	@Override
	public String toString() {
		return "" + data;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ByteTag o = (ByteTag) obj;
			return data == o.data;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Byte";
	}

	@Override
	public Tag copy() {
		return new ByteTag(getName(), data);
	}
}

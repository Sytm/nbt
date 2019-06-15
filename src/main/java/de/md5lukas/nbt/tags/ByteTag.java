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
 * This tag represents a single byte
 */
public class ByteTag extends Tag {

	private byte value;

	public ByteTag(String name) {
		super(name);
	}

	public ByteTag(byte value) {
		super(null);
		this.value = value;
	}

	public ByteTag(String name, byte value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeByte(value);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		value = dis.readByte();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Byte;
	}

	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ByteTag o = (ByteTag) obj;
			return value == o.value;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Byte";
	}

	@Override
	public Tag copy() {
		return new ByteTag(getName(), value);
	}

	public byte value() {
		return value;
	}

	public void value(byte value) {
		this.value = value;
	}
}

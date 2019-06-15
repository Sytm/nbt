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
import java.util.Arrays;

/**
 * This tag represents a simple byte array
 */
public class ByteArrayTag extends Tag {

	private byte[] value;

	public ByteArrayTag(String name) {
		super(name);
	}

	public ByteArrayTag(byte[] value) {
		super(null);
		this.value = value;
	}

	public ByteArrayTag(String name, byte[] value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(value.length);
		dos.write(value);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		value = new byte[length];
		dis.readFully(value);
	}

	@Override
	public byte getId() {
		return Tags.TAG_Byte_Array;
	}

	@Override
	public String toString() {
		return "[" + value.length + " bytes]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ByteArrayTag o = (ByteArrayTag) obj;
			return ((value == null && o.value == null) || (value != null && Arrays.equals(value, o.value)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Byte_Array";
	}

	@Override
	public Tag copy() {
		byte[] cp = new byte[value.length];
		System.arraycopy(value, 0, cp, 0, value.length);
		return new ByteArrayTag(getName(), cp);
	}

	public byte[] value() {
		return value;
	}

	public void value(byte[] value) {
		this.value = value;
	}
}

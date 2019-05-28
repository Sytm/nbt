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
 * This tag represents a simple byte array
 */
public class ByteArrayTag extends Tag {

	public byte[] data;

	public ByteArrayTag(String name) {
		super(name);
	}

	public ByteArrayTag(String name, byte[] data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(data.length);
		dos.write(data);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		data = new byte[length];
		dis.readFully(data);
	}

	@Override
	public byte getId() {
		return Tags.TAG_Byte_Array;
	}

	@Override
	public String toString() {
		return "[" + data.length + " bytes]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ByteArrayTag o = (ByteArrayTag) obj;
			return ((data == null && o.data == null) || (data != null && data.equals(o.data)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Byte_Array";
	}

	@Override
	public Tag copy() {
		byte[] cp = new byte[data.length];
		System.arraycopy(data, 0, cp, 0, data.length);
		return new ByteArrayTag(getName(), cp);
	}
}

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
 * This tag represents a single string
 */
public class StringTag extends Tag {

	public String data;

	public StringTag(String name) {
		super(name);
	}

	public StringTag(String name, String data) {
		super(name);
		this.data = data;
		if (data == null) throw new IllegalArgumentException("Empty string not allowed");
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeUTF(data);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		data = dis.readUTF();
	}

	@Override
	public byte getId() {
		return Tags.TAG_String;
	}

	@Override
	public String toString() {
		return "" + data;
	}

	@Override
	public Tag copy() {
		return new StringTag(getName(), data);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			StringTag o = (StringTag) obj;
			return ((data == null && o.data == null) || (data != null && data.equals(o.data)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_String";
	}

}

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

	private String value;

	public StringTag(String name) {
		super(name);
		this.value = value;
	}

	public StringTag(String name, String value) {
		super(name);
		if (value == null) throw new IllegalArgumentException("Empty string not allowed");
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeUTF(value);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		value = dis.readUTF();
	}

	@Override
	public byte getId() {
		return Tags.TAG_String;
	}

	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public Tag copy() {
		return new StringTag(getName(), value);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			StringTag o = (StringTag) obj;
			return ((value == null && o.value == null) || (value != null && value.equals(o.value)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_String";
	}

	public String value() {
		return value;
	}

	public void value(String value) {
		this.value = value;
	}
}

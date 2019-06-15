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

	private short value;

	public ShortTag(String name) {
		super(name);
	}

	public ShortTag(short value) {
		super(null);
		this.value = value;
	}

	public ShortTag(String name, short value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeShort(value);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		value = dis.readShort();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Short;
	}

	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public Tag copy() {
		return new ShortTag(getName(), value);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ShortTag o = (ShortTag) obj;
			return value == o.value;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Short";
	}


	public short value() {
		return value;
	}

	public void value(short value) {
		this.value = value;
	}
}

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
 * This tag represents a single double
 */
public class DoubleTag extends Tag {

	public double data;

	public DoubleTag(String name) {
		super(name);
	}

	public DoubleTag(String name, double data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeDouble(data);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		data = dis.readDouble();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Double;
	}

	@Override
	public String toString() {
		return "" + data;
	}

	@Override
	public Tag copy() {
		return new DoubleTag(getName(), data);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			DoubleTag o = (DoubleTag) obj;
			return data == o.data;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Double";
	}

}

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
 * This tag represents a single float
 */
public class FloatTag extends Tag {

	public float data;

	public FloatTag(String name) {
		super(name);
	}

	public FloatTag(String name, float data) {
		super(name);
		this.data = data;
	}

	public void write(DataOutput dos) throws IOException {
		dos.writeFloat(data);
	}

	public void load(DataInput dis) throws IOException {
		data = dis.readFloat();
	}

	public byte getId() {
		return Tags.TAG_Float;
	}

	public String toString() {
		return "" + data;
	}

	@Override
	public Tag copy() {
		return new FloatTag(getName(), data);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			FloatTag o = (FloatTag) obj;
			return data == o.data;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Float";
	}

}

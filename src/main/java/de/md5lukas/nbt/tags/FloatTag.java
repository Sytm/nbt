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
 * This tag represents a single float
 */
public class FloatTag extends Tag {

	private float value;

	public FloatTag(String name) {
		super(name);
	}

	public FloatTag(float value) {
		super(null);
		this.value = value;
	}

	public FloatTag(String name, float value) {
		super(name);
		this.value = value;
	}

	public void write(DataOutput dos) throws IOException {
		dos.writeFloat(value);
	}

	public void load(DataInput dis) throws IOException {
		value = dis.readFloat();
	}

	public byte getId() {
		return Tags.TAG_Float;
	}

	public String toString() {
		return "" + value;
	}

	@Override
	public Tag copy() {
		return new FloatTag(getName(), value);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			FloatTag o = (FloatTag) obj;
			return value == o.value;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Float";
	}

	public float value() {
		return value;
	}

	public void value(float value) {
		this.value = value;
	}
}

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

	private double value;

	public DoubleTag(String name) {
		super(name);
	}

	public DoubleTag(double value) {
		super(null);
		this.value = value;
	}

	public DoubleTag(String name, double value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeDouble(value);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		value = dis.readDouble();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Double;
	}

	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public Tag copy() {
		return new DoubleTag(getName(), value);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			DoubleTag o = (DoubleTag) obj;
			return value == o.value;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Double";
	}

	public double value() {
		return value;
	}

	public void value(double value) {
		this.value = value;
	}
}

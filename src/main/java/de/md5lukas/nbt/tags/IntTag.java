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
 * This tag represents a single int
 */
public class IntTag extends Tag {

	private int value;

	public IntTag(String name) {
		super(name);
	}

	public IntTag(int value) {
		super(null);
		this.value = value;
	}

	public IntTag(String name, int value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(value);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		value = dis.readInt();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Int;
	}

	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public Tag copy() {
		return new IntTag(getName(), value);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			IntTag o = (IntTag) obj;
			return value == o.value;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Int";
	}

	public int value() {
		return value;
	}

	public void value(int value) {
		this.value = value;
	}
}

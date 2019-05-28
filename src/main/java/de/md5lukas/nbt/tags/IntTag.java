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

	public int data;

	public IntTag(String name) {
		super(name);
	}

	public IntTag(String name, int data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(data);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		data = dis.readInt();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Int;
	}

	@Override
	public String toString() {
		return "" + data;
	}

	@Override
	public Tag copy() {
		return new IntTag(getName(), data);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			IntTag o = (IntTag) obj;
			return data == o.data;
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Int";
	}

}

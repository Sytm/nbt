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
import java.util.Arrays;

/**
 * This tag represents an int array
 */
public class IntArrayTag extends Tag {

	private int[] value;

	public IntArrayTag(String name) {
		super(name);
	}

	public IntArrayTag(int[] value) {
		super(null);
		this.value = value;
	}

	public IntArrayTag(String name, int[] value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(value.length);
		for (int i = 0; i < value.length; i++) {
			dos.writeInt(value[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		value = new int[length];
		for (int i = 0; i < length; i++) {
			value[i] = dis.readInt();
		}
	}

	@Override
	public byte getId() {
		return Tags.TAG_Int_Array;
	}

	@Override
	public String toString() {
		return "[" + value.length + " ints]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			IntArrayTag o = (IntArrayTag) obj;
			return ((value == null && o.value == null) || (value != null && Arrays.equals(value, o.value)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Int_Array";
	}

	@Override
	public Tag copy() {
		int[] cp = new int[value.length];
		System.arraycopy(value, 0, cp, 0, value.length);
		return new IntArrayTag(getName(), cp);
	}

	public int[] value() {
		return value;
	}

	public void value(int[] value) {
		this.value = value;
	}
}

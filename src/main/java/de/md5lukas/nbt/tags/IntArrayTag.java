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
import java.util.Arrays;

/**
 * This tag represents an int array
 */
public class IntArrayTag extends Tag {

	public int[] data;

	public IntArrayTag(String name) {
		super(name);
	}

	public IntArrayTag(String name, int[] data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(data.length);
		for (int i = 0; i < data.length; i++) {
			dos.writeInt(data[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		data = new int[length];
		for (int i = 0; i < length; i++) {
			data[i] = dis.readInt();
		}
	}

	@Override
	public byte getId() {
		return Tags.TAG_Int_Array;
	}

	@Override
	public String toString() {
		return "[" + data.length + " ints]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			IntArrayTag o = (IntArrayTag) obj;
			return ((data == null && o.data == null) || (data != null && Arrays.equals(data, o.data)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Int_Array";
	}

	@Override
	public Tag copy() {
		int[] cp = new int[data.length];
		System.arraycopy(data, 0, cp, 0, data.length);
		return new IntArrayTag(getName(), cp);
	}
}

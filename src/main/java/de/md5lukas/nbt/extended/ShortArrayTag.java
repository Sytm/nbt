package de.md5lukas.nbt.extended;

import de.md5lukas.nbt.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

/**
 * This tag represents a simple short array
 */
public class ShortArrayTag extends Tag {

	public short[] data;

	public ShortArrayTag(String name) {
		super(name);
	}

	public ShortArrayTag(String name, short[] data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(data.length);
		for (int i = 0; i < data.length; i++) {
			dos.writeShort(data[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		data = new short[length];
		for (int i = 0; i < length; i++) {
			data[i] = dis.readShort();
		}
	}

	@Override
	public byte getId() {
		return 18;
	}

	@Override
	public String toString() {
		return "[" + data.length + " shorts]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ShortArrayTag o = (ShortArrayTag) obj;
			return ((data == null && o.data == null) || (data != null && Arrays.equals(data, o.data)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Short_Array";
	}

	@Override
	public Tag copy() {
		short[] cp = new short[data.length];
		System.arraycopy(data, 0, cp, 0, data.length);
		return new ShortArrayTag(getName(), cp);
	}
}

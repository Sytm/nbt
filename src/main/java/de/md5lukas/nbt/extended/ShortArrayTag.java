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

	private short[] value;

	public ShortArrayTag(String name) {
		super(name);
	}

	public ShortArrayTag(String name, short[] value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(value.length);
		for (int i = 0; i < value.length; i++) {
			dos.writeShort(value[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		value = new short[length];
		for (int i = 0; i < length; i++) {
			value[i] = dis.readShort();
		}
	}

	@Override
	public byte getId() {
		return 18;
	}

	@Override
	public String toString() {
		return "[" + value.length + " shorts]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ShortArrayTag o = (ShortArrayTag) obj;
			return ((value == null && o.value == null) || (value != null && Arrays.equals(value, o.value)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Short_Array";
	}

	@Override
	public Tag copy() {
		short[] cp = new short[value.length];
		System.arraycopy(value, 0, cp, 0, value.length);
		return new ShortArrayTag(getName(), cp);
	}

	public short[] value() {
		return value;
	}

	public void value(short[] value) {
		this.value = value;
	}
}

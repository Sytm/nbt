package de.md5lukas.nbt.extended;

import de.md5lukas.nbt.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

/**
 * This tag represents a simple float array
 */
public class FloatArrayTag extends Tag {

	private float[] value;

	public FloatArrayTag(String name) {
		super(name);
	}

	public FloatArrayTag(String name, float[] value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(value.length);
		for (int i = 0; i < value.length; i++) {
			dos.writeFloat(value[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		value = new float[length];
		for (int i = 0; i < length; i++) {
			value[i] = dis.readFloat();
		}
	}

	@Override
	public byte getId() {
		return 19;
	}

	@Override
	public String toString() {
		return "[" + value.length + " floats]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			FloatArrayTag o = (FloatArrayTag) obj;
			return ((value == null && o.value == null) || (value != null && Arrays.equals(value, o.value)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Float_Array";
	}

	@Override
	public Tag copy() {
		float[] cp = new float[value.length];
		System.arraycopy(value, 0, cp, 0, value.length);
		return new FloatArrayTag(getName(), cp);
	}

	public float[] value() {
		return value;
	}

	public void value(float[] value) {
		this.value = value;
	}
}

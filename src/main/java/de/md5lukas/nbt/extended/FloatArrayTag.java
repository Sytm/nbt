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

	public float[] data;

	public FloatArrayTag(String name) {
		super(name);
	}

	public FloatArrayTag(String name, float[] data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(data.length);
		for (int i = 0; i < data.length; i++) {
			dos.writeFloat(data[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		data = new float[length];
		for (int i = 0; i < length; i++) {
			data[i] = dis.readFloat();
		}
	}

	@Override
	public byte getId() {
		return 19;
	}

	@Override
	public String toString() {
		return "[" + data.length + " floats]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			FloatArrayTag o = (FloatArrayTag) obj;
			return ((data == null && o.data == null) || (data != null && Arrays.equals(data, o.data)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Float_Array";
	}

	@Override
	public Tag copy() {
		float[] cp = new float[data.length];
		System.arraycopy(data, 0, cp, 0, data.length);
		return new FloatArrayTag(getName(), cp);
	}
}

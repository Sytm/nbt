package de.md5lukas.nbt.extended;

import de.md5lukas.nbt.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

/**
 * This tag represents a simple double array
 */
public class DoubleArrayTag extends Tag {

	public double[] data;

	public DoubleArrayTag(String name) {
		super(name);
	}

	public DoubleArrayTag(String name, double[] data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(data.length);
		for (int i = 0; i < data.length; i++) {
			dos.writeDouble(data[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		data = new double[length];
		for (int i = 0; i < length; i++) {
			data[i] = dis.readDouble();
		}
	}

	@Override
	public byte getId() {
		return 20;
	}

	@Override
	public String toString() {
		return "[" + data.length + " doubles]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			DoubleArrayTag o = (DoubleArrayTag) obj;
			return ((data == null && o.data == null) || (data != null && Arrays.equals(data, o.data)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Double_Array";
	}

	@Override
	public Tag copy() {
		double[] cp = new double[data.length];
		System.arraycopy(data, 0, cp, 0, data.length);
		return new DoubleArrayTag(getName(), cp);
	}
}

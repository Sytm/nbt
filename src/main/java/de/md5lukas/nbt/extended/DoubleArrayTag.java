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

	private double[] value;

	public DoubleArrayTag(String name) {
		super(name);
	}

	public DoubleArrayTag(String name, double[] value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(value.length);
		for (int i = 0; i < value.length; i++) {
			dos.writeDouble(value[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		value = new double[length];
		for (int i = 0; i < length; i++) {
			value[i] = dis.readDouble();
		}
	}

	@Override
	public byte getId() {
		return 20;
	}

	@Override
	public String toString() {
		return "[" + value.length + " doubles]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			DoubleArrayTag o = (DoubleArrayTag) obj;
			return ((value == null && o.value == null) || (value != null && Arrays.equals(value, o.value)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Double_Array";
	}

	@Override
	public Tag copy() {
		double[] cp = new double[value.length];
		System.arraycopy(value, 0, cp, 0, value.length);
		return new DoubleArrayTag(getName(), cp);
	}

	public double[] value() {
		return value;
	}

	public void value(double[] value) {
		this.value = value;
	}
}

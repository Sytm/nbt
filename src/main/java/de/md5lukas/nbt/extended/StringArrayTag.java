package de.md5lukas.nbt.extended;

import de.md5lukas.nbt.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

/**
 * This tag represents a simple string array, with an maximum string length of {@link Integer#MAX_VALUE}
 */
public class StringArrayTag extends Tag {

	private String[] value;

	public StringArrayTag(String name) {
		super(name);
	}

	public StringArrayTag(String name, String[] value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(value.length);
		for (int i = 0; i < value.length; i++) {
			ExtendedStringTag.writeString(dos, value[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		value = new String[length];
		for (int i = 0; i < length; i++) {
			value[i] = ExtendedStringTag.readString(dis);
		}
	}

	@Override
	public byte getId() {
		return 17;
	}

	@Override
	public String toString() {
		return "[" + value.length + " strings]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			StringArrayTag o = (StringArrayTag) obj;
			return ((value == null && o.value == null) || (value != null && Arrays.equals(value, o.value)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Sring_Array";
	}

	@Override
	public Tag copy() {
		String[] cp = new String[value.length];
		System.arraycopy(value, 0, cp, 0, value.length);
		return new StringArrayTag(getName(), cp);
	}

	public String[] value() {
		return value;
	}

	public void value(String[] value) {
		this.value = value;
	}
}

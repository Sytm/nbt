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

	public String[] data;

	public StringArrayTag(String name) {
		super(name);
	}

	public StringArrayTag(String name, String[] data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeInt(data.length);
		for (int i = 0; i < data.length; i++) {
			ExtendedStringTag.writeString(dos, data[i]);
		}
	}

	@Override
	public void load(DataInput dis) throws IOException {
		int length = dis.readInt();
		data = new String[length];
		for (int i = 0; i < length; i++) {
			data[i] = ExtendedStringTag.readString(dis);
		}
	}

	@Override
	public byte getId() {
		return 17;
	}

	@Override
	public String toString() {
		return "[" + data.length + " strings]";
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			StringArrayTag o = (StringArrayTag) obj;
			return ((data == null && o.data == null) || (data != null && Arrays.equals(data, o.data)));
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_Sring_Array";
	}

	@Override
	public Tag copy() {
		String[] cp = new String[data.length];
		System.arraycopy(data, 0, cp, 0, data.length);
		return new StringArrayTag(getName(), cp);
	}
}

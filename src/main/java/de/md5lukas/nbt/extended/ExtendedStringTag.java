package de.md5lukas.nbt.extended;

import de.md5lukas.nbt.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * This tag represents a string which length isn't limited by {@link DataOutput#writeUTF(String)} max length of
 * {@link Short#MAX_VALUE}, but can be up to {@link Integer#MAX_VALUE} bytes long
 */
public class ExtendedStringTag extends Tag {

	public String data;

	public ExtendedStringTag(String name) {
		super(name);
	}

	public ExtendedStringTag(String name, String value) {
		super(name);
		data = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		writeString(dos, data);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		data = readString(dis);
	}

	@Override
	public String toString() {
		return data;
	}

	@Override
	public byte getId() {
		return 16;
	}

	@Override
	public String getTagName() {
		return "TAG_Extended_String";
	}

	@Override
	public Tag copy() {
		return new ExtendedStringTag(getName(), data);
	}

	public static void writeString(DataOutput dos, String string) throws IOException {
		byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
		dos.writeInt(bytes.length);
		dos.write(bytes);
	}

	public static String readString(DataInput dis) throws IOException {
		int length = dis.readInt();
		byte[] bytes = new byte[length];
		dis.readFully(bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}
}

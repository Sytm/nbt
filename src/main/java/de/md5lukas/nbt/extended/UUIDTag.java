package de.md5lukas.nbt.extended;

import de.md5lukas.nbt.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.UUID;

public class UUIDTag extends Tag {

	public UUID data;

	public UUIDTag(String name) {
		super(name);
	}

	public UUIDTag(String name, UUID data) {
		super(name);
		this.data = data;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeLong(data.getMostSignificantBits());
		dos.writeLong(data.getLeastSignificantBits());
	}

	@Override
	public void load(DataInput dis) throws IOException {
		data = new UUID(dis.readLong(), dis.readLong());
	}

	@Override
	public String toString() {
		return data.toString();
	}

	@Override
	public byte getId() {
		return 21;
	}

	@Override
	public String getTagName() {
		return "TAG_UUID";
	}

	@Override
	public Tag copy() {
		return new UUIDTag(getName(), data);
	}
}

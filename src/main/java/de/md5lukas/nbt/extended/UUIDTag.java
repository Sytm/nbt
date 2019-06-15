package de.md5lukas.nbt.extended;

import de.md5lukas.nbt.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.UUID;

public class UUIDTag extends Tag {

	public UUID value;

	public UUIDTag(String name) {
		super(name);
	}

	public UUIDTag(String name, UUID value) {
		super(name);
		this.value = value;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeLong(value.getMostSignificantBits());
		dos.writeLong(value.getLeastSignificantBits());
	}

	@Override
	public void load(DataInput dis) throws IOException {
		value = new UUID(dis.readLong(), dis.readLong());
	}

	@Override
	public String toString() {
		return value.toString();
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
		return new UUIDTag(getName(), value);
	}

	public UUID value() {
		return value;
	}

	public void value(UUID value) {
		this.value = value;
	}
}

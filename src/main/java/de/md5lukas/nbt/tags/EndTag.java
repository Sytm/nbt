package de.md5lukas.nbt.tags;

/**
 * Copyright Mojang AB.
 * 
 * Don't do evil.
 */

import de.md5lukas.nbt.Tag;
import de.md5lukas.nbt.Tags;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * This tag represents the end of a {@link CompoundTag}
 */
public class EndTag extends Tag {

	public EndTag() {
		super(null);
	}

	@Override
	public void load(DataInput dis) throws IOException {
	}

	@Override
	public void write(DataOutput dos) throws IOException {
	}

	@Override
	public byte getId() {
		return Tags.TAG_End;
	}

	@Override
	public String toString() {
		return "END";
	}

	@Override
	public Tag copy() {
		return new EndTag();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String getTagName() {
		return "TAG_End";
	}

}

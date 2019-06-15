package de.md5lukas.nbt;

/**
 * Copyright Mojang AB.
 * <p>
 * Don't do evil.
 */

import de.md5lukas.nbt.exceptions.InvalidTagException;
import de.md5lukas.nbt.tags.EndTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;

/**
 * Base class defining basic methods required by every tag
 */
public abstract class Tag {

	private String name;

	public Tag(String name) {
		setName(name);
	}

	/**
	 * Writes the data of the tag into the {@link DataOutput}.<br>
	 * Implementations may only write their data, not their name, because this is handled externally by {@link Tag#writeNamedTag(Tag, DataOutput)}
	 *
	 * @param dos The {@link DataOutput} to write to
	 * @throws IOException If an I/O exception occurred while writing
	 */
	public abstract void write(DataOutput dos) throws IOException;

	/**
	 * Reads the data of {@link DataInput} and then sets the tag's data with the read data.
	 *
	 * @param dis The DataInput to read from
	 * @throws IOException If an I/O exception occurred while reading
	 */
	public abstract void load(DataInput dis) throws IOException;

	/**
	 * @return A short string representation of the tag, does not have to contain every piece of data contained by the tag
	 */
	@Override
	public abstract String toString();

	/**
	 * Every tag has his own custom id ranging from {@link Byte#MIN_VALUE -128} to {@link Byte#MAX_VALUE 127} to be stored alongside
	 * the tags data to know which tag has been used to store the data.<br><br>
	 * Ids from <code>0</code> to <code>12</code> are reserved by the original nbt tags.
	 *
	 * @return The id of the Tag
	 */
	public abstract byte getId();

	/**
	 * This is used when {@link Tag#print(PrintStream)} is used to show the contents of the tag.<br><br>
	 * It should be in the format TAG_<i>CustomName</i>Tag.
	 * The cursive text can be replaced by your own.
	 *
	 * @return The tag's name
	 */
	public abstract String getTagName();

	/**
	 * Checks if the object is equals to this tag
	 *
	 * @param obj The object to check against
	 * @return <code>true</code> if the object is a tag of the same type, has the same name and values
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tag)) {
			return false;
		}
		Tag o = (Tag) obj;
		if (getId() != o.getId()) {
			return false;
		}
		return Objects.equals(name, o.name);
	}

	/**
	 * This method prints the whole tree of tags to the provided {@link PrintStream}
	 *
	 * @param out The PrintStream to output the text to
	 * @see #print(String, PrintStream) Print with prefix
	 */
	public void print(PrintStream out) {
		print("", out);
	}

	/**
	 * This method prints the whole tree of tags to the provided {@link PrintStream} with a set prefix in front every line
	 *
	 * @param out    The PrintStream to output the text to
	 * @param prefix The prefix to use
	 */
	public void print(String prefix, PrintStream out) {
		String name = getName();

		out.print(prefix);
		out.print(getTagName());
		if (name.length() > 0) {
			out.print("(\"" + name + "\")");
		}
		out.print(": ");
		out.println(toString());
	}

	/**
	 * Sets a new name for this tag
	 *
	 * @param name The name
	 * @return this {@link Tag}
	 */
	public Tag setName(String name) {
		if (name == null) {
			this.name = "";
		} else {
			this.name = name;
		}
		return this;
	}

	/**
	 * Retrieves the name of the tag
	 *
	 * @return The name
	 */
	public String getName() {
		if (name == null) return "";
		return name;
	}

	/**
	 * Reads a named tag from the {@link DataInput}
	 *
	 * @param dis The {@link DataInput} to read from
	 * @return The tag
	 * @throws IOException         If an I/O Exception occurred
	 * @throws InvalidTagException If the stream contains an unregistered Tag
	 */
	public static Tag readNamedTag(DataInput dis) throws IOException {
		byte type = dis.readByte();
		if (type == 0) return new EndTag();

		String name = dis.readUTF();

		Tag tag = Tags.newTag(type, name);

		tag.load(dis);
		return tag;
	}

	/**
	 * Writes a named tag to the {@link DataOutput}
	 *
	 * @param tag The tag to be written
	 * @param dos The {@link DataOutput} to write to
	 * @throws IOException         If an I/O Exception occurred
	 * @throws InvalidTagException If the tag contains an unregistered Tag
	 */
	public static void writeNamedTag(Tag tag, DataOutput dos) throws IOException {
		if (!Tags.isRegistered(tag))
			throw new InvalidTagException("A unregistered tag with the type id " + tag.getId() + " has been attempted to save!");
		dos.writeByte(tag.getId());
		if (tag.getId() == Tags.TAG_End) return;

		dos.writeUTF(tag.getName());

		tag.write(dos);
	}

	/**
	 * This method copies this tag. The created copy is not connected to the original tag in any way. Each value may be changed without any effects on the other one
	 *
	 * @return A copy of this tag
	 */
	public abstract Tag copy();
}

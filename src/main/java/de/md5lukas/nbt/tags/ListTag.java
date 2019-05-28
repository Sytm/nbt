package de.md5lukas.nbt.tags;

/**
 * Copyright Mojang AB.
 * <p>
 * Don't do evil.
 */

import de.md5lukas.nbt.Tag;
import de.md5lukas.nbt.Tags;
import de.md5lukas.nbt.exceptions.NBTTagTypeMismatchException;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTag extends Tag {

	private List<Tag> list = new ArrayList<>();
	private byte type;

	public ListTag() {
		super("");
	}

	public ListTag(String name) {
		super(name);
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		if (list.size() > 0)
			type = list.get(0).getId();
		else
			type = 1;

		dos.writeByte(type);
		dos.writeInt(list.size());
		for (int i = 0; i < list.size(); i++)
			list.get(i).write(dos);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		type = dis.readByte();
		int size = dis.readInt();

		list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Tag tag = Tags.newTag(type, null);
			tag.load(dis);
			list.add(tag);
		}
	}

	@Override
	public byte getId() {
		return Tags.TAG_List;
	}

	@Override
	public String toString() {
		return "" + list.size() + " entries of type " + Tags.getTagName(type);
	}

	@Override
	public void print(String prefix, PrintStream out) {
		super.print(prefix, out);

		out.println(prefix + "{");
		String orgPrefix = prefix;
		prefix += "   ";
		for (int i = 0; i < list.size(); i++)
			list.get(i).print(prefix, out);
		out.println(orgPrefix + "}");
	}

	/**
	 * Appends a new tag to the end of the list
	 *
	 * @param tag The tag to add
	 * @throws NBTTagTypeMismatchException if the tag's type doesn't match with the existing ones
	 */
	public void add(Tag tag) {
		if (list.isEmpty())
			type = tag.getId();
		else if (type != tag.getId())
			throw new NBTTagTypeMismatchException("The ListTag is filled with " + Tags.getTagName(type) + ", but it was attempted to add an " + Tags.getTagName(tag.getId()));
		list.add(tag);
	}

	/**
	 * Retrieve the tag at index N of the list
	 *
	 * @param index The index in the list
	 * @return The tag at index X of the list
	 * @see ArrayList#get(int) This method is used to retrieve the tag
	 */
	public Tag get(int index) {
		return list.get(index);
	}

	/**
	 * @return The size of the list
	 */
	public int size() {
		return list.size();
	}

	/**
	 * The list returned contains every value of the tag, but is not modifiable
	 *
	 * @return The contents of this list
	 * @see Collections#unmodifiableList(List) This is used to lock the list
	 */
	public List<Tag> values() {
		return Collections.unmodifiableList(list);
	}

	/**
	 * Sets the new list. After this method is called, you cannot modify the backing list of this tag with the list passed
	 * as an argument
	 *
	 * @param newList The new list to be set
	 * @throws NBTTagTypeMismatchException If the list contains different types of tags
	 */
	public void setList(List<Tag> newList) {
		if (newList == null)
			throw new IllegalArgumentException("The new list for the list tag cannot be null");
		if (newList.size() > 0) {
			byte type = newList.get(0).getId();
			for (int i = 1; i < newList.size(); ++i)
				if (newList.get(i).getId() != type)
					throw new NBTTagTypeMismatchException("The new list contains a " + newList.get(i).getTagName() + " at index " + i + ", but the list should only contain " + newList.get(0).getTagName());
		}
		list.clear();
		list.addAll(newList);
	}

	@Override
	public Tag copy() {
		ListTag res = new ListTag(getName());
		res.type = type;
		for (Tag t : list) {
			Tag copy = t.copy();
			res.list.add(copy);
		}
		return res;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ListTag o = (ListTag) obj;
			if (type == o.type) {
				return list.equals(o.list);
			}
		}
		return false;
	}

	@Override
	public String getTagName() {
		return "TAG_List";
	}
}

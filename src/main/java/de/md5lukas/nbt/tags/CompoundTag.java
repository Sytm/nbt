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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This tag represents a object which can contain multiple named tags of different types
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class CompoundTag extends Tag {

	private Map<String, Tag> tags = new HashMap<>();

	public CompoundTag() {
		super(null);
	}

	public CompoundTag(String name) {
		super(name);
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		for (Tag tag : tags.values()) {
			Tag.writeNamedTag(tag, dos);
		}
		dos.writeByte(Tags.TAG_End);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		tags.clear();
		Tag tag;
		while ((tag = Tag.readNamedTag(dis)).getId() != Tags.TAG_End) {
			tags.put(tag.getName(), tag);
		}
	}

	/**
	 * Get all values contained by this tag
	 *
	 * @return The values
	 * @see HashMap#values() Is used to retrieve this collection
	 */
	public Collection<Tag> getAllTags() {
		return tags.values();
	}

	@Override
	public byte getId() {
		return Tags.TAG_Compound;
	}

	/**
	 * Adds a tag to this compound tag
	 *
	 * @param name The name of the tag
	 * @param tag  The tag to be added
	 */
	public void put(String name, Tag tag) {
		if (tag == null)
			tags.remove(name);
		else
			tags.put(name, tag.setName(name));
	}

	/**
	 * Adds a byte tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public ByteTag putByte(String name, byte value) {
		ByteTag byteTag = new ByteTag(name, value);
		tags.put(name, byteTag);
		return byteTag;
	}

	/**
	 * Adds a short tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public ShortTag putShort(String name, short value) {
		ShortTag shortTag = new ShortTag(name, value);
		tags.put(name, shortTag);
		return shortTag;
	}

	/**
	 * Adds a int tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public IntTag putInt(String name, int value) {
		IntTag intTag = new IntTag(name, value);
		tags.put(name, intTag);
		return intTag;
	}

	/**
	 * Adds a long tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public LongTag putLong(String name, long value) {
		LongTag longTag = new LongTag(name, value);
		tags.put(name, longTag);
		return longTag;
	}

	/**
	 * Adds a float tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public FloatTag putFloat(String name, float value) {
		FloatTag floatTag = new FloatTag(name, value);
		tags.put(name, floatTag);
		return floatTag;
	}

	/**
	 * Adds a double tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public DoubleTag putDouble(String name, double value) {
		DoubleTag doubleTag = new DoubleTag(name, value);
		tags.put(name, doubleTag);
		return doubleTag;
	}

	/**
	 * Adds a string tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public StringTag putString(String name, String value) {
		StringTag stringTag = new StringTag(name, value);
		tags.put(name, stringTag);
		return stringTag;
	}

	/**
	 * Adds a byte array tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public ByteArrayTag putByteArray(String name, byte[] value) {
		ByteArrayTag byteArrayTag = new ByteArrayTag(name, value);
		tags.put(name, byteArrayTag);
		return byteArrayTag;
	}

	/**
	 * Adds a int array tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public IntArrayTag putIntArray(String name, int[] value) {
		IntArrayTag intArrayTag = new IntArrayTag(name, value);
		tags.put(name, intArrayTag);
		return intArrayTag;
	}

	/**
	 * Adds a long array tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public LongArrayTag putLongArray(String name, long[] value) {
		LongArrayTag longArrayTag = new LongArrayTag(name, value);
		tags.put(name, longArrayTag);
		return longArrayTag;
	}

	/**
	 * Adds a list tag to this compound tag
	 *
	 * @param name The name of the tag
	 * @return The added tag
	 */
	public ListTag putListTag(String name) {
		ListTag listTag = new ListTag(name);
		tags.put(name, listTag);
		return listTag;
	}

	/**
	 * Adds a # tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The values to be added to the tag
	 * @return The added tag
	 */
	public ListTag putListTag(String name, List<Tag> value) {
		ListTag listTag = new ListTag(name);
		value.forEach(listTag::add);
		tags.put(name, listTag);
		return listTag;
	}

	/**
	 * Adds a compound tag to this compound tag
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The added tag
	 */
	public CompoundTag putCompound(String name, CompoundTag value) {
		tags.put(name, value.setName(name));
		return value;
	}

	/**
	 * Create and adds a compound tag to this compound tag
	 *
	 * @param name The name of the tag
	 * @return The added tag
	 */
	public CompoundTag putCompound(String name) {
		CompoundTag compoundTag = new CompoundTag(name);
		tags.put(name, compoundTag);
		return compoundTag;
	}

	/**
	 * Creates and adds a byte tag to represent the boolean.<br>
	 * <code>1</code> represents <code>true</code>, <code>0</code> represents <code>false</code>
	 *
	 * @param name  The name of the tag
	 * @param value The value of the tag
	 * @return The byte tag created to represent the boolean
	 */
	public ByteTag putBoolean(String name, boolean value) {
		return putByte(name, value ? (byte) 1 : 0);
	}

	/**
	 * Retrieves the tag stored under the provided name
	 *
	 * @param name The name of the tag
	 * @return The tag stored under that name or if not present <code>null</code>
	 * @see HashMap#get(Object) The method used to retrieve the tag
	 */
	public Tag get(String name) {
		return tags.get(name);
	}

	/**
	 * Checks if this compound tag contains a tag with the provided name
	 *
	 * @param name The name to check for
	 * @return <code>true</code> if a tag is present with that name
	 * @see HashMap#containsKey(Object) Is used to check if this compound tag contains a tag
	 */
	public boolean contains(String name) {
		return tags.containsKey(name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored <code>0</code> is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or <code>0</code> if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a byte tag
	 */
	public byte getByte(String name) {
		if (!tags.containsKey(name)) return (byte) 0;
		if (tags.get(name) instanceof ByteTag)
			return ((ByteTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Byte), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored <code>0</code> is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or <code>0</code> if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a short tag
	 */
	public short getShort(String name) {
		if (!tags.containsKey(name)) return (short) 0;
		if (tags.get(name) instanceof ShortTag)
			return ((ShortTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Short), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored <code>0</code> is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or <code>0</code> if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a int tag
	 */
	public int getInt(String name) {
		if (!tags.containsKey(name)) return 0;
		if (tags.get(name) instanceof IntTag)
			return ((IntTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Int), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored <code>0</code> is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or <code>0</code> if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a long tag
	 */
	public long getLong(String name) {
		if (!tags.containsKey(name)) return (long) 0;
		if (tags.get(name) instanceof LongTag)
			return ((LongTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Long), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored <code>0</code> is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or <code>0</code> if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a float tag
	 */
	public float getFloat(String name) {
		if (!tags.containsKey(name)) return (float) 0;
		if (tags.get(name) instanceof FloatTag)
			return ((FloatTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Float), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored <code>0</code> is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or <code>0</code> if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a double tag
	 */
	public double getDouble(String name) {
		if (!tags.containsKey(name)) return (double) 0;
		if (tags.get(name) instanceof DoubleTag)
			return ((DoubleTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Double), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored an empty string is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or an empty string if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a string tag
	 */
	public String getString(String name) {
		if (!tags.containsKey(name)) return "";
		if (tags.get(name) instanceof StringTag)
			return ((StringTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_String), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored an empty array is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or an empty array if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a byte array tag
	 */
	public byte[] getByteArray(String name) {
		if (!tags.containsKey(name)) return new byte[0];
		if (tags.get(name) instanceof ByteArrayTag)
			return ((ByteArrayTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Byte_Array), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored an empty array is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or an empty array if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a int array tag
	 */
	public int[] getIntArray(String name) {
		if (!tags.containsKey(name)) return new int[0];
		if (tags.get(name) instanceof IntArrayTag)
			return ((IntArrayTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Int_Array), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored an empty array is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or an empty array if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a long array tag
	 */
	public long[] getLongArray(String name) {
		if (!tags.containsKey(name)) return new long[0];
		if (tags.get(name) instanceof LongArrayTag)
			return ((LongArrayTag) tags.get(name)).value();
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Long_Array), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored an empty compound tag is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or an empty compound tag if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a compound tag
	 */
	public CompoundTag getCompound(String name) {
		if (!tags.containsKey(name)) return new CompoundTag(name);
		if (tags.get(name) instanceof CompoundTag)
			return (CompoundTag) tags.get(name);
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_Compound), tags.get(name).getTagName(), name);
	}

	/**
	 * Retrieves the value stored under the provided name. If there is no value stored an empty list tag is returned
	 *
	 * @param name The name of the tag
	 * @return The value stored under the name or an empty list tag if not present
	 * @throws NBTTagTypeMismatchException If the tag present is not a list tag
	 */
	public ListTag getList(String name) {
		if (!tags.containsKey(name)) return new ListTag(name);
		if (tags.get(name) instanceof ListTag)
			return (ListTag) tags.get(name);
		else
			throw new NBTTagTypeMismatchException(Tags.getTagName(Tags.TAG_List), tags.get(name).getTagName(), name);
	}

	/**
	 * Checks if the byte tag is not <code>0</code> and returns <code>true</code>,<code>false</code> otherwise. If the byte tag is not present <code>false</code> is returned
	 *
	 * @param name The name of the tag
	 * @return <code>true</code> if the byte tag is not <code>0</code>
	 * @throws NBTTagTypeMismatchException If the tag present is not a byte tag
	 */
	public boolean getBoolean(String name) {
		return getByte(name) != 0;
	}

	/**
	 * Checks if the compound tag is empty
	 *
	 * @return <code>true</code> if the compound tag is empty
	 */
	public boolean isEmpty() {
		return tags.isEmpty();
	}

	@Override
	public String toString() {
		return "" + tags.size() + " entries";
	}

	@Override
	public void print(String prefix, PrintStream out) {
		super.print(prefix, out);
		out.println(prefix + "{");
		String orgPrefix = prefix;
		prefix += "   ";
		for (Tag tag : tags.values()) {
			tag.print(prefix, out);
		}
		out.println(orgPrefix + "}");
	}

	@Override
	public String getTagName() {
		return "TAG_Compound";
	}

	@Override
	public Tag copy() {
		CompoundTag tag = new CompoundTag(getName());
		for (String key : tags.keySet()) {
			tag.put(key, tags.get(key).copy());
		}
		return tag;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			CompoundTag o = (CompoundTag) obj;
			for (Map.Entry<String, Tag> entry : tags.entrySet()) {
				if (!o.tags.containsKey(entry.getKey()))
					return false;
				if (!entry.getValue().equals(o.tags.get(entry.getKey())))
					return false;
			}
			return true;
		}
		return false;
	}
}

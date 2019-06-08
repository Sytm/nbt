package de.md5lukas.nbt;

import de.md5lukas.nbt.exceptions.InvalidTagException;
import de.md5lukas.nbt.extended.*;
import de.md5lukas.nbt.tags.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This is the tag registry managing all tags and creating new ones if required
 *
 * @author Sytm
 */
@SuppressWarnings("unused")
public class Tags {

	/**
	 * Constant ID value that represents the tag TAG_End
	 */
	public static final byte TAG_End = 0;
	/**
	 * Constant ID value that represents the tag TAG_Byte
	 */
	public static final byte TAG_Byte = 1;
	/**
	 * Constant ID value that represents the tag TAG_Short
	 */
	public static final byte TAG_Short = 2;
	/**
	 * Constant ID value that represents the tag TAG_Int
	 */
	public static final byte TAG_Int = 3;
	/**
	 * Constant ID value that represents the tag TAG_Long
	 */
	public static final byte TAG_Long = 4;
	/**
	 * Constant ID value that represents the tag TAG_Float
	 */
	public static final byte TAG_Float = 5;
	/**
	 * Constant ID value that represents the tag TAG_Double
	 */
	public static final byte TAG_Double = 6;
	/**
	 * Constant ID value that represents the tag TAG_Byte_Array
	 */
	public static final byte TAG_Byte_Array = 7;
	/**
	 * Constant ID value that represents the tag TAG_String
	 */
	public static final byte TAG_String = 8;
	/**
	 * Constant ID value that represents the tag TAG_List
	 */
	public static final byte TAG_List = 9;
	/**
	 * Constant ID value that represents the tag TAG_Compound
	 */
	public static final byte TAG_Compound = 10;
	/**
	 * Constant ID value that represents the tag TAG_Int_Array
	 */
	public static final byte TAG_Int_Array = 11;
	/**
	 * Constant ID value that represents the tag TAG_Long_Array
	 */
	public static final byte TAG_Long_Array = 12;

	private static Map<Byte, Function<String, Tag>> tags = new HashMap<>();
	private static Map<Byte, String> tagNames = new HashMap<>();
	private static Map<Byte, Class<? extends Tag>> registeredClasses = new HashMap<>();

	static {
		registerTag0((name) -> new EndTag());
		registerTag0(ByteTag::new);
		registerTag0(ShortTag::new);
		registerTag0(IntTag::new);
		registerTag0(LongTag::new);
		registerTag0(FloatTag::new);
		registerTag0(DoubleTag::new);
		registerTag0(ByteArrayTag::new);
		registerTag0(StringTag::new);
		registerTag0(ListTag::new);
		registerTag0(CompoundTag::new);
		registerTag0(IntArrayTag::new);
		registerTag0(LongArrayTag::new);
	}

	/**
	 * With this method you can register custom tags. You can have up to 242 custom types. This is because the limitation
	 * is the one byte which defines the type of the tag. <br>
	 * <code>2<sup>8</sup>-1 - 13 (Amount of default tags) = 242</code><br>
	 * The function passed as an argument acts as an factory to create the tags. The easiest way to register a new tag
	 * would be:<br>
	 * <code>Tags.registerTag(CustomTag::new);</code><br>
	 * Optionally if you need to do certain things to prepare the tag you could do this:<br>
	 * <pre>{@code Tags.registerTag((name) -> {
	 *     CustomTag tag = new CustomTag(name);
	 *     tag.doStuffX();
	 *     return tag;
	 * });}</pre>
	 *
	 * @param factory The function which creates a new tag. The requested name is passed into it
	 * @see Tags#newTag(byte, String) The factory is used in that function to create a new tag
	 */
	public static void registerTag(Function<String, Tag> factory) {
		Tag tag = factory.apply(null);
		if (tag.getId() >= 0 && tag.getId() <= 12)
			throw new IllegalArgumentException("Tag type ids from 0 - 12 are reserved to the default nbt tags");
		tags.put(tag.getId(), factory);
		tagNames.put(tag.getId(), tag.getTagName());
		registeredClasses.put(tag.getId(), tag.getClass());
	}

	// This is to register the default tags internally, skipping the check if it would override them
	private static void registerTag0(Function<String, Tag> factory) {
		Tag tag = factory.apply(null);
		tags.put(tag.getId(), factory);
		tagNames.put(tag.getId(), tag.getTagName());
		registeredClasses.put(tag.getId(), tag.getClass());
	}

	/**
	 * Creates a new tag which has been registered before using the provided {@link Function factory}
	 *
	 * @param type The id of the tag requested
	 * @param name The name the new tag should have
	 * @return The newly created {@link Tag}
	 * @throws InvalidTagException If a tag has not been registered with that id
	 * @see Tags#registerTag(Function) Register custom tags with this
	 */
	public static Tag newTag(byte type, String name) {
		if (!tags.containsKey(type))
			throw new InvalidTagException("A tag with the tag type id " + type + " has been requested for creation, but was not registered!");
		return tags.get(type).apply(name);
	}

	/**
	 * Returns the TagName of a tag. This uses the value returned from {@link Tag#getTagName()}
	 *
	 * @param type The type id of the tag
	 * @return The TagName
	 * @throws InvalidTagException If a tag has not been registered with that id
	 * @see Tag#getTagName() If you have the object itself use this instead
	 * @see Tags#registerTag(Function) REgister custom tags with this
	 */
	public static String getTagName(byte type) {
		if (!tags.containsKey(type))
			throw new InvalidTagException("The name for a tag with the type id " + type + " has been requested, but was not registered!");
		return tagNames.get(type);
	}

	/**
	 * Checks if a tag has been registered with the id of the provided tag, and if there is one also checks if the classes are the same
	 *
	 * @param tag The {@link Tag} to check
	 * @return <code>true</code> if the Tag has been registered properly
	 */
	public static boolean isRegistered(Tag tag) {
		if (!registeredClasses.containsKey(tag.getId()))
			return false;
		return registeredClasses.get(tag.getId()).equals(tag.getClass());
	}

	/**
	 * Checks if a tag has been registered with the provided id
	 *
	 * @param type The tag id to check
	 * @return If a tag exists with that tag id
	 */
	public static boolean isRegistered(byte type) {
		return registeredClasses.containsKey(type);
	}

	/**
	 * Registers all the tags from the package de.md5lukas.nbt.extended
	 */
	public static void registerExtendedTags() {
		registerTag(Position3DTag::new);
		registerTag(ExtendedStringTag::new);
		registerTag(StringArrayTag::new);
		registerTag(ShortArrayTag::new);
		registerTag(FloatArrayTag::new);
		registerTag(DoubleArrayTag::new);
		registerTag(UUIDTag::new);
	}
}

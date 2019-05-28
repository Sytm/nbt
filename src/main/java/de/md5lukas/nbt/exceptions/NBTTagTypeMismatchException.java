package de.md5lukas.nbt.exceptions;

/**
 * If a method requires one type of tag, but gets passed another one, this exception will be thrown
 */
public class NBTTagTypeMismatchException extends RuntimeException {

	public NBTTagTypeMismatchException(String name, String requestedType) {
		super("Cannot convert to TAG_" + requestedType + " with the value from the tag " + name);
	}

	public NBTTagTypeMismatchException(String message) {
		super(message);
	}
}

package de.md5lukas.nbt.exceptions;

/**
 * If a method requires one type of tag, but gets passed another one, this exception will be thrown
 */
public class NBTTagTypeMismatchException extends RuntimeException {

	public NBTTagTypeMismatchException(String expected, String actual) {
		super("A " + expected + " was expected, but " + actual + " was present instead");
	}

	public NBTTagTypeMismatchException(String expected, String actual, String keyName) {
		super("A " + expected + " was expected, but " + actual + " was present under the key " + keyName);
	}

	public NBTTagTypeMismatchException(String message) {
		super(message);
	}
}

package de.md5lukas.nbt.exceptions;

/**
 * If somewhere while reading or writing a nbt file an unregistered tag shows up this exception will be thrown
 *
 * @author Sytm
 */
public class InvalidTagException extends RuntimeException {

	public InvalidTagException(String message) {
		super(message);
	}
}

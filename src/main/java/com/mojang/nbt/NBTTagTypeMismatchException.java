package com.mojang.nbt;

public class NBTTagTypeMismatchException extends RuntimeException {

	public NBTTagTypeMismatchException(String name, String requestedType) {
		super("Cannot convert to TAG_" + requestedType + " with the value from the tag " + name);
	}
}

package de.md5lukas.nbt.test;

import de.md5lukas.nbt.tags.*;

import java.util.Arrays;

public class CompoundTagPopulator {

	public static void populateCompoundTag(CompoundTag root) {
		populateLayer(root);
		CompoundTag layer1 = root.putCompound("layer1");
		populateLayer(layer1);
		CompoundTag layer2 = layer1.putCompound("layer2");
		populateLayer(layer2);
	}

	private static void populateLayer(CompoundTag layer) {
		layer.putString("string", "Some string");
		layer.putByte("byte", Byte.MIN_VALUE);
		layer.putShort("short", Short.MAX_VALUE);
		layer.putInt("integer", Integer.MIN_VALUE);
		layer.putLong("long", Long.MAX_VALUE);
		layer.putFloat("float", Float.MIN_VALUE);
		layer.putDouble("double", Double.MAX_VALUE);
		layer.putByteArray("array_byte", new byte[]{Byte.MIN_VALUE, Byte.MAX_VALUE});
		layer.putIntArray("array_integer", new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE});
		layer.putLongArray("array_long", new long[]{Long.MIN_VALUE, Long.MAX_VALUE});
		layer.putListTag("list_string", Arrays.asList(new StringTag(null, "String 1"), new StringTag(null, "String 2")));
		layer.putListTag("list_byte", Arrays.asList(new ByteTag(null, Byte.MIN_VALUE), new ByteTag(null, Byte.MAX_VALUE)));
		layer.putListTag("list_short", Arrays.asList(new ShortTag(null, Short.MIN_VALUE), new ShortTag(null, Short.MAX_VALUE)));
		layer.putListTag("list_integer", Arrays.asList(new IntTag(null, Integer.MIN_VALUE), new IntTag(null, Integer.MAX_VALUE)));
		layer.putListTag("list_long", Arrays.asList(new LongTag(null, Long.MIN_VALUE), new LongTag(null, Long.MAX_VALUE)));
		layer.putListTag("list_float", Arrays.asList(new FloatTag(null, Float.MIN_VALUE), new FloatTag(null, Float.MAX_VALUE)));
		layer.putListTag("list_double", Arrays.asList(new DoubleTag(null, Double.MIN_VALUE), new DoubleTag(null, Double.MAX_VALUE)));
	}
}

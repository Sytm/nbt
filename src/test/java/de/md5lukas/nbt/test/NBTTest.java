package de.md5lukas.nbt.test;

import de.md5lukas.nbt.NbtIo;
import de.md5lukas.nbt.tags.CompoundTag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NBTTest {

	private CompoundTag root;

	@BeforeEach
	void populateTag() {
		root = new CompoundTag();
		CompoundTagPopulator.populateCompoundTag(root);
	}

	@Test
	@ExtendWith(IgnoreIOExceptionExtension.class)
	void testWriteAndLoad() throws IOException {
		File file = new File("test.nbt");
		try {
			NbtIo.write(root, file);
			CompoundTag read = NbtIo.read(file);
			assertEquals(root, read);
		} finally {
			file.delete();
		}
	}

	@Test
	@ExtendWith(IgnoreIOExceptionExtension.class)
	void testWriteAndLoadCompressed() throws IOException {
		File file = new File("test.nbt");
		try {
			NbtIo.writeCompressed(root, file);
			CompoundTag read = NbtIo.readCompressed(file);
			assertEquals(root, read);
		} finally {
			file.delete();
		}
	}
}

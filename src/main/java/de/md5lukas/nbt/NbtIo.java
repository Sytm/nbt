package de.md5lukas.nbt;

/**
 * Copyright Mojang AB.
 * <p>
 * Don't do evil.
 */

import de.md5lukas.nbt.exceptions.InvalidTagException;
import de.md5lukas.nbt.tags.CompoundTag;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * This class handles the basic I/O of nbt files
 */
public class NbtIo {

	/**
	 * This method reads a from an compressed {@link InputStream} and creates a new CompoundTag with it
	 *
	 * @param in The input stream to read from
	 * @return The read tag which was present in the InputStream
	 * @throws IOException         If there occurred an I/O exception
	 * @throws InvalidTagException If the root tag is not a {@link CompoundTag}
	 * @throws InvalidTagException If the nbt file uses custom tags which have not been registered
	 */
	public static CompoundTag readCompressed(InputStream in) throws IOException {
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new GZIPInputStream(in)))) {
			return read(dis);
		}
	}

	/**
	 * This method reads a from an compressed {@link File} and creates a new CompoundTag with it
	 *
	 * @param file The file to read from
	 * @return The read tag which was present in the InputStream
	 * @throws IOException         If there occurred an I/O exception
	 * @throws InvalidTagException If the root tag is not a {@link CompoundTag}
	 * @throws InvalidTagException If the nbt file uses custom tags which have not been registered
	 */
	public static CompoundTag readCompressed(File file) throws IOException {
		if (!file.exists()) return null;
		return readCompressed(new FileInputStream(file));
	}

	/**
	 * This method writes an CompoundTag to an compressed {@link OutputStream}
	 *
	 * @param tag The CompoundTag to write to the stream
	 * @param out The output stream to write to
	 * @throws IOException         If there occurred an I/O exception
	 * @throws InvalidTagException If the CompoundTag uses custom tags which have not been registered
	 */
	public static void writeCompressed(CompoundTag tag, OutputStream out) throws IOException {
		try (DataOutputStream dos = new DataOutputStream(new GZIPOutputStream(out))) {
			write(tag, dos);
		}
	}

	/**
	 * This method writes an CompoundTag to an compressed {@link File}
	 *
	 * @param tag  The CompoundTag to write to the stream
	 * @param file The file to write to
	 * @throws IOException         If there occurred an I/O exception
	 * @throws InvalidTagException If the CompoundTag uses custom tags which have not been registered
	 */
	public static void writeCompressed(CompoundTag tag, File file) throws IOException {
		writeCompressed(tag, new FileOutputStream(file));
	}

	/**
	 * This methods reads the provided input and creates a new CompoundTag
	 *
	 * @param dis The data input to read from
	 * @return The read CompoundTag
	 * @throws IOException         If there occurred an I/O exception
	 * @throws InvalidTagException If the nbt data uses custom tags which have not been registered
	 */
	public static CompoundTag read(DataInput dis) throws IOException {
		Tag tag = Tag.readNamedTag(dis);
		if (tag instanceof CompoundTag) return (CompoundTag) tag;
		throw new InvalidTagException("Root tag must be a named compound tag");
	}

	/**
	 * This methods reads the provided file and creates a new CompoundTag
	 *
	 * @param file The file to read from
	 * @return The read CompoundTag
	 * @throws IOException         If there occurred an I/O exception
	 * @throws InvalidTagException If the nbt file uses custom tags which have not been registered
	 */
	public static CompoundTag read(File file) throws IOException {
		if (!file.exists()) return null;
		try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
			return read(dis);
		}
	}

	/**
	 * This methods writes the CompoundTag to the provided output
	 *
	 * @param tag The CompoundTag to write
	 * @param dos The data output to write to
	 * @throws IOException         If there occurred an I/O exception
	 * @throws InvalidTagException If the nbt data uses custom tags which have not been registered
	 */
	public static void write(CompoundTag tag, DataOutput dos) throws IOException {
		Tag.writeNamedTag(tag, dos);
	}

	/**
	 * This methods writes the CompoundTag to the provided file
	 *
	 * @param tag  The CompoundTag to write
	 * @param file The file to write to
	 * @throws IOException         If there occurred an I/O exception
	 * @throws InvalidTagException If the nbt data uses custom tags which have not been registered
	 */
	public static void write(CompoundTag tag, File file) throws IOException {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
			write(tag, dos);
		}
	}
}

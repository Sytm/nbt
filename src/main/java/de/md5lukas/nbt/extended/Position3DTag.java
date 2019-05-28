package de.md5lukas.nbt.extended;

import de.md5lukas.nbt.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * This custom tag is a representation of three coordinates, namely x, y and z.<br><br>
 * <b>Note: Normal nbt software will not understand these custom types and therefore cannot read it properly</b>
 */
public class Position3DTag extends Tag {

	private double x, y, z;

	public Position3DTag(String name) {
		super(name);
	}

	public Position3DTag(String name, double x, double y, double z) {
		super(name);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void write(DataOutput dos) throws IOException {
		dos.writeDouble(x);
		dos.writeDouble(y);
		dos.writeDouble(z);
	}

	@Override
	public void load(DataInput dis) throws IOException {
		x = dis.readDouble();
		y = dis.readDouble();
		z = dis.readDouble();
	}

	/**
	 * @return The current x value
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets new x value and returns this object for chaining
	 *
	 * @param x Sets the new x value
	 * @return This object
	 */
	public Position3DTag setX(double x) {
		this.x = x;
		return this;
	}

	/**
	 * @return The current y value
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets new y value and returns this object for chaining
	 *
	 * @param y Sets the new y value
	 * @return This object
	 */
	public Position3DTag setY(double y) {
		this.y = y;
		return this;
	}

	/**
	 * @return The current z value
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Sets new z value and returns this object for chaining
	 *
	 * @param z Sets the new z value
	 * @return This object
	 */
	public Position3DTag setZ(double z) {
		this.z = z;
		return this;
	}

	@Override
	public String toString() {
		return " X: " + x + " Y: " + y + " Z: " + z;
	}

	@Override
	public byte getId() {
		return 15;
	}

	@Override
	public String getTagName() {
		return "TAG_Position3D";
	}

	@Override
	public Tag copy() {
		return new Position3DTag(getName(), x, y, z);
	}
}

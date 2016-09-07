package com.simoncat.framework.serializer.api.access;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import com.esotericsoftware.kryo.KryoException;

/**
 * This API for provide unified and easy ways to use serialization service WRITE functions.
 * 
 * @author Simon LUO (printscroll@163.com)
 */
public interface SimoncatOutput {

	/**
	 * Write String.
	 *
	 * @param value
	 * @throws KryoException
	 */
	void writeString(@Nonnull String value);

	/**
	 * Write primitive int
	 *
	 * @param value
	 * @throws KryoException
	 */
	void writeInt(int value);

	/**
	 * Write primitive long
	 *
	 * @param value
	 * @throws KryoException
	 */
	void writeLong(long value);

	/**
	 * Write primitive boolean
	 *
	 * @param value
	 * @throws KryoException
	 */
	void writeBoolean(boolean value);

	/**
	 * Write primitive double
	 *
	 * @param value
	 * @throws KryoException
	 */
	void writeDouble(double value);

	/**
	 * Write primitive float
	 *
	 * @param value
	 * @throws KryoException
	 */
	void writeFloat(float value);

	/**
	 * Write primitive short
	 *
	 * @param value
	 * @throws KryoException
	 */
	void writeShort(short value);

	/**
	 * Write primitive byte
	 *
	 * @param value
	 * @throws KryoException
	 */
	void writeByte(byte value);

	/**
	 * Write primitive char
	 *
	 * @param value
	 * @throws KryoException
	 */
	void writeChar(char value);

	/**
	 * Try to write an object with specific class.
	 *
	 * @param value
	 * @param clazz
	 * @throws KryoException
	 */
	@CheckForNull
	<T> void writeObject(T value, Class<T> clazz);

	/**
	 * Try to write an object.
	 * 
	 * @param value
	 * @throws KryoException
	 */
	@CheckForNull
	void writeObject(Object value);
}

package com.simoncat.framework.serializer.api.access;

import javax.annotation.CheckForNull;

/**
 * This API for provide unified and easy ways to use serialization service READ functions.
 * 
 * @author Simon LUO (printscroll@163.com)
 */
public interface SimoncatInput {

	/**
	 * Read String.
	 *
	 * @return
	 */
	@CheckForNull
	String readString();

	/**
	 * Read primitive int
	 *
	 * @return
	 * @throws KryoException
	 */
	int readInt();

	/**
	 * Read primitive long
	 *
	 * @return
	 * @throws KryoException
	 */
	long readLong();

	/**
	 * Read primitive boolean
	 *
	 * @return
	 * @throws KryoException
	 */
	boolean readBoolean();

	/**
	 * Read primitive double
	 *
	 * @return
	 * @throws KryoException
	 */
	double readDouble();

	/**
	 * Read primitive float
	 *
	 * @return
	 * @throws KryoException
	 */
	float readFloat();

	/**
	 * Read primitive short
	 *
	 * @return
	 * @throws KryoException
	 */
	short readShort();

	/**
	 * Read primitive byte
	 *
	 * @return
	 * @throws KryoException
	 */
	byte readByte();

	/**
	 * Read primitive char
	 *
	 * @return
	 * @throws KryoException
	 */
	char readChar();

	/**
	 * Try to read an object with specific class. Please NOTE: the returned value may be null.
	 *
	 * @param clazz
	 * @return
	 */
	@CheckForNull
	<T> T readObject(Class<T> clazz);

	/**
	 * Try to read an object. Please NOTE: the returned value may be null.
	 * 
	 * @return
	 */
	@CheckForNull
	Object readObject();
}

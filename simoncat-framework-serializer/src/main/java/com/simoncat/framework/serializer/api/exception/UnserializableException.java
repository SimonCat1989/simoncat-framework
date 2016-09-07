package com.simoncat.framework.serializer.api.exception;

/**
 * The Exception for Serialization Service.
 * 
 * @author Simon LUO (printscroll@163.com)
 *
 */
public class UnserializableException extends RuntimeException {

	private static final long serialVersionUID = -6586250496133557786L;

	public UnserializableException() {
		super();
	}

	public UnserializableException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnserializableException(String message) {
		super(message);
	}

	public UnserializableException(Throwable cause) {
		super(cause);
	}
}

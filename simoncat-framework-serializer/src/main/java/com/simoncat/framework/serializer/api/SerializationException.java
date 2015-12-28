package com.simoncat.framework.serializer.api;

public class SerializationException extends RuntimeException {

	private static final long serialVersionUID = -7666031144132934731L;

	public SerializationException() {
        super();
    }

    public SerializationException(String msg) {
        super(msg);
    }

    public SerializationException(String msg, Throwable e) {
        super(msg, e);
    }

    public SerializationException(Throwable e) {
        super(e);
    }
}

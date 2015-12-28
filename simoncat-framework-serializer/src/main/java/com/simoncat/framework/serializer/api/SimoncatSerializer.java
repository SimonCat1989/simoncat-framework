package com.simoncat.framework.serializer.api;

import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Nonnull;

public interface SimoncatSerializer {

	byte[] serialize(@Nonnull Object o);

	byte[] serializeWithClass(@Nonnull Object o);

	void serializeIntoStream(@Nonnull Object o, @Nonnull OutputStream os);

	void serializeWithClassIntoStream(@Nonnull Object o, @Nonnull OutputStream os);

	<T> T deserialize(@Nonnull byte[] bytes, @Nonnull Class<T> clazz);

	Object deserializeWithClass(@Nonnull byte[] bytes);

	<T> T deserialize(@Nonnull InputStream is, @Nonnull Class<T> clazz);

	Object deserializeWithClass(@Nonnull InputStream is);
}

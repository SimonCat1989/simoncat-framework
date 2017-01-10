package com.simoncat.framework.json.api;

import java.io.File;

import com.simoncat.framework.json.exception.InvalidJsonConversionException;

public interface JsonOperator {

	<T> T read(File jsonFile, Class<T> instanceClass) throws InvalidJsonConversionException;

	<T> boolean write(File jsonFile, T instance) throws InvalidJsonConversionException;

	<T> String convert(T instance) throws InvalidJsonConversionException;
}

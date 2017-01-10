package com.simoncat.framework.json.core;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simoncat.framework.json.api.JsonOperator;
import com.simoncat.framework.json.exception.InvalidJsonConversionException;

public class JacksonJsonOperator implements JsonOperator {

	private static final ObjectMapper LOCAL_MAPPER = new ObjectMapper();

	private static final String JSON_READ_EXCEPTION_MESSAGE = "Failed to read from jsonfile: ";
	private static final String JSON_GENERATION_EXCEPTION_MESSAGE = "[JsonGenerationException] Exception type for "
	        + "exceptions during JSON writing, such as trying to output content in wrong context (non-matching end-array "
	        + " or end-object, for example).";
	private static final String JSON_MAPPING_EXCEPTION_MESSAGE = "[JsonMappingException] Checked exception used to signal "
	        + "fatal problems with mapping of content.";
	private static final String IO_EXCEPTION_MESSAGE = "Can NOT find output json file: ";
	private static final String JSON_PROCESSING_EXCEPTION_MESSAGE = "[JsonProcessingException] Failed to convert object to json.";

	@Override
	public <T> T read(File jsonFile, Class<T> instanceClass) throws InvalidJsonConversionException {
		try {
			return LOCAL_MAPPER.readValue(jsonFile, instanceClass);
		} catch (IOException e) {
			throw new InvalidJsonConversionException(
			        JSON_READ_EXCEPTION_MESSAGE + jsonFile.getAbsolutePath(), e);
		}
	}

	@Override
	public <T> boolean write(File jsonFile, T instance) throws InvalidJsonConversionException {
		try {
			LOCAL_MAPPER.writeValue(jsonFile, instance);
			return true;
		} catch (JsonGenerationException e) {
			throw new InvalidJsonConversionException(JSON_GENERATION_EXCEPTION_MESSAGE, e);
		} catch (JsonMappingException e) {
			throw new InvalidJsonConversionException(JSON_MAPPING_EXCEPTION_MESSAGE, e);
		} catch (IOException e) {
			throw new InvalidJsonConversionException(IO_EXCEPTION_MESSAGE + jsonFile.getAbsolutePath(), e);
		}
	}

	@Override
	public <T> String convert(T instance) throws InvalidJsonConversionException {
		try {
			return LOCAL_MAPPER.writeValueAsString(instance);
		} catch (JsonProcessingException e) {
			throw new InvalidJsonConversionException(JSON_PROCESSING_EXCEPTION_MESSAGE, e);
		}
	}

}

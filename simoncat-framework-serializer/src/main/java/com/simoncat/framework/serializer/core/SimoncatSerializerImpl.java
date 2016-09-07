package com.simoncat.framework.serializer.core;

import java.io.InputStream;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;

import com.simoncat.framework.serializer.api.SimoncatSerializer;

@Slf4j
public class SimoncatSerializerImpl implements SimoncatSerializer {

	@Override
	public byte[] serialize(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] serializeWithClass(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void serializeIntoStream(Object o, OutputStream os) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serializeWithClassIntoStream(Object o, OutputStream os) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T deserialize(byte[] bytes, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deserializeWithClass(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T deserialize(InputStream is, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deserializeWithClass(InputStream is) {
		// TODO Auto-generated method stub
		return null;
	}

}

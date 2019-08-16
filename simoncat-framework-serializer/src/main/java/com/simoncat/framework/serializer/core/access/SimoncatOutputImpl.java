package com.simoncat.framework.serializer.core.access;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimoncatOutputImpl implements com.simoncat.framework.serializer.api.access.Output {

	private Kryo kryo;
	private Output output;

	@Override
	public void writeString(String value) {
		output.writeString(value);
	}

	@Override
	public void writeInt(int value) {
		output.writeInt(value);
	}

	@Override
	public void writeLong(long value) {
		output.writeLong(value);
	}

	@Override
	public void writeBoolean(boolean value) {
		output.writeBoolean(value);
	}

	@Override
	public void writeDouble(double value) {
		output.writeDouble(value);
	}

	@Override
	public void writeFloat(float value) {
		output.writeFloat(value);
	}

	@Override
	public void writeShort(short value) {
		output.writeShort(value);
	}

	@Override
	public void writeByte(byte value) {
		output.writeByte(value);
	}

	@Override
	public void writeChar(char value) {
		output.writeChar(value);
	}

	@Override
	public <T> void writeObject(T value, Class<T> clazz) {
		kryo.writeObjectOrNull(output, value, clazz);
	}

	@Override
	public void writeObject(Object value) {
		kryo.writeClassAndObject(output, value);
	}
}

package com.simoncat.framework.serializer.core.access;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimoncatInputImpl implements com.simoncat.framework.serializer.api.access.Input {

	private Kryo kryo;
	private Input input;

	@Override
	public String readString() {
		return input.readString();
	}

	@Override
	public int readInt() {
		return input.readInt();
	}

	@Override
	public long readLong() {
		return input.readLong();
	}

	@Override
	public boolean readBoolean() {
		return input.readBoolean();
	}

	@Override
	public double readDouble() {
		return input.readDouble();
	}

	@Override
	public float readFloat() {
		return input.readFloat();
	}

	@Override
	public short readShort() {
		return input.readShort();
	}

	@Override
	public byte readByte() {
		return input.readByte();
	}

	@Override
	public char readChar() {
		return input.readChar();
	}

	@Override
	public <T> T readObject(Class<T> clazz) {
		return kryo.readObjectOrNull(input, clazz);
	}

	@Override
	public Object readObject() {
		return kryo.readClassAndObject(input);
	}

}

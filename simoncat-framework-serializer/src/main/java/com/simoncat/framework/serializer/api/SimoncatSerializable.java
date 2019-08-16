package com.simoncat.framework.serializer.api;

import com.simoncat.framework.serializer.api.access.Input;
import com.simoncat.framework.serializer.api.access.Output;

/**
 * Allow to implement the own serialization process. If your class does not implement this interface, then by default, the serializer is
 * {@link SimoncatDefaultSerializer}.
 * 
 * @author Simon LUO (printscroll@163.com)
 *
 */
public interface SimoncatSerializable {

	/**
	 * The customized process for deserialize this class.
	 * 
	 * @param input
	 */
	public void read(Input input);

	/**
	 * The customized process for serialize this class.
	 * 
	 * @param output
	 */
	public void write(Output output);

}

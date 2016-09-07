package com.simoncat.framework.serializer.api;

import com.simoncat.framework.serializer.api.access.SimoncatInput;
import com.simoncat.framework.serializer.api.access.SimoncatOutput;

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
	public void read(SimoncatInput input);

	/**
	 * The customized process for serialize this class.
	 * 
	 * @param output
	 */
	public void write(SimoncatOutput output);

}

package com.agnie.common.util.converter;

import com.agnie.common.util.client.converter.ConversionException;

/**
 * Basic interface for converting Single column tokens into required data type
 * 
 */
public abstract class AbstractSingleColumnConverter {

	public abstract Object convert(String token, Class<?> cls) throws ConversionException;

}

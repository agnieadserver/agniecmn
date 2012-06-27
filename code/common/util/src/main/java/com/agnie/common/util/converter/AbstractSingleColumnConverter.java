package com.agnie.common.util.converter;

/**
 * Basic interface for converting Single column tokens into required data type
 * 
 */
public abstract class AbstractSingleColumnConverter {

	public abstract Object convert(String token, Class<?> cls) throws ConversionException;

}

package com.agnie.common.util.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * Basic converter for Enum Types.
 * 
 */

public class BasicEnumSingleColumnConverter extends AbstractSingleColumnConverter {
	protected static final Log	logger	= LogFactory.getLog(BasicEnumSingleColumnConverter.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object convert(String token, Class<?> cls) throws ConversionException {
		if (token != null && !("".equals(token)) && cls.isEnum()) {
			return Enum.valueOf((Class<Enum>) cls, token);
		}
		return null;
	}

}

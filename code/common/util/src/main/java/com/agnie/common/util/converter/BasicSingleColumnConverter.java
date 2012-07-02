package com.agnie.common.util.converter;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agnie.common.util.tablefile.GeneralException;

/**
 * 
 * Basic converter for most of the primitive types and string types.
 * 
 */

@For({ int.class, Integer.class, long.class, Long.class, float.class, Float.class, double.class, Double.class, byte.class, Byte.class, short.class, Short.class, boolean.class, Boolean.class,
		String.class })
public class BasicSingleColumnConverter extends AbstractSingleColumnConverter {
	protected static final Log	logger	= LogFactory.getLog(BasicSingleColumnConverter.class);

	@Override
	public Object convert(String token, Class<?> cls) throws ConversionException {
		if (token != null && !("".equals(token))) {
			try {
				if (int.class.equals(cls) || Integer.class.equals(cls)) {
					StringTokenizer st = new StringTokenizer(token, ".");
					token = st.nextToken();
					return Integer.parseInt(token);
				} else if (float.class.equals(cls) || Float.class.equals(cls)) {
					return Float.parseFloat(token);
				} else if (long.class.equals(cls) || Long.class.equals(cls)) {
					StringTokenizer st = new StringTokenizer(token, ".");
					token = st.nextToken();
					return Long.parseLong(token);
				} else if (double.class.equals(cls) || Double.class.equals(cls)) {
					return Double.parseDouble(token);
				} else if (boolean.class.equals(cls) || Boolean.class.equals(cls)) {
					return Boolean.parseBoolean(token);
				} else if (String.class.equals(cls)) {
					return token;
				} else if (short.class.equals(cls) || Short.class.equals(cls)) {
					StringTokenizer st = new StringTokenizer(token, ".");
					token = st.nextToken();
					return Short.parseShort(token);
				} else if (byte.class.equals(cls) || Byte.class.equals(cls)) {
					StringTokenizer st = new StringTokenizer(token, ".");
					token = st.nextToken();
					return Byte.parseByte(token);
				} else {
					logger.error("There is no convertor avaialble for class '" + cls.getCanonicalName() + "'.");
					throw new GeneralException("Programming issue : There is no convertor avaialble for class '" + cls.getCanonicalName() + "'.","converter.not.provided");
				}
			} catch (NumberFormatException ex) {
				logger.error(ex);
				throw new ConversionException("number.format", ex);
			}
		}
		return null;
	}

}

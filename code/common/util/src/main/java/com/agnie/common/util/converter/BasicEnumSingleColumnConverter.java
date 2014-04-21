/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agnie.common.util.client.converter.ConversionException;

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

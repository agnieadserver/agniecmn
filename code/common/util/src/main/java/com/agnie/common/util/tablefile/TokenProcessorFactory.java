/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import java.util.HashMap;
import java.util.Map;

import com.agnie.common.util.converter.TypeInfo;

@SuppressWarnings("rawtypes")
public class TokenProcessorFactory {

	private static final Map<Class, TokenProcessor>	map	= new HashMap<Class, TokenProcessor>();

	@SuppressWarnings("unchecked")
	public static TokenProcessor getConverter(Class cls, boolean throwErrors) {
		TokenProcessor converter = map.get(cls);
		if (converter == null) {
			converter = new TokenProcessor(cls, throwErrors);
			map.put(cls, converter);
		}
		converter.setTypeInfo(new TypeInfo(cls));
		return converter;
	}

	@SuppressWarnings("unchecked")
	protected static TokenProcessor getConverter(Class cls, TypeInfo info, boolean throwErrors) {
		TokenProcessor converter = map.get(cls);
		if (converter == null) {
			converter = new TokenProcessor(cls, throwErrors);
			map.put(cls, converter);
		}
		converter.setTypeInfo(info);
		return converter;
	}
}

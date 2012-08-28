package com.agnie.common.util.tablefile;

import java.util.HashMap;
import java.util.Map;

import com.agnie.common.util.converter.TypeInfo;

@SuppressWarnings("rawtypes")
public class SimpleTokenProcessorFactory {

	private static final Map<Class, SimpleTokenProcessor>	map	= new HashMap<Class, SimpleTokenProcessor>();

	@SuppressWarnings("unchecked")
	public static SimpleTokenProcessor getConverter(Class cls, boolean throwErrors) {
		SimpleTokenProcessor converter = map.get(cls);
		if (converter == null) {
			converter = new SimpleTokenProcessor(cls, throwErrors);
			map.put(cls, converter);
		}
		converter.setTypeInfo(new TypeInfo(cls));
		return converter;
	}

	@SuppressWarnings("unchecked")
	protected static SimpleTokenProcessor getConverter(Class cls, TypeInfo info, boolean throwErrors) {
		SimpleTokenProcessor converter = map.get(cls);
		if (converter == null) {
			converter = new SimpleTokenProcessor(cls, throwErrors);
			map.put(cls, converter);
		}
		converter.setTypeInfo(info);
		return converter;
	}
}

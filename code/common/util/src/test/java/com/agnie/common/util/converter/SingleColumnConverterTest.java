package com.agnie.common.util.converter;

import junit.framework.Assert;

import org.junit.Test;

public class SingleColumnConverterTest {

	@Test
	public void intializeTest() {

		SingleColumnConverterFactory factory = SingleColumnConverterFactory.getInstance();
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(int.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(Integer.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(long.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(Long.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(float.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(Float.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(boolean.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(Boolean.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(double.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(Double.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(byte.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(Byte.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(short.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(Short.class).getClass());
		Assert.assertEquals(BasicSingleColumnConverter.class, factory.getConverter(String.class).getClass());

	}
}

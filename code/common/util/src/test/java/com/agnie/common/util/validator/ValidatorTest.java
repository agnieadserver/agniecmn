package com.agnie.common.util.validator;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {

	@Test
	public void notNullValiMappingTest() {
		ValidatorFactory factory = new ValidatorFactory();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setTest1", String.class));
			Assert.assertEquals(NotNullValidator.class, validators.get(0).getClass());

			validators = factory.getFieldValidator(Bean.class.getDeclaredField("test1"));
			Assert.assertEquals(NotNullValidator.class, validators.get(0).getClass());

		} catch (SecurityException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
}

class Bean {
	@NotNull
	private String	test1;

	public String getTest1() {
		return test1;
	}

	@NotNull
	public void setTest1(String test1) {
		this.test1 = test1;
	}

}

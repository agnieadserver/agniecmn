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

	@Test
	public void notNullValidatorTest() {
		ValidatorFactory factory = new ValidatorFactory();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setTest1", String.class));
			Assert.assertEquals(NotNullValidator.class, validators.get(0).getClass());
			Assert.assertEquals(true, validators.get(0).validate("SomeData"));
			Assert.assertEquals(false, validators.get(0).validate(null));

		} catch (SecurityException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void minLengthValiMappingTest() {
		ValidatorFactory factory = new ValidatorFactory();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setMinLengthTest", String.class));
			Assert.assertEquals(MinimumLengthValidator.class, validators.get(0).getClass());

			validators = factory.getFieldValidator(Bean.class.getDeclaredField("minLengthTest"));
			Assert.assertEquals(MinimumLengthValidator.class, validators.get(0).getClass());

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

	@Test
	public void minLengthValidatorTest() {
		ValidatorFactory factory = new ValidatorFactory();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setMinLengthTest", String.class));
			Assert.assertEquals(MinimumLengthValidator.class, validators.get(0).getClass());

			Assert.assertEquals(true, validators.get(0).validate("Manmohan Singh"));
			Assert.assertEquals(false, validators.get(0).validate("balu"));

			validators = factory.getFieldValidator(Bean.class.getDeclaredField("minLengthTest"));
			Assert.assertEquals(MinimumLengthValidator.class, validators.get(0).getClass());
			Assert.assertEquals(true, validators.get(0).validate("Manmo"));
			Assert.assertEquals(false, validators.get(0).validate("balu"));
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

	@MinimumLength(5)
	private String	minLengthTest;

	public String getTest1() {
		return test1;
	}

	@NotNull
	public void setTest1(String test1) {
		this.test1 = test1;
	}

	/**
	 * @return the minLengthTest
	 */
	public String getMinLengthTest() {
		return minLengthTest;
	}

	/**
	 * @param minLengthTest
	 *            the minLengthTest to set
	 */

	@MinimumLength(10)
	public void setMinLengthTest(String minLengthTest) {
		this.minLengthTest = minLengthTest;
	}

}

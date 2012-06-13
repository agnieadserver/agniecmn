package com.agnie.common.util.validator;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {

	@Test
	public void notNullValiMappingTest() {
		ValidatorFactory factory = ValidatorFactory.getInstance();
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
		ValidatorFactory factory = ValidatorFactory.getInstance();
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
		ValidatorFactory factory = ValidatorFactory.getInstance();
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
		ValidatorFactory factory = ValidatorFactory.getInstance();
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


	@Test
	public void maxLengthValiMappingTest() {
		ValidatorFactory factory = ValidatorFactory.getInstance();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setMaxLengthTest", String.class));
			Assert.assertEquals(MaximumLengthValidator.class, validators.get(0).getClass());

			validators = factory.getFieldValidator(Bean.class.getDeclaredField("maxLengthTest"));
			Assert.assertEquals(MaximumLengthValidator.class, validators.get(0).getClass());

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
	public void maxLengthValidatorTest() {
		ValidatorFactory factory = ValidatorFactory.getInstance();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setMaxLengthTest", String.class));
			Assert.assertEquals(MaximumLengthValidator.class, validators.get(0).getClass());

			Assert.assertEquals(false, validators.get(0).validate("12345678901234567890"));
			Assert.assertEquals(true, validators.get(0).validate("12345678"));

			validators = factory.getFieldValidator(Bean.class.getDeclaredField("maxLengthTest"));
			Assert.assertEquals(MaximumLengthValidator.class, validators.get(0).getClass());
			Assert.assertEquals(true, validators.get(0).validate("12345678"));
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
	public void eMailValiMappingTest() {
		ValidatorFactory factory = ValidatorFactory.getInstance();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setEMailTest", String.class));
			Assert.assertEquals(EMailValidator.class, validators.get(0).getClass());

			validators = factory.getFieldValidator(Bean.class.getDeclaredField("eMailTest"));
			Assert.assertEquals(EMailValidator.class, validators.get(0).getClass());

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
	public void eMailValidatorTest() {
		ValidatorFactory factory = ValidatorFactory.getInstance();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setEMailTest", String.class));
			Assert.assertEquals(EMailValidator.class, validators.get(0).getClass());
			Assert.assertEquals(true, validators.get(0).validate("abc@abc.com"));
			Assert.assertEquals(false, validators.get(0).validate("abc"));
			Assert.assertEquals(false, validators.get(0).validate("abc@abc.c"));

		} catch (SecurityException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test
	public void regularExpValiMappingTest() {
		ValidatorFactory factory = ValidatorFactory.getInstance();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setRegularExpTest", String.class));
			Assert.assertEquals(RegularExpValidator.class, validators.get(0).getClass());

			validators = factory.getFieldValidator(Bean.class.getDeclaredField("regularExpTest"));
			Assert.assertEquals(RegularExpValidator.class, validators.get(0).getClass());

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
	public void regularExpValidatorTest() {
		ValidatorFactory factory = ValidatorFactory.getInstance();
		try {
			List<Validator> validators = factory.getMethodValidator(Bean.class.getDeclaredMethod("setRegularExpTest", String.class));
			Assert.assertEquals(RegularExpValidator.class, validators.get(0).getClass());
			Assert.assertEquals(true, validators.get(0).validate("192.168.1.1"));
			Assert.assertEquals(false, validators.get(0).validate("12345"));
			Assert.assertEquals(false, validators.get(0).validate("321.168.1.1"));

		} catch (SecurityException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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

	@MaximumLength(10)
	private String	maxLengthTest;
	
	@EMail
	private String eMailTest;
	
	@RegularExp("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
	private String regularExpTest;

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


	/**
	 * @return the maxLengthTest
	 */
	public String getMaxLengthTest() {
		return maxLengthTest;
	}

	/**
	 * @param maxLengthTest
	 *            the maxLengthTest to set
	 */

	@MaximumLength(15)
	public void setMaxLengthTest(String maxLengthTest) {
		this.maxLengthTest = maxLengthTest;
	}


	/**
	 * @return the eMailTest
	 */
	public String getEMailTest() {
		return eMailTest;
	}

	/**
	 * @param eMailTest
	 *            the eMailTest to set
	 */

	@EMail
	public void setEMailTest(String eMailTest) {
		this.eMailTest = eMailTest;
	}



	/**
	 * @return the regeularExpTest
	 */
	public String getRegularExpTest() {
		return regularExpTest;
	}

	/**
	 * @param regularExpTest
	 *            the regularExpTest to set
	 */

	@RegularExp("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
	public void setRegularExpTest(String regularExpTest) {
		this.regularExpTest = regularExpTest;
	}

}

package com.agnie.common.util.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.util.client.validator.ConstraintRegularExpressions;

public class RegularExpTest {

	@Test
	public void emailExpTest() {
		Pattern pattern = Pattern.compile(ConstraintRegularExpressions.EMAIL_VALIDATOR_EXP);
		Matcher matcher = pattern.matcher("abc@abc.com");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("abc");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("abc@abc.c");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("abc@abc.co.in");
		Assert.assertTrue(matcher.matches());
	}

	@Test
	public void noWhiteSpaceTest() {
		Pattern pattern = Pattern.compile(ConstraintRegularExpressions.NO_WHITE_SPACE_EXP);
		Matcher matcher = pattern.matcher("test");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("This contains    space");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("This contains \t tab");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("This contains \r\n line feed new line");
		Assert.assertFalse(matcher.matches());
	}

	@Test
	public void URLTest() {
		Pattern pattern = Pattern.compile(ConstraintRegularExpressions.VALID_URL_EXP);
		Matcher matcher = pattern.matcher("http://test.com/");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test.com/");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test.com");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test.com:");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test.com:/");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test.com:890");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test.com:890/");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("https://test/");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("ttps://test.com/");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("test.com");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("test5674563");
		Assert.assertFalse(matcher.matches());
	}

}

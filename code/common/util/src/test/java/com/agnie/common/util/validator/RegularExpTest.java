/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
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
		matcher = pattern.matcher("https://test.com:/");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test.com:890");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test.com:890/");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://192.168.9.1:890/");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("http://localhost");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("http://localhost/");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test");
		Assert.assertTrue(matcher.matches());
		matcher = pattern.matcher("https://test/");
		Assert.assertTrue(matcher.matches());

		matcher = pattern.matcher("https://test.com:");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("ttps://test.com/");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("test.com");
		Assert.assertFalse(matcher.matches());
		matcher = pattern.matcher("test5674563");
		Assert.assertFalse(matcher.matches());
	}

}

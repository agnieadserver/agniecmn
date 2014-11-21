/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class TokenProcessorTest {

	@SuppressWarnings({ "unchecked" })
	@Test
	public void simpleTest() {

		TokenProcessor<SampleBean> processor = TokenProcessorFactory.getConverter(SampleBean.class, false);
		Map<String, String> row = new HashMap<String, String>();
		row.put("Fname", "Pranoti");
		row.put("Lname", "Patil");
		List<Map<String, String>> rowTokens = new ArrayList<Map<String, String>>();
		rowTokens.add(row);
		SampleBean actual = processor.getBean(rowTokens);
		SampleBean expected = new SampleBean();
		expected.setFname("Pranoti");
		expected.setLname("Patil");
		Assert.assertEquals(expected, actual);

	}

	@Test
	@SuppressWarnings({ "unchecked" })
	public void constraintTest() {
		TokenProcessor<SampleBean> processor = TokenProcessorFactory.getConverter(SampleBean.class, false);
		Map<String, String> row = new HashMap<String, String>();
		row.put("Lname", "Patil");
		row.put("Age", "30");
		List<Map<String, String>> rowTokens = new ArrayList<Map<String, String>>();
		rowTokens.add(row);
		SampleBean actual = processor.getBean(rowTokens);
		SampleBean expected = new SampleBean();
		expected.setLname("Patil");
		expected.setAge(30);
		Assert.assertEquals(expected, actual);
		ErrorMapping error = actual.getError("Fname");
		Assert.assertEquals("constraint.notnull.fail", error.getErrors().get(0));
	}

	@Test
	@SuppressWarnings({ "unchecked" })
	public void invalidInputTest() {
		TokenProcessor<SampleBean> processor = TokenProcessorFactory.getConverter(SampleBean.class, false);
		Map<String, String> row = new HashMap<String, String>();
		row.put("Fname", "Pranoti");
		row.put("Lname", "Patil");
		row.put("Age", "sbcd");
		List<Map<String, String>> rowTokens = new ArrayList<Map<String, String>>();
		rowTokens.add(row);
		SampleBean actual = processor.getBean(rowTokens);
		SampleBean expected = new SampleBean();
		expected.setFname("Pranoti");
		expected.setLname("Patil");
		Assert.assertEquals(expected, actual);
		ErrorMapping error = actual.getError("Age");
		Assert.assertEquals("invalid.number.format", error.getErrors().get(0));
	}

	@Test
	@SuppressWarnings({ "unchecked" })
	public void singleColCollectionTypeTest() {
		TokenProcessor<SampleBean> processor = TokenProcessorFactory.getConverter(SampleBean.class, false);
		Map<String, String> row = new HashMap<String, String>();
		row.put("Fname", "Pranoti");
		row.put("Lname", "Patil");
		row.put("Age", "30");
		row.put("Phone", "34345234~363456455");
		List<Map<String, String>> rowTokens = new ArrayList<Map<String, String>>();
		rowTokens.add(row);
		SampleBean actual = processor.getBean(rowTokens);
		SampleBean expected = new SampleBean();
		expected.setFname("Pranoti");
		expected.setLname("Patil");
		expected.setAge(30);
		expected.addPhone(34345234L);
		expected.addPhone(363456455L);
		Assert.assertEquals(expected, actual);
	}
}

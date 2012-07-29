package com.agnie.common.util.tablefile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.util.converter.MultiColumnType;
import com.agnie.common.util.converter.UseTokenizer;
import com.agnie.common.util.validator.NotNull;

public class TokenProcessorTest {

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


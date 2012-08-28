package com.agnie.common.util.tablefile;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class SimpleTokenProcessorTest {

	@Test
	public void invalidBeanTest() {

		try {
			SimpleTokenProcessor<SampleBean> processor = SimpleTokenProcessorFactory.getConverter(SampleBean.class, false);
			Map<String, String> row = new HashMap<String, String>();
			row.put("Fname", "Pranoti");
			row.put("Lname", "Patil");
			SampleBean actual = processor.getBean(row);
		} catch (InvalidBeanType e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void simpleTokenProcessorTest() {
		try {
			SimpleTokenProcessor<SimpleBean> processor = SimpleTokenProcessorFactory.getConverter(SimpleBean.class, false);
			Map<String, String> row = new HashMap<String, String>();
			row.put("Fname", "Pranoti");
			row.put("Lname", "Patil");
			SimpleBean actual = processor.getBean(row);
			SimpleBean expected = new SimpleBean();
			expected.setFname("Pranoti");
			expected.setLname("Patil");
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
}

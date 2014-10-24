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

public class MultiTokenProcessorTest {

	@Test
	public void simpleTest() {
		@SuppressWarnings("unchecked")
		TokenProcessor<MultiBean> processor = TokenProcessorFactory.getConverter(MultiBean.class, false);
		Map<String, String> row = new HashMap<String, String>();
		row.put("SinglColumn", "Something");
		row.put("Fname", "Pranoti");
		row.put("Lname", "Patil");
		row.put("Age", "30");
		List<Map<String, String>> rowTokens = new ArrayList<Map<String, String>>();
		rowTokens.add(row);
		MultiBean actual = processor.getBean(rowTokens);
		MultiBean expected = new MultiBean();
		expected.setSinglColumn("Something");
		SampleBean bean = new SampleBean();
		bean.setFname("Pranoti");
		bean.setLname("Patil");
		bean.setAge(30);
		expected.setSampleBean(bean);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void constraintTest() {
		@SuppressWarnings("unchecked")
		TokenProcessor<MultiBean> processor = TokenProcessorFactory.getConverter(MultiBean.class, false);
		Map<String, String> row = new HashMap<String, String>();
		row.put("SinglColumn", "Something");
		row.put("Lname", "Patil");
		row.put("Age", "30");
		List<Map<String, String>> rowTokens = new ArrayList<Map<String, String>>();
		rowTokens.add(row);
		MultiBean actual = processor.getBean(rowTokens);
		MultiBean expected = new MultiBean();
		expected.setSinglColumn("Something");
		SampleBean bean = new SampleBean();
		bean.setLname("Patil");
		bean.setAge(30);
		expected.setSampleBean(bean);
		Assert.assertEquals(expected, actual);

		ErrorMapping error = actual.getSampleBean().getError("Fname");
		Assert.assertEquals("constraint.notnull.fail", error.getErrors().get(0));
	}

	@Test
	public void multiCollectionTest() {
		@SuppressWarnings("unchecked")
		TokenProcessor<MultiColBean> processor = TokenProcessorFactory.getConverter(MultiColBean.class, false);
		List<Map<String, String>> rowTokens = new ArrayList<Map<String, String>>();
		Map<String, String> row = new HashMap<String, String>();
		row.put("SingleColumn", "Something");
		row.put("Fname", "Pranoti");
		row.put("Lname", "Patil");
		row.put("Age", "30");
		rowTokens.add(row);
		row = new HashMap<String, String>();
		row.put("Fname", "Suresh");
		row.put("Lname", "Heble");
		row.put("Age", "30");
		rowTokens.add(row);
		MultiColBean actual = processor.getBean(rowTokens);
		MultiColBean expected = new MultiColBean();
		expected.setSingleColumn("Something");
		SampleBean bean = new SampleBean();
		bean.setFname("Pranoti");
		bean.setLname("Patil");
		bean.setAge(30);
		expected.addSampleBeans(bean);
		bean = new SampleBean();
		bean.setFname("Suresh");
		bean.setLname("Heble");
		bean.setAge(30);
		expected.addSampleBeans(bean);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void innerMutiCollectionTest() {
		@SuppressWarnings("unchecked")
		TokenProcessor<MultiColWrapperBean> processor = TokenProcessorFactory.getConverter(MultiColWrapperBean.class, false);
		List<Map<String, String>> rowTokens = new ArrayList<Map<String, String>>();
		Map<String, String> row = new HashMap<String, String>();
		row.put("WrapperColName", "WrapperSomething");
		row.put("SingleColumn", "Something");
		row.put("Fname", "Pranoti");
		row.put("Lname", "Patil");
		row.put("Age", "30");
		rowTokens.add(row);
		row = new HashMap<String, String>();
		row.put("Fname", "Suresh");
		row.put("Lname", "Heble");
		row.put("Age", "30");
		rowTokens.add(row);
		row = new HashMap<String, String>();
		row.put("SingleColumn", "2nd Something");
		row.put("Fname", "Pranoti1");
		row.put("Lname", "Patil1");
		row.put("Age", "31");
		rowTokens.add(row);
		row = new HashMap<String, String>();
		row.put("Fname", "Suresh1");
		row.put("Lname", "Heble1");
		row.put("Age", "31");
		rowTokens.add(row);
		row = new HashMap<String, String>();
		row.put("Fname", "Suresh2");
		row.put("Lname", "Heble2");
		row.put("Age", "32");
		rowTokens.add(row);

		MultiColWrapperBean actual = processor.getBean(rowTokens);
		MultiColWrapperBean expected = new MultiColWrapperBean();
		expected.setWrapperColName("WrapperSomething");
		MultiColBean mBean = new MultiColBean();
		mBean.setSingleColumn("Something");
		SampleBean bean = new SampleBean();
		bean.setFname("Pranoti");
		bean.setLname("Patil");
		bean.setAge(30);
		mBean.addSampleBeans(bean);
		bean = new SampleBean();
		bean.setFname("Suresh");
		bean.setLname("Heble");
		bean.setAge(30);
		mBean.addSampleBeans(bean);
		expected.addMultiColBeans(mBean);

		mBean = new MultiColBean();
		mBean.setSingleColumn("2nd Something");
		bean = new SampleBean();
		bean.setFname("Pranoti1");
		bean.setLname("Patil1");
		bean.setAge(31);
		mBean.addSampleBeans(bean);
		bean = new SampleBean();
		bean.setFname("Suresh1");
		bean.setLname("Heble1");
		bean.setAge(31);
		mBean.addSampleBeans(bean);
		bean = new SampleBean();
		bean.setFname("Suresh2");
		bean.setLname("Heble2");
		bean.setAge(32);
		mBean.addSampleBeans(bean);
		expected.addMultiColBeans(mBean);
		Assert.assertEquals(expected, actual);
	}
}

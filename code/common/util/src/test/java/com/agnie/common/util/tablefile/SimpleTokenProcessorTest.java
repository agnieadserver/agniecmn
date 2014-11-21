/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.util.client.tablefile.InvalidBeanType;

public class SimpleTokenProcessorTest {

	@SuppressWarnings({ "unchecked", "unused" })
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
	@SuppressWarnings({ "unchecked" })
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

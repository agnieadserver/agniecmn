/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.agnie.common.util.tablefile.CSVConfig;
import com.agnie.common.util.tablefile.CSVFileIterator;

/**
 * Unit test for CSVFileIterator.
 */
public class CSVIteratorTest {
	@Test
	public void csvNumberOfRecordTest() {
		String doc = "\"Name\", \"Age\", \"LongData\", \"salary\" \n\"Ravindra\", \"27\",\"\", \"\" \n \"Ram\", \"29\", \"323\",\"345.67\"";

		BufferedReader reader = new BufferedReader(new StringReader(doc));
		CSVFileIterator<SampleBean> itr;
		try {
			itr = new CSVFileIterator<SampleBean>(reader, SampleBean.class);
			int count = 0;
			while (itr.hasNext()) {
				SampleBean sampleBean = (SampleBean) itr.next();
				System.out.println(sampleBean);
				count++;
			}
			Assert.assertEquals(2, count);
			Assert.assertTrue(true);
		} catch (IOException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void csvRecordTest() {
		String doc = "\"Name\", \"Age\", \"LongData\", \"salary\" \n\"Ravindra\", \"27\",\"323\",\"345.56\"";

		BufferedReader reader = new BufferedReader(new StringReader(doc));
		CSVFileIterator<SampleBean> itr;
		try {
			CSVConfig config = new CSVConfig();
			config.setStrictQuote(true);
			itr = new CSVFileIterator<SampleBean>(reader, SampleBean.class, config);
			int count = 0;
			SampleBean beanExpected = new SampleBean("Ravindra", 27, 323L, (float) 345.56);
			while (itr.hasNext()) {
				SampleBean beanActual = (SampleBean) itr.next();
				Assert.assertEquals(beanExpected, beanActual);
				count++;
			}
			Assert.assertEquals(1, count);
			Assert.assertTrue(true);
		} catch (IOException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void csvWithoutStrictQuoatTest() {
		String doc = "Name, Age, LongData, salary \nRavindra, 27,323,345.56 \n Sam, 45, 453, 563.67";

		BufferedReader reader = new BufferedReader(new StringReader(doc));
		CSVFileIterator<SampleBean> itr;
		try {
			itr = new CSVFileIterator<SampleBean>(reader, SampleBean.class);
			int count = 0;
			List<SampleBean> listExpected = new ArrayList<SampleBean>();
			listExpected.add(new SampleBean("Ravindra", 27, 323L, (float) 345.56));
			listExpected.add(new SampleBean("Sam", 45, 453, (float) 563.67));
			List<SampleBean> listActual = new ArrayList<SampleBean>();
			while (itr.hasNext()) {
				SampleBean beanActual = (SampleBean) itr.next();
				listActual.add(beanActual);
				count++;
			}
			Assert.assertEquals(2, count);
			Assert.assertEquals(listExpected, listActual);
			Assert.assertTrue(true);
		} catch (IOException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void csvLastFewCellEmptyTest() {
		String doc = "Name, Age, LongData, salary \nRavindra, 27,, \n Sam, 45, 453, 563.67";

		BufferedReader reader = new BufferedReader(new StringReader(doc));
		CSVFileIterator<SampleBean> itr;
		try {
			itr = new CSVFileIterator<SampleBean>(reader, SampleBean.class);
			int count = 0;
			List<SampleBean> listExpected = new ArrayList<SampleBean>();
			listExpected.add(new SampleBean("Ravindra", 27, 0, null));
			listExpected.add(new SampleBean("Sam", 45, 453, (float) 563.67));
			List<SampleBean> listActual = new ArrayList<SampleBean>();
			while (itr.hasNext()) {
				SampleBean beanActual = (SampleBean) itr.next();
				listActual.add(beanActual);
				count++;
			}
			Assert.assertEquals(2, count);
			Assert.assertEquals(listExpected, listActual);
			Assert.assertTrue(true);
		} catch (IOException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void csvFailConstraintTest() {
		String doc = "\"Name\", \"Age\", \"LongData\", \"salary\" \n\"\", \"27\",\"323\",\"345.56\"";

		BufferedReader reader = new BufferedReader(new StringReader(doc));
		CSVFileIterator<SampleBean> itr;
		try {
			CSVConfig config = new CSVConfig();
			config.setStrictQuote(true);
			itr = new CSVFileIterator<SampleBean>(reader, SampleBean.class, config);
			int count = 0;
			SampleBean beanExpected = new SampleBean(null, 27, 323L, (float) 345.56);
			while (itr.hasNext()) {
				SampleBean beanActual = itr.next();
				Assert.assertEquals(beanExpected, beanActual);
				ErrorMapping error = beanActual.getError("Name");
				Assert.assertEquals("constraint.notnull.fail", error.getErrors().get(0));
				count++;
			}
			Assert.assertEquals(1, count);
			Assert.assertTrue(true);
		} catch (IOException e) {
			Assert.assertTrue(false);
		}
	}
}

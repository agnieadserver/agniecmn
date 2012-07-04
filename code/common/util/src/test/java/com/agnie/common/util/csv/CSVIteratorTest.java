package com.agnie.common.util.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

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
			itr = new CSVFileIterator<SampleBean>(reader, SampleBean.class);
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
	public void csvFailConstraintTest() {
		String doc = "\"Name\", \"Age\", \"LongData\", \"salary\" \n\"\", \"27\",\"323\",\"345.56\"";

		BufferedReader reader = new BufferedReader(new StringReader(doc));
		CSVFileIterator<SampleBean> itr;
		try {
			itr = new CSVFileIterator<SampleBean>(reader, SampleBean.class);
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

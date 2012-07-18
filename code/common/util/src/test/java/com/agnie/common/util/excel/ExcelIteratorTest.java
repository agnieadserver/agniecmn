package com.agnie.common.util.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;

import com.agnie.common.util.csv.SampleBean;
import com.agnie.common.util.tablefile.ExcelSheetIterator;
import com.agnie.common.util.tablefile.ExcelWorkbook;

/**
 * Unit test for ExcelSheetIterator.
 */
public class ExcelIteratorTest {
	@Test
	public void HSSFRecordTest() {
		try {
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			InputStream is = cl.getResourceAsStream("BeanTest.xls");
			ExcelWorkbook<SampleBean> workbook = new ExcelWorkbook<SampleBean>(is, SampleBean.class);

			while (workbook.hasNext()) {
				ExcelSheetIterator<SampleBean> itr = workbook.next();

				SampleBean beanExpected = new SampleBean("abc", 10, 1000, (float) 2000.5);
				int count = 0;

				while (itr.hasNext()) {
					SampleBean beanActual = (SampleBean) itr.next();
					count++;
					System.out.println("Bean No. " + count + " = " + beanActual.toString());
					if (count == 3)
						Assert.assertEquals(beanExpected, beanActual);
				}

				Assert.assertEquals("A-Wing", itr.getSheetName());
				System.out.println("Total beans = " + count);
				Assert.assertEquals(5, count);
				Assert.assertTrue(true);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			Assert.assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void XSSFRecordTest() {
		try {
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			InputStream is = cl.getResourceAsStream("BeanTest.xlsx");
			ExcelWorkbook<SampleBean> workbook = new ExcelWorkbook<SampleBean>(is, SampleBean.class);

			while (workbook.hasNext()) {
				ExcelSheetIterator<SampleBean> itr = workbook.next();

				SampleBean beanExpected = new SampleBean("abc", 10, 1000, (float) 2000.5);
				int count = 0;

				while (itr.hasNext()) {
					SampleBean beanActual = (SampleBean) itr.next();
					count++;
					System.out.println("Bean No. " + count + " = " + beanActual.toString());
					if (count == 3)
						Assert.assertEquals(beanExpected, beanActual);
				}

				Assert.assertEquals("A-Wing", itr.getSheetName());
				System.out.println("Total beans = " + count);
				Assert.assertEquals(5, count);
				Assert.assertTrue(true);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			Assert.assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

}

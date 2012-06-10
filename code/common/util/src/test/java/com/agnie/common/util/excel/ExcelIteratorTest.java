package com.agnie.common.util.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.agnie.common.util.csv.SampleBean;
import com.agnie.common.util.tablefile.ExcelFileIterator;

/**
 * Unit test for ExcelFileIterator.
 */
public class ExcelIteratorTest
{
		@Test
		public void excelRecordTest()
		{
			try
			{
				ClassLoader cl = ClassLoader.getSystemClassLoader();
				InputStream is = cl.getResourceAsStream("BeanTest.xls");
				
				ExcelFileIterator<SampleBean> itr = new ExcelFileIterator<SampleBean>(is, SampleBean.class);
				
				SampleBean beanExpected = new SampleBean("abc", 10, 1000, (float) 2000.5);
				int count = 0;
				
				while (itr.hasNext())
				{
					SampleBean beanActual = (SampleBean)itr.next();
					count++;
					System.out.println("Bean No. " + count + " = " + beanActual.toString());
					if(count == 3)
						Assert.assertEquals(beanExpected, beanActual);
				}
				System.out.println("Total beans = " + count);
				Assert.assertEquals(5, count);
				Assert.assertTrue(true);
			}
			catch (FileNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (IOException e)
			{
				Assert.assertTrue(false);
			}
		}

}

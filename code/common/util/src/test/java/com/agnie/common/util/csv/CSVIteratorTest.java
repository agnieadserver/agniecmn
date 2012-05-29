package com.agnie.common.util.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for CSVFileIterator.
 */
public class CSVIteratorTest {
	@Test
	public void testSimpleCSV() {
		String doc = "\"Name\", \"Age\", \"LongData\", \"salary\" \n\"Ravindra\", \"27\",\"\", \"\" \n \"Ram\", \"29\", \"323\",\"345.67\"";

		BufferedReader reader = new BufferedReader(new StringReader(doc));
		CSVFileIterator<SampleBean> itr;
		try {
			itr = new CSVFileIterator<SampleBean>(reader, SampleBean.class);
			while (itr.hasNext()) {
				SampleBean sampleBean = (SampleBean) itr.next();
				System.out.println(sampleBean);
			}
			Assert.assertTrue(true);
		} catch (IOException e) {
			Assert.assertTrue(false);
		}
	}
}

class SampleBean {
	private String	name;
	private int		age;
	private long	longData;
	private Float	salary;

	@CSVHeader(constraints = { CSVConstraint.NOTNULL })
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * @return the age
	 */

	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	@CSVHeader
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the longData
	 */
	public long getLongData() {
		return longData;
	}

	/**
	 * @param longData
	 *            the longData to set
	 */
	@CSVHeader
	public void setLongData(long longData) {
		this.longData = longData;
	}

	/**
	 * @return the salary
	 */
	public Float getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	@CSVHeader(name = "salary")
	public void setSalary(Float salary) {
		this.salary = salary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SampleBean [name=" + name + ", age=" + age + ", longData=" + longData + ", salary=" + salary + "]";
	}

}
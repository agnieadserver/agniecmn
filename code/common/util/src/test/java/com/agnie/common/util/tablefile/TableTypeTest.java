/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.util.client.converter.MultiColumnType;
import com.agnie.common.util.client.tablefile.TableType;
import com.agnie.common.util.validator.NotNull;

public class TableTypeTest {

	@Test
	public void simpleTest() {
		@SuppressWarnings("unchecked")
		TokenProcessor<SampleTableBean> processor = TokenProcessorFactory.getConverter(SampleTableBean.class, false);
		Map<String, String> row = new HashMap<String, String>();
		row.put("Fname", "Pranoti");
		row.put("Lname", "Patil");
		List<Map<String, String>> rowTokens = new ArrayList<Map<String, String>>();
		rowTokens.add(row);
		SampleTableBean actual = processor.getBean(rowTokens);
		SampleTableBean expected = new SampleTableBean();
		expected.setFname("Pranoti");
		expected.setLname("Patil");
		List<String> errors = new ArrayList<String>();
		errors.add("constraint.notnull.fail");
		expected.insertError("Age", null, errors);
		Assert.assertEquals(expected, actual);
	}

}

@MultiColumnType
class SampleTableBean extends BaseTableBean {

	private TableType<Integer>	age;
	private TableType<String>	fname;
	private TableType<String>	lname;

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age != null ? age.getValue() : null;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	@NotNull
	public void setAge(Integer age) {
		if (this.age == null) {
			this.age = new TableType<Integer>(age);
		} else {
			this.age.setValue(age);
		}
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname != null ? fname.getValue() : null;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		if (this.fname == null) {
			this.fname = new TableType<String>(fname);
		} else {
			this.fname.setValue(fname);
		}
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname.getValue();
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		if (this.lname == null) {
			this.lname = new TableType<String>(lname);
		} else {
			this.lname.setValue(lname);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SampleTableBean other = (SampleTableBean) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SampleTableBean [age=" + age + ", fname=" + fname + ", lname=" + lname + "]";
	}

}

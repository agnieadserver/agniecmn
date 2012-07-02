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

@MultiColumnType
class SampleBean implements TableBean {

	private String		fname;
	private String		lname;
	private float		age;
	private List<Long>	phones	= new ArrayList<Long>();

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	@NotNull
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	@NotNull
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the age
	 */
	public float getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(float age) {
		this.age = age;
	}

	@UseTokenizer(separator = "~")
	public void addPhone(Long phone) {
		phones.add(phone);
	}

	private Map<String, ErrorMapping>	errors	= new HashMap<String, ErrorMapping>();

	public void insertError(String property, String value, List<String> errors) {
		this.errors.put(property, new ErrorMapping(value, errors));
	}

	public ErrorMapping getError(String property) {
		return errors.get(property);
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
		result = prime * result + Float.floatToIntBits(age);
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
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
		SampleBean other = (SampleBean) obj;
		if (Float.floatToIntBits(age) != Float.floatToIntBits(other.age))
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
		if (phones == null) {
			if (other.phones != null)
				return false;
		} else if (!phones.equals(other.phones))
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
		return "SampleBean [fname=" + fname + ", lname=" + lname + ", errors=" + errors + "]";
	}

}

class ErrorMapping {
	private String			token;
	private List<String>	errors;

	/**
	 * @param token
	 * @param errors
	 */
	public ErrorMapping(String token, List<String> errors) {
		super();
		this.token = token;
		this.errors = errors;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

}
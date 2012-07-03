package com.agnie.common.util.tablefile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.util.converter.MultiColumnType;
import com.agnie.common.util.validator.NotNull;

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
}

@MultiColumnType
class MultiColBean implements TableBean {

	private String				singleColumn;
	private List<SampleBean>	sampleBeans	= new ArrayList<SampleBean>();

	/**
	 * @return the singleColumn
	 */
	public String getSingleColumn() {
		return singleColumn;
	}

	/**
	 * @param singleColumn
	 *            the singleColumn to set
	 */
	@NotNull
	public void setSingleColumn(String singleColumn) {
		this.singleColumn = singleColumn;
	}

	/**
	 * @return the sampleBeans
	 */
	public List<SampleBean> getSampleBeans() {
		return sampleBeans;
	}

	/**
	 * @param sampleBeans
	 *            the sampleBeans to set
	 */
	public void addSampleBeans(SampleBean sampleBean) {
		this.sampleBeans.add(sampleBean);
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
		result = prime * result + ((sampleBeans == null) ? 0 : sampleBeans.hashCode());
		result = prime * result + ((singleColumn == null) ? 0 : singleColumn.hashCode());
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
		MultiColBean other = (MultiColBean) obj;
		if (sampleBeans == null) {
			if (other.sampleBeans != null)
				return false;
		} else if (!sampleBeans.equals(other.sampleBeans))
			return false;
		if (singleColumn == null) {
			if (other.singleColumn != null)
				return false;
		} else if (!singleColumn.equals(other.singleColumn))
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
		return "MultiColBean [singleColumn=" + singleColumn + ", sampleBeans=" + sampleBeans + "]";
	}

	public void insertError(String property, String value, List<String> errors) {
	}
}

@MultiColumnType
class MultiBean implements TableBean {

	private String		singlColumn;
	private SampleBean	sampleBean;

	/**
	 * @return the singlColumn
	 */
	public String getSinglColumn() {
		return singlColumn;
	}

	/**
	 * @param singlColumn
	 *            the singlColumn to set
	 */
	public void setSinglColumn(String singlColumn) {
		this.singlColumn = singlColumn;
	}

	/**
	 * @return the sampleBean
	 */
	public SampleBean getSampleBean() {
		return sampleBean;
	}

	/**
	 * @param sampleBean
	 *            the sampleBean to set
	 */
	public void setSampleBean(SampleBean sampleBean) {
		this.sampleBean = sampleBean;
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
		result = prime * result + ((sampleBean == null) ? 0 : sampleBean.hashCode());
		result = prime * result + ((singlColumn == null) ? 0 : singlColumn.hashCode());
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
		MultiBean other = (MultiBean) obj;
		if (sampleBean == null) {
			if (other.sampleBean != null)
				return false;
		} else if (!sampleBean.equals(other.sampleBean))
			return false;
		if (singlColumn == null) {
			if (other.singlColumn != null)
				return false;
		} else if (!singlColumn.equals(other.singlColumn))
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
		return "MultiBean [singlColumn=" + singlColumn + ", sampleBean=" + sampleBean + ", errors=" + errors + "]";
	}

}
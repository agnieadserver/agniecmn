package com.agnie.common.util.tablefile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.agnie.common.util.converter.MultiColumnType;

@MultiColumnType
public class MultiBean implements TableBean {

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
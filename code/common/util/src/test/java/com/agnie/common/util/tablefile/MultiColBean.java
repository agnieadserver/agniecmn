/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import java.util.ArrayList;
import java.util.List;

import com.agnie.common.util.client.converter.MultiColumnType;
import com.agnie.common.util.client.tablefile.TableBean;
import com.agnie.common.util.validator.NotNull;

@MultiColumnType
public class MultiColBean implements TableBean {

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

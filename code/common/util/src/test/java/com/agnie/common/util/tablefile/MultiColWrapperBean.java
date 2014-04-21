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
import java.util.List;

import com.agnie.common.util.client.converter.MultiColumnType;
import com.agnie.common.util.client.tablefile.TableBean;
import com.agnie.common.util.validator.NotNull;

@MultiColumnType
public class MultiColWrapperBean implements TableBean {
	private String				wrapperColName;
	private List<MultiColBean>	multiColBeans	= new ArrayList<MultiColBean>();

	/**
	 * @return the wrapperColName
	 */
	public String getWrapperColName() {
		return wrapperColName;
	}

	/**
	 * @param wrapperColName
	 *            the wrapperColName to set
	 */
	@NotNull
	public void setWrapperColName(String wrapperColName) {
		this.wrapperColName = wrapperColName;
	}

	/**
	 * @return the multiColBeans
	 */
	public List<MultiColBean> getMultiColBeans() {
		return multiColBeans;
	}

	/**
	 * @param multiColBeans
	 *            the multiColBeans to set
	 */
	public void addMultiColBeans(MultiColBean multiColBean) {
		this.multiColBeans.add(multiColBean);
	}

	public void insertError(String property, String value, List<String> errors) {
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
		result = prime * result + ((multiColBeans == null) ? 0 : multiColBeans.hashCode());
		result = prime * result + ((wrapperColName == null) ? 0 : wrapperColName.hashCode());
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
		MultiColWrapperBean other = (MultiColWrapperBean) obj;
		if (multiColBeans == null) {
			if (other.multiColBeans != null)
				return false;
		} else if (!multiColBeans.equals(other.multiColBeans))
			return false;
		if (wrapperColName == null) {
			if (other.wrapperColName != null)
				return false;
		} else if (!wrapperColName.equals(other.wrapperColName))
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
		return "MultiColWrapperBean [wrapperColName=" + wrapperColName + ", multiColBeans=" + multiColBeans + "]";
	}

}

/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.jdbc;

import com.agnie.common.util.client.converter.MultiColumnType;
import com.agnie.common.util.client.tablefile.TableHeader;
import com.agnie.common.util.client.tablefile.TableType;
import com.agnie.common.util.tablefile.BaseTableBean;
import com.agnie.common.util.validator.NotNull;

@MultiColumnType
public class SimpleCompany extends BaseTableBean {

	private TableType<Integer>	id;
	private TableType<String>	company;
	private TableType<String>	location;

	/**
	 * @return the id
	 */
	public int getId() {
		return id != null ? id.getValue() : 0;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@NotNull
	@TableHeader(name = "id")
	public void setId(int id) {
		if (this.id == null) {
			this.id = new TableType<Integer>();
		}
		this.id.setValue(id);
	}

	/**
	 * @return the company
	 */
	public String getCompanyName() {
		return company != null ? company.getValue() : null;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	@TableHeader(name = "company")
	public void setCompanyName(String companyName) {
		if (this.company == null) {
			this.company = new TableType<String>();
		}
		this.company.setValue(companyName);
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location != null ? location.getValue() : null;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	@TableHeader(name = "location")
	public void setLocation(String location) {
		if (this.location == null) {
			this.location = new TableType<String>();
		}
		this.location.setValue(location);
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
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		SimpleCompany other = (SimpleCompany) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
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
		return "SimpleCompany [id=" + id + ", company=" + company + ", location=" + location + "]";
	}

}

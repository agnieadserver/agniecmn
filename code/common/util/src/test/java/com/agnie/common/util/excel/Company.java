/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.excel;

import java.util.ArrayList;
import java.util.List;

import com.agnie.common.util.client.converter.MultiColumnType;
import com.agnie.common.util.client.tablefile.TableHeader;
import com.agnie.common.util.client.tablefile.TableType;
import com.agnie.common.util.tablefile.BaseTableBean;
import com.agnie.common.util.validator.NotNull;

@MultiColumnType
public class Company extends BaseTableBean {

	private TableType<Integer>	id;
	private TableType<String>	companyName;
	private List<Employee>		emploies	= new ArrayList<Employee>();

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
	public void setId(int id) {
		if (this.id == null) {
			this.id = new TableType<Integer>();
		}
		this.id.setValue(id);
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName != null ? companyName.getValue() : null;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	@TableHeader(name = "Company Name")
	public void setCompanyName(String companyName) {
		if (this.companyName == null) {
			this.companyName = new TableType<String>();
		}
		this.companyName.setValue(companyName);
	}

	/**
	 * @return the emploies
	 */
	public List<Employee> getEmploies() {
		return emploies;
	}

	/**
	 * @param emploies
	 *            the emploies to set
	 */
	public void addEmployee(Employee employee) {
		this.emploies.add(employee);
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
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((emploies == null) ? 0 : emploies.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Company other = (Company) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (emploies == null) {
			if (other.emploies != null)
				return false;
		} else if (!emploies.equals(other.emploies))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "Company [id=" + id + ", companyName=" + companyName + ", emploies=" + emploies + "]";
	}

}

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
import com.agnie.common.util.converter.UseTokenizer;
import com.agnie.common.util.tablefile.BaseTableBean;
import com.agnie.common.util.validator.NotNull;

@MultiColumnType
public class Employee extends BaseTableBean {

	private TableType<String>	name;
	private TableType<Integer>	age;
	private List<String>		contacts	= new ArrayList<String>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name != null ? name.getValue() : null;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@TableHeader(name = "Employee Name")
	@NotNull
	public void setName(String name) {
		if (this.name == null) {
			this.name = new TableType<String>();
		}
		this.name.setValue(name);
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age != null ? age.getValue() : 0;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		if (this.age == null) {
			this.age = new TableType<Integer>();
		}
		this.age.setValue(age);
	}

	/**
	 * @return the contacts
	 */
	public List<String> getContacts() {
		return contacts;
	}

	/**
	 * @param contacts
	 *            the contacts to set
	 */
	@TableHeader(name = "Contact Nos")
	@UseTokenizer(separator = "-")
	public void addContact(String contact) {
		this.contacts.add(contact);
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
		result = prime * result + ((contacts == null) ? 0 : contacts.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Employee other = (Employee) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (contacts == null) {
			if (other.contacts != null)
				return false;
		} else if (!contacts.equals(other.contacts))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "Employee [name=" + name + ", age=" + age + ", contacts=" + contacts + "]";
	}

}

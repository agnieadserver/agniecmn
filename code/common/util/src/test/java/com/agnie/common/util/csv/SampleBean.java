package com.agnie.common.util.csv;

import com.agnie.common.util.tablefile.TableHeader;
import com.agnie.common.util.validator.NotNull;

public class SampleBean {
	private String	name;
	private int		age;
	private long	longData;
	private Float	salary;

	public SampleBean() {
	}

	public SampleBean(String name, int age, long longData, Float salary) {
		this.name = name;
		this.age = age;
		this.longData = longData;
		this.salary = salary;
	}

	@TableHeader()
	@NotNull
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
	@TableHeader
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
	@TableHeader
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
	@TableHeader(name = "salary")
	public void setSalary(Float salary) {
		this.salary = salary;
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
		result = prime * result + age;
		result = prime * result + (int) (longData ^ (longData >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
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
		if (age != other.age)
			return false;
		if (longData != other.longData)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
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
		return "SampleBean [name=" + name + ", age=" + age + ", longData=" + longData + ", salary=" + salary + "]";
	}

}
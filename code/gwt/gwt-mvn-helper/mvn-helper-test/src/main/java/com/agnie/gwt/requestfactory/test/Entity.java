package com.agnie.gwt.requestfactory.test;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.helper.requestfactory.marker.RFEntityProxy;
import com.agnie.gwt.helper.requestfactory.marker.RFProxyMethod;
import com.agnie.gwt.helper.requestfactory.marker.RFServiceMethod;

@RFEntityProxy
public class Entity extends BaseEntity {

	private String	id;
	private double	salary;
	private String	location;
	private Status	status;

	/**
	 * @return the id
	 */
	@RFProxyMethod
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@RFProxyMethod
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the salary
	 */
	@RFProxyMethod
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	@RFProxyMethod
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * @return the location
	 */
	@RFProxyMethod
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	@RFProxyMethod
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the status
	 */
	@RFProxyMethod
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	@RFProxyMethod
	public void setStatus(Status status) {
		this.status = status;
	}

	@RFServiceMethod
	public void persist() {

	}

	@RFServiceMethod
	public static String testFind(String first, int second) {
		List<String> test = new ArrayList<String>();
		return null;
	}

}

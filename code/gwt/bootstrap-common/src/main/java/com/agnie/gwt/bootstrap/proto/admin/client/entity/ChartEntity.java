package com.agnie.gwt.bootstrap.proto.admin.client.entity;

public class ChartEntity {

	private String	key;

	private double	value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public ChartEntity(String key, double value) {
		this.key = key;
		this.value = value;
	}

}

package com.agnie.gwt.helper;

import com.agnie.gwt.helper.marker.OverlayField;
import com.agnie.gwt.helper.marker.OverlayType;

@OverlayType
public class Address {

	@OverlayField
	private String	add1;
	@OverlayField
	private String	city;
	@OverlayField
	private String	state;

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

package com.agnie.gwt.requestfactory.test;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.helper.requestfactory.marker.RFServiceMethod;

public class BaseEntity {

	@RFServiceMethod
	public void basePersist() {

	}

	@RFServiceMethod
	public static String baseTestFind(String first, int second) {
		List<String> test = new ArrayList<String>();
		return null;
	}
}

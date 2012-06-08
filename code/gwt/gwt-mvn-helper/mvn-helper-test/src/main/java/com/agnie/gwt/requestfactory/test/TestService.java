package com.agnie.gwt.requestfactory.test;

import com.agnie.gwt.helper.requestfactory.marker.RFService;
import com.agnie.gwt.helper.requestfactory.marker.RFServiceMethod;

@RFService(value=TestLocator.class)
public class TestService {

	@RFServiceMethod
	public static String getUserName(String id) {
		return null;
	}
}

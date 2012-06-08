package com.agnie.gwt.requestfactory.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.agnie.gwt.helper.requestfactory.marker.RFService;
import com.agnie.gwt.helper.requestfactory.marker.RFServiceMethod;

@RFService(value = TestLocator.class)
public class TestService {

	@RFServiceMethod
	public static String getUserName(Set<Integer> id) {
		return null;
	}

	@RFServiceMethod
	public static Value tryEntityValue(Set<Entity> id) {
		return null;
	}

	@RFServiceMethod
	public static List<List<List<Value>>> trySomethingComplicatedfsadfa(List<List<List<Entity>>> in) {
		return null;
	}

	@RFServiceMethod
	public static Set<List<Map<String, Value>>> trySomethingComplicated(Value id, List<String> sec) {
		return null;
	}
}

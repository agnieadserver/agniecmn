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

	/*
	 * This is a negative test case to test it uncomment the below method to check if plugin throws error
	 */

	// @RFServiceMethod
	// public static Set<List<Map<String, File>>> negativeUnsupportedType(File id, List<String> sec) {
	// return null;
	// }

	@RFServiceMethod
	public static String arrayParams(Entity[][] params) {
		return null;
	}

	@RFServiceMethod
	public static String arrayParams(String[][] params) {
		return null;
	}

	@RFServiceMethod
	public static String[] arrayReturn(String[][] params) {
		return null;
	}

	@RFServiceMethod
	public static String genericArrayParam(Set<Double[]> id) {
		return null;
	}

	/*
	 * TODO: Need to take of situation of return type in below method
	 */
	@RFServiceMethod
	public static List<List<List<Value>[][]>[][]> genericArrayParamproxy(Set<Entity[]> id) {
		return null;
	}
}

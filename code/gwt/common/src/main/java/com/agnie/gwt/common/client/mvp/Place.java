/**
 * 
 */
package com.agnie.gwt.common.client.mvp;

import java.util.HashMap;
import java.util.Map;

public class Place<PLACE extends Enum<PLACE>> {

	private Map<String, String>	parameters	= new HashMap<String, String>();

	private PLACE				place;

	public Place(PLACE place) {
		this.place = place;
	}

	public PLACE getPlace() {
		return place;
	}

	public void put(String key, String value) {
		parameters.put(key, value);
	}

	public String get(String key) {
		return parameters.get(key);
	}

	public void setParameters(String token) {

		if (token != null) {
			for (String singToken : token.split("&")) {
				if (!singToken.isEmpty()) {
					parameters.put(singToken.substring(0, singToken.indexOf('=')), singToken.substring(singToken.indexOf('=') + 1, singToken.length()));
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer string = new StringBuffer(place.name());
		if (parameters.size() > 0) {
			string.append(":");
			boolean first = true;
			for (String key : parameters.keySet()) {
				if (first) {
					first = false;
				} else {
					string.append("&");
				}
				string.append(key);
				string.append("=");
				string.append(parameters.get(key));
			}
		}
		return string.toString();
	}

}

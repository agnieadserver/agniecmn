/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
/**
 * 
 */
package com.agnie.gwt.common.client.mvp;

import java.util.HashMap;
import java.util.Map;

public class Place<PLACE extends Enum<PLACE>> {

	private Map<String, String> parameters = new HashMap<String, String>();

	private PLACE place;

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
					parameters.put(singToken.substring(0,
							singToken.indexOf('=')), singToken.substring(
							singToken.indexOf('=') + 1, singToken.length()));
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

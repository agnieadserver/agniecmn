/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.gwt.serverclient.client.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;

public class StringURL {
	private String						urlStr;

	private String						queryString;

	private String						baseUrl;

	private Map<String, List<String>>	parameterMap	= new HashMap<String, List<String>>();

	public StringURL(String path) {
		this.urlStr = path;
		if (path.contains(QueryString.HASH.getKey())) {
			baseUrl = path.substring(0, path.indexOf(QueryString.HASH.getKey()));
		} else {
			baseUrl = path;
		}
		if (baseUrl.contains(QueryString.QUESTION_MARK.getKey())) {
			// removed all query parameters including "?" so it removes "?gwt.server=127.0.0.1:9997"
			queryString = baseUrl.substring(baseUrl.indexOf(QueryString.QUESTION_MARK.getKey()) + 1, baseUrl.length());
			for (String paramPair : queryString.split(QueryString.AMPERSAND.getKey())) {
				String[] param = paramPair.split("=");
				List<String> values = parameterMap.get(param[0]);
				if (values == null) {
					values = new ArrayList<String>();
					parameterMap.put(param[0], values);
				}
				values.add(param[1]);
			}
			baseUrl = baseUrl.substring(0, baseUrl.indexOf(QueryString.QUESTION_MARK.getKey()));
		}

	}

	public String getParameter(String name) {
		List<String> values = parameterMap.get(name);
		return ((values != null && values.size() > 0) ? values.get(0) : null);
	}

	public String[] getAllValues(String name) {
		List<String> values = parameterMap.get(name);
		return ((values != null) ? values.toArray(new String[0]) : null);
	}

	public String getHostURL() {
		return urlStr;
	}

	public Set<String> getParameterKeySet() {
		return parameterMap.keySet();
	}

	public String getQueryString() {
		return queryString;
	}

	public String getHostBaseURL() {
		return baseUrl;
	}
}

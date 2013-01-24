package com.agnie.common.helper;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.agnie.common.gwt.serverclient.client.helper.URLInfo;

public class TestURLInfoImpl implements URLInfo {

	private String						urlStr;

	private URL							url;

	private String						queryString;

	private String						baseUrl;

	private Map<String, List<String>>	parameterMap	= new HashMap<String, List<String>>();

	public TestURLInfoImpl(String path) throws MalformedURLException {
		this.urlStr = path;
		url = new URL(urlStr);
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

	public String getHost() {
		return url.getHost() + ":" + url.getPort();
	}

	public String getUTF8EncodedURL(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String decodeUTF8URL(String encodedUrl) {
		try {
			return URLDecoder.decode(encodedUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	@Override
	public String getQueryString() {
		return queryString;
	}

	@Override
	public String getHostBaseURL() {
		return null;
	}

}

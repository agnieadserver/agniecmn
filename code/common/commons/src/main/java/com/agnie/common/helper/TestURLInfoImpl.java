package com.agnie.common.helper;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Set;

import com.agnie.common.gwt.serverclient.client.helper.StringURL;
import com.agnie.common.gwt.serverclient.client.helper.URLInfo;

public class TestURLInfoImpl implements URLInfo {

	private URL			url;

	private StringURL	strUrl;

	public TestURLInfoImpl(String path) throws MalformedURLException {
		url = new URL(path);
		strUrl = new StringURL(path);
	}

	public String getParameter(String name) {
		return strUrl.getParameter(name);
	}

	public String[] getAllValues(String name) {
		return strUrl.getAllValues(name);
	}

	public String getHostURL() {
		return strUrl.getHostURL();
	}

	public Set<String> getParameterKeySet() {
		return strUrl.getParameterKeySet();
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

	public String getQueryString() {
		return strUrl.getQueryString();
	}

	public String getHostBaseURL() {
		return strUrl.getHostBaseURL();
	}

}

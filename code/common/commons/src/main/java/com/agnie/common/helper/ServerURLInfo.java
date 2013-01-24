package com.agnie.common.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.agnie.common.gwt.serverclient.client.helper.URLInfo;

public class ServerURLInfo implements URLInfo {

	private HttpServletRequest	request;

	public ServerURLInfo(HttpServletRequest request) {
		this.request = request;
	}

	public String getParameter(String name) {
		return request.getParameter(name);
	}

	public String getHostURL() {
		return request.getRequestURL().toString() + (request.getQueryString() != null ? QueryString.QUESTION_MARK.getKey() + request.getQueryString() : "");
	}

	public String[] getAllValues(String name) {
		return request.getParameterValues(name);
	}

	@SuppressWarnings("unchecked")
	public Set<String> getParameterKeySet() {
		return request.getParameterMap().keySet();
	}

	public String getUTF8EncodedURL(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getHost() {
		return request.getServerName() + ":" + request.getServerPort();
	}

	public String decodeUTF8URL(String encodedUrl) {
		try {
			return URLDecoder.decode(encodedUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public String getQueryString() {
		return request.getQueryString();
	}

	public String getHostBaseURL() {
		return request.getRequestURL().toString();
	}

}

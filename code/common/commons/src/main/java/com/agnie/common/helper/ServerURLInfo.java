package com.agnie.common.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.agnie.common.gwt.serverclient.client.helper.URLInfoBaseImpl;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ServerURLInfo extends URLInfoBaseImpl {

	private HttpServletRequest	request;
	private String				proxyServer;

	@Inject
	public ServerURLInfo(HttpServletRequest request) {
		this.request = request;
		// Given header is sent by apache proxy server. by passing original host requested.
		proxyServer = request.getHeader("X-Forwarded-Host");
	}

	public String getParameter(String name) {
		return request.getParameter(name);
	}

	public String getHostURL() {
		String url = getHostBaseURL();
		return url + (request.getQueryString() != null ? QueryString.QUESTION_MARK.getKey() + request.getQueryString() : "");
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
		if (proxyServer != null && !proxyServer.isEmpty())
			return proxyServer;
		else
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
		String url = request.getRequestURL().toString();
		if (proxyServer != null && !proxyServer.isEmpty()) {
			url = url.replace(request.getServerName() + ":" + request.getServerPort(), proxyServer);
		}
		return url;
	}

	public String getProtocol() {
		return request.getProtocol();
	}

}

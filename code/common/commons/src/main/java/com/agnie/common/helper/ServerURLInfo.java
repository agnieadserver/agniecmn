/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.agnie.common.gwt.serverclient.client.helper.URLInfoBaseImpl;
import com.google.inject.Inject;

public class ServerURLInfo extends URLInfoBaseImpl {

	private HttpServletRequest	request;
	private String				proxyServer;
	private String				proxyRequestProtocol;

	@Inject
	public ServerURLInfo(HttpServletRequest request) {
		this.request = request;
		// Given header is sent by apache proxy server. by passing original host requested.
		proxyServer = request.getHeader("X-Forwarded-Host");
		proxyRequestProtocol = request.getHeader("X-Forwarded-Scheme");
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
		if (proxyRequestProtocol != null && !proxyRequestProtocol.isEmpty()) {
			url = url.replace(request.getScheme() + "://", proxyRequestProtocol + "://");
		}
		return url;
	}

	public String getProtocol() {
		if (proxyRequestProtocol != null && !proxyRequestProtocol.isEmpty())
			return proxyRequestProtocol;
		else
			return request.getScheme();
	}

	@Override
	public String getReferrer() {
		return request.getHeader("Referer");
	}
}

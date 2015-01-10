/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.helper;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Set;

import com.agnie.common.gwt.serverclient.client.helper.StringURL;
import com.agnie.common.gwt.serverclient.client.helper.URLInfoBaseImpl;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class TestURLInfoImpl extends URLInfoBaseImpl {

	public static final String	TEST_URL	= "test-url";

	private URL					url;

	private StringURL			strUrl;

	private String				referrer;

	@Inject
	public TestURLInfoImpl(@Named(TEST_URL) String path) throws MalformedURLException {
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
		return url.getHost() + ((url.getPort() == -1 || (isSecure() && url.getPort() == 443) || ((!isSecure() && url.getPort() == 80))) ? "" : ":" + url.getPort());
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

	public String getProtocol() {
		return url.getProtocol();
	}

	@Override
	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

}

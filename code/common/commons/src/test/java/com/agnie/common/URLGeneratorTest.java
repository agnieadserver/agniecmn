/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.agnie.common.gwt.serverclient.client.helper.URLGenerator;
import com.agnie.common.gwt.serverclient.client.helper.URLInfo;
import com.agnie.common.helper.TestURLInfoImpl;

public class URLGeneratorTest {

	@Test
	public void rootContextTest() throws MalformedURLException {
		String url = "http://localhost/userapp/login.jsp";
		String expectedUrl = "http://localhost/userapp";
		URLInfo ui = new TestURLInfoImpl(url);
		Assert.assertEquals(expectedUrl, ui.getRootContextURL());
		
		url = "http://localhost:8080/userapp/login.jsp";
		expectedUrl = "http://localhost:8080/userapp";
		ui = new TestURLInfoImpl(url);
		Assert.assertEquals(expectedUrl, ui.getRootContextURL());
		
		url = "https://localhost:8080/userapp/login.jsp";
		expectedUrl = "https://localhost:8080/userapp";
		ui = new TestURLInfoImpl(url);
		Assert.assertEquals(expectedUrl, ui.getRootContextURL());
		
		url = "https://localhost/userapp/login.jsp";
		expectedUrl = "https://localhost/userapp";
		ui = new TestURLInfoImpl(url);
		Assert.assertEquals(expectedUrl, ui.getRootContextURL());

	}

	// http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&param1=value11#sample=test
	@Test
	public void simpleChangeLocaleURLTest() throws MalformedURLException {
		String url = "http://localhost:8080/useradmin/login.jsp";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?locale=en";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void nextLevelChangeLocalURLTest() throws MalformedURLException {
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&locale=en";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void thirdLevelChangeLocalURLTest() throws MalformedURLException {

		String url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&locale=en";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void forthLevelChangeLocalURLTest() throws MalformedURLException {
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&locale=en";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void fifthLevelChangeLocalURLTest() throws MalformedURLException {
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&param1=value11";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param1=value11&param2=value2&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&param1=value11&locale=en";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&param1=value11&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param1=value11&param2=value2&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param2=value2&param1=value11#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.codesvr=127.0.0.1:9997&param1=value1&param1=value11&param2=value2&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void loginUrlTest() throws MalformedURLException {
		String url = "http://localhost:8080/housingsociety.html";
		URLInfo ui = new TestURLInfoImpl(url);
		String expectedUrl = "http://localhost:8080/login.jsp?gwt.codesvr=127.0.0.1:9997&source=" + ui.getUTF8EncodedURL(url);
		URLGenerator usur = new URLGenerator();
		String changedUrl = usur.getLoginURL(ui, null, "127.0.0.1:9997");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997";
		expectedUrl = "http://localhost:8080/login.jsp?gwt.codesvr=127.0.0.1:9997&source=" + ui.getUTF8EncodedURL("http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997");
		ui = new TestURLInfoImpl(url);
		changedUrl = usur.getLoginURL(ui, null, "127.0.0.1:9997");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997&locale=mr";
		expectedUrl = "http://localhost:8080/login.jsp?gwt.codesvr=127.0.0.1:9997&locale=mr&source="
				+ ui.getUTF8EncodedURL("http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997&locale=mr");
		ui = new TestURLInfoImpl(url);
		changedUrl = usur.getLoginURL(ui, null, "127.0.0.1:9997");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997";
		expectedUrl = "http://localhost:8080/login.jsp?source=" + ui.getUTF8EncodedURL("http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997");
		ui = new TestURLInfoImpl(url);
		changedUrl = usur.getLoginURL(ui, null, null);
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997";
		expectedUrl = "http://localhost:8080/login.jsp?domain=" + URLGenerator.USERADMIN + "&source=" + ui.getUTF8EncodedURL("http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997");
		ui = new TestURLInfoImpl(url);
		changedUrl = usur.getLoginURL(ui, URLGenerator.USERADMIN, null);
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

	}

	@Test
	public void sourceUrlTest() throws MalformedURLException, UnsupportedEncodingException {
		String url = "http://localhost:8080/login.jsp?gwt.codesvr=127.0.0.1:9997&source=" + URLEncoder.encode("http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997", "UTF-8");
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator urlGen = new URLGenerator();
		String changedUrl = urlGen.getSourceUrl(ui);
		Assert.assertEquals("http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997", changedUrl);

		url = "http://localhost:8080/login.jsp?gwt.codesvr=127.0.0.1:9997&source=" + URLEncoder.encode("http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997", "UTF-8") + "&"
				+ QueryString.HISTORY_HASH.getKey() + "=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = urlGen.getSourceUrl(ui);
		Assert.assertEquals("http://localhost:8080/housingsociety.html?gwt.codesvr=127.0.0.1:9997#test", changedUrl);

		String referrer = "http://localhost:8080/housingsociety.html";
		url = "http://localhost:8080/login.jsp?gwt.codesvr=127.0.0.1:9997&Referer=" + URLEncoder.encode(referrer, "UTF-8") + "&source="
				+ URLEncoder.encode("http://localhost:8080/billing.html", "UTF-8");
		ui = new TestURLInfoImpl(url);
		changedUrl = urlGen.getSourceUrl(ui);
		Assert.assertEquals("http://localhost:8080/billing.html?source=" + URLEncoder.encode(referrer, "UTF-8"), changedUrl);

		url = "http://localhost:8080/login.jsp?gwt.codesvr=127.0.0.1:9997&Referer=" + URLEncoder.encode(referrer, "UTF-8") + "&source="
				+ URLEncoder.encode("http://localhost:8080/billing.html", "UTF-8") + "&" + QueryString.HISTORY_HASH.getKey() + "=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = urlGen.getSourceUrl(ui);
		Assert.assertEquals("http://localhost:8080/billing.html?source=" + URLEncoder.encode(referrer, "UTF-8") + "#test", changedUrl);

		url = "http://localhost:8080/login.jsp?gwt.codesvr=127.0.0.1:9997&Referer=" + URLEncoder.encode(referrer, "UTF-8") + "&source="
				+ URLEncoder.encode("http://localhost:8080/billing.html?gwt.codesvr=127.0.0.1:9997", "UTF-8") + "&" + QueryString.HISTORY_HASH.getKey() + "=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = urlGen.getSourceUrl(ui);
		Assert.assertEquals("http://localhost:8080/billing.html?gwt.codesvr=127.0.0.1:9997&source=" + URLEncoder.encode(referrer, "UTF-8") + "#test", changedUrl);

	}

}

package com.agnie.common;

import java.net.MalformedURLException;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.gwt.serverclient.client.helper.URLGenerator;
import com.agnie.common.gwt.serverclient.client.helper.URLInfo;
import com.agnie.common.helper.TestURLInfoImpl;

public class URLGeneratorTest {

	// http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11#sample=test
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
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=en";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void thirdLevelChangeLocalURLTest() throws MalformedURLException {

		String url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&gwt.server=127.0.0.1:9997&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&locale=en";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void forthLevelChangeLocalURLTest() throws MalformedURLException {
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param2=value2&gwt.server=127.0.0.1:9997&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&locale=en";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param2=value2&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param2=value2&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void fifthLevelChangeLocalURLTest() throws MalformedURLException {
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param1=value11&param2=value2&gwt.server=127.0.0.1:9997&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11&locale=en";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param1=value11&param2=value2&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param1=value11&param2=value2&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		// System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
	}

}

package com.agnie.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.agnie.common.gwt.serverclient.client.helper.URLGenerator;
import com.agnie.common.gwt.serverclient.client.helper.URLInfo;

public class URLGeneratorTest {

	// http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11#sample=test
	@Test
	public void simpleChangeLocaleURLTest() {
		String url = "http://localhost:8080/useradmin/login.jsp";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?locale=en";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void nextLevelChangeLocalURLTest() {
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);
		
		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=en";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);
		
		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
		Assert.assertEquals(expectedUrl, changedUrl);
	}
	
	@Test
	public void thirdLevelChangeLocalURLTest() {
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&gwt.server=127.0.0.1:9997&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
		
		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&locale=en";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
		
		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
	}

	@Test
	public void forthLevelChangeLocalURLTest() {
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param2=value2&gwt.server=127.0.0.1:9997&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
		
		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&locale=en";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
		
		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param2=value2&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param2=value2&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
	}
	
	@Test
	public void fifthLevelChangeLocalURLTest() {
		String url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11";
		String expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param1=value11&param2=value2&gwt.server=127.0.0.1:9997&locale=mr";
		URLInfo ui = new TestURLInfoImpl(url);
		URLGenerator ug = new URLGenerator();
		String changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
		
		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11&locale=en";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
		
		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11&locale=en#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param1=value11&param2=value2&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);

		url = "http://localhost:8080/useradmin/login.jsp?gwt.server=127.0.0.1:9997&param1=value1&param2=value2&param1=value11#sample=test";
		expectedUrl = "http://localhost:8080/useradmin/login.jsp?param1=value1&param1=value11&param2=value2&gwt.server=127.0.0.1:9997&locale=mr#sample=test";
		ui = new TestURLInfoImpl(url);
		ug = new URLGenerator();
		changedUrl = ug.getChangeLocaleURL(ui, "mr");
//		System.out.println(changedUrl);
		Assert.assertEquals(expectedUrl, changedUrl);
	}
	
}

class TestURLInfoImpl implements URLInfo {

	private String						url;

	private Map<String, List<String>>	parameterMap	= new HashMap<String, List<String>>();

	public TestURLInfoImpl(String path) {
		this.url = path;
		String location = null;
		if (path.contains(QueryString.HASH.getKey())) {
			location = path.substring(0, path.indexOf(QueryString.HASH.getKey()));
		} else {
			location = path;
		}
		if (location.contains(QueryString.QUESTION_MARK.getKey())) {
			// removed all query parameters including "?" so it removes "?gwt.server=127.0.0.1:9997"
			String queryString = location.substring(location.indexOf(QueryString.QUESTION_MARK.getKey()) + 1, location.length());
			for (String paramPair : queryString.split(QueryString.AMPERSAND.getKey())) {
				String[] param = paramPair.split("=");
				List<String> values = parameterMap.get(param[0]);
				if (values == null) {
					values = new ArrayList<String>();
					parameterMap.put(param[0], values);
				}
				values.add(param[1]);
			}
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
		return url;
	}

	public Set<String> getParameterKeySet() {
		return parameterMap.keySet();
	}

}

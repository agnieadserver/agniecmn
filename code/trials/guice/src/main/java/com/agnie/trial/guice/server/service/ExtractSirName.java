package com.agnie.trial.guice.server.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Key;
import com.google.inject.name.Names;

public class ExtractSirName implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getCookies() != null && httpRequest.getCookies().length > 0) {
			Cookie cookie = httpRequest.getCookies()[0];
			String sirname = cookie.getValue();
			httpRequest.setAttribute(Key.get(String.class, Names.named("sir-name")).toString(), sirname);
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

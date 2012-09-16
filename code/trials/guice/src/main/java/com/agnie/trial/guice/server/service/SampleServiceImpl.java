package com.agnie.trial.guice.server.service;

import com.agnie.trial.guice.client.SampleService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class SampleServiceImpl extends RemoteServiceServlet implements SampleService {

	Provider<String>	sirName;

	@Inject
	public void setSirName(@Named("sir-name") Provider<String> sirName) {
		this.sirName = sirName;
	}

	private static final long	serialVersionUID	= 1L;

	@Override
	public String getMessage(String name) {

		return "Hello " + name + ", " + sirName.get();
	}

}

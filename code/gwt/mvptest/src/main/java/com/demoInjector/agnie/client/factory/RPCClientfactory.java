package com.demoInjector.agnie.client.factory;

import com.demoInjector.agnie.client.rpc.GreetingService;
import com.demoInjector.agnie.client.rpc.GreetingServiceAsync;
import com.google.gwt.core.shared.GWT;
import com.google.inject.Singleton;

@Singleton
public class RPCClientfactory {

	GreetingServiceAsync	greetingService	= GWT.create(GreetingService.class);

	public GreetingServiceAsync getRpcService() {

		return greetingService;
	}

}

package com.agnie.gwt.client;

import com.agnie.gwt.client.injector.MVPInjector;
import com.google.gwt.core.shared.GWT;

public class MobileFactory implements PlatformFactory {
	private MVPInjector	injector	= GWT.create(MVPInjector.class);

	public MVPInjector getInjector() {
		return injector;
	}

	public ViewFactory getViewFactory() {
		return injector.getMobileViewFactory();
	}

}

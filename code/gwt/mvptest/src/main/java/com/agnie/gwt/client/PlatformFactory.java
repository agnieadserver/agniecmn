package com.agnie.gwt.client;

import com.agnie.gwt.client.injector.MVPInjector;

public interface PlatformFactory {

	MVPInjector getInjector();

	ViewFactory getViewFactory();
}

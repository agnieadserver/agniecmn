package com.agnie.gwt.client;

import com.google.gwt.core.shared.GWT;

public class SuperFactory {
	private static final SuperFactory	instance	= new SuperFactory();
	private static final ViewFactory	viewFactory	= GWT.create(ViewFactory.class);

	private SuperFactory() {
	}

	public static SuperFactory getInstance() {
		return instance;
	}

	public ViewFactory viewFactory() {
		return viewFactory;
	}
}

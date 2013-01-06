package com.agnie.gwt.common.client;

import com.google.gwt.core.client.GWT;

public interface I18 {
	static final Messages	messages	= GWT.create(Messages.class);
}

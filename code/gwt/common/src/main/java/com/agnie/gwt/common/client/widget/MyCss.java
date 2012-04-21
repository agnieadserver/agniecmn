package com.agnie.gwt.common.client.widget;

import com.google.gwt.resources.client.CssResource;

public interface MyCss extends CssResource {
	@ClassName("green-button")
	String greenButton();
	
	@ClassName("green-button-label")
	String greenButtonLabel();

	@ClassName("green-button-body")
	String greenButtonBody();

	@ClassName("green-button-left")
	String greenButtonLeft();

	@ClassName("green-button-right")
	String greenButtonRight();
}

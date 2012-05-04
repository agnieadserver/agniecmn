package com.agnie.gwt.common.client.ui;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public interface HasTabClickHandler {

	HandlerRegistration addClickHandler(String label, ClickHandler handler);

	HandlerRegistration addClickHandler(Integer tabIndex, ClickHandler handler);

}

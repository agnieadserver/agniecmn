package com.agnie.gwt.common.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class FocusWidget extends Composite implements HasClickHandlers {

	private Widget	container;

	/**
	 * Creates a new focus widget with no element. {@link #setElement(Element)} must be called before any other methods.
	 */
	protected FocusWidget() {
	}

	/**
	 * @param container
	 *            the container to set
	 */
	protected void setContainer(Widget container) {
		this.container = container;
	}

	/**
	 * @return the container
	 */
	protected Widget getContainer() {
		return container;
	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return container.addDomHandler(handler, ClickEvent.getType());
	}

}

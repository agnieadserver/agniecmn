/*******************************************************************************
 * ? 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.common.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.FocusImpl;

public abstract class FocusWidget extends Composite implements HasClickHandlers {

	private Widget	container;

	/**
	 * Creates a new focus widget with no element. {@link #setElement(Element)} must be called before any other methods.
	 */
	protected FocusWidget() {
	}

	private static final FocusImpl	impl	= FocusImpl.getFocusImplForWidget();

	/**
	 * Gets the FocusImpl instance.
	 * 
	 * @return impl
	 */
	protected static FocusImpl getFocusImpl() {
		return impl;
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

	/**
	 * Gets the tab index.
	 * 
	 * @return the tab index
	 */
	public int getTabIndex() {
		return impl.getTabIndex(getElement());
	}

	public void setFocus(boolean focused) {
		if (focused) {
			impl.focus(getElement());
		} else {
			impl.blur(getElement());
		}
	}

	public void setTabIndex(int index) {
		impl.setTabIndex(getElement(), index);
	}

	@Override
	protected void onAttach() {
		super.onAttach();

		// Accessibility: setting tab index to be 0 by default, ensuring element
		// appears in tab sequence. We must ensure that the element doesn't
		// already
		// have a tabIndex set. This is not a problem for normal widgets, but
		// when
		// a widget is used to wrap an existing static element, it can already
		// have
		// a tabIndex.
		int tabIndex = getTabIndex();
		if (-1 == tabIndex) {
			setTabIndex(0);
		}
	}
}

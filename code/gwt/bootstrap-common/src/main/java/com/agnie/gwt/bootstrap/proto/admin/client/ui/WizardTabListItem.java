/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import java.util.List;

import org.gwtbootstrap3.client.ui.base.AbstractListItem;
import org.gwtbootstrap3.client.ui.base.HasDataTarget;
import org.gwtbootstrap3.client.ui.base.HasDataToggle;
import org.gwtbootstrap3.client.ui.base.HasHref;
import org.gwtbootstrap3.client.ui.base.HasTargetHistoryToken;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 25-Aug-2014
 *
 */
public class WizardTabListItem extends AbstractListItem implements HasDataTarget, com.google.gwt.user.client.ui.HasText, HasHref, HasTargetHistoryToken, HasClickHandlers, Focusable, HasDataToggle {

	protected final WizardAnchor	anchor;

	protected WizardTabListItem() {
		anchor = new WizardAnchor();
		add(anchor, (Element) getElement());
		anchor.addStyleName(ProtoStyles.TEXT_CENTER);
		setText("");
	}

	SpanElement	ele	= null;

	@Override
	public void setText(final String text) {
		anchor.setText(text);
	}

	@Override
	public String getText() {
		return anchor.getText();
	}

	/**
	 * Creates the default widget with the desired text
	 *
	 * @param text
	 *            text for the list item
	 */
	public WizardTabListItem(final String text) {
		this();
		setText(text);
		setDataToggle(Toggle.TAB);
	}

	/**
	 * It is expected that setNumber will be called only once while being used inside uiBinder.
	 * 
	 * @param number
	 */
	public void setNumber(int number) {
		anchor.setNumber(number);
	}

	public void setProgress(boolean flag) {
		anchor.setProgress(flag);
	}

	/**
	 * Forces the tab pane associated with this list item to be shown and default fires the events
	 */
	public void showTab() {
		showTab(anchor.getElement());
	}

	@Override
	public void setDataTargetWidgets(final List<Widget> widgets) {
		anchor.setDataTargetWidgets(widgets);
	}

	@Override
	public void setDataTargetWidget(final Widget widget) {
		anchor.setDataTargetWidget(widget);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDataTarget(final String dataTarget) {
		anchor.setDataTarget(dataTarget);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataTarget() {
		return anchor.getDataTarget();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);

		// On enable/disable we need to add/remove the data toggle for it to work properly
		if (enabled) {
			setDataToggle(Toggle.TAB);
		} else {
			setDataToggle(null);
		}
	}

	public String getHTML() {
		return anchor.getHTML();
	}

	public void setHTML(final String html) {
		anchor.setHTML(html);
	}

	@Override
	public void setHref(final String href) {
		anchor.setHref(href);
	}

	@Override
	public String getHref() {
		return anchor.getHref();
	}

	@Override
	public void setTargetHistoryToken(final String targetHistoryToken) {
		anchor.setTargetHistoryToken(targetHistoryToken);
	}

	@Override
	public String getTargetHistoryToken() {
		return anchor.getTargetHistoryToken();
	}

	@Override
	public HandlerRegistration addClickHandler(final ClickHandler handler) {
		return anchor.addClickHandler(handler);
	}

	@Override
	public int getTabIndex() {
		return anchor.getTabIndex();
	}

	@Override
	public void setAccessKey(final char key) {
		anchor.setAccessKey(key);
	}

	@Override
	public void setFocus(final boolean focused) {
		anchor.setFocus(focused);
	}

	@Override
	public void setTabIndex(final int index) {
		anchor.setTabIndex(index);
	}

	@Override
	public void setDataToggle(final Toggle toggle) {
		anchor.setDataToggle(toggle);
	}

	@Override
	public Toggle getDataToggle() {
		return anchor.getDataToggle();
	}

	private native void showTab(Element e) /*-{
											$wnd.jQuery(e).tab('show');
											}-*/;

}

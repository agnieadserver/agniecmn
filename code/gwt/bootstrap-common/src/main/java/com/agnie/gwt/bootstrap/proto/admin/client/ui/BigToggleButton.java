/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.base.HasDataToggle;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.mixin.DataToggleMixin;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.constants.Toggle;
import org.gwtbootstrap3.client.ui.gwt.HTMLPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Event;

/**
 * @author Pandurang Patil 27-Aug-2014
 *
 */
public class BigToggleButton extends HTMLPanel implements HasDataToggle {
	protected DataToggleMixin<BigToggleButton>	dataToggle	= new DataToggleMixin<BigToggleButton>(this);

	public BigToggleButton(SafeHtml safeHtml) {
		super(safeHtml);
		init();
	}

	public BigToggleButton(String tag, String html) {
		super(tag, html);
		init();
	}

	public BigToggleButton(String html) {
		super(html);
		init();
	}

	private void init() {
		this.sinkEvents(Event.ONCLICK);
		setStyleName(ProtoStyles.AG_BIG_BUTTON);
		addStyleName(ProtoStyles.BLUE);
		addStyleName(Styles.BTN);
		addStyleName(Styles.BTN_BLOCK);
	}

	public void setActive(boolean flag) {
		StyleHelper.toggleStyleName(this, flag, Styles.ACTIVE);
	}

	public boolean isActive() {
		return StyleHelper.containsStyle(Styles.ACTIVE, getStyleName());
	}

	@Override
	public void setDataToggle(Toggle toggle) {
		dataToggle.setDataToggle(toggle);
	}

	@Override
	public Toggle getDataToggle() {
		return dataToggle.getDataToggle();
	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return this.addHandler(handler, ClickEvent.getType());
	}

}

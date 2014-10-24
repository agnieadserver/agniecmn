/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.base.HasActive;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.mixin.OnOffMixin;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 28-Aug-2014
 *
 */
public class ToggleSwitch extends Composite implements HasActive {

	private static ToggleSwitchUiBinder	uiBinder	= GWT.create(ToggleSwitchUiBinder.class);

	interface ToggleSwitchUiBinder extends UiBinder<Widget, ToggleSwitch> {
	}

	private OnOffMixin<HTMLPanel>	onoffmixin;

	@UiField
	FocusPanel						mainpanel;

	@UiField
	HTMLPanel						onOffPanel;

	ToggleSwitchSize				lastSize;

	ToggleSwitchType				lastType;

	boolean							status;

	public ToggleSwitch() {
		initWidget(uiBinder.createAndBindUi(this));
		onoffmixin = new OnOffMixin<HTMLPanel>(onOffPanel);
		status = false;
		setActive(status);
		mainpanel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				status = !status;
				setActive(status);
			}
		});
	}

	@Override
	public void setActive(boolean active) {
		status = active;
		onoffmixin.setActive(active);
	}

	@Override
	public boolean isActive() {
		return onoffmixin.isActive();
	}

	public void setSize(final ToggleSwitchSize size) {
		if (lastSize != null) {
			mainpanel.removeStyleName(lastSize.getSize());
		}
		mainpanel.addStyleName(size.getSize());
		lastSize = size;
	}

	public void setType(final ToggleSwitchType type) {
		if (lastType != null) {
			mainpanel.removeStyleName(lastType.getType());
		}
		mainpanel.addStyleName(type.getType());
		lastType = type;
	}

}

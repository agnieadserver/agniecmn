/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 10-Jan-2015
 *
 */
public class EntierScreenFrontPanel extends Composite {

	private static EntierScreenFrontPanelUiBinder	uiBinder	= GWT.create(EntierScreenFrontPanelUiBinder.class);

	interface EntierScreenFrontPanelUiBinder extends UiBinder<Widget, EntierScreenFrontPanel> {
	}

	HTMLPanel	mainPanel;
	@UiField
	SimplePanel	content;

	@UiConstructor
	public EntierScreenFrontPanel(String width) {
		mainPanel = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(mainPanel);
		content.setWidth(width);
	}

	public void show() {
		RootPanel.get().add(this);
	}

	public void hide() {
		removeFromParent();
	}

	public void addContent(Widget widget) {
		content.add(widget);
	}

	public void removeContent(Widget widget) {
		content.remove(widget);
	}
}

/*******************************************************************************
 * ? 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget;

import com.agnie.gwt.common.client.I18;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Two state SlidePanel as ChildWidget for SlideButton.
 * 
 *
 */
public class SlideButtonScale extends Composite implements HasMouseDownHandlers {
	private static SlideButtonScaleResources	resource	= SlideButtonScaleResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, SlideButtonScale> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	private HTMLPanel			container;
	@UiField
	SpanElement					application;
	@UiField
	SpanElement					context;

	public SlideButtonScale() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
		setLeftTitle(I18.messages.application());
		setRightTitle(I18.messages.context());
	}

	public void setLeftTitle(String title) {
		this.application.setInnerText(title);
	}

	public void setRightTitle(String title) {
		this.context.setInnerText(title);
	}

	public void setWidth(String width) {
		this.container.setWidth(width);
	}

	public void setHeight(String height) {
		this.container.setHeight(height);
	}

	public static SlideButtonScaleResources getResources() {
		return resource;
	}

	@Override
	public Widget asWidget() {
		return this.asWidget();
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return addDomHandler(handler, MouseDownEvent.getType());
	}

}

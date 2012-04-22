package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class GreenButton extends Composite implements HasClickHandlers {

	private static GreenButtonResources	resource	= GWT.create(GreenButtonResources.class);

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, GreenButton> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	protected Label				label;

	@UiField
	protected HTMLPanel			panel;

	public GreenButton(String label) {
		initWidget(uiBinder.createAndBindUi(this));
		this.label.setText(label);
		this.label.setStyleName(resource.css().greenButtonLabel());
	}

	@UiFactory
	/* this method could be static if you like */
	public static GreenButtonResources getResources() {
		return resource;
	}

	public void setText(String text) {
		label.setText(text);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return panel.addDomHandler(handler, ClickEvent.getType());
	}

}

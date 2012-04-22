package com.agnie.gwt.common.client.widget;

import com.agnie.gwt.common.client.ui.FocusWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class GreenButton extends FocusWidget {

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
		setContainer(panel);
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

}

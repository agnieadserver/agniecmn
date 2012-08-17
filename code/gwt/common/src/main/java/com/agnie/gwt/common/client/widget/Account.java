package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * Account widget.To show users account detail.
 * 
 */
public class Account extends Composite {
	private static AccountResources	resource	= AccountResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, Account> {
	}

	private static MyUiBinder	uiBinder		= GWT.create(MyUiBinder.class);

	@UiField
	SpanElement					accTitle;

	@UiField
	SpanElement					name;

	@UiField
	Image						accUserImg;
	@UiField
	Anchor						changePass;
	@UiField
	Anchor						modify;
	@UiField
	Anchor						logout;

	protected HTMLPanel			container;
	protected boolean			visibleDropPan	= false;

	public Account() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
		setUserImageResource(resource.person());
	}

	public void setAccName(String title) {
		Window.alert("title length"+title.length());
		accTitle.setInnerText(title);
		name.setInnerText(title);
	}
	public void setUserImageResource(ImageResource resource){
		accUserImg.setResource(resource);
	}
	public void setHeight(String height) {
		container.setHeight(height);
	}

	public void setSize(String width, String height) {
		container.setSize(width, height);
	}

	public static AccountResources getResources() {
		return resource;
	}

}

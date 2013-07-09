package com.agnie.gwt.common.client.widget;

import com.agnie.common.gwt.serverclient.client.dto.UserAccount;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
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
	HTMLPanel					accTitlePan;
	@UiField
	SpanElement					accTitle;
	@UiField
	FocusPanel					accDropPan;
	@UiField
	DivElement					accDropPanBody;
	@UiField
	Image						accImg;
	@UiField
	Image						accUserImg;
	@UiField
	HTMLPanel					changePass;
	@UiField
	HTMLPanel					modify;
	@UiField
	HTMLPanel					logout;

	protected HTMLPanel			container;
	protected boolean			visibleDropPan	= false;

	public Account() {
		this(resource.css().accPan());
	}

	CancelClickHandler	cancleClikInstance	= new CancelClickHandler();
	UserAccount			userAcc;

	public Account(String styleClassName) {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		container.addStyleName(styleClassName);
		initWidget(container);

		accImg.setUrl(GWT.getModuleBaseURL() + "images/transparent.png");
		setUserImageResource(resource.person());
		accTitlePan.sinkEvents(Event.ONCLICK);// 'enables' click events for the
												// HtmlPanel
		accTitlePan.addHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (visibleDropPan) {
					hide();
				} else {
					show();
				}
			}
		}, ClickEvent.getType());

	}

	@UiHandler("accDropPan")
	void handleBlurDiv(BlurEvent be) {
		this.hide();
	}

	private void setAccName(String title) {
		accTitle.setInnerText(title);
	}

	private void setUserImageResource(ImageResource resource) {
		accUserImg.setResource(resource);
	}

	private void setUserImageUrl(String imageUrl) {
		accUserImg.setUrl(imageUrl);
	}

	public void setUserAcc(UserAccount userAcc) {
		if (userAcc != null) {
			this.userAcc = userAcc;
			setAccName(userAcc.getFirstName() + " " + userAcc.getLastName());
			if (userAcc.getUserImgUrl() != null && !(userAcc.getUserImgUrl().isEmpty())) {
				setUserImageUrl(userAcc.getUserImgUrl());
			} else {
				setUserImageResource(resource.person());
			}
		}
	}

	private void hide() {
		accDropPan.removeStyleName(resource.css().accDropPanVisible());
		visibleDropPan = false;
	}

	private void show() {
		accDropPan.addStyleName(resource.css().accDropPanVisible());
		accDropPan.setFocus(true);
		visibleDropPan = true;
	}

	public void addChangePassClickHandler(ClickHandler handler) {
		changePass.sinkEvents(Event.ONCLICK);
		changePass.addHandler(handler, ClickEvent.getType());
		changePass.addHandler(cancleClikInstance, ClickEvent.getType());
	}

	public void addModifyClickHandler(ClickHandler handler) {
		modify.sinkEvents(Event.ONCLICK);
		modify.addHandler(handler, ClickEvent.getType());
		modify.addHandler(cancleClikInstance, ClickEvent.getType());
	}

	public void addLogoutClickHandler(ClickHandler handler) {
		logout.sinkEvents(Event.ONCLICK);
		logout.addHandler(handler, ClickEvent.getType());
		logout.addHandler(cancleClikInstance, ClickEvent.getType());
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

	private class CancelClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			hide();
		}
	}

}

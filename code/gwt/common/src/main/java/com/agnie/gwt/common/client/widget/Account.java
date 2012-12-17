package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
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
	HTMLPanel					accTitlePan;
	@UiField
	SpanElement					accTitle;
	@UiField
	DivElement					accDropPan;
	@UiField
	Image		                accImg;
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
		this(resource.css().accPan());
	}

	public Account(String styleClassName) {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		container.addStyleName(styleClassName);
		initWidget(container);
		setUserImageResource(resource.person());
		
		accTitlePan.sinkEvents(Event.ONCLICK);//'enables' click events for the HtmlPanel 
		accTitlePan.addHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(visibleDropPan){
					accDropPan.removeClassName(resource.css().accDropPanVisible());
					visibleDropPan=false;
				}else{
				accDropPan.addClassName(resource.css().accDropPanVisible());
				visibleDropPan=true;
				}
			}
		}, ClickEvent.getType());
	}
	
	public void addChangePassClickHandler(ClickHandler handler){
		changePass.addClickHandler(handler);
	}
	
	public void addModifyClickHandler(ClickHandler handler){
		modify.addClickHandler(handler);
	}
	
	public void addLogoutClickHandler(ClickHandler handler){
		logout.addClickHandler(handler);
	}
	
	public void setAccName(String title) {
		accTitle.setInnerText(title);
	}

	public void setUserImageResource(ImageResource resource) {
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

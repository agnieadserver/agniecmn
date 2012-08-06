package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Search Box
 * 
 */
public class SearchBox extends Composite {
	private static SearchBoxResources	resource	= SearchBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, SearchBox> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	LabelTextBox				search;
	@UiField
	HTMLPanel					inputWidgetContainer;
	
	Widget						widget;
	protected HTMLPanel			container;

	public SearchBox() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
	}
	
	
	public void setLabel(String label){
		search.setLabel(label);
	}
	public void addInputWidgetContainer(Widget widget) {
		this.widget=widget;
		inputWidgetContainer.add(widget);
	}

	public void setHeight(String height) {
		container.setHeight(height);
	}

	public void setSize(String width, String height) {
		container.setSize(width, height);
	}

	public String getValue() {
		return search.getValue();
	}

	public static SearchBoxResources getResources() {
		return resource;
	}

}

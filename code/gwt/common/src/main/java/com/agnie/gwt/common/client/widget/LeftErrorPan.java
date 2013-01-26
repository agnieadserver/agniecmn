package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * LeftErrorPan widget.
 * 
 */
public class LeftErrorPan extends Composite {
	private static LeftErrorPanResources	resource	= LeftErrorPanResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, LeftErrorPan> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	HTMLPanel					errorPanBody;

	@UiField
	Anchor						img;

	@UiField
	HTML						message;

	@UiField
	Anchor						close;

	protected HTMLPanel			container;

	public LeftErrorPan() {
		this(resource.css().leftErrorPan());
	}

	public LeftErrorPan(String styleClassName) {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		container.addStyleName(styleClassName);
		initWidget(container);

	}

	public static LeftErrorPanResources getResources() {
		return resource;
	}

}

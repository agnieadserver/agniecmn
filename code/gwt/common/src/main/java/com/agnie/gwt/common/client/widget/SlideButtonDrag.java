package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * DragElement for SlideButton widget.
 *
 */
public class SlideButtonDrag extends Composite implements HasMouseMoveHandlers,HasMouseDownHandlers,HasMouseUpHandlers{
	private static SlideButtonDragResources	resource	= SlideButtonDragResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, SlideButtonDrag> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	private HTMLPanel			container;

	public SlideButtonDrag() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
	}

	public void setWidth(String width) {
		this.container.setWidth(width);
	}

	public void setHeight(String height) {
		this.container.setHeight(height);
	}

	public static SlideButtonDragResources getResources() {
		return resource;
	}
	
	@Override
	public Widget asWidget(){
		return this.asWidget();
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler,MouseMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return addDomHandler(handler, MouseDownEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return addDomHandler(handler,MouseUpEvent.getType());
	}

}

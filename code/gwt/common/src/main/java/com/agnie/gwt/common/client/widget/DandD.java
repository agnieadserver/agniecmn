package com.agnie.gwt.common.client.widget;

import com.agnie.gwt.common.client.widget.CloseBtn.MyUiBinder;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class DandD extends Composite {
	private static DandDResources	resource	= DandDResources.INSTANCE;
	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, DandD> {
	}

	private static MyUiBinder		uiBinder	= GWT.create(MyUiBinder.class);
	private AbsolutePanel			scale;
	private PickupDragController	dragController;
	protected HTMLPanel				container;

	public DandD() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);

	}

	public void setScale(AbsolutePanel scale) {
		this.scale = scale;
		dragController = new PickupDragController(scale, true);
		this.container.add(scale);
	}

	public void addDragWidget(final Widget  drag, int leftBorderOfDrag, int topBorderOfDrag) {
		this.scale.add(drag, -leftBorderOfDrag, -topBorderOfDrag);
		dragController.makeDraggable(drag);
	}
}

package com.agnie.gwt.common.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
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

	private static MyUiBinder			uiBinder			= GWT.create(MyUiBinder.class);
	private AbsolutePanel				scale				= new AbsolutePanel();
	private PickupDragController		dragController;
	protected HTMLPanel					container;
	protected HandlerManager			handlerManager		= new HandlerManager(this);
	protected DandDDrag					sbDrag				= new DandDDrag();
	int									currentValue		= 0;
	private List<HandlerRegistration>	valueChangeHandlers	= new ArrayList<HandlerRegistration>();

	public DandD() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
		scale.addStyleName(resource.css().slideButtonScale());
		scale.sinkEvents(Event.ONCLICK);
		scale.addHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (95 > event.getRelativeX(DandD.this.scale.getElement())) {
					DandD.this.setDragPosition(Position.ZERO);
				} else {
					if (95 < event.getRelativeX(DandD.this.scale.getElement())) {
						DandD.this.setDragPosition(Position.ONE);
					}
				}
			}
		}, ClickEvent.getType());
		setScale(scale);
		addDragWidget(sbDrag, 2, 2);
	}

	public void setScale(AbsolutePanel scale) {
		this.scale = scale;
		dragController = new PickupDragController(scale, true);
		this.container.add(scale);
	}

	public void addDragWidget(final HasAllMouseHandlers drag, int leftBorderOfDrag, int topBorderOfDrag) {
		this.scale.add((Widget) drag, -leftBorderOfDrag, -topBorderOfDrag);
		dragController.makeDraggable((Widget) drag);

		drag.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				int x = ((Widget) drag).getAbsoluteLeft();
				int scaleX = scale.getAbsoluteLeft();
				int scaleHFWidth = scale.getOffsetWidth() / 2;
				int dragHFWidth = ((Widget) drag).getOffsetWidth() / 2;
				int cal = scaleX + scaleHFWidth - dragHFWidth;
				int leftPos = -2;

				if (x < cal) {
					leftPos = -2;
					currentValue = 0;
				} else {
					leftPos = ((Widget) drag).getOffsetWidth() - 6;
					currentValue = 1;
				}
				((Widget) drag).getElement().setAttribute("style", "position: relative;" + "left:" + leftPos + "px ;");
				handlerManager.fireEvent(new BarValueChangedEvent(currentValue));
			}
		});
	}

	public int getDragPosition() {
		return currentValue;
	}

	public static enum Position {
		ZERO(0), ONE(1);
		private int	key;

		private Position(int key) {
			this.key = key;
		}

		/**
		 * @return the key
		 */
		public int getKey() {
			return key;
		}
	}

	// To set dragPosition manually
	public void setDragPosition(final Position position) {
		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {
				if (0 == position.getKey()) {
					currentValue = 0;
					(DandD.this.sbDrag).getElement().setAttribute("style", "position: relative;" + "left:" + -2 + "px ;" + "top:" + -2 + "px ;");
					handlerManager.fireEvent(new BarValueChangedEvent(currentValue));
				}
				if (1 == position.getKey()) {
					int leftPos = (DandD.this.sbDrag).getOffsetWidth() - 6;
					(DandD.this.sbDrag).getElement().setAttribute("style", "position: relative;" + "left:" + leftPos + "px ;" + "top:" + -2 + "px ;");
					currentValue = 1;
					handlerManager.fireEvent(new BarValueChangedEvent(currentValue));
				}
			}
		});
	}

	public static interface BarValueChangedHandler extends EventHandler {
		void onBarValueChanged(BarValueChangedEvent event);
	}

	public static class BarValueChangedEvent extends GwtEvent<BarValueChangedHandler> {
		public static Type<BarValueChangedHandler>	TYPE	= new Type<BarValueChangedHandler>();

		int											value;

		public BarValueChangedEvent(int position) {
			this.value = position;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int position) {
			this.value = position;
		}

		@Override
		public Type<BarValueChangedHandler> getAssociatedType() {
			return TYPE;
		}

		@Override
		protected void dispatch(BarValueChangedHandler handler) {
			handler.onBarValueChanged(this);
		}
	}

	public HandlerRegistration addBarValueChangedHandler(BarValueChangedHandler barValueChangedHandler) {
		HandlerRegistration hr = handlerManager.addHandler(BarValueChangedEvent.TYPE, barValueChangedHandler);
		valueChangeHandlers.add(hr);
		return hr;
	}

	/**
	 * To clear BarValueChangeHandlers for slideButton.
	 */
	public void clearBarValueChangeHandlers() {
		for (HandlerRegistration vch : valueChangeHandlers) {
			vch.removeHandler();
		}
		valueChangeHandlers.clear();
	}

	public List<HandlerRegistration> getValueChangeHandlerRegsList() {
		return this.valueChangeHandlers;
	}

}

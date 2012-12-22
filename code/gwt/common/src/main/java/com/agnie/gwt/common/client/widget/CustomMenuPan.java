package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * CustomMenuPan widget.
 * 
 */
public class CustomMenuPan extends Composite implements ContextMenuHandler {
	private static CustomMenuPanResources	resource	= CustomMenuPanResources.INSTANCE;
	private MenuPan							contextMenu	= new MenuPan();
	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, CustomMenuPan> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	SpanElement					title;
	
	public HTMLPanel					container;

	public CustomMenuPan() {
		this(resource.css().customMenuPan());
	}

	MenuBar	popupMenuBar1To3	= new MenuBar(true);
	MenuBar	popupMenuBar4To5	= new MenuBar(true);
	Account	acc1			= new Account();
	Account	acc2			= new Account();
	Account	acc3			= new Account();
	Account	acc4			= new Account();
	Account	acc5			= new Account();

	public CustomMenuPan(String styleClassName) {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		container.addStyleName(styleClassName);
		initWidget(container);
		this.setCustomMenuTitle("Right click Test !");
		/* ContextMenu related code starts here */
		this.contextMenu.hide();
		this.contextMenu.setAutoHideEnabled(true);
		addDomHandler(this, ContextMenuEvent.getType());

		Command c1 = new Command() {

			@Override
			public void execute() {
				Window.alert("Command 1 executed.");
				acc1.setAccName("Account1");
				container.add(acc1);
			}
		};
		Command c2 = new Command() {

			@Override
			public void execute() {
				Window.alert("Command 2 executed.");
				acc2.setAccName("Account2");
				container.add(acc2);
			}
		};
		Command c3 = new Command() {

			@Override
			public void execute() {
				Window.alert("Command 3 executed.");
				acc3.setAccName("Account3");
				container.add(acc3);
			}
		};
		Command c4 = new Command() {

			@Override
			public void execute() {
				Window.alert("Command 4 executed.");
				acc4.setAccName("Account4");
				container.add(acc4);
			}
		};
		Command c5 = new Command() {

			@Override
			public void execute() {
				Window.alert("Command 5 executed.");
				acc5.setAccName("Account5");
				container.add(acc5);
			}
		};
		MenuItem fItem = new MenuItem("Command1", true, c1);
		MenuItem sItem = new MenuItem("Command2 ", true, c2);
		MenuItem tItem = new MenuItem("Command3 ", true, c3);
		MenuItem frItem = new MenuItem("Command4 ", true, c4);
		MenuItem fiItem = new MenuItem("Command5 ", true, c5);

		popupMenuBar1To3.addItem(fItem);
		popupMenuBar1To3.addItem(sItem);
		popupMenuBar1To3.addItem(tItem);
		
		popupMenuBar4To5.addItem(frItem);
		popupMenuBar4To5.addItem(fiItem);

		if (contextMenu != null) {
			Window.alert("Not null");
		} else {
			Window.alert(" null");
		}

		popupMenuBar1To3.setVisible(true);
		popupMenuBar4To5.setVisible(true);
		contextMenu.container.add(popupMenuBar1To3);
		contextMenu.addMenuSeparator();
		contextMenu.container.add(popupMenuBar4To5);
	}

	public void onContextMenu(ContextMenuEvent event) {
		// stop the browser from opening the context menu
		event.preventDefault();
		event.stopPropagation();

		this.contextMenu.setPopupPosition(event.getNativeEvent().getClientX(), event.getNativeEvent().getClientY());
		this.contextMenu.show();
	}

	public void setCustomMenuTitle(String title) {
		this.title.setInnerText(title);
	}

}

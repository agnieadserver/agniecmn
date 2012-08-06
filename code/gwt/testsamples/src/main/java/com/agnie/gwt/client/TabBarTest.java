package com.agnie.gwt.client;

import com.agnie.gwt.common.client.widget.TabBar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TabBarTest extends Composite {
	private TabBar			tab		= new TabBar();
	private VerticalPanel	panel	= new VerticalPanel();

	public TabBarTest() {
		tab.addTab("Home");
		tab.addTab("User Manager");
		tab.addTab("Accounting");
		tab.addTab("Society Manager");
		for (int index = 0; index < tab.getTabCount(); index++) {
			tab.getTab(index).addClickHandler(new TabClickHandler(index));
		}
		tab.selectTab(0);
		Button removeAll = new Button("Remove All");
		removeAll.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				tab.removeAll();
			}
		});
		panel.add(tab);
		panel.add(removeAll);

		initWidget(panel);
	}

	private class TabClickHandler implements ClickHandler {

		private int	index;

		public TabClickHandler(int index) {
			this.index = index;
		}

		public void onClick(ClickEvent event) {
			tab.selectTab(index);
		}

	}
}

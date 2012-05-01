package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class MainTabPanel extends Composite {

	private SimplePanel	container;
	private HTMLPanel	innerContainer;

	public MainTabPanel() {
		container = new SimplePanel();
		innerContainer = new HTMLPanel("");
		initWidget(container);
		container.setStyleName("tabBox");
		innerContainer.setStyleName("tabs");
		container.add(innerContainer);
	}

	public void addTab(String label) {
		final SimplePanel tab = new SimplePanel();
		Anchor anchTab = new Anchor(label);
		anchTab.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				tab.setStyleName("selected");
			}
		});
		// anchTab.setHref("#");
		tab.add(anchTab);
		innerContainer.add(tab);
	}

}

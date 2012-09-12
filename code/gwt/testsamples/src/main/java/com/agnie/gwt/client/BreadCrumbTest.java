package com.agnie.gwt.client;

import com.agnie.gwt.common.client.widget.BreadCrumbPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;

public class BreadCrumbTest extends Composite {

	public BreadCrumbTest() {
		BreadCrumbPanel panel = new BreadCrumbPanel();
		panel.addBreadCrumb("Societies");
		panel.addBreadCrumb("Dashboard");
		panel.addBreadCrumb("Account Summary");
		panel.getBreadCrumb(0).addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Window.alert("This works damit");
			}
		});

		initWidget(panel);
	}
}

package com.agnie.gwt.client;

import com.agnie.gwt.client.ui.CreateView;
import com.agnie.gwt.client.ui.ListView;
import com.agnie.gwt.client.ui.MobileListView;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class MobileViewFactory implements ViewFactory {
	private static HTMLPanel	mainPanel;
	private static ListView		listView	= new MobileListView();

	public HTMLPanel getMainContentPanel() {
		if (mainPanel == null) {
			mainPanel = new HTMLPanel("");
			mainPanel.setStyleName("mobile-main-panel");
			RootPanel.get().add(mainPanel);
		}
		return mainPanel;
	}

	public ListView getListView() {
		return listView;
	}

	public CreateView getCreateView() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMainHeading() {
		// TODO Auto-generated method stub
		return "Mobile application";
	}
}

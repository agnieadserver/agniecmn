package com.agnie.gwt.client;

import com.agnie.gwt.client.ui.CreateView;
import com.agnie.gwt.client.ui.DeskListView;
import com.agnie.gwt.client.ui.ListView;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class DesktopViewFactory implements ViewFactory {

	private static HTMLPanel	mainPanel;
	private static ListView		listView	= new DeskListView();

	public HTMLPanel getMainContentPanel() {
		if (mainPanel == null) {
			mainPanel = new HTMLPanel("");
			mainPanel.setStyleName("desktop-main-panel");
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
		return "Destop application";
	}

}

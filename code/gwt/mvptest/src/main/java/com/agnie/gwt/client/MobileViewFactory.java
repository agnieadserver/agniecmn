package com.agnie.gwt.client;

import com.agnie.gwt.client.injector.MVPModule;
import com.agnie.gwt.client.ui.CreateView;
import com.agnie.gwt.client.ui.ListView;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class MobileViewFactory implements ViewFactory {
	private HTMLPanel	mainPanel;
	@Inject
	@Named(MVPModule.MOBILE)
	private ListView	listView;

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

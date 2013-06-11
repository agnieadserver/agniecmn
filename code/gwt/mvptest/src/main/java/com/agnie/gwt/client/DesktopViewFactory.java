package com.agnie.gwt.client;

import com.agnie.gwt.client.injector.MVPInjector;
import com.agnie.gwt.client.injector.MVPModule;
import com.agnie.gwt.client.ui.CreateView;
import com.agnie.gwt.client.ui.ListView;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class DesktopViewFactory implements ViewFactory {
	private MVPInjector			injector	= GWT.create(MVPInjector.class);
	private boolean				initalised	= false;
	private static HTMLPanel	mainPanel;
	@Inject
	@Named(MVPModule.DESKTOP)
	private ListView			listView;

	public HTMLPanel getMainContentPanel() {
		if (mainPanel == null) {
			mainPanel = new HTMLPanel("");
			mainPanel.setStyleName("desktop-main-panel");
			RootPanel.get().add(mainPanel);
		}
		return mainPanel;
	}

	private void init() {
		injector.injectMembers(this);
		initalised = true;
	}

	public ListView getListView() {
		if (!initalised) {
			init();
		}
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

package com.agnie.gwt.client;

import com.agnie.gwt.client.ui.CreateView;
import com.agnie.gwt.client.ui.ListView;
import com.google.gwt.user.client.ui.HTMLPanel;

public interface ViewFactory {

	HTMLPanel getMainContentPanel();

	ListView getListView();

	CreateView getCreateView();

	String getMainHeading();
}

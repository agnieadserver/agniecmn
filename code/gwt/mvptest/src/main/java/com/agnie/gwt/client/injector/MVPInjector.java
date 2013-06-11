package com.agnie.gwt.client.injector;

import com.agnie.gwt.client.DesktopViewFactory;
import com.agnie.gwt.client.MobileViewFactory;
import com.agnie.gwt.client.SampleAppController;
import com.agnie.gwt.client.TabletViewFactory;
import com.agnie.gwt.client.ViewFactory;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(MVPModule.class)
public interface MVPInjector extends Ginjector {

	DesktopViewFactory getDesktopViewFactory();

	MobileViewFactory getMobileViewFactory();

	TabletViewFactory getTabletViewFactory();

	SampleAppController getAppController();

	void injectMembers(ViewFactory factory);

}

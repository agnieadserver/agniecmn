package com.agnie.gwt.client.injector;

import com.agnie.gwt.client.presenter.ListPresenter;
import com.agnie.gwt.client.ui.DeskListView;
import com.agnie.gwt.client.ui.ListView;
import com.agnie.gwt.client.ui.ListView.Presenter;
import com.agnie.gwt.client.ui.MobileListView;
import com.agnie.gwt.client.ui.TabletListView;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.name.Names;

public class MVPModule extends AbstractGinModule {

	public static final String	DESKTOP	= "desktop";
	public static final String	MOBILE	= "mobile";
	public static final String	TABLET	= "tablet";

	@Override
	protected void configure() {
		// bind(SampleAppController.class).asEagerSingleton();
		bind(Presenter.class).to(ListPresenter.class);
		bind(ListView.class).annotatedWith(Names.named(DESKTOP)).to(DeskListView.class);
		bind(ListView.class).annotatedWith(Names.named(MOBILE)).to(MobileListView.class);
		bind(ListView.class).annotatedWith(Names.named(TABLET)).to(TabletListView.class);

	}
}

package com.agnie.gwt.client.injector;

import com.agnie.gwt.client.SampleAppController;
import com.agnie.gwt.client.ui.DeskListView;
import com.agnie.gwt.client.ui.ListView;
import com.agnie.gwt.client.ui.MobileListView;
import com.agnie.gwt.client.ui.TabletListView;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.name.Names;

public class MVPModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(SampleAppController.class).asEagerSingleton();
		bind(ListView.class).annotatedWith(Names.named("desktop")).to(DeskListView.class);
		bind(ListView.class).annotatedWith(Names.named("mobile")).to(MobileListView.class);
		bind(ListView.class).annotatedWith(Names.named("tablet")).to(TabletListView.class);

	}
}

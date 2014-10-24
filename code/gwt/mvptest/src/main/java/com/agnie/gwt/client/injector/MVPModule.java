/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
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
	

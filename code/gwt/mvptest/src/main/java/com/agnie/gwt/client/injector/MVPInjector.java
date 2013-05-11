package com.agnie.gwt.client.injector;

import com.agnie.gwt.client.SampleAppController;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(MVPModule.class)
public interface MVPInjector extends Ginjector {

	SampleAppController getAppController();

}

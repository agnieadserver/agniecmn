package com.demoInjector.agnie.client.injector;

import com.demoInjector.agnie.client.DemoController;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(ModuleOne.class)
public interface DemoInjector extends Ginjector {

	DemoController getApController();

}

package com.agnie.trial.guice.client;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainClass {

	public static void main(String[] args) {

		Injector injector = Guice.createInjector(new ModuleOne());

		DependencyTwo two1 = injector.getInstance(DependencyTwo.class);

		DependencyThree two2 = injector.getInstance(DependencyThree.class);

		System.out.println(two1);

		System.out.println(two2);

	}
}

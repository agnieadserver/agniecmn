package com.agnie.trial.guice.server.access;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

public class Test {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new AbstractModule() {

			@Override
			protected void configure() {
				bind(TestDepdency.class).annotatedWith(Foo.class).to(FooTestDependencyImpl.class);
				bind(TestDepdency.class).annotatedWith(Bar.class).to(BarTestDependencyImpl.class);
			}
		});

		TestDepdency foo = injector.getInstance(Key.get(TestDepdency.class, Foo.class));
		System.out.println(foo.getValue());
		TestDepdency bar = injector.getInstance(Key.get(TestDepdency.class, Bar.class));
		System.out.println(bar.getValue());
	}
}

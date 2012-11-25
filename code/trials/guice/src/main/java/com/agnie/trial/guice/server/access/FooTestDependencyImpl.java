package com.agnie.trial.guice.server.access;

public class FooTestDependencyImpl implements TestDepdency {

	@Override
	public String getValue() {
		return "Foo";
	}
}

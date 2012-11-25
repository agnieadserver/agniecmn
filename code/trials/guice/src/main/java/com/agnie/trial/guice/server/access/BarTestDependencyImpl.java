package com.agnie.trial.guice.server.access;

public class BarTestDependencyImpl implements TestDepdency {

	@Override
	public String getValue() {
		return "Bar";
	}

}

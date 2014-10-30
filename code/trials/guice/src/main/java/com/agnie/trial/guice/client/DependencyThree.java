package com.agnie.trial.guice.client;

import com.google.inject.Inject;

public class DependencyThree {

	@Inject
	DependencyTwo two = null;

	@Override
	public String toString() {
		return "DependencyThree [two=" + two + "]";
	}

}

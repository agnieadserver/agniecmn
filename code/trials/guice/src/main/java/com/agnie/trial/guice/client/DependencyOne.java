package com.agnie.trial.guice.client;

import java.util.UUID;

public class DependencyOne {

	private String UniQueID = null;

	public DependencyOne() {
		this.UniQueID = UUID.randomUUID().toString();
	}

	@Override
	public String toString() {
		return "DependencyOne [UniQueID=" + UniQueID + "]";
	}

}

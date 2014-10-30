package com.agnie.trial.guice.client;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class DependencyTwo {

	@Inject
	private DependencyOne	dependecyOne	= null;
	@Inject
	@Named("other")
	private String			otherMessage;

	private String			message;

	@Inject
	public DependencyTwo(@Named("excel") String strMessage) {

		this.message = strMessage;
	}

	@Override
	public String toString() {
		return "DependencyTwo [dependecyOne=" + dependecyOne + ", otherMessage=" + otherMessage + ", message=" + message + "]";
	}

}

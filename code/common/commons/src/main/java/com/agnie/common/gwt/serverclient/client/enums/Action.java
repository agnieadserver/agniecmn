package com.agnie.common.gwt.serverclient.client.enums;

public enum Action {
	LIST("list"), VIEW("view"), NEW("new"), EDIT("edit");
	private String	action;

	private Action(String action) {
		this.action = action;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
}
package com.agnie.gwt.common.client.mvp;

public abstract interface Presenter {
	public abstract boolean go();

	public abstract void postRender(MainView view);

	public boolean checkPermission(String permission);
}

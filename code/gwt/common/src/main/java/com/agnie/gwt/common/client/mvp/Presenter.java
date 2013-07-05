package com.agnie.gwt.common.client.mvp;

public abstract interface Presenter {
	public abstract boolean go();

	public abstract void postRender();

	public boolean checkPermission(String permission);
}

package com.agnie.gwt.common.client.mvp;

public abstract interface Presenter {
	public abstract void go();
	
	public abstract void postRender(MainView view);
}

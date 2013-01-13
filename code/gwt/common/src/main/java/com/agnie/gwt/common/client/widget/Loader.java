package com.agnie.gwt.common.client.widget;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class Loader extends PopupPanel {
	static {
		LoaderResources.INSTANCE.css().ensureInjected();
	}

	public Loader() {
		this(LoaderResources.INSTANCE.defaultLoader());
	}

	public Loader(ImageResource resource) {
		Image img = new Image(resource);
		setAnimationEnabled(true);
		setWidget(img);
		setGlassStyleName(LoaderResources.INSTANCE.css().grayout());
		setGlassEnabled(true);
		setModal(true);
		center();
	}
}

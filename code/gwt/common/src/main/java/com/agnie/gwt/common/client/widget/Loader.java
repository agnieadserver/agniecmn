/*******************************************************************************
 * ? 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
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

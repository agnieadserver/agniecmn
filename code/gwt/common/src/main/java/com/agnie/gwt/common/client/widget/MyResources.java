package com.agnie.gwt.common.client.widget;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface MyResources extends ClientBundle {
	@Source("test-button.css")
	MyCss css();

	@Source("button_left.png")
	ImageResource leftGrnBtn();

	@Source("button_right.png")
	ImageResource rightGrnBtn();

	@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
	@Source("button_body.png")
	ImageResource bodyGrnBtn();

	@Source("transperent.png")
	ImageResource transperent();

}

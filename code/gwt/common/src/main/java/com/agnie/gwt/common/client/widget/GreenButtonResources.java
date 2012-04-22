package com.agnie.gwt.common.client.widget;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface GreenButtonResources extends ClientBundle {
	@Source("green-button.css")
	GreenButtonCss css();

	@Source("button_left.png")
	ImageResource leftGrnBtn();

	@Source("button_right.png")
	ImageResource rightGrnBtn();

	@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
	@Source("button_body.png")
	ImageResource bodyGrnBtn();

}

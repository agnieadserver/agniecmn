package com.agnie.gwt.client;

import com.agnie.gwt.common.client.widget.SlideButtonDrag;
import com.agnie.gwt.common.client.widget.SlideButtonScale;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.kiouri.sliderbar.client.event.BarValueChangedEvent;
import com.kiouri.sliderbar.client.event.BarValueChangedHandler;
import com.kiouri.sliderbar.client.view.SliderBarHorizontal;

public class KDEHorizontalLeftBW extends SliderBarHorizontal {

	public KDEHorizontalLeftBW(int maxValue, int width, int height) {
		SlideButtonScale sbs = new SlideButtonScale();

		int scaleWidth = width - 4;
		int scaleHeight = height - 4;
		GWT.log("scaleWidth=" + scaleWidth + "scaleHeight=" + scaleHeight);

		sbs.setHeight(String.valueOf(scaleHeight) + "px");
		sbs.setWidth(String.valueOf(scaleWidth) + "px");// width-4

		setScaleWidget((Widget) sbs, height);

		final SlideButtonDrag sbd = new SlideButtonDrag();
		sbd.setHeight("26px");
		sbd.setWidth("50px");
		setDragWidget((Widget) sbd);

		this.setWidth(String.valueOf(width) + "px");
		this.setMaxValue(maxValue);
		
		this.addBarValueChangedHandler(new BarValueChangedHandler() {
			
			public void onBarValueChanged(BarValueChangedEvent event) {
				if(1==event.getValue()){
					sbd.setWidth("46px");
				}else{
					sbd.setWidth("50px");
				}
			}
		});
	}

}

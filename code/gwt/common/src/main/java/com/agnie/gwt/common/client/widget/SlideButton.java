package com.agnie.gwt.common.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.kiouri.sliderbar.client.event.BarValueChangedEvent;
import com.kiouri.sliderbar.client.event.BarValueChangedHandler;
import com.kiouri.sliderbar.client.view.SliderBarHorizontal;

/**
 * 
 *<p> SlideButton Widget is an simple On-Off switch(Toggles between two states) with BarValueChangedHandler <br>
 * Fixed Size Width:200px Height:30px,changing size might cause errors.
 *  <br>
 * dependency:- <br>
 * groupId:-gwt-slider-bar artifactId:-gwt-slider-bar version:-1.0</p>
 * <p>IMP:We have to adjust SlideButton drag element width for state0 as it is but for state1 it must be (width-4)
 * we have to adjust in stateChangeHandler {for event.state1 =slideButton.getDragWidget().getElement().setScrollLeft(96); and <br>
 * event.state0=slideButton.getDragWidget().getElement().setScrollLeft(100);</p>
 */
public class SlideButton extends SliderBarHorizontal {

	int						maxValue	= 1;
	int						width		= 200;
	int						height		= 30;
	SlideButtonScale		sbs			= new SlideButtonScale();
	final SlideButtonDrag	sbd			= new SlideButtonDrag();
	private List<HandlerRegistration> valueChangeHandlers=new ArrayList<HandlerRegistration>();

	public SlideButton() {
		/**
		 * <br>
		 * for SlideButton(of SIZE 200x30 ) SlideButtonScale css must be border-radius: 5px 5px 5px 5px; border: 2px
		 * solid #1F3F88; height: 26px !important; width: 196px !important; <br>
		 * and for SlideButton(of SIZE 100x30) SlideButtonScale css must be border-radius: 5px 5px 5px 5px; border: 2px
		 * solid #1F3F88; height: 26px !important; width: 96px !important;
		 */

		setScaleWidget((Widget) sbs, height);

		sbd.setHeight("26px");
		sbd.setWidth("100px");
		/**
		 * SlideButtonDrag top:0px 'SlideButtonDrag' width ='SlideButtonScale'/2 Width also changes in
		 * BarValueChangedEvent
		 */
		setDragWidget((Widget) sbd);

		this.setWidth(String.valueOf(width) + "px");
		this.setMaxValue(maxValue);// For 2 steps value is 1
		this.addBarValueChangedHandler(new BarValueChangedHandler() {
			
			@Override
			public void onBarValueChanged(BarValueChangedEvent event) {
				if (1 == event.getValue()) {
					sbd.removeStyleName(SlideButtonDrag.getResources().css().positionLeft());
					sbd.addStyleName(SlideButtonDrag.getResources().css().positionRight());
					
				} else {
					sbd.removeStyleName(SlideButtonDrag.getResources().css().positionRight());
					sbd.addStyleName(SlideButtonDrag.getResources().css().positionLeft());
				}
			}
		});
	}
	
	
	@Override
	/**
	 * Adds BarValueChangedHandler (for handling changes of knob position)
	 * @param barValueChangedHandler
	 * @return HandlerRegistration used to remove this handler
	 */
	public HandlerRegistration addBarValueChangedHandler(
			BarValueChangedHandler barValueChangedHandler){
		HandlerRegistration hr=super.addBarValueChangedHandler(barValueChangedHandler);
		valueChangeHandlers.add(hr);
		return hr;
	}
	
	/**
	 * To clear BarValueChangeHandlers for slideButton.
	 */
	public void clearBarValueChangeHandlers(){
		for (HandlerRegistration vch : valueChangeHandlers) {
			vch.removeHandler();
		}
		valueChangeHandlers.clear();
	}
	
	public List<HandlerRegistration> getValueChangeHandlerRegsList(){
		return this.valueChangeHandlers;
	}

	/**
	 * To change SlideButtonScale leftTitle <br>
	 * need to change in css(margin-left)
	 * 
	 * @param title
	 */

	public void setLeftTitle(String title) {
		this.sbs.setLeftTitle(title);
	}

	/**
	 * To change SlideButtonScale rightTitle<br>
	 * need to change in css(margin-left)
	 * 
	 * @param title
	 */
	public void setRightTitle(String title) {
		this.sbs.setRightTitle(title);
	}
}

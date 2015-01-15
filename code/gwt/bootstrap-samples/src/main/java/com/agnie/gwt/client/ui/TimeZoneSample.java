/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import com.agnie.gwt.bootstrap.proto.admin.client.timezone.AgnieDate;
import com.agnie.gwt.bootstrap.proto.admin.client.timezone.SecondsChangeEvent;
import com.agnie.gwt.bootstrap.proto.admin.client.timezone.SecondsChangeHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 14-Jan-2015
 *
 */
public class TimeZoneSample extends Composite {

	private static TimeZoneSampleUiBinder	uiBinder	= GWT.create(TimeZoneSampleUiBinder.class);

	interface TimeZoneSampleUiBinder extends UiBinder<Widget, TimeZoneSample> {
	}

	@UiField
	SpanElement	datetime;
	@UiField
	SpanElement	gmt;

	public TimeZoneSample() {
		initWidget(uiBinder.createAndBindUi(this));
		final AgnieDate ad = new AgnieDate("America/Dawson", true);
		ad.addSecondsChangeHandler(new SecondsChangeHandler() {

			@Override
			public void onChange(SecondsChangeEvent event) {
				datetime.setInnerHTML(ad.toLocaleString());
				gmt.setInnerText(ad.toString());
			}
		});

	}
}

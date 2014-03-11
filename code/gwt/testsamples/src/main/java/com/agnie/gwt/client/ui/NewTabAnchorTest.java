/**
 * 
 */
package com.agnie.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 11-Mar-2014
 * 
 */
public class NewTabAnchorTest extends Composite {

	private static NewTabAnchorTestUiBinder	uiBinder	= GWT.create(NewTabAnchorTestUiBinder.class);

	interface NewTabAnchorTestUiBinder extends UiBinder<Widget, NewTabAnchorTest> {
	}

	/**
	 * Because this class has a default constructor, it can be used as a binder template. In other words, it can be used
	 * in other *.ui.xml files as follows: <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 * xmlns:g="urn:import:**user's package**"> <g:**UserClassName**>Hello!</g:**UserClassName> </ui:UiBinder> Note that
	 * depending on the widget that is used, it may be necessary to implement HasHTML instead of HasText.
	 */
	public NewTabAnchorTest() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}

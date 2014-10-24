/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.AnchorListItem;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.NavVerticalBarNav;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 20-Aug-2014
 *
 */
public class SamplePage extends Composite {

	private static SamplePageUiBinder	uiBinder	= GWT.create(SamplePageUiBinder.class);

	interface SamplePageUiBinder extends UiBinder<Widget, SamplePage> {
	}

	@UiField
	TabListItem			tab1;

	@UiField
	Button				clickme;

	@UiField
	NavVerticalBarNav	navbar;

	@UiField
	Button				addmenu;

	int					counter	= 1;

	public SamplePage() {
		initWidget(uiBinder.createAndBindUi(this));
		clickme.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get().clear();
				RootPanel.get().add(SamplePage.this);
			}
		});

		addmenu.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AnchorListItem item = new AnchorListItem("Menu " + counter++);
				item.setIcon(IconType.AMBULANCE);
				navbar.add(item);
			}
		});
		tab1.showTab(false);
		tab1.setDataToggle(null);
		tab1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				tab1.showTab(false);
			}
		});
	}
}

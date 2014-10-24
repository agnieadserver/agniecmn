/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client;

import com.agnie.gwt.common.client.widget.BreadCrumbPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;

public class BreadCrumbTest extends Composite {

	public BreadCrumbTest() {
		BreadCrumbPanel panel = new BreadCrumbPanel();
		panel.addBreadCrumb("Societies");
		panel.addBreadCrumb("Dashboard");
		panel.addBreadCrumb("Account Summary");
		panel.getBreadCrumb(0).addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Window.alert("This works damit");
			}
		});

		initWidget(panel);
	}
}

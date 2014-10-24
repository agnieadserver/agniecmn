/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.presenter;

import com.agnie.gwt.client.ViewFactory;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

public class BasePresenter implements Presenter {

	protected ViewFactory	viewFactory;

	public boolean go() {
		HTMLPanel content = viewFactory.getMainContentPanel();
		content.clear();
		content.add(new Label(viewFactory.getMainHeading()));
		return true;
	}

	public void postRender() {
		// TODO Auto-generated method stub

	}

	public boolean checkPermission(String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setViewFactory(ViewFactory viewFactory) {
		this.viewFactory = viewFactory;
	}

}

/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.sample.celltable.client.factory;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sample.celltable.client.ui.CreatePage;
import com.sample.celltable.client.ui.ListPage;

/**
 * @author Pandurang Patil 25-Oct-2014
 *
 */
@Singleton
public class ViewFactory {

	@Inject
	private CreatePage	createPage;

	@Inject
	private ListPage	listPage;

	private Label		header	= new Label("Header");
	private Label		footer	= new Label("Footer");

	public ViewFactory() {
		header.addStyleName("headercss");
		footer.addStyleName("headercss");
	}

	public CreatePage getCreatePage() {
		return createPage;
	}

	public ListPage getListPage() {
		return listPage;
	}

	public Widget getHeader() {
		return header;
	}

	public Widget getFooter() {
		return footer;
	}

}

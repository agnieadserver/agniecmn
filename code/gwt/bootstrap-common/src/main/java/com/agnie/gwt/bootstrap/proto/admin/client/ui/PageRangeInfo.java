/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.base.ComplexWidget;

import com.agnie.gwt.bootstrap.proto.admin.client.Messages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.cellview.client.SimplePager;

/**
 * @author Pandurang Patil 27-Aug-2014
 *
 */
public class PageRangeInfo extends ComplexWidget {

	protected SimplePager	pager;
	Messages				messages	= GWT.create(Messages.class);

	public PageRangeInfo() {
		setElement(Document.get().createDivElement());
	}

	public void setPager(SimplePager pager) {
		this.pager = pager;
		refresh();
	}

	public void refresh() {
		int pageStart = pager.getPageStart();
		int pageEnd = pageStart + pager.getPageSize();

		getElement().setInnerText(messages.label_page_range_info((++pageStart) + "", pageEnd + ""));
	}
}

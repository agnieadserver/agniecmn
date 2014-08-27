/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.base.ComplexWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.cellview.client.SimplePager;

/**
 * @author Pandurang Patil 27-Aug-2014
 *
 */
public class PageRangeInfo extends ComplexWidget {

	protected SimplePager	pager;

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

		getElement().setInnerText("Showing " + ++pageStart + " to " + pageEnd);
	}
}

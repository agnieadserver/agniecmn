/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;

/**
 * @author Pandurang Patil 25-Aug-2014
 *
 */
public class ProgressIndicator extends FlowPanel {
	protected final DivElement	progressIndicator	= Document.get().createDivElement();

	public ProgressIndicator() {
		setStyleName(ProtoStyles.WZ_STEPS_PROGRESS);
		progressIndicator.setAttribute("style", "width: 0%;");
		getElement().appendChild(progressIndicator);
	}
}

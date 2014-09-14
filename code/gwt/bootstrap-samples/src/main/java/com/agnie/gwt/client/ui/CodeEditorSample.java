/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 26-Aug-2014
 *
 */
public class CodeEditorSample extends Composite {

	private static CellTableSampleUiBinder	uiBinder	= GWT.create(CellTableSampleUiBinder.class);

	interface CellTableSampleUiBinder extends UiBinder<Widget, CodeEditorSample> {
	}

	@UiField
	HTMLPanel	editor;

	public CodeEditorSample() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}

/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui.upload;

import gwtupload.client.DecoratedFileUpload;
import gwtupload.client.IFileInput;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.ButtonType;

/**
 * @author Pandurang Patil 15-Dec-2014
 *
 */
public class ButtonFileInput extends DecoratedFileUpload implements IFileInput {

	public ButtonFileInput(ButtonType type) {
		super(new Button());
		((Button) button).setType(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IFileInput#newInstance()
	 */
	@Override
	public IFileInput newInstance() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IFileInput#setLength(int)
	 */
	@Override
	public void setLength(int length) {
		// TODO Auto-generated method stub
	}

}

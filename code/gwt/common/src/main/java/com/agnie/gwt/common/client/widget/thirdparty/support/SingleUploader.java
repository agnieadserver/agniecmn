/**
 * 
 */
package com.agnie.gwt.common.client.widget.thirdparty.support;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus;

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class SingleUploader extends gwtupload.client.SingleUploader {

	/**
	 * 
	 */
	public SingleUploader() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 */
	public SingleUploader(FileInputType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 */
	public SingleUploader(IUploadStatus status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 * @param status
	 */
	public SingleUploader(FileInputType type, IUploadStatus status) {
		super(type, status);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param submitButton
	 */
	public SingleUploader(IUploadStatus status, Widget submitButton) {
		super(status, submitButton);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 * @param status
	 * @param submitButton
	 */
	public SingleUploader(FileInputType type, IUploadStatus status, Widget submitButton) {
		super(type, status, submitButton);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 * @param status
	 * @param submitButton
	 * @param form
	 */
	public SingleUploader(FileInputType type, IUploadStatus status, Widget submitButton, FormPanel form) {
		super(type, status, submitButton, form);
		// TODO Auto-generated constructor stub
	}

	public void setFileType(String type) {
		if (type != null) {
			FileInputType fileType = FileInputType.valueOf(type.toUpperCase());
			setFileInput(fileType.getInstance());
		}
	}

	@UiChild(limit = 1)
	public void addStatusBar(IUploadStatus status) {
		super.setStatusWidget((IUploadStatus) status);
	}

}

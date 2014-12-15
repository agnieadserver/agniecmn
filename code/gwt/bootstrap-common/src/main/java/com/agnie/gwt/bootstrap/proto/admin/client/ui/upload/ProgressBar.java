/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui.upload;

import gwtupload.client.IUploadStatus;

import java.util.List;
import java.util.Set;

import org.gwtbootstrap3.client.ui.constants.ProgressBarType;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 15-Dec-2014
 *
 */
public class ProgressBar extends org.gwtbootstrap3.client.ui.Progress implements IUploadStatus {
	Status									status;
	org.gwtbootstrap3.client.ui.ProgressBar	bar	= new org.gwtbootstrap3.client.ui.ProgressBar();

	public ProgressBar() {
		add(bar);
	}

	public void setProgressBarType(ProgressBarType type) {
		bar.setType(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.HasProgress#setProgress(long, long)
	 */
	@Override
	public void setProgress(long done, long total) {
		if (done > 0) {
			double percent = (((double) done / total) * 100);
			GWT.log("Percentage - " + percent);
			bar.setPercent(percent);
		} else {
			bar.setPercent(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#addCancelHandler(gwtupload.client.IUploadStatus.UploadCancelHandler)
	 */
	@Override
	public HandlerRegistration addCancelHandler(UploadCancelHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#getStatus()
	 */
	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#getWidget()
	 */
	@Override
	public Widget getWidget() {
		// TODO Auto-generated method stub
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#newInstance()
	 */
	@Override
	public IUploadStatus newInstance() {
		// TODO Auto-generated method stub
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#setCancelConfiguration(java.util.Set)
	 */
	@Override
	public void setCancelConfiguration(Set<CancelBehavior> config) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#setError(java.lang.String)
	 */
	@Override
	public void setError(String error) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#setFileNames(java.util.List)
	 */
	@Override
	public void setFileNames(List<String> names) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#setI18Constants(gwtupload.client.IUploadStatus.UploadStatusConstants)
	 */
	@Override
	public void setI18Constants(UploadStatusConstants strs) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.client.IUploadStatus#setStatus(gwtupload.client.IUploadStatus.Status)
	 */
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gwtupload.client.IUploadStatus#setStatusChangedHandler(gwtupload.client.IUploadStatus.UploadStatusChangedHandler)
	 */
	@Override
	public void setStatusChangedHandler(UploadStatusChangedHandler handler) {
		// TODO Auto-generated method stub

	}

}

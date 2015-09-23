/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import gwtupload.client.SingleUploader;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.ProgressBarType;
import org.gwtbootstrap3.client.ui.constants.ProgressType;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.upload.ButtonFileInput;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.upload.ProgressBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 15-Dec-2014
 *
 */
public class GWTUploadSample extends Composite {

    private static GWTUploadSampleUiBinder uiBinder = GWT.create(GWTUploadSampleUiBinder.class);

    interface GWTUploadSampleUiBinder extends UiBinder<Widget, GWTUploadSample> {
    }

    @UiField(provided = true)
    SingleUploader singleUploader;
    @UiField
    HTMLPanel      display;

    public GWTUploadSample() {
        ProgressBar pb = new ProgressBar();
        pb.setProgressBarType(ProgressBarType.SUCCESS);
        pb.setActive(true);
        pb.setType(ProgressType.STRIPED);
        pb.setSize("200px", "7px");
        Button btn = new Button("Upload");
        btn.setType(ButtonType.DEFAULT);
        singleUploader = new SingleUploader(FileInputType.CUSTOM, pb, btn);
        singleUploader.setFileInput(new ButtonFileInput(ButtonType.SUCCESS));
        initWidget(uiBinder.createAndBindUi(this));
        singleUploader.setValidExtensions("jpg", "gif", "png");
        singleUploader.setAutoSubmit(true);
        singleUploader.setServletPath("file.aguplod?sessionid=pandurang");
        // singleUploader.addStatusBar(status);
        singleUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
    }

    OnLoadPreloadedImageHandler               showImage               = new OnLoadPreloadedImageHandler() {
                                                                          public void onLoad(PreloadedImage img) {
                                                                              display.add(img);
                                                                          }
                                                                      };
    // Load the image in the document and in the case of success attach it to the viewer
    private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
                                                                          public void onFinish(IUploader uploader) {
                                                                              if (uploader.getStatus() == Status.SUCCESS) {
                                                                                  GWT.log("File Url:" + uploader.fileUrl());
                                                                                  new PreloadedImage(uploader.fileUrl(), showImage);

                                                                                  // The server sends useful
                                                                                  // information to the client by
                                                                                  // default
                                                                                  UploadedInfo info = uploader.getServerInfo();
                                                                                  GWT.log("File name " + info.name);
                                                                                  GWT.log("File content-type " + info.ctype);
                                                                                  GWT.log("File size " + info.size);

                                                                                  // You can send any customized
                                                                                  // message and parse it
                                                                                  GWT.log("Server message " + info.message);
                                                                                  GWT.log(uploader.getServerMessage().getMessage());
                                                                              } else if (uploader.getStatus() == Status.ERROR) {
                                                                                  GWT.log(uploader.getServerRawResponse());
                                                                              }
                                                                          }
                                                                      };

}

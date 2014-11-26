/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.tag.FocusElement;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.tag.InputTagElement;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.tag.SelectionEvent;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.tag.SelectionEventHandler;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.tag.TagEditor;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.tag.TagElement;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Pandurang Patil 25-Nov-2014
 *
 */
public class TagEditorTest extends Composite {

	private static TagEditorTestUiBinder	uiBinder	= GWT.create(TagEditorTestUiBinder.class);

	interface TagEditorTestUiBinder extends UiBinder<Widget, TagEditorTest> {
	}

	@UiField
	TagEditor	tageditor;

	@UiField
	TextBox		input;
	@UiField
	Button		add;
	@UiField
	Button		error;
	@UiField
	Button		fixerror;

	public TagEditorTest() {
		initWidget(uiBinder.createAndBindUi(this));
		tageditor.addTagElement(new TagElement("( os =~ [ 'linux', 'mac' ] )"));
		tageditor.addTagElement(new TagElement(" && "));
		tageditor.addTagElement(new TagElement(" ( "));
		tageditor.addTagElement(new TagElement("( browser =~ [ 'Chrome', 'Msie' ] || country == 'India' )"));
		tageditor.addTagElement(new TagElement(" ) "));
		tageditor.addSelectionHandler(new SelectionEventHandler() {

			@Override
			public void onSelection(SelectionEvent event) {
				FocusElement element = event.getElement();
				if (element instanceof InputTagElement) {
					GWT.log("Its a input tag element");
				} else {
					GWT.log("Its a tag element");
				}
			}
		});
	}

	@UiHandler("add")
	public void addHandler(ClickEvent event) {
		tageditor.addTagElement(new TagElement(input.getValue()));
	}

	@UiHandler("error")
	public void errorHandler(ClickEvent event) {
		tageditor.getTagElementList().get(0).setError();
	}

	@UiHandler("fixerror")
	public void fixErrorHandler(ClickEvent event) {
		tageditor.getTagElementList().get(0).fixError();
	}

}

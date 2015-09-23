/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/

package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ganesh Dawar
 *
 *         2:04:22 pm
 */
public class RichTextBox extends Composite {
    @UiField()
    TextBox                            input;
    @UiField
    LabelElement                       label;

    private static RichTextBoxUiBinder uiBinder = GWT.create(RichTextBoxUiBinder.class);

    interface RichTextBoxUiBinder extends UiBinder<Widget, RichTextBox> {
    }

    public RichTextBox() {
        this("");
    }

    public RichTextBox(String label) {
        initWidget(uiBinder.createAndBindUi(this));
        input.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                // Window.alert(input.getValue());
                if (input.getValue() != "") {
                    input.removeStyleName("empty");
                } else {
                    input.addStyleName("empty");
                }
            }
        });
        setLabel(label);
    }

    public void setLabel(String label) {
        this.label.setInnerText(label);
    }
}

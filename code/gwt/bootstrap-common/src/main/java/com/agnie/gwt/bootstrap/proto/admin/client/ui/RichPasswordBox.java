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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Ganesh Dawar
 *
 *         3:55:53 pm
 */
public class RichPasswordBox extends Composite {
    @UiField
    LabelElement                           label;
    @UiField
    PasswordTextBox                        input;

    private static RichPasswordBoxUiBinder uiBinder = GWT.create(RichPasswordBoxUiBinder.class);

    interface RichPasswordBoxUiBinder extends UiBinder<Widget, RichPasswordBox> {
    }

    public RichPasswordBox() {
        this("");
    }

    public RichPasswordBox(String label) {
        initWidget(uiBinder.createAndBindUi(this));
        input.addKeyUpHandler(new KeyUpHandler() {

            @Override
            public void onKeyUp(KeyUpEvent event) {
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

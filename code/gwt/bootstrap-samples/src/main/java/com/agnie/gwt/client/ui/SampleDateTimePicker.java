package com.agnie.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SampleDateTimePicker extends Composite {

    @UiField
    Button                                      btn;

    private static SampleDateTimePickerUiBinder uiBinder = GWT.create(SampleDateTimePickerUiBinder.class);

    interface SampleDateTimePickerUiBinder extends UiBinder<Widget, SampleDateTimePicker> {
    }

    public SampleDateTimePicker() {
        initWidget(uiBinder.createAndBindUi(this));
        btn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                new MyPopUpPanel().show();
            }
        });

    }
}

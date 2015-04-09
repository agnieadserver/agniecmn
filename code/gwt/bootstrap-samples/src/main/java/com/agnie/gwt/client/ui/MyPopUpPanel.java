package com.agnie.gwt.client.ui;

import java.util.Arrays;
import java.util.List;

import org.gwtbootstrap3.client.shared.event.HideEvent;
import org.gwtbootstrap3.client.shared.event.HideHandler;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.base.constants.DateTimePickerView;

import com.agnie.gwt.common.client.widget.ListBox;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.PopupPanel;

public class MyPopUpPanel extends PopupPanel {
    SimplePager simplePager;

    public MyPopUpPanel() {
        super(true);
        DateTimePicker monthPicker = new DateTimePicker();
        monthPicker.setPlaceholder("Select Month");
        monthPicker.setStartView(DateTimePickerView.YEAR);
        monthPicker.setMinView(DateTimePickerView.YEAR);
        monthPicker.setMaxView(DateTimePickerView.YEAR);
        monthPicker.setAutoClose(true);
        monthPicker.reload();
        setWidget(monthPicker);

        monthPicker.addHideHandler(new HideHandler() {

            @Override
            public void onHide(HideEvent hideEvent) {
                // // Create a cell to render each value in the list.
                // Cell<String> textCell = new TextCell();
                //
                // // Create a CellList that uses the cell.
                // CellList<String> cellList = new CellList<String>(textCell);
                //
                // // Set the total row count. This isn't strictly necessary, but it affects
                // // paging calculations, so its good habit to keep the row count up to date.
                // cellList.setRowCount(CONTACTS.size(), true);
                // // Push the data into the widget.
                // cellList.setRowData(0, CONTACTS);
                // cellList.addStyleName("margintop");
                // // Add it to the root panel.
                // setWidget(cellList);

                final ListBox<String> dropBox = new ListBox<String>(false);
                for (int i = 0; i < CONTACTS.size(); i++) {
                    dropBox.addItem(CONTACTS.get(i));
                }
                dropBox.ensureDebugId("cwListBox-dropBox");
                setWidget(dropBox);
                dropBox.setTitle("Select Week");
                dropBox.addChangeHandler(new ChangeHandler() {

                    @Override
                    public void onChange(com.google.gwt.event.dom.client.ChangeEvent event) {

                    }
                });
            }
        });

    }

    // The list of data to display.
    private static List<String> CONTACTS = Arrays.asList("John", "123 Fourth Road", "Mary", "222 Lancer Lane");

}

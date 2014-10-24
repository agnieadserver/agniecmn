/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import com.agnie.gwt.client.Person;
import com.agnie.gwt.client.presenter.ListPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;

public class DeskListView extends Composite implements ListView {
	interface MyUiBinder extends UiBinder<Widget, DeskListView> {
	}

	@UiField
	SimplePager					pager;
	@UiField
	CellTable<Person>			table;
	@Inject
	ListPresenter				presenter;
	ListDataProvider<Person>	dataProvider	= new ListDataProvider<Person>();
	private static MyUiBinder	uiBinder		= GWT.create(MyUiBinder.class);

	public DeskListView() {
		initWidget(uiBinder.createAndBindUi(this));
		table.setPageSize(10);
		Column<Person, String> fName = new TextColumn<Person>() {
			@Override
			public String getValue(Person object) {
				return object.getFname();
			}
		};
		table.addColumn(fName, "First Name");

		Column<Person, String> lName = new TextColumn<Person>() {
			@Override
			public String getValue(Person object) {
				return object.getLname();
			}
		};
		table.addColumn(lName, "Last Name");
		Column<Person, String> email = new TextColumn<Person>() {
			@Override
			public String getValue(Person object) {
				return object.getLname();
			}
		};
		table.addColumn(email, "Email");

		Column<Person, String> age = new TextColumn<Person>() {
			@Override
			public String getValue(Person object) {
				return object.getLname();
			}
		};
		table.addColumn(age, "Age");
		VerticalPanel panel = new VerticalPanel();
		Label label = new Label("No Data");
		panel.add(label);
		panel.setCellHorizontalAlignment(label, HasHorizontalAlignment.ALIGN_CENTER);
		table.setEmptyTableWidget(label);
		pager.setRangeLimited(true);
		pager.setDisplay(table);

		dataProvider.addDataDisplay(table);
	}


	public void init() {
		if (dataProvider.getList().size() <= 0) {
			presenter.fillData(dataProvider.getList());
		}
	}
}

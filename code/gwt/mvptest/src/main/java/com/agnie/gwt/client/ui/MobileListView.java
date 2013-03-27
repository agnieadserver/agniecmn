package com.agnie.gwt.client.ui;

import com.agnie.gwt.client.Person;
import com.agnie.gwt.client.ui.renderer.MobilePearsonCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class MobileListView extends Composite implements ListView {
	interface MyUiBinder extends UiBinder<Widget, MobileListView> {
	}

	@UiField
	SimplePager					pager;
	@UiField
	CellTable<Person>			table;
	Presenter					presenter;
	ListDataProvider<Person>	dataProvider	= new ListDataProvider<Person>();
	private static MyUiBinder	uiBinder		= GWT.create(MyUiBinder.class);

	public MobileListView() {
		initWidget(uiBinder.createAndBindUi(this));
		table.setPageSize(4);
		Column<Person, Person> permissions = new Column<Person, Person>(new MobilePearsonCell()) {
			@Override
			public Person getValue(Person object) {
				return object;
			}
		};
		table.addColumn(permissions, "Person");
		VerticalPanel panel = new VerticalPanel();
		Label label = new Label("No Data");
		panel.add(label);
		panel.setCellHorizontalAlignment(label, HasHorizontalAlignment.ALIGN_CENTER);
		table.setEmptyTableWidget(label);
		pager.setRangeLimited(true);
		pager.setDisplay(table);

		dataProvider.addDataDisplay(table);
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	public void init() {
		if (dataProvider.getList().size() <= 0) {
			presenter.fillData(dataProvider.getList());
		}
	}
}

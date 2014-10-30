package com.sample.celltable.client.ui;

import org.gwtbootstrap3.client.ui.Button;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sample.celltable.client.presenter.ListPresenter;
import com.sample.celltable.shared.USerDataBase;
import com.sample.celltable.shared.USerDataBase.User;

/**
 * @author rajgauravdubey
 *
 */
@Singleton
public class ListPage extends Composite {

	ListPresenter	userPresenter	= null;

	interface CelltableBinderUiBinder extends UiBinder<Widget, ListPage> {
	}

	private static CelltableBinderUiBinder	uiBinder	= GWT.create(CelltableBinderUiBinder.class);

	@Inject
	public ListPage() {

		initWidget(onInitialize());

		btnadd.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				userPresenter.showForm();
			}
		});

	}

	public void setListPresenter(ListPresenter presenter) {
		this.userPresenter = presenter;
	}

	/**
	 * Button to add More Data to the Table
	 * */

	@UiField
	Button			btnadd;

	/**
	 * The main CellTable.
	 */
	@UiField(provided = true)
	CellTable<User>	cellTable;

	/**
	 * The pager used to change the range of data.
	 */
	@UiField(provided = true)
	SimplePager		pager;

	/**
	 * Initialize this example.
	 */

	public Widget onInitialize() {
		// Create a CellTable.

		// Set a key provider that provides a unique key for each contact. If
		// key is
		// used to identify contacts when fields (such as the name and address)
		// change.
		cellTable = new CellTable<User>(USerDataBase.User.KEY_PROVIDER);
		cellTable.setWidth("100%%", true);

		// Do not refresh the headers and footers every time the data is
		// updated.
		cellTable.setAutoHeaderRefreshDisabled(true);
		cellTable.setAutoFooterRefreshDisabled(true);

		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(cellTable);
		setValuesToTable();
		// Create the UiBinder.
		Widget widget = uiBinder.createAndBindUi(this);
		return widget;
	}

	public void setValuesToTable() {
		// Add a selection model so we can select cells.
		final SelectionModel<User> selectionModel = new MultiSelectionModel<User>(USerDataBase.User.KEY_PROVIDER);
		cellTable.setSelectionModel(selectionModel, DefaultSelectionEventManager.<User> createCheckboxManager());

		// Initialize the columns.
		initTableColumns(selectionModel, null);

		// Add the CellList to the adapter in the database.

	}

	/**
	 * Get Cell Table instance from CellTable UI binder
	 * 
	 * @return {@link CellTable}.
	 */

	public CellTable<User> getCellTable() {
		return cellTable;
	}

	/**
	 * Add the columns to the table.
	 */
	private void initTableColumns(final SelectionModel<User> selectionModel, ListHandler<User> sortHandler1) {

		// First name.
		Column<User, String> firstNameColumn = new Column<User, String>(new EditTextCell()) {
			@Override
			public String getValue(User object) {
				return object.getfName();
			}
		};

		firstNameColumn.setSortable(true);

		cellTable.addColumn(firstNameColumn, "First Name");
		cellTable.setColumnWidth(firstNameColumn, 10, Unit.PCT);

		// Last name.
		Column<User, String> lastNameColumn = new Column<User, String>(new EditTextCell()) {
			@Override
			public String getValue(User object) {
				return object.getlName();
			}
		};
		lastNameColumn.setSortable(true);

		cellTable.addColumn(lastNameColumn, "Last Name");

		cellTable.setColumnWidth(lastNameColumn, 10, Unit.PCT);

		// Age.
		Column<User, String> ageColumn = new Column<User, String>(new TextCell()) {
			@Override
			public String getValue(User object) {
				return object.getAge() + "";
			}
		};
		ageColumn.setSortable(true);
		ageColumn.setDefaultSortAscending(false);

		cellTable.addColumn(ageColumn, "Age");
		cellTable.setColumnWidth(ageColumn, 10, Unit.PCT);
	}

}

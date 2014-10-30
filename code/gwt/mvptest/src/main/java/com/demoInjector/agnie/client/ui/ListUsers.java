/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.demoInjector.agnie.client.ui;

import org.gwtbootstrap3.client.ui.Button;

import com.agnie.gwt.common.client.mvp.Place;
import com.demoInjector.agnie.client.injector.DemoInjector;
import com.demoInjector.agnie.client.presenter.ListuserPresenter;
import com.demoInjector.agnie.client.presenter.PlaceToken;
import com.demoInjector.agnie.shared.USerDataBase;
import com.demoInjector.agnie.shared.USerDataBase.User;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

/**
 * @author rajgaurav
 *
 */
public class ListUsers extends Composite {

	ListuserPresenter	userPresenter	= null;

	@Inject
	DemoInjector		injector;

	interface CelltableBinder extends UiBinder<Widget, ListUsers> {
	}

	private static CelltableBinder	uiBinder	= GWT.create(CelltableBinder.class);

	public ListUsers() {

		initWidget(onInitialize());

		btnadd.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				injector.getApController().getPlaceManager().changePlace(new Place<PlaceToken>(PlaceToken.CREATE));
			}
		});

	}

	public void setListPresenter(ListuserPresenter presenter) {
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
		GWT.log("Inside List UI Binder After initialization");
		// Set a key provider that provides a unique key for each contact. If
		// key is
		// used to identify contacts when fields (such as the name and address)
		// change.
		cellTable = new CellTable<User>(USerDataBase.User.KEY_PROVIDER);
		cellTable.setWidth("100%%", true);

		// Do not refresh the headers and footers every time the data is
		// updated.
		// cellTable.setAutoHeaderRefreshDisabled(true);
		// cellTable.setAutoFooterRefreshDisabled(true);

		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		final SingleSelectionModel<User> selectionModel = new SingleSelectionModel<User>();

		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				User selected = selectionModel.getSelectedObject();
				Place<PlaceToken> place = new Place<PlaceToken>(PlaceToken.EDIT);
				place.put("id", selected.getId() + "");
				injector.getApController().getPlaceManager().changePlace(place);
			}
		});

		cellTable.setSelectionModel(selectionModel);

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
		// Initialize the columns.
		initTableColumns();

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
	private void initTableColumns() {

		// First name.
		Column<User, String> firstNameColumn = new Column<User, String>(new TextCell()) {
			@Override
			public String getValue(User object) {
				return object.getfName();
			}
		};

		firstNameColumn.setSortable(true);

		cellTable.addColumn(firstNameColumn, "First Name");
		cellTable.setColumnWidth(firstNameColumn, 10, Unit.PCT);

		// Last name.
		Column<User, String> lastNameColumn = new Column<User, String>(new TextCell()) {
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

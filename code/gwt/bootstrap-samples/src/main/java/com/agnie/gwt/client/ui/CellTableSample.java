/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.SimplePagination;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

/**
 * @author Pandurang Patil 26-Aug-2014
 *
 */
public class CellTableSample extends Composite {

	private static CellTableSampleUiBinder	uiBinder	= GWT.create(CellTableSampleUiBinder.class);

	interface CellTableSampleUiBinder extends UiBinder<Widget, CellTableSample> {
	}

	@UiField
	HTMLPanel			panelBody;

	@UiField
	SimplePagination	pager;

	SimplePager			simplePager;

	private static class Contact {
		private final String	address;
		private final String	name;

		public Contact(String name, String address) {
			this.name = name;
			this.address = address;
		}
	}

	// The list of data to display.
	private static List<Contact>	CONTACTS	= Arrays.asList(new Contact("John", "123 Fourth Road"), new Contact("Mary", "222 Lancer Lane"), new Contact("Zasdnder", "94 Road Street"), new Contact(
														"Zandsdfer", "94 Road Street"), new Contact("Zansdfder", "94 Road Street"), new Contact("Zandgafaer", "94 Road Street"), new Contact(
														"Zanhfasder", "94 Road Street"), new Contact("Zahksfgnder", "94 Road Street"), new Contact("Zanhhgfgfder", "94 Road Street"), new Contact(
														"Zanjhgfgfdsdsfgder", "94 Road Street"), new Contact("Zandsfgjlfsder", "94 Road Street"), new Contact("Zasfhdaak;nder", "94 Road Street"),
														new Contact("Zandsdfgkffsdfer", "94 Road Street"), new Contact("Zafgvdf nder", "94 Road Street"), new Contact("Zanddfgser", "94 Road Street"),
														new Contact("Jodfghn", "123 Fourth Road"), new Contact("Mary", "222 Lancer Lane"), new Contact("Zandfder", "94 Road Street"), new Contact(
																"Zandgfdser", "94 Road Street"), new Contact("Zansdfgder", "94 Road Street"), new Contact("Za sdfgdsfnder", "94 Road Street"),
														new Contact("Zand2342345er", "94 Road Street"), new Contact("Zan345udder", "94 Road Street"), new Contact("Zasdfnder", "94 Road Street"),
														new Contact("Zaasdjhw5432nder", "94 Road Street"), new Contact("Zanddasdasfhder", "94 Road Street"), new Contact("Zandfsdffgder",
																"94 Road Street"), new Contact("Ze453256dsgander", "94 Road Street"), new Contact("Zanfdfsdder", "94 Road Street"), new Contact(
																"Zanhsader", "94 Road Street"), new Contact("John", "123 Fourth Road"), new Contact("Mary", "222 Lancer Lane"), new Contact(
																"Zanddfghhaer", "94 Road Street"), new Contact("Zasdfadhnder", "94 Road Street"), new Contact("Zansdfggder", "94 Road Street"),
														new Contact("Zandeshasr", "94 Road Street"), new Contact("Zansdffader", "94 Road Street"), new Contact("Zanddfhjsdfger", "94 Road Street"),
														new Contact("Zandasdfadsfer", "94 Road Street"), new Contact("Zandasfaadsfder", "94 Road Street"), new Contact("Zansdsghkldder",
																"94 Road Street"), new Contact("Zanadsdasfder", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact(
																"Zandsddgsdfger", "94 Road Street"), new Contact("Zandashder", "94 Road Street"), new Contact("John", "123 Fourth Road"), new Contact(
																"Mary", "222 Lancer Lane"), new Contact("Zanasfder", "94 Road Street"), new Contact("Zasdfander", "94 Road Street"), new Contact(
																"Zakander", "94 Road Street"), new Contact("Zazdffdzsdander", "94 Road Street"), new Contact("Zaasdfhsdfnder", "94 Road Street"),
														new Contact("Zanddfgdfgder", "94 Road Street"), new Contact("Zan43trdfsdder", "94 Road Street"), new Contact("Zandahsfder", "94 Road Street"),
														new Contact("Zandu67rghr5er", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact(
																"Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("John", "123 Fourth Road"), new Contact("Mary",
																"222 Lancer Lane"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("John", "123 Fourth Road"), new Contact("Mary",
																"222 Lancer Lane"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("John", "123 Fourth Road"), new Contact("Mary",
																"222 Lancer Lane"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("John", "123 Fourth Road"), new Contact("Mary",
																"222 Lancer Lane"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander", "94 Road Street"), new Contact("Zander",
																"94 Road Street"), new Contact("Zander last one", "94 Road Street"));

	public CellTableSample() {
		initWidget(uiBinder.createAndBindUi(this));
		// Create a CellTable.
		CellTable<Contact> table = new CellTable<Contact>();

		// Create name column.
		TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact contact) {
				return contact.name;
			}
		};

		// Make the name column sortable.
		nameColumn.setSortable(true);

		// Create address column.
		TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact contact) {
				return contact.address;
			}
		};

		// Add the columns.
		table.addColumn(nameColumn, "Name");
		table.addColumn(addressColumn, "Address");

		// Create a data provider.
		ListDataProvider<Contact> dataProvider = new ListDataProvider<Contact>();

		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		simplePager = new SimplePager(TextLocation.CENTER, pagerResources, true, 0, true);
		simplePager.setDisplay(table);
		// Connect the table to the data provider.
		dataProvider.addDataDisplay(table);

		// Add the data to the data provider, which automatically pushes it to the
		// widget.
		List<Contact> list = dataProvider.getList();
		for (Contact contact : CONTACTS) {
			list.add(contact);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Contact> columnSortHandler = new ListHandler<Contact>(list);
		columnSortHandler.setComparator(nameColumn, new Comparator<Contact>() {
			public int compare(Contact o1, Contact o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.name.compareTo(o2.name) : 1;
				}
				return -1;
			}
		});
		table.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		table.getColumnSortList().push(nameColumn);
		simplePager.setPageSize(3);
		table.setCondensed(true);
		table.setStriped(true);
		table.setHover(true);
		panelBody.add(table);
		pager.setPager(simplePager);
		pager.rebuild(true);

		// table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
		// @Override
		// public void onRangeChange(RangeChangeEvent event) {
		// pager.rebuild(simplePager);
		// }
		// });
	}

}

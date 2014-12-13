/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.CheckBoxType;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.cellwidgets.SelectTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

/**
 * @author Pandurang Patil 27-Nov-2014
 *
 */
public class SelectTableSample extends Composite {

	private static SelectUnselectSampleUiBinder	uiBinder	= GWT.create(SelectUnselectSampleUiBinder.class);

	interface SelectUnselectSampleUiBinder extends UiBinder<Widget, SelectTableSample> {
	}

	@UiField(provided = true)
	SelectTable<User>	selectTable;

	@UiField
	Button				check;

	@UiField
	Button				addSelected;
	List<User>			data;

	public SelectTableSample() {
		ListDataProvider<User> listDataProvider = new ListDataProvider<User>();
		data = listDataProvider.getList();
		Random ran = new Random();
		for (int i = 0; i < 200; i++) {
			data.add(new User(i, "Item " + i, ran.nextInt(), "Item '" + i + "' desc"));
		}
		Column<User, User> column = new Column<User, User>(new UserCell()) {
			@Override
			public User getValue(User object) {
				return object;
			}
		};
		selectTable = new SelectTable<User>(7, true, CheckBoxType.PRIMARY, column);
		selectTable.setDataProvider(listDataProvider);
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setSelected() {
		List<User> selected = new ArrayList<User>();
		selected.add(new User(1));
		selected.add(new User(4));
		// selected.add(new User(8));
		// selected.add(new User(15));
		// selected.add(new User(18));
		selectTable.setSelected(new User(1), true);
		selectTable.setSelected(new User(4), true);
		selectTable.setSelected(new User(8), true);
		selectTable.setSelected(new User(15), true);
	}

	@UiHandler("check")
	public void clickEvent(ClickEvent event) {
		Window.alert("Selected items count - '" + selectTable.getSelectedEntities().size());
	}

	@UiHandler("addSelected")
	public void addSelectedEvent(ClickEvent event) {
		setSelected();
	}
}

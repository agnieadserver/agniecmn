/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.ui;

import java.util.List;
import java.util.Random;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.cellwidgets.SelectUnselect;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

/**
 * @author Pandurang Patil 27-Nov-2014
 *
 */
public class SelectUnselectSample extends Composite {

	private static SelectUnselectSampleUiBinder	uiBinder	= GWT.create(SelectUnselectSampleUiBinder.class);

	interface SelectUnselectSampleUiBinder extends UiBinder<Widget, SelectUnselectSample> {
	}

	@UiField(provided = true)
	SelectUnselect<User>	selectUnselect;

	public SelectUnselectSample() {
		ListDataProvider<User> listDataProvider = new ListDataProvider<User>();
		List<User> data = listDataProvider.getList();
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
		selectUnselect = new SelectUnselect<User>(7, column);
		selectUnselect.setAvailableDataProvider(listDataProvider);
		// selectUnselect.setSelectedDataProvider(listDataProvider);
		initWidget(uiBinder.createAndBindUi(this));
	}

}

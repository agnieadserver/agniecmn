/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget;

import java.util.List;

import com.agnie.gwt.common.client.I18;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Simple suggestion box When end user press any key in search field ,suggestion list get displayed.
 * 
 */
public class SuggestionBox extends Composite {
	private static SuggestionBoxResources	resource	= SuggestionBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, SuggestionBox> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	TextBox						search;
	@UiField
	CellList<String>			list;

	ListDataProvider<String>	dp			= new ListDataProvider<String>();
	TextCell					textCell	= new TextCell();

	protected HTMLPanel			container;

	public SuggestionBox() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
		list.setVisible(false);

		Label label = new Label(I18.messages.noData());
		list.setEmptyListWidget(label);

		dp.addDataDisplay(list);

		search.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				list.setVisible(true);
			}
		});

		list.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a selection model to handle user selection.
		final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
		list.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				String selected = selectionModel.getSelectedObject();
				if (selected != null) {
					Window.alert("You selected: " + selected);
				}
			}
		});

	}

	/**
	 * Sets data to suggestion list
	 * 
	 * @param list
	 * 
	 */
	public void setData(List<String> list) {
		dp.setList(list);
	}

	public static SuggestionBoxResources getResources() {
		return resource;
	}

	@UiFactory
	public CellList<String> getCellList() {
		list = new CellList<String>(textCell);
		return list;
	}

}

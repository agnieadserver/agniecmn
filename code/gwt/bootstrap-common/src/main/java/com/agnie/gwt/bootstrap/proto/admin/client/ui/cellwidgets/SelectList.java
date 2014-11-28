/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui.cellwidgets;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;

/**
 * Select table with support for pagination and search.
 * 
 * @author Pandurang Patil 28-Nov-2014
 *
 */
public class SelectList<ENTITY extends SelectEntity> extends Composite {
	private final ProvidesKey<ENTITY>	SELECTION_ENTITY_KEY_PROVIDER	= new ProvidesKey<ENTITY>() {

																			@Override
																			public Object getKey(ENTITY item) {

																				return item == null ? null : item;
																			}
																		};

	@SuppressWarnings("rawtypes")
	interface SelectUiBinder extends UiBinder<Widget, SelectList> {
	}

	private static SelectUiBinder	uiBinder		= GWT.create(SelectUiBinder.class);
	@UiField(provided = true)
	CellList<ENTITY>				celllist;
	MultiSelectionModel<ENTITY>		selectionModel	= null;
	@UiField
	Anchor							clear;
	@UiField
	Anchor							removeAll;
	@UiField
	Anchor							removeSelected;

	public SelectList(Cell<ENTITY> cell) {
		celllist = new CellList<ENTITY>(cell);
		celllist.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		initWidget(uiBinder.createAndBindUi(this));
		selectionModel = new MultiSelectionModel<ENTITY>(SELECTION_ENTITY_KEY_PROVIDER);

		celllist.setSelectionModel(selectionModel);
	}

	/**
	 * Set data provider which will feed data to this table.
	 * 
	 * @param dataProvider
	 */
	public void setDataProvider(AbstractDataProvider<ENTITY> dataProvider) {
		dataProvider.addDataDisplay(celllist);
	}

	@UiHandler("clear")
	public void clearHandler(ClickEvent click) {
		selectionModel.clear();
	}

	@UiHandler("removeAll")
	public void removeAllHandler(ClickEvent click) {
	}

	@UiHandler("removeSelected")
	public void removeSelectedHandler(ClickEvent click) {
	}

	/**
	 * get selected Set of entities.
	 * 
	 * @return
	 */
	public Set<ENTITY> getSelectedEntities() {
		return selectionModel.getSelectedSet();
	}

	/**
	 * Set given list of entities as selected entities.
	 * 
	 * @param entities
	 */
	public void setSelectedEntities(List<ENTITY> entities) {
		for (Iterator<ENTITY> iterator = entities.iterator(); iterator.hasNext();) {
			ENTITY entity = iterator.next();
			selectionModel.setSelected(entity, true);
		}
	}

	/**
	 * Refresh complete table.
	 */
	public void refreshCompleteTable() {
		celllist.setRowCount(0, false);
		Range resetrange = new Range(0, celllist.getPageSize());
		celllist.setVisibleRangeAndClearData(resetrange, true);
	}

	/**
	 * Add Clear select click handler
	 * 
	 * @param handler
	 *            click handler
	 * @return
	 */
	public HandlerRegistration addClearClickHandler(ClickHandler handler) {
		return clear.addClickHandler(handler);
	}

	/**
	 * set if you want to show clear selection link.
	 * 
	 * @param flag
	 */
	public void setClear(boolean flag) {
		clear.setVisible(flag);
	}

}

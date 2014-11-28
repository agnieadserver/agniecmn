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

import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.CheckBoxType;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.CheckboxCell;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.PageRangeInfo;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.SearchBox;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.SimplePagination;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RangeChangeEvent;

/**
 * Select table with support for pagination and search.
 * 
 * @author Pandurang Patil 28-Nov-2014
 *
 */
public class SelectTable<ENTITY extends SelectEntity> extends Composite {
	private final ProvidesKey<ENTITY>	SELECTION_ENTITY_KEY_PROVIDER	= new ProvidesKey<ENTITY>() {

																			@Override
																			public Object getKey(ENTITY item) {

																				return item == null ? null : item;
																			}
																		};

	@SuppressWarnings("rawtypes")
	interface SelectUiBinder extends UiBinder<Widget, SelectTable> {
	}

	private static SelectUiBinder	uiBinder		= GWT.create(SelectUiBinder.class);
	@UiField(provided = true)
	CellTable<ENTITY>				table;
	int								pageSize		= 0;
	MultiSelectionModel<ENTITY>		selectionModel	= null;
	@UiField
	SimplePagination				pager;
	@UiField
	Anchor							clear;
	@UiField
	PageRangeInfo					pageInfo;
	@UiField
	SearchBox						search;

	SimplePager						simplePager;

	public SelectTable(Integer pageSize, Column<ENTITY, ENTITY> dispColumn) {
		table = new CellTable<ENTITY>();
		this.pageSize = pageSize;
		table.setWidth("100%", false);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		initWidget(uiBinder.createAndBindUi(this));
		selectionModel = new MultiSelectionModel<ENTITY>(SELECTION_ENTITY_KEY_PROVIDER);

		Column<ENTITY, Boolean> checkColumn = new Column<ENTITY, Boolean>(new CheckboxCell(true, false, CheckBoxType.PRIMARY)) {
			@Override
			public Boolean getValue(ENTITY object) {
				return selectionModel.isSelected(object);
			}
		};
		checkColumn.setFieldUpdater(new FieldUpdater<ENTITY, Boolean>() {

			@Override
			public void update(int index, ENTITY object, Boolean value) {

				Window.alert("Label - '" + object.getId() + "' " + (value ? " Checked" : " Unchecked"));
			}
		});
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		simplePager = new SimplePager(TextLocation.CENTER, pagerResources, true, 0, true);
		simplePager.setDisplay(table);
		simplePager.setPageSize(pageSize);
		table.addColumn(checkColumn);
		table.addColumn(dispColumn);
		table.setColumnWidth(dispColumn, 90, Unit.PCT);
		table.setColumnWidth(checkColumn, 10, Unit.PCT);
		table.setCondensed(true);
		table.setStriped(true);
		table.setHover(true);
		table.setPageSize(pageSize);
		table.setSelectionModel(selectionModel, DefaultSelectionEventManager.<ENTITY> createCheckboxManager());
		pager.setPager(simplePager);
		pager.rebuild(true);
		pageInfo.setPager(simplePager);
		table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
			@Override
			public void onRangeChange(RangeChangeEvent event) {
				pageInfo.refresh();
			}
		});
	}

	/**
	 * Set data provider which will feed data to this table.
	 * 
	 * @param dataProvider
	 */
	public void setDataProvider(AbstractDataProvider<ENTITY> dataProvider) {
		dataProvider.addDataDisplay(table);
		pager.rebuild(true);
	}

	@UiHandler("clear")
	public void clearHandler(ClickEvent click) {
		selectionModel.clear();
	}

	/**
	 * this will help user to refresh the pagination.
	 */
	public void rebuildPagination() {
		pager.rebuild(true);
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
	 * refresh current page.
	 */
	public void refreshCurrentPageOfTable() {
		table.setVisibleRangeAndClearData(table.getVisibleRange(), true);
	}

	/**
	 * Refresh complete table.
	 */
	public void refreshCompleteTable() {
		table.setRowCount(0, false);
		Range resetrange = new Range(0, table.getPageSize());
		table.setVisibleRangeAndClearData(resetrange, true);
	}

	/**
	 * Add search click handler to intercept search clicks.
	 * 
	 * @param handler
	 *            Click handler
	 * @return
	 */
	public HandlerRegistration addSearchClickHandler(ClickHandler handler) {
		return search.addClickHandler(handler);
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
	 * Set if you want to show search or not .
	 * 
	 * @param flag
	 */
	public void setSearch(boolean flag) {
		search.setVisible(flag);
	}

	/**
	 * set if you want to show clear selection link.
	 * 
	 * @param flag
	 */
	public void setClear(boolean flag) {
		clear.setVisible(flag);
	}

	/**
	 * get search text.
	 * 
	 * @return
	 */
	public String getSearchText() {
		return search.getText();
	}
}

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

import org.gwtbootstrap3.client.ui.Button;

import com.agnie.gwt.bootstrap.proto.admin.client.Messages;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.CheckBoxType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.ListDataProvider;

/**
 * @author Pandurang Patil 30-Nov-2014
 *
 */
public class SelectUnselect<ENTITY extends SelectEntity> extends Composite {

	private static SelectUnselectUiBinder	uiBinder	= GWT.create(SelectUnselectUiBinder.class);

	@SuppressWarnings("rawtypes")
	interface SelectUnselectUiBinder extends UiBinder<Widget, SelectUnselect> {
	}

	Messages	messages	= GWT.create(Messages.class);

	interface SelectUnselectStyle extends CssResource {
		String desc();

		@ClassName("desc-selected")
		String descSelected();
	}

	@UiField(provided = true)
	SelectTable<ENTITY>			available;
	@UiField(provided = true)
	SelectTable<ENTITY>			selected;
	@UiField
	Button						resetAll;
	@UiField
	DivElement					selectedDesc;
	@UiField
	SelectUnselectStyle			style;
	ListDataProvider<ENTITY>	selectedListDP;

	public SelectUnselect(final Integer pageSize, Column<ENTITY, ENTITY> dispColumn) {
		available = new SelectTable<ENTITY>(pageSize, true, CheckBoxType.PRIMARY, dispColumn);
		selected = new SelectTable<ENTITY>(pageSize, true, CheckBoxType.SUCCESS, dispColumn);
		initWidget(uiBinder.createAndBindUi(this));
		selectedListDP = new ListDataProvider<ENTITY>();
		final List<ENTITY> selectedList = selectedListDP.getList();
		available.addRecordSelectHandler(new RecordSelectEventHandler<ENTITY>() {
			@Override
			public void onSelection(RecordSelectEvent<ENTITY> event) {
				if (event.getUserAction() && !selectedList.contains(event.getEntity())) {
					selectedList.add(event.getEntity());
				} else if (!event.getUserAction()) {
					selectedList.remove(event.getEntity());
				}
				selected.rebuildPagination();
				selectedDesc.setInnerText(event.getEntity().getDescription());
				if (!selectedDesc.getClassName().contains(style.descSelected())) {
					selectedDesc.addClassName(style.descSelected());
				}
			}
		});
		selected.addRecordSelectHandler(new RecordSelectEventHandler<ENTITY>() {
			@Override
			public void onSelection(RecordSelectEvent<ENTITY> event) {
				selectedDesc.setInnerText(event.getEntity().getDescription());
				if (!selectedDesc.getClassName().contains(style.descSelected())) {
					selectedDesc.addClassName(style.descSelected());
				}
			}
		});
		selected.setDataProvider(selectedListDP);
		selected.addRemoveClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				for (Iterator<ENTITY> iterator = selected.getSelectedEntities().iterator(); iterator.hasNext();) {
					ENTITY entity = (ENTITY) iterator.next();
					selectedList.remove(entity);
					available.setSelected(entity, false);
				}
			}
		});
		selected.addRemoveClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clearSelectedDescription();
			}
		});
		selected.addClearClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clearSelectedDescription();
			}
		});
		selected.setEmptyMessage(messages.msg_nothing_selected());
		clearSelectedDescription();
	}

	@UiHandler("resetAll")
	public void resetAllHandler(ClickEvent event) {
		selectedListDP.getList().clear();
		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {
				selected.rebuildPagination();
			}
		});
		available.clearSelection();
		selected.clearSelection();
		clearSelectedDescription();
	}

	void clearSelectedDescription() {
		selectedDesc.removeClassName(style.descSelected());
		selectedDesc.setInnerText(messages.msg_nothing_selected());
	}

	public void setAvailableDataProvider(AbstractDataProvider<ENTITY> dataprovider) {
		available.setDataProvider(dataprovider);
	}
}

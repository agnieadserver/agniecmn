package com.agnie.gwt.common.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.common.client.renderer.Title;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * CustomListBox widget. Accepts only entity which is implementing 'Title' interface
 */

public class CustomListBox<TYPE extends Title> extends Composite {

	private static CustomListBoxResources	resource	= CustomListBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	@SuppressWarnings("rawtypes")
	interface MyUiBinder extends UiBinder<Widget, CustomListBox> {
	}

	public static interface ChangeHandler {

		/**
		 * Called when a change selection is fired.
		 * 
		 * 
		 */
		void onChange();
	}

	private static MyUiBinder			uiBinder		= GWT.create(MyUiBinder.class);

	@UiField
	HTMLPanel							listTitlePan;
	@UiField
	SpanElement							customListTitle;
	@UiField
	Image								customListImg;

	CellList<TYPE>						cellList;

	HTMLPanel							container;

	final SingleSelectionModel<TYPE>	selectionModel	= new SingleSelectionModel<TYPE>();
	List<TYPE>							listItem;
	List<ChangeHandler>					changeHandler	= new ArrayList<ChangeHandler>();
	private int							itemCount		= 0;

	public CustomListBox(AbstractCell<TYPE> cell) {
		this(resource.css().customListBoxPan(), cell);
	}

	public CustomListBox(String styleClassName, AbstractCell<TYPE> cell) {
		initCellList(cell);
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		container.addStyleName(styleClassName);
		customListImg.setUrl(GWT.getModuleBaseURL() + "images/transparent.png");

		initWidget(container);
		container.add(cellList);

		cellList.setKeyboardSelectedRow(0);

		cellList.setVisible(false);
		customListImg.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (cellList.isVisible()) {
					cellList.setVisible(false);
					cellList.removeStyleName(resource.css().cellListSeparator());
					container.removeStyleName(resource.css().customListBoxBorder());
				} else {
					cellList.setVisible(true);
					cellList.addStyleName(resource.css().cellListSeparator());
					container.addStyleName(resource.css().customListBoxBorder());
				}

			}
		});
	}

	public void addChangeHandler(ChangeHandler handler) {
		changeHandler.add(handler);
	}

	public void initCellList(AbstractCell<TYPE> cell) {

		cellList = new CellList<TYPE>(cell);

		// Add a selection model to handle user selection.

		cellList.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Title selected = selectionModel.getSelectedObject();
				if (selected != null) {
					for (ChangeHandler ch : changeHandler) {
						ch.onChange();
					}
					setListBoxTitle(selected.getTitle());
					cellList.setVisible(false);
					cellList.removeStyleName(resource.css().cellListSeparator());
					container.removeStyleName(resource.css().customListBoxBorder());
				}
			}
		});
	}

	public TYPE getSelectedItem() {
		return selectionModel.getSelectedObject();
	}

	public void setSelectedItem(TYPE item, Boolean selected) {
		selectionModel.setSelected(item, selected);
	}

	public void setListBoxTitle(String title) {
		customListTitle.setInnerText(title);
	}

	public void setList(List<TYPE> list) {
		listItem = list;
		setListBoxTitle(list.get(0).getTitle());
		cellList.setRowCount(list.size(), true);
		cellList.setRowData(list);
		itemCount = list.size();
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public void setSize(int width, int height) {
		this.container.setPixelSize(width, height);
	}

	public void setWidth(String width) {
		this.container.setWidth(width);
	}

}

package com.agnie.gwt.common.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.common.client.renderer.CustomListCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Account widget.To show users account detail.
 * 
 */
public class CustomListBox extends Composite {
	private static CustomListBoxResources	resource	= CustomListBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, CustomListBox> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	HTMLPanel					listTitlePan;
	@UiField
	SpanElement					customListTitle;
	@UiField
	Image						customListImg;

	@UiField(provided = true)
	CellList<String>			cellList;

	HTMLPanel					container;
	CustomListCell				cell		= new CustomListCell();

	public CustomListBox() {
		this(resource.css().customListBoxPan());
	}

	public CustomListBox(String styleClassName) {
		initCellList();
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		container.addStyleName(styleClassName);
		initWidget(container);

		this.setList(createDummyList());
		cellList.setKeyboardSelectedRow(0);

		cellList.setVisible(false);
		customListImg.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (cellList.isVisible())
					cellList.setVisible(false);
				else
					cellList.setVisible(true);
			}
		});
	}

	public void initCellList() {
		cellList = new CellList<String>(cell);
		cellList.setVisibleRange(0,3);

		cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a selection model to handle user selection.
		final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
		cellList.setSelectionModel(selectionModel);

		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				String selected = selectionModel.getSelectedObject();
				if (selected != null) {
					setListBoxTitle(selected);
					cellList.setVisible(false);
				}
			}
		});
	}

	public void setListBoxTitle(String title) {
		customListTitle.setInnerText(title);
	}

	public void setList(List<String> list) {
		setListBoxTitle(list.get(0).toString());
		cellList.setRowCount(list.size(), true);
		cellList.setRowData(list);
	}
	
	public void setSize(int width,int height){
		this.container.setPixelSize(width, height);
	}
	
	public void setWidth(String width){
		this.container.setWidth(width);
	}

	public List<String> createDummyList() {
		List<String> list = new ArrayList<String>();

		for(int i=0;i<10;i++){
			list.add(i, "UserName"+i);
		}

		return list;
	}

}

package com.agnie.gwt.common.client.widget;

import java.util.List;

import com.agnie.gwt.common.client.widget.ListBox.GetText;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SelectUnselect<T> extends Composite {

	private static SelectUnselectResources	resource	= SelectUnselectResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}
	@UiField
	ListBox<T>	available;

	@UiField
	ListBox<T>	selected;

	@UiField
	Button		mvlft;

	@UiField
	Button		mvrgt;

	GetText<T>	getText;

	interface SelectUnselectUiBinder extends UiBinder<Widget, SelectUnselect> {
	}

	private static SelectUnselectUiBinder	uiBinder	= GWT.create(SelectUnselectUiBinder.class);

	public SelectUnselect(GetText<T> getText, List<T> sel, List<T> av) {
		this.getText = getText;
		initWidget(uiBinder.createAndBindUi(this));
		selected.setRowData(sel);
		available.setRowData(av);
		mvlft.setText("<<");
		mvrgt.setText(">>");
	}

	@UiFactory
	ListBox<T> makeList() {
		return new ListBox<T>(true, getText);
	}

	@UiHandler("mvlft")
	void moveLeft(ClickEvent event) {
		selected.addRowData(available.moveSelectedItems());
	}

	@UiHandler("mvrgt")
	void moveRight(ClickEvent event) {
		available.addRowData(selected.moveSelectedItems());
	}

	public List<T> getSelected() {
		return selected.getList();
	}

	public List<T> getAvailable() {
		return available.getList();
	}

	public ListBox<T> getSelectedListBox() {
		return selected;
	}

	public void enable() {
		mvlft.setEnabled(true);
		mvrgt.setEnabled(true);
	}

	public void disable() {
		mvlft.setEnabled(false);
		mvrgt.setEnabled(false);
	}
	public static SelectUnselectResources getResources() {
		return resource;
	}
}

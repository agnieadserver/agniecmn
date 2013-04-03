/**
 * 
 */
package com.agnie.gwt.common.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;

/**
 * 
 */
public class ListBox<T> extends com.google.gwt.user.client.ui.ListBox {

	private List<T>		list;
	private GetText<T>	gt;

	public ListBox(boolean isMultipleSelect, GetText<T> gt) {
		super(isMultipleSelect);
		this.gt = gt;
	}

	/**
	 * To set ListItem Label as it's Title(tooltip) for each ListItem.
	 */
	private void setTitle() {
		SelectElement selectElement = SelectElement.as(this.getElement());
		NodeList<OptionElement> options = selectElement.getOptions();
		for (int i = 0; i < options.getLength(); i++) {
			T t = this.list.get(i);
			options.getItem(i).setTitle(gt.getText(t));
		}
	}

	public ListBox(boolean isMultipleSelect) {
		super(isMultipleSelect);
	}

	public ListBox(GetText<T> gt) {
		super(false);
		this.gt = gt;
	}

	public void setGetText(GetText<T> gt) {
		this.gt = gt;
	}

	public void setRowData(List<T> list) {
		this.list = list;
		clear();
		for (int index = 0; index < this.list.size(); index++) {
			T t = this.list.get(index);
			addItem(gt.getText(t));
		}
		this.setTitle();
	}

	public void addRowData(List<T> newdata) {
		list.addAll(newdata);
		for (int index = 0; index < newdata.size(); index++) {
			T t = newdata.get(index);
			addItem(gt.getText(t));
		}
		this.setTitle();
	}

	public List<T> moveSelectedItems() {
		List<T> remaining = new ArrayList<T>();
		List<T> selected = new ArrayList<T>();
		for (int index = 0; index < list.size(); index++) {
			if (isItemSelected(index)) {
				GWT.log("selected item== " + list.get(index));
				selected.add(list.get(index));
			} else {
				remaining.add(list.get(index));
			}
		}
		setRowData(remaining);
		return selected;
	}

	public List<T> getList() {
		return list;
	}

	public interface GetText<T> {
		String getText(T object);
	}

	public T getSelected() {
		return list.get(getSelectedIndex());
	}

}

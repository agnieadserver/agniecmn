/**
 * 
 */
package com.agnie.gwt.common.client.widget;

import java.util.ArrayList;
import java.util.List;

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
	}

	public void addRowData(List<T> newdata) {
		list.addAll(newdata);
		for (int index = 0; index < newdata.size(); index++) {
			T t = newdata.get(index);
			addItem(gt.getText(t));
		}
	}

	public List<T> moveSelectedItems() {
		List<T> remaining = new ArrayList<T>();
		List<T> selected = new ArrayList<T>();
		for (int index = 0; index < list.size(); index++) {
			if (isItemSelected(index)) {
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

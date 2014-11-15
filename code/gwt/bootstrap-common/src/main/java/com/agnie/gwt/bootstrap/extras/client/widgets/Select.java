/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.extras.client.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import org.gwtbootstrap3.extras.select.client.ui.Option;

import com.agnie.common.gwt.serverclient.client.renderer.Title;
import com.google.gwt.core.client.GWT;

/**
 * @author Pandurang Patil 15-Nov-2014
 *
 */
public class Select<ENTITY extends Title> extends org.gwtbootstrap3.extras.select.client.ui.Select {

	private List	list;

	/**
	 * Set list of items inside drop down.
	 * 
	 * @param list
	 *            the list to set
	 */
	public void setList(java.util.List<ENTITY> list) {
		this.list = new List(list);
	}

	/**
	 * Get list of items
	 * 
	 * @return list of items
	 */
	public java.util.List<ENTITY> getList() {
		return list;
	}

	/**
	 * add new item to the list.
	 * 
	 * @param entity
	 *            item
	 */
	public void addItem(ENTITY entity) {
		if (this.list == null) {
			this.list = new List(new ArrayList<ENTITY>());
		}
		this.list.add(entity);

	}

	/**
	 * get selected item. if non of the item is selected it will return null. in case of multiselect very first item
	 * will be returned.
	 * 
	 * @return very first selected item
	 */
	public ENTITY getSelectedItem() {
		for (int i = 0; i < getItemCount(); i++) {
			if (isItemSelected(i)) {
				return this.list.get(i);
			}
		}
		return null;
	}

	/**
	 * set selected item by index.
	 * 
	 * @param index
	 *            item index to be set selected.
	 */
	public void setSelected(int index) {
		setValue(this.list.getOption(index));
	}

	/**
	 * set given item as selected item
	 * 
	 * @param entity
	 *            item to be selected.
	 */
	public void setSelected(ENTITY entity) {
		setValue(this.list.getOption(entity));
	}

	/**
	 * get all selected items.
	 * 
	 * @return List of selected items.
	 */
	public java.util.List<ENTITY> getAllSelectedItems() {
		final java.util.List<ENTITY> allSelected = new ArrayList<ENTITY>();

		for (int i = 0; i < getItemCount(); i++) {
			if (isItemSelected(i)) {
				allSelected.add(this.list.get(i));
			}
		}
		return allSelected;
	}

	/**
	 * set list of items to be selected.
	 * 
	 * @param items
	 *            list of items to be selected.
	 */
	public void setSelected(java.util.List<ENTITY> items) {
		java.util.List<Option> selectedItems = new ArrayList<Option>();
		for (int index = 0; index < items.size(); index++) {
			Option option = this.list.getOption(items.get(index));
			if (option == null) {
				GWT.log("This item '" + items.get(index).getTitle() + "' doesn't exist in the added list");
			}
			selectedItems.add(option);
		}
		setValues(selectedItems.toArray(new Option[0]));
	}

	private class List implements java.util.List<ENTITY> {

		private java.util.List<ENTITY>	delegator;
		private java.util.List<Option>	options;

		/**
		 * @param delegator
		 */
		public List(java.util.List<ENTITY> delegator) {
			this.delegator = delegator;
			options = new ArrayList<Option>();
			Iterator<ENTITY> iterator = delegator.iterator();
			Select.this.clear();
			for (int index = 0; index < delegator.size(); index++) {
				addEntity(iterator.next());
			}
			Select.this.refresh();
		}

		private Option createOption(ENTITY entity) {
			Option option = new Option();
			option.setText(entity.getTitle());
			return option;
		}

		Option getOption(ENTITY entity) {
			return options.get(delegator.indexOf(entity));
		}

		Option getOption(int index) {
			return options.get(index);
		}

		@Override
		public int size() {
			return delegator.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#isEmpty()
		 */
		@Override
		public boolean isEmpty() {
			return delegator.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#contains(java.lang.Object)
		 */
		@Override
		public boolean contains(Object o) {
			return delegator.contains(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#iterator()
		 */
		@Override
		public Iterator<ENTITY> iterator() {
			return delegator.iterator();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray()
		 */
		@Override
		public Object[] toArray() {
			return delegator.toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#toArray(java.lang.Object[])
		 */
		@Override
		public <T> T[] toArray(T[] a) {
			return delegator.toArray(a);
		}

		private void addEntity(ENTITY entity) {
			Option option = createOption(entity);
			options.add(option);
			Select.this.add(option);
			Select.this.refresh();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(java.lang.Object)
		 */
		@Override
		public boolean add(ENTITY entity) {
			addEntity(entity);
			return delegator.add(entity);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(java.lang.Object)
		 */
		@Override
		public boolean remove(Object o) {
			int index = delegator.indexOf(o);
			Select.this.remove(options.get(index));
			options.remove(index);
			Select.this.refresh();
			return delegator.remove(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#containsAll(java.util.Collection)
		 */
		@Override
		public boolean containsAll(Collection<?> c) {
			return delegator.containsAll(c);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends ENTITY> c) {
			@SuppressWarnings("unchecked")
			Iterator<ENTITY> iterator = (Iterator<ENTITY>) c.iterator();
			for (int index = 0; index < c.size(); index++) {
				add(iterator.next());
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends ENTITY> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#removeAll(java.util.Collection)
		 */
		@Override
		public boolean removeAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#retainAll(java.util.Collection)
		 */
		@Override
		public boolean retainAll(Collection<?> c) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#clear()
		 */
		@Override
		public void clear() {
			Select.this.clear();
			delegator.clear();
			options.clear();
			Select.this.refresh();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#get(int)
		 */
		@Override
		public ENTITY get(int index) {
			return delegator.get(index);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#set(int, java.lang.Object)
		 */
		@Override
		public ENTITY set(int index, ENTITY element) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, ENTITY element) {
			throw new UnsupportedOperationException();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#remove(int)
		 */
		@Override
		public ENTITY remove(int index) {
			Select.this.remove(index);
			Select.this.refresh();
			options.remove(index);
			return delegator.remove(index);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#indexOf(java.lang.Object)
		 */
		@Override
		public int indexOf(Object o) {
			return delegator.indexOf(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#lastIndexOf(java.lang.Object)
		 */
		@Override
		public int lastIndexOf(Object o) {
			return delegator.lastIndexOf(o);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator()
		 */
		@Override
		public ListIterator<ENTITY> listIterator() {
			return delegator.listIterator();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#listIterator(int)
		 */
		@Override
		public ListIterator<ENTITY> listIterator(int index) {
			return delegator.listIterator(index);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.List#subList(int, int)
		 */
		@Override
		public java.util.List<ENTITY> subList(int fromIndex, int toIndex) {
			throw new UnsupportedOperationException();
		}

	}
}

package com.sample.celltable.shared;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;

public class USerDataBase {

	public static class User implements Comparable<User>, Serializable {

		private static final long serialVersionUID = 1L;
		private String fName;
		private String lName;
		private int age;
		private String id;

		/**
		 * The key provider that provides the unique ID of a contact.
		 */
		public static final ProvidesKey<User> KEY_PROVIDER = new ProvidesKey<User>() {
			@Override
			public Object getKey(User item) {
				return item == null ? null : item.getId();
			}
		};

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getfName() {
			return fName;
		}

		public void setfName(String fName) {
			this.fName = fName;
		}

		public String getlName() {
			return lName;
		}

		public void setlName(String lName) {
			this.lName = lName;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public int compareTo(User o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	/**
	 * The provider that holds the list of contacts in the database.
	 */
	// private ListDataProvider<User> dataProvider = new
	// ListDataProvider<User>();

	/**
	 * Async data Provider for Async data Filling.
	 */

	int start = 0;
	int end = 0;

	

	private static USerDataBase instance = null;

	public static USerDataBase get() {

		if (instance == null) {
			instance = new USerDataBase();
		}
		return instance;

	}

	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);
	}

	/**
	 * private Constructor for Singleton Class implementation
	 * 
	 * 
	 * */

	private USerDataBase() {

	}

//	/**
//	 * Get Data Provider
//	 */
//
//	public AsyncDataProvider<User> getDataProvider() {
//		return dataProvider;
//
//	}
//
//	public void addDisplayData(HasData<User> data) {
//		dataProvider.addDataDisplay(data);
//	}

	/**
	 * overrides existing data list with given list
	 */

	public void setDataList(List<User> list) {
		// dataProvider.setList(list);
		
	}

	public void addUsers(User newUser) {
		// List<User> userList = dataProvider.getList();
		// userList.remove(newUser);
		// userList.add(newUser);
		//
		// dataProvider.refresh();
	}

	/**
	 * Refresh all displays.
	 */
	public void refreshDisplays() {
		// dataProvider.refresh();
	}
}

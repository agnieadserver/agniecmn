package com.agnie.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.common.client.widget.ListBox;
import com.agnie.gwt.common.client.widget.ListBox.GetText;
import com.google.gwt.user.client.ui.Composite;

public class ListBoxTest extends Composite {

	ListBox<User>	listBox;

	public ListBoxTest() {

		listBox = new ListBox<User>(new GetText<User>() {

			public String getText(User object) {
				if (object != null) {
					return object.getFname() + ", " + object.getLname();
				}
				return "";
			}

		});

		initWidget(listBox);

		List<User> list = new ArrayList<User>();
		User us = new User();
		us.setFname("Pranoti");
		us.setLname("Patil");
		list.add(us);
		us = new User();
		us.setFname("Pandurang");
		us.setLname("Patil");
		list.add(us);
		us = new User();
		us.setFname("Samar");
		us.setLname("Shinde");
		list.add(us);

		listBox.setRowData(list);
	}

	public User getSelected() {
		return listBox.getSelected();
	}
}

class User {
	private String	fname;
	private String	lname;

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + "]";
	}

}

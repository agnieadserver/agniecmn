package com.agnie.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.common.client.widget.ListBox.GetText;
import com.agnie.gwt.common.client.widget.SelectUnselect;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;

public class SelectUnselectTest extends Composite {

	SelectUnselect<User>	selUnsel;

	public SelectUnselectTest() {
		List<User> sel = new ArrayList<User>();
		User us = new User();
		us.setFname("Pranoti");
		us.setLname("Patil");
		sel.add(us);
		us = new User();
		us.setFname("Pandurang");
		us.setLname("Patil");
		sel.add(us);
		us = new User();
		us.setFname("Samar");
		us.setLname("Shinde");
		sel.add(us);

		List<User> av = new ArrayList<User>();
		us = new User();
		us.setFname("Ram");
		us.setLname("Shetty");
		av.add(us);
		us = new User();
		us.setFname("Mahesh");
		us.setLname("Shaha");
		av.add(us);
		selUnsel = new SelectUnselect<User>(new GetText<User>() {

			public String getText(User object) {
				if (object != null) {
					return object.getFname() + ", " + object.getLname();
				}
				return "";
			}
		}, sel, av);
		initWidget(selUnsel);
	}

	public void showAvailable() {
		Window.alert(selUnsel.getAvailable().toString());
	}

	public void showSelected() {
		Window.alert(selUnsel.getSelected().toString());
	}

}

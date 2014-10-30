package com.sample.celltable.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sample.celltable.client.GreetingService;
import com.sample.celltable.shared.USerDataBase;
import com.sample.celltable.shared.USerDataBase.User;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	static List<User> dataList = null;

	static {
		dataList = new ArrayList<USerDataBase.User>();

		int fixAge = 0;
		for (int i = 0; i < 35; i++) {
			User user = new User();
			user.setfName("Udayan" + i);
			user.setlName("Dubey" + i);
			user.setAge(fixAge + i);
			dataList.add(user);

		}
	}

	public String greetServer(String input) throws IllegalArgumentException {

		return "Hello, " + input + "!<br><br>I am running "
				+ ".<br><br>It looks like you are using:<br>";
	}

	@Override
	public List<User> listUserData(int start, int range) throws Exception {

		return createSubList(start, range);

	}

	private List<User> createSubList(int start, int range) {

		List<User> list = new ArrayList<USerDataBase.User>();
		System.out.println("Start -> " + start + " Size -> " + " Range "
				+ range);

		for (int i = start; i <= range; i++) {

			if (i != dataList.size())
				list.add(dataList.get(i));
			else
				break;
		}

		return list;

	}

	@Override
	public void addUser(User user) throws Exception {
		dataList.add(user);

	}

}

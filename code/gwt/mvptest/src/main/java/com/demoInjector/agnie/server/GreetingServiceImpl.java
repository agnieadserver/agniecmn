package com.demoInjector.agnie.server;

import java.util.ArrayList;
import java.util.List;

import com.demoInjector.agnie.client.rpc.GreetingService;
import com.demoInjector.agnie.shared.USerDataBase;
import com.demoInjector.agnie.shared.USerDataBase.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	static List<User>	dataList	= null;

	static {
		dataList = new ArrayList<USerDataBase.User>();

		int fixAge = 0;
		for (int i = 0; i < 13; i++) {
			User user = new User();
			user.setId(i);
			user.setfName("Udayan" + i);
			user.setlName("Dubey" + i);
			user.setAge(fixAge + i);
			dataList.add(user);
		}
	}

	@Override
	public String greetServer(String name) throws IllegalArgumentException {
		return "Hello, " + name + "!<br><br>I am running " + ".<br><br>It looks like you are using:<br>";
	}

	@Override
	public List<User> listUserData(int start, int range) throws Exception {

		GWT.log("@@@@@@@@@@Came In server Side Code");
		return createSubList(start, range);
	}

	@Override
	public void addUser(User user) throws Exception {
		editUser(user);

	}

	private List<User> createSubList(int start, int range) {

		List<User> list = new ArrayList<USerDataBase.User>();

		for (int i = start; i <= range; i++) {

			if (i != dataList.size())
				list.add(dataList.get(i));
			else
				break;
		}

		return list;

	}

	@Override
	public List<User> editUser(User user) throws Exception {

		System.out.println("Index " + user.getId());
		if (user.getId() != -1) {

			dataList.get(user.getId()).setAge(user.getAge());
			dataList.get(user.getId()).setfName(user.getfName());
			dataList.get(user.getId()).setlName(user.getlName());
		} else {
			user.setId(dataList.size());
			dataList.add(user);
		}

		return dataList;
	}

	@Override
	public User getUser(int id) {
		return dataList.get(id);
	}

}

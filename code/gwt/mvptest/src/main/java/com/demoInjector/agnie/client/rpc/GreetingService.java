package com.demoInjector.agnie.client.rpc;

import java.util.List;

import com.demoInjector.agnie.shared.USerDataBase.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

	String greetServer(String name) throws IllegalArgumentException;

	List<User> listUserData(int start, int range) throws Exception;

	void addUser(User user) throws Exception;

	List<User> editUser(User user) throws Exception;

	User getUser(int id);
}

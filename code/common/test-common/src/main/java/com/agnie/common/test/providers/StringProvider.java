package com.agnie.common.test.providers;

import com.google.inject.Provider;

/**
 * 
 * This provider has been specifically been created to facilitate setting required String value for testing purpose.
 * This class shouldn't be used in main code. Usage is limited to single threaded environment. If its being used in
 * multi threaded environment then behaviour is not guaranteed.
 * 
 * @author Pandurang Patil 10-Feb-2014
 * 
 */
public class StringProvider implements Provider<String> {

	private StringManager	mgr;

	public StringProvider(StringManager mgr) {
		this.mgr = mgr;
	}

	public String get() {
		return mgr.getValue();
	}

}

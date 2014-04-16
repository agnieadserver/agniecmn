package com.agnie.gwt.client.ui;

/**
 * @author Pandurang Patil 18-Mar-2014
 * 
 */
public class GWTCode {

	public String hello(String name) {
		return "Hello " + name + "!";
	}

	public static native void exportMethod(GWTCode code) /*-{
		$wnd.gwtcode_hello = function(name) {
			return code.@com.agnie.gwt.client.ui.GWTCode::hello(Ljava/lang/String;)(name);
		};
	}-*/;
}

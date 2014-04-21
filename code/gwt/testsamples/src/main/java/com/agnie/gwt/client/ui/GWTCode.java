/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
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

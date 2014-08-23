/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client;

import com.agnie.gwt.bootstrap.proto.admin.client.resources.ProtoAdminClientBundle;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.user.client.Command;

/**
 * 
 * @author Pandurang Patil 23-Aug-2014
 *
 */
public class ProtoAdmin implements EntryPoint {
	public void onModuleLoad() {
		// NOTE: we have to defer the loading of following script to make sure bootstrap scripts are fully loaded first
		// and then these scripts are loaded. Other wise you will see few events are not fired.
		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {
				ScriptInjector.fromString(ProtoAdminClientBundle.INSTANCE.nanoscroller().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
				ScriptInjector.fromString(ProtoAdminClientBundle.INSTANCE.theme().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
				ScriptInjector.fromString(ProtoAdminClientBundle.INSTANCE.customTheme().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
				ScriptInjector.fromString(ProtoAdminClientBundle.INSTANCE.themeInit().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			}
		});
	}

}

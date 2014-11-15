/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.flatui.client;

import com.agnie.gwt.bootstrap.flatui.client.assets.FlatUIClientBundle;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.user.client.Command;

/**
 * @author Pandurang Patil 15-Nov-2014
 *
 */
public class FlatUI implements EntryPoint {
	public void onModuleLoad() {
		// NOTE: we have to defer the loading of following script to make sure bootstrap scripts are fully loaded first
		// and then these scripts are loaded. Other wise you will see few events are not fired.
		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {
				ScriptInjector.fromString(FlatUIClientBundle.INSTANCE.flatui().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			}
		});
	}

}

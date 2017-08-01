/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.demo.client;

import com.agnie.demo.client.injector.DemoInjector;
import com.agnie.demo.client.mvp.DemoAppController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;

/**
 * @author Pandurang Patil 26-Jan-2017 - 4:13:40 pm
 *
 */
public class Demo implements EntryPoint {

    public void onModuleLoad() {
        Scheduler.get().scheduleDeferred(new Command() {
            public void execute() {
                DemoInjector injector = GWT.create(DemoInjector.class);
                DemoAppController controller = injector.getAppController();
                controller.go();
            }
        });
    }

}

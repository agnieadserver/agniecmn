/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.shutdown;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Singleton;

/**
 * This will facilitate grace full shutdown required for independent utilities that are being used. Utility or framework
 * that need to invoke graceful shutdown of the utility requires to implement ShutdownHook and register the same with
 * ShutdownProcessor. One can retrieve singleton instance of ShutdownProcessor from guice injector.
 * 
 * @author Pandurang Patil 15-Mar-2014
 * 
 */
@Singleton
public class ShutdownProcessor {
	// TODO: Make use of Event bus mechanism to avoid sequential intimation to individual hook.

	List<ShutdownHook>	hooks	= new ArrayList<ShutdownHook>();

	/**
	 * To register new shutdown hook which will be called when system (application either from inside web container or
	 * from act as independent application) is getting shutdown.
	 * 
	 * Note: You need to make sure your hook don't take much time. Other wise that may impact on shutdown
	 * for other hooks in queue.
	 * 
	 * @param hook
	 */
	public void register(ShutdownHook hook) {
		hooks.add(hook);
	}

	/**
	 * To initiate shutdown with shutdown hooks registered with processor. Ideally this has to be called from inside
	 * Servlet context listeners destroy method in case of web app. In case of independent app / tool you knwow the code
	 * better you should invoke it when you know your app is existing gracefully.
	 */
	public void shutdown(boolean sync) {
		for (ShutdownHook hook : hooks) {
			hook.shutdown(sync);
		}
	}

}

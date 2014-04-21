/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.tools;

/**
 * 
 * @author Pandurang Patil 04-Feb-2014
 * 
 */
public abstract class BaseAdminTools {
	protected Commander	commander	= new Commander(new MainArgs());

	public BaseAdminTools() {
		registerCommands();
	}

	/*
	 * Individual tools module implementors will registers required commands that will be supported by that tools
	 * utility.
	 */
	public abstract void registerCommands();

	protected void addCommand(String command, CommandProcessor processor) {
		commander.addCommand(command, processor);
	}

	public void processArguments(String[] args) throws Exception {
		commander.processArguments(args);
	}

}

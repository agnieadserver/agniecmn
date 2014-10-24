/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.tools;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public abstract class CommandProcessor {
	@Parameter(names = "--help", help = true)
	private boolean		help;

	private JCommander	commander;

	private String		command;

	/**
	 * @return the help
	 */
	public boolean isHelp() {
		return help;
	}

	/**
	 * @return the commander
	 */
	public JCommander getCommander() {
		return commander;
	}

	/**
	 * @param commander
	 *            the commander to set
	 */
	public void setCommander(JCommander commander) {
		this.commander = commander;
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * @param command
	 *            the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	public void processCommand() throws Exception {
		if (help) {
			commander.usage(command);
		} else {
			process();
		}
	}

	abstract public void process() throws Exception;

}

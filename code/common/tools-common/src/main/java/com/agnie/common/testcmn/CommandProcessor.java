package com.agnie.common.testcmn;

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

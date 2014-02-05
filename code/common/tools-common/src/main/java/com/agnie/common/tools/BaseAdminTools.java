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

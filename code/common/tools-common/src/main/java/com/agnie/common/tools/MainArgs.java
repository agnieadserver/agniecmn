package com.agnie.common.tools;

import com.beust.jcommander.Parameter;

public class MainArgs {
	@Parameter(names = "--help", help = true)
	private boolean	help;

	/**
	 * @return the help
	 */
	public boolean isHelp() {
		return help;
	}

	/**
	 * @param help
	 *            the help to set
	 */
	public void setHelp(boolean help) {
		this.help = help;
	}

}

package com.agnie.tools.war;

import com.agnie.common.tools.BaseAdminTools;

/**
 * 
 * @author Pandurang Patil 02-Mar-2014
 * 
 */
public class AdminTools extends BaseAdminTools {
	public static final String	RE_PACK	= "repack";

	public static void main(String[] args) throws Exception {
		AdminTools tools = new AdminTools();
		tools.processArguments(args);
	}

	@Override
	public void registerCommands() {
		addCommand(RE_PACK, new RepackProcessor());
	}

}

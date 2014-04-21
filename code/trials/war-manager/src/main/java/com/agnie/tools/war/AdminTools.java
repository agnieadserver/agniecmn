/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
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

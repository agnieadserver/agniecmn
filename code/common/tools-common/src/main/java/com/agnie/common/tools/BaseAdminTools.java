/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.tools;

import java.util.Map;
import java.util.Set;

import com.google.inject.Inject;

/**
 * 
 * @author Pandurang Patil 04-Feb-2014
 * 
 */
public abstract class BaseAdminTools {
	protected Commander	commander;

	@Inject
	public BaseAdminTools(Commander commander) {
		this.commander = commander;
	}
	
	public void setCommands(Map<String, CommandProcessor> cmdMapping){
		Set<String> keys = cmdMapping.keySet();
		for (String key : keys) {
			commander.addCommand(key, cmdMapping.get(key));
		}
		
	}

	public void processArguments(String[] args) throws Exception {
		commander.processArguments(args);
	}

}

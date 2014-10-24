/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.injector;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * 
 * @author Pandurang Patil 30-Jan-2014
 * 
 */
@Singleton
public class AgnieDeploymentConfig {

	private String	agnieHome;
	@SuppressWarnings("unused")
	private String	project;
	private String	projectHome;
	private String	projectConfig;
	private String	projectBin;

	@Inject
	public AgnieDeploymentConfig(@Named(CommonModule.AGNIE_PROJECT) String project) {
		this.agnieHome = CommonModule.agnieHome;
		this.project = project;
		this.projectHome = agnieHome + "/" + project;
		this.projectConfig = projectHome + "/config";

	}

	public String getAgnieHome() {
		return agnieHome;
	}

	public String getHome() {
		return projectHome;
	}

	public String getConfig() {
		return projectConfig;
	}

	public String getBin() {
		return projectBin;
	}

}

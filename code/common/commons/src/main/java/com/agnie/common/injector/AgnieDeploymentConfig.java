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

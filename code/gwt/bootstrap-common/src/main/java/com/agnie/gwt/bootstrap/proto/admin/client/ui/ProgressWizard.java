/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

/**
 * @author Pandurang Patil 25-Aug-2014
 *
 */
public class ProgressWizard extends FlowPanel {
	public ProgressWizard() {
		setStyleName(ProtoStyles.WIZARD_PROGRESS);
	}

	public void setLarge(boolean flag) {
		addStyleName(ProtoStyles.WIZARD_PROGRESS_LG);
	}
}

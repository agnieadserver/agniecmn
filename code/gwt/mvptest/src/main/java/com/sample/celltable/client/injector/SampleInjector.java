/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.sample.celltable.client.injector;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.sample.celltable.client.CellTableAppController;
import com.sample.celltable.client.factory.ClientFactory;
import com.sample.celltable.client.factory.ViewFactory;

/**
 * @author Pandurang Patil 25-Oct-2014
 *
 */
@GinModules(GinModule.class)
public interface SampleInjector extends Ginjector {

	ClientFactory getClientFactory();

	ViewFactory getViewFactory();

	CellTableAppController getAppController();

}

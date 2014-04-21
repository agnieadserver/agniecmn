/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.injector;

/**
 * Defined a interface to start and stop persistence service also to begin and end unit of work. In multiple Persistent
 * unit environment.
 * 
 */
public interface PersistenceLifeCycleManager {

	public void startService();

	public void stopService();

	public void beginUnitOfWork();

	public void endUnitOfWork();
}

/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.shutdown;

/**
 * @author Pandurang Patil 15-Mar-2014
 * 
 */
public interface ShutdownHook {
	/**
	 * 
	 * @param sync represents call should be returned only after shutdown is done.
	 */
	void shutdown(boolean sync);
}

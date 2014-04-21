/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.requestfactory;

import org.apache.log4j.Logger;

import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class AgnieRFExceptionHandler implements ExceptionHandler {
	private static org.apache.log4j.Logger	logger	= Logger.getLogger(AgnieRFExceptionHandler.class);

	public ServerFailure createServerFailure(Throwable throwable) {
		throwable.printStackTrace();
		logger.error(throwable);
		return new ServerFailure((throwable == null ? null : throwable.getMessage()), (throwable == null ? null : throwable.getClass().getName()), null, true);
	}

}

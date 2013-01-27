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

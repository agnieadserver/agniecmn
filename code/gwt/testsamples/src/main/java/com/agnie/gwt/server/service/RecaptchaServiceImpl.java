/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.server.service;

import com.agnie.gwt.client.service.RecaptchService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sfeir.captcha.server.CaptchaValidator;
import com.sfeir.captcha.shared.CaptchaResult;

/**
 * @author Pandurang Patil 26-Feb-2014
 * 
 */
public class RecaptchaServiceImpl extends RemoteServiceServlet implements RecaptchService {

	private static final long	serialVersionUID	= 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agnie.gwt.client.service.RecaptchService#validate(com.sfeir.captcha.shared.CaptchaResult)
	 */
	public boolean validate(CaptchaResult captcha) {
		CaptchaValidator validator = new CaptchaValidator("6LdyNO8SAAAAAL4NUIBXw-eYMgA4NpbvcbivPTUq");

		return validator.validateCaptcha(captcha, getThreadLocalRequest().getRemoteAddr());
	}

}

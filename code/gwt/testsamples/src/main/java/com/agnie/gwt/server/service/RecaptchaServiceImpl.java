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

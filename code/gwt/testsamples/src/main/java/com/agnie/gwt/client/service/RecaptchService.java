package com.agnie.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sfeir.captcha.shared.CaptchaResult;

/**
 * @author Pandurang Patil 26-Feb-2014
 * 
 */
@RemoteServiceRelativePath("captchaService")
public interface RecaptchService extends RemoteService {

	boolean validate(CaptchaResult captcha);

}

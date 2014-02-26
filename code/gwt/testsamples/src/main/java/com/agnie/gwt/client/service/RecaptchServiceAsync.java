package com.agnie.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sfeir.captcha.shared.CaptchaResult;

/**
 * @author Pandurang Patil 26-Feb-2014
 * 
 */
public interface RecaptchServiceAsync {

	void validate(CaptchaResult captcha, AsyncCallback<Boolean> callback);

}

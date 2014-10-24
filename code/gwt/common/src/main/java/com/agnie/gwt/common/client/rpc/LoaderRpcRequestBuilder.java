/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.rpc;

import com.agnie.common.gwt.serverclient.client.enums.QueryString;
import com.agnie.common.gwt.serverclient.client.helper.URLGenerator;
import com.agnie.common.gwt.serverclient.client.helper.URLInfo;
import com.agnie.common.gwt.serverclient.client.injector.CommonServerClientModule;
import com.agnie.gwt.common.client.widget.Loader;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * RpcRequest builder to show modal loader .gif image before making call and hide once we receive the response.
 * 
 */
@Singleton
public class LoaderRpcRequestBuilder extends RpcRequestBuilder {
	@Inject
	@Named(CommonServerClientModule.CURRENT_APP_DOMAIN)
	String			appDomain;
	@Inject
	URLGenerator	urlGenerator;
	@Inject
	URLInfo			urlInfo;

	private Loader	loader;

	private class RequestCallbackWrapper implements RequestCallback {

		private RequestCallback	callback;

		RequestCallbackWrapper(RequestCallback aCallback) {
			this.callback = aCallback;
		}

		@Override
		public void onResponseReceived(Request request, Response response) {
			loader.hide();
			if (Response.SC_UNAUTHORIZED == response.getStatusCode()) {
				GWT.log("User session timed out or user logged out");
				Window.Location.assign(urlGenerator.getClientSideLoginURL(urlInfo, appDomain, urlInfo.getParameter(QueryString.GWT_DEV_MODE.getKey())));
			} else {
				callback.onResponseReceived(request, response);
			}
		}

		@Override
		public void onError(Request request, Throwable exception) {
			loader.hide();
			callback.onError(request, exception);
		}

	}

	public LoaderRpcRequestBuilder(Loader loader) {
		this.loader = loader;
		loader.hide();
	}

	public LoaderRpcRequestBuilder() {
		this(new Loader());
	}

	@Override
	protected RequestBuilder doCreate(String serviceEntryPoint) {
		RequestBuilder rb = super.doCreate(serviceEntryPoint);
		loader.show();
		return rb;
	}

	@Override
	protected void doFinish(RequestBuilder rb) {
		super.doFinish(rb);
		rb.setCallback(new RequestCallbackWrapper(rb.getCallback()));
	}
}

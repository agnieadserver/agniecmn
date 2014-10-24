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
import com.agnie.gwt.common.client.widget.LoaderResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Request factory intercepter to show loader image while call is under progress and stop it once we recieve the
 * response.
 * 
 */
@Singleton
public class LoaderRequestTransport extends DefaultRequestTransport {

	@Inject
	@Named(CommonServerClientModule.CURRENT_APP_DOMAIN)
	String			appDomain;
	@Inject
	URLGenerator	urlGenerator;
	@Inject
	URLInfo			urlInfo;

	private Loader	loader;

	public LoaderRequestTransport(Loader loader) {
		this.loader = loader;
		loader.hide();
	}

	public LoaderRequestTransport() {
		this(new Loader(LoaderResources.INSTANCE.communicating()));
	}

	@Override
	public void send(final String payload, final TransportReceiver receiver) {
		GWT.log("making rpc");
		final TransportReceiver proxy = new TransportReceiver() {
			@Override
			public void onTransportFailure(final ServerFailure failure) {
				GWT.log("rpc returned");
				loader.hide();
				receiver.onTransportFailure(failure);
			}

			@Override
			public void onTransportSuccess(final String payload) {
				loader.hide();
				GWT.log("rpc returned");
				receiver.onTransportSuccess(payload);
			}
		};
		loader.show();
		super.send(payload, proxy);
	}

	protected RequestCallback createRequestCallback(final TransportReceiver receiver) {
		final RequestCallback callback = super.createRequestCallback(receiver);
		RequestCallback newCallback = new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				if (Response.SC_UNAUTHORIZED == response.getStatusCode()) {
					GWT.log("User session timed out or user logged out");
					Window.Location.assign(urlGenerator.getClientSideLoginURL(urlInfo, appDomain, urlInfo.getParameter(QueryString.GWT_DEV_MODE.getKey())));
				} else {
					callback.onResponseReceived(request, response);
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				callback.onError(request, exception);

			}
		};
		return newCallback;
	}
}

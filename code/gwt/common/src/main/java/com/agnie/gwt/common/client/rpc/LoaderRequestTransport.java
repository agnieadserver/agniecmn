package com.agnie.gwt.common.client.rpc;

import com.agnie.gwt.common.client.widget.Loader;
import com.agnie.gwt.common.client.widget.LoaderResources;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Request factory intercepter to show loader image while call is under progress and stop it once we recieve the
 * response.
 * 
 */
public class LoaderRequestTransport extends DefaultRequestTransport {
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
}

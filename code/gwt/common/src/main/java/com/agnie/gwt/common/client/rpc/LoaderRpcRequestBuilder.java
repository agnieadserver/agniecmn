package com.agnie.gwt.common.client.rpc;

import com.agnie.gwt.common.client.widget.Loader;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;

/**
 * RpcRequest builder to show modal loader .gif image before making call and hide once we receive the response.
 * 
 */
public class LoaderRpcRequestBuilder extends RpcRequestBuilder {
	private Loader	loader;

	private class RequestCallbackWrapper implements RequestCallback {

		private RequestCallback	callback;

		RequestCallbackWrapper(RequestCallback aCallback) {
			this.callback = aCallback;
		}

		@Override
		public void onResponseReceived(Request request, Response response) {
			loader.hide();
			callback.onResponseReceived(request, response);
		}

		@Override
		public void onError(Request request, Throwable exception) {
			loader.hide();
			callback.onError(request, exception);
		}

	}

	public LoaderRpcRequestBuilder(Loader loader) {
		this.loader = loader;
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

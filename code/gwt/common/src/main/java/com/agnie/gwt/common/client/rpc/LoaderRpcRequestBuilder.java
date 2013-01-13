package com.agnie.gwt.common.client.rpc;

import com.agnie.gwt.common.client.widget.Loader;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;

/**
 * RpcRequest builder to show modal loader .gif image before making call and hide once we receive the response.
 * 
 */
public class LoaderRpcRequestBuilder extends RpcRequestBuilder {
	private Loader	loader;

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
		loader.hide();
	}
}

package com.agnie.common.email;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class TestModule extends AbstractModule {

	public static final String	TEST_TEMPLATE	= "test_msg.tpl";

	@Override
	protected void configure() {
		bind(MessageTemplate.class).annotatedWith(Names.named(TEST_TEMPLATE)).toInstance(new MessageTemplate(TEST_TEMPLATE));
	}
}

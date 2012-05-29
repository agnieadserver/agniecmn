package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;

public @interface Map {
	Class<? extends Annotation> constraint();

	Class<? extends Validator> validator();
}

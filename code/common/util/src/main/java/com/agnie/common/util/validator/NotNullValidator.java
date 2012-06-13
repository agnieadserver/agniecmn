package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;

/**
 * Vallidator for @NotNull constraint
 * 
 */
@Constraint(NotNull.class)
public class NotNullValidator extends Validator {

	public NotNullValidator(Annotation constraint) {
		super(constraint);
	}

	public boolean validate(String value) {
		return (value != null && !("".equals(value)));
	}

}

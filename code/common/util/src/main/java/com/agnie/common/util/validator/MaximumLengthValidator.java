package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;

/**
 * Validator for @MaximumLength constraint
 * 
 */
public class MaximumLengthValidator extends Validator {

	private MaximumLength	maxConstraint;

	public MaximumLengthValidator(Annotation constraint) {
		super(constraint);
		if (constraint instanceof MaximumLength) {
			maxConstraint = (MaximumLength) constraint;
		}

	}

	@Override
	public boolean validate(String value) {
		if (maxConstraint != null && value != null) {
			if (value.length() <= maxConstraint.value())
				return true;
		}
		return false;
	}

}

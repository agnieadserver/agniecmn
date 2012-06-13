package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;

/**
 * Validator for @MinimumLength constraint
 * 
 */
@Constraint(MinimumLength.class)
public class MinimumLengthValidator extends Validator {

	private MinimumLength	minConstraint;

	public MinimumLengthValidator(Annotation constraint) {
		super(constraint);
		if (constraint instanceof MinimumLength) {
			minConstraint = (MinimumLength) constraint;
		}

	}

	@Override
	public boolean validate(String value) {
		if (minConstraint != null && value != null && !("".equals(value))) {
			if (value.length() >= minConstraint.value())
				return true;
		}
		return false;
	}

}

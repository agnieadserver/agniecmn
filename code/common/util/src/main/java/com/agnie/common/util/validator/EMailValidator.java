package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.agnie.common.util.client.validator.ConstraintRegularExpressions;

/**
 * Validator for @EMail constraint
 * 
 */
@Constraint(EMail.class)
public class EMailValidator extends Validator {

	@SuppressWarnings("unused")
	private EMail	eMailConstraint;

	public EMailValidator(Annotation constraint) {
		super(constraint);
		if (constraint instanceof EMail) {
			eMailConstraint = (EMail) constraint;
		}

	}

	@Override
	public boolean validate(String value) {

		if (value != null) {
			Pattern pattern = Pattern.compile(ConstraintRegularExpressions.EMAIL_VALIDATOR_EXP);
			Matcher matcher = pattern.matcher(value);
			return matcher.matches();
		}
		return false;
	}

}

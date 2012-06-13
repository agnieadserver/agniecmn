package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for @EMail constraint
 * 
 */
@Constraint(EMail.class)
public class EMailValidator extends Validator {

	private EMail	eMailConstraint;

	public EMailValidator(Annotation constraint) {
		super(constraint);
		if (constraint instanceof EMail) {
			eMailConstraint = (EMail) constraint;
		}

	}

	@Override
	public boolean validate(String value) {
		// TODO Auto-generated method stub

		if (value != null) {
			Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher matcher = pattern.matcher(value);
			return matcher.matches();
		}
		return false;
	}

}

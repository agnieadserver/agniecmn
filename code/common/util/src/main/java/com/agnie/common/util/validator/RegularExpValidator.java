package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator for @RegularExp constraint
 * 
 */
@Constraint(RegularExp.class)
public class RegularExpValidator extends Validator {

	private RegularExp	regularExpConstraint;

	public RegularExpValidator(Annotation constraint) {
		super(constraint);
		if (constraint instanceof RegularExp) {
			regularExpConstraint = (RegularExp) constraint;
		}

	}

	@Override
	public boolean validate(String value) {
		// TODO Auto-generated method stub
		
		if(value != null)
		{
			Pattern pattern = Pattern.compile(regularExpConstraint.value());
			Matcher matcher = pattern.matcher(value);
			return matcher.matches();
		}
		return false;
	}

}

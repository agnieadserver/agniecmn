/**
 * 
 */
package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;

/**
 * Base validator
 */
public abstract class Validator {

	protected Annotation	constraint;

	/**
	 * @param constraint
	 */
	public Validator(Annotation constraint) {
		this.constraint = constraint;
	}

	/**
	 * When validator instance is created it will be passed with constraint annotation applied on the entity. If your
	 * validator requires to take some inputs e.g. max length. That one can take it through constraint and that
	 * constraint is accessible in validator. Only you have to cast it to corresponding constraint you declared in
	 * Validator factory Mapping.
	 * 
	 * @param value
	 * @return
	 */
	public abstract boolean validate(String value);
}

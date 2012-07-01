package com.agnie.common.util.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to provide tokenizer for single column collection type fields.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface UseTokenizer {

	Class<? extends Tokenizer> tokenizer() default DefaultTokenizer.class;

	String separator();
}

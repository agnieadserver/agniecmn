/**
 * 
 */
package com.agnie.gwt.overlaytype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a class to be considered for generating overlay type
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = { ElementType.TYPE })
public @interface OverlayType {

	/**
	 * package for generated source class TODO: default will be same as that of source class
	 * 
	 * @return
	 */
	String sourcePackage() default "";

	/**
	 * Add list of imports if you need to import any class or package inside overlay type
	 * 
	 * @return
	 */
	String[] imports() default {};
	
}

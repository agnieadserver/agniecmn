package com.agnie.gwt.overlaytype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * While generating overlay type of any class. If you want to add some additional method inside Overlaytype. Then use
 * OverlayTypeAddMethod annotation to specify those methods
 * 
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = { ElementType.TYPE })
public @interface OverlayTypeAddMethod {

	/**
	 * Name of the method
	 * 
	 * @return
	 */
	String name();

	/**
	 * Fully qualified name of the class as a return type. Default will be void
	 */
	String returnType() default "void";

	/**
	 * Add space separated access specifiers including static native final
	 * 
	 * @return
	 */
	AcessSepcifier[] accessSpecifiers() default { AcessSepcifier.DEFAULT };

	/**
	 * Add comma separated list of parameter fully qualified class name. If you want to use any other generated overlay
	 * Type class then TODO: Add rest of the comments
	 * 
	 * @return
	 */
	String[] parameters() default {};

	String body();

}

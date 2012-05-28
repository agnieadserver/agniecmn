/**
 * 
 */
package com.agnie.gwt.overlaytype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * You can mark particular getters or setters with this annotation and the given method will be considered while
 * generating the overlay type. Note : as of now only getter and setters are supported
 * 
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = { ElementType.METHOD })
public @interface Method {

	/**
	 * method name in generated file default will be same as that of source
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * Fully qualified name of the class as a return type. Default will be same as that of source return value
	 */
	String returnType() default "";

	/**
	 * Add array of access specifiers default will be public final native
	 * 
	 * @return
	 */
	AcessSepcifier[] accessSpecifiers() default { AcessSepcifier.PUBLIC, AcessSepcifier.FINAL, AcessSepcifier.NATIVE };

	/**
	 * If you want to customise the body of the method then you can use body attribute for the same. Default
	 * implementation would depend on whether it is getter or setter
	 * 
	 * @return
	 */
	String body() default "";
}

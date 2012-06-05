/**
 * 
 */
package com.agnie.gwt.helper.overlaytype.marker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this marker to mark the fields of pojo or bean class which is already marked with {@link OverlayType} annotation.
 * So that when overlay type is generated for the same getter and setter for the marked filed will be generated
 * 
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = { ElementType.FIELD })
public @interface OverlayField {

	/**
	 * You can have your own custom JavaScript body of setterContent
	 * 
	 * @return
	 */
	String setterContent() default "";

	/**
	 * You can have your own custom JavaScript body of getterContent
	 * 
	 * @return
	 */
	String getterContent() default "";

}

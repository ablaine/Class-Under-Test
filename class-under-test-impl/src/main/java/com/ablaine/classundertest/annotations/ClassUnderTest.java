package com.ablaine.classundertest.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to link the test class with the class being tested.
 * 
 * @author Andrew Blaine
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ClassUnderTest {
	/**
	 * The class being tested.
	 * 
	 * <pre>
	 * [com.package.Class].java
	 * </pre>
	 */
	Class<?> value();

	/**
	 * An optional prefix to the class name. By default this is the empty string.
	 * 
	 * <pre>
	 * com.package.[prefix]Class.java
	 * </pre>
	 */
	String prefix() default "";

	/**
	 * An optional suffix on the class name. By default this is "Test".
	 * 
	 * <pre>
	 * com.package.Class[suffix].java
	 * </pre>
	 */
	String suffix() default "Test";
}

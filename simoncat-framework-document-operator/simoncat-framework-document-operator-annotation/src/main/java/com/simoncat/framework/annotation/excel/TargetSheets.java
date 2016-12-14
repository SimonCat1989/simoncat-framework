package com.simoncat.framework.annotation.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TargetSheets {

	public static final String ALL_SHEETS = ".";
	public static final String GROUP_SEPARATOR = ",";
	public static final String RANGE_SEPARATOR = "-";

	String value() default ALL_SHEETS;

}

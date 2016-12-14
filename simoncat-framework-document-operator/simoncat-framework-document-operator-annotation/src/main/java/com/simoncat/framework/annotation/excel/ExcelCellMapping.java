package com.simoncat.framework.annotation.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelCellMapping {

	final static int NO_HEADER_ROW = -1;

	int index() default 0;

	Type type() default Type.STRING;

	int nameRow() default NO_HEADER_ROW;

	public enum Type {
		/**
		 * String Cell type
		 */
		STRING,
		/**
		 * Numeric Cell type
		 */
		NUMERIC,
		/**
		 * Formula Cell type
		 */
		FORMULA,
		/**
		 * Boolean Cell type
		 */
		BOOLEAN,
		/**
		 * Date Cell type
		 */
		DATE,
		/**
		 * Hyper link Cell type
		 */
		HYPERLINK,
		/**
		 * Error Cell type
		 */
		ERROR
	}
}

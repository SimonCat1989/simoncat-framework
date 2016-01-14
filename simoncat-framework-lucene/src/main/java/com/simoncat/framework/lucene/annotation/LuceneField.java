package com.simoncat.framework.lucene.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LuceneField {

	public enum FieldType {

		STRING_FIELD, LONG_FIELD, TEXT_FIELD
	}
	
	/**
	 * Use a field that is indexed (i.e. searchable), but don't tokenize the field into separate words and don't index term frequency or positional
	 * information
	 * 
	 * @return
	 */
	boolean isKey() default false;
	
	String name() default "";

	FieldType type() default FieldType.TEXT_FIELD;

	boolean isStored() default false;

}

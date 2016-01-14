package com.simoncat.framework.lucene.core;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import com.google.common.collect.Lists;
import com.simoncat.framework.lucene.annotation.DeclareAsLuceneIndex;
import com.simoncat.framework.lucene.annotation.LuceneField;
import com.simoncat.framework.lucene.annotation.LuceneField.FieldType;

@Slf4j
public class LuceneAnnotationCompiler {

	public <T> Optional<LuceneIndex> compile(T obj, Class<T> clazz) {
		if (!obj.getClass().getName().equals(clazz.getName())) {
			log.warn("Passed object is not an instance of the class.");
			return Optional.empty();
		}

		if (clazz.isAnnotationPresent(DeclareAsLuceneIndex.class)) {
			Field keyField = null;
			List<Field> fields = Lists.newArrayList();
			for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(LuceneField.class)) {
					Annotation annotation = field.getAnnotation(LuceneField.class);
					LuceneField luceneField = (LuceneField) annotation;
					boolean isKey = luceneField.isKey();
					FieldType type = luceneField.type();
					boolean isStored = luceneField.isStored();
					try {
						field.setAccessible(true);
						Object value = field.get(obj);
						if (isKey) {
							keyField = new StringField(luceneField.name(), convertToString(value), Field.Store.YES);
						} else {
							switch (type) {
							case STRING_FIELD:
								fields.add(new StringField(luceneField.name(), convertToString(value), (isStored ? Field.Store.YES : Field.Store.NO)));
								break;
							case LONG_FIELD:
								fields.add(new LongField(luceneField.name(), (Long) (value), (isStored ? Field.Store.YES : Field.Store.NO)));
								break;
							case TEXT_FIELD:
								fields.add(new TextField(luceneField.name(), convertToString(value), (isStored ? Field.Store.YES : Field.Store.NO)));
								break;
							default:
								log.warn("No supported type.");
							}
						}
					} catch (IllegalArgumentException e) {
						log.warn("Illegal or inappropriate argument with passed object.", e);
					} catch (IllegalAccessException e) {
						log.warn("Does not have access to the definition of the specified field.", e);
					}
				}
			}
			return Optional.of(new LuceneIndex(keyField, fields));
		}

		log.warn("Can NOT compile with LuceneAnnotationCompiler for class: [{}]", clazz.getName());
		return Optional.empty();
	}

	/**
	 * TODO Import serialization here.
	 * 
	 * @param obj
	 * @return
	 */
	private String convertToString(Object obj) {
		return (String) obj;
	}

	public <T> Optional<T> decompile(Document doc, Class<T> clazz) {
		if (clazz.isAnnotationPresent(DeclareAsLuceneIndex.class)) {
			try {
				T result = clazz.newInstance();
				for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
					if (field.isAnnotationPresent(LuceneField.class)) {
						Annotation annotation = field.getAnnotation(LuceneField.class);
						LuceneField luceneField = (LuceneField) annotation;
						String name = luceneField.name();
						boolean isKey = luceneField.isKey();
						FieldType type = luceneField.type();
						boolean isStored = luceneField.isStored();
						field.setAccessible(true);
						if (isKey) {
							field.set(result, doc.getField(name).stringValue());
						} else {
							if (isStored) {
								switch (type) {
								case STRING_FIELD:
									field.set(result, doc.getField(name).stringValue());
									break;
								case LONG_FIELD:
									field.set(result, doc.getField(name).numericValue().longValue());
									break;
								case TEXT_FIELD:
									field.set(result, doc.getField(name).stringValue());
									break;
								default:
									log.warn("No supported type.");
								}
							}
						}
					}
				}
				return Optional.of(result);
			} catch (IllegalArgumentException e) {
				log.warn("Illegal or inappropriate argument with passed object.", e);
			} catch (IllegalAccessException e) {
				log.warn("Does not have access to the definition of the specified field.", e);
			} catch (InstantiationException e) {
				log.warn("Does not have access to the definition of the specified field.", e);
			}
		}

		log.warn("Can NOT decompile with LuceneAnnotationCompiler for class: [{}]", clazz.getName());
		return Optional.empty();
	}
}

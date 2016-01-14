package com.simoncat.framework.lucene.core;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.apache.lucene.document.Field;

@Data
@AllArgsConstructor
public class LuceneIndex {

	private final Field keyField;
	private final List<Field> normalFields;

	public void add(Field field) {
		this.normalFields.add(field);
	}
}

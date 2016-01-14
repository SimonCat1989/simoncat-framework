package com.simoncat.framework.lucene;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.simoncat.framework.lucene.annotation.DeclareAsLuceneIndex;
import com.simoncat.framework.lucene.annotation.LuceneField;
import com.simoncat.framework.lucene.annotation.LuceneField.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DeclareAsLuceneIndex
public class LuceneTestData {

	@LuceneField(isKey=true, name="name")
	private String name;
	
	@LuceneField(name="description", type=FieldType.TEXT_FIELD, isStored=true)
	private String description;
	
	@LuceneField(name="count", type=FieldType.LONG_FIELD)
	private Long count;
}

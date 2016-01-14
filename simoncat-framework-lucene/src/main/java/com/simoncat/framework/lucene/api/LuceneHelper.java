package com.simoncat.framework.lucene.api;

import java.util.Collection;
import java.util.List;


public interface LuceneHelper {

	static final String DEFAULT_INDEX_FOLDER = "index";
	
	enum IndexType {
		IN_MEMORY, DISK
	}

	<T> void initialize(Collection<T> initialDatas, Class<T> clazz);
	
	<T> List<T> search(Class<T> clazz, String field, String queryString, int maxHitsCount);
}

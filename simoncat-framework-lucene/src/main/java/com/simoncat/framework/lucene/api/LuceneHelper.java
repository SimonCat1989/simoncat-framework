package com.simoncat.framework.lucene.api;


public interface LuceneHelper {

	static final String DEFAULT_INDEX_FOLDER = "index";
	
	enum IndexType {
		IN_MEMORY, DISK
	}

}

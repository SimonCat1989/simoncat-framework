package com.simoncat.framework.lucene.api;

import java.io.IOException;

public interface LuceneInitializeFactory {

	void initialize() throws IOException;

	void initialize(String filePath) throws IOException;

	void initializeWithResourceFolder() throws IOException;

	void initializeWithResourceFolder(ClassLoader loader) throws IOException;

	void initializeWithResourceFolder(ClassLoader loader, String indexFolderPath) throws IOException;
}

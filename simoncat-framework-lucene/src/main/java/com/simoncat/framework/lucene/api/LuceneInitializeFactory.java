package com.simoncat.framework.lucene.api;

import java.io.IOException;

import com.simoncat.framework.lucene.core.LuceneHelperImpl;

public class LuceneInitializeFactory {

	public static LuceneHelper createInstanceWithMemoryOnly() throws IOException {
		return createInstanceWithMemoryOnly(false);
	}

	public static LuceneHelper createInstanceWithMemoryOnly(boolean isUpdateMode) throws IOException {
		return new LuceneHelperImpl(true, null, isUpdateMode);
	}

	public static LuceneHelper createInstanceWithFileSystem(String indexFolder) throws IOException {
		return createInstanceWithFileSystem(indexFolder, false);
	}

	public static LuceneHelper createInstanceWithFileSystem(String indexFolder, boolean isUpdateMode) throws IOException {
		return new LuceneHelperImpl(false, indexFolder, isUpdateMode);
	}

	public static LuceneHelper createInstanceWithResourceFolder(ClassLoader loader, String indexFolder) throws IOException {
		return createInstanceWithResourceFolder(loader, indexFolder, false);
	}

	public static LuceneHelper createInstanceWithResourceFolder(ClassLoader loader, String indexFolder, boolean isUpdateMode) throws IOException {
		return new LuceneHelperImpl(false, loader.getResource(indexFolder).getFile(), isUpdateMode);
	}
}

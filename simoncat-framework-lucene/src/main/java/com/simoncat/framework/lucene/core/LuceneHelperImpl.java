package com.simoncat.framework.lucene.core;

import java.io.IOException;
import java.nio.file.FileSystems;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import com.simoncat.framework.lucene.api.LuceneHelper;

public class LuceneHelperImpl implements LuceneHelper {

	private Directory directory;
	private IndexWriter writer;
	private IndexReader reader;

	public LuceneHelperImpl() {

	}

	public Directory getDirectory() {
		return this.directory;
	}

	public IndexWriter getWriter() {
		return this.writer;
	}

	public IndexReader getReader() {
		return this.reader;
	}

	public void initialize() throws IOException {
		/**
		 * Create lucene directory inside of memory. Please note, it is not stored in disk.
		 */
		this.directory = new RAMDirectory();
		doInitialize(this.directory);
	}

	public void initialize(String filePath) throws IOException {
		this.directory = FSDirectory.open(FileSystems.getDefault().getPath(filePath));
		doInitialize(this.directory);
	}

	public void initializeWithResourceFolder() throws IOException {
		initializeWithResourceFolder(getClass().getClassLoader());
	}

	public void initializeWithResourceFolder(ClassLoader loader) throws IOException {
		initializeWithResourceFolder(loader, DEFAULT_INDEX_FOLDER);
	}

	public void initializeWithResourceFolder(ClassLoader loader, String indexFolderPath) throws IOException {
		initialize(loader.getResource(indexFolderPath).getFile());
	}

	private void doInitialize(Directory directory) throws IOException {
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		this.writer = new IndexWriter(directory, indexWriterConfig);

		Document document = new Document();
		Field field = new StringField("fileName", "testname", Store.YES);
		document.add(field);
		Field field2 = new LongField("fileSize", 12, Store.NO);
		document.add(field2);
		Field field3 = new LongField("fileLastModified", 12, Store.NO);
		document.add(field3);
		// Field field4 = new TextField("content", new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8)));
		// document.add(field4);
		
		if (writer.getConfig().getOpenMode() == OpenMode.CREATE)  
        {  
            writer.addDocument(document);  
        } else  
        {  
            writer.updateDocument(new Term("path", f.toString()), document);  
        }  
	}
}

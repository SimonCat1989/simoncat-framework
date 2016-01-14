package com.simoncat.framework.lucene.core;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import com.google.common.collect.Lists;
import com.simoncat.framework.lucene.api.LuceneHelper;

@Slf4j
public class LuceneHelperImpl implements LuceneHelper {

	private final Directory directory;
	private final boolean isUpdateMode;

	private final static LuceneAnnotationCompiler compiler = new LuceneAnnotationCompiler();

	public LuceneHelperImpl(boolean isInMemoryMode, String indexFolder, boolean isUpdateMode) throws IOException {
		if (isInMemoryMode) {
			this.directory = new RAMDirectory();
			this.isUpdateMode = isUpdateMode;
		} else {
			this.directory = FSDirectory.open(FileSystems.getDefault().getPath(indexFolder));
			this.isUpdateMode = isUpdateMode;
		}
	}

	@Override
	public <T> void initialize(Collection<T> initialDatas, Class<T> clazz) {
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
		indexWriterConfig.setOpenMode(this.isUpdateMode ? OpenMode.CREATE_OR_APPEND : OpenMode.CREATE);

		try (IndexWriter writer = new IndexWriter(this.directory, indexWriterConfig)) {
			initialDatas.stream().forEach(initialData -> {
				doInitialize(initialData, clazz, writer);
			});
		} catch (IOException e) {
			log.warn("Initial data contains error.", e);
		}
	}

	private <T> void doInitialize(T initialData, Class<T> clazz, IndexWriter writer) {
		Document document = new Document();

		Optional<LuceneIndex> indeces = compiler.compile(initialData, clazz);
		if (indeces.isPresent()) {
			Field keyField = indeces.get().getKeyField();
			document.add(keyField);
			List<Field> fieldList = indeces.get().getNormalFields();
			fieldList.stream().forEach(field -> {
				document.add(field);
			});
			try {
				if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
					writer.addDocument(document);
				} else {
					writer.updateDocument(new Term(keyField.name(), keyField.stringValue()), document);
				}
			} catch (IOException e) {
				log.warn("Initial data contains error.", e);
			}
		}
	}

	@Override
	public <T> List<T> search(Class<T> clazz, String field, String queryString, int maxHitsCount) {
		try (IndexReader reader = DirectoryReader.open(this.directory)) {
			IndexSearcher searcher = new IndexSearcher(reader);
			Query query = new QueryParser(field, new StandardAnalyzer()).parse(queryString);
			ScoreDoc[] hits = searcher.search(query, maxHitsCount).scoreDocs;
			List<T> resultList = Lists.newArrayList();
			for (ScoreDoc hit : hits) {
				Document doc = searcher.doc(hit.doc);
				Optional<T> result = compiler.decompile(doc, clazz);
				if (result.isPresent()) {
					resultList.add(result.get());
				}
			}
			return resultList;
		} catch (IOException e) {
			log.warn("Search data contains error.", e);
		} catch (ParseException e) {
			log.warn("Search data contains error.", e);
		}
		return Collections.emptyList();
	}
}

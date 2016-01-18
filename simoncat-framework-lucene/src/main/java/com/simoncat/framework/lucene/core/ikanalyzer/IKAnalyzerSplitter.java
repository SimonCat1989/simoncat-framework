package com.simoncat.framework.lucene.core.ikanalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.google.common.collect.Lists;

@Slf4j
public class IKAnalyzerSplitter {

	/**
	 * 
	 * 
	 * @param text
	 * @param isMaxWordLength
	 * @return
	 */
	public static List<String> split(String text, boolean isMaxWordLength) {
		try (StringReader reader = new StringReader(text)) {
			List<String> resultList = Lists.newArrayList();
			Analyzer analyzer = new IKAnalyzer(isMaxWordLength);
			TokenStream ts = analyzer.tokenStream("", reader);
			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
			while (ts.incrementToken()) {
				resultList.add(term.toString());
			}
			return resultList;
		} catch (IOException e) {
			log.warn("IO exception", e);
			return Collections.emptyList();
		}
	}
}

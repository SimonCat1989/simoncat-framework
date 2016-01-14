package com.simoncat.framework.lucene;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.simoncat.framework.lucene.api.LuceneHelper;
import com.simoncat.framework.lucene.api.LuceneInitializeFactory;
import com.simoncat.framework.lucene.config.LuceneConfig;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LuceneConfig.class)
public class LuceneHelperTest {

	@Test
	public void testLuceneHelperWithMemoryOnly() {
		try {
			LuceneTestData data1 = new LuceneTestData("test1", "Hello world", 12L);
			LuceneTestData data2 = new LuceneTestData("test2", "I like this world", 24L);
			LuceneTestData data3 = new LuceneTestData("test3", "Sometimes i like to go aboard", 36L);
			
//			LuceneHelper luceneHelper = LuceneInitializeFactory.createInstanceWithResourceFolder(this.getClass().getClassLoader(), "index");
			LuceneHelper luceneHelper = LuceneInitializeFactory.createInstanceWithMemoryOnly();
			luceneHelper.initialize(Lists.newArrayList(data1, data2, data3), LuceneTestData.class);
			
			List<LuceneTestData> result = luceneHelper.search(LuceneTestData.class, "description", "like", 3);
			
			log.info(result.toString());
			assertTrue(result.size() == 2);
		} catch (IOException e) {
			fail("Fail");
		}
	}
	
	@Test
	public void testLuceneHelperWithResourceFolder() {
		try {
			LuceneTestData data1 = new LuceneTestData("test1", "Hello world", 12L);
			LuceneTestData data2 = new LuceneTestData("test2", "I like this world", 24L);
			LuceneTestData data3 = new LuceneTestData("test3", "Sometimes i like to go aboard", 36L);
			
			LuceneHelper luceneHelper = LuceneInitializeFactory.createInstanceWithResourceFolder(this.getClass().getClassLoader(), "index");
			luceneHelper.initialize(Lists.newArrayList(data1, data2, data3), LuceneTestData.class);
			
			List<LuceneTestData> result = luceneHelper.search(LuceneTestData.class, "description", "like", 3);
			
			log.info(result.toString());
			assertTrue(result.size() == 2);
		} catch (IOException e) {
			fail("Fail");
		}
	}
}

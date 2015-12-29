package com.simoncat.framework.lucene.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.simoncat.framework.lucene.api.LuceneHelper;
import com.simoncat.framework.lucene.core.LuceneHelperImpl;

@Configuration
@PropertySource(value = "classpath:lucene.properties", ignoreResourceNotFound = true)
public class LuceneConfig {

	@Value("${redis.cluster.nodes}")
    private String redisIPsAndPorts;

    @Value("${redis.cluster.timeout}")
    private int redisClusterTimeout;
	
	@Bean
	public LuceneHelper luceneHelper() {
		return new LuceneHelperImpl();
	}
}

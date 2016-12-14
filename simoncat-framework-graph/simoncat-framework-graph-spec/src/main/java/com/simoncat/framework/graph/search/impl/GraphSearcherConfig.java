package com.simoncat.framework.graph.search.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simoncat.framework.graph.search.GraphSearcher;

@Configuration
public class GraphSearcherConfig {

	@Bean
	public <VERTEX_VAL, EDGE_VAL> GraphSearcher<VERTEX_VAL, EDGE_VAL> graphSearcher() {
		return new GraphSearcherImpl<VERTEX_VAL, EDGE_VAL>();
	}
}

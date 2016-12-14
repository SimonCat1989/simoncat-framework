package com.simoncat.framework.graph.elements;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simoncat.framework.graph.elements.impl.GraphFactoryImpl;

@Configuration
public class GraphConfig {

	@Bean
	public GraphFactory graphFactory() {
		return new GraphFactoryImpl();
	}
}

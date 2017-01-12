package com.simoncat.framework.json.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simoncat.framework.json.api.JsonOperator;
import com.simoncat.framework.json.core.JacksonJsonOperator;

@Configuration
public class JsonConfigImpl implements JsonConfig {

	@Bean
	@Override
	public JsonOperator jsonOperator() {
		return new JacksonJsonOperator();
	}

}

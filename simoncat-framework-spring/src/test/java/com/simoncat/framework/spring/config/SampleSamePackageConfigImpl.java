package com.simoncat.framework.spring.config;

import org.springframework.context.annotation.Bean;

class SampleSamePackageConfigImpl implements SampleSamePackageConfig {

	@Bean
	@Override
	public SampleBean sampleBean() {
		return new SampleBeanImpl();
	}

	static class SampleBeanImpl implements SampleBean {

	}
}

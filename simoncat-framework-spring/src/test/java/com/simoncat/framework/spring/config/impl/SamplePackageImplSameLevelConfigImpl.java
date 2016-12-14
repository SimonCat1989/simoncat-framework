package com.simoncat.framework.spring.config.impl;

import org.springframework.context.annotation.Bean;

import com.simoncat.framework.spring.config.SampleBean;
import com.simoncat.framework.spring.config.api.SamplePackageImplSameLevelConfig;

class SamplePackageImplSameLevelConfigImpl implements SamplePackageImplSameLevelConfig {

	@Bean
	@Override
	public SampleBean sampleBean() {
		return new SampleBeanImpl();
	}
}

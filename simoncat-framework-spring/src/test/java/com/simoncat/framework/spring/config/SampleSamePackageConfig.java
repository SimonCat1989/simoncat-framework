package com.simoncat.framework.spring.config;

@ConfigurationSpec(SampleSamePackageConfig.class)
public interface SampleSamePackageConfig {

	SampleBean sampleBean();
}

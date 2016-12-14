package com.simoncat.framework.spring.config;

@ConfigurationSpec(value = SamplePackageImplSubLevelConfig.class, location = ConfigurationImplementationClassLocation.PACKAGE_IMPL_SUB_LEVEL)
public interface SamplePackageImplSubLevelConfig {

	SampleBean sampleBean();
}

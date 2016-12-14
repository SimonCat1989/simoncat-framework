package com.simoncat.framework.spring.config.api;

import com.simoncat.framework.spring.config.ConfigurationImplementationClassLocation;
import com.simoncat.framework.spring.config.ConfigurationSpec;
import com.simoncat.framework.spring.config.SampleBean;

@ConfigurationSpec(value = SamplePackageImplSameLevelConfig.class, location = ConfigurationImplementationClassLocation.PACKAGE_IMPL_SAME_LEVEL)
public interface SamplePackageImplSameLevelConfig {

	SampleBean sampleBean();
}

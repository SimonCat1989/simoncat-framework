package com.simoncat.framework.config.supplier.api;

import com.simoncat.framework.spring.config.ConfigurationImplementationClassLocation;
import com.simoncat.framework.spring.config.ConfigurationSpec;

@ConfigurationSpec(value = ConfigSupplierConfig.class, location = ConfigurationImplementationClassLocation.PACKAGE_CORE_SAME_LEVEL)
public interface ConfigSupplierConfig {

	ConfigSupplier configSupplier();
}

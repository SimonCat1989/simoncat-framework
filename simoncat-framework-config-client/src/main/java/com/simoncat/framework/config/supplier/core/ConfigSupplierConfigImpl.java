package com.simoncat.framework.config.supplier.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simoncat.framework.config.supplier.api.ConfigSupplier;
import com.simoncat.framework.config.supplier.api.ConfigSupplierConfig;

@Configuration
class ConfigSupplierConfigImpl implements ConfigSupplierConfig {

	@Bean
	@Override
	public ConfigSupplier configSupplier() {
		return new PropertyConfigSupplier();
	}
}

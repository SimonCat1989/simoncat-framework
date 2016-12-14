package com.simoncat.framework.config.supplier.core;

import java.util.Objects;

import com.simoncat.framework.config.supplier.api.ConfigSupplier;

class PropertyConfigSupplier implements ConfigSupplier {

	@Override
	public <T> T load(Class<T> configClass) {
		Objects.requireNonNull(configClass, "Detect NULL class when try to load configurations.");
		// T configInstance = BeanUtils.instantiate(configClass);
		return null;
	}

}

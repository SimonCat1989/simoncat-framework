package com.simoncat.framework.config.supplier.api;

public interface ConfigSupplier {

	<T> T load(Class<T> configClass);
}

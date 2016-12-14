package com.simoncat.framework.spring.config;

import java.io.File;
import java.nio.file.Paths;

public enum ConfigurationImplementationClassLocation {

	SAME_PACKAGE("./"), PACKAGE_IMPL_SAME_LEVEL("../impl"), PACKAGE_CORE_SAME_LEVEL("../core"), PACKAGE_IMPL_SUB_LEVEL("impl"), PACKAGE_CORE_SUB_LEVEL(
			"core");

	private String implementationClassLocation;

	private ConfigurationImplementationClassLocation(String implementationClassLocation) {
		this.implementationClassLocation = implementationClassLocation;
	}

	public String getLocation(Class<?> specClass) {
		return Paths.get(specClass.getPackage().getName().replaceAll("\\.", File.separator), implementationClassLocation, specClass.getSimpleName())
				.normalize().toString().replaceAll(File.separator, "\\.");
	}
}

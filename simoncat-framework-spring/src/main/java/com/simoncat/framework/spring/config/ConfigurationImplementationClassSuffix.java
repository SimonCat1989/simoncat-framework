package com.simoncat.framework.spring.config;

public enum ConfigurationImplementationClassSuffix {

	SUFFIX_NONE(""), SUFFIX_IMPL("Impl");

	private String suffix;

	private ConfigurationImplementationClassSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}

}

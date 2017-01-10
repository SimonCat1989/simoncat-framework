package com.simoncat.framework.json.config;

import com.simoncat.framework.json.api.JsonOperator;
import com.simoncat.framework.spring.config.ConfigurationSpec;

@ConfigurationSpec(JsonConfig.class)
public interface JsonConfig {

	public JsonOperator jsonOperator();
}

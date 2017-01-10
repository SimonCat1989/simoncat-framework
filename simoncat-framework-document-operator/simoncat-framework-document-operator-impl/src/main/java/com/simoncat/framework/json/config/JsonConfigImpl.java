package com.simoncat.framework.json.config;

import com.simoncat.framework.json.api.JsonOperator;
import com.simoncat.framework.json.core.JacksonJsonOperator;

public class JsonConfigImpl implements JsonConfig {

	@Override
	public JsonOperator jsonOperator() {
		return new JacksonJsonOperator();
	}

}

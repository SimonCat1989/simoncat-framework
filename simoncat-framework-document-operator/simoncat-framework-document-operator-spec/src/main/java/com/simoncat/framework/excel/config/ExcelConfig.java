package com.simoncat.framework.excel.config;

import com.simoncat.framework.excel.api.ExcelOperator;
import com.simoncat.framework.spring.config.ConfigurationSpec;

@ConfigurationSpec(ExcelConfig.class)
public interface ExcelConfig {

	public ExcelOperator excelOperator();
}

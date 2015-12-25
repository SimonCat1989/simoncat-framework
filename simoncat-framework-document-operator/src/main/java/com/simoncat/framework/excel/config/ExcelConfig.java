package com.simoncat.framework.excel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simoncat.framework.excel.api.ExcelOperator;
import com.simoncat.framework.excel.core.ExcelOperatorImpl;

@Configuration
public class ExcelConfig {

	@Bean
    public ExcelOperator excelOperator() {
        return new ExcelOperatorImpl();
    }
}

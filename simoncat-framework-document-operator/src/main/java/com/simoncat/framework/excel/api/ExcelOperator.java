package com.simoncat.framework.excel.api;

public interface ExcelOperator {

	void operation(Parameter parameter, Job job);
	
	void create(Parameter parameter, Job job);
	
	void update(Parameter parameter, Job job);
	
}

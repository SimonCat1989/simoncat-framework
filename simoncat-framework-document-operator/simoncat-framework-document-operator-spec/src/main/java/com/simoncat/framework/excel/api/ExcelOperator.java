package com.simoncat.framework.excel.api;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelOperator {

	<T> List<T> readAll(Parameter parameter, Class<T> instanceClass);

	void operation(Parameter parameter, Job job);

	void create(Parameter parameter, Job job);

	void update(Parameter parameter, Job job);

	@FunctionalInterface
	interface Job {

		void doJob(Workbook document);
	}
}

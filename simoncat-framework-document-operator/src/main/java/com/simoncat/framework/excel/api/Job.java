package com.simoncat.framework.excel.api;

import org.apache.poi.ss.usermodel.Workbook;

@FunctionalInterface
public interface Job {

	void doJob(Workbook document);
}

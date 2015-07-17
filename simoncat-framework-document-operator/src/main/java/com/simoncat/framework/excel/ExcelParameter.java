package com.simoncat.framework.excel;

import java.io.File;

import lombok.Data;

@Data
public class ExcelParameter {

	private final String filePath;
	private final String fileName;

	public String getFile() {
		return filePath + File.separator + fileName;
	}
}

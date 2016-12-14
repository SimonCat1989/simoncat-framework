package com.simoncat.framework.excel.meta;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcelMetaImpl implements ExcelMeta {

	private String fileName;
	private String sheetName;
	private int sheetPosition;
	private int rowPos;

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public String getSheetName() {
		return sheetName;
	}

	@Override
	public int getSheetPostion() {
		return sheetPosition;
	}

	@Override
	public int getRowPosition() {
		return rowPos;
	}
}

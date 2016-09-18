package com.simoncat.framework.excel.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.simoncat.framework.excel.api.annotation.ExcelCellMapping;
import com.simoncat.framework.excel.api.annotation.ExcelCellMapping.Type;
import com.simoncat.framework.excel.api.annotation.ExcelFileInfo;

@Data
@AllArgsConstructor
@ExcelFileInfo(targetSheetPositions = { 0 })
public class SampleInstance {

	@ExcelCellMapping(index = 0, type = Type.STRING, nameRow = 0)
	private String column1;

	@ExcelCellMapping(index = 1, type = Type.STRING, nameRow = 0)
	private String column2;

	@ExcelCellMapping(index = 2, type = Type.STRING, nameRow = 0)
	private String column3;

	@ExcelCellMapping(index = 3, type = Type.NUMERIC, nameRow = 0)
	private double column4;
}

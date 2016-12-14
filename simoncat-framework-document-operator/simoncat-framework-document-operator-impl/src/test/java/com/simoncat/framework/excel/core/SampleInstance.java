package com.simoncat.framework.excel.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.simoncat.framework.annotation.excel.ExcelCellMapping;
import com.simoncat.framework.annotation.excel.ExcelCellMapping.Type;
import com.simoncat.framework.annotation.excel.TargetSheets;
import com.simoncat.framework.annotation.excel.ExcelMetaAnnotation;
import com.simoncat.framework.excel.meta.ExcelMeta;

@Data
@AllArgsConstructor
@TargetSheets
public class SampleInstance {

	@ExcelMetaAnnotation
	private ExcelMeta excelMeta;

	@ExcelCellMapping(index = 0, type = Type.STRING, nameRow = 0)
	private String column1;

	@ExcelCellMapping(index = 1, type = Type.STRING, nameRow = 0)
	private String column2;

	@ExcelCellMapping(index = 2, type = Type.STRING, nameRow = 0)
	private String column3;

	@ExcelCellMapping(index = 3, type = Type.NUMERIC, nameRow = 0)
	private double column4;
}

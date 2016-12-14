package com.simoncat.framework.excel.api;

import java.io.File;
import java.util.Arrays;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Parameter {

	@NonNull
	private final String filePath;

	@NonNull
	private final String fileName;

	@Nullable
	private ExcelType type = ExcelType.XLS;

	@Nullable
	private String password;

	public String getFile() {
		return filePath + File.separator + fileName;
	}

	public enum ExcelType {
		XLS(".xls"), XLSX(".xlsx");

		private String suffix;

		private ExcelType(String suffix) {
			this.suffix = suffix;
		}

		public String getSuffix() {
			return suffix;
		}
	}

	public boolean isExcelFile() {
		return Arrays.stream(ExcelType.values()).anyMatch(excelType -> fileName.toLowerCase().endsWith(excelType.getSuffix()));
	}
}

package com.simoncat.framework.excel.api;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import com.sun.istack.internal.Nullable;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Parameter {

	@NonNull
	private final String filePath;

	@NonNull
	private final String fileName;
		
	@NonNull
	private ExcelType type = ExcelType.XLS;
	
	@Nullable
	private String password;

	public String getFile() {
		return filePath + File.separator + fileName;
	}
}

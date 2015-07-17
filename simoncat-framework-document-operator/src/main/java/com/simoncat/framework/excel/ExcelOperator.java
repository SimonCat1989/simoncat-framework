package com.simoncat.framework.excel;

import java.io.File;
import java.io.IOException;

import lombok.Data;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@Data
public class ExcelOperator {

	private final ExcelParameter parameter;
	
	public Workbook getExcelDocument() {
		try {
			return WorkbookFactory.create(new File(parameter.getFile()));
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

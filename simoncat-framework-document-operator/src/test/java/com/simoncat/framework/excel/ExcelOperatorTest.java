package com.simoncat.framework.excel;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class ExcelOperatorTest {

	private static final ExcelParameter TEST_DOC = new ExcelParameter(
			"./src/test/resources", "test.xlsx");

	@Test
	public void test() {
		ExcelOperator operator = new ExcelOperator(TEST_DOC);
		Workbook document = operator.getExcelDocument();
		Sheet sheet = document.getSheet("Sheet1");
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			String cellNovalue = "";
			Row row = sheet.getRow(i);
			Iterator it = row.cellIterator();
			while (it.hasNext()) {
				Cell cell = (Cell) it.next();
				try {
					cellNovalue = cell.getStringCellValue();
				} catch (IllegalStateException e) {
					try {
						double dcellNovalue = sheet.getRow(i).getCell(0)
								.getNumericCellValue();
						cellNovalue = String.valueOf(dcellNovalue);
					} catch (IllegalStateException e2) {
						cellNovalue = "";
						e.printStackTrace();
					}
				} catch (Exception e3) {
					cellNovalue = "";
					e3.printStackTrace();
				}

				System.out.println("Row=" + i + "; Cell="
						+ cell.getColumnIndex() + "; Value=" + cellNovalue);
			}
		}
	}
}

package com.simoncat.framework.excel.core;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simoncat.framework.excel.api.ExcelOperator;
import com.simoncat.framework.excel.api.Parameter;
import com.simoncat.framework.excel.config.ExcelConfigImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExcelConfigImpl.class)
public class ExcelOperatorImplTest {

	private static final Parameter TEST_DOC = new Parameter("./src/test/resources", "test.xls");

	@Autowired
	private ExcelOperator excelOperator;

	@Test
	public void testRead() {
		excelOperator.operation(TEST_DOC, document -> {
			readData(document);
		});
	}

	private void readData(Workbook document) {
		Sheet sheet = document.getSheet("Sheet1");
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			String cellNovalue = "";
			Row row = sheet.getRow(i);
			Iterator<?> it = row.cellIterator();
			while (it.hasNext()) {
				Cell cell = (Cell) it.next();
				try {
					cellNovalue = cell.getStringCellValue();
				} catch (IllegalStateException e) {
					try {
						double dcellNovalue = sheet.getRow(i).getCell(0).getNumericCellValue();
						cellNovalue = String.valueOf(dcellNovalue);
					} catch (IllegalStateException e2) {
						cellNovalue = "";
						e.printStackTrace();
					}
				} catch (Exception e3) {
					cellNovalue = "";
					e3.printStackTrace();
				}
				System.out.println("Row=" + i + "; Cell=" + cell.getColumnIndex() + "; Value=" + cellNovalue);
			}
		}
	}

	@Test
	public void testWrite() {
		excelOperator.update(TEST_DOC, document -> {
			Sheet sheet = document.getSheet("Sheet1");
			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
				String cellNovalue = "";
				Row row = sheet.getRow(i);
				Iterator<?> it = row.cellIterator();
				while (it.hasNext()) {
					Cell cell = (Cell) it.next();
					try {
						cellNovalue = cell.getStringCellValue();
					} catch (IllegalStateException e) {
						try {
							double dcellNovalue = sheet.getRow(i).getCell(0).getNumericCellValue();
							cellNovalue = String.valueOf(dcellNovalue);
						} catch (IllegalStateException e2) {
							cellNovalue = "";
							e.printStackTrace();
						}
					} catch (Exception e3) {
						cellNovalue = "";
						e3.printStackTrace();
					}
					System.out.println("Row=" + i + "; Cell=" + cell.getColumnIndex() + "; Value=" + cellNovalue);

					cell.setCellValue(cellNovalue + "_modified");
				}
			}
		});

		excelOperator.operation(TEST_DOC, document -> {
			readData(document);
		});
	}

	@Test
	public void testCreate() {
		excelOperator.create(new Parameter("./src/test/resources", getCurrentTimeStamp() + "-test.xlsx", Parameter.ExcelType.XLSX, null),
				document -> {
					Sheet sheet = document.createSheet("TemplateSheet");
					Row row = sheet.createRow(0);
					for (int i = 0; i < 5; i++) {
						Cell cell = row.createCell(i);
						cell.setCellValue("Cell" + i);
					}
				});
	}

	private String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss-");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	@Test
	public void testReadAll() {
		Collection<SampleInstance> list = excelOperator.readAll(TEST_DOC, SampleInstance.class);
		System.out.println(list.size());
	}
}

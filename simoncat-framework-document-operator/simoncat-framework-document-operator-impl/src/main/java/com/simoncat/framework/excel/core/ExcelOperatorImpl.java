package com.simoncat.framework.excel.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Lists;
import com.simoncat.framework.annotation.excel.ExcelCellMapping;
import com.simoncat.framework.annotation.excel.ExcelCellMapping.Type;
import com.simoncat.framework.annotation.excel.ExcelMetaAnnotation;
import com.simoncat.framework.annotation.excel.TargetSheets;
import com.simoncat.framework.annotation.excel.TargetSheetsUtils;
import com.simoncat.framework.excel.api.ExcelOperator;
import com.simoncat.framework.excel.api.Parameter;
import com.simoncat.framework.excel.meta.ExcelMeta;
import com.simoncat.framework.excel.meta.ExcelMetaImpl;

@Slf4j
public class ExcelOperatorImpl implements ExcelOperator {

	@Override
	public void operation(Parameter parameter, Job job) {
		Objects.requireNonNull(parameter, "Can NOT do operations with EMPTY parameter.");
		Objects.requireNonNull(job, "Can NOT do operations with EMPTY job.");

		try (Workbook document = WorkbookFactory.create(new File(parameter.getFile()), parameter.getPassword())) {
			if (Objects.nonNull(document)) {
				job.doJob(document);
			}
		} catch (EncryptedDocumentException e) {
			log.warn("Can NOT open an encrypted document {}.", parameter.getFile(), e);
		} catch (InvalidFormatException e) {
			log.warn("Can NOT open an invalide format document {}.", parameter.getFile(), e);
		} catch (IOException e) {
			log.warn("IO exceptions happen when open document {}.", parameter.getFile(), e);
		} catch (Exception e) {
			log.warn("Other exceptions happen when open document {}.", parameter.getFile(), e);
		}
	}

	@Override
	public void create(Parameter parameter, Job job) {
		Objects.requireNonNull(parameter, "Can NOT do operations with EMPTY parameter.");
		Objects.requireNonNull(job, "Can NOT do operations with EMPTY job.");

		try (Workbook document = parameter.getType().equals(Parameter.ExcelType.XLS) ? new HSSFWorkbook() : new XSSFWorkbook();
				FileOutputStream fileOut = new FileOutputStream(parameter.getFile())) {
			if (Objects.nonNull(document)) {
				job.doJob(document);
			}
			document.write(fileOut);
			fileOut.flush();
		} catch (FileNotFoundException e) {
			log.warn("Can NOT open a not existing document {}.", parameter.getFile(), e);
		} catch (SecurityException e) {
			log.warn("Can NOT write to a document denies write access to it {}.", parameter.getFile(), e);
		} catch (IOException e) {
			log.warn("IO exceptions happen when create document {}.", parameter.getFile(), e);
		} catch (Exception e) {
			log.warn("Other exceptions happen when open document {}.", parameter.getFile(), e);
		}
	}

	@Override
	public void update(Parameter parameter, Job job) {
		Objects.requireNonNull(parameter, "Can NOT do operations with EMPTY parameter.");
		Objects.requireNonNull(job, "Can NOT do operations with EMPTY job.");

		Parameter tempParameter = createTempFile(parameter);
		try (Workbook document = WorkbookFactory.create(new File(parameter.getFile()), parameter.getPassword());
				FileOutputStream fileOut = new FileOutputStream(tempParameter.getFile())) {
			if (Objects.nonNull(document)) {
				job.doJob(document);
			}
			document.write(fileOut);
			fileOut.flush();
		} catch (EncryptedDocumentException e) {
			log.warn("Can NOT open an encrypted document {}.", parameter.getFile(), e);
		} catch (InvalidFormatException e) {
			log.warn("Can NOT open an invalide format document {}.", parameter.getFile(), e);
		} catch (FileNotFoundException e) {
			log.warn("Can NOT open a not existing document {}.", parameter.getFile(), e);
		} catch (SecurityException e) {
			log.warn("Can NOT write to a document denies write access to it {}.", parameter.getFile(), e);
		} catch (IOException e) {
			log.warn("IO exceptions happen when open document {}.", parameter.getFile(), e);
		} catch (Exception e) {
			log.warn("Other exceptions happen when open document {}.", parameter.getFile(), e);
		}
		File oldFile = new File(parameter.getFile());
		File newFile = new File(tempParameter.getFile());
		oldFile.delete();
		newFile.renameTo(oldFile);
	}

	private Parameter createTempFile(Parameter parameter) {
		return new Parameter(parameter.getFilePath(), getCurrentTimeStamp() + parameter.getFileName());
	}

	private String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss-");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	@Override
	public <R> List<R> readAll(Parameter parameter, Class<R> instanceClass) {
		Objects.requireNonNull(parameter, "Can NOT do operations with EMPTY parameter.");

		try (Workbook document = WorkbookFactory.create(new File(parameter.getFile()), parameter.getPassword())) {
			if (Objects.nonNull(document)) {
				return doReadAll(document, parameter.getFile(), instanceClass);
			}
			log.warn("Can NOT do operations with NULL document");
		} catch (EncryptedDocumentException e) {
			log.warn("Can NOT open an encrypted document {}.", parameter.getFile(), e);
		} catch (InvalidFormatException e) {
			log.warn("Can NOT open an invalide format document {}.", parameter.getFile(), e);
		} catch (IOException e) {
			log.warn("IO exceptions happen when open document {}.", parameter.getFile(), e);
		} catch (Exception e) {
			log.warn("Other exceptions happen when open document {}.", parameter.getFile(), e);
		}
		return Collections.emptyList();
	}

	private <R> List<R> doReadAll(Workbook document, String fileName, Class<R> instanceClass) throws NoSuchMethodException, SecurityException {
		// Process @ExcelFileInfo
		if (instanceClass.isAnnotationPresent(TargetSheets.class)) {
			List<ExcelCellMapping> mappingList = Lists.newArrayList();
			List<Class<?>> constructorArgClassList = Lists.newArrayList();

			// Check ExcelMeta field existing or not.
			boolean isExcelMetaEnabled = Arrays.stream(instanceClass.getDeclaredFields()).anyMatch(
					field -> field.isAnnotationPresent(ExcelMetaAnnotation.class));
			if (isExcelMetaEnabled) {
				constructorArgClassList.add(ExcelMeta.class);
			}

			// Check ExcelCellMapping field and add it.
			Arrays.stream(instanceClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(ExcelCellMapping.class)).forEach(field -> {
				mappingList.add((ExcelCellMapping) field.getAnnotation(ExcelCellMapping.class));
				constructorArgClassList.add(field.getType());
			});

			int headerLine = mappingList.stream().map(ExcelCellMapping::nameRow).reduce(ExcelCellMapping.NO_HEADER_ROW, Integer::max);

			Constructor<R> constructor = instanceClass.getDeclaredConstructor(constructorArgClassList.toArray(new Class<?>[constructorArgClassList
					.size()]));
			TargetSheets fileInfo = (TargetSheets) instanceClass.getAnnotation(TargetSheets.class);
			return Arrays.stream(TargetSheetsUtils.convertToPositionArray(document.getNumberOfSheets(), fileInfo.value())).mapToObj(position -> {
				Collection<R> resultList = Lists.newArrayList();
				Sheet sheet = document.getSheetAt(position);
				for (int i = getFirstRow(headerLine); i < sheet.getPhysicalNumberOfRows(); i++) {
					List<Object> argumentList = Lists.newArrayList();
					if (isExcelMetaEnabled) {
						argumentList.add(new ExcelMetaImpl(fileName, sheet.getSheetName(), position, i));
					}
					argumentList.addAll(getValueFromRow(mappingList, sheet.getRow(i)));
					try {
						resultList.add(constructor.newInstance(argumentList.toArray()));
					} catch (Exception e) {
						log.warn("Can NOT create instance with {}.", argumentList, e);
					}
				}
				return resultList;
			}).flatMap(instanceList -> instanceList.stream()).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private int getFirstRow(int headerLine) {
		return (headerLine > ExcelCellMapping.NO_HEADER_ROW) ? headerLine + 1 : 0;
	}

	private List<Object> getValueFromRow(List<ExcelCellMapping> mappingList, Row row) {
		return mappingList.stream().map(mapping -> getValueFromCell(mapping.type(), row.getCell(mapping.index()))).collect(Collectors.toList());
	}

	private Object getValueFromCell(Type type, Cell cell) {
		switch (type) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return cell.getNumericCellValue();
		case FORMULA:
			return cell.getCellFormula();
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case DATE:
			return cell.getDateCellValue();
		case HYPERLINK:
			return cell.getHyperlink();
		case ERROR:
			return cell.getErrorCellValue();
		default:
			log.warn("Detect Undefined Type of Excel Cell from your usage of ExcelCellMapping annotation.");
			return null;
		}
	}
}

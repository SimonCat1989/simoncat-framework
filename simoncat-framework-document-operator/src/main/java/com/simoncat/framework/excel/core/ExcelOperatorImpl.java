package com.simoncat.framework.excel.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.simoncat.framework.excel.api.ExcelOperator;
import com.simoncat.framework.excel.api.ExcelType;
import com.simoncat.framework.excel.api.Job;
import com.simoncat.framework.excel.api.Parameter;

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

		try (Workbook document = parameter.getType().equals(ExcelType.XLS) ? new HSSFWorkbook() : new XSSFWorkbook();
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
}

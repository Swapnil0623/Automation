package com.ExcelLibrary.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;

import com.intalk.baseclasses.BaseClass;

public class ExcelUtils extends BaseClass {
	public static int rowNum1 = 1;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public static int rowNum = 2;

	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	String getUrl = "";
	String expectedUrl;

	public ExcelUtils(String path) {
		this.path = path;
		File file = new File(path);
		if (!file.exists()) {
			throw new RuntimeException("Excel file does not exist at path: " + path);
		}
	}



	public static int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount1 = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount1;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException, InterruptedException {

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}
	public int getRowNum1() {
		return rowNum1++;
	}



	public static String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}

		workbook.close();
		fi.close();

		return data;
	}



	public static int getColumnCount(String sheetName, int rowNum) {
		sheet = workbook.getSheet(sheetName);
		if (sheet != null) {
			row = sheet.getRow(rowNum - 1); // Adjust for 0-based index
			if (row != null) {
				return row.getLastCellNum();
			} else {
				return 0;
			}
		}

		return 0; // Return 0 if sheet or row is not found
	}

	public static String setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);

		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		return data;

	}

	public void reloadWorkbook() throws IOException {
		if (fi != null) {

			workbook.close();

			FileInputStream inputStream = new FileInputStream(path);
			workbook = new XSSFWorkbook(inputStream);
		}
	}

	public int getRowNum() {
		return rowNum++;
	}
	
	

}

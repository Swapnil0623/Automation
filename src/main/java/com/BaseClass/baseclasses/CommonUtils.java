package com.intalk.baseclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import com.ExcelLibrary.data.ExcelUtils;
import com.relevantcodes.extentreports.LogStatus;

public class CommonUtils extends BaseClass {

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	public FileInputStream fi;
	public FileOutputStream fo;
	public CellStyle style;
	public static String path = BaseClass.path;
	public static String path1 = BaseClass.path1;
	public static String urldomain = BaseClass.urldomain;

	public CommonUtils(String excelpath, String sheetName) throws IOException {
		try {
			workbook = new XSSFWorkbook(excelpath);
			sheet = workbook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getExcelData(int rowIndex, int colIndex) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(path1);
		String value = WorkbookFactory.create(file).getSheet("AgentHome").getRow(rowIndex).getCell(colIndex)
				.getStringCellValue();
		return value;
	}

	public static String getURL_Domain(int rowIndex, int colIndex) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(urldomain);
		String value = WorkbookFactory.create(file).getSheet("URLDomain").getRow(rowIndex).getCell(colIndex)
				.getStringCellValue();
		return value;
	}

	public static String writeExcel(String sheetName, int rowNum, int cellNum, String inputString)
			throws EncryptedDocumentException, IOException {
		try {
			FileInputStream fis = new FileInputStream(path);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			if (row == null) {
				row = sh.createRow(rowNum);
			}
			Cell cell = row.createCell(cellNum);
			cell.setCellValue(inputString);
			FileOutputStream fos = new FileOutputStream(path);
			wb.write(fos);
			fos.close();
			wb.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputString;
	}

	public static void CallingData(String sheetName, int rowNum, int cellNum, String inputString)
			throws EncryptedDocumentException, IOException {
		try {
			FileInputStream fis = new FileInputStream(path1);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			if (row == null) {
				row = sh.createRow(rowNum);
			}
			Cell cell = row.createCell(cellNum);
			cell.setCellValue(inputString);
			FileOutputStream fos = new FileOutputStream(path1);
			wb.write(fos);
			fos.close();
			wb.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ButtonIsClickable(WebElement ele, String buttonname)
			throws EncryptedDocumentException, InterruptedException, IOException {
		boolean button = ele.isEnabled();

		if (button) {
			button = true;
			test.log(LogStatus.PASS, buttonname + "Button is clickable");
		} else {
			button = false;
			test.log(LogStatus.FAIL, buttonname + "Button is not working");
		}
		Thread.sleep(1000);
		Assert.assertTrue(button);
	}

	public static void dropdownIsSelectable(WebElement ele, String dropdownname)
			throws EncryptedDocumentException, InterruptedException, IOException {
		boolean dropdown = ele.isEnabled();
		if (dropdown) {
			dropdown = true;
			test.log(LogStatus.PASS, dropdownname + " is selectable");
		} else {
			dropdown = false;
			test.log(LogStatus.FAIL, dropdownname + " is not working");
		}
		Thread.sleep(1000);
		Assert.assertTrue(dropdown);
	}

	public static void writeExcel1(String sheet, int rowNum, int cellNum, String inputString)
			throws EncryptedDocumentException, IOException {
		String xlPath = BaseClass.path;
		String xlSheet = "CallList_Data";
		FileInputStream fis = new FileInputStream(xlPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(xlSheet);
		Row row = sh.getRow(rowNum);
		Cell cell = row.createCell(cellNum);
		cell.setCellValue(xlSheet);
		FileOutputStream fos = new FileOutputStream(xlPath);
		wb.write(fos);
		fos.close();

	}

	public static void DomMethod() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		By elementsToRemoveLocator = By.xpath("//div[@class='loader']");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementsToRemoveLocator));
		List<WebElement> elementsToRemove = driver.findElements(elementsToRemoveLocator);
		for (WebElement element : elementsToRemove) {
			((JavascriptExecutor) driver).executeScript("arguments[0].remove()", element);
		}
	}

}

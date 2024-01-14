package com.intalk.pom;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.opencsv.exceptions.CsvValidationException;
import com.relevantcodes.extentreports.LogStatus;

public class CDRReportPom extends BaseClass {
	Actions act;
	WebDriverWait wait;
	String sheet = "CDRFetchData";
	String pathforexcel = BaseClass.path;
	int rowNum = 3;
	static String[] csvData;
	static String[] cdrDataArray;
	int i;
	int j;

	public CDRReportPom(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[@class='report-icon']")
	public WebElement reportModuleLink;
	@FindBy(linkText = "//*[@id=\"mainController\"]/div[9]/div[1]/div/nav/div/div/div[1]/div/div[2]/ul/li[6]/ul/li[2]/p")
	public WebElement clickCallReport;
	@FindBy(xpath = "//a[normalize-space()='CDR Call Detailed Report']")
	public WebElement clickCDReport;

	@FindBy(xpath = "//input[@ng-model='cdrRequestForm.search.start_stamp']")
	public WebElement Click_churn_Create_dataTime;

	@FindBy(xpath = "(//div[@class='ranges']//ul)[1]")
	public WebElement churn_Create_dataTime_Dropdown;

	@FindBy(xpath = "//span[@class='checkmark checkboxcheck']")
	public WebElement Click_churn_Last_dataTime;

	@FindBy(xpath = "//input[@id='daterangepick']")
	public WebElement churn_Last_dataTime_Dropdown;

	@FindBy(xpath = "//select[@name='campaign']")
	public WebElement churn_selectCampaign;

	@FindBy(xpath = "(//input[@class='select2-search__field'])[1]")
	public WebElement churn_selectListName;
	@FindBy(xpath = "(//input[@class='select2-search__field'])[2]")
	public WebElement churn_selectdisposition;

	@FindBy(xpath = "//input[@name='total_attempts']")
	public WebElement churn_totalAttemps;

	@FindBy(xpath = "(//button[@type='submit'])[1]")
	public WebElement churn_filterClick;

	@FindBy(xpath = "(//input[@name='daterangepicker_start'])[1]")
	public WebElement CreatedDateSelectedstart;
	@FindBy(xpath = "(//input[@name='daterangepicker_end'])[1]")
	public WebElement CreatedDateSelectedto;

	@FindBy(xpath = "(//input[@name='daterangepicker_start'])[2]")
	public WebElement lastDateSelectedstart;
	@FindBy(xpath = "(//input[@name='daterangepicker_end'])[2]")
	public WebElement lastDateSelectedto;

	@FindBy(xpath = "(//button[@class='applyBtn btn btn-sm btn-primary'])[1]")
	public WebElement createapply;
	@FindBy(xpath = "(//button[@class='applyBtn btn btn-sm btn-primary'])[2]")
	public WebElement lastapply;
	@FindBy(xpath = "//span[text()='Call Status']")
	public WebElement callstatuss;

	@FindBy(xpath = "//div[@ng-model='cdrRequestForm.search.call_type']//input[@type='text']")
	public WebElement searchcallstatus;
	@FindBy(xpath = "(//span[@class='selectAllli'])[2]")
	public WebElement searchselectcalltype;
	@FindBy(xpath = "(//button[@type='submit'])[1]")
	public WebElement filterbutton;
	@FindBy(xpath = "//tr[@ng-repeat='item in cdrData'][1]//td")
	public WebElement CDRFirstRowData;

	@FindBy(xpath = "//span[text()='Select Agent']")
	public WebElement selectagent;
	@FindBy(xpath = "(//input[@type='text'])[14]")
	public WebElement searchselectagent;
	@FindBy(xpath = "//label[normalize-space()='st_agentAutomation']//span[@class='checkmark']")
	public WebElement clickagent;
	@FindBy(xpath = "//button[@ng-class=\"applyPermission('Export')\"]")
	public WebElement export;
	@FindBy(xpath = "//a[@ng-click=\"exportTodata('csv')\"]")
	public WebElement csvdownload;
	@FindBy(xpath = "//select[@ng-change='callreportLimit()']")
	public WebElement nextpageofCDR;

	public void ClickReportSubModule() throws InterruptedException {
		Thread.sleep(1000);
		act = new Actions(driver);
		driver.navigate().to("https://qacloud.intalk.io/cc/supervisor-home#!/cdr-call-detailed-report");

	}
	public void ClickCallReport() {
		clickCallReport.click();

	}

	public void ClickCDRReport() {
		clickCDReport.click();
	}

	public void Churn_Create_DatePicker(String createwisedata) throws InterruptedException {
		Click_churn_Create_dataTime.click();
		Thread.sleep(1000);
		if (createwisedata.equalsIgnoreCase("Today")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Today'])[1]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (createwisedata.equalsIgnoreCase("Yesterday")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Yesterday'])[1]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (createwisedata.equalsIgnoreCase("Last 7 Days")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Last 7 Days'])[1]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (createwisedata.equalsIgnoreCase("Last 30 Days")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Last 30 Days'])[1]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (createwisedata.equalsIgnoreCase("This Month")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='This Month'])[1]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (createwisedata.equalsIgnoreCase("Last Month")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Last Month'])[1]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (createwisedata.equalsIgnoreCase("Custom Range")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Custom Range'])[1]"));
			Thread.sleep(2000);
			lielements.click();
		}
	}

	public void Churn_Last_Datepicker(String lastwisedata) throws InterruptedException {
		Click_churn_Last_dataTime.click();
		WebElement datepicker = driver.findElement(By.xpath("(//input[@id='daterangepick'])[1]"));
		datepicker.click();
		Thread.sleep(1000);
		if (lastwisedata.equalsIgnoreCase("Today")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Today'])[2]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (lastwisedata.equalsIgnoreCase("Yesterday")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Yesterday'])[2]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (lastwisedata.equalsIgnoreCase("Last 7 Days")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Last 7 Days'])[2]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (lastwisedata.equalsIgnoreCase("Last 30 Days")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Last 30 Days'])[2]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (lastwisedata.equalsIgnoreCase("This Month")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='This Month'])[2]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (lastwisedata.equalsIgnoreCase("Last Month")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Last Month'])[2]"));
			Thread.sleep(2000);
			lielements.click();
		} else if (lastwisedata.equalsIgnoreCase("Custom Range")) {
			WebElement lielements = driver.findElement(By.xpath("(//li[text()='Custom Range'])[2]"));
			Thread.sleep(2000);
			lielements.click();
			Thread.sleep(1000);
		}
	}

	public void CreateDatePickerStart(String start1) {
		String str = start1;
		String[] res = str.split("[,]", 0);
		for (int i = 0; i < res.length; i++) {
			String myStr = res[i];
			int indexOfTwo = ArrayUtils.indexOf(res, myStr);
			if (i == 0) {
				System.out.println("hello");
				CreatedDateSelectedstart.click();
				CreatedDateSelectedstart.clear();
				CreatedDateSelectedstart.sendKeys(myStr);
			} else if (i == 1) {
				CreatedDateSelectedto.click();
				CreatedDateSelectedto.clear();
				CreatedDateSelectedto.sendKeys(myStr);
			}
		}
		createapply.click();
	}

	public void LastDatePickerStart(String start2) {
		System.out.println(start2);
		String str = start2;
		String[] res = str.split("[,]", 0);
		for (int i = 0; i < res.length; i++) {
			String myStr = res[i];
			int indexOfTwo = ArrayUtils.indexOf(res, myStr);
			if (i == 0) {
				System.out.println("hello");
				lastDateSelectedstart.click();
				lastDateSelectedstart.clear();
				lastDateSelectedstart.sendKeys(myStr);
			} else if (i == 1) {
				lastDateSelectedto.click();
				lastDateSelectedto.clear();
				lastDateSelectedto.sendKeys(myStr);
			}

		}
		lastapply.click();
	}

	public void CallStatuss(String callstatus) throws InterruptedException {
		callstatuss.click();
		String str = callstatus;
		String[] res = str.split("[,]", 0);
		for (String myStr : res) {
			Thread.sleep(1000);
			searchcallstatus.sendKeys(myStr);
			searchcallstatus.clear();

		}

	}

	public void SelectAgent(String agent) throws InterruptedException {
		selectagent.click();
		System.out.println(agent);
		searchselectagent.sendKeys(agent);
		searchselectagent.sendKeys(Keys.SPACE);
		
	}

	public void CDRFilter() throws EncryptedDocumentException, ParseException, IOException {
		filterbutton.click();

	}

	public void CDRData(int rowNum, int elementIndex) throws EncryptedDocumentException, IOException, ParseException {
		String data1;
		List<WebElement> data = driver
				.findElements(By.xpath("(//tr[@ng-repeat='item in cdrData'])[" + elementIndex + "]//td"));

		if (data.size() > 0) {
			String[] dataArray = data.get(0).getText().split("\n");
			for (int j = 0; j < dataArray.length; j++) {
				data1 = dataArray[j];
				String p1 = ExcelUtils.getCellData1("AgentHome", rowNum, 13);
				String[] parts = p1.split(" ");
				for (String part : parts) {
					String time = parts[1];
					String dat = parts[0];
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
					Date d2 = simpleDateFormat.parse(time);
					Date d1 = simpleDateFormat.parse(dataArray[1]);
					long timeDifferenceMillis = d1.getTime() - d2.getTime();
					long seconds = timeDifferenceMillis / 1000;
					long hours = seconds / 3600;
					long minutes = (seconds % 3600) / 60;
					seconds = seconds % 60;
					if (seconds == 1) {
						d2.setTime(d2.getTime() + 1000);
					} else if (seconds == 2) {
						d2.setTime(d2.getTime() + 2000);
					} else if (seconds == -1) {
						d2.setTime(d2.getTime() - 1000);
					} else if (seconds == -2) {
						d2.setTime(d2.getTime() - 2000);
					} else if (seconds == 0) {
						d2.setTime(d2.getTime());
						CommonUtils.CallingData("AgentHome", rowNum, 22, "PASS");
						test.log(LogStatus.PASS, "Data matching with CDR");
					} else {
						CommonUtils.CallingData("AgentHome", rowNum, 22, "FAIL");
						test.log(LogStatus.FAIL, "Data not matching with CDR");
					}

					String updatedTimeStr = simpleDateFormat.format(d2);
					System.out.println("Updated Time: " + updatedTimeStr);

					String timeDifferenceStr = String.format("%02d:%02d:%02d", hours, minutes, seconds);
					String data2 = parts[0] + " " + updatedTimeStr;
					System.out.println("Call Time Difference between Call Time: " + parts[0] + " " + updatedTimeStr);
					CommonUtils.CallingData("AgentHome", rowNum, 13, data2);
				}

			}
		} else

			System.out.println("The array is either null or empty.");

	}

	public void CDRData1(int rowNum, int elementIndex) throws EncryptedDocumentException, IOException {
		String data9 = null;
		String data10 = null;
		String data14 = null;
		String data15 = null;
		String data16 = null;
		String data18 = null;
		String data30 = null;

		try {
			List<WebElement> data = driver
					.findElements(By.xpath("(//tr[@ng-repeat='item in cdrData'])[" + elementIndex + "]//td"));

			if (!data.isEmpty()) {
				cdrDataArray = new String[data.size()];

				for (int j = 0; j < data.size(); j++) {
					WebElement element = data.get(j);
					String dataArray[] = element.getText().split("\n");
					if (dataArray.length > 0) {
						cdrDataArray[j] = dataArray[0];
						System.out.println(" CDR-Index " + j + ": " + cdrDataArray[j]);
						if (j == 14) {
							data14 = dataArray[0];
							System.out.println("Data at index 14: " + data14);
							int seconds = Integer.parseInt(data14);
							int hours = seconds / 3600;
							int minutes = (seconds % 3600) / 60;
							int remainingSeconds = seconds % 60;
							String updatedTimeStr = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);

							System.out.println("Updated Time: " + updatedTimeStr);
							System.out.println("Call Time Difference between Call Time: " + updatedTimeStr);
							CommonUtils.CallingData("AgentHome", rowNum, 14, updatedTimeStr);
						}
						if (j == 9) {
							data9 = dataArray[0];
							System.out.println("Data at index 10: " + data9);
							CommonUtils.CallingData("AgentHome", rowNum, 18, data9);
						}
						if (j == 10) {
							data10 = dataArray[0];
							System.out.println("Data at index 10: " + data10);
							CommonUtils.CallingData("AgentHome", rowNum, 17, data10);
						}
						if (j == 15) {
							data15 = dataArray[0];
							System.out.println("Data at index 15: " + data15);
							CommonUtils.CallingData("AgentHome", rowNum, 15, data15);
						}
						if (j == 16) {
							data16 = dataArray[0];
							System.out.println("Data at index 16: " + data16);
							CommonUtils.CallingData("AgentHome", rowNum, 19, data16);
						}
						if (j == 18) {
							data18 = dataArray[0];
							System.out.println("Data at index 18: " + data18);
							CommonUtils.CallingData("AgentHome", rowNum, 16, data18);
						}
						if (j == 30) {
							data30 = dataArray[0];
							System.out.println("Data at index 30: " + data30);
							CommonUtils.CallingData("AgentHome", rowNum, 20, data30);
						}
					}
				}
			} else {
				System.out.println("No CDR data found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void GetDataFromCDR(int rowNum, int elementIndex) {
		try {
			List<WebElement> data = driver
					.findElements(By.xpath("//tr[@ng-repeat='item in cdrData'][" + elementIndex + "]"));
			String[] cdrDataArray = new String[data.size()];
			if (!data.isEmpty()) {
				for (int j = 0; j < data.size(); j++) {
					WebElement element = data.get(j);
					String dataArray[] = element.getText().split("\n");
					if (dataArray.length > 0) {
						cdrDataArray[j] = dataArray[0];
						System.out.println("Index " + j + ": " + cdrDataArray[j]);
					}
				}
			} else {
				System.out.println("No CDR data found.");
				return;

			}
		} catch (Exception e) {
			System.out.println("");
		}

	}

	public void ExportCDRData() throws CsvValidationException, IOException, InterruptedException {
		export.click();
		csvdownload.click();
		Thread.sleep(1000);
	}

//	public static void ReadingCSVFile(String filename) throws CsvValidationException, IOException {
//		String directoryPath = "/home/swapnil/Downloads";
//		File directory = new File(directoryPath);
//		if (directory.isDirectory()) {
//			File mostRecentFile = null;
//			long mostRecentTime = Long.MIN_VALUE;
//			File[] files = directory.listFiles();
//			if (files != null) {
//				for (File file : files) {
//					if (file.isFile()) {
//						long lastModifiedTime = file.lastModified();
//
//						if (lastModifiedTime > mostRecentTime) {
//							mostRecentFile = file;
//							mostRecentTime = lastModifiedTime;
//						}
//					}
//				}
//				if (mostRecentFile != null) {
//					System.out.println("The most recently downloaded file is: " + mostRecentFile.getName());
//					try {
//						CSVReader reader = new CSVReader(new FileReader(mostRecentFile));
//						String[] nextLine;
//						int currentRow = 0;
//						while ((nextLine = reader.readNext()) != null) {
//							currentRow++;
//							if (currentRow < 8) {
//								continue;
//							}
//							boolean isEmptyLine = true;
//							for (String data : nextLine) {
//								if (data != null && !data.isEmpty()) {
//									isEmptyLine = false;
//									break;
//								}
//							}
//							if (isEmptyLine) {
//								continue;
//							}
//							if (nextLine.length >= 24) {
//								String[] csvData = new String[23];
//								for (int i = 0; i < 23; i++) {
//									csvData[i] = nextLine[i];
//									System.out.println("CSV Index " + i + "" + csvData[i]);
//									if(i==0) {
//										System.out.println(csvData[i] + "swapnil");
//									}
//
//								}
//							} else {
//								System.out.println("Row does not have enough columns.");
//							}
//						}
//						reader.close();
//					} catch (IOException | CsvValidationException e) {
//						e.printStackTrace();
//					}
//				} else {
//					System.out.println("No files found in the specified directory.");
//				}
//			} else {
//				System.out.println("The specified directory is empty.");
//			}
//		} else
//
//		{
//			System.out.println("The specified path is not a directory.");
//		}

//	}

//	public void campare(int rowNum, int elementIndex) {
//		String[] cdrDataArray = null;
//		String[] csvData = null;
//
//		try {
//			List<WebElement> data = driver
//					.findElements(By.xpath("//tr[@ng-repeat='item in cdrData'][" + elementIndex + "]"));
//			cdrDataArray = new String[data.size()];
//			if (!data.isEmpty()) {
//				for (int j = 0; j < data.size(); j++) {
//					WebElement element = data.get(j);
//					String dataArray[] = element.getText().split("\n");
//					if (dataArray.length > 0) {
//						cdrDataArray[j] = dataArray[0];
//						System.out.println("Index " + j + ": " + cdrDataArray[j]);
//					}
//				}
//			} else {
//				System.out.println("No CDR data found.");
//				return;
//
//			}
//		} catch (Exception e) {
//			System.out.println("");
//		}
//
//		String directoryPath = "/home/swapnil/Downloads";
//		File directory = new File(directoryPath);
//		if (directory.isDirectory()) {
//			File mostRecentFile = null;
//			long mostRecentTime = Long.MIN_VALUE;
//			File[] files = directory.listFiles();
//			if (files != null) {
//				for (File file : files) {
//					if (file.isFile()) {
//						long lastModifiedTime = file.lastModified();
//
//						if (lastModifiedTime > mostRecentTime) {
//							mostRecentFile = file;
//							mostRecentTime = lastModifiedTime;
//						}
//					}
//				}
//				if (mostRecentFile != null) {
//					System.out.println("The most recently downloaded file is: " + mostRecentFile.getName());
//					try {
//						CSVReader reader = new CSVReader(new FileReader(mostRecentFile));
//						String[] nextLine;
//						int currentRow = 0;
//						while ((nextLine = reader.readNext()) != null) {
//							currentRow++;
//							if (currentRow < 8) {
//								continue;
//							}
//							boolean isEmptyLine = true;
//							for (String data : nextLine) {
//								if (data != null && !data.isEmpty()) {
//									isEmptyLine = false;
//									break;
//								}
//							}
//							if (isEmptyLine) {
//								continue;
//							}
//							if (nextLine.length >= 24) {
//								csvData = new String[23];
//								for (int i = 0; i < 23; i++) {
//									csvData[i] = nextLine[i];
//									System.out.println("CSV Index " + i + "" + csvData[i]);
//
//								}
//							} else {
//								System.out.println("Row does not have enough columns.");
//							}
//						}
//						reader.close();
//					} catch (IOException | CsvValidationException e) {
//						e.printStackTrace();
//					}
//				} else {
//					System.out.println("No files found in the specified directory.");
//				}
//			} else {
//				System.out.println("The specified directory is empty.");
//			}
//		} else
//
//		{
//			System.out.println("The specified path is not a directory.");
//		}
//
//		if (cdrDataArray != null && csvData != null && cdrDataArray.length == csvData.length) {
//			for (int i = 0; i < cdrDataArray.length; i++) {
//				String cdrValue = cdrDataArray[i];
//				String csvValue = csvData[i];
//
//				if (cdrValue != null && csvValue != null && cdrValue.equals(csvValue)) {
//					System.out.println("Matching at Index " + i + ": " + cdrValue);
//				} else {
//					System.out.println("Not Matching at Index " + i + ": CDR=" + cdrValue + ", CSV=" + csvValue);
//				}
//			}
//		} else {
//			System.out.println("Arrays have different lengths or one or both arrays are null.");
//		}
//	}

	public void NextPageOfCdr() {
		nextpageofCDR.click();
		nextpageofCDR.sendKeys(Keys.DOWN);
		nextpageofCDR.sendKeys(Keys.DOWN);
		nextpageofCDR.sendKeys(Keys.DOWN);
		nextpageofCDR.sendKeys(Keys.DOWN);
		nextpageofCDR.sendKeys(Keys.ENTER);

	}
}
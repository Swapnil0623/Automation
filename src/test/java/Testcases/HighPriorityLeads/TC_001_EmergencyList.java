package emergencyList;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.intalk.pom.EmergencyListPom;
import com.intalk.screenshot.ScreenShotClass;
import com.relevantcodes.extentreports.LogStatus;

public class TC_001_EmergencyList extends BaseClass {
	public static EmergencyListPom emergency;
	private String sheetName = "EmergencyList";
	private ExcelUtils excel;
	private int rowNum = 2;
	String getUrl = "";
	String expectedUrl;

	@BeforeClass
	public void openBrowser() throws InterruptedException {
		emergency = new EmergencyListPom(driver);
	}

	@Test(priority = 8, dataProvider = "emergencylist")
	public void Emergency_Functionality(String operation, String filtername, String filternumber, String filtercampaign,
			String filterstatus, String name, String number, String status, String campaignName)
			throws InterruptedException, EncryptedDocumentException, IOException {
		Thread.sleep(1000);
		emergency.AppsClick();
		Thread.sleep(1000);
		emergency.ClickModuleEmercenyModule();
		Thread.sleep(1000);
		try {
			switch (operation.toLowerCase()) {
			case "add":
				handleAddOperation(operation, name, number, status, campaignName);
				emergency.Error_Display();
				break;

			case "edit":
				handleEditOperation(operation, filtername, filternumber, filtercampaign, filterstatus, name, number,
						status, campaignName);
				emergency.Error_Display();
				break;

			case "delete":
				handleDeleteOperation(operation, filtername, filternumber, filtercampaign, filterstatus);
				emergency.Error_Display();
				break;

			default:
				System.out.println("Invalid operation: " + operation);
				emergency.Error_Display();
				ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			}
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (operation.equalsIgnoreCase("Delete")) {
					CommonUtils.writeExcel(sheetName, rowNum, 13, "Deleted");
				} else {

					Thread.sleep(1000);
					if (emergency.save.isEnabled()) {
						emergency.SaveButton();
						CommonUtils.writeExcel(sheetName, rowNum, 13, "PASS");
						Assert.assertTrue(true);
					} else {
						CommonUtils.writeExcel(sheetName, rowNum, 13, "FAIL");
						Assert.assertTrue(false);
					}
				}
			} catch (AssertionError e) {
				ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
				e.printStackTrace();
				CommonUtils.writeExcel(sheetName, rowNum, 13, "FAIL");
				Assert.fail("Assertion failed: " + e.getMessage());

			} catch (Exception e) {
				ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
				e.printStackTrace();
				CommonUtils.writeExcel(sheetName, rowNum, 13, "FAIL");

			} finally {
				rowNum++;
			}
		}

	}

	// ===============================Add
	// Operations=======================================================

	private void handleAddOperation(String dropdow, String name, String number, String status, String campaignName)
			throws InterruptedException {
		Thread.sleep(1000);
		emergency.ClickAdd();
		Thread.sleep(500);
		emergency.EmergencyListName(name);
		emergency.EmergencyNumber(number);
		emergency.EmergencyStatus(status);
		try {
			emergency.DropdownCampaign(campaignName);
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();

		}
	}

	// ===============================Edit
	// Operations=======================================================

	private void handleEditOperation(String operation, String filtername, String filternumber, String filtercampaign,
			String filterstatus, String name, String number, String status, String campaignName)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(emergency.filter));
		Thread.sleep(1000);
		emergency.FilterClick();
		Thread.sleep(300);
		try {
			if (filternumber.equalsIgnoreCase("")) {
				emergency.filtername.clear();
			} else {
				Thread.sleep(1000);
				emergency.filtername.clear();
				emergency.filtername.sendKeys(filtername);
			}
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();

		}
		try {
			if (filternumber.equalsIgnoreCase("")) {
				emergency.filternumber.clear();
			} else {
				emergency.filternumber.clear();
				emergency.filternumber.sendKeys(filternumber);
			}
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();

		}

		try {
			if (filterstatus.equalsIgnoreCase("")) {
			} else {
				emergency.FilterEmergencyStatus(filterstatus);
			}
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();

		}

		try {
			if (filtercampaign.equalsIgnoreCase("")) {
			} else {

				emergency.filterCampaign.sendKeys(filtercampaign);
			}
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
		emergency.filterSubmit.click();
		Thread.sleep(1000);
		emergency.EditClick();

		try {
			if (name.equalsIgnoreCase("")) {
			} else {
				Thread.sleep(300);
				emergency.emergencylistname.clear();
				emergency.EmergencyListName(name);
				test.log(LogStatus.PASS, "Edited Emergency Name  =>  EmergencyList Edited Successfully.");

			}

			if (number.equalsIgnoreCase("")) {
			} else {
				emergency.emergencylistNumber.clear();
				emergency.EmergencyNumber(number);
				test.log(LogStatus.PASS, "Edited Emergency Number  =>  EmergencyNumber Edited Successfully.");
			}

			if (status.equalsIgnoreCase("")) {
			} else {
				emergency.EmergencyStatus(status);
				test.log(LogStatus.PASS, "Edited Emergency Status =>  Emergency Status Edited Successfully.");
			}

			if (campaignName.equalsIgnoreCase("")) {
			} else {
				
				emergency.DropdownCampaignForEdit(campaignName);
			}

		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}

	}

	// ===============================Delete
	// Operations=======================================================

	private void handleDeleteOperation(String operation, String filtername, String filternumber, String filtercampaign,
			String filterstatus) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement filterButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Filter Data']")));
		Thread.sleep(1000);
		emergency.FilterClick();
		Thread.sleep(300);
		emergency.filtername.clear();
		emergency.filtername.sendKeys(filtername);
		try {
			if (filternumber.equalsIgnoreCase("")) {
				emergency.filternumber.clear();
			} else {
				emergency.filternumber.clear();
				emergency.filternumber.sendKeys(filternumber);
			}
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (filterstatus.equalsIgnoreCase("")) {
			} else {
				emergency.FilterEmergencyStatus(filterstatus);
			}
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}

		try {
			if (filtercampaign.equalsIgnoreCase("")) {
			} else {
				emergency.filterCampaign.clear();
				emergency.filterCampaign.sendKeys(filtercampaign);
			}
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
		try {
			emergency.filterSubmit.click();
			Thread.sleep(500);
			emergency.clickfordelete.click();
			emergency.Delete();
		} catch (Exception e) {
			ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void logTestData(String... data) {
		System.out.print("Test Data: ");
		for (String value : data) {
			System.out.print(value + " ");
		}
		System.out.println();

	}

	@DataProvider(name = "emergencylist")
	public Object[][] getData() throws IOException, InterruptedException {
		String path = BaseClass.path;
		excel = new ExcelUtils(path);
		int startRow = 2;
		int startCol = 2;
		int totalRows = excel.getRowCount(sheetName);
		int totalCols = 9;

		List<Object[]> validData = new ArrayList<>();

		for (int i = startRow; i <= totalRows; i++) {
			boolean rowDataPresent = false;
			Object[] rowData = new Object[totalCols];

			for (int j = startCol; j < startCol + totalCols; j++) {
				rowData[j - startCol] = excel.getCellData(sheetName, i, j);
				if (rowData[j - startCol] != null && !rowData[j - startCol].toString().trim().isEmpty()) {
					rowDataPresent = true;
				}
			}

			if (rowDataPresent) {
				validData.add(rowData);
			}
		}

		return validData.toArray(new Object[0][]);
	}

	@AfterTest
	public void closeBrowser() {
		report.endTest(test);
		report.flush();
	}
}

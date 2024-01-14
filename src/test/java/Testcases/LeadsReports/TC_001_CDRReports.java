package cdrReports;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.intalk.pom.CDRReportPom;
import com.intalk.pom.UsersModelPom;
import com.relevantcodes.extentreports.LogStatus;

import agentLogin.TC_001_AgentLogin;
import login.TC_001_Login;

public class TC_001_CDRReports extends BaseClass {

	CDRReportPom cdrreport;
	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	String getUrl = "";
	String expectedUrl;
	int i;
	public static String sheetname = "CDRReport";
	UsersModelPom userspom;
	private Map<String, String> userProfiles;
	int time;
	int timeInSeoconds;
	WebElement element;
	boolean data;
	int rowNum = 0;
	int RowNum = 2;
	private int dataCounter = 0;
	public static Set<String> windowHandlescdr;
	int rownum = 2;
	int colnum = 3;
	int startingRow = 2;
	int row = 0;
	int cell = 1;

	@BeforeClass
	public void openBrowser() throws Exception {
		cdrreport = new CDRReportPom(driver);
	}

	@Test(priority = 17, dataProvider = "CDRReports", groups = "cdr")
	public void CDRReport_Functionality(String createwise, String lastwise, String createdStart, String LastStart,
			String agent, String exportpath)
			throws InterruptedException, EncryptedDocumentException, IOException, ParseException {

		Thread.sleep(1000);
		cdrreport.ClickReportSubModule();
		Thread.sleep(500);

		try {
			cdrreport.SelectAgent(agent);
			// test.log(LogStatus.PASS, "CDR Data Filter Select Agent");
		} catch (Exception e) {
			// test.log(LogStatus.FAIL, "CDR Data Filter not Selected Agent");
		}

//		try {
//			cdrreport.ExportCDRData();
//			cdrreport.ReadingCSVFile(exportpath);
//		} catch (Exception e) {
//
//		}

		try {
			cdrreport.CDRFilter();
		} catch (Exception e) {
		}

		try {
			cdrreport.NextPageOfCdr();
			Thread.sleep(1000);
		} catch (Exception e) {

		}
		cdrreport.ScrollDown();
		Thread.sleep(2000);
		try {

			
			int maxRow = ExcelUtils.getRowCount("AgentHome");
			int maxRows = maxRow - 1;
			System.out.println(maxRows + "MaxRowConsole");
			for (int rownum = startingRow; rownum <= maxRow; rownum++) {
				for (int elementIndex = maxRows; elementIndex >= maxRows; elementIndex--) {
					cdrreport.CDRData(rownum, elementIndex);
					cdrreport.CDRData1(rownum, elementIndex);
					cdrreport.GetDataFromCDR(elementIndex, rownum);
					Thread.sleep(1000);

				}
				maxRows--;
			}
			startingRow++;

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Call Status CDR Error");
		}

		finally {
			RowNum++;
			cell++;
		}
	}

	@DataProvider(name = "CDRReports")
	public Object[][] getData() throws IOException, InterruptedException {
		String sheetName = "CDRReport";
		String path = TC_001_Login.loginpath;
		excel = new ExcelUtils(path);
		int startRow = 1;
		int startCol = 2;
		int totalrows = excel.getRowCount(sheetName);
		System.out.println(totalrows);
		int totalcols = excel.getCellCount(sheetName, startRow);
		Object[][] userData = new Object[totalrows - startRow + 1][6];
		for (int i = startRow; i <= totalrows; i++) {
			for (int j = startCol; j <= startCol + 5; j++) {
				userData[i - startRow][j - startCol] = excel.getCellData(sheetName, i, j);
			}
		}
		return userData;

	}

//	@AfterTest()
//	public void closeBrowser() {
//		report.endTest(test);
//		report.flush();
//	}

}
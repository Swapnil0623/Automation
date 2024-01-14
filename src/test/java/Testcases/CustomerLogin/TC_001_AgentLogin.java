package agentLogin;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.intalk.pom.AgentLoginPom;
import com.intalk.pom.UsersModelPom;

public class TC_001_AgentLogin extends BaseClass {

	public static AgentLoginPom agent;
	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	String getUrl = "";
	String expectedUrl;
	int i;
	public static String sheetname = "AgentLogin";
	public static String agentloginpath = "TestData/Intalk.io_AgentData.xlsx";
	UsersModelPom userspom;
	private Map<String, String> userProfiles;

	@BeforeClass
	public void openBrowser() throws Exception {
		initilizeBrowserChrome();
		agent = new AgentLoginPom(driver);

	}

	@Test(priority = 1, dataProvider = "AgentLoginData")
	public void AgentLogin_Functionality(String username, String Password, String queue, String queueName,
			String endpoint, String mobile) throws EncryptedDocumentException, IOException, InterruptedException {

		try {
			agent.Username(username);
			agent.Password(Password);
			agent.Dropdowndomain();
			agent.DropdpwnLanguage();
			agent.LoginBtn();
		} catch (Exception e) {
			System.out.println("Data provide");
		}
		try {
			if (queue.equalsIgnoreCase("Yes") || queue.equalsIgnoreCase("")) {
				System.out.println(queue);
				agent.SubmitButton();
			} else {
				if (queue.equalsIgnoreCase("No"))
					agent.SelectQueue(queueName);
			}
//			if (endpoint.equalsIgnoreCase(endpoint)) {
//				agent.SelectEndpoint(endpoint);
//				if (endpoint.equalsIgnoreCase("Mobile")) {
//					agent.MobileNumberExtention(mobile);
//				} else if (endpoint.equalsIgnoreCase("SoftPhone/IP-Phone")) {
//					agent.EndpointExtention(mobile);
//				}
//			}
		} catch (Exception e) {
			System.out.println("select queue");
		}

		finally {

			agent.SubmitButton();
			getUrl = driver.getCurrentUrl();
			System.out.println(getUrl + " agent Url");
			expectedUrl = "https://qacloud.intalk.io/cc/";
			Thread.sleep(1000);
			int rowNum = excel.getRowNum();
			if (expectedUrl.equalsIgnoreCase(getUrl)) {
				CommonUtils.writeExcel(sheetname, rowNum, 6, "PASS");
				Thread.sleep(1000);
				Assert.assertTrue(true);
			} else {
				CommonUtils.writeExcel(sheetname, rowNum, 6, "FAIL");
				Assert.assertTrue(false);

			}
		}

	}

	@DataProvider(name = "AgentLoginData")
	public Object[][] getData() throws IOException, InterruptedException {
		String path = agentloginpath;
		excel = new ExcelUtils(path);
		int startRow = 1;
		int startCol = 2;
		int totalrows = excel.getRowCount(sheetname);
		System.out.println(totalrows);
		int totalcols = excel.getCellCount(sheetname, startRow);
		System.out.println(totalcols);
		Object[][] loginData = new Object[totalrows - startRow + 1][6];
		for (int i = startRow; i <= totalrows; i++) {
			for (int j = startCol; j <= startCol + 5; j++) {
				loginData[i - startRow][j - startCol] = excel.getCellData(sheetname, i, j);
			}
		}
		return loginData;
	}

//
	@AfterTest(alwaysRun = true)
	public void closeBrowser() {

		report.endTest(test);
		report.flush();
	}
}

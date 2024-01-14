package login;

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
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.intalk.pom.IndexPagePom;
import com.intalk.pom.UsersModelPom;

@Listeners(com.intalk.listeners.ListenerTest.class)

public class TC_001_Login extends BaseClass {
	public static IndexPagePom login;
	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	String getUrl = "";
	String expectedUrl;
	int i;
	int rowNum = 2;
	public static String sheetname = "Login";
	public static String loginpath = BaseClass.path;
	UsersModelPom userspom;
	private Map<String, String> userProfiles;

	@BeforeClass
	public void openBrowser() throws Exception {
		secondinitilizeBrowser();
		login = new IndexPagePom(driver);
	}

	@Test(priority = 3, dataProvider = "LoginData")
	public void Login_Functionality(String UserName, String Password, String endpoint, String mobile)
			throws InterruptedException, EncryptedDocumentException, IOException {

		try {
			Thread.sleep(1000);
			login.username.clear();
			login.Username(UserName);
			login.password.clear();
			login.Password(Password);
			login.Dropdowndomain();
			login.DropdpwnLanguage();
			// login.LoginBtn();
			Thread.sleep(500);
			// login.SelectEndpoint(endpoint);
		} catch (Exception e) {
			
		
	}finally {
		login.LoginBtn();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait2.until(ExpectedConditions.elementToBeClickable(login.homepage));
		getUrl = driver.getCurrentUrl();
		System.out.println(getUrl + "  current");
		expectedUrl = "https://" + CommonUtils.getURL_Domain(1, 1) + "/cc/supervisor-home#!/live-data";
		System.out.println(expectedUrl);
		rowNum = excel.getRowNum();
		if (expectedUrl.equalsIgnoreCase(getUrl)) {
			Thread.sleep(1000);
			CommonUtils.writeExcel(sheetname, rowNum, 6, "PASS");
			Assert.assertTrue(true);
		} else {
			CommonUtils.writeExcel(sheetname, rowNum, 6, "FAIL");
			Assert.assertTrue(false);
		}
		rowNum++;
	}

	}

	@DataProvider(name = "LoginData")
	public Object[][] getData() throws IOException, InterruptedException {
		String path = loginpath;
		excel = new ExcelUtils(path);
		int startRow = 2;
		int startCol = 2;
		int totalrows = excel.getRowCount(sheetname);
		System.out.println(totalrows);
		int totalcols = excel.getCellCount(sheetname, startRow);
		System.out.println(totalcols);
		Object[][] loginData = new Object[totalrows - startRow + 1][4];
		for (int i = startRow; i <= totalrows; i++) {
			for (int j = startCol; j <= startCol + 3; j++) {
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

package emailConfiguration;

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

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.intalk.pom.EmailConfigurationPom;

public class TC_001_EmailConfiguration extends BaseClass {

	public static EmailConfigurationPom emailconfig;
	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	int rownum = 2;
	String getUrl = "";
	String expectedUrl;
	String sheetName = "EmailConfiguration";

	@BeforeClass
	public void opernBrowers() {
		emailconfig = new EmailConfigurationPom(driver);
	}

	@Test(priority = 9, dataProvider = "emailconfig")
	public void EmailConfiguration_functionality(String host, String port, String emailfromname, String email,
			String username, String password, String encry, String emailreply, String replyemailId)
			throws InterruptedException, EncryptedDocumentException, IOException {
		Thread.sleep(500);
		emailconfig.AppsClick();
		emailconfig.EmailConfigClick();
		Thread.sleep(1000);
		emailconfig.Addemailconfig();
		emailconfig.EmailHost(host);
		emailconfig.EmailPort(port);
		emailconfig.EmailFromName(emailfromname);
		emailconfig.EmailId(email);
		emailconfig.EmailUsername(username);
		emailconfig.EmailPassword(password);
		emailconfig.EmailEncrypted(encry);
		try {
		if (emailreply.equals("Agent Specific Email-Id")) {
			emailconfig.EmailReplyToAgentSpecificEmailId();
		} else {
			if (emailreply.equals("Following Email-Id")) {
				emailconfig.EmailReplyToFollowingSpecificEmailId();
				emailconfig.ReplyAgentMailId(replyemailId);
			} else {
				if (emailreply.equals("None")) {
					emailconfig.None();
				}
			}
		}
		}
		catch(Exception e) {
			System.out.println("");
		}
		finally {
			emailconfig.SaveButton();
			Thread.sleep(2000);
			getUrl = driver.getCurrentUrl();
			System.out.println(getUrl);
			expectedUrl = "https://qacloud.intalk.io/cc/supervisor-home#!/mail_configuration";
			int rowNum = excel.getRowNum();
			if (expectedUrl.equals(getUrl)) {
				AssertJUnit.assertTrue(true);
				CommonUtils.writeExcel(sheetName, rowNum, 14, "PASS");
			} else {
				CommonUtils.writeExcel(sheetName, rowNum, 14, "FAIL");
				assert (false);
			}

			rownum++;
		
		}
	}

	@DataProvider(name = "emailconfig")
	public Object[][] getData() throws IOException, InterruptedException {
		String sheetName = "EmailConfiguration";
		String path = BaseClass.path;
		excel = new ExcelUtils(path);
		int startRow = 2;
		int startCol = 2;
		int totalrows = excel.getRowCount(sheetName);
		System.out.println(totalrows);
		int totalcols = excel.getCellCount(sheetName, startRow);
		System.out.println(totalcols);
		Object[][] userData = new Object[totalrows - startRow + 1][9];
		for (int i = startRow; i <= totalrows; i++) {
			for (int j = startCol; j <= startCol + 8; j++) {
				userData[i - startRow][j - startCol] = excel.getCellData(sheetName, i, j);
			}
		}
		return userData;
	}

	@AfterTest()
	public void closeBrowser() {
		report.endTest(test);
		report.flush();
	}

}

 package agentQueueMultiSelection;

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
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.intalk.pom.AgentQueueMultiSelectionPom;

public class TC_001_AgentQueueMultiSelection extends BaseClass{
	public static AgentQueueMultiSelectionPom multiselection;
	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	int rownum = 2;
	String getUrl = "";
	String expectedUrl;
	String sheetName = "AgentQueueMultiSelection";

	@BeforeClass
	public void opernBrowers() {
		multiselection = new AgentQueueMultiSelectionPom(driver);
	}
	
	@Test(priority = 13,dataProvider = "multiSelection")
	public void AgentQueueMUltiSelection(String agent,String level,String position) throws InterruptedException, EncryptedDocumentException, IOException {
		multiselection.AppsClick();
		multiselection.ClickModuleCallBlock();
		Thread.sleep(1000);
		try {
		multiselection.AgentSelection(agent);
		}catch(Exception e) {
			System.out.println("Please Select Agent");
		}
		try {
		multiselection.ManageTierlevel(level);
		}catch(Exception e) {
			System.out.println("Please Select level");
		}
		try {
			multiselection.ManageTierPosistion(position);
			}catch(Exception e) {
				System.out.println("Please Select position");
			}
		
		finally {
			multiselection.SaveButton();
			Thread.sleep(2000);
			getUrl = driver.getCurrentUrl();
			System.out.println(getUrl);
			expectedUrl = "https://qacloud.intalk.io/cc/home#!/users";
			int rowNum = excel.getRowNum();
			if (expectedUrl.equals(getUrl)) {
				AssertJUnit.assertTrue(true);
				CommonUtils.writeExcel(sheetName,rowNum, 36, "PASS");
			} else {
				CommonUtils.writeExcel(sheetName,rowNum, 36, "FAIL");
				assert (false);
			}

			rownum++;
		}
		
		}
		

	@DataProvider(name = "multiSelection")
	public Object[][] getData() throws IOException, InterruptedException {
		String sheetName = "AgentQueueMultiSelection";
		String path = BaseClass.path;
		excel = new ExcelUtils(path);
		int startRow = 2;
		int startCol = 2;
		int totalrows = excel.getRowCount(sheetName);
		System.out.println(totalrows);
		int totalcols = excel.getCellCount(sheetName, startRow);
		System.out.println(totalcols);
		Object[][] userData = new Object[totalrows - startRow + 1][3];
		for (int i = startRow; i <= totalrows; i++) {
			for (int j = startCol; j <= startCol + 2; j++) {
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

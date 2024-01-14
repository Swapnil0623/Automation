package advanceCallManagement;

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

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.pom.AdvanceCallManagmentPom;

public class TC_001_AdvanceCallManagement extends BaseClass {

	public static AdvanceCallManagmentPom advancePom;
	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	int rownum = 2;
	String getUrl = "";
	String expectedUrl;
	String sheetName = "AdvanceCallManagement";

	@BeforeClass
	public void opernBrowers() {
		advancePom = new AdvanceCallManagmentPom(driver);
	}

	@Test(priority = 3, dataProvider = "advancecall")
	public void AdvanceCallManagement(String selecttype, String queueName, String campaignname, String listname,
			String disposition, String mapqueuename, String mapcampaignname, String status, String duplicatecheck,
			String timeduration, String avoidcheckclick, String considerClick, String migrateselectcamapign,
			String migrateselectqueue, String listnamee) throws InterruptedException {
		System.out.println(considerClick);
		advancePom.AppsClick();
		advancePom.AdvanceCallModule();
		Thread.sleep(1000);
		advancePom.AddadvanceCall();
		advancePom.DropdownCallQueueMapping(selecttype);

		if (selecttype.equals("All Abandoned Calls")) {
			try {
				advancePom.DropdownQueueName(queueName);
				advancePom.DropdownMappingQueueName(mapqueuename);
				advancePom.DropdownStatus(status);
				advancePom.DropdownDuplicateCheck(duplicatecheck);
				advancePom.TimeDuration(timeduration);
				System.out.println(avoidcheckclick);
				if (avoidcheckclick.equals("Yes")) {
					advancePom.ConnectBeforeAtempt();
				} else {
					System.out.println("");
				}
			} catch (Exception e) {
				System.out.println("");
			} finally {
				advancePom.SaveButton();
			}
		} else {
			if (selecttype.equals("Call Back Calls")) {
				try {
					advancePom.DropdownQueueName(queueName);
					advancePom.DropdownMappingQueueName(mapqueuename);
					advancePom.DropdownStatus(status);
				} catch (Exception e) {
					System.out.println("");
				} finally {
					advancePom.SaveButton();
				}
			} else {
				if (selecttype.equals("Feedback Calls")) {
					try {
						advancePom.DropdownQueueName(queueName);
						advancePom.DropdownMappingCampaignName(mapcampaignname);
						advancePom.DropdownStatus(status);
					} catch (Exception e) {
						System.out.println("");
					} finally {
						advancePom.SaveButton();
					}
				} else {
					if (selecttype.equals("Failed Survey Reattempt")) {
						try {
							advancePom.DropdownCampaignName(campaignname);
							advancePom.DropdownStatusForFailedFeedback(status);
							if (considerClick.equals("Yes")) {
								advancePom.ConsiderFailedFeedback();
							} else {
								System.out.println("not click consider failed feedback");
							}
						} catch (Exception e) {
							System.out.println("");
						} finally {
							advancePom.SaveButton();
						}
					} else {
						if (selecttype.equals("Disposition wise Lead Migration")) {
							advancePom.DropdownCampaignName(campaignname);
							Thread.sleep(1000);
							advancePom.DropdownQueueNameForFailedFeedback(queueName);
							try {
							advancePom.DropdownListname(listname);
							}catch(Exception e) {
								System.out.println("List Name Error");
							}
							advancePom.DropdownDisposition(disposition);
							advancePom.MigrateCampaignName(migrateselectcamapign);
							advancePom.MigrateQueueName(migrateselectqueue);
							advancePom.MigrateListName(listnamee);
							advancePom.SaveButton();
							
						}
					}
				}
			}
		}
	}

	@DataProvider(name = "advancecall")
	public Object[][] getData() throws IOException, InterruptedException {
		String sheetName = "AdvanceCallManagement";
		String path = BaseClass.path;
		excel = new ExcelUtils(path);
		int startRow = 2;
		int startCol = 2;
		int totalrows = excel.getRowCount(sheetName);
		System.out.println(totalrows);
		int totalcols = excel.getCellCount(sheetName, startRow);
		System.out.println(totalcols);
		Object[][] userData = new Object[totalrows - startRow + 1][15];
		for (int i = startRow; i <= totalrows; i++) {
			for (int j = startCol; j <= startCol + 14; j++) {
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

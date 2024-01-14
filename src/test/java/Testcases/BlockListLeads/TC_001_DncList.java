package dncList;

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
import com.intalk.pom.DNCListPom;
import com.relevantcodes.extentreports.LogStatus;

public class TC_001_DncList extends BaseClass {
	public static DNCListPom dnclistPom;
	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	int rownum = 2;
	String getUrl = "";
	String expectedUrl;
	String sheetName = "DNCList";

	@BeforeClass
	public void opernBrowers() {
		dnclistPom = new DNCListPom(driver);
	}

	@Test(priority = 6, dataProvider = "dnclist")
	public void DncList_Functionality(String dropdown, String filterstatus, String systemdnclist, String dnc,
			String status, String campaign, String importdnc, String uploaddata, String choosefile, String description)
			throws InterruptedException, EncryptedDocumentException, IOException {
		dnclistPom.AppsClick();
		dnclistPom.ClickModuleDNC();

		if (dropdown.equalsIgnoreCase("Add")) {
			Thread.sleep(500);
			dnclistPom.ClickAdd();
			System.out.println(dnc);
			dnclistPom.DNCName(dnc);
			dnclistPom.DropdownStatus(status);
			dnclistPom.CampaignNameDropdown(campaign);
			try {
				if (importdnc.equals("Yes")) {
					dnclistPom.ImportDncList(choosefile);
				} else {
					dnclistPom.SampleDownload();
					Thread.sleep(1000);
					dnclistPom.DownloadCSVFile();
				}
			} catch (Exception e) {
				System.out.println("");
			}
			try {
				dnclistPom.Description(description);
			} catch (Exception e) {
				System.out.println("Description optional");
			} finally {
				dnclistPom.SaveButton();
				Thread.sleep(2000);
				getUrl = driver.getCurrentUrl();
				System.out.println(getUrl);
				expectedUrl = "https://" + CommonUtils.getURL_Domain(1, 1) + "/cc/supervisor-home#!/dnc";
				int rowNum = excel.getRowNum();
				if (expectedUrl.equals(getUrl)) {
					AssertJUnit.assertTrue(true);
					CommonUtils.writeExcel(sheetName, rowNum, 13, "PASS");
				} else {
					CommonUtils.writeExcel(sheetName, rowNum, 13, "FAIL");
					assert (false);
				}

				rownum++;
			}

		} else if (dropdown.equalsIgnoreCase("Edit")) {
			Thread.sleep(500);
			dnclistPom.FilterClick();
			dnclistPom.FilterdncName();
			try {
				dnclistPom.FilterdncCampaignName(campaign);
			} catch (Exception e) {
				System.out.println("");
			}
			dnclistPom.FilterdncStatus(filterstatus);
			dnclistPom.FilterSubmit();
			Thread.sleep(500);
			try {
				if (!dnc.equalsIgnoreCase("NA") && !dnc.equalsIgnoreCase("")) {
					Thread.sleep(500);
					dnclistPom.dncname.clear();
					dnclistPom.DNCName(dnc);
					test.log(LogStatus.PASS, "Edit Dnc List");
				} else {
					if (dnc.equalsIgnoreCase("")) {
						System.out.println("Not changed");
					} else {
						if (dnc.equalsIgnoreCase("NA")) {
							dnclistPom.dncname.clear();
							test.log(LogStatus.FAIL, "Edit Dnc List Error");

						}
					}
				}

			} catch (Exception e) {
				System.out.println("Call List Name Error");
			}

			try {
				if (!status.equalsIgnoreCase("NA") && !status.equalsIgnoreCase("")) {
					Thread.sleep(500);
					dnclistPom.DropdownStatus(status);
					test.log(LogStatus.PASS, "Status Dnc List");
				} else {
					if (status.equalsIgnoreCase("")) {
						System.out.println("Not changed");
					} else {
						if (status.equalsIgnoreCase("NA")) {
							test.log(LogStatus.FAIL, "Status Dnc List Error");

						}
					}
				}

			} catch (Exception e) {
				System.out.println("Status Dnc List Error");
			}

			try {
				if (!campaign.equalsIgnoreCase("NA") && !campaign.equalsIgnoreCase("")) {
					Thread.sleep(500);
					dnclistPom.CampaignNameDropdown(campaign);
					test.log(LogStatus.PASS, "Campaign Name In Dnc List");
				} else {
					if (campaign.equalsIgnoreCase("")) {
						System.out.println("Not changed");
					} else {
						if (campaign.equalsIgnoreCase("NA")) {
							test.log(LogStatus.FAIL, "Campaign Name In Dnc List Error");

						}
					}
				}

			} catch (Exception e) {
				System.out.println("Campaign Name In Dnc List Error");
			}

			try {
				if (!importdnc.equalsIgnoreCase("NA") && !importdnc.equalsIgnoreCase("")) {
					Thread.sleep(500);
					dnclistPom.ImportDncList(importdnc);
					test.log(LogStatus.PASS, "Import CSVFile In Dnc List");
				} else {
					if (importdnc.equalsIgnoreCase("")) {
						System.out.println("Not changed");
					} else {
						if (importdnc.equalsIgnoreCase("NA")) {
							test.log(LogStatus.FAIL, "Import CSVFile In Dnc Error");

						}
					}
				}

			} catch (Exception e) {
				System.out.println("Import CSVFile In Dnc Error");
			}

			try {
				if (!uploaddata.equalsIgnoreCase("NA") && !uploaddata.equalsIgnoreCase("")) {
					Thread.sleep(500);
					if (uploaddata.equalsIgnoreCase("Yes")) {
						dnclistPom.UploadData();
					} else if (uploaddata.equalsIgnoreCase("No")) {
						dnclistPom.UploadDataAppent();
					}
				} else {
					if (uploaddata.equalsIgnoreCase("")) {
						System.out.println("Not changed");
					} else {
						if (uploaddata.equalsIgnoreCase("NA")) {
							test.log(LogStatus.FAIL, "Upload Data Not Clickable Error");

						}
					}
				}

			} catch (Exception e) {
				System.out.println(" Error");
			}

			try {
				if (!description.equalsIgnoreCase("NA") && !description.equalsIgnoreCase("")) {
					Thread.sleep(500);
					dnclistPom.description.clear();
					dnclistPom.Description(description);
				} else {
					if (description.equalsIgnoreCase("")) {
						System.out.println("Not changed");
					}
				}
			} catch (Exception e) {
				System.out.println(" Error");
			}

			finally {
				dnclistPom.SaveButton();
				Thread.sleep(2000);
				getUrl = driver.getCurrentUrl();
				System.out.println(getUrl);
				expectedUrl = "https://" + CommonUtils.getURL_Domain(1, 1) + "/cc/supervisor-home#!/dnc";
				int rowNum = excel.getRowNum();
				if (expectedUrl.equals(getUrl)) {
					AssertJUnit.assertTrue(true);
					CommonUtils.writeExcel(sheetName, rowNum, 13, "PASS");
				} else {
					CommonUtils.writeExcel(sheetName, rowNum, 13, "FAIL");
					assert (false);
				}
				rowNum++;
			}

		} else if (systemdnclist.equalsIgnoreCase("Delete")) {
			Thread.sleep(500);
			dnclistPom.FilterClick();
			dnclistPom.FilterdncName();
			try {
				dnclistPom.FilterdncCampaignName(campaign);
			} catch (Exception e) {
				System.out.println("");
			}
			dnclistPom.FilterdncStatus(filterstatus);
			dnclistPom.FilterSubmit();
			Thread.sleep(500);
			dnclistPom.Delete();

		} else if (systemdnclist.equalsIgnoreCase("SystemDnc")) {
			Thread.sleep(500);
			if (systemdnclist.equalsIgnoreCase("Number") || systemdnclist.equalsIgnoreCase("Edit")) {
				Thread.sleep(500);
				dnclistPom.FilterClick();
				dnclistPom.FilterdncName();
				try {
					dnclistPom.FilterdncCampaignName(campaign);
				} catch (Exception e) {
					System.out.println("");
				}
				dnclistPom.FilterdncStatus(filterstatus);
				dnclistPom.FilterSubmit();
				Thread.sleep(500);
				if (systemdnclist.equalsIgnoreCase("Edit")) {
					dnclistPom.EditDnc();
					Thread.sleep(1000);
					System.out.println(importdnc + "-----------------------");
					Thread.sleep(1000);
					try {
						if (importdnc.equals("Yes")) {
							Thread.sleep(1000);
							System.out.println(choosefile);
							dnclistPom.SystemImport(choosefile);
						} else {
							dnclistPom.SampleDownload();
							Thread.sleep(1000);
							dnclistPom.DownloadCSVFile();
						}
					} catch (Exception e) {
						System.out.println("");
					}
					System.out.println(uploaddata);
					if (uploaddata.equalsIgnoreCase("Replace")) {
						dnclistPom.UploadData();
					}

					try {
						dnclistPom.SystemDecription1(description);
					} catch (Exception e) {
						System.out.println("");
					} finally {
						dnclistPom.SaveButton1();
						Thread.sleep(2000);
						getUrl = driver.getCurrentUrl();
						System.out.println(getUrl);
						expectedUrl = "https://qacloud.intalk.io/cc/supervisor-home#!/dnc";
						int rowNum = excel.getRowNum();
						if (expectedUrl.equals(getUrl)) {
							AssertJUnit.assertTrue(true);
							CommonUtils.writeExcel(sheetName, rowNum, 13, "PASS");
						} else {
							CommonUtils.writeExcel(sheetName, rowNum, 13, "FAIL");
							assert (false);
						}

						rownum++;

					}
				} else if (systemdnclist.equalsIgnoreCase("Number")) {
					Thread.sleep(500);
					dnclistPom.Eye_ManuallyAddNumber();

				}
			}
		}
	}

	@DataProvider(name = "dnclist")
	public Object[][] getData() throws IOException, InterruptedException {
		String sheetName = "DNCList";
		String path = BaseClass.path;
		excel = new ExcelUtils(path);
		int startRow = 2;
		int startCol = 2;
		int totalrows = excel.getRowCount(sheetName);
		System.out.println(totalrows);
		int totalcols = excel.getCellCount(sheetName, startRow);
		System.out.println(totalcols);
		Object[][] userData = new Object[totalrows - startRow + 1][10];
		for (int i = startRow; i <= totalrows; i++) {
			for (int j = startCol; j <= startCol + 9; j++) {
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

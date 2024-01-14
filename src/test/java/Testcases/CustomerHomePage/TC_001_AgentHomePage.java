package agentHomePage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.pom.AgentHomePageClassPom;
import com.intalk.pom.UsersModelPom;

import agentLogin.TC_001_AgentLogin;

public class TC_001_AgentHomePage extends BaseClass {
	public static AgentHomePageClassPom agenthome;
	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	String getUrl = "";
	String expectedUrl;
	int i;
	public static String sheetname = "AgentHome";
	UsersModelPom userspom;
	private Map<String, String> userProfiles;
	int time;
	int timeInSeoconds;
	WebElement element;
	boolean data;
	int rowNum = 2;

	WebDriverWait wait;

	@BeforeClass
	public void openBrowser() throws Exception {
		agenthome = new AgentHomePageClassPom(driver);

	}

	@Test(priority = 2, dataProvider = "AgentHome", groups = "agentHome")
	public void AgentHomePage_Functionality(String queue, String phone_number, String time1, String disposition,
			String remark, String mood, String AutoWrapTime, String wrapuptime, String clearAllCallback, String hangup)
			throws EncryptedDocumentException, IOException, InterruptedException {
		// ==========================================================================
		agenthome.Agent_HomePage_Refresh();
		try {
			agenthome.PhoneNumber(phone_number);
			agenthome.CallButton(rowNum);
			agenthome.SelectCustomerDetails(rowNum);

			if (agenthome.selectCustomerDetailsClick.isDisplayed()) {
				agenthome.SelectCustomerDetails(rowNum);
			}
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
				wait.until(ExpectedConditions.elementToBeClickable(agenthome.AfterReceiveCallDisplay));
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data = agenthome.AfterReceiveCallDisplay.isDisplayed();
			} catch (Exception e) {
				WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(120));
				wait2.until(ExpectedConditions.elementToBeClickable(agenthome.mooddisplay));

				if (!disposition.isEmpty()) {
					agenthome.DialpadDisposition(disposition);
				} else {
					System.out.println("Disposition blank");
				}

				if (!remark.isEmpty()) {
					agenthome.DialpadRemark(remark);
				} else {
					System.out.println("Remark blank");
				}

				Thread.sleep(500);
				try {
					if (mood.equalsIgnoreCase("Very Bad")) {
						agenthome.Verybad();
					} else if (mood.equalsIgnoreCase("Very Good")) {
						agenthome.VeryGood();
					} else if (mood.equalsIgnoreCase("Good")) {
						agenthome.Good();
					} else if (mood.equalsIgnoreCase("Average")) {
						agenthome.Average();
					} else if (mood.equalsIgnoreCase("Bad")) {
						agenthome.Bad();
					}
				} catch (Exception e1) {
					System.out.println("Not Selected");
				}

				try {
					if (!clearAllCallback.equalsIgnoreCase("Yes") && clearAllCallback.equalsIgnoreCase("")) {
						agenthome.clearallcallback.click();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					if (AutoWrapTime.equalsIgnoreCase("No")) {
						agenthome.submitAfterCall.click();
					} else if (AutoWrapTime.equalsIgnoreCase("Yes") || AutoWrapTime.equalsIgnoreCase("Redial")) {
						WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(70));
						wait3.until(ExpectedConditions.elementToBeClickable(agenthome.mooddisplay));
						time = Integer.parseInt(wrapuptime);
						timeInSeoconds = time * 1000;
						System.out.println(timeInSeoconds);
						Thread.sleep(timeInSeoconds);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			// ==========================================

//===========================================Running==true============================================
			if (data) {
				WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(70));
				wait4.until(ExpectedConditions.elementToBeClickable(agenthome.AfterReceiveCallDisplay));
				if (hangup.equalsIgnoreCase("Agent")) {
					if (!agenthome.AfterReceiveCallDisplay.isDisplayed()) {
						agenthome.ClickDilapadButton();
					}
					int time = Integer.parseInt(time1);
					int timeInSeoconds = time * 1000;
					System.out.println(timeInSeoconds);
					Thread.sleep(timeInSeoconds);
					try {
						agenthome.CutCallButton();
					} catch (Exception e) {
						System.out.println("");
					}
					WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(50));
					wait5.until(ExpectedConditions.elementToBeClickable(agenthome.mooddisplay));

					if (!disposition.isEmpty()) {
						agenthome.DialpadDisposition(disposition);
					} else {
						System.out.println("Disposition blank");
					}

					if (!remark.isEmpty()) {
						agenthome.DialpadRemark(remark);
					} else {
						System.out.println("Remark blank");
					}
					Thread.sleep(500);
					try {
						if (mood.equalsIgnoreCase("Very Bad")) {
							agenthome.Verybad();
						} else if (mood.equalsIgnoreCase("Very Good")) {
							agenthome.VeryGood();
						} else if (mood.equalsIgnoreCase("Good")) {
							agenthome.Good();
						} else if (mood.equalsIgnoreCase("Average")) {
							agenthome.Average();
						} else if (mood.equalsIgnoreCase("Bad")) {
							agenthome.Bad();
						}
					} catch (Exception e1) {
						System.out.println("Not Selected");
					}

					try {
						if (!clearAllCallback.equalsIgnoreCase("Yes") && clearAllCallback.equalsIgnoreCase("")) {
							agenthome.clearallcallback.click();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					try {
						if (AutoWrapTime.equalsIgnoreCase("No")) {
							agenthome.submitAfterCall.click();
						} else if (AutoWrapTime.equalsIgnoreCase("Redial")) {
							agenthome.submitAfterCall.click();
						} else if (AutoWrapTime.equalsIgnoreCase("Yes")) {
							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
							wait.until(ExpectedConditions.elementToBeClickable(agenthome.mooddisplay));
							time = Integer.parseInt(wrapuptime);
							timeInSeoconds = time * 1000;
							System.out.println(timeInSeoconds);
							Thread.sleep(timeInSeoconds);

						}
					} catch (Exception e1) {
						System.out.println("");
					}

				} else if (hangup.equalsIgnoreCase("Customer") || hangup.equalsIgnoreCase("")) {
					WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(120));
					wait7.until(ExpectedConditions.elementToBeClickable(agenthome.mooddisplay));
					if (!disposition.isEmpty()) {
						agenthome.DialpadDisposition(disposition);
					} else {
						System.out.println("Disposition blank");
					}

					if (!remark.isEmpty()) {
						agenthome.DialpadRemark(remark);
					} else {
						System.out.println("Remark blank");
					}
					Thread.sleep(500);
					try {
						if (mood.equalsIgnoreCase("Very Bad")) {
							agenthome.Verybad();
						} else if (mood.equalsIgnoreCase("Very Good")) {
							agenthome.VeryGood();
						} else if (mood.equalsIgnoreCase("Good")) {
							agenthome.Good();
						} else if (mood.equalsIgnoreCase("Average")) {
							agenthome.Average();
						} else if (mood.equalsIgnoreCase("Bad")) {
							agenthome.Bad();
						}
					} catch (Exception e1) {
						System.out.println("Not Selected");
					}

					try {
						if (!clearAllCallback.equalsIgnoreCase("Yes") && clearAllCallback.equalsIgnoreCase("")) {
							agenthome.clearallcallback.click();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					try {
						if (AutoWrapTime.equalsIgnoreCase("No")) {
							agenthome.submitAfterCall.click();
						} else if (AutoWrapTime.equalsIgnoreCase("Redial")) {
							agenthome.submitAfterCall.click();
						} else if (AutoWrapTime.equalsIgnoreCase("Yes")) {
							WebDriverWait wait8 = new WebDriverWait(driver, Duration.ofSeconds(70));
							wait8.until(ExpectedConditions.elementToBeClickable(agenthome.mooddisplay));
							time = Integer.parseInt(wrapuptime);
							timeInSeoconds = time * 1000;
							System.out.println(timeInSeoconds);
							Thread.sleep(timeInSeoconds);
						}
					} catch (Exception e) {
						System.out.println("");
					}

				}
//=========================Running==false================================================
			} else if (!data) {
				WebDriverWait wait9 = new WebDriverWait(driver, Duration.ofSeconds(70));
				wait9.until(ExpectedConditions.elementToBeClickable(agenthome.mooddisplay));
				if (!disposition.isEmpty()) {
					agenthome.DialpadDisposition(disposition);
				} else {
					System.out.println("Disposition blank");
				}

				if (!remark.isEmpty()) {
					agenthome.DialpadRemark(remark);
				} else {
					System.out.println("Remark blank");
				}
				Thread.sleep(500);
				try {
					if (mood.equalsIgnoreCase("Very Bad")) {
						agenthome.Verybad();
					} else if (mood.equalsIgnoreCase("Very Good")) {
						agenthome.VeryGood();
					} else if (mood.equalsIgnoreCase("Good")) {
						agenthome.Good();
					} else if (mood.equalsIgnoreCase("Average")) {
						agenthome.Average();
					} else if (mood.equalsIgnoreCase("Bad")) {
						agenthome.Bad();
					}
				} catch (Exception e1) {
					System.out.println("Not Selected");
				}

				try {
					if (!clearAllCallback.equalsIgnoreCase("Yes") && clearAllCallback.equalsIgnoreCase("")) {
						agenthome.clearallcallback.click();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					if (AutoWrapTime.equalsIgnoreCase("No")) {
						agenthome.submitAfterCall.click();
					} else if (AutoWrapTime.equalsIgnoreCase("Redial")) {
						agenthome.submitAfterCall.click();
					} else if (AutoWrapTime.equalsIgnoreCase("Yes")) {
						WebDriverWait wait10 = new WebDriverWait(driver, Duration.ofSeconds(120));
						wait10.until(ExpectedConditions.elementToBeClickable(agenthome.mooddisplay));
						time = Integer.parseInt(wrapuptime);
						timeInSeoconds = time * 1000;
						System.out.println(timeInSeoconds);
						Thread.sleep(timeInSeoconds);
					}
				} catch (Exception e) {
					System.out.println("");
				}
			}

//			} catch (Exception e) {
//				System.out.println("");
//			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}

		finally {
			rowNum++;

		}

	}

	@DataProvider(name = "AgentHome")
	public Object[][] getData() throws IOException, InterruptedException {
		String path = TC_001_AgentLogin.agentloginpath;
		excel = new ExcelUtils(path);
		int startRow = 2;
		int startCol = 2;
		int totalrows = excel.getRowCount(sheetname);
		System.out.println(totalrows);
		int totalcols = excel.getCellCount(sheetname, startRow);
		System.out.println(totalcols);
		Object[][] loginData = new Object[totalrows - startRow + 1][10];
		for (int i = startRow; i <= totalrows; i++) {
			for (int j = startCol; j <= startCol + 9; j++) {
				loginData[i - startRow][j - startCol] = excel.getCellData(sheetname, i, j);

			}
		}
		return loginData;
	}

	@AfterClass
	public void Close() throws IOException, InterruptedException {

		report.endTest(test);
		report.flush();
		Thread.sleep(30000);
		//driver.quit();

	}

}

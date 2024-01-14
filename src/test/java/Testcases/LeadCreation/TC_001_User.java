package User;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.temp.AesZipFileZipEntrySource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ExcelLibrary.data.ExcelUtils;
import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.intalk.pom.UsersModelPom;
import com.intalk.screenshot.ScreenShotClass;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(com.intalk.listeners.ListenerTest.class)
public class TC_001_User extends BaseClass {

	public static UsersModelPom userspom;
	private String sTestCaseName;
	private int iTestCaseRow;
	ExcelUtils excel;
	int rowNum = 2;
	String getUrl = "";
	String expectedUrl;
	String sheetName = "UserModuleData";
	WebDriverWait wait;
	private List<Object[]> testData;

	@BeforeClass
	public void opernBrowers() throws InvalidFormatException, IOException {
		userspom = new UsersModelPom(driver);

	}

	@Test(priority = 1, dataProvider = "UserData")
	public void User_Functionality(String dropdown, String userfilter, String enablefilter, String groupfilter,
			String user, String pswrd, String confirmpswrd, String usage, String endpoint, String mobilenumber,
			String drpExtention, String ex_number, String ex_pswrd, String excon_pswrd, String enabledrecording,
			String enabledvoicemail, String autoAnswer, String displayevalution, String Gateway,
			String specificcalledid, String calledid, String email, String firstname, String lastname, String grouptype,
			String group, String enablecalling, String supervisorTL, String api, String desktop, String managename,
			String country, String languge, String greeting, String enableDrp, String campaign, String delete)
			throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.elementToBeClickable(userspom.homepage));
		userspom.AppsClick();
		Thread.sleep(1000);
		driver.navigate().refresh();
		CommonUtils.DomMethod();

		try {
			switch (dropdown.toLowerCase()) {
			case "add":
				handleAddOperation(dropdown, user, pswrd, confirmpswrd, usage, endpoint, mobilenumber, drpExtention,
						ex_number, ex_pswrd, excon_pswrd, enabledrecording, enabledvoicemail, autoAnswer,
						displayevalution, Gateway, specificcalledid, calledid, email, firstname, lastname, grouptype,
						group, enablecalling, supervisorTL, api, desktop, managename, country, languge, greeting,
						enableDrp, campaign);
				userspom.ErrorMessege();
				break;

			case "edit":
				handleEditOperation(dropdown, userfilter, enablefilter, groupfilter, user, pswrd, confirmpswrd, usage,
						endpoint, mobilenumber, drpExtention, ex_number, ex_pswrd, excon_pswrd, enabledrecording,
						enabledvoicemail, autoAnswer, displayevalution, Gateway, specificcalledid, calledid, email,
						firstname, lastname, grouptype, group, enablecalling, supervisorTL, api, desktop, managename,
						country, languge, greeting, enableDrp, campaign);
				userspom.ErrorMessege();
				break;

			case "delete":
				handleDeleteOperation(dropdown, userfilter, enablefilter, groupfilter);
				userspom.ErrorMessege();
				break;

			default:
				System.out.println("Invalid operation: " + dropdown);
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (dropdown.equalsIgnoreCase("Delete")) {
					if (userspom.successfulmessege.isDisplayed()) {
						CommonUtils.writeExcel(sheetName, rowNum, 40, "Deleted");
					} else {
						CommonUtils.writeExcel(sheetName, rowNum, 40, "Not Deleted");
					}

				} else if (dropdown.equalsIgnoreCase("Edit")) {
					userspom.savebuttonclickble();
					WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
					wait.until(ExpectedConditions.elementToBeClickable(userspom.userTextOnpage));
					getUrl = driver.getCurrentUrl();
					System.out.println(getUrl);
					expectedUrl = "https://" + CommonUtils.getURL_Domain(1, 1) + "/cc/supervisor-home#!/users";
					Thread.sleep(1000);
					if (expectedUrl.equalsIgnoreCase(getUrl)) {
						CommonUtils.writeExcel(sheetName, rowNum, 40, "PASS");
						Assert.assertTrue(true);
					} else {
						CommonUtils.writeExcel(sheetName, rowNum, 40, "FAIL");
						Assert.assertTrue(false);
					}

				}else if(dropdown.equalsIgnoreCase("Add")) {
					userspom.savebuttonclickble();
					WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
					wait.until(ExpectedConditions.elementToBeClickable(userspom.userTextOnpage));
					getUrl = driver.getCurrentUrl();
					System.out.println(getUrl);
					expectedUrl = "https://" + CommonUtils.getURL_Domain(1, 1) + "/cc/supervisor-home#!/users";
					Thread.sleep(1000);
					if (expectedUrl.equalsIgnoreCase(getUrl)) {
						CommonUtils.writeExcel(sheetName, rowNum, 40, "PASS");
						Assert.assertTrue(true);
					} else {
						CommonUtils.writeExcel(sheetName, rowNum, 40, "FAIL");
						Assert.assertTrue(false);
					}
				}
			} catch (AssertionError e) {
				System.out.println("Exception occurred: " + e.getMessage());
				ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
				e.printStackTrace();
				CommonUtils.writeExcel(sheetName, rowNum, 40, "FAIL");
				Assert.fail("Assertion failed: " + e.getMessage());

			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
				ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
				e.printStackTrace();
				CommonUtils.writeExcel(sheetName, rowNum, 40, "FAIL");

			} finally {
				rowNum++;
			}
		}

	}

	private void handleDeleteOperation(String dropdown, String userfilter, String enablefilter, String groupfilter)
			throws InterruptedException {
		if (dropdown.equalsIgnoreCase("Delete"))

		{

			userspom.Filter();
			userspom.FilterUser(userfilter);
			try {
				userspom.FilterStatus(enablefilter);
				userspom.FilterGroup(groupfilter);
				userspom.FilterSumbit();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
				wait.until(ExpectedConditions.elementToBeClickable(userspom.afterUserfilterShowData));
				Thread.sleep(5000);
				String fetchUserFilterData = userspom.afterUserfilterShowData.getText();
				String getData = userfilter;
				if (fetchUserFilterData.equalsIgnoreCase(getData)) {
					Thread.sleep(500);
					userspom.Delete();
				}
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
				ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
				System.out.println("Exception occurred: " + e.getMessage());
				e.printStackTrace();
			}

		}

	}

	private void handleEditOperation(String dropdown, String userfilter, String enablefilter, String groupfilter,
			String user, String pswrd, String confirmpswrd, String usage, String endpoint, String mobilenumber,
			String drpExtention, String ex_number, String ex_pswrd, String excon_pswrd, String enabledrecording,
			String enabledvoicemail, String autoAnswer, String displayevalution, String Gateway,
			String specificcalledid, String calledid, String email, String firstname, String lastname, String grouptype,
			String group, String enablecalling, String supervisorTL, String api, String desktop, String managename,
			String country, String languge, String greeting, String enableDrp, String campaign)
			throws InterruptedException, EncryptedDocumentException, IOException {
		if (dropdown.equalsIgnoreCase("Edit")) {
			Thread.sleep(1000);
			userspom.Filter();
			userspom.FilterUser(userfilter);
			try {
				userspom.FilterStatus(enablefilter);
				try {
					userspom.FilterGroup(groupfilter);
				} catch (Exception e) {
					System.out.println("optional");
				}
				userspom.FilterSumbit();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
				wait.until(ExpectedConditions.elementToBeClickable(userspom.afterUserfilterShowData));
				Thread.sleep(5000);
				String fetchUserFilterData = userspom.afterUserfilterShowData.getText();
				String getData = userfilter;
				if (fetchUserFilterData.equalsIgnoreCase(getData)) {
					Thread.sleep(500);
					userspom.EditClickbutton();
				}

			} catch (Exception e) {
				ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "FilterError.png");
				System.out.println("Exception occurred: " + e.getMessage());
				e.printStackTrace();
				System.out.println("Exception occurred: " + e.getMessage());
			}
			try {
				if (usage.equals(usage)) {
					userspom.EditUsageType(usage);
				} else {
					if (usage.equals("")) {
						System.out.println("");
					}
				}
			} catch (Exception e) {
				ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "" + user + "UsagType.png");
				System.out.println("Exception occurred: " + e.getMessage());
				e.printStackTrace();
				System.out.println("Exception occurred: " + e.getMessage());
			}
			// ========================================================Softphone/ip==================================================

			if (endpoint.equalsIgnoreCase(endpoint)) {
				try {
					userspom.EditEndPoint(endpoint);
				} catch (Exception e) {
					ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "" + user + "Endpoint.png");
					System.out.println("Exception occurred: " + e.getMessage());
					e.printStackTrace();
					System.out.println("Exception occurred: " + e.getMessage());
				}

				if (endpoint.equalsIgnoreCase("SoftPhone/IP-Phone")) {
					if (drpExtention != "") { // drpExtention=use
						try {
							userspom.clickUseExistingExtention();
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
									"" + user + "Existing Extention.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
							System.out.println("Exception occurred: " + e.getMessage());
						}
						try {
							if (!drpExtention.equals("NA") && !drpExtention.equals("")) {
								userspom.extention_dropdown(drpExtention);
								test.log(LogStatus.PASS, "Edit Use Extention Number ");
							} else if (drpExtention.equalsIgnoreCase("")) {
								System.out.println("not changes");
							} else {
								if (drpExtention.equalsIgnoreCase("NA")) {
									test.log(LogStatus.FAIL, "Use Extention Number Error");
								}
							}
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
									"" + user + "ExtentionDropdown.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
							System.out.println("Exception occurred: " + e.getMessage());
						}

					} else {
						if (ex_number != "") { // new Extention
							try {
								userspom.clickCreateNewExtention();
							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
										"" + user + "Create New Extention Error.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (!ex_number.equals("NA") && !ex_number.equals("")) {
								userspom.extentionNumber(ex_number);

							} else {
								if (ex_number.equals("")) {
									System.out.println("Not Changed");
								} else {
									if (ex_number.equals("NA")) {
										test.log(LogStatus.FAIL, "Create New Extention Error");
									}
								}
							}
							if (!ex_pswrd.equals("NA") && !ex_pswrd.equals("")) {
								userspom.extentionPassword(ex_pswrd);
							} else {
								if (ex_pswrd.equals("")) {
									System.out.println("Not Changed");
								} else {
									if (excon_pswrd.equals("NA")) {
										test.log(LogStatus.FAIL, "Password Extention Error");
									}
								}
							}
							if (!excon_pswrd.equals("NA") && !excon_pswrd.equals("")) {
								userspom.extentionConfirmPassword(excon_pswrd);
							} else {
								if (excon_pswrd.equals("")) {
									System.out.println("Not Changed");
								} else {
									if (excon_pswrd.equals("NA")) {
										test.log(LogStatus.FAIL, "Confirm Extention Error");
									}
								}
							}

						} else {
							System.out.println("Extention Error");
						}
					}

					try {
						if (!enabledrecording.equals("NA") && !enabledrecording.equals("")
								&& enabledrecording.equals("Yes")) {
							userspom.clickEnabled_recording();
						} else if (enabledrecording.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (enabledrecording.equals("NA")) {
								test.log(LogStatus.FAIL, "Enable Recording Error");
							}
						}

					} catch (Exception e) {
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
								"" + user + "Enabled Recording Error.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}

					try {
						if (!enabledvoicemail.equalsIgnoreCase("NA") && !enabledvoicemail.equalsIgnoreCase("")
								&& enabledvoicemail.equals("Yes")) {
							userspom.clickEnabled_voicemail();
						} else if (enabledvoicemail.equalsIgnoreCase("")) {
							System.out.println("Not Changed");
						} else {
							if (enabledvoicemail.equalsIgnoreCase("NA")) {
								test.log(LogStatus.FAIL, "Enable Recording Error");
							}
						}

					} catch (Exception e) {
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}

					if (!mobilenumber.equals("NA") && !mobilenumber.equals("")) {
						userspom.phone_number.clear();
						userspom.mobileNumber(mobilenumber);
						test.log(LogStatus.PASS, "Edit Mobile Number ");
					} else {
						if (mobilenumber.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (mobilenumber.equals("NA")) {
								userspom.phone_number.clear();
								test.log(LogStatus.FAIL, "Mobile Number Error");
							}
						}
					}

					try {
						if (!specificcalledid.equals("NA") && !specificcalledid.equals("")) {
							userspom.specifyCallerId.clear();
							userspom.enterSpecifyCallerIdNumber(specificcalledid);
							test.log(LogStatus.FAIL, "Edit specific Caller Id Number");
						} else {
							if (specificcalledid.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (specificcalledid.equals("NA")) {
									test.log(LogStatus.FAIL, "specific Caller Id Number Error");
								}
							}
						}
					} catch (Exception e) {
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}

					if (displayevalution.equals(displayevalution)) {
						userspom.ClickDisplayEvalution();
					} else {
						if (displayevalution.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (displayevalution.equals("NA")) {
								test.log(LogStatus.FAIL, "DisplayEvalution Error");
							}
						}
					}
					try {
						if (!calledid.equals("NA") && !calledid.equals("")) {
							userspom.CLID_Number.clear();
							userspom.clidNumber(calledid);
							test.log(LogStatus.FAIL, "EDIT CLID/DID Number");
						} else {
							if (calledid.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (calledid.equals("NA")) {
									userspom.CLID_Number.clear();
									test.log(LogStatus.FAIL, "CLID/DID Number Error");
								}
							}
						}
					} catch (Exception e) {
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}

					if (!email.equals("NA") && !email.equals("")) {
						userspom.EmailId_users.clear();
						userspom.enterEmail(email);
						test.log(LogStatus.FAIL, "Edit EmailId ");
					} else {
						if (email.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (email.equals("NA")) {
								userspom.EmailId_users.clear();
								test.log(LogStatus.FAIL, "EmailId Error");
							}
						}
					}
					if (!firstname.equals("NA") && !firstname.equals("")) {
						userspom.FirstName.clear();
						userspom.setFirstName(firstname);
					} else {
						if (firstname.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (firstname.equals("NA")) {
								userspom.FirstName.clear();
							}
						}
					}
					if (!lastname.equals("NA") && !lastname.equals("")) {
						userspom.LastName.clear();
						userspom.setLastName(lastname);
					} else {
						if (lastname.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (lastname.equals("NA")) {
								userspom.LastName.clear();
							}
						}
					}

					// =======================================Agent=============================================================================
					String data = userspom.Editgrouptype.getAttribute("value");
					if (data.equalsIgnoreCase("Agent")) {

						if (group != "") {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.FAIL, "Group Droupdown");
							} catch (Exception e) {
								System.out.println("");
								test.log(LogStatus.FAIL, "Group Droupdown Error");
							}
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (!supervisorTL.equals("NA") && !supervisorTL.equals("")) {
							userspom.SupervisorTL(supervisorTL);
							test.log(LogStatus.PASS, "SupervisorTL Droupdown ");
						} else {
							if (supervisorTL.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (supervisorTL.equals("NA")) {
									test.log(LogStatus.FAIL, "SupervisorTL Droupdown Error");
								}
							}
						}
						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						
						try {
							if(country.equalsIgnoreCase("")) {
								test.log(LogStatus.PASS, "User Country Error");
							}else {
								userspom.Country(country);
								test.log(LogStatus.PASS, "User Country");
							}
						}catch(Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						
					
					try {
						if(languge.equalsIgnoreCase("")) {
							test.log(LogStatus.PASS, "User Language Error");
						}else {
							userspom.Lauguge(languge);
							test.log(LogStatus.PASS, "User language");
						}
					}catch(Exception e) {
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}
					
						
						if (!greeting.equals("NA") && !greeting.equals("")) {
							userspom.GreetingFileDropdown(greeting);
							test.log(LogStatus.PASS, "Greeting Droupdown ");
						} else {
							if (greeting.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (greeting.equals("NA")) {
									test.log(LogStatus.FAIL, "Greeting Droupdown Error");
								}
							}
						}
						try {
							if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
								userspom.EnabledDropdown(enableDrp);
								test.log(LogStatus.PASS, "Enabled Droupdown");
							} else {
								if (enableDrp.equals("")) {
									System.out.println("Not Changed");
								} else {
									if (enableDrp.equals("NA")) {
										test.log(LogStatus.FAIL, "Enabled Droupdown Error");
									}
								}

							}
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}

					} else if (data.equalsIgnoreCase("Supervisor")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
							test.log(LogStatus.FAIL, "Group Droupdown ");
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							} else if (group.equals("NA")) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");

							}
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
							test.log(LogStatus.PASS, "Enabled Droupdown ");
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("campaign_supervisor")) {
						try {
							if (group != "") {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown ");
							} else {
								if (group.equals("")) {
									System.out.println("Not Changed");
								} else if (group.equals("NA")) {
									test.log(LogStatus.FAIL, "Group Droupdown Error");
								}
							}
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (campaign.equals(campaign)) {
							userspom.campaignName.clear();
							userspom.ManageCampaignName(campaign);
							test.log(LogStatus.PASS, "Campaign Droupdown ");
						} else {
							if (campaign.equals("")) {
								System.out.println("Not Changed");
							} else if (campaign.equals("NA")) {
								test.log(LogStatus.FAIL, "Campaign Droupdown Error");
							}
						}
						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("supervisor_TL")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
							test.log(LogStatus.PASS, "Group Droupdown ");
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							} else if (group == "NA") {
								test.log(LogStatus.FAIL, "Group Droupdown Error");

							}
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("wallboard")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}
					} else if (data.equalsIgnoreCase("operator")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (campaign.equals(campaign)) {
							userspom.ManageCampaignName(campaign);
							test.log(LogStatus.PASS, "Campaign Droupdown ");
						} else {
							if (campaign.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
							test.log(LogStatus.PASS, "Enabled Droupdown ");
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					}

				} else if (endpoint.equalsIgnoreCase("WebRtc")) {
					if (mobilenumber != ("")) {
						userspom.phone_number.clear();
						userspom.mobileNumber(mobilenumber);
					} else {
						if (mobilenumber.equals("")) {
							System.out.println("Not Changed");
						}
					}
					if (autoAnswer.equals(autoAnswer)) {
						userspom.ClickAutoAnswer();
					} else {
						if (autoAnswer.equals("")) {
							System.out.println("Not Changed");
						}
					}
					if (displayevalution.equals(displayevalution)) {
						userspom.ClickDisplayEvalution();
					} else {
						if (displayevalution.equals("")) {
							System.out.println("Not Changed");
						}
					}
					try {
						if (!calledid.equals("NA") && !calledid.equals("")) {
							userspom.CLID_Number.clear();
							userspom.clidNumber(calledid);
						} else {
							if (calledid.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (calledid.equals("NA")) {
									userspom.CLID_Number.clear();
									test.log(LogStatus.FAIL, "CLID/DID Number Error");
								}
							}
						}
					} catch (Exception e) {
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}

					try {
						if (!calledid.equals("NA") && !calledid.equals("")) {
							userspom.CLID_Number.clear();
							userspom.clidNumber(calledid);
						} else {
							if (calledid.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (calledid.equals("NA")) {
									userspom.CLID_Number.clear();
									test.log(LogStatus.FAIL, "CLID/DID Number Error");
								}
							}
						}
					} catch (Exception e) {
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}
					if (!email.equals("NA") && !email.equals("")) {
						userspom.EmailId_users.clear();
						userspom.enterEmail(email);
					} else {
						if (email.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (email.equals("NA")) {
								userspom.EmailId_users.clear();
								test.log(LogStatus.FAIL, "EmailID Error");
							}
						}
					}
					if (!firstname.equals("NA") && !firstname.equals("")) {
						userspom.FirstName.clear();
						userspom.setFirstName(firstname);
					} else {
						if (firstname.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (firstname.equals("NA")) {
								userspom.FirstName.clear();
							}
						}
					}
					if (!lastname.equals("NA") && !lastname.equals("")) {
						userspom.LastName.clear();
						userspom.setLastName(lastname);
					} else {
						if (lastname.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (lastname.equals("NA")) {
								userspom.LastName.clear();
							}
						}
					}
					if (group != "") {
						userspom.selectGroupdropdown(group);
					} else {
						if (group.equals("")) {
							System.out.println("Not Changed");
						}
					}
					String data = userspom.Editgrouptype.getAttribute("value");
					System.out.println(data + "[[[[[[[[[[[[[[[[[[");
					if (data.equalsIgnoreCase("Agent")) {

						if (!supervisorTL.equals("NA") && !supervisorTL.equals("")) {
							userspom.SupervisorTL(supervisorTL);
						} else {
							if (supervisorTL.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (supervisorTL.equals("NA")) {
									test.log(LogStatus.FAIL, "SupervisorTL Droupdown Error");
								}
							}
						}
						if (desktop.equals("Yes")) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (api.equals("Yes")) {
							userspom.UserTypeAPIClickable();
						} else {
							if (api.equals("")) {
								System.out.println("Not Changed");
							}
						}
						
						try {
							if(country.equalsIgnoreCase("")) {
								test.log(LogStatus.PASS, "User Country Error");
							}else {
								userspom.Country(country);
								test.log(LogStatus.PASS, "User Country");
							}
						}catch(Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						
					
					try {
						if(languge.equalsIgnoreCase("")) {
							test.log(LogStatus.PASS, "User Language Error");
						}else {
							userspom.Lauguge(languge);
							test.log(LogStatus.PASS, "User language");
						}
					}catch(Exception e) {
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}
						
						
						if (!greeting.equals("NA") && !greeting.equals("")) {
							userspom.GreetingFileDropdown(greeting);
						} else {
							if (greeting.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (greeting.equals("NA")) {
									test.log(LogStatus.FAIL, "Greeting Droupdown Error");
								}
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("Supervisor")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("campaign_supervisor")) {
						try {
							if (group != "") {
								userspom.EnabledDropdown(enableDrp);
							} else {
								if (group.equals("")) {
									System.out.println("Not Changed");
								}
							}
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (campaign.equals(campaign)) {
							userspom.campaignName.clear();
							userspom.ManageCampaignName(campaign);
						} else {
							if (campaign.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("supervisor_TL")) {
						if (group != "") {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("wallboard")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("operator")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (campaign.equals(campaign)) {
							userspom.ManageCampaignName(campaign);
							test.log(LogStatus.PASS, "Campaign Droupdown ");
						} else {
							if (campaign.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					}

				} else if (endpoint.equalsIgnoreCase("Mobile")) {
					if (!mobilenumber.equals("NA") && !mobilenumber.equals("")) {
						userspom.phone_number.clear();
						userspom.mobileNumber(mobilenumber);
					} else {
						if (mobilenumber.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (mobilenumber.equals("NA")) {
								userspom.phone_number.clear();
								test.log(LogStatus.FAIL, "Mobile Number Error");
							}
						}
					}
					if (!Gateway.equals("NA") && !Gateway.equals("")) {
						userspom.specifyGateway(Gateway);
					} else {
						if (Gateway.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (Gateway.equals("NA")) {
								test.log(LogStatus.FAIL, "Gateway Error");
							}
						}
					}
					if (!specificcalledid.equals("NA") && !specificcalledid.equals("")) {
						userspom.SpecificCallerIdNumber(specificcalledid);
						test.log(LogStatus.FAIL, "User Specific Caller Id Number ");
					} else {
						if (specificcalledid.equals("")) {
							System.out.println("Not Changed");

						} else {
							if (specificcalledid.equals("NA")) {
								System.out.println("Optional");
								test.log(LogStatus.FAIL, "User Specific Caller Id Number Error");
							}
						}
					}
					if (displayevalution.equals(displayevalution)) {
						userspom.ClickDisplayEvalution();
					} else {
						if (displayevalution.equals("")) {
							System.out.println("Not Changed");
						}
					}
					try {
						if (!calledid.equals("NA") && !calledid.equals("")) {
							userspom.CLID_Number.clear();
							userspom.clidNumber(calledid);
						} else {
							if (calledid.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (calledid.equals("NA")) {
									userspom.CLID_Number.clear();
									test.log(LogStatus.FAIL, "CLID/DID Number Error");
								}
							}
						}
					} catch (Exception e) {
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}
					if (!email.equals("NA") && !email.equals("")) {
						userspom.EmailId_users.clear();
						userspom.enterEmail(email);
					} else {
						if (email.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (email.equals("NA")) {
								userspom.EmailId_users.clear();
								test.log(LogStatus.FAIL, "EmailId Error");
							}
						}
					}
					if (!firstname.equals("NA") && !firstname.equals("")) {
						userspom.FirstName.clear();
						userspom.setFirstName(firstname);
					} else {
						if (firstname.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (firstname.equals("NA")) {
								userspom.FirstName.clear();
								test.log(LogStatus.FAIL, "FirstName Error => Optional");
							}
						}
					}
					if (!lastname.equals("NA") && !lastname.equals("")) {
						userspom.LastName.clear();
						userspom.setLastName(lastname);
					} else {
						if (lastname.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (lastname.equals("NA")) {
								userspom.LastName.clear();
								test.log(LogStatus.FAIL, "LastName Error => Optional");
							}
						}
					}
					if (!group.equals("NA") && !group.equals("")) {
						userspom.selectGroupdropdown(group);
					} else {
						if (group.equals("")) {
							System.out.println("Not Changed");
						} else {
							if (group.equals("NA")) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
							}
						}
					}
					String data = userspom.Editgrouptype.getAttribute("value");
					if (data.equalsIgnoreCase("Agent")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!supervisorTL.equals("NA") && !supervisorTL.equals("")) {
							userspom.SupervisorTL(supervisorTL);
						} else {
							if (supervisorTL.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (supervisorTL.equals("NA")) {
									test.log(LogStatus.FAIL, "SupervisorTL Droupdown Error");
								}
							}
							if (desktop.equals(desktop)) {
								userspom.UserTypeClickable();
							} else {
								if (desktop.equals("")) {
									System.out.println("Not Changed");
								}
							}
							if (api.equals(api)) {
								userspom.UserTypeAPIClickable();
							} else {
								if (api.equals("")) {
									System.out.println("Not Changed");
								}
							}
							
							try {
								if(country.equalsIgnoreCase("")) {
									test.log(LogStatus.PASS, "User Country Error");
								}else {
									userspom.Country(country);
									test.log(LogStatus.PASS, "User Country");
								}
							}catch(Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							
						
						try {
							if(languge.equalsIgnoreCase("")) {
								test.log(LogStatus.PASS, "User Language Error");
							}else {
								userspom.Lauguge(languge);
								test.log(LogStatus.PASS, "User language");
							}
						}catch(Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
							
							if (!greeting.equals("NA") && !greeting.equals("")) {
								userspom.GreetingFileDropdown(greeting);
							} else {
								if (greeting.equals("")) {
									System.out.println("Not Changed");
								} else {
									if (greeting.equals("NA")) {
										test.log(LogStatus.FAIL, "Greeting Droupdown Error");
									}
								}
								if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
									userspom.EnabledDropdown(enableDrp);
								} else {
									if (enableDrp.equals("")) {
										System.out.println("Not Changed");
									} else {
										if (enableDrp.equals("NA")) {
											test.log(LogStatus.FAIL, "Enabled Droupdown Error");
										}
									}

								}
							}
						}

					} else if (data.equalsIgnoreCase("Supervisor")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (!enablecalling.equals("NA") && !enablecalling.equals("")) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enablecalling.equals("NA")) {
									test.log(LogStatus.FAIL, "enablecalling Droupdown Error");
								}
							}
						}
						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (enableDrp.equals(enableDrp)) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							}
						}

					} else if (data.equalsIgnoreCase("campaign_supervisor")) {
						try {
							if (group != "") {
								userspom.selectGroupdropdown(group);
							} else {
								if (group.equals("")) {
									System.out.println("Not Changed");
								}
							}
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (campaign.equals(campaign)) {
							userspom.campaignName.clear();
							userspom.ManageCampaignName(campaign);
						} else {
							if (campaign.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("supervisor_TL")) {
						if (group != "") {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("wallboard")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}

						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					} else if (data.equalsIgnoreCase("operator")) {
						if (group != "") {
							userspom.selectGroupdropdown(group);
						} else {
							if (group.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (enablecalling.equals(enablecalling)) {
							userspom.EnabledCalling();
						} else {
							if (enablecalling.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (campaign.equals(campaign)) {
							userspom.ManageCampaignName(campaign);
						} else {
							if (campaign.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (desktop.equals(desktop)) {
							userspom.UserTypeClickable();
						} else {
							if (desktop.equals("")) {
								System.out.println("Not Changed");
							}
						}
						if (!enableDrp.equals("NA") && !enableDrp.equals("")) {
							userspom.EnabledDropdown(enableDrp);
						} else {
							if (enableDrp.equals("")) {
								System.out.println("Not Changed");
							} else {
								if (enableDrp.equals("NA")) {
									test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								}
							}

						}

					}

				}
			} else

			{
				test.log(LogStatus.FAIL, "Endpoint Droupdown Error");
			}

			{
				System.out.println("Endpoint error optional");
			}

			Thread.sleep(500);
			userspom.savebuttonclickble();
		}
	}

	private void handleAddOperation(String dropdown, String user, String pswrd, String confirmpswrd, String usage,
			String endpoint, String mobilenumber, String drpExtention, String ex_number, String ex_pswrd,
			String excon_pswrd, String enabledrecording, String enabledvoicemail, String autoAnswer,
			String displayevalution, String Gateway, String specificcalledid, String calledid, String email,
			String firstname, String lastname, String grouptype, String group, String enablecalling,
			String supervisorTL, String api, String desktop, String managename, String country, String languge,
			String greeting, String enableDrp, String campaign) throws InvalidFormatException, IOException, Exception {
		if (dropdown.equalsIgnoreCase("Add"))

		{

			userspom.UsersAddClick();
			try {
				userspom.setusernamepom(user);
				userspom.setPasswordpom(pswrd);
				userspom.setconfirmpasswordpom(confirmpswrd);
				try {
					userspom.usagetypeDropdown(usage);
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Usage Type Error");
					ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
					System.out.println("Exception occurred: " + e.getMessage());
					e.printStackTrace();
				}

				if (usage.equals("Callcenter")) {
					try {
						userspom.endPoint(endpoint);
						test.log(LogStatus.PASS, "EndPoint Droupdown");
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "EndPoint Droupdown Error");
						ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
						System.out.println("Exception occurred: " + e.getMessage());
						e.printStackTrace();
					}
					if (endpoint.equals("Mobile")) {
						try {
							userspom.mobileNumber(mobilenumber);
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Mobile Number Error");
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						try {
							System.out.println(Gateway);
							userspom.specifyGateway(Gateway);
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Specific Gateway Droupdown Error");
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						try {
							userspom.enterSpecifyCallerIdNumber(specificcalledid);
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Specific CallerId Number Error");
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}

						try {
							if (displayevalution.equalsIgnoreCase("No")) {

							} else {
								userspom.ClickDisplayEvalution();
							}
						} catch (Exception e) {
						}

						try {
							userspom.clidNumber(calledid);
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						try {
							userspom.enterEmail(email);
							userspom.setFirstName(firstname);
							userspom.setLastName(lastname);
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						try {
							userspom.selectGroupTypeDropdown(grouptype);
							test.log(LogStatus.PASS, "GroupType Droupdown");
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "GroupType Droupdown Error");
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						if (grouptype.equals("Agent")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown ");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								userspom.SuperviserTLDropdown(supervisorTL);
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Superviser TL Dropdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								userspom.UserTypeAPIClickable();
								userspom.UserTypeClickable();
								userspom.ManageQueueAgentName(managename);

							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							
							try {
								if(country.equalsIgnoreCase("")) {
									test.log(LogStatus.PASS, "User Country Error");
								}else {
									userspom.Country(country);
									test.log(LogStatus.PASS, "User Country");
								}
							}catch(Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							
						
						try {
							if(languge.equalsIgnoreCase("")) {
								test.log(LogStatus.PASS, "User Language Error");
							}else {
								userspom.Lauguge(languge);
								test.log(LogStatus.PASS, "User language");
							}
						}catch(Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}

							try {
								if (greeting.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "Greeting Droupdown Error");
								} else {
									userspom.GreetingFileDropdown(greeting);
									test.log(LogStatus.PASS, "Greeting Droupdown ");
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Greeting Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

							try {
								if (enableDrp.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								} else {
									userspom.EnabledDropdown(enableDrp);
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						} else if (grouptype.equals("Campaign Supervisor") || grouptype.equals("Operator")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown ");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (enablecalling.equals("Yes")) {
								userspom.EnabledCalling();
							}
							try {
								userspom.ManageCampaignName(campaign);
								test.log(LogStatus.PASS, "Campaign Droupdown ");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Campaign Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								if (enableDrp.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								} else {
									userspom.EnabledDropdown(enableDrp);
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						} else if (grouptype.equals("Supervisor") || grouptype.equals("Supervisor TL")) {
							try {
								try {
									userspom.selectGroupdropdown(group);
									test.log(LogStatus.PASS, "Group Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Group Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								if (enablecalling.equals("Yes")) {
									userspom.EnabledCalling();
								}
								try {
									userspom.EnabledDropdown(enableDrp);
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}

							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
						}

						if (grouptype.equals("Wallboard")) {
							try {
								try {
									userspom.selectGroupdropdown(group);
									test.log(LogStatus.PASS, "Group Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Group Droupdown Error");
								}
								try {
									if (enableDrp.equalsIgnoreCase("")) {
										test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									} else {
										userspom.EnabledDropdown(enableDrp);
									}
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}

							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
						}
					} else if (endpoint.equals("WebRtc")) {
						try {
							userspom.mobileNumber(mobilenumber);
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "MobileNumber Error");
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						if (autoAnswer.equalsIgnoreCase("Yes")) {
							userspom.ClickAutoAnswer();
						}
						if (displayevalution.equalsIgnoreCase("Yes")) {
							userspom.ClickDisplayEvalution();
						}
						try {
							userspom.clidNumber(calledid);

						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						try {
							userspom.enterEmail(email);
							userspom.setFirstName(firstname);
							userspom.setLastName(lastname);
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}

						try {
							userspom.selectGroupTypeDropdown(grouptype);
							test.log(LogStatus.PASS, "GroupType Droupdown ");
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "GroupType Droupdown Error");
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}

						if (grouptype.equals("Agent")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown ");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								userspom.SuperviserTLDropdown(supervisorTL);
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Supervisor TL Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								if (api.equalsIgnoreCase("Yes")) {
									userspom.UserTypeAPIClickable();
								}
								if (desktop.equalsIgnoreCase("Yes")) {
									userspom.UserTypeClickable();
								}
							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								userspom.ManageQueueAgentName(managename);
							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							
							try {
								if(country.equalsIgnoreCase("")) {
									test.log(LogStatus.PASS, "User Country Error");
								}else {
									userspom.Country(country);
									test.log(LogStatus.PASS, "User Country");
								}
							}catch(Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							
						
						try {
							if(languge.equalsIgnoreCase("")) {
								test.log(LogStatus.PASS, "User Language Error");
							}else {
								userspom.Lauguge(languge);
								test.log(LogStatus.PASS, "User language");
							}
						}catch(Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
							
							try {
								if (greeting.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "Greeting Droupdown Error");
								} else {
									userspom.GreetingFileDropdown(greeting);
									test.log(LogStatus.FAIL, "Greeting Droupdown");
								}

							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Greeting Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								if (enableDrp.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								} else {
									userspom.EnabledDropdown(enableDrp);
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						} else if (grouptype.equals("Campaign Supervisor") || grouptype.equals("Operator")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (enablecalling.equals("Yes")) {
								userspom.EnabledCalling();
							}
							userspom.ManageCampaignName(campaign);
							try {
								userspom.ManageCampaignName(campaign);
								test.log(LogStatus.PASS, "Campaign Droupdown ");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Campaign Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						} else if (grouptype.equals("Supervisor") || grouptype.equals("Supervisor TL")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (enablecalling.equals("Yes")) {
								userspom.EnabledCalling();
							}
							try {
								userspom.EnabledDropdown(enableDrp);
								test.log(LogStatus.PASS, "Enabled Droupdown ");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						} else if (grouptype.equals("Wallboard")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								if (enableDrp.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								} else {
									userspom.EnabledDropdown(enableDrp);
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						}
					} else if (endpoint.equals("SoftPhone/IP-Phone")) {
						if (drpExtention != "") {
							userspom.clickUseExistingExtention();
							try {

								userspom.extention_dropdown(drpExtention);
								test.log(LogStatus.PASS, "Extention Droupdown");

							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Extention Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
						} else {
							if (ex_number != "") { // new
								try {
									userspom.clickCreateNewExtention();
									userspom.extentionNumber(ex_number);
									userspom.extentionPassword(ex_pswrd);
									userspom.extentionConfirmPassword(excon_pswrd);
								} catch (Exception e) {
									System.out.println("");
								}
							} else {
								System.out.println("error on extention");
							}
						}
						try {
							if (enabledrecording.equalsIgnoreCase("Yes")) {
								userspom.clickEnabled_recording();
							} else {
								System.out.println("");
							}
							if (enabledvoicemail.equalsIgnoreCase("Yes")) {
								userspom.clickEnaled_voicemail();
							} else {
								System.out.println("");
							}

						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						try {
							userspom.mobileNumber(mobilenumber);
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}

						if (displayevalution.equalsIgnoreCase("Yes")) {
							userspom.ClickDisplayEvalution();
						}
						try {

							userspom.clidNumber(calledid);
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						try {
							userspom.enterEmail(email);
							userspom.setFirstName(firstname);
							userspom.setLastName(lastname);
						} catch (Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}

						try {
							userspom.selectGroupTypeDropdown(grouptype);
							test.log(LogStatus.PASS, "GroupType Droupdown ");
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "GroupType Droupdown Error");
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						if (grouptype.equals("Agent")) {
							userspom.selectGroupdropdown(group);
							test.log(LogStatus.PASS, "Group Droupdown Error");
							try {
								userspom.SuperviserTLDropdown(supervisorTL);
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "SupervisorTL Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (api.equalsIgnoreCase("Yes")) {
								userspom.UserTypeAPIClickable();
							}
							if (desktop.equalsIgnoreCase("Yes")) {
								userspom.UserTypeClickable();
							}
							try {
								userspom.ManageQueueAgentName(managename);
							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							
							try {
								if(country.equalsIgnoreCase("")) {
									test.log(LogStatus.PASS, "User Country Error");
								}else {
									userspom.Country(country);
									test.log(LogStatus.PASS, "User Country");
								}
							}catch(Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							
						
						try {
							if(languge.equalsIgnoreCase("")) {
								test.log(LogStatus.PASS, "User Language Error");
							}else {
								userspom.Lauguge(languge);
								test.log(LogStatus.PASS, "User language");
							}
						}catch(Exception e) {
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
							
							
							try {
								if (greeting.equalsIgnoreCase("")) {

								} else {
									userspom.GreetingFileDropdown(greeting);
								}
							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								if (enableDrp.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								} else {
									userspom.EnabledDropdown(enableDrp);
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						} else if (grouptype.equals("Campaign Supervisor") || grouptype.equals("Operator")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (enablecalling.equals("Yes")) {
								userspom.EnabledCalling();
							} else {
								System.out.println("not selected Enablecalling");
							}
							try {
								userspom.ManageCampaignName(campaign);
								test.log(LogStatus.PASS, "Campaign Droupdown");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Campaign Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								if (enableDrp.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								} else {
									userspom.EnabledDropdown(enableDrp);
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						} else if (grouptype.equals("Supervisor")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (enablecalling.equals("Yes")) {
								userspom.EnabledCalling();
							}
							try {
								if (enableDrp.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								} else {
									userspom.EnabledDropdown(enableDrp);
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
						} else if (grouptype.equals("Wallboard")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

							try {
								userspom.ManageCampaignName(campaign);
								test.log(LogStatus.PASS, "Campaign Droupdown ");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Campaign Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

							try {
								if (enableDrp.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								} else {
									userspom.EnabledDropdown(enableDrp);
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						} else if (grouptype.equals("Supervisor TL")) {
							try {
								userspom.selectGroupdropdown(group);
								test.log(LogStatus.PASS, "Group Droupdown ");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Group Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (enablecalling.equals("Yes")) {
								userspom.EnabledCalling();
							}

							try {
								userspom.ManageCampaignName(campaign);
								test.log(LogStatus.PASS, "Campaign Droupdown ");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Campaign Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

							try {
								if (enableDrp.equalsIgnoreCase("")) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								} else {
									userspom.EnabledDropdown(enableDrp);
								}
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}

						}
					} else {
						System.out.println("Select usage type");
					}
				} else {
					if (usage.equals("PBX + Callcenter") || usage.equals("PBX")) {
						try {
							userspom.endPoint(endpoint);
							test.log(LogStatus.PASS, "Endpoint Droupdown ");
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Endpoint Droupdown Error");
							ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
							System.out.println("Exception occurred: " + e.getMessage());
							e.printStackTrace();
						}
						if (endpoint.equals("SoftPhone/IP-Phone")) {
							if (drpExtention != "") {
								userspom.clickUseExistingExtention();
								try {
									userspom.extention_dropdown(drpExtention);
									test.log(LogStatus.PASS, "Extention Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Extention Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
							} else {
								if (ex_number != "") {
									userspom.clickCreateNewExtention();
									try {
										userspom.extentionNumber(ex_number);
									} catch (Exception e) {
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
									try {
										userspom.extentionPassword(ex_pswrd);
										userspom.extentionConfirmPassword(excon_pswrd);
									} catch (Exception e) {
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
								}
							}
							try {
								if (enabledrecording.equalsIgnoreCase("Yes")) {
									userspom.clickEnabled_recording();
								} else {
									System.out.println("");
								}

								if (enabledvoicemail.equalsIgnoreCase("Yes")) {
									userspom.clickEnaled_voicemail();
								} else {
									System.out.println("");
								}
							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								userspom.mobileNumber(mobilenumber);
							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (displayevalution.equalsIgnoreCase("Yes")) {
								userspom.ClickDisplayEvalution();
							}
							try {
								userspom.clidNumber(calledid);
							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								userspom.enterEmail(email);
								userspom.setFirstName(firstname);
								userspom.setLastName(lastname);
							} catch (Exception e) {
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							try {
								userspom.selectGroupTypeDropdown(grouptype);
								test.log(LogStatus.PASS, "GroupType Droupdown");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "GroupType Droupdown Error");
								ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
								System.out.println("Exception occurred: " + e.getMessage());
								e.printStackTrace();
							}
							if (grouptype.equals("Agent")) {
								try {
									userspom.selectGroupdropdown(group);
									test.log(LogStatus.PASS, "Group Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Group Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								try {
									userspom.SuperviserTLDropdown(supervisorTL);
									test.log(LogStatus.PASS, "SupervisorTL Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "SupervisorTL Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								try {
									if (api.equalsIgnoreCase("Yes")) {
										userspom.UserTypeAPIClickable();
									}
									if (desktop.equalsIgnoreCase("Yes")) {
										userspom.UserTypeClickable();
									}
								} catch (Exception e) {
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								try {
									userspom.ManageQueueAgentName(managename);
									try {
										userspom.GreetingFileDropdown(greeting);
										test.log(LogStatus.PASS, "Greeting Droupdown ");
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "Greeting Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
								} catch (Exception e) {
									System.out.println("queue optional");
								}
								try {
									if (enableDrp.equalsIgnoreCase("")) {
										test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									} else {
										userspom.EnabledDropdown(enableDrp);
									}
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
							} else if (grouptype.equals("Campaign Supervisor") || grouptype.equals("Operator")) {
								try {
									userspom.selectGroupdropdown(group);
									test.log(LogStatus.PASS, "Group Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Group Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								if (enablecalling.equals("Yes")) {
									userspom.EnabledCalling();
								}

								try {
									userspom.ManageCampaignName(campaign);
									test.log(LogStatus.PASS, "Campaign Droupdown");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Campaign Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}

							} else if (grouptype.equals("Supervisor")) {
								try {
									userspom.selectGroupdropdown(group);
									test.log(LogStatus.PASS, "Group Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Group Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								if (enablecalling.equals("Yes")) {
									userspom.EnabledCalling();
								}
								try {
									if (enableDrp.equalsIgnoreCase("")) {
										test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									} else {
										userspom.EnabledDropdown(enableDrp);
									}
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
							} else if (grouptype.equals("Supervisor TL")) {
								try {
									userspom.SuperviserTLDropdown(supervisorTL);
									test.log(LogStatus.PASS, "Group Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Group Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								if (enablecalling.equals("Yes")) {
									userspom.EnabledCalling();
								}
								try {
									if (enableDrp.equalsIgnoreCase("")) {
										test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									} else {
										userspom.EnabledDropdown(enableDrp);
									}
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}

							} else if (grouptype.equals("Wallboard")) {
								try {
									userspom.selectGroupdropdown(group);
									test.log(LogStatus.PASS, "Group Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Group Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								if (enablecalling.equals("Yes")) {
									userspom.EnabledCalling();
								}
								try {
									if (enableDrp.equalsIgnoreCase("")) {
										test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									} else {
										userspom.EnabledDropdown(enableDrp);
									}
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}

							}
						} else if (usage.equals("PBX")) {
							userspom.endPoint(endpoint);
							if (endpoint.equals("SoftPhone/IP-Phone")) {
								if (drpExtention != "") {
									userspom.clickUseExistingExtention();
									try {
										userspom.extention_dropdown(drpExtention);
										test.log(LogStatus.PASS, "Extention Droupdown ");
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "Extention Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
								} else {
									if (ex_number != "") {
										userspom.clickCreateNewExtention();
										try {
											userspom.extentionNumber(ex_number);
										} catch (Exception e) {
											ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
													"error_screenshot.png");
											System.out.println("Exception occurred: " + e.getMessage());
											e.printStackTrace();
										}
										try {
											userspom.extentionPassword(ex_pswrd);
											userspom.extentionConfirmPassword(excon_pswrd);
										} catch (Exception e) {
											ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
													"error_screenshot.png");
											System.out.println("Exception occurred: " + e.getMessage());
											e.printStackTrace();
										}

									}
								}
								try {
									if (enabledrecording.equalsIgnoreCase("Yes")) {
										userspom.clickEnabled_recording();
									} else {
										if (enabledvoicemail.equalsIgnoreCase("Yes")) {
											userspom.clickEnaled_voicemail();
										}
									}
								} catch (Exception e) {
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								try {
									userspom.mobileNumber(mobilenumber);
								} catch (Exception e) {
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								if (displayevalution.equalsIgnoreCase("Yes")) {
									userspom.ClickDisplayEvalution();
								}
								try {
									userspom.enterEmail(email);
									userspom.setFirstName(firstname);
									userspom.setLastName(lastname);
								} catch (Exception e) {
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								try {
									userspom.selectGroupTypeDropdown(grouptype);
									test.log(LogStatus.PASS, "GroupType Droupdown ");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "GroupType Droupdown Error");
									ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
											"error_screenshot.png");
									System.out.println("Exception occurred: " + e.getMessage());
									e.printStackTrace();
								}
								if (grouptype.equals("Agent")) {
									try {
										userspom.selectGroupdropdown(group);
										test.log(LogStatus.PASS, "Group Droupdown ");
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "Group Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
									try {
										userspom.SuperviserTLDropdown(supervisorTL);
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "SupervisorTL Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
									try {
										if (api.equalsIgnoreCase("Yes")) {
											userspom.UserTypeAPIClickable();
										}
										if (desktop.equalsIgnoreCase("Yes")) {
											userspom.UserTypeClickable();
										}
										try {
											userspom.GreetingFileDropdown(greeting);
										} catch (Exception e) {
											test.log(LogStatus.FAIL, "Greeting Droupdown Error");
											ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
													"error_screenshot.png");
											System.out.println("Exception occurred: " + e.getMessage());
											e.printStackTrace();
										}
									} catch (Exception e) {
										System.out.println("User Type Clickble error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
									try {
										if (enableDrp.equalsIgnoreCase("")) {
											test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
										} else {
											userspom.EnabledDropdown(enableDrp);
										}
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}

								} else if (grouptype.equals("Campaign Supervisor") || grouptype.equals("Operator")) {
									try {
										userspom.selectGroupdropdown(group);
										test.log(LogStatus.PASS, "Group Droupdown");
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "Group Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
									try {
										userspom.ManageCampaignName(campaign);
									} catch (Exception e) {
										test.log(LogStatus.PASS,
												"User Campaign selection in User Module is not Selected");
									}

									try {
										if (enableDrp.equalsIgnoreCase("")) {
											test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
										} else {
											userspom.EnabledDropdown(enableDrp);
										}
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}

								} else if (grouptype.equals("Supervisor") || grouptype.equals("Wallboard")) {
									try {
										userspom.selectGroupdropdown(group);
										test.log(LogStatus.PASS, "Group Droupdown ");
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "Group Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
									try {
										if (enableDrp.equalsIgnoreCase("")) {
											test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
										} else {
											userspom.EnabledDropdown(enableDrp);
										}
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}

								} else if (grouptype.equals("Supervisor TL")) {
									try {
										userspom.SuperviserTLDropdown(supervisorTL);
										test.log(LogStatus.PASS, "Group Droupdown ");
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "Group Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}
									try {
										if (enableDrp.equalsIgnoreCase("")) {
											test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
										} else {
											userspom.EnabledDropdown(enableDrp);
										}
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "User Enabled Droupdown Error");
										ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots",
												"error_screenshot.png");
										System.out.println("Exception occurred: " + e.getMessage());
										e.printStackTrace();
									}

								}
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Data not provided");
				ScreenShotClass.captureScreenshot(driver, "ErrorScreenshots", "error_screenshot.png");
				System.out.println("Exception occurred: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@DataProvider(name = "UserData")
	public Object[][] getData() throws IOException, InterruptedException {
		String path = BaseClass.path;
		excel = new ExcelUtils(path);
		int startRow = 2;
		int startCol = 2;
		int totalRows = excel.getRowCount(sheetName);
		int totalCols = 37;

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

	@AfterClass()
	public void closeBrowser() {
		report.endTest(test);
		report.flush();
	}

}
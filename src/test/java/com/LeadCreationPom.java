package com.intalk.pom;

import java.io.IOException;
import java.util.List;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.util.NumericRanges;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.reporter.configuration.StatusFilter;
import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.relevantcodes.extentreports.LogStatus;

public class UsersModelPom extends BaseClass {
	Actions act;
	static String s;
	@FindBy(xpath = "//*[@id=\"mySidenav\"]/div[2]/ul/li[1]/a")
	public WebElement edit;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/form/div[1]/div/div/div[2]/span/button[1]")
	public WebElement delete;
	@FindBy(xpath = "//span[@class='checkmark']")
	public WebElement deleteclick;
	@FindBy(xpath = "//*[@id=\"mySidenav\"]/div[2]/ul/li[1]/a")
	public WebElement UserModule;
	@FindBy(xpath = "//span[text()='Username is required']")
	public WebElement required_error;
	@FindBy(xpath = "//span[@ng-show='errusername']")
	public WebElement username_already;
	@FindBy(xpath = "//a[@class='apps-icon ng-binding']")
	public WebElement clickApps;
	@FindBy(xpath = "//a[@href='#!/users/add']")
	public WebElement adduser;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[1]/div/div[2]/input")
	public WebElement username_user;
	@FindBy(xpath = "//span[@ng-show='UserAdd.username_new.$invalid && UserAdd.username_new.$error.minlength']")
	public WebElement username_validation;
	@FindBy(xpath = "//span[@ng-show='UserAdd.password.$invalid && UserAdd.password.$error.minlength']")
	public WebElement password_err;
	@FindBy(xpath = "//span[@ng-show='UserAdd.password.$touched && UserAdd.password.$error.required']")
	public WebElement password_req;
	@FindBy(xpath = "//input[@id='pass_word']")
	public WebElement password_user;
	@FindBy(xpath = "//span[@ng-show='checkpass']")
	public WebElement Confirm_password_err;
	@FindBy(xpath = "//input[@id='pass_word_cnf']")
	public WebElement Confirm_password_user;
	@FindBy(xpath = "//select[@id='usage_type']")
	public WebElement usage_type;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[5]/div/div[2]/select")
	public WebElement End_Point;
	@FindBy(xpath = "//*[@id=\"gateway_uuid\"]")
	public WebElement specifieGateway;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[7]/div/div[2]/span[1]")
	public WebElement specifieGateway_err;
	@FindBy(xpath = "//input[@name='phone_number']")
	public WebElement phone_number;
	@FindBy(xpath = "//span[@ng-show='UserAdd.phone_number.$invalid-pattern && UserAdd.phone_number.$touched']")
	public WebElement phone_number_requird;
	@FindBy(xpath = "//span[@ng-show='Duplicate_checked != 0']")
	public WebElement phone_number_already;
	@FindBy(xpath = "//span[@ng-show='UserAdd.phone_number.$invalid-pattern && UserAdd.phone_number.$touched']")
	public WebElement phone_number_error;

	@FindBy(xpath = "//input[@id='autoAnswer']")
	public WebElement checkBox_autoAnswer_users;
	@FindBy(xpath = "//input[@id='call_evaluation_information']")
	public WebElement checkBox_users;
	@FindBy(xpath = "//select[@name='user_language']")
	public WebElement Language_users;
	@FindBy(xpath = "//input[@name='outbound_caller_id_number']")
	public WebElement CLID_Number;
	@FindBy(xpath = "//span[@ng-show='UserAdd.outbound_caller_id_number.$invalid-pattern && UserAdd.outbound_caller_id_number.$touched']")
	public WebElement CLID_Number_err;
	@FindBy(xpath = "//span[@ng-show='errdialplan_number']")
	public WebElement CLID_already;
	@FindBy(xpath = "//input[@name='user_email']")
	public WebElement EmailId_users;
	@FindBy(xpath = "//span[@ng-show='UserAdd.user_email.$dirty&&UserAdd.user_email.$error.pattern']")
	public WebElement EmailId_users_err;
	@FindBy(xpath = "(//span[@ng-show='erremail'])[2]")
	public WebElement EmailId_users_already;
	@FindBy(xpath = "//input[@name='contact_name_given']")
	public WebElement FirstName;
	@FindBy(xpath = "//input[@name='contact_name_family']")
	public WebElement LastName;
	@FindBy(xpath = "//input[@name='user_type']")
	public WebElement UserType_checkbox;
	@FindBy(xpath = "//input[@value='api_agents']")
	public WebElement UserTypeAPI_checkbox;
	@FindBy(xpath = "//select[@name='user_enabled']")
	public WebElement Enabled_users;
	@FindBy(xpath = "//a[@class='btn btn-info ng-binding ng-scope']")
	public WebElement AdvanceClick;
	@FindBy(xpath = "//input[@value='call']")
	public WebElement UserApp_users;
	@FindBy(xpath = "//input[@id='insert']")
	public WebElement Users_Save;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[5]/div/div[2]/select")
	public WebElement Group_Type;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[6]/div/div[2]/select")
	public WebElement Group_Dropdown;
	@FindBy(xpath = "//select[@name='user_voicemail']")
	public WebElement GreetingFile;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[7]/div/div[2]/select")
	public WebElement supervisorTL;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[1]")
	public WebElement manageQueuename1;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[4]")
	public WebElement manageQueuename4;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[7]")
	public WebElement manageQueuename7;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[10]")
	public WebElement manageQueuename10;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[13]")
	public WebElement manageQueuename13;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[16]")
	public WebElement manageQueuename16;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[19]")
	public WebElement manageQueuename19;
	@FindBy(xpath = "//input[@class='select2-search__field']")
	public WebElement SearchmanageQueuename;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[2]")
	public static WebElement manageTierlevel2;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[5]")
	public static WebElement manageTierlevel5;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[8]")
	public static WebElement manageTierlevel8;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[11]")
	public static WebElement manageTierlevel11;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[14]")
	public static WebElement manageTierlevel14;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[17]")
	public static WebElement manageTierlevel17;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[20]")
	public static WebElement manageTierlevel20;
	@FindBy(xpath = "//input[@class='select2-search__field']")
	public static WebElement searchtierlevel;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[3]")
	public WebElement manageTierPosition3;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[6]")
	public WebElement manageTierPosition6;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[9]")
	public WebElement manageTierPosition9;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[12]")
	public WebElement manageTierPosition12;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[15]")
	public WebElement manageTierPosition15;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[18]")
	public WebElement manageTierPosition18;
	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[21]")
	public WebElement manageTierPosition21;

	@FindBy(xpath = "//input[@class='select2-search__field']")
	public WebElement searchtierpossition;

	@FindBy(xpath = "//input[@id='default_outbound_0']")
	public WebElement manageDefaultQueue0;
	@FindBy(xpath = "//input[@id='default_outbound_1']")
	public WebElement manageDefaultQueue1;
	@FindBy(xpath = "//input[@id='default_outbound_2']")
	public WebElement manageDefaultQueue2;
	@FindBy(xpath = "//input[@id='default_outbound_3']")
	public WebElement manageDefaultQueue3;
	@FindBy(xpath = "//input[@id='default_outbound_4']")
	public WebElement manageDefaultQueue4;
	@FindBy(xpath = "//input[@id='default_outbound_5']")
	public WebElement manageDefaultQueue5;
	@FindBy(xpath = "//input[@id='default_outbound_6']")
	public WebElement manageDefaultQueue6;
	@FindBy(xpath = "//input[@id='default_outbound_7']")
	public WebElement manageDefaultQueue7;

	@FindBy(xpath = "//input[@id='outbound_checkbox_0']")
	public WebElement manageDefaultOutbound0;
	@FindBy(xpath = "//input[@id='outbound_checkbox_1']")
	public WebElement manageDefaultOutbound1;
	@FindBy(xpath = "//input[@id='outbound_checkbox_2']")
	public WebElement manageDefaultOutbound2;
	@FindBy(xpath = "//input[@id='outbound_checkbox_3']")
	public WebElement manageDefaultOutbound3;
	@FindBy(xpath = "//input[@id='outbound_checkbox_4']")
	public WebElement manageDefaultOutbound4;
	@FindBy(xpath = "//input[@id='outbound_checkbox_5']")
	public WebElement manageDefaultOutbound5;
	@FindBy(xpath = "//input[@id='outbound_checkbox_6']")
	public WebElement manageDefaultOutbound6;
	@FindBy(xpath = "//input[@id='outbound_checkbox_7']")
	public WebElement manageDefaultOutbound7;

	@FindBy(xpath = "//i[@class='fa fa-plus-circle']")
	public WebElement manageAddButton;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[9]/div/div[2]/table/tbody/tr/td[5]")
	public WebElement deleteQueue;
	@FindBy(xpath = "//select[@name='user_enabled']")
	public WebElement enabledrp1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[2]/div/div[2]/span[1]/i")
	public WebElement passwordEye;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[3]/div/div[2]/span[1]/i")
	public WebElement confirmpasswordEye;
	@FindBy(xpath = "//input[@value='chat']")
	public WebElement chat;
	@FindBy(xpath = "//input[@value='call']")
	public WebElement call;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[7]/div[1]/div/div[2]/input")
	public WebElement ExtentionNumber;
	@FindBy(xpath = "//span[text()=' Enter only 4 digits ']")
	public WebElement extention_error;
	@FindBy(xpath = "//span[text()='Extension_number_already_Used. ']")
	public WebElement extention_already;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[6]/div/div[2]/label[1]/input")
	public WebElement createnewExtention;
	@FindBy(xpath = "//input[@name='weekly']")
	public WebElement useExitingExtention;
	@FindBy(xpath = "//input[@id='ext_pass_word']")
	public WebElement extentionPassword;
	@FindBy(xpath = "//span[@ng-show='UserAdd.ext_password.$invalid-pattern && UserAdd.ext_password.$touched']")
	public WebElement extentionPassword_err;
	@FindBy(xpath = "//input[@id='conf_ext_pass_word']")
	public WebElement extentionconfirmPassword;
	@FindBy(xpath = "//span[text()=' Password must Numeric ,Char And symbol(@#$%) with atleast one capital letter and length of password should be 4-25 character ']")
	public WebElement exconfirmPassword_err;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[7]/div[3]/div/div[2]/span[4]")
	public WebElement exconfirmPassword_matching;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[7]/div/div[2]/select")
	public WebElement extention;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[7]/div[2]/div/div[2]/span[1]/i")
	public WebElement extentionPassword_eye;
	@FindBy(xpath = "(//i[@class='glyphicon glyphicon-eye-open ng-scope'])[5]")
	public WebElement extentionconfirmPassword_eye;
	@FindBy(xpath = "//input[@name='Enable_Recording']")
	public WebElement enabled_recording;
	@FindBy(xpath = "//input[@name='Enable_Voicemail']")
	public WebElement enabled_voicemail;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[8]/div/div[2]/span[1]/span[1]/span/ul/li/input")
	public WebElement campaignNamee;
	@FindBy(xpath = "//input[@name='outbound_caller_id_num']")
	public WebElement specifyCallerIdNumber;

	@FindBy(xpath = "//span[text()=' User Specific Caller ID must be Numeric. ']")
	public WebElement specifyCallerIdNumber_err;
	@FindBy(xpath = "//input[@class='select2-search__field']")
	public WebElement campaignName;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[8]/div/div[2]/span[2]")
	public WebElement campaignName_err;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/div/div")
	public static WebElement title;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/form/div[1]/div/div/div[2]/span/div/div/div/div[3]/button")
	public WebElement sumbitfilter;
	@FindBy(xpath = "(//button[@class='btn btn-success ng-binding'])[1]")
	public static WebElement filterclick;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/form/div[1]/div/div/div[2]/span/div/div/div/div[2]/div/div[1]/input")
	public static WebElement filteruser;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/form/div[1]/div/div/div[2]/span/div/div/div/div[2]/div/div[4]/select")
	public static WebElement statusfilter;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/form/div[1]/div/div/div[2]/span/div/div/div/div[2]/div/div[3]/select")
	public WebElement groupfilter;
	@FindBy(xpath = "//*[@id=\"userlist\"]/tbody/tr/td[8]/button[3]")
	public static WebElement changepassword;
	@FindBy(xpath = "//*[@id=\"password1\"]")
	public static WebElement ppassword;
	@FindBy(xpath = "//*[@id=\"change_password\"]/div/div/div[2]/div[1]/span[1]/i")
	public static WebElement Eyeppassword;
	@FindBy(xpath = "//*[@id=\"cnfrm_password1\"]")
	public static WebElement confirmpasswordd;
	@FindBy(xpath = "//*[@id=\"change_password\"]/div/div/div[2]/div[2]/span[1]/i")
	public static WebElement Eyeconfirmppassword;
	@FindBy(xpath = "//*[@id=\"enable_calling\"]")
	public static WebElement EnableCalling;
	@FindBy(xpath = "//*[@id=\"change_password\"]/div/div/div[3]/input")
	public static WebElement update;
	@FindBy(xpath = "//*[@id=\"userlist\"]/tbody/tr[1]/td[8]/button[1]")
	public static WebElement EditClickButton;
//-------------------------------------------------------------------------------------------------------
	@FindBy(xpath = "//*[@id=\"usage_type\"]")
	public static WebElement Editusage;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[3]/div/div[2]/select")
	public static WebElement Editendpoint;
	@FindBy(xpath = "//select[@name='gateway_uuid']")
	public WebElement specifieGateway1;
	@FindBy(xpath = "//span[@ng-if='gatewayIdErrorMsg']")
	public WebElement specifieGateway1_error;
	@FindBy(xpath = "//input[@name='phone_number']")
	public WebElement phone_number1;

	@FindBy(xpath = "//*[@id=\"autoAnswer\"]")
	public WebElement checkBox_autoAnswer_users1;
	@FindBy(xpath = "//*[@id=\"call_evaluation_information\"]")
	public WebElement checkBox_users1;
	@FindBy(xpath = "//select[@name='user_language']")
	public WebElement Language_users1;
	@FindBy(xpath = "//input[@name='outbound_caller_id_number']")
	public WebElement CLID_Number1;
	@FindBy(xpath = "//input[@name='user_email']")
	public WebElement EmailId_users1;
	@FindBy(xpath = "//input[@name='contact_name_given']")
	public WebElement FirstName1;
	@FindBy(xpath = "//input[@name='contact_name_family']")
	public WebElement LastName1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[8]/div/div[2]/label[1]/input")
	public WebElement UserType_checkbox1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[8]/div/div[2]/label[2]/input")
	public WebElement UserTypeAPI_checkbox1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[11]/div[1]/div[2]/select")
	public WebElement Enabled_users1;
	@FindBy(xpath = "//a[@class='btn btn-info ng-binding ng-scope']")
	public WebElement AdvanceClick1;
	@FindBy(xpath = "//input[@value='call']")
	public WebElement UserApp_users1;
	@FindBy(xpath = "//input[@id='insert']")
	public WebElement Users_Save1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[6]/div/div[2]/select")
	public WebElement Group_Dropdown1;
	@FindBy(xpath = "//select[@name='user_voicemail']")
	public WebElement GreetingFile1;
	@FindBy(xpath = "//select[@name='supervisor_team_lead']")
	public WebElement supervisorTL1;
	@FindBy(xpath = "//select[@name='user_enabled']")
	public WebElement enabledrp;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[2]/div/div[2]/span[1]/i")
	public WebElement passwordEye1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[3]/div/div[2]/span[1]/i")
	public WebElement confirmpasswordEye1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[11]/div[3]/div/div/div[2]/label[2]/input")
	public WebElement chat1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[11]/div[3]/div/div/div[2]/label[1]/input")
	public WebElement call1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[5]/div[1]/div/div[2]/input")
	public WebElement ExtentionNumber1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[4]/div/div[2]/label[1]/input")
	public WebElement createnewExtention1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[4]/div/div[2]/label[2]/input")
	public WebElement useExitingExtention1;
	@FindBy(xpath = "//*[@id=\"ext_pass_word\"]")
	public WebElement extentionPassword1;
	@FindBy(xpath = "//*[@id=\"conf_ext_pass_word\"]")
	public WebElement extentionconfirmPassword1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[7]/div[3]/div/div[2]/span[4]")
	public WebElement exconfirmPassword_matching1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[5]/div/div[2]/select")
	public WebElement extention1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[5]/div[2]/div/div[2]/span[1]/i")
	public WebElement extentionPassword_eye1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[5]/div[3]/div/div[2]/span[1]/i")
	public WebElement extentionconfirmPassword_eye1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[6]/div[2]/label[1]/input")
	public WebElement enabled_recording1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div[6]/div[2]/label[2]/input")
	public WebElement enabled_voicemail1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[8]/div/div[2]/span[1]/span[1]/span/ul/li/input")
	public WebElement campaignNamee1;
	@FindBy(xpath = "//*[@id=\"outbound_caller_id_num\"]")
	public WebElement specifyCallerIdNumber1;
	@FindBy(xpath = "//input[@class='select2-search__field']")
	public WebElement campaignName1;
	@FindBy(xpath = "//input[@id='enable_calling']")
	public WebElement emabledcalling1;
	@FindBy(xpath = "//select[@name='group_type']")
	public static WebElement Editgrouptype;
	@FindBy(xpath = "//input[@name='outbound_caller_id_num']")
	public static WebElement specifyCallerId;

	public SoftAssert softAssert;

	public UsersModelPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		softAssert = new SoftAssert();
	}

	public static void Data() {

		s = Editgrouptype.getAttribute("value");
		System.out.println(s + "AgentType===========");
	}

	public void AppsClick() throws InterruptedException {
		clickApps.click();

	}

	public void DeleteClick() throws InterruptedException {
		delete.click();

	}

	public void EditClick() throws InterruptedException {
		edit.click();

	}

	public void EditClickbutton() throws InterruptedException {
		EditClickButton.click();

	}

	public void UsersAddClick() throws InterruptedException {
		adduser.sendKeys(Keys.ENTER);

	}

	public void setusernamepom(String user) throws InvalidFormatException, IOException, Exception {
		boolean text = username_user.isEnabled();
		username_user.sendKeys(user);
		username_user.sendKeys(Keys.TAB);
//		boolean validation1 = username_already.isDisplayed();
//		boolean validation2 = required_error.isDisplayed();
//		if (text) {
//			text = true;
//			if (validation1) {
//				validation1 = true;
//				test.log(LogStatus.FAIL, "Invalid Username =>  Username already used " + "=>" + user);
//				Assert.assertFalse(false);
//			} else if (validation2) {
//				validation2 = true;
//				test.log(LogStatus.FAIL, "Invalid Username =>  Username Error " + "=>" + user);
//				Assert.assertFalse(false);
//			} else {
//				test.log(LogStatus.PASS, "valid Username => Correct Username  " + "=>" + user);
//			}
//		}

	}

	public void setPasswordpom(String password) throws IOException {
		boolean text = password_user.isEnabled();
		password_user.sendKeys(password);
		passwordEye.click();
		password_user.sendKeys(Keys.TAB);
//		boolean validation1 = password_err.isDisplayed();
//		boolean validation2 = password_req.isDisplayed();
//		if (text) {
//			text = true;
//			if (validation1) {
//				validation1 = true;
//				test.log(LogStatus.FAIL, "Invalid Password => Password Error  " + "=>" + password);
//				softAssert.assertTrue(false);
//			} else {
//				if (validation2) {
//					validation2 = true;
//					test.log(LogStatus.FAIL, "Invalid Password =>  Password is required " + "=>" + password);
//					softAssert.assertTrue(false);
//				} else {
//					test.log(LogStatus.PASS, "valid Password => Correct Password  " + "=>" + password);
//
//				}
//			}
//		}
	}

	public void setconfirmpasswordpom(String confirmpassword) throws InvalidFormatException, IOException {
		boolean textbox = Confirm_password_user.isEnabled();
		Confirm_password_user.sendKeys(confirmpassword);
		confirmpasswordEye.click();
		Confirm_password_user.sendKeys(Keys.TAB);
//		boolean validation1 = Confirm_password_err.isDisplayed();
//		if (textbox) {
//			textbox = true;
//			if (validation1) {
//				validation1 = true;
//				test.log(LogStatus.FAIL,
//						"Invalid ConfirmPassword =>  ConfirmPassword must be same as Password / Required field ";
//				softAssert.assertTrue(false);
//			} else {
//				test.log(LogStatus.PASS, "valid ConfirmPassword => Correct Password  " + "=>" + confirmpassword);
//
//			}
//		}

	}

	public void usagetypeDropdown(String usagedropdown) throws InvalidFormatException, IOException {
		boolean dropdown = usage_type.isEnabled();
		if (dropdown) {
			dropdown = true;
			usage_type.click();
			Select usage = new Select(usage_type);
			usage.selectByVisibleText(usagedropdown);
			usage_type.click();

		}
	}

	public void endPoint(String endpoint) throws EncryptedDocumentException, IOException {
		boolean dropdown = End_Point.isEnabled();
		if (dropdown) {
			dropdown = true;
			End_Point.click();
			Select usage = new Select(End_Point);
			System.out.println(endpoint);
			usage.selectByVisibleText(endpoint);
			End_Point.click();

		}

	}

	public void clickCreateNewExtention() throws EncryptedDocumentException, InterruptedException, IOException {

		// CommonUtils.ButtonIsClickable(createnewExtention, "Create New Extention");
		createnewExtention.click();

	}

	public void clickUseExistingExtention() throws EncryptedDocumentException, InterruptedException, IOException {

		// CommonUtils.ButtonIsClickable(useExitingExtention, "Use Existing Extention");
		useExitingExtention.click();
	}

	public void extentionNumber(String extention) throws InvalidFormatException, IOException {
		boolean textbox = ExtentionNumber.isEnabled();
		ExtentionNumber.sendKeys(extention);
		ExtentionNumber.sendKeys(Keys.TAB);

	}

	public void extentionPassword(String password) throws InvalidFormatException, IOException {
		boolean textbox = extentionPassword.isEnabled();
		extentionPassword.sendKeys(password);
		extentionPassword.sendKeys(Keys.TAB);

	}

	public void extention_passwordEye() throws EncryptedDocumentException, InterruptedException, IOException {
		extentionPassword_eye.click();

	}

	public void extentionConfirmPassword(String confirmpassword) throws InvalidFormatException, IOException {
		boolean textbox = extentionconfirmPassword.isEnabled();
		extentionconfirmPassword.sendKeys(confirmpassword);
		extentionconfirmPassword.sendKeys(Keys.TAB);
	}

	public void extention_confirmpasswordEye() throws EncryptedDocumentException, InterruptedException, IOException {
		extentionconfirmPassword_eye.click();

	}

	public void clickEnabled_recording() throws EncryptedDocumentException, InterruptedException, IOException {
		enabled_recording.click();

	}
	
	public void clickEnabled_voicemail() throws EncryptedDocumentException, InterruptedException, IOException {
		enabled_voicemail.click();

	}

	public void ClickAutoAnswer() throws EncryptedDocumentException, InterruptedException, IOException {
		checkBox_autoAnswer_users.click();
	}

	public void ClickDisplayEvalution() throws EncryptedDocumentException, InterruptedException, IOException {
		checkBox_users.click();
	}

	public void clickEnaled_voicemail() throws EncryptedDocumentException, InterruptedException, IOException {
		enabled_voicemail.click();
	}

	public void mobileNumber(String mobile) throws InvalidFormatException, IOException {
		boolean textbox = phone_number.isEnabled();
		phone_number.sendKeys(mobile);
		phone_number.sendKeys(Keys.TAB);

	}

	public void extention_dropdown(String extentiondropdown) throws EncryptedDocumentException, IOException {
		boolean dropdown = extention.isEnabled();
		extention.click();
		Select s = new Select(extention);
		s.selectByVisibleText(extentiondropdown);
		extention.click();

//		Assert.assertTrue(dropdown);

	}

	public void clidNumber(String clid) throws EncryptedDocumentException, IOException {
		boolean textbox = CLID_Number.isEnabled();
		CLID_Number.sendKeys(clid);

	}

	public void enterEmail(String email) throws EncryptedDocumentException, IOException {
		boolean textbox = EmailId_users.isEnabled();
		EmailId_users.sendKeys(email);
//		
	}

	public void setFirstName(String firstname) throws EncryptedDocumentException, IOException {
		FirstName.sendKeys(firstname);

	}

	public void setLastName(String lastname) throws EncryptedDocumentException, IOException {
		LastName.sendKeys(lastname);
	}

	public void selectGroupTypeDropdown(String grouptype)
			throws InterruptedException, EncryptedDocumentException, IOException {
		boolean dropdown = Group_Type.isEnabled();
		if (dropdown) {
			dropdown = true;
			Group_Type.click();
			Select usage = new Select(Group_Type);
			usage.selectByVisibleText(grouptype);
			Group_Type.click();

//
		}
	}

	public void selectGroupdropdown(String group) throws InterruptedException, EncryptedDocumentException, IOException {
		boolean dropdown = Group_Dropdown.isEnabled();
		String data = Group_Type.getAttribute("value");
		Select usage = new Select(Group_Dropdown);
		Group_Dropdown.click();
		usage.selectByVisibleText(group + "@ho.qacloud.intalk.io");

	}

	public void selectGroupdropdownWithSupervisor()
			throws InterruptedException, EncryptedDocumentException, IOException {
		boolean dropdown = Group_Dropdown.isEnabled();
		Select usage = new Select(Group_Dropdown);
		Group_Dropdown.click();
		usage.selectByVisibleText("SupervisorrTL@ho.qacloud.intalk.io");

	}

	public void SuperviserTLDropdown(String supervisortl) throws EncryptedDocumentException, IOException {
		boolean dropdown = supervisorTL.isEnabled();
		if (dropdown) {
			dropdown = true;
			Select usage = new Select(supervisorTL);
			usage.selectByVisibleText(supervisortl);
		}
	}

	public void UserTypeAPIClickable() throws EncryptedDocumentException, InterruptedException, IOException {
		// CommonUtils.ButtonIsClickable(UserTypeAPI_checkbox, "UserTypeAPI");
		UserTypeAPI_checkbox.click();
	}

	public void UserTypeClickable() throws EncryptedDocumentException, InterruptedException, IOException {
		// CommonUtils.ButtonIsClickable(UserType_checkbox, "UserType");
		UserType_checkbox.click();
	}

	public void ManageQueueAgentName(String managequeuename)
			throws EncryptedDocumentException, IOException, InterruptedException {

		String str = managequeuename;
		String[] res = str.split("[,]", 0);
		for (int i = 0; i < res.length; i++) {
			String myStr = res[i];
			System.out.println("==============" + i + myStr);
			try {
				if (i == 0) {
					manageQueuename1.click();
					SearchmanageQueuename.sendKeys(myStr);
					SearchmanageQueuename.sendKeys(Keys.ENTER);
				} else if (i == 1) {
					manageTierlevel2.click();
					searchtierlevel.sendKeys(myStr);
					searchtierlevel.sendKeys(Keys.ENTER);
				} else if (i == 2) {
					manageTierPosition3.click();
					searchtierpossition.sendKeys(myStr);
					searchtierpossition.sendKeys(Keys.ENTER);
					manageAddButton.click();
				} else if (i == 3 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultQueue0.click();
				} else if (i == 4 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultOutbound0.click();
				} else if (i == 5) {
					manageQueuename4.click();
					SearchmanageQueuename.sendKeys(myStr);
					SearchmanageQueuename.sendKeys(Keys.ENTER);
				} else if (i == 6) {
					manageTierlevel5.click();
					searchtierlevel.sendKeys(myStr);
					searchtierlevel.sendKeys(Keys.ENTER);
				} else if (i == 7) {
					manageTierPosition6.click();
					searchtierpossition.sendKeys(myStr);
					searchtierpossition.sendKeys(Keys.ENTER);
					manageAddButton.click();
				} else if (i == 8 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultQueue1.click();
				} else if (i == 9 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultOutbound1.click();
				} else if (i == 10) {
					manageQueuename7.click();
					SearchmanageQueuename.sendKeys(myStr);
					SearchmanageQueuename.sendKeys(Keys.ENTER);
				} else if (i == 11) {
					manageTierlevel8.click();
					searchtierlevel.sendKeys(myStr);
					searchtierlevel.sendKeys(Keys.ENTER);
				} else if (i == 12) {
					manageTierPosition9.click();
					searchtierpossition.sendKeys(myStr);
					searchtierpossition.sendKeys(Keys.ENTER);
					manageAddButton.click();
				} else if (i == 13 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultQueue2.click();
				} else if (i == 14 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultOutbound2.click();
				} else if (i == 15) {
					manageQueuename10.click();
					SearchmanageQueuename.sendKeys(myStr);
					SearchmanageQueuename.sendKeys(Keys.ENTER);
				} else if (i == 16) {
					manageTierlevel11.click();
					searchtierlevel.sendKeys(myStr);
					searchtierlevel.sendKeys(Keys.ENTER);
				} else if (i == 17) {
					manageTierPosition12.click();
					searchtierpossition.sendKeys(myStr);
					searchtierpossition.sendKeys(Keys.ENTER);
					manageAddButton.click();
				} else if (i == 18 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultQueue3.click();
				} else if (i == 19 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultOutbound3.click();
				} else if (i == 20) {
					manageQueuename13.click();
					SearchmanageQueuename.sendKeys(myStr);
					SearchmanageQueuename.sendKeys(Keys.ENTER);
				} else if (i == 21) {
					manageTierlevel14.click();
					searchtierlevel.sendKeys(myStr);
					searchtierlevel.sendKeys(Keys.ENTER);
				} else if (i == 22) {
					manageTierPosition15.click();
					searchtierpossition.sendKeys(myStr);
					searchtierpossition.sendKeys(Keys.ENTER);
					manageAddButton.click();
				} else if (i == 23 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultQueue3.click();
				} else if (i == 24 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultOutbound3.click();
				} else if (i == 25) {
					manageQueuename16.click();
					SearchmanageQueuename.sendKeys(myStr);
					SearchmanageQueuename.sendKeys(Keys.ENTER);
				} else if (i == 26) {
					manageTierlevel17.click();
					searchtierlevel.sendKeys(myStr);
					searchtierlevel.sendKeys(Keys.ENTER);
				} else if (i == 27) {
					manageTierPosition18.click();
					searchtierpossition.sendKeys(myStr);
					searchtierpossition.sendKeys(Keys.ENTER);
					manageAddButton.click();
				} else if (i == 28 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultQueue4.click();
				} else if (i == 29 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultOutbound4.click();
				} else if (i == 30) {
					manageQueuename19.click();
					SearchmanageQueuename.sendKeys(myStr);
					SearchmanageQueuename.sendKeys(Keys.ENTER);
				} else if (i == 31) {
					manageTierlevel20.click();
					searchtierlevel.sendKeys(myStr);
					searchtierlevel.sendKeys(Keys.ENTER);
				} else if (i == 32) {
					manageTierPosition21.click();
					searchtierpossition.sendKeys(myStr);
					searchtierpossition.sendKeys(Keys.ENTER);
					manageAddButton.click();
				} else if (i == 33 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultQueue5.click();
				} else if (i == 34 && myStr.equalsIgnoreCase("Yes")) {
					manageDefaultOutbound5.click();
				}

			} catch (Exception e) {
				System.out.println("Error performing action: " + e.getMessage());
			}
		}
	}

	public void ManageTierlevel(String tierlevel) throws EncryptedDocumentException, IOException, InterruptedException {

//				if (indexOfTwo >= 1) {
//					manageTierlevel.click();
//					searchtierlevel.sendKeys(myStr);
//					searchtierlevel.sendKeys(Keys.ENTER);
//					
//				} else {
//					manageTierlevel.click();
//					searchtierlevel.sendKeys(myStr);
//					searchtierlevel.sendKeys(Keys.ENTER);

	}

	public void ManageAddButton() throws EncryptedDocumentException, InterruptedException, IOException {

		// CommonUtils.ButtonIsClickable(manageAddButton, "Add new queue click");
		manageAddButton.click();

	}

	public void GreetingFileDropdown(String greeting) throws EncryptedDocumentException, IOException {
		boolean dropdown = GreetingFile.isEnabled();
		GreetingFile.click();
		Select usage = new Select(GreetingFile);
		System.out.println(greeting + "greeting+++++");
		usage.selectByVisibleText(greeting);
		GreetingFile.click();

	}

	public void EnabledDropdown(String enableDrp) throws EncryptedDocumentException, IOException, InterruptedException {
		Thread.sleep(1000);
		boolean dropdown = enabledrp1.isEnabled();
		enabledrp1.click();
		System.out.println(enableDrp + "=====");
		Select usage = new Select(enabledrp1);
		usage.selectByVisibleText(enableDrp);

	}

//=====advance click======
	public void AdvanceClick() {
		boolean click = AdvanceClick.isEnabled();
		if (click) {
			click = true;
			AdvanceClick.click();
			test.log(LogStatus.PASS, "User Module :-Advance Click successfully clickable");
		} else {
			click = false;
			test.log(LogStatus.FAIL, "User Module :-Advance Click not clickable");
		}

		// Assert.assertTrue(click);
	}

//====User App====
	public void userAppclickble() {
		boolean click = UserApp_users.isEnabled();
		if (click) {
			click = true;
			chat.click();
			test.log(LogStatus.PASS, "User Module :-User App Click successfully clickable");
		} else {
			click = false;
			test.log(LogStatus.FAIL, "User Module :-User App Click not clickable");
		}
		Assert.assertTrue(click);
		call.click();
	}

//=====save button====
	public void savebuttonclickble() {
		boolean click = Users_Save.isEnabled();
		if (click) {
			click = true;
			Users_Save.click();

		}
	}

	public void ManageCampaignName(String campaignname)
			throws EncryptedDocumentException, IOException, InterruptedException {
		System.out.println(campaignname);
		boolean dropdown = campaignName.isEnabled();
		if (dropdown) {
			dropdown = true;
			UserType_checkbox.sendKeys(Keys.TAB);
			String str = campaignname;
			String[] res = str.split("[,]", 0);
			for (String myStr : res) {
				System.out.println(res);
				campaignName.sendKeys(myStr);
				campaignName.sendKeys(Keys.ARROW_DOWN);
				campaignName.sendKeys(Keys.ENTER);
			}
		}
	}

	public void specifyGateway(String gateway) throws EncryptedDocumentException, IOException {
		boolean textbox = specifieGateway.isEnabled();
		specifieGateway.sendKeys(gateway);
		specifieGateway.sendKeys(Keys.TAB);

	}

	public void DropdownLanguageUsers() throws EncryptedDocumentException, InterruptedException, IOException {

		boolean dropdown = Language_users.isEnabled();
		Select usage = new Select(Language_users);
		// usage.selectByValue();
		usage.selectByValue("en-us");

	}

	public void enterSpecifyCallerIdNumber(String specifycallerid) throws EncryptedDocumentException, IOException {
		boolean textbox = specifyCallerIdNumber.isEnabled();
		specifyCallerIdNumber.sendKeys(specifycallerid);
		specifyCallerIdNumber.sendKeys(Keys.TAB);

	}

	public void Filter() {
		filterclick.click();
	}

	public String FilterUser(String user1) {
		filteruser.sendKeys(user1);
		return user1;

	}

	public void FilterSumbit() {
		sumbitfilter.click();
	}

	public void ChangePassword() {
		changepassword.click();
	}

	public void PPassword(String password) {
		ppassword.sendKeys(password);
		Eyeppassword.click();
	}

	public void ConfirmPassword(String confirmpassword) {
		confirmpasswordd.sendKeys(confirmpassword);
		Eyeconfirmppassword.click();
	}

	public void Update() {
		update.click();
	}

	public void EnabledCalling() {
		EnableCalling.click();
	}

	public void FilterStatus(String filterstatus) {
		boolean dropdown = statusfilter.isEnabled();
		if (dropdown) {
			dropdown = true;
			statusfilter.click();
			Select s = new Select(statusfilter);
			s.selectByVisibleText(filterstatus);

		}
	}

	public void FilterGroup(String filtergroup) {
		boolean dropdown = groupfilter.isEnabled();
		if (dropdown) {
			dropdown = true;
			groupfilter.click();
			Select s = new Select(groupfilter);
			System.out.println(filtergroup + "iiii");
			s.selectByVisibleText(filtergroup);
			groupfilter.click();
		}
	}

	public void Delete() throws InterruptedException {
		deleteclick.click();
		Thread.sleep(1000);
		delete.click();
		ChromeOptions opt = new ChromeOptions();
		driver.switchTo().alert().accept();
	}

	public void EditUsageType(String usagetype) {
		// usage_type.click();
		Select s = new Select(usage_type);
		s.selectByVisibleText(usagetype);
		// usage_type.sendKeys(Keys.ENTER);
	}

	public void EditEndPoint(String endpoint1) {

		Select s = new Select(Editendpoint);
		s.selectByVisibleText(endpoint1);
	}

	public void MobileNumber(String mobile) {
		phone_number1.sendKeys(mobile);
	}

	public void clickCreateNewExtention1() throws EncryptedDocumentException, InterruptedException, IOException {

		// CommonUtils.ButtonIsClickable(createnewExtention, "Create New Extention");
		createnewExtention1.click();

	}

	public void clickUseExistingExtention1() throws EncryptedDocumentException, InterruptedException, IOException {

		// CommonUtils.ButtonIsClickable(useExitingExtention, "Use Existing Extention");
		useExitingExtention1.click();
	}

	public void ExtentionDropdown1(String extentionn1) {
		Select s = new Select(extention1);
		s.selectByVisibleText(extentionn1);
	}

	public void clickEnabled_recording1() throws EncryptedDocumentException, InterruptedException, IOException {
		enabled_recording1.click();

	}

	public void ClickAutoAnswer1() throws EncryptedDocumentException, InterruptedException, IOException {
		checkBox_autoAnswer_users1.click();
	}

	public void ClickDisplayEvalution1() throws EncryptedDocumentException, InterruptedException, IOException {
		checkBox_users1.click();
	}

	public void clickEnaled_voicemail1() throws EncryptedDocumentException, InterruptedException, IOException {
		enabled_voicemail1.click();
	}

	public void NewExtentionNumber1(String extention1number) {
		ExtentionNumber1.sendKeys(extention1number);
	}

	public void ExtentionPassword1(String expassword) {
		extentionPassword1.sendKeys(expassword);
	}

	public void ConfirmExtentionPassword1(String comexpassword) {
		extentionconfirmPassword1.sendKeys(comexpassword);

	}

	public void MobileNumber1(String mobilenumberr) {
		System.out.println(mobilenumberr + "hhhhhhhhhhhhhhhhh");
		phone_number1.sendKeys(mobilenumberr);

	}

	public void ClidNumber1(String clidnumber1) {
		System.out.println(clidnumber1);
		CLID_Number1.sendKeys(clidnumber1);
	}

	public void Email1(String emailid) {
		EmailId_users1.sendKeys(emailid);
	}

	public void FirstName1(String first) {
		FirstName1.sendKeys(first);
	}

	public void LastName1(String lastname) {
		LastName1.sendKeys(lastname);
	}

	public void GroupDropdown1(String group1) {
		Select s = new Select(Group_Dropdown1);
		s.selectByVisibleText(group1 + "@ho.qacloud.intalk.io");
	}

	public void SupervisorTL1(String superTL1) {
		Select s = new Select(supervisorTL1);
		s.selectByVisibleText(superTL1);
	}

	public void UserTypeAPIClickable1() throws EncryptedDocumentException, InterruptedException, IOException {
		// CommonUtils.ButtonIsClickable(UserTypeAPI_checkbox, "UserTypeAPI");
		UserTypeAPI_checkbox1.click();
	}

	public void UserTypeClickable1() throws EncryptedDocumentException, InterruptedException, IOException {
		// CommonUtils.ButtonIsClickable(UserType_checkbox, "UserType");
		UserType_checkbox1.click();
	}

	public void GreetingFile1(String greetingfile1)
			throws EncryptedDocumentException, InterruptedException, IOException {
		// CommonUtils.ButtonIsClickable(UserType_checkbox, "UserType");
		Select s = new Select(GreetingFile1);
		System.out.println(greetingfile1 + "GDGGGD");
		s.selectByVisibleText(greetingfile1);
	}

	public void StatusDropdown1(String status1) {
		Select s = new Select(enabledrp);
		s.selectByVisibleText(status1);
	}

	public void AdvanceClick1() {
		AdvanceClick1.click();
	}

	public void CallClick() {
		call1.click();
	}

	public void ChatClick() {
		chat1.click();
	}

	public void AutoAnswer() {
		checkBox_autoAnswer_users1.click();
	}

	public void EnabledCallingClick() {
		emabledcalling1.click();
	}

	public void ManageCampaignName1(String campaignname1)
			throws EncryptedDocumentException, IOException, InterruptedException {
		System.out.println(campaignname1);
		boolean dropdown = campaignName1.isEnabled();
		if (dropdown) {
			dropdown = true;
			UserType_checkbox.sendKeys(Keys.TAB);
			String str = campaignname1;
			String[] res = str.split("[,]", 0);
			for (String myStr : res) {
				System.out.println(res);
				campaignName1.sendKeys(myStr);
				campaignName1.sendKeys(Keys.ARROW_DOWN);
				campaignName1.sendKeys(Keys.ENTER);

			}
		}
	}

	public void gatewaydropdown1(String gateway1) {
		Select s = new Select(specifieGateway1);
		s.selectByVisibleText(gateway1);
	}

	public void SpecificCallerIdNumber(String calledid) {
		specifyCallerId.sendKeys(calledid);
	}

	public void ErrorMessege() {
		try {
			if (required_error.isDisplayed() == true) {
				test.log(LogStatus.FAIL, "User Required Error");
			} else {
				if (username_already.isDisplayed() == true) {
					test.log(LogStatus.FAIL, "User Already Exist Error");
				} else if (username_validation.isDisplayed() == true) {
					test.log(LogStatus.FAIL, "User Validation Messege");
				} else {
					test.log(LogStatus.PASS, "User Name");
				}
			}
		} catch (Exception e) {
			System.out.println("");
		}

		try {
			if (password_err.isDisplayed() == true) {
				test.log(LogStatus.FAIL, "Password  Error");
			} else {
				if (password_req.isDisplayed() == true) {
					test.log(LogStatus.FAIL, "Password Requird  Error");
				} else {
					test.log(LogStatus.PASS, "User Password");
				}
			}
		} catch (Exception e) {
			System.out.println("");
		}
		try {
			if (Confirm_password_err.isDisplayed() == true) {
				test.log(LogStatus.FAIL, "Confirm Password  Error");
			} else {
				test.log(LogStatus.PASS, "User Confirm Password");
			}

			if (phone_number_already.isDisplayed() == true) {
				test.log(LogStatus.FAIL, "Phone Number  already Exist");
			} else {
				if (phone_number_requird.isDisplayed() == true) {
					test.log(LogStatus.FAIL, "Phone Number Requird");
				} else {
					test.log(LogStatus.PASS, "Phone Number");
				}
			}
		} catch (Exception e) {
			System.out.println("");
		}
		try {
			if (CLID_already.isDisplayed() == true) {
				test.log(LogStatus.FAIL, " CLID/DID already Exist");
			} else {
				if (CLID_Number_err.isDisplayed() == true) {
					test.log(LogStatus.FAIL, "CLID/DID Validation Error");
				} else {
					test.log(LogStatus.PASS, "CLID/DID Number");
				}
			}
		} catch (Exception e) {
			System.out.println("");
		}

		try {
			if (EmailId_users_already.isDisplayed() == true) {
				test.log(LogStatus.FAIL, " EmailId already Exist");
			} else {
				if (EmailId_users_err.isDisplayed() == true) {
					test.log(LogStatus.FAIL, "EmailId Validation Error");
				} else {
					test.log(LogStatus.PASS, "User EmailId ");
				}
			}
		} catch (Exception e) {
			System.out.println("");
		}
		try {
			if (extentionPassword_err.isDisplayed() == true) {
				test.log(LogStatus.FAIL, "Invalid Extention Password =>  Password must Numeric ,"
						+ "Char And symbol(@#$%) with atleast one capital letter and length of password should be 4-25 character. ");
			} else {
				test.log(LogStatus.PASS, "Extention Password");
			}
		} catch (Exception e) {
			System.out.println("");
		}
		try {
			if (exconfirmPassword_err.isDisplayed() == true) {
				test.log(LogStatus.FAIL,
						"Extention Confirm Password Invalide => Char And symbol(@#$%) with atleast one capital letter and "
								+ "length of password should be 4-25 character.");
			} else {
				test.log(LogStatus.PASS, " Extention Confirm Password");
			}
		} catch (Exception e) {
			System.out.println("");
		}
		try {
			if (specifyCallerIdNumber_err.isDisplayed() == true) {
				test.log(LogStatus.FAIL, "specify CallerId Number Error");

			} else {
				test.log(LogStatus.PASS, "specify CallerId Number");
			}
		} catch (Exception e) {
			System.out.println("");
		}

		try {
			if (extention_error.isDisplayed() == true) {
				test.log(LogStatus.FAIL, "Extention Number Must be 4 Digit Error");
			} else if (extention_already.isDisplayed() == true) {
				test.log(LogStatus.FAIL, "Extention Number Already Exist");

			} else {
				test.log(LogStatus.PASS, "Extention Number");
			}
		} catch (Exception e) {
			System.out.println("");
		}

		try {
			if (specifieGateway1_error.isDisplayed() == true) {
				test.log(LogStatus.FAIL,
						"Either both Gateway & Callerid must have value or both Gateway & Callerid must be blank!");
			} else {
				test.log(LogStatus.PASS, "Extention Number");
			}
		} catch (Exception e) {
			System.out.println("");
		}
	}
}
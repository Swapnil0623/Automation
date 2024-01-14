package com.intalk.pom;

import javax.crypto.SealedObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.intalk.baseclasses.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

public class EmergencyListPom extends BaseClass {

	WebDriver driver;
	SoftAssert softAssert;

	public EmergencyListPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='apps-icon ng-binding']")
	public WebElement clickApps;
	@FindBy(xpath = "//*[@id=\"mySidenav\"]/div[2]/ul/li[21]/a")
	public WebElement clickEmergencyListModule;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/form/div[1]/div/div/div[2]/span/a")
	public WebElement AddemergencyListclick;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div/div[2]/input")
	public WebElement emergencylistname;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div/div[2]/span[4]")
	public WebElement emergencylistRequerd;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div/div[2]/span[3]")
	public WebElement emergencylistError;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div/div[2]/span[5]")
	public WebElement emergencylistAlready;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[2]/div/div[2]/input")
	public WebElement emergencylistNumber;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[2]/div/div[2]/span[1]")
	public WebElement emergencylistNumberError;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[2]/div/div[2]/span[2]")
	public WebElement emergencylistNumberAlready;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[3]/div/div[2]/select")
	public WebElement emergencyStatus;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[4]/div/div[2]/span/span[1]/span/ul/li/input")
	public WebElement emergencyDropdown;
	@FindBy(xpath = "//*[@id=\"insert\"]")
	public WebElement save;

	public void AppsClick() throws InterruptedException {
		clickApps.click();
	}

	public void ClickModuleCallBlock() {
		clickEmergencyListModule.sendKeys(Keys.ENTER);
	}

	public void ClickAdd() {
		AddemergencyListclick.sendKeys(Keys.ENTER);
	}

	public void EmergencyListName(String name) {
		emergencylistname.sendKeys(name);
		boolean validation1 = emergencylistRequerd.isDisplayed();
		boolean validation2 = emergencylistError.isDisplayed();
		boolean validation3 = emergencylistAlready.isDisplayed();
		if (validation1) {
			validation1 = true;
			test.log(LogStatus.FAIL, "Invalid Emergency Name =>  Emergency List Name is required." + "=>" + name);
		} else if (validation2) {
			validation2 = true;
			test.log(LogStatus.FAIL, "Invalid Emergency Name  =>  Maximum 32 Characters Required   " + "=>" + name);
		} else if (validation3) {
			validation3 = true;
			test.log(LogStatus.FAIL, "Invalid Emergency Name  =>  This number is already in block list." + "=>" + name);

		} else {
			test.log(LogStatus.PASS, "valid Emergency Name => Emergency List Name is already exist" + "=>" + name);
		}
	}

	public void EmergencyNumber(String number) {
		emergencylistNumber.sendKeys(number);
		boolean validation1 = emergencylistNumberError.isDisplayed();
		boolean validation2 = emergencylistNumberAlready.isDisplayed();
		if (validation1) {
			validation1 = true;
			test.log(LogStatus.FAIL, "Invalid Emergency Number =>  Emergency Number Error." + "=>" + number);
		} else if (validation2) {
			validation2 = true;
			test.log(LogStatus.FAIL, "Invalid Emergency Number  => Already in Database " + "=>" + number);
		}
	}

	public void EmergencyStatus(String statuss) {
		System.out.println(statuss);
		boolean dropdown = emergencyStatus.isEnabled();
		if (dropdown) {
			dropdown = true;
			Select s = new Select(emergencyStatus);
			s.selectByVisibleText(statuss);
		}
	}
//	public void DropdownCampaign(String campaign) {
//		boolean dropdown=emergencyDropdown.isEnabled();
//		if(dropdown) {
//			dropdown=true;
//			emergencyStatus.sendKeys(Keys.TAB);
//			emergencyDropdown.sendKeys(campaign);
//			emergencyDropdown.sendKeys(Keys.ARROW_DOWN);
//			emergencyDropdown.sendKeys(Keys.ENTER);
//			
//		}

	public void DropdownCampaign(String campaign) {
		emergencyStatus.sendKeys(Keys.TAB);
		String str = campaign;
		String[] res = str.split("[,]", 0);
		for (String myStr : res) {
			System.out.println(res);
			emergencyDropdown.sendKeys(myStr);
			emergencyDropdown.sendKeys(Keys.ARROW_DOWN);
			emergencyDropdown.sendKeys(Keys.ENTER);
		}

	}

	public void SaveButton() {
		save.click();
	}
}

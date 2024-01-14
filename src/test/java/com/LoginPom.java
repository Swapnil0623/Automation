package com.intalk.pom;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.relevantcodes.extentreports.LogStatus;

public class IndexPagePom extends BaseClass {

	static WebDriver driver;
	@FindBy(xpath = "//input[@id='username']")
	public WebElement username;
	@FindBy(xpath = "//input[@id='password']")
	public WebElement password;
	@FindBy(xpath = "//input[@id='loginbtn']")
	public WebElement BtnClick;
	@FindBy(xpath = "//select[@id='domainId']")
	private WebElement domainid;
	@FindBy(xpath = "//select[@id='langDropDown']")
	private WebElement Language;
	@FindBy(xpath = "(//div[@class='detail-box col-md-12 ng-scope'])[1]")
	public static WebElement homepage;
	@FindBy(xpath = "//*[@id=\"dialpad-icon\"]")
	public static WebElement cg;
	@FindBy(xpath = "//select[@id='endpoint_type1']")
	public WebElement endpoint;
	@FindBy(xpath = "//input[@name='mobile_extension1']")
	public WebElement mobilenumber;
	@FindBy(xpath = "//select[@id='zoiper_extension1']")
	public WebElement extentionnumber;
	@FindBy(xpath = "//button[@id='Extension-Btn']")
	public WebElement extentionnumbersubmit;

	public IndexPagePom(WebDriver driver) {
		PageFactory.initElements(driver, this);
		SoftAssert soft = new SoftAssert();
	}

	public String Username(String userName) throws EncryptedDocumentException, IOException {
		System.out.println(userName);
		username.sendKeys(userName);
		username.sendKeys(Keys.TAB);
		return userName;

	}

	public String Password(String passWord) throws EncryptedDocumentException, IOException {
		password.sendKeys(passWord);
		password.sendKeys(Keys.TAB);
		return passWord;

	}

	public void Dropdowndomain() throws EncryptedDocumentException, IOException {
		Select domain = new Select(domainid);
		if (domain.getOptions() != null) {
			domain.selectByVisibleText(CommonUtils.getURL_Domain(1, 1));

		}
	}

	public void DropdpwnLanguage() {
		Select dropLanguage = new Select(Language);
		if (dropLanguage.getOptions() != null) {
			dropLanguage.selectByValue("en");

		}
	}

	public void dialpad() throws EncryptedDocumentException, IOException {
		cg.isDisplayed();

	}

	public void LoginBtn() throws EncryptedDocumentException, IOException, InterruptedException {
		BtnClick.click();

	}
	
	public void SelectEndpoint(String engpoint) {
		Select s = new Select(endpoint);
		s.selectByVisibleText(engpoint);
	}

	public void MobileNumberExtention(String mobile) {
		mobilenumber.sendKeys(mobile);
	}

	public void EndpointExtention(String extention) {
		Select s = new Select(extentionnumber);
		s.selectByVisibleText(extention);
	}
	
	public void SubmitLoginsupervisor() {
		extentionnumbersubmit.click();
	}

}

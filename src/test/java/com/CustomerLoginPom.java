package com.intalk.pom;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;
import com.relevantcodes.extentreports.LogStatus;

public class AgentLoginPom extends BaseClass {

	@FindBy(xpath = "//*[@id=\"username\"]")
	public WebElement agentusername;
	@FindBy(xpath = "//*[@id=\"password\"]")
	public WebElement agentpassword;
	@FindBy(xpath = "//*[@id=\"domainId\"]")
	public WebElement agentdomain;
	@FindBy(xpath = "//*[@id=\"langDropDown\"]")
	public WebElement agentLanguage;
	@FindBy(xpath = "//*[@id=\"loginbtn\"]")
	public WebElement agentBtn;
	@FindBy(xpath = "//div[@class='ms-parent ms-choice2']")
	public WebElement selectQueue;
	@FindBy(xpath = "//input[@data-name='selectAllcampaignName[]']")
	public WebElement selectQueueInduvial;
	@FindBy(xpath = "//*[@id=\"campaignName_dropdown\"]/div/div/ul/li[16]")
	public WebElement selectQueueInduvial1;
	@FindBy(xpath = "//*[@id=\"campaignName_dropdown\"]/div/div/div/input")
	public WebElement SearchselectQueue;
	@FindBy(xpath = "//*[@id=\"campaignName_dropdown\"]/div/div/ul/li[2]/label/span[2]")
	public WebElement selectQueueclick;
	@FindBy(xpath = "//*[@id=\"Campaign-Btn\"]")
	public WebElement submit;
	@FindBy(xpath = "//select[@id='endpoint_type1']")
	public WebElement endpoint;
	@FindBy(xpath = "//input[@name='mobile_extension1']")
	public WebElement mobilenumber;
	@FindBy(xpath = "//select[@id='zoiper_extension1']")
	public WebElement extentionnumber;

	public AgentLoginPom(WebDriver driver) {
		PageFactory.initElements(driver, this);
		SoftAssert soft = new SoftAssert();
	}

	public String Username(String userName) throws EncryptedDocumentException, IOException {
		System.out.println(userName);
		agentusername.sendKeys(userName);
		agentusername.sendKeys(Keys.TAB);
		return userName;

	}

	public String Password(String passWord) throws EncryptedDocumentException, IOException {
		agentpassword.sendKeys(passWord);
		agentpassword.sendKeys(Keys.TAB);
		return passWord;

	}

	public void Dropdowndomain() throws EncryptedDocumentException, IOException {
		Select domain = new Select(agentdomain);
		if (domain.getOptions() != null) {
			domain.selectByVisibleText(CommonUtils.getURL_Domain(1, 1));
		}
	}

	public void DropdpwnLanguage() {
		Select dropLanguage = new Select(agentLanguage);
		if (dropLanguage.getOptions() != null) {
			dropLanguage.selectByValue("en");

		}
	}

	public void LoginBtn() throws EncryptedDocumentException, IOException, InterruptedException {
		agentBtn.click();
	}

	public void SelectQueue(String queueName) throws InterruptedException {
		boolean dropdown = selectQueue.isEnabled();
		if (dropdown) {
			dropdown = true;
			String str = queueName;
			System.out.println(str);
			String[] res = str.split("[,]", 0);
			selectQueue.click();
			selectQueueclick.click();
			for (String myStr : res) {
				int indexOfTwo = ArrayUtils.indexOf(res, myStr);
				System.out.println(indexOfTwo + "yyyyyyy");
				if (indexOfTwo >= 1) {
					selectQueue.click();
					SearchselectQueue.sendKeys(myStr);
					Thread.sleep(1000);
					selectQueueclick.click();
					selectQueue.click();
				} else {
					Thread.sleep(1000);
					SearchselectQueue.sendKeys(myStr);
					Thread.sleep(1000);
					selectQueueInduvial.click();
					selectQueue.click();
				}

			}
			test.log(LogStatus.PASS, "CallList Module:-Campaign =>" + queueName);
		} else {
			test.log(LogStatus.FAIL, "CallList Module:-Please select campaign ");

		}
	}

	public void SearchQueue(String queuename) {
		SearchselectQueue.sendKeys(queuename);
	}

	public void SelectQueueClick() {
		selectQueueclick.click();
	}

	public void SubmitButton() {
		submit.click();
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

}
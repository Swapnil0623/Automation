package com.intalk.pom;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.intalk.baseclasses.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

public class AgentQueueMultiSelectionPom extends BaseClass {

	WebDriver driver;
	SoftAssert softAssert;

	public AgentQueueMultiSelectionPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='apps-icon ng-binding']")
	public WebElement clickApps;
	@FindBy(xpath = "//*[@id=\"mySidenav\"]/div[2]/ul/li[22]/a")
	public WebElement clickAgentQueueMultiSelectionModule;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/div[2]/div/div[1]/span/span[1]/span/ul/li/input")
	public WebElement AgentQueueMultiSelectionclick;
	@FindBy(xpath = "//*[@id=\"select2-tierval-container\"]")
	public WebElement manageTierlevel;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchtierlevel;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/div[2]/div/div[3]/span/span[1]/span/span[2]")
	public WebElement manageTierPosition;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchTierPosition;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/div[4]/div/span/input")
	public WebElement save;

	public void AppsClick() throws InterruptedException {
		clickApps.click();
	}

	public void ClickModuleCallBlock() {
		clickAgentQueueMultiSelectionModule.sendKeys(Keys.ENTER);
	}

	public void AgentSelection(String agent) {
		boolean dropdown = AgentQueueMultiSelectionclick.isEnabled();
		if (dropdown) {
			dropdown = true;
			AgentQueueMultiSelectionclick.click();
			String str = agent;
			String[] res = str.split("[,]", 0);
			for (String myStr : res) {
				System.out.println(res);
				AgentQueueMultiSelectionclick.sendKeys(myStr);
				AgentQueueMultiSelectionclick.sendKeys(Keys.ARROW_DOWN);
				AgentQueueMultiSelectionclick.sendKeys(Keys.ENTER);
			}
		}
	}

	public void ManageTierlevel(String tierlevel) throws EncryptedDocumentException, IOException, InterruptedException {
		boolean dropdown = manageTierlevel.isEnabled();
		if (dropdown) {
			dropdown = true;
			manageTierlevel.click();
			searchtierlevel.sendKeys(tierlevel);
			searchtierlevel.sendKeys(Keys.ENTER);
			test.log(LogStatus.PASS, "User Module:-Manage Queue Tier Level dropdown is selectable" + "=>" + tierlevel);
		} else {
			dropdown = false;
			test.log(LogStatus.FAIL, "User Module:-Manage Queue Tier Level dropdown is not working" + "=>" + tierlevel);
		}
//	Assert.assertTrue(dropdown);
	}

	public void ManageTierPosistion(String tierposition)
			throws EncryptedDocumentException, IOException, InterruptedException {
		boolean dropdown = manageTierPosition.isEnabled();
		if (dropdown) {
			dropdown = true;
			manageTierPosition.click();
			searchTierPosition.sendKeys(tierposition);
			searchTierPosition.sendKeys(Keys.ENTER);
			test.log(LogStatus.PASS,
					"User Module:-Manage Queue Tier Position dropdown is selectable" + "=>" + tierposition);
		} else {
			dropdown = false;
			test.log(LogStatus.FAIL,
					"User Module:-Manage Queue Tier Position dropdown is not working" + "=>" + tierposition);
		}

	}
	public void SaveButton() {
		save.click();
	}

}

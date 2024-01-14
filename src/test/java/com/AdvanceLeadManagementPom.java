package com.intalk.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvanceCallManagmentPom {

	public static WebDriver driver;

	////////
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[3]/input")
	public WebElement saveButton;
	//////////
	@FindBy(xpath = "//a[@class='apps-icon ng-binding']")
	public WebElement clickApps;
	@FindBy(xpath = "//*[@id=\"mySidenav\"]/div[2]/ul/li[15]/a")
	public WebElement clickAdvanceCallModule;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/form/div[1]/div/div/div[2]/span/button[1]")
	public WebElement AddclickAdvanceCall;
	// All Abandoned Call
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[1]/select")
	public WebElement callQueueMapping;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[1]/span[1]/span[1]/span")
	public WebElement Queuename;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[2]/span[1]/span[1]/span")
	public WebElement mapQueuename;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchQueuename;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement mapsearchQueuename;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[3]/select")
	public WebElement statusDrpdown;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[4]/select")
	public WebElement duplicatecheck;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[5]/input")
	public WebElement timeduration;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[6]/input")
	public WebElement checkClick;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[2]/span[1]/span[1]/span")
	public WebElement mappingcampaignname;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchmappingcampaignname;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[3]/input")
	public WebElement failedfeedback;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[1]/span[1]")
	public WebElement campaignname;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchcampaignname;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[3]/span/span[1]/span")
	public WebElement listname;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchlistname;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[4]/span[1]/span[1]/span/ul/li/input")
	public WebElement dispositon;
	@FindBy(xpath = "//*[@id=\"select2-h1sc-container\"]")
	public WebElement selectcampaign;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchselectcampaign;
	@FindBy(xpath = "//*[@id=\"select2-h9ly-container\"]")
	public WebElement selectqueue;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchselectqueue;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[2]/select")
	public WebElement statusforfailed;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[4]/span[1]/span[1]/span/ul/li/input")
	public WebElement disposition;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[2]/span[1]/span[1]/span")
	public WebElement queueforfailedfeedback;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchqueueforfailedfeedback;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[6]/span[1]/span[1]/span")
	public WebElement migratecampaignname;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchmigratecampaignname;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[7]/span[1]/span[1]/span")
	public WebElement migratequeuename;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchmigratequeuename;
	@FindBy(xpath = "//*[@id=\"form_abandonedmapadd\"]/div/div/div[2]/div[2]/div[8]/span[1]/span[1]/span")
	public WebElement migrateList;
	@FindBy(xpath = "//*[@id=\"mainBody\"]/span/span/span[1]/input")
	public WebElement searchmigrateList;
	
	public void SaveButton() {
		saveButton.click();
	}

	public AdvanceCallManagmentPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void AppsClick() throws InterruptedException {
		clickApps.click();
	}

	public void AdvanceCallModule() {
		clickAdvanceCallModule.sendKeys(Keys.ENTER);
	}

	public void AddadvanceCall() {
		AddclickAdvanceCall.sendKeys(Keys.ENTER);
	}

	public void DropdownCallQueueMapping(String queueMapping) {
		boolean dropdown = callQueueMapping.isEnabled();
		if (dropdown) {
			dropdown = true;
			Select s = new Select(callQueueMapping);
			s.selectByVisibleText(queueMapping);
		}
	}

	public void DropdownCampaignName(String campaignnamei) throws InterruptedException {
		boolean dropdown = campaignname.isEnabled();
		if (dropdown) {
			dropdown = true;
			callQueueMapping.sendKeys(Keys.TAB);
			campaignname.click();
			searchcampaignname.sendKeys(campaignnamei);
			searchcampaignname.sendKeys(Keys.ENTER);

		}
	}

	public void DropdownQueueName(String queuename) {
		boolean dropdown = Queuename.isEnabled();
		if (dropdown) {
			dropdown = true;
			callQueueMapping.sendKeys(Keys.TAB);
			Queuename.sendKeys(Keys.ENTER);
			searchQueuename.sendKeys(queuename);
			searchQueuename.sendKeys(Keys.ENTER);

		}

	}

	public void DropdownQueueNameForFailedFeedback(String queuenamee) {
		boolean dropdown = queueforfailedfeedback.isEnabled();
		if (dropdown) {
			dropdown = true;
			queueforfailedfeedback.sendKeys(Keys.ENTER);
			searchqueueforfailedfeedback.sendKeys(queuenamee);
			searchqueueforfailedfeedback.sendKeys(Keys.ENTER);

		}

	}

	public void DropdownMappingCampaignName(String campaignnamee) {
		boolean dropdown = mappingcampaignname.isEnabled();
		if (dropdown) {
			dropdown = true;
			Queuename.sendKeys(Keys.TAB);
			mappingcampaignname.sendKeys(Keys.ENTER);
			searchmappingcampaignname.sendKeys(campaignnamee);
			searchmappingcampaignname.sendKeys(Keys.ENTER);
		}
	}

	public void DropdownMappingQueueName(String mapqueuename) {
		boolean dropdown = mapQueuename.isEnabled();
		if (dropdown) {
			dropdown = true;
			Queuename.sendKeys(Keys.TAB);
			mapQueuename.sendKeys(Keys.ENTER);
			mapsearchQueuename.sendKeys(mapqueuename);
			mapsearchQueuename.sendKeys(Keys.ENTER);
		}
	}

	public void DropdownStatusForFailedFeedback(String statuss) {
		boolean dropdown = statusforfailed.isEnabled();
		if (dropdown) {
			dropdown = true;
			Select s = new Select(statusforfailed);
			s.selectByVisibleText(statuss);
		}
	}

	public void DropdownStatus(String status) {
		boolean dropdown = statusDrpdown.isEnabled();
		if (dropdown) {
			dropdown = true;
			Select s = new Select(statusDrpdown);
			s.selectByVisibleText(status);
		}
	}

	public void DropdownDuplicateCheck(String duplicatecheckk) {
		boolean dropdown = duplicatecheck.isEnabled();
		if (dropdown) {
			dropdown = true;
			Select s = new Select(duplicatecheck);
			s.selectByVisibleText(duplicatecheckk);
		}
	}

	public void TimeDuration(String timedurationn) {
		timeduration.sendKeys(timedurationn);
	}

	public void ConnectBeforeAtempt() {
		checkClick.click();
	}

	public void ConsiderFailedFeedback() {
		failedfeedback.click();
	}

	public void DropdownListname(String listnamee) {
		boolean dropdown = listname.isEnabled();
		if (dropdown) {
			dropdown = true;
			queueforfailedfeedback.sendKeys(Keys.TAB);
			listname.sendKeys(Keys.ENTER);
			System.out.println(listnamee + "99999999999999999999");
			searchlistname.sendKeys(listnamee);
			searchlistname.sendKeys(Keys.ENTER);
		}
	}

	public void DropdownDisposition(String dips) {
		boolean dropdown = disposition.isEnabled();
		if (dropdown) {
			dropdown = true;
			disposition.click();
			String str = dips;
			String[] res = str.split("[,]", 0);
			for (String myStr : res) {
				System.out.println(res);
				disposition.sendKeys(myStr);
				disposition.sendKeys(Keys.ARROW_DOWN);
				disposition.sendKeys(Keys.ENTER);
			}
		}

	}
	
	public void MigrateCampaignName(String migratecampaign) {
		boolean dropdown = migratecampaignname.isEnabled();
		if (dropdown) {
			dropdown = true;
			disposition.sendKeys(Keys.TAB);
			migratecampaignname.sendKeys(Keys.ENTER);
			searchmigratecampaignname.sendKeys(migratecampaign);
			searchmigratecampaignname.sendKeys(Keys.ENTER);
		}
	
	}
	
	public void MigrateQueueName(String migratequeue) {
		boolean dropdown = migratequeuename.isEnabled();
		if (dropdown) {
			dropdown = true;
			migratecampaignname.sendKeys(Keys.TAB);
			migratequeuename.click();
			searchmigratequeuename.sendKeys(migratequeue);
			searchmigratequeuename.sendKeys(Keys.ENTER);
			
		}
	
	}
	public void MigrateListName(String listname) {
		boolean dropdown = migrateList.isEnabled();
		if (dropdown) {
			dropdown = true;
			migratequeuename.sendKeys(Keys.TAB);
			migrateList.click();
			searchmigrateList.sendKeys(listname);
			searchmigrateList.sendKeys(Keys.ENTER);
			
		}
	}
	
}

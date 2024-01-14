package com.intalk.pom;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.intalk.baseclasses.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

public class DNCListPom extends BaseClass {
	SoftAssert softAssert;

	public DNCListPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='apps-icon ng-binding']")
	public WebElement clickApps;
	@FindBy(xpath = "//*[@id=\"mySidenav\"]/div[2]/ul/li[19]/a")
	public WebElement clickDNCModule;
	@FindBy(xpath = "//button[@title='Add DNC List']")
	public WebElement AddDncclick;
	@FindBy(xpath = "//input[@ng-change='check_name(insertDncList.dnc_uuid)']")
	public WebElement dncname;
	@FindBy(xpath = "//span[@ng-show='dnclistform.dnc_name.$dirty&&dnclistform.dnc_name.$error.minlength']")
	public WebElement dncnameEror;
	@FindBy(xpath = "//span[@ng-show='dnclistform.dnc_name.$touched && dnclistform.dnc_name.$invalid']")
	public WebElement dncnameRequerd;
	@FindBy(xpath = "//span[@ng-show='showError']")
	public WebElement dncnameAlready;
	@FindBy(xpath = "//select[@ng-model='insertDncList.status']")
	public WebElement status;
	@FindBy(xpath = "//input[@class='select2-search__field']")
	public WebElement campaignname;
	@FindBy(xpath = "//input[@type='file']")
	public WebElement chooseFile;
	@FindBy(xpath = "//input[@name='description']")
	public WebElement description;
	@FindBy(xpath = "//button[@ng-click='show_popup_dnc()']")
	public WebElement samplefilefordnclile;
	@FindBy(xpath = "//a[@download='campaign.csv']")
	public WebElement downloadssamplefile;
	@FindBy(xpath = "(//input[@name='insert'])[2]")
	public WebElement save;

	////// System Dnc List//////////////////////////////////////////////////////
	@FindBy(xpath = "//button[@ng-click='edit_dnc_list(item.dnc_uuid)']")
	public WebElement systemdnclist;
	@FindBy(xpath = "//input[@ng-model='insertDncList.dnc_name']")
	public WebElement systemdncname;
	@FindBy(xpath = "//input[@name='file']")
	public WebElement uploaddata;
	@FindBy(xpath = "//input[@name='description']")
	public WebElement description1;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/div[2]/button")
	public WebElement sampleFilesSystemDNC;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/div[2]/button")
	public WebElement downloadsampleFilesSystemDNC;
	@FindBy(xpath = "//input[@name='file']")
	public WebElement chooseFile1;
	@FindBy(xpath = "(//input[@ng-model='insertDncList.upload_data'])[1]")
	public WebElement appendsclick;
	@FindBy(xpath = "(//input[@ng-model='insertDncList.upload_data'])[2]")
	public WebElement replaceclick;

	@FindBy(xpath = "(//input[@name='insert'])[2]")
	public WebElement save1;

	// =====filter=====
	@FindBy(xpath = "(//button[@class='btn btn-success ng-binding'])[2]")
	public WebElement filter;
	@FindBy(xpath = "//input[@ng-model='dnc_search.name']")
	public WebElement filtername;
	@FindBy(xpath = "//input[@ng-model='dnc_search.campaign_name']")
	public WebElement filtercampaignname;
	@FindBy(xpath = "//select[@ng-model='dnc_search.status']")
	public WebElement filterstatus;
	@FindBy(xpath = "(//button[@type='submit'])[1]")
	public WebElement filtersubmit;
	@FindBy(xpath = "//button[@title='Edit']")
	public WebElement edit;
	@FindBy(xpath = "(//span[@class='checkmark ng-scope'])[1]")
	public WebElement deleteclick;
	@FindBy(xpath = "//button[@ng-click='multideleteDnc()']")
	public WebElement delete;

	@FindBy(xpath = "(//button[@title='DNC Numbers'])[1]")
	public WebElement Eye_addmanuallyNumber;
	@FindBy(xpath = "//button[@title='Add number in DNC list']")
	public WebElement manuallyNumber;
	@FindBy(xpath = "(//button[@title='Edit'])[1]")
	public WebElement editmanuallyNumber;

	public void AppsClick() throws InterruptedException {
		clickApps.click();
	}

	public void ClickModuleDNC() {
		clickDNCModule.sendKeys(Keys.ENTER);
	}

	public void ClickAdd() {
		AddDncclick.sendKeys(Keys.ENTER);
	}

	public void DNCName(String dnc) {
		dncname.sendKeys(dnc);
		boolean validation1 = dncnameEror.isDisplayed();
		boolean validation2 = dncnameRequerd.isDisplayed();
		boolean validation3 = dncnameAlready.isDisplayed();

		if (validation1) {
			validation1 = true;
			test.log(LogStatus.FAIL, "Invalid Username =>  DNC Name is Invalid " + "=>" + dnc);
		} else if (validation2) {
			validation2 = true;
			test.log(LogStatus.FAIL, "Invalid Username =>  DNC Name is required " + "=>" + dnc);

		} else if (validation3) {
			validation3 = true;
			test.log(LogStatus.FAIL, "Invalid Username =>  DNC Name is Already used " + "=>" + dnc);

		} else {
			test.log(LogStatus.PASS, "valid DNC Name => Correct DNC Name  " + "=>" + dnc);

		}

	}

	public void DropdownStatus(String statuss) {
		boolean dropdown = status.isEnabled();
		if (dropdown) {
			dropdown = true;
			Select s = new Select(status);
			s.selectByVisibleText(statuss);
		}
	}

	public void CampaignNameDropdown(String campaigN) {
		boolean dropdown = campaignname.isEnabled();
		if (dropdown) {
			dropdown = true;
			status.sendKeys(Keys.TAB);
			String str = campaigN;
			String[] res = str.split("[,]", 0);
			for (String myStr : res) {
				System.out.println(res);
				try {
					campaignname.sendKeys(myStr);
					campaignname.sendKeys(Keys.ARROW_DOWN);
					campaignname.sendKeys(Keys.ENTER);
				} catch (Exception e) {
					test.log(LogStatus.PASS, "DNC => Please Select Campaign Name  ");
				}
			}
		}
	}

	public void ImportDncList(String importt) {
		System.out.println(importt);
		campaignname.sendKeys(Keys.TAB);
		String filepath = "/home/swapnil/Downloads/" + importt + ".csv";
		System.out.println(filepath);
		try {
			chooseFile.sendKeys(filepath);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Please First Download The File");
		}
	}

	public void Description(String desc) {
		description.sendKeys(desc);
	}

	public void SampleDownload() {
		samplefilefordnclile.click();
	}

	public void DownloadCSVFile() {
		WebElement filedown = driver.findElement(By.linkText("Download Sample File"));
		String sourceLocation = filedown.getAttribute("href");
		filedown.click();
	}

	public void SaveButton() {
		save.click();
	}

	public void Eye_ManuallyAddNumber() {
		Eye_addmanuallyNumber.click();
	}

	public void AddManualNumber() {
		manuallyNumber.click();
	}
	
	public void EditManuallyNumber() {
		editmanuallyNumber.click();
	}

	public void SystemDncList() {
		systemdnclist.click();
	}

	public void SystemDncName(String dnc) {
		dncname.clear();
		dncname.sendKeys(dnc);
	}

	public void SystemImport(String importt) {
		systemdncname.click();
		String filepath = "/home/swapnil/Downloads/" + importt + ".csv";
		System.out.println(filepath);
		chooseFile1.sendKeys(filepath);
	}

	public void UploadData() {
		replaceclick.click();
	}
	
	public void UploadDataAppent() {
		appendsclick.click();
	}

	public void SystemDecription1(String desc1) {
		description1.clear();
		description1.sendKeys(desc1);
	}

	public void SystemSampleDownloadsFiles() {
		sampleFilesSystemDNC.click();
	}

	public void SystemDownloadsFiles(String systemfile) {
		WebElement filedown = driver.findElement(By.linkText("Download Sample File"));
		String sourceLocation = filedown.getAttribute("href");
		filedown.click();
	}

	public void SaveButton1() {
		save1.click();
	}

	public void FilterClick() {
		filter.click();
	}

	public void FilterSubmit() {
		filtersubmit.click();
	}

	public void FilterdncName() {
		filtername.sendKeys("System_DNC_List");
	}

	public void FilterdncCampaignName(String campaign) {
		filtercampaignname.sendKeys(campaign);
	}

	public void FilterdncStatus(String status) {
		Select s = new Select(filterstatus);
		s.selectByVisibleText(status);
	}

	public void Delete() throws InterruptedException {
		deleteclick.click();
		Thread.sleep(1000);
		delete.click();
		ChromeOptions opt = new ChromeOptions();
		driver.switchTo().alert().accept();
	}

	public void EditDnc() {
		edit.click();
	}
}

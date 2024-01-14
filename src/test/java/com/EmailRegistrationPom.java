package com.intalk.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.intalk.baseclasses.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

public class EmailConfigurationPom extends BaseClass {

	WebDriver driver;

	public EmailConfigurationPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='apps-icon ng-binding']")
	private WebElement clickApps;
	@FindBy(xpath = "//*[@id=\"mySidenav\"]/div[2]/ul/li[24]/a")
	public WebElement emailconfigModule;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/form/div[1]/div/div/div[2]/span/a")
	public WebElement addemailconfig;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div/div[2]/input")
	public WebElement emailhost;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div/div[2]/span[1]")
	public WebElement emailhostRequired;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[1]/div/div[2]/span[2]")
	public WebElement emailhostError;

	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[2]/div/div[2]/input")
	public WebElement emailportNumber;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[2]/div/div[2]/span[1]")
	public WebElement emailportNumberRequired;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[2]/div/div[2]/span[2]")
	public WebElement emailportNumberError;

	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[3]/div/div[2]/input")
	public WebElement emailFromname;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[3]/div/div[2]/span[2]")
	public WebElement emailFromnameRequired;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[3]/div/div[2]/span[1]")
	public WebElement emailFromnameError;

	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[4]/div/div[2]/input")
	public WebElement emailId;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[4]/div/div[2]/span[2]")
	public WebElement emailIdRequired;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[4]/div/div[2]/span[3]")
	public WebElement emailIdAlready;

	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[5]/div/div[2]/input")
	public WebElement emailusername;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[5]/div/div[2]/span[1]")
	public WebElement emailusernameError;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[5]/div/div[2]/span[2]")
	public WebElement emailusernameRequired;
	
	@FindBy(xpath = "//*[@id=\"pass\"]")
	public WebElement emailpassword;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[7]/div/div[2]/select")
	public WebElement emailencrypted;
	@FindBy(xpath = "//*[@id=\"agent_spec\"]")
	public WebElement agentspecifyemailId;
	@FindBy(xpath = "//*[@id=\"agent_not_spec\"]")
	public WebElement followingemailId;
	@FindBy(xpath = "//*[@id=\"detailbox\"]/div/div/form/div[9]/div/div[2]/input")
	public WebElement replyemailid;
	@FindBy(xpath = "//*[@id=\"noneemail\"]")
	public WebElement none;
	@FindBy(xpath = "//*[@id=\"insert\"]")
	public WebElement save;

	public void AppsClick() throws InterruptedException {
		clickApps.click();

	}

	public void EmailConfigClick() throws InterruptedException {
		emailconfigModule.sendKeys(Keys.ENTER);
	}

	public void Addemailconfig() {
		addemailconfig.sendKeys(Keys.ENTER);
	}

	public void ClickEmailCongifModule() {
		emailhost.sendKeys(Keys.ENTER);
	}

	public void EmailHost(String host) {
		emailhost.sendKeys(host);
		boolean validation1 = emailhostRequired.isDisplayed();
		boolean validation2 = emailhostError.isDisplayed();
		if (validation1) {
			validation1 = true;
			test.log(LogStatus.FAIL, "Invalid Host Name =>  Email Host required" + "=>" + host);
		} else if (validation2) {
			validation2 = true;
			test.log(LogStatus.FAIL,
					"Invalid Host Name =>  Invalid Email Host,character should be alphabet or number, space should not allowed."
							+ "=>" + host);
		} else {
			test.log(LogStatus.PASS, "valid Host Name => Correct Username  " + "=>" + host);
		}
	}

	public void EmailPort(String port) {
		emailportNumber.sendKeys(port);
		boolean validation1 = emailportNumberRequired.isDisplayed();
		boolean validation2 = emailportNumberError.isDisplayed();
		if (validation1) {
			validation1 = true;
			test.log(LogStatus.FAIL, "Invalid Port Number =>  Email Port Number required" + "=>" + port);
		} else if (validation2) {
			validation2 = true;
			test.log(LogStatus.FAIL,
					"Invalid Port Number =>  Invalid Email Host,character should be alphabet or number, space should not allowed."
							+ "=>" + port);
		} else {
			test.log(LogStatus.PASS, "valid Port Number => Correct port Number  " + "=>" + port);
		}
	}

	public void EmailFromName(String emailname) throws InterruptedException {
		emailFromname.sendKeys(emailname);
		Thread.sleep(1000);
		boolean validation1 = emailFromnameRequired.isDisplayed();
		boolean validation2 = emailFromnameError.isDisplayed();
		if (validation1) {
			validation1 = true;
			test.log(LogStatus.FAIL, "Invalid Email From Name =>  Form Name Requried" + "=>" + emailname);
		} else if (validation2) {
			validation2 = true;
			test.log(LogStatus.FAIL, "Invalid Email From Name =>  Enter Valid Form Name" + "=>" + emailname);
		} else {
			test.log(LogStatus.PASS, "valid Email From Name  " + "=>" + emailname);
		}
	}

	public void EmailId(String emailid) {
		emailId.sendKeys(emailid);
		boolean validation1 = emailIdRequired.isDisplayed();
		boolean validation2 = emailIdAlready.isDisplayed();
		if (validation1) {
			validation1 = true;
			test.log(LogStatus.FAIL, "Invalid Email ID  =>  Email ID Requried" + "=>" + emailid);
		} else if (validation2) {
			validation2 = true;
			test.log(LogStatus.FAIL, "Invalid Email ID =>   Email ID Already used" + "=>" + emailid);
		} else {
			test.log(LogStatus.PASS, "valid Email ID " + "=>" + emailid);
		}
	}

	public void EmailUsername(String username) {
		emailusername.sendKeys(username);
		boolean validation1 = emailusernameRequired.isDisplayed();
		boolean validation2 = emailusernameError.isDisplayed();
		if (validation1) {
			validation1 = true;
			test.log(LogStatus.FAIL, "Invalid Host Name =>  Email Host required" + "=>" + username);
		} else if (validation2) {
			validation2 = true;
			test.log(LogStatus.FAIL,
					"Invalid Host Name =>  Invalid Email Host,character should be alphabet or number, space should not allowed."
							+ "=>" + username);
		} else {
			test.log(LogStatus.PASS, "valid Host Name => Correct Username  " + "=>" + username);
		}
	}

	public void EmailPassword(String password) {
		emailpassword.sendKeys(password);
	}

	public void EmailEncrypted(String encry) {
		boolean dropdown = emailencrypted.isEnabled();
		if (dropdown) {
			dropdown = true;
			Select s = new Select(emailencrypted);
			s.selectByVisibleText(encry);
		}
	}

	public void EmailReplyToAgentSpecificEmailId() {
		agentspecifyemailId.click();
	}

	public void EmailReplyToFollowingSpecificEmailId() {
		followingemailId.click();
	}

	public void None() {
		none.click();
	}

	public void SaveButton() {
		save.click();
	}

	public void ReplyAgentMailId(String agentmailidreply) {
		replyemailid.sendKeys(agentmailidreply);
	}
}

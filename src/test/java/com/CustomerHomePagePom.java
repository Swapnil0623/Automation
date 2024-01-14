package com.intalk.pom;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.intalk.baseclasses.BaseClass;
import com.intalk.baseclasses.CommonUtils;

public class AgentHomePageClassPom extends BaseClass {

	boolean result;
	Actions act;
	static String ringingtime;
	static String lastRingtime;
	static String firstRingtime;
	static String date1;
	static String date11;
	static String lastOnCalltime;
	static String lastWrapuptime;
	static String currentWindowHandle;
	public static String timeDifferenceStr;
	@FindBy(xpath = "//button[@title='Refresh']")
	public WebElement refresh_agentSide;

	@FindBy(xpath = "//*[@id=\"mainController\"]/div[9]/section/div/div/div[2]/div[1]/div[2]/div[3]/div[1]/div/div/div/button")
	public WebElement queue_select;

	@FindBy(xpath = "//*[@id=\"mainController\"]/div[9]/section/div/div/div[2]/div[1]/div[2]/div[3]/div[1]/div/div/div/div/div/input")
	public WebElement queue_search;

	@FindBy(xpath = "//*[@id=\"phoneScreen\"]")
	public WebElement phonenumber;

	@FindBy(xpath = "//button[@title='Call']")
	public WebElement callButton;

	@FindBy(xpath = "//select[@name='call_to_queue']")
	public WebElement selectCallList;

	@FindBy(xpath = "//*[@id=\"mainController\"]/div[9]/section/div/div/div[2]/div[1]/div[2]/div[3]/div[1]/div/div/div/div/ul/li[12]/label/input")
	public WebElement displayCheckBox;

	@FindBy(xpath = "//*[@id=\"btn_call\"]/button")
	public WebElement callbtn;

	@FindBy(xpath = "//div[@ng-class=\"applyPermission('Agent Mood','Call-Dialpad')\"]")
	public WebElement dialpadDisplayAfterCutCall;
	@FindBy(xpath = "//select[@ng-model='dispositionData.disposition']")
	public static WebElement dialpadDisposition;

	@FindBy(xpath = "//div[@class='dials-controller-option']")
	public static WebElement AfterReceiveCallDisplay;
	@FindBy(xpath = "//strong[@ng-bind='timeStatus|lang']")
	public static WebElement zoneTime;
	@FindBy(xpath = "//select[@ng-model='dispositionData.disposition']")
	public static WebElement mooddisplay;

	@FindBy(xpath = "(//textarea[@ng-model='dispositionData.remark'])[2]")
	public WebElement dialpadremark;
	@FindBy(xpath = "//button[@ng-if='!disposition_required']")
	public WebElement dialpadSumbit;
	@FindBy(xpath = "(//button[@id='hangUpcall'])[1]")
	public WebElement CallbuttonCur;
	@FindBy(xpath = "(//img[@title='Very Bad'])[2]")
	public WebElement verybad;
	@FindBy(xpath = "(//img[@title='Bad'])[2]")
	public WebElement bad;
	@FindBy(xpath = "(//img[@title='Average'])[2]")
	public WebElement average;
	@FindBy(xpath = "(//img[@title='Good'])[2]")
	public WebElement Good;
	@FindBy(xpath = "(//img[@title='Very Good'])[2]")
	public WebElement veryGood;
	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	public WebElement clearallcallback;
	@FindBy(xpath = "//button[@ng-if=\"group_type != 'supervisor' && group_type != 'operator' && group_type != 'campaign_supervisor'\"]")
	public WebElement redial;
	@FindBy(xpath = "//button[@ng-if='!disposition_required']")
	public WebElement submitAfterCall;
	@FindBy(xpath = "//button[@ng-disabled='!call_to_campaign.queue']")
	public WebElement selectCustomerDetailsClick;

	@FindBy(xpath = "//button[@ng-if='!disposition_required']")
	public WebElement submit;
	@FindBy(xpath = "(//strong[@class='ng-binding ng-scope'])[2]")
	public WebElement liverunningTime;

	@FindBy(xpath = "//span[@class='dialpad_call_timer ng-binding']")
	public WebElement callTime;

	@FindBy(xpath = "//div[@class='callback_call_type']")
	public WebElement calltype;
	@FindBy(xpath = "//span[@id='dialpadUP2']")
	public WebElement clickdialpadbtn;
	@FindBy(xpath = "//div[@class='dials-pad']")
	public WebElement dialpaddisplay;
	
	
	@FindBy(xpath = "//div[@id='changeDisp']")
	public WebElement wrapupDisplay;
	

	public AgentHomePageClassPom(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	public void Agent_HomePage_Refresh() throws EncryptedDocumentException, IOException, InterruptedException {
		refresh_agentSide.click();
	}

//	public void SelectQueueAgent(String queue) throws InterruptedException {
//		WebElement clickqueue=driver.findElement(By.xpath("(//optgroup[@label='vp_manual'])[2]"));
//		System.out.println(clickqueue+"kkkkkkkkkkkkkk");
//		boolean dropdown = queue_select.isEnabled();
//		if (dropdown) {
//			dropdown = true;
//			queue_select.sendKeys(Keys.ENTER);
//				queue_search.click();
//				Thread.sleep(2000);
//			    queue_search.sendKeys(queue);
//				System.out.println("-------------------------------");
//				act.moveToElement(clickqueue).click();
//				clickqueue.click();
//				Thread.sleep(2000);

	// Thread.sleep(2000);
//			}
//		
//	}

	public void ClickDilapadButton() {
		clickdialpadbtn.click();
	}

	public void DisplayMethodQueueBox() {
		displayCheckBox.isDisplayed();
	}

	public String PhoneNumber(String phonenumberr) {
		phonenumber.sendKeys(phonenumberr);
		phonenumber.sendKeys(Keys.TAB);
		return phonenumberr;

	}

	public void CallButton(int rowNum) throws EncryptedDocumentException, IOException {
		callbtn.click();

	}

	public void Display_Dialpad() {
		result = dialpadDisplayAfterCutCall.isDisplayed();

	}

	public void DialpadDisposition(String dispos) throws InterruptedException {
		Select s = new Select(dialpadDisposition);
		Thread.sleep(500);
		s.selectByVisibleText(dispos);
		Thread.sleep(500);
	}

	public void DispositionCustomer(String disposCust) throws InterruptedException {
		Select s = new Select(dialpadDisposition);
		s.selectByVisibleText(disposCust);
		Thread.sleep(500);
	}

	public void DialpadRemark(String remark) {
		dialpadremark.sendKeys(remark);
	}

	public void SumbitAfterCall() {
		dialpadSumbit.click();
	}

	public void CutCallButton() throws EncryptedDocumentException, IOException {

		CallbuttonCur.click();

	}

	public void Verybad() {
		verybad.click();
	}

	public void Bad() {
		bad.click();
	}

	public void Good() {
		Good.click();
	}

	public void Average() {
		average.click();
	}

	public void VeryGood() {
		veryGood.click();
	}

	public static void RunningDialDisplay() {
		boolean dialpadDisplay = dialpadDisposition.isDisplayed();

	}

	public static void WrapupDisplay() {
		boolean wrapupdisplay = dialpadDisposition.isDisplayed();
	}

	public void RedialMethod() {
		redial.click();
	}

	public static void Setup() {
		String data = zoneTime.getText();
	}

	public void SelectCustomerDetails(int rowNum) throws EncryptedDocumentException, IOException {
		selectCustomerDetailsClick.click();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		date1 = dateFormat.format(date);
		date11 = timeFormat.format(date);
		CommonUtils.CallingData("AgentHome", rowNum, 13, date1);
		firstRingtime = date11;
	}

	public void LiveRunningCallTiming(int rowNum) throws EncryptedDocumentException, IOException {
		String ringingtime = liverunningTime.getText();
		CommonUtils.CallingData("AgentHome", rowNum, 14, ringingtime);
	}

	public void LiveOnCallTime(int rowNum) throws EncryptedDocumentException, IOException {
		String oncall = liverunningTime.getText();
		CommonUtils.CallingData("AgentHome", rowNum, 15, oncall);
	}

	public void WrapupTime(int rowNum) throws EncryptedDocumentException, IOException, ParseException {
		String wrapup = liverunningTime.getText();
		CommonUtils.CallingData("AgentHome", rowNum, 16, wrapup);

	}

	public void CallType(int rowNum) throws EncryptedDocumentException, IOException {
		String calltypee = calltype.getText();
		CommonUtils.CallingData("AgentHome", rowNum, 18, calltypee);
	}

	public void setCurrentRingTime(int rowNum) throws ParseException, EncryptedDocumentException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date2 = new Date();
		lastRingtime = dateFormat.format(date2);
		calculateDifferenceRinging(rowNum);
	}

	public void calculateDifferenceRinging(int rowNum) throws ParseException, EncryptedDocumentException, IOException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		Date d2 = simpleDateFormat.parse(lastRingtime);
		Date d1 = simpleDateFormat.parse(firstRingtime);
		long timeDifferenceMillis = d2.getTime() - d1.getTime();
		long seconds = timeDifferenceMillis / 1000;
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		seconds = seconds % 60;

		String timeDifferenceStr = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		CommonUtils.CallingData("AgentHome", rowNum, 14, timeDifferenceStr);
	}

	public void setCurrentOnCallTime(int rowNum) throws ParseException, EncryptedDocumentException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date2 = new Date();
		lastOnCalltime = dateFormat.format(date2);
		calculateDifferenceOnCall(rowNum);
	}

	public void calculateDifferenceOnCall(int rowNum) throws ParseException, EncryptedDocumentException, IOException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		Date d2 = simpleDateFormat.parse(lastOnCalltime);
		Date d1 = simpleDateFormat.parse(lastRingtime);
		long timeDifferenceMillis = d2.getTime() - d1.getTime();
		long seconds = timeDifferenceMillis / 1000;
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		seconds = seconds % 60;

		String timeDifferenceStr = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		CommonUtils.CallingData("AgentHome", rowNum, 15, timeDifferenceStr);

	}

}

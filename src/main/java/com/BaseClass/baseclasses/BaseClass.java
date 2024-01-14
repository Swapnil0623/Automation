package com.intalk.baseclasses;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(com.intalk.listeners.ListenerTest.class)
public class BaseClass {

	public static WebDriver driver;
//	public static WebDriver driver;
	public static ExtentTest test;
	public static ExtentReports report;
	public static String urldomain = "";
	public static String path1 = "";
	public static String path = "";
	public static String calllistpath = "";

	static Optional<Path> browserPath;

//	
	public static void initilizeBrowserChrome() throws IOException {
		if (report == null) {
			buildExtentReport();

			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--enable-notifications");
			opt.addArguments("--custom-flag=normal");
			opt.addArguments("--use-fake-ui-for-media-stream");
			opt.addArguments("--use-fake-device-for-media-stream");
			opt.addArguments("allow-file-access-from-files");
			opt.addArguments("--enable-user-media-security=true");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 1);
			prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
			prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
			prefs.put("profile.default_content_setting_values.geolocation", 1);
			prefs.put("profile.default_content_setting_values.notifications", 1);
			opt.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			driver = new ChromeDriver(opt);
			driver.get(CommonUtils.getURL_Domain(1, 0));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}
	}

	public static void secondinitilizeBrowser() throws IOException {
		if (report == null) {
			buildExtentReport();
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--incognito");
			opt.addArguments("--custom-flag=incognito");
			opt.addArguments("--enable-notifications");
			opt.addArguments("enable-notifications");
			opt.addArguments("--enable-notifications");
			opt.addArguments("--use-fake-ui-for-media-stream");
			opt.addArguments("--use-fake-device-for-media-stream");
			opt.addArguments("allow-file-access-from-files");
			opt.addArguments("--enable-user-media-security=true");
			// opt.addArguments("--headless");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 1);
			prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
			prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
			prefs.put("profile.default_content_setting_values.geolocation", 1);
			prefs.put("profile.default_content_setting_values.notifications", 1);
			opt.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(opt);
			driver.get(CommonUtils.getURL_Domain(1, 0));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}
	}

	public static void buildExtentReport() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div>");
		sb.append("<p style = 'text -align :center'><h3>Result</h3><br></p>");
		sb.append("<img src=\"Logo/image.png\"  alt=\"intalk.io\"  style=\"height: 50px;\">");

		sb.append("</div>");

		String path = "TestData " + ".html";
		report = new ExtentReports(path);
		test = report.startTest(sb.toString());
	}

}

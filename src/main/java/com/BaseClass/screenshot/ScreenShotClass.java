package com.intalk.screenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.intalk.baseclasses.BaseClass;

public class ScreenShotClass extends BaseClass {

	public static void captureScreenshot(WebDriver driver, String folderName, String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            fileName = fileName + "_" + timestamp + ".png";
            String projectPath = System.getProperty("user.dir");
            String screenshotPath = projectPath + "/screenshots/" + folderName + "/" + fileName;

            FileUtils.forceMkdirParent(new File(screenshotPath));
            FileUtils.copyFile(source, new File(screenshotPath));

            System.out.println("Screenshot taken: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}

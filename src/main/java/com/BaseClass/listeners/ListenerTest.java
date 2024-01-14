package com.intalk.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.intalk.baseclasses.BaseClass;
import com.intalk.screenshot.ScreenShotClass;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.ExceptionInfo;
import com.relevantcodes.extentreports.model.ITest;
import com.relevantcodes.extentreports.model.Log;
import com.relevantcodes.extentreports.model.Test;
import com.relevantcodes.extentreports.model.TestAttribute;

public class ListenerTest implements ITestListener{

//	public void onStart(ITestContext context) {	
//		System.out.println("onStart method started");
//		System.out.println("========================================");
//	}
//
//	public void onFinish(ITestContext context) {
//		System.out.println("onFinish method started");
//		System.out.println("========================================");
//	}
//	
//		public void onTestStart(ITestResult result) {
//			System.out.println("New Test Started" +result.getName());
//			System.out.println("========================================");
//		}
//		
//		public void onTestSuccess(ITestResult result) {
//			System.out.println("onTestSuccess Method" +result.getName());
//			System.out.println("========================================");
//		}
//
//		public void onTestFailure(ITestResult result) {
//			System.out.println("onTestFailure Method" +result.getName());
//			System.out.println("========================================");
//			  try {
//		            ScreenShotClass.TakeScreenshot(BaseClass.driver, result.getName());
//		        	System.out.println("========================================");
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//			
//		}
//
//		public void onTestSkipped(ITestResult result) {
//			System.out.println("onTestSkipped Method" +result.getName());
//			System.out.println("========================================");
//		}
//
//		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//			System.out.println("onTestFailedButWithinSuccessPercentage" +result.getName());
//			System.out.println("========================================");
//		}
//		
//
}
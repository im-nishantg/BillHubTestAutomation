package com.billhub.qa.extentReportListener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class ExtentReportNG extends TestBase implements ITestListener{
	
	ExtentReports report = new ExtentReports();
    File extentReportFile = new File(System.getProperty("user.dir") + "\\test-output" + File.separator + "ExtentReport.html");
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
    ExtentTest eTest;

    {
        report.attachReporter(sparkReporter);
    }

	public void onTestStart(ITestResult result) {
		
		String testName = result.getName();
		eTest = report.createTest(testName);
		eTest.log(Status.INFO,testName+" execution started");
		
	}

	public void onTestSuccess(ITestResult result) {
		
		String testName = result.getName();
		eTest.log(Status.PASS,testName+" got successfully executed");
		
	}

	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		eTest.log(Status.FAIL,testName+" got failed");
		eTest.addScreenCaptureFromPath(TestUtils.takeScreenshot(testName),testName);
		eTest.log(Status.INFO,result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		
		String testName = result.getName();
		eTest.log(Status.SKIP,testName+" got skipped");
		eTest.log(Status.INFO,result.getThrowable());
		
	}

	public void onFinish(ITestContext context) {
		
		report.flush();
		File eReportFile = new File(System.getProperty("user.dir")+ "\\test-output" + File.separator + "ExtentReport.html");
		
		try {
			Desktop.getDesktop().browse(eReportFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


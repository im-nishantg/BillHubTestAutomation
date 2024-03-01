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
		File eReportFile = new File(System.getProperty("user.dir")+"\\ExtentReports\\eReport.html");
		
		try {
			Desktop.getDesktop().browse(eReportFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

//public class ExtentReportNG implements IReporter {
//    private ExtentReports extent;
//
//    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//
//        ExtentSparkReporter spark = new ExtentSparkReporter(outputDirectory + File.separator + "ExtentReport.html");
//        extent = new ExtentReports();
//        extent.attachReporter(spark);
//
//        for (ISuite suite : suites) {
//            Map<String, ISuiteResult> result = suite.getResults();
//
//            for (ISuiteResult r : result.values()) {
//                ITestContext context = r.getTestContext();
//
//                buildTestNodes(context.getPassedTests().getAllResults(), Status.PASS);
//                buildTestNodes(context.getFailedTests().getAllResults(), Status.FAIL);
//                buildTestNodes(context.getSkippedTests().getAllResults(), Status.SKIP);
//            }
//        }
//
//        extent.flush();
//    }
//
//    private void buildTestNodes(Set<ITestResult> resultSet, Status status) {
//        ExtentTest test;
//
//        if (!resultSet.isEmpty()) {
//            for (ITestResult result : resultSet) {
//                test = extent.createTest(result.getMethod().getMethodName());
//
//                test.getModel().setStartTime(getTime(result.getStartMillis()));
//                test.getModel().setEndTime(getTime(result.getEndMillis()));
//
//                for (String group : result.getMethod().getGroups()) {
//                    test.assignCategory(group);
//                }
//
//                if (result.getThrowable() != null) {
//                    test.log(status, result.getThrowable());
//                } else {
//                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
//                }
//
//                extent.flush();
//            }
//        }
//    }
//
//    private Date getTime(long millis) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(millis);
//        return calendar.getTime();
//    }
//}


//package com.billhub.qa.extentReportListener;
//
//import java.io.File;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.testng.IReporter;
//import org.testng.ISuite;
//import org.testng.ISuiteResult;
//import org.testng.ITestContext;
//import org.testng.ITestResult;
//import org.testng.xml.XmlSuite;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//// Import necessary libraries for screenshot capture
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//public class ExtentReportNG implements IReporter {
//    private ExtentReports extent;
//    private WebDriver driver; // Add a field to store the WebDriver instance
//
//    public ExtentReportNG(WebDriver driver) { // Inject the WebDriver instance in the constructor
//        this.driver = driver;
//    }
//
//    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//
//        ExtentSparkReporter spark = new ExtentSparkReporter(outputDirectory + File.separator + "ExtentReport.html");
//        extent = new ExtentReports();
//        extent.attachReporter(spark);
//
//        for (ISuite suite : suites) {
//            Map<String, ISuiteResult> result = suite.getResults();
//
//            for (ISuiteResult r : result.values()) {
//                ITestContext context = r.getTestContext();
//
//                buildTestNodes(context.getPassedTests().getAllResults(), Status.PASS, true); // Add true to capture screenshot for passed tests
//                buildTestNodes(context.getFailedTests().getAllResults(), Status.FAIL, true);
//                buildTestNodes(context.getSkippedTests().getAllResults(), Status.SKIP, false); // Don't capture screenshot for skipped tests
//            }
//        }
//
//        extent.flush();
//    }
//
//    private void buildTestNodes(Set<ITestResult> resultSet, Status status, boolean captureScreenshot) {
//        ExtentTest test;
//
//        if (!resultSet.isEmpty()) {
//            for (ITestResult result : resultSet) {
//                test = extent.createTest(result.getMethod().getMethodName());
//
//                test.getModel().setStartTime(getTime(result.getStartMillis()));
//                test.getModel().setEndTime(getTime(result.getEndMillis()));
//
//                for (String group : result.getMethod().getGroups()) {
//                    test.assignCategory(group);
//                }
//
//                if (result.getThrowable() != null) {
//                    test.log(status, result.getThrowable());
//                } else {
//                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
//                }
//
//                // Capture screenshot logic
//                if (captureScreenshot) {
//                    try {
//                        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//                        String screenshotPath = test.addScreenCaptureFromPath(screenshot.getAbsolutePath()).toString(); // Correct line
//                        test.log(status, "Screenshot: " + screenshotPath);
//                    } catch (Exception e) {
//                        System.out.println("Failed to capture screenshot: " + e.getMessage());
//                    }
//                }
//
//                extent.flush();
//            }
//        }
//    }
//
//    private Date getTime(long millis) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(millis);
//        return calendar.getTime();
//    }
//}


package com.billhub.qa.extentReportListener;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG implements IReporter {
    private ExtentReports extent;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        ExtentSparkReporter spark = new ExtentSparkReporter(outputDirectory + File.separator + "ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getPassedTests().getAllResults(), Status.PASS);
                buildTestNodes(context.getFailedTests().getAllResults(), Status.FAIL);
                buildTestNodes(context.getSkippedTests().getAllResults(), Status.SKIP);
            }
        }

        extent.flush();
    }

    private void buildTestNodes(Set<ITestResult> resultSet, Status status) {
        ExtentTest test;

        if (!resultSet.isEmpty()) {
            for (ITestResult result : resultSet) {
                test = extent.createTest(result.getMethod().getMethodName());

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));

                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                extent.flush();
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}


package com.billhub.qa.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.CustomerPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;

public class CustomerPageTest extends TestBase{
	String customerName="Rajneesh";
	String customerCode="9087654";
	String customerPeriod="2";
	String customerDrop="AOB";
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	CustomerPage customerPage;

	public CustomerPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException{
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("Mdm_userId"),prop.getProperty("password"));
		customerPage = mdmDashboardPage.clickOnCustomerLink();
	}

	@Test
	public void SearchCustomerByNameTest() throws InterruptedException {
			boolean result = customerPage.validateSearchCustomerByName();
			Assert.assertTrue(result, "Search Customer By Name Test Passed");
	}
	@Test
	public void SearchCustomerByCodeTest() throws InterruptedException {
//		customerPage.validateSearchCustomerByCode();
			boolean result = customerPage.validateSearchCustomerByCode();
			Assert.assertTrue(result, "Search Customer By Code Test Passed");

	}

	@Test
	public void AddCustomerWithInvalidDataTest() throws InterruptedException {
		boolean isTestFailed = customerPage.validateAddCustomerWithValidData(customerCode,customerName,customerPeriod,customerDrop);
		Assert.assertFalse(isTestFailed, "Test failed as invalid data saved successfully.");
	}
	@Test
	public void AddCustomerWithValdiDataTest() throws InterruptedException {
		boolean result= customerPage.validateAddCustomerWithValidData(customerName,customerCode,customerPeriod,customerDrop);
		Assert.assertTrue(result,"Test failed");
	}

	@Test
	public void CustomerAppearanceTest() throws InterruptedException {
		boolean result= customerPage.validateCustomerInTable(customerName,customerCode,customerPeriod,customerDrop);
		Assert.assertTrue(result,"Test failed");
	}

	@Test
	public void AddCustomerWithoutDataTest() throws InterruptedException {
		boolean result=customerPage.validateAddCustomerWithBlank();
		Assert.assertTrue(result,"Test failed as it has not saved with blank data");
	}
	@AfterMethod
	public void tearDown() {
		driver.close();						
	}

}

package com.billhub.qa.testcases;

import com.billhub.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.CustomerPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;

import java.time.Duration;

public class CustomerPageTest extends TestBase{
	
	String customerName="Aman sharma";
	String customerCode="234510";
	String customerPeriod="2";
	String customerDrop="AOB";
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	CustomerPage customerPage;

	public CustomerPageTest() {
		super();
	}
	
	@BeforeClass
	public void setup() throws InterruptedException  {
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
		Thread.sleep(Duration.ofSeconds(30).toMillis());
		customerPage = mdmDashboardPage.clickOnCustomerLink();
	}

	@Test
	public void SearchCustomerByNameTest() throws InterruptedException {
			boolean result = customerPage.validateSearchCustomerByName(customerName);
			Assert.assertTrue(result, "Search Customer By Name Test Failed");
	}
	@Test
	public void SearchCustomerByCodeTest() throws InterruptedException {
			boolean result = customerPage.validateSearchCustomerByCode(customerCode);
			Assert.assertTrue(result, "Search Customer By Code Test Failed");

	}
	@Test
	public void AddCustomerWithValdiDataTest() throws InterruptedException {
		boolean result= customerPage.validateAddCustomerWithValidData(customerName,customerCode,customerPeriod,customerDrop);
		Assert.assertTrue(result,"Test failed");
	}
	@Test
	public void AddCustomerWithInvalidDataTest() throws InterruptedException {
		boolean isTestFailed = customerPage.validateAddCustomerWithInvalidData(customerName,customerCode,customerPeriod,customerDrop);
		Assert.assertFalse(isTestFailed, "Test failed as invalid data saved successfully.");
	}

	@Test
	public void AddCustomerWithoutDataTest() throws InterruptedException {
		boolean result=customerPage.validateAddNewCustomerWithoutData(customerName,customerCode,customerPeriod,customerDrop);
		Assert.assertTrue(result,"Test failed as it has not saved with blank data");
	}

	@Test
	public void ValidateCustomerInDatabaseTest(){
		boolean isPresent = customerPage.validateAddedCustomerInDatabase(customerCode);
		Assert.assertTrue(isPresent, "Customer is present but not found in the database.");
	}
	@AfterClass
	public void tearDown() {
		driver.close();						
	}

}
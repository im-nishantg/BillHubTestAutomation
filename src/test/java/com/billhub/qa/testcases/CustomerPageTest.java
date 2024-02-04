package com.billhub.qa.testcases;

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
		customerPage.validateSearchCustomerByName();
	}
	@Test
	public void SearchCustomerByCodeTest() throws InterruptedException {
		customerPage.validateSearchCustomerByCode();
	}

	@Test
	public void AddCustomerWithInvalidDataTest(){
		boolean isTestFailed = false;
		try {
			isTestFailed = customerPage.validateAddCustomerWithValidData(customerCode,customerName,customerPeriod,customerDrop);
			Assert.fail("Test should fail as invalid data is saved successfully.");
		} catch (Exception e) {
			// Catch any exceptions
		}
		Assert.assertTrue(isTestFailed, "Test passed as invalid data saved successfully.");
	}
	@Test
	public void AddCustomerWithValdiDataTest() throws InterruptedException {

		customerPage.validateAddCustomerWithValidData(customerName,customerCode,customerPeriod,customerDrop);
	}

	@Test
	public void CustomerAppearanceTest() throws InterruptedException {
		customerPage.validateCustomerInTable(customerName,customerCode,customerPeriod,customerDrop);
	}

	@Test
	public void AddCustomerWithoutDataTest() throws InterruptedException {
		customerPage.validateAddCustomerWithBlank();
	}
	@AfterMethod
	public void tearDown() {
		driver.close();						
	}

}

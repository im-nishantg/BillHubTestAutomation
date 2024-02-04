package com.billhub.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.CustomerPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;

public class CustomerPageTest extends TestBase{
	
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
	public void verifySearchCustomerByName() throws InterruptedException {
		customerPage.validateSearchCustomerByName();
	}
	@Test
	public void verifySearchCustomerByCode() throws InterruptedException {
		customerPage.validateSearchCustomerByCode();
	}
//	@Test
//	public void verifyUpdateByName() throws InterruptedException {
//		customerPage.validateUpdateByCustomerName();
//	}
	@Test
	public void verifyAddCustomerWithValidData() throws InterruptedException {
		customerPage.validateAddCustomerWithValidData();
	}

	@Test
	public void verifyCustomerAppearanceTest() throws InterruptedException {
		customerPage.validateCustomerInTable();
	}

	@Test
	public void verifyAddCustomerWithBlankTest() throws InterruptedException {
		customerPage.validateAddCustomerWithBlank();
	}
	@AfterMethod
	public void tearDown() {
		driver.close();						
	}

}

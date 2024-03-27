package com.billhub.qa.testcases;

import com.billhub.qa.pages.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;

public class LoginPageTest extends TestBase{

	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	AccountDashboardPage accountDashboardPage;
	TaxationDashboardPage taxationDashboardPage;
	BADashboardPage baDashboardPage;
	CommercialDashboardPage commercialDashboardPage;
	
	public LoginPageTest(){
		super();
	}

	@BeforeMethod
	public void setup(){
		initialization();
		loginPage= new LoginPage();
	}

	@Test(priority = 1)
	public void loginAsMdmTest(){
		mdmDashboardPage= loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
	}
	
	@Test(priority = 2)
	public void loginAsBATest(){
		baDashboardPage= loginPage.loginAsBa(prop.getProperty("BA_userId"),prop.getProperty("password"));
	}

	@Test(priority = 3)
	public void loginAsCommercialTest(){
		commercialDashboardPage=loginPage.loginAsCommercial(prop.getProperty("Commercial_userId"),prop.getProperty("password"));
	}

	@Test(priority = 4)
	public void loginAsAccountTest(){
		accountDashboardPage= loginPage.loginAsAccount(prop.getProperty("Account_userId"),prop.getProperty("password"));
	}

	@Test(priority = 5)
	public void loginAsTaxationTest(){
		taxationDashboardPage= loginPage.loginAsTaxation(prop.getProperty("Taxation_userId"),prop.getProperty("password"));
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}
	
}

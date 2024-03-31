package com.billhub.qa.testcases;

import com.billhub.qa.pages.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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

	@BeforeClass
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
		baDashboardPage= loginPage.loginAsBa(prop.getProperty("ba_userid_po"),prop.getProperty("ba_password_po"));
	}

	@Test(priority = 3)
	public void loginAsCommercialTest(){
		commercialDashboardPage=loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
	}

	@Test(priority = 4)
	public void loginAsAccountTest(){
		accountDashboardPage= loginPage.loginAsAccount(prop.getProperty("accounts_loginid_1022"),prop.getProperty("accounts_password_1022"));
	}

	@Test(priority = 5)
	public void loginAsTaxationTest(){
		taxationDashboardPage= loginPage.loginAsTaxation(prop.getProperty("taxation_loginid_1022"),prop.getProperty("taxation_password_1022"));
	}
	
	@AfterClass						
	public void tearDown() {
		driver.close();						
	}
	
}

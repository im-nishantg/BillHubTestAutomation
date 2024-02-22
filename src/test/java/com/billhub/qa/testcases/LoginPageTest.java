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

	@Test
	public void loginAsMdmTest() throws InterruptedException{
		mdmDashboardPage= loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
	}
	@Test
	public void loginAsBATest(){
		baDashboardPage= loginPage.loginAsBa(prop.getProperty("BA_userId"),prop.getProperty("password"));
	}

	@Test
	public void loginAsCommercialTest(){
		commercialDashboardPage=loginPage.loginAsCommercial(prop.getProperty("Commercial_userId"),prop.getProperty("password"));
	}

	@Test
	public void loginAsAccountTest(){
		accountDashboardPage= loginPage.loginAsAccount(prop.getProperty("Account_userId"),prop.getProperty("password"));
	}

	@Test
	public void loginAsTaxationTest(){
		taxationDashboardPage= loginPage.loginAsTaxation(prop.getProperty("Taxation_userId"),prop.getProperty("password"));
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}
	
}

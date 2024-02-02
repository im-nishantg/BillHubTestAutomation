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
	BaDashboardPage baDashboardPage;
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
	public void loginAsMdmTest(){
		mdmDashboardPage= loginPage.loginAsMdm(prop.getProperty("Mdm_userId"),prop.getProperty("password"));;
	}
	@Test
	public void loginAsBaTest(){
		baDashboardPage= loginPage.loginAsBa(prop.getProperty("Ba_userId"),prop.getProperty("password"));
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

package com.billhub.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.pages.BADashboard;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.base.TestBase;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	BADashboard baDashBoard;
	
	public LoginPageTest() {
		super();					
	}
	
	@BeforeMethod						
	public void setUp() {
		initialization();								
		loginPage = new LoginPage();												
	}
	
	@Test(priority=1)
	public void loginTest() {
		baDashBoard = loginPage.login(prop.getProperty("ba_po_userId"), prop.getProperty("ba_po_password"));
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}
	
}

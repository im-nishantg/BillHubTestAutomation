package com.billhub.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.ReasonPage;

public class ReasonPageTest extends TestBase{
	
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	ReasonPage reasonPage;
	
	public ReasonPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userId"),prop.getProperty("mdm_password"));
		reasonPage = mdmDashboardPage.clickOnReasonLink();
	}
	
	@Test
	public void addNewUserTest(){
		reasonPage.clickOnAddReasonBtn();
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}
}

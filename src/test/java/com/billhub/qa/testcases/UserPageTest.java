package com.billhub.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.CustomerPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.UserPage;

public class UserPageTest extends TestBase{
	
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	UserPage userPage;
	
	public UserPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException{
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("Mdm_userId"),prop.getProperty("password"));
		userPage = mdmDashboardPage.clickOnUserLink();
	}
	
	@Test
	public void addNewUserTest(){
		userPage.clickOnAddUserBtn();
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}

}

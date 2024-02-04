package com.billhub.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BAPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;

public class BAPageTest extends TestBase{
	
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	BAPage baPage;
	
	public BAPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException{
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("Mdm_userId"),prop.getProperty("password"));
		baPage = mdmDashboardPage.clickOnBaLink();
	}
	
	@Test
	public void addNewBaTest() throws InterruptedException  {
		baPage.clickOnAddBaBtn();
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}

}

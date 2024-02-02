package com.billhub.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.RolePage;

public class RolePageTest extends TestBase{
	
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	RolePage rolePage;
	
	public RolePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException{
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("Mdm_userId"),prop.getProperty("password"));
		rolePage = mdmDashboardPage.clickOnRoleLink();
	}
	
	@Test
	public void addNewRoleTest() throws InterruptedException{
		rolePage.clickOnAddRoleBtn();
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}
}

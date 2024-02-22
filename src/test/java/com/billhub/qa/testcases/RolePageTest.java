package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.RolePage;

import java.time.Duration;

public class RolePageTest extends TestBase{
	String roleName="TAX";
	String roleCode="MDM";
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
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
		Thread.sleep(Duration.ofSeconds(15).toMillis());
		rolePage = mdmDashboardPage.clickOnRoleLink();
	}
	
	@Test
	public void addRoleWithValidDataTest() throws InterruptedException {
		boolean result=rolePage.validateAddRoleWithValidData(roleCode,roleName);
		Assert.assertTrue(result,"Test failed!");
	}

	@Test
	public void addRoleWithInvalidDataTest() throws InterruptedException {
		boolean result = rolePage.validateAddRoleWithValidData(roleName, roleCode);
		Assert.assertFalse(result, "Test failed!");
	}

    @Test
		public void addRoleWithOutDataTest() throws InterruptedException{
		boolean result=rolePage.validateAddRoleWithValidData("","");
		Assert.assertFalse(result, "Test failed");

		}

	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}
}

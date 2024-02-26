package com.billhub.qa.testcases;

import com.billhub.qa.utils.TestUtils;
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
//	String roleName="20164014@";
//	String roleCode="suraj@";
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	RolePage rolePage;

	public Object[][] data = TestUtils.getTestData("Role");
	
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


	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}
}

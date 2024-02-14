package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
	
	@BeforeClass
	public void setup() throws InterruptedException{
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("Mdm_userId"),prop.getProperty("password"));
		baPage = mdmDashboardPage.clickOnBaLink();
	}
	
	@Test
	public void addNewBaWithValidDataTest() {
		boolean isAdded = baPage.addNewBA("427", "Nishant", "Maharashtra", "10", "2", "5", "420", "small", "nishant@gmail.com", "Nis", "1234567890");
		Assert.assertTrue(isAdded, "BA was not added.");
	}
	
	@Test
	public void addNewBaWithInvalidDataTest() {
		boolean isAdded = baPage.addNewBA("nish", "Nishant", "Maharashtra", "10", "2", "5", "420", "small", "nishant@gmail.com", "Nis", "1234567890");
		Assert.assertTrue(isAdded, "BA was not added.");
	}
	
	@Test
	public void addNewBaWithoutDataTest() {
		boolean isAdded = baPage.addNewBA("", "", "", "", "", "", "", "", "", "", "");
		Assert.assertFalse(isAdded, "BA was added without data.");
	}
	
	@AfterClass						
	public void tearDown() {
		driver.close();						
	}

}

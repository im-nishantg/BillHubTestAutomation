package com.billhub.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.WithholdingTaxPage;

public class WithholdingTaxPageTest extends TestBase{
	
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	WithholdingTaxPage withholdingTaxPage;
	
	public WithholdingTaxPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException{
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("Mdm_userId"),prop.getProperty("password"));
		withholdingTaxPage = mdmDashboardPage.clickOnWithholdingTaxLink();
	}
	
	@Test
	public void addNewWithholdingTaxTest(){
		withholdingTaxPage.clickOnWithholdingTaxBtn();;
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}

}

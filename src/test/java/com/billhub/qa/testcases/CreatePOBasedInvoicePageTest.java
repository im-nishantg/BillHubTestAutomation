package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.CreatePOBasedInvoicePage;
import com.billhub.qa.pages.LoginPage;


public class CreatePOBasedInvoicePageTest extends TestBase{

	LoginPage loginPage;
	BADashboardPage baDashboardPage;
	CreatePOBasedInvoicePage createPOBasedInvoicePage;
	
	public CreatePOBasedInvoicePageTest() {
		super();
	}
	
	@BeforeClass
	public void setup(){
		initialization();
		loginPage= new LoginPage();
		baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_po"),prop.getProperty("ba_password_po"));
		createPOBasedInvoicePage = baDashboardPage.createNewMemoPOBased();
	} 
	
	@Test
	public void createInvoiceTest(){
		createPOBasedInvoicePage.createInvoice();
	}
	@Test
	public void gstCodeVerificationTest(){
		String expected_amount="11.8";
		String actual_amount=createPOBasedInvoicePage.verifyGstCode();
		Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
	}
	@Test
	public void tdCodeVerificationTest(){
		String expected_amount="9";
		String actual_amount=createPOBasedInvoicePage.verifyTdCode();
		Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
	}
	@Test
	public void additionalAmountVerificationTest(){
		String expected_amount="11";
		String actual_amount=createPOBasedInvoicePage.verifyAdditionalAmount();
		Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}

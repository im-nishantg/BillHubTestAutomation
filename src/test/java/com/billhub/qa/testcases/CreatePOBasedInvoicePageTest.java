package com.billhub.qa.testcases;

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
		createPOBasedInvoicePage = baDashboardPage.createNewMemo();
	} 
	
	@Test
	public void createInvoiceTest(){
		createPOBasedInvoicePage.createInvoice();
	}
}

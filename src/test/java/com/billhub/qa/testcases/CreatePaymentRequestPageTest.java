package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.CreatePaymentRequestPage;
import com.billhub.qa.pages.LoginPage;
import org.testng.annotations.Test;


public class CreatePaymentRequestPageTest extends TestBase{
	
	LoginPage loginPage;
	CreatePaymentRequestPage createPaymentRequestPage;
//    Object[][] data=TestUtils.getTestData("CommercialDashboardPage");

    @BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid_for_po"),prop.getProperty("commercial_password_for_po"));
        createPaymentRequestPage = new CreatePaymentRequestPage();
    }
    
    

	// ************************  Tests associated with View Payment Request Page ************************
    
    @Test(priority = 1)
    public void searchInvoicesByValidBaNameTest()  {
    	
    	String ba_name = "anusha logi";
        boolean isDataDisplayed = createPaymentRequestPage.searchInvoicesByValidBaName(ba_name);
        Assert.assertTrue(isDataDisplayed, "Data was not displayed for valid BA name.");
    }
    
    @Test(priority = 2)
    public void searchInvoicesByInvalidBaNameTest()  {
    	
    	String ba_name = "Nishant Gore";
        boolean isDataDisplayed = createPaymentRequestPage.searchInvoicesByInvalidBaName(ba_name);
        Assert.assertFalse(isDataDisplayed, "Data was not displayed for Invalid BA name.");
    }
    
    @Test(priority = 3)
    public void validateExcelsheetDataTest()  {
    	
    	String ba_name = "anusha logi";
        boolean isDataDisplayed = createPaymentRequestPage.validateExcelsheetData(ba_name);
        Assert.assertTrue(isDataDisplayed, "Data was not displayed for valid BA name.");
    }
    

	// ************************  Test associated with View Payment Request Page ************************
    
    @Test(priority = 4)
    public void searchWithValidRequestNumberTest()  {
    	
    	String request_number = "MSAEN04/07708/Mar/2023-24";
        boolean isDataDisplayed = createPaymentRequestPage.searchWithValidRequestNumber(request_number);
        Assert.assertTrue(isDataDisplayed, "Data was not displayed for valid BA name.");
    }
    
}

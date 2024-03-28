package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.CreatePaymentRequestForPOPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.TestUtils;

import org.testng.annotations.Test;


public class CreatePaymentRequestForPOPageTest extends TestBase{
	
	LoginPage loginPage;
	CreatePaymentRequestForPOPage createPaymentRequestPage;
    Object[][] data;

    @BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid_for_po"),prop.getProperty("commercial_password_for_po"));
        createPaymentRequestPage = new CreatePaymentRequestForPOPage();
        data = TestUtils.getTestData("CreatePaymentRequestForPo");
    }
    
	// ************************  Tests associated with View Payment Request Page ************************
    
    @Test(priority = 1)
    public void searchInvoicesByValidBaNameTest()  {
    	
    	String ba_name = (String) data[0][0];
        boolean isDataDisplayed = createPaymentRequestPage.searchInvoicesByValidBaName(ba_name);
        Assert.assertTrue(isDataDisplayed, "Data was not displayed for valid BA name.");
    }
    
    @Test(priority = 2)
    public void searchInvoicesByInvalidBaNameTest()  {
    	
    	String ba_name = (String) data[1][0];
        boolean isDataDisplayed = createPaymentRequestPage.searchInvoicesByInvalidBaName(ba_name);
        Assert.assertFalse(isDataDisplayed, "Data was not displayed for Invalid BA name.");
    }
    
    @Test(priority = 3)
    public void validateExcelsheetDataTest(){		// Test for creating payment request and downloading excel file
    	
    	String ba_name = (String) data[0][0], invoice_number = (String) data[0][2];
        boolean isDataDisplayed = createPaymentRequestPage.validateExcelsheetData(ba_name, invoice_number);
        createPaymentRequestPage.readPaymentRequestNumber(invoice_number);
        Assert.assertTrue(isDataDisplayed, "Data was not displayed for valid BA name.");
    }
    
	// ************************  Test associated with View Payment Request Page ************************
    
    @Test(priority = 4)
    public void searchWithValidRequestNumberTest()  {
    	
    	String request_number = (String) data[0][3];
        boolean isDataDisplayed = createPaymentRequestPage.searchWithValidRequestNumber(request_number);
        Assert.assertTrue(isDataDisplayed, "Data was not displayed for valid request Number.");
    }
    
    @Test(priority = 5)
    public void searchWithInvalidRequestNumberTest()  {
    	
    	String request_number = (String) data[1][3];
        boolean isDataDisplayed = createPaymentRequestPage.searchWithInvalidRequestNumber(request_number);
        Assert.assertFalse(isDataDisplayed, "Data was displayed for Invalid request Number.");
    }
    
    @Test(priority = 6)
    public void ValidateDownloadPaymentRequestTest()  {
    	
    	data = TestUtils.getTestData("CreatePaymentRequestForPo");			// to get the updated request number
    	String request_number = (String) data[0][3];
        boolean isFileDownloaded= createPaymentRequestPage.ValidateDownloadPaymentRequest(request_number);
        Assert.assertTrue(isFileDownloaded, "Payment request was not downloaded.");
    }
    
    @Test(priority = 7)
    public void ValidateRejectPaymentRequestTest()  {
    	
    	String request_number = (String) data[2][3], reason = (String) data[2][4];
        boolean isPaymentRequestRejected= createPaymentRequestPage.ValidateRejectPaymentRequest(request_number, reason);
        Assert.assertTrue(isPaymentRequestRejected, "Payment request was not rejected.");
    }
    
    @AfterClass
	public void tearDown() {
		driver.close();
	}
}

package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.POBasedInvoiceValidationPage;

public class POBasedInvoiceValidationPageTest extends TestBase{
	
	LoginPage loginPage;
	POBasedInvoiceValidationPage poBasedInvoiceValidationPage;
	
	@BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
        poBasedInvoiceValidationPage = new POBasedInvoiceValidationPage();
    }
	
	@Test(priority = 1)
    public void ValidateSubmittedPoBasedInvoiceInDashboardTest()  {
        
        boolean areInvoicesDisplayed = poBasedInvoiceValidationPage.ValidateSubmittedPoBasedInvoiceInDashboard();
        Assert.assertTrue(areInvoicesDisplayed, "Invoices were not displayed after clicking on Load dashboard button.");
    }
	

	@Test(priority = 2)
    public void ValidateAcknowledgeTheMemoTest()  {
        
        boolean isMemoAcknowledged = poBasedInvoiceValidationPage.ValidateAcknowledgeTheMemo();
        Assert.assertTrue(isMemoAcknowledged, "Invoices were not displayed after clicking on Load dashboard button.");
    }
	
	@Test(priority = 3)
    public void ValidateReassignTheMemoTest()  {
        
        boolean isMemoReassigned = poBasedInvoiceValidationPage.ValidateReassignTheMemo();
        Assert.assertTrue(isMemoReassigned, "Memo was not reassigned.");
    }
	
	@Test(priority = 4)
    public void ValidateRejectTheMemoTest()  {
        
        boolean isMemoRejected = poBasedInvoiceValidationPage.ValidateRejectTheMemo();
        Assert.assertTrue(isMemoRejected, "Memo was not rejected.");
    }
	
	@Test(priority = 5)
    public void validateAcknowledgedStatusTest()  {
        
        String memo_status = poBasedInvoiceValidationPage.validateAcknowledgedMemoStatus();
        Assert.assertEquals(memo_status, "Acknowledged", "Memo status was not updated successfully.");
    }
	
	@Test(priority = 6)
    public void validateRejectedStatusTest()  {
        
        String memo_status = poBasedInvoiceValidationPage.validateRejectedMemoStatus();
        Assert.assertEquals(memo_status, "Rejected", "Memo status was not updated successfully.");
    }
}

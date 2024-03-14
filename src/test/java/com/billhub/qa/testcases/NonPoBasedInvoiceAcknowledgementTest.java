package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.NonPoBasedInvoiceAcknowledgementPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NonPoBasedInvoiceAcknowledgementTest  extends TestBase {
    LoginPage loginPage;
    NonPoBasedInvoiceAcknowledgementPage nonPoBasedInvoiceAcknowledgementPage;

    @BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
        nonPoBasedInvoiceAcknowledgementPage = new NonPoBasedInvoiceAcknowledgementPage();
    }

    @Test(priority = 1)
    public void ValidateSubmittedNonPoBasedInvoiceInDashboardTest()  {

        boolean areInvoicesDisplayed = nonPoBasedInvoiceAcknowledgementPage.ValidateSubmittedNonPoBasedInvoiceInDashboard();
        Assert.assertTrue(areInvoicesDisplayed, "Invoices were not displayed after clicking on Load dashboard button.");
    }

    @Test(priority = 2)
    public void ValidateAcknowledgeTheMemoTest()  {

        boolean areInvoicesDisplayed = nonPoBasedInvoiceAcknowledgementPage.ValidateAcknowledgeTheMemo();
        Assert.assertTrue(areInvoicesDisplayed, "Invoices were not displayed after clicking on Load dashboard button.");
    }

    @Test(priority = 3)
    public void ValidateReassignMemoTest(){

        String submit_from="";
        String submit_to="";
        boolean isAssign= nonPoBasedInvoiceAcknowledgementPage.ValidateReassignTheMemo(submit_from,submit_to);
        Assert.assertTrue(isAssign,"test fail");
    }

    @Test(priority = 4)
    public void ValidateRejectTheMemoTest()  {

        String reason = "Agreement not available";

        boolean isMemoRejected = nonPoBasedInvoiceAcknowledgementPage.ValidateRejectTheMemo(reason);
        Assert.assertTrue(isMemoRejected, "Memo was not rejected.");
    }
    @Test(priority = 5)
    public void validateAcknowledgedStatusTest()  {

        String memo_status = nonPoBasedInvoiceAcknowledgementPage.validateAcknowledgedMemoStatus();
        Assert.assertEquals(memo_status, "Acknowledged", "Memo status was not updated successfully.");
    }
    @Test(priority = 6)
    public void validateRejectedStatusTest()  {

        String memo_status = nonPoBasedInvoiceAcknowledgementPage.validateRejectedMemoStatus();
        Assert.assertEquals(memo_status, "Rejected", "Memo status was not updated successfully.");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}

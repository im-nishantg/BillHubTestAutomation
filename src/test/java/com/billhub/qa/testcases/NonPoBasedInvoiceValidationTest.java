package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.NonPoBasedInvoiceValidationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NonPoBasedInvoiceValidationTest extends TestBase {
    LoginPage loginPage;
    NonPoBasedInvoiceValidationPage nonPoBasedInvoiceValidationPage;

    @BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
        nonPoBasedInvoiceValidationPage = new NonPoBasedInvoiceValidationPage();
    }

    @Test(priority = 1)
    public void ValidateSubmittedNonPoBasedInvoiceInDashboardTest()  {

        boolean areInvoicesDisplayed = nonPoBasedInvoiceValidationPage.ValidateSubmittedNonPoBasedInvoiceInDashboard();
        Assert.assertTrue(areInvoicesDisplayed, "Invoices were not displayed after clicking on Load dashboard button.");
    }


    @Test(priority = 2)
    public void ValidateAcknowledgeTheMemoTest()  {

        boolean areInvoicesDisplayed = nonPoBasedInvoiceValidationPage.ValidateAcknowledgeTheMemo();
        Assert.assertTrue(areInvoicesDisplayed, "Invoices were not displayed after clicking on Load dashboard button.");
    }
    @Test(priority = 3)
    public void ValidateReassignMemoTest(){
        String submit_from="";
        String submit_to="";
        boolean isAssign= nonPoBasedInvoiceValidationPage.ValidateReassignMemo(submit_from,submit_to);
        Assert.assertTrue(isAssign,"test fail");
    }

}

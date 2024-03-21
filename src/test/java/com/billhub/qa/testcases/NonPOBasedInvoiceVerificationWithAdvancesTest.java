package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.NonPoBasedInvoiceVerificationWithAdvancesPage;
import com.billhub.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NonPOBasedInvoiceVerificationWithAdvancesTest extends TestBase {
    LoginPage loginPage;
    NonPoBasedInvoiceVerificationWithAdvancesPage nonPoBasedInvoiceVerificationWithAdvancesPage;
    public Object [][]data = TestUtils.getTestData("BTBasedInvoiceAcknowledgement");
    @BeforeClass
    public void setup(){

        initialization();
        loginPage=new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
        nonPoBasedInvoiceVerificationWithAdvancesPage= new NonPoBasedInvoiceVerificationWithAdvancesPage();
    }

    @Test(priority = 1)
    public void validateInvoiceVerificationWithAdvancesTest(){

        String memo_number = (String) data[2][0], assignment = (String) data[2][3], item_text = (String) data[2][4],hsn_code= TestUtils.numberToString(data[2][10]);
        String advance_doc_number=TestUtils.numberToString(data[2][11]), advance_amount=(String)data[2][12];
        boolean isAdvanceAdded= nonPoBasedInvoiceVerificationWithAdvancesPage.InvoiceVerificationWithAdvances(hsn_code,assignment,item_text,memo_number,advance_doc_number,advance_amount);
        Assert.assertTrue(isAdvanceAdded,"Advances not added");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}

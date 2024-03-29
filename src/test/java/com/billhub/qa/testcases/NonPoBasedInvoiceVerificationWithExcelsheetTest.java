package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.NonPoBasedInvoiceVerificationWithExcelsheetPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NonPoBasedInvoiceVerificationWithExcelsheetTest extends TestBase {
    
	LoginPage loginPage;
    NonPoBasedInvoiceVerificationWithExcelsheetPage nonPoBasedInvoiceVerificationWithExcelsheetPage;

    @BeforeClass
    public void setup(){
        initialization();
        loginPage=new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
        nonPoBasedInvoiceVerificationWithExcelsheetPage= new NonPoBasedInvoiceVerificationWithExcelsheetPage();
    }
    @Test(priority = 1)
    public void validateInvoiceVerificationWithExcelsheetTest(){
        String withholding_tax = "00-00-0%", item_text = "test", payment_term = "B002", assignment = "test";

        boolean isVerified = nonPoBasedInvoiceVerificationWithExcelsheetPage.invoiceVerificationWithExcelsheet(withholding_tax, item_text, payment_term, assignment);
        Assert.assertTrue(isVerified, "Invoice was not verified with valid data.");
    }

}

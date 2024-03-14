package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.NonPoBasedInvoiceVerificationPage;
import com.billhub.qa.pages.NonPoBasedInvoiceVerificationWithExcelsheetPage;
import com.billhub.qa.utils.TestUtils;
import org.bouncycastle.pqc.crypto.util.PQCOtherInfoGenerator;
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
    @Test
    public void validateInvoiceVerificationWithExcelsheetTest(){
        boolean isVerified= nonPoBasedInvoiceVerificationWithExcelsheetPage.invoiceVerificationWithExcelsheet();
        Assert.assertTrue(isVerified,"test fail");
    }

}

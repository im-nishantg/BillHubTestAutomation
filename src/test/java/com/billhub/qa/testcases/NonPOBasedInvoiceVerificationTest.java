package com.billhub.qa.testcases;

import com.beust.ah.A;
import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.NonPoBasedInvoiceVerificationPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NonPOBasedInvoiceVerificationTest extends TestBase {
    LoginPage loginPage;
    NonPoBasedInvoiceVerificationPage nonPoBasedInvoiceVerificationPage;
    @BeforeClass
    public void setup(){
        initialization();
        loginPage=new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
        nonPoBasedInvoiceVerificationPage= new NonPoBasedInvoiceVerificationPage();
    }

    @Test(priority = 1)
    public void invalidTaxCodeVerificationTest(){
        String hsn_code="996713";
        String tax_code="KF - 12% input IGST Deductible";
        String assignment="Test";
        String item_text="Test";

        boolean isVerified= nonPoBasedInvoiceVerificationPage.invalideTaxCode(hsn_code,tax_code,assignment,item_text);
        Assert.assertFalse(isVerified,"Invoice verified with invalide tax code");

    }
    @Test(priority = 2)
    public void invoiceFilePreviewTest(){
        boolean isFileDisplayed= nonPoBasedInvoiceVerificationPage.ValidateInvoiceFilePreview();
        Assert.assertTrue(isFileDisplayed,"Invice file preview is unavailable");
    }
    @Test(priority = 3)
    public void validateInvoiceVerificationWithInvalideDataTest(){
        String hsn_code="996711";
        String tax_code="V0 - 0% Tax";
        String assignment="Test";
        String item_text="Test";
        String expected_status="error";
        String actuat_status = nonPoBasedInvoiceVerificationPage.invoiceVerificationWithInvalideData(hsn_code,tax_code,assignment,item_text);
        Assert.assertEquals(actuat_status,expected_status,"Invoice verified with data");
    }


    @Test(priority = 4)
    public void invoiceRejectionTest(){
        String reject_reason="";
        boolean isRejected= nonPoBasedInvoiceVerificationPage.invoiceRejection(reject_reason);
        Assert.assertTrue(isRejected,"Invoice is not rejected");
    }
    @Test(priority = 5)
    public void invoiceVerificationTest(){
        String hsn_code="996713";
        String tax_code="KG - 18% Input  IGST Deductible";
        String assignment="Test";
        String item_text="Test";
        boolean isVerified= nonPoBasedInvoiceVerificationPage.invoiceVerification(hsn_code,tax_code,assignment,item_text);
        Assert.assertTrue(isVerified,"Invoice is not verified");
    }
    @Test(priority = 6)
    public void verifiedInvoiceStatusTest(){
        String expected_status="Verified";
        String actual_status= nonPoBasedInvoiceVerificationPage.validateVerifiedMemoStatus();
        Assert.assertEquals(actual_status,expected_status,"Invoice status has not changed to verified after verification");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}

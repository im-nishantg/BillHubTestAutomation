package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.NonPoBasedInvoiceVerificationWithExcelsheetPage;
import com.billhub.qa.utils.TestUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NonPoBasedInvoiceVerificationWithExcelsheetTest extends TestBase {
    
	LoginPage loginPage;
    NonPoBasedInvoiceVerificationWithExcelsheetPage nonPoBasedInvoiceVerificationWithExcelsheetPage;

    public Object[][] data;
    
    @BeforeClass
    public void setup(){
        initialization();
        loginPage=new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
        nonPoBasedInvoiceVerificationWithExcelsheetPage= new NonPoBasedInvoiceVerificationWithExcelsheetPage();
        data = TestUtils.getTestData("VerificationWithExcelsheetBT");
    }
    
//    @Test(priority = 1)
//    public void validateInvoiceVerificationWithExcelsheetTest(){
//        String withholding_tax = "00-00-0%", item_text = "test", payment_term = "B002", assignment = "test";
//
//        boolean isVerified = nonPoBasedInvoiceVerificationWithExcelsheetPage.invoiceVerificationWithExcelsheet(withholding_tax, item_text, payment_term, assignment);
//        Assert.assertTrue(isVerified, "Invoice was not verified with valid data.");
//    }
//
//    @Test(priority = 2)
//    public void validateVerifiedStatusTest()  {
//
//        String invoice_number = (String) data[0][1];
//
//        String memo_status = nonPoBasedInvoiceVerificationWithExcelsheetPage.validateVerifiedMemoStatus(invoice_number);
//        Assert.assertTrue(StringUtils.containsIgnoreCase(memo_status,"Verified"), "Memo status was not updated successfully.");
//    }

    @Test(priority = 3)
    public void validateInvoiceVerificationWithInvalidTaxCodeTest(){

        String memo_number = (String) data[1][0];
        String sheetPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\non_po_invoice_verification_with_invalid_tax_code.xlsx";

        boolean isErrorDisplayed = nonPoBasedInvoiceVerificationWithExcelsheetPage.invoiceVerificationWithInvalidTaxCode(memo_number, sheetPath);
        Assert.assertTrue(isErrorDisplayed, "Invoice was verified with invalid tax code.");
    }

    @Test(priority = 4)
    public void validateLogButtonFunctionalityTest(){

        boolean isErrorDisplayed = nonPoBasedInvoiceVerificationWithExcelsheetPage.validateLogButtonFunctionality();
        Assert.assertTrue(isErrorDisplayed, "Invoice was verified with invalid tax code.");
    }

    @Test(priority = 5)
    public void InvoiceVerificationWithDifferentDataInTwoSheetsTest(){

        String memo_number = (String) data[2][0];
        String sheetPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\non_po_invoice_verification_with_different_data_in_2_sheets.xlsx";

        boolean isErrorDisplayed = nonPoBasedInvoiceVerificationWithExcelsheetPage.InvoiceVerificationWithDifferentDataInTwoSheets(memo_number, sheetPath);
        Assert.assertTrue(isErrorDisplayed, "Invoice was verified with different data in two sheets.");
    }

    @Test(priority = 6)
    public void InvoiceVerificationWithInvalidDataTest(){

        String memo_number = (String) data[2][0];
        String sheetPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\non_po_invoice_verification_with_invalid_data.xlsx";

        boolean isErrorDisplayed = nonPoBasedInvoiceVerificationWithExcelsheetPage.invoiceVerificationWithInvalidData(memo_number, sheetPath);
        Assert.assertTrue(isErrorDisplayed, "Invoice was verified with Invalid data.");
    }


}

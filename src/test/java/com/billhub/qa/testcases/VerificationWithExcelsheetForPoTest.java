package com.billhub.qa.testcases;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.POBasedInvoiceAcknowledgementPage;
import com.billhub.qa.pages.VerificationWithExcelsheetForPoPage;
import com.billhub.qa.utils.TestUtils;

public class VerificationWithExcelsheetForPoTest extends TestBase{
	
	VerificationWithExcelsheetForPoPage verificationWithExcelsheetForPoPage;
	LoginPage loginPage;
	public Object[][] data;
	
	@BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid_for_po"),prop.getProperty("commercial_password_for_po"));
        verificationWithExcelsheetForPoPage = new VerificationWithExcelsheetForPoPage();
        data = TestUtils.getTestData("VerificationWithExcelsheetPO");
    }
	
	@Test(priority = 1)
    public void InvoiceVerificationWithValidDataTest(){
		
		String memo_number = (String) data[0][0], withholding_tax = (String) data[0][2] , item_text = (String) data[0][3], payment_term = (String) data[0][4], assignment = (String) data[0][5];
		
        boolean isVerified = verificationWithExcelsheetForPoPage.invoiceVerificationWithValidData(memo_number, withholding_tax, item_text, payment_term, assignment);
        Assert.assertTrue(isVerified, "Invoice was not verified with valid data.");
    }
	
	@Test(priority = 2)
    public void validateVerifiedStatusTest()  {
       
		String invoice_number = (String) data[0][1];
		
        String memo_status = verificationWithExcelsheetForPoPage.validateVerifiedMemoStatus(invoice_number);
        Assert.assertTrue(StringUtils.containsIgnoreCase(memo_status,"Verified"), "Memo status was not updated successfully.");
    }
	
	@Test(priority = 3)
    public void validateInvoiceVerificationWithInvalidTaxCodeTest(){
		
		String memo_number = (String) data[1][0];
		String sheetPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\Po_Invoice_verification_with_invalid_tax_code.xlsx";
	
        boolean isErrorDisplayed = verificationWithExcelsheetForPoPage.invoiceVerificationWithInvalidTaxCode(memo_number, sheetPath);
        Assert.assertTrue(isErrorDisplayed, "Invoice was verified with invalid tax code.");
    }
	
	@Test(priority = 4)
    public void validateLogButtonFunctionalityTest(){

        boolean isErrorDisplayed = verificationWithExcelsheetForPoPage.validateLogButtonFunctionality();
        Assert.assertTrue(isErrorDisplayed, "Invoice was verified with invalid tax code.");
    }
	
	@Test(priority = 5)
    public void InvoiceVerificationWithDifferentDataInTwoSheetsTest(){
		
		String memo_number = (String) data[2][0];
		String sheetPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\Po_invoice_verification_with_different_data_in_2_sheets.xlsx";
		
        boolean isErrorDisplayed = verificationWithExcelsheetForPoPage.InvoiceVerificationWithDifferentDataInTwoSheets(memo_number, sheetPath);
        Assert.assertTrue(isErrorDisplayed, "Invoice was verified with different data in two sheets.");
    }
	
	@Test(priority = 6)
    public void InvoiceVerificationWithInvalidDataTest(){
		
		String memo_number = (String) data[2][0];
		String sheetPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\Po_invoice_verification_with_invalid_data.xlsx";
		
        boolean isErrorDisplayed = verificationWithExcelsheetForPoPage.invoiceVerificationWithInvalidData(memo_number, sheetPath);
        Assert.assertTrue(isErrorDisplayed, "Invoice was verified with Invalid data.");
    }
	
	@AfterClass
    public void tearDown() {
        driver.close();
    }
}

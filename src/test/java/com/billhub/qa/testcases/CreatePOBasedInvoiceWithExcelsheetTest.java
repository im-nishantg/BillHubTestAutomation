package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.CreatePOBasedInvoiceWithExcelsheetPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.TestUtils;

public class CreatePOBasedInvoiceWithExcelsheetTest extends TestBase{

	LoginPage loginPage;
	BADashboardPage baDashboardPage;
	CreatePOBasedInvoiceWithExcelsheetPage createPOBasedInvoiceWithExcelsheetPage;
	public Object[][] data;
	public Object[][] memoData = TestUtils.getTestData("BADashboardPage");
	public static String SHEET_PATH_FOR_SINGLE_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\10005176_template_for_po_based_with_single_invoice.xlsx";
	public static String SHEET_PATH_FOR_MULTIPLE_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\10005176_template_for_po_based_with_multiple_invoices.xlsx";
	public static String SHEET_PATH_FOR_EMPTY_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\10005176_template_for_po_based_without_data.xlsx";
	
	public CreatePOBasedInvoiceWithExcelsheetTest() {
		super();
	}
	
	public void updateSingleInvoiceExcelSheet() {
		
		String invoice_number = "TESTINV"  + TestUtils.generateRandomNumber(5);
		String quantity = TestUtils.generateRandomNumber(1);
		String base_amount = quantity;
		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo", 0, 0, invoice_number);
		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Invoice_PO_Mapping", 0, 1, invoice_number);
		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo", 0, 3, base_amount);
		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo", 0, 0, invoice_number);
		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Invoice_PO_Mapping", 0, 2, invoice_number);
	}
	
//	public void updateMultipleInvoiceExcelSheet() {
//		
//		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, , 0, 0, );
//	}
	
	@BeforeClass
	public void setup(){
		
		String from_state = (String) memoData[0][0], to_state = (String) memoData[0][1];
		
		initialization();
//		updateSingleInvoiceExcelSheet();
		loginPage= new LoginPage();
		baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_po"),prop.getProperty("ba_password_po"));
		createPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoPOBasedWithExcelsheet(from_state, to_state);
		createPOBasedInvoiceWithExcelsheetPage.uploadExcelSheet(SHEET_PATH_FOR_SINGLE_INVOICE);
		data = TestUtils.readExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo");
	} 

	@Test(priority = 1)
	public void gstCodeVerificationTest(){
		
		String base_amount = (String) data[0][4], igst = (String) data[0][8];
		double expected_amount = Double.parseDouble(base_amount) + Double.parseDouble(igst);
		double actual_amount = createPOBasedInvoiceWithExcelsheetPage.verifyGstCode(base_amount, igst);
		Assert.assertEquals(actual_amount, expected_amount, "Test failed");
	}
	
	@Test(priority = 2)
	public void tdCodeVerificationTest(){
		
		String base_amount = (String) data[0][4], cd = (String) data[0][10];
		double expected_amount = Double.parseDouble(base_amount) - Double.parseDouble(cd);
		double actual_amount = createPOBasedInvoiceWithExcelsheetPage.verifyTdCode(base_amount, cd);
		Assert.assertEquals(actual_amount,expected_amount,"Test failed");
	}
	
	@Test(priority = 3)
	public void additionalAmountVerificationTest(){
		
		String base_amount = (String) data[0][4], tcs = (String) data[0][9];
		double expected_amount = Double.parseDouble(base_amount) + Double.parseDouble(tcs);
		double actual_amount = createPOBasedInvoiceWithExcelsheetPage.verifyAdditionalAmount(base_amount, tcs);
		Assert.assertEquals(actual_amount, expected_amount, "Test failed");
	}
	
	@Test(priority = 4)
	public void submitMemoWithValidDataTest(){
		
		String submitting_at = (String) memoData[0][2], submitting_to = (String) memoData[0][3];
		
		boolean isSubmitted = createPOBasedInvoiceWithExcelsheetPage.submitMemoWithValidData(submitting_at, submitting_to);
		Assert.assertTrue(isSubmitted, "Memo was not submitted");
	}
	
	@Test(priority = 5)
	public void submitMemoWithDuplicateDataTest(){
		
		boolean isSubmitted = createPOBasedInvoiceWithExcelsheetPage.submitMemoWithDuplicateData();
		Assert.assertTrue(isSubmitted, "Memo was submitted with duplicate data.");
	}
	
	@Test(priority = 6)
	public void submitMemoWithoutDataTest(){
		
		String from_state = (String) memoData[0][0], to_state = (String) memoData[0][1];
		createPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoPOBasedWithExcelsheet(from_state, to_state);
		
		boolean isSubmitted = createPOBasedInvoiceWithExcelsheetPage.submitMemoWithoutData(SHEET_PATH_FOR_EMPTY_INVOICE);
		Assert.assertFalse(isSubmitted, "Memo was submitted without data.");
	}

}

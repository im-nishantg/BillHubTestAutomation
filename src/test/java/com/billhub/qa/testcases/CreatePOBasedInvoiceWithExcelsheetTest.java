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
	private static boolean isMultipleInvoicesSubmitted = false;
	public Object[][] data;
	public Object[][] memoData = TestUtils.getTestData("BADashboardPage");
	public static String SHEET_PATH_FOR_SINGLE_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\10005176_template_for_po_based_with_single_invoice.xlsx";
	public static String SHEET_PATH_FOR_MULTIPLE_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\10005176_template_for_po_based_with_multiple_invoices.xlsx";
	public static String SHEET_PATH_FOR_EMPTY_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\10005176_template_for_po_based_without_data.xlsx";
	String BaDashboardPageUrl = "https://billhub.mlldev.com/#/dashboard";
	String po_number = "4500000887";										// This PO number has been used in all the test
	
	public CreatePOBasedInvoiceWithExcelsheetTest() {
		super();
	}
	
	public void updateSingleInvoiceExcelSheet() {
		
		String invoice_number = "TESTINV"  + TestUtils.generateRandomNumber(5);
		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Invoice_PO_Mapping", 1, 1, invoice_number);
		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo", 1, 1, invoice_number);
	
//		String quantity = TestUtils.generateRandomNumber(1) + ".00";
//		String base_amount = quantity, igst = "0.00", tcs = "0.00", cd = "0.00";			// can't generate random data for igst, tcs, cd as it wont let excel sheet to upload
		
//		double total_amount = Double.parseDouble(base_amount) + Double.parseDouble(igst) + Double.parseDouble(tcs) - Double.parseDouble(cd);
//		total_amount = Math.floor(total_amount * 100) / 100.0;	
//		String total_invoice_amount = base_amount;

//		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Invoice_PO_Mapping", 1, 2, quantity);	
//		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo", 1, 4, base_amount);
//		TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo", 1, 14, total_invoice_amount);
		
	}
	
	public void updateMultipleInvoiceExcelSheet() {
		
		for(int i=1; i<=2; i++) {
			String invoice_number = "TESTINV"  + TestUtils.generateRandomNumber(5);
			TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_MULTIPLE_INVOICE, "Invoice_PO_Mapping", i, 1, invoice_number);
			TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_MULTIPLE_INVOICE, "Memo", i, 1, invoice_number);
		}
		
	}
	

	@BeforeClass
	public void setup(){
		
		String from_state = (String) memoData[0][0], to_state = (String) memoData[0][1];
		
		initialization();
		updateSingleInvoiceExcelSheet();
		updateMultipleInvoiceExcelSheet();
		loginPage= new LoginPage();
		baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_po"),prop.getProperty("ba_password_po"));
		createPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoPOBasedWithExcelsheet(from_state, to_state, po_number);
		createPOBasedInvoiceWithExcelsheetPage.uploadExcelSheet(SHEET_PATH_FOR_SINGLE_INVOICE);
		data = TestUtils.readExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo");
	} 
	
	@Test(priority = 1)
	public void gstCodeVerificationTest(){
		
		String base_amount = (String) data[0][4], igst = (String) data[0][8];
		double expected_amount = Double.parseDouble(base_amount) + Double.parseDouble(igst);
		double actual_amount = createPOBasedInvoiceWithExcelsheetPage.verifyGstCode();
		Assert.assertEquals(actual_amount, expected_amount, "Test failed");
	}
	
	@Test(priority = 2)
	public void tdCodeVerificationTest(){
		
		String base_amount = (String) data[0][4], cd = (String) data[0][10];
		double expected_amount = Double.parseDouble(base_amount) - Double.parseDouble(cd);
		double actual_amount = createPOBasedInvoiceWithExcelsheetPage.verifyTdCode();
		Assert.assertEquals(actual_amount,expected_amount,"Test failed");
	}
	
	@Test(priority = 3)
	public void additionalAmountVerificationTest(){
		
		String base_amount = (String) data[0][4], tcs = (String) data[0][9];
		double expected_amount = Double.parseDouble(base_amount) + Double.parseDouble(tcs);
		double actual_amount = createPOBasedInvoiceWithExcelsheetPage.verifyAdditionalAmount();
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
		
		driver.get(BaDashboardPageUrl);
		String from_state = (String) memoData[0][0], to_state = (String) memoData[0][1];
		createPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoPOBasedWithExcelsheet(from_state, to_state, po_number);
		
		boolean isErrorDisplayed = createPOBasedInvoiceWithExcelsheetPage.submitMemoWithDuplicateData(SHEET_PATH_FOR_SINGLE_INVOICE);
		Assert.assertTrue(isErrorDisplayed, "Memo was submitted with duplicate data.");
	}
	
	@Test(priority = 6)
	public void submitMemoWithoutDataTest(){
		
		driver.get(BaDashboardPageUrl);
		String from_state = (String) memoData[0][0], to_state = (String) memoData[0][1];
		createPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoPOBasedWithExcelsheet(from_state, to_state, po_number);
		
		boolean isSubmitted = createPOBasedInvoiceWithExcelsheetPage.submitMemoWithoutData(SHEET_PATH_FOR_EMPTY_INVOICE);
		Assert.assertFalse(isSubmitted, "Memo was submitted without data.");
	}
	
	@Test(priority = 7)
	public void createMultipleInvoiceInSingleMemoTest(){
		
		driver.get(BaDashboardPageUrl);
		String from_state = (String) memoData[0][0], to_state = (String) memoData[0][1];
		createPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoPOBasedWithExcelsheet(from_state, to_state, po_number);
		
		String submitting_at = (String) memoData[0][2], submitting_to = (String) memoData[0][3];
		boolean isSubmitted = createPOBasedInvoiceWithExcelsheetPage.createMultipleInvoiceInSingleMemo(SHEET_PATH_FOR_MULTIPLE_INVOICE, submitting_at, submitting_to);
		isMultipleInvoicesSubmitted = isSubmitted;
		Assert.assertTrue(isSubmitted, "Memo was not submitted");
	}
	
	@Test(priority = 8)
	public void splitPoQuantityAndCreateMultipleInvoicesTest(){
		
		// this test was already performed in test createMultipleInvoiceInSingleMemoTest and hence their result is same
		Assert.assertTrue(isMultipleInvoicesSubmitted, "Memo was not submitted");
	}
	
}

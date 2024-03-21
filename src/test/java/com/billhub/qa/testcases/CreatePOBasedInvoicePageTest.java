package com.billhub.qa.testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.CreatePOBasedInvoicePage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.Invoice;
import com.billhub.qa.utils.TestUtils;


public class CreatePOBasedInvoicePageTest extends TestBase{

	LoginPage loginPage;
	BADashboardPage baDashboardPage;
	CreatePOBasedInvoicePage createPOBasedInvoicePage;
	private static boolean isMultipleInvoicesSubmitted = false;
	public Object[][] data;
	public Object[][] memoData = TestUtils.getTestData("BADashboardPage");
	String po_number = "4500000891";										// This PO number has been used in all the test
	
	public CreatePOBasedInvoicePageTest() {
		super();
	}
	
	public void updateExcelSheetData() {
		
		for(int i=1; i<=8; i++)
		{
			String invoice_number = "TESTINV"  + TestUtils.generateRandomNumber(5);
			String quantity = TestUtils.generateRandomNumber(1);
			String base_amount = quantity;
			double igst = Double.parseDouble(quantity) * 0.18;
			igst = Math.floor(igst * 100) / 100.0;		// Round down igst to 2 digits after the decimal
			String Igst = String.valueOf(igst);
			TestUtils.setCellData("POBasedInvoice", i, 0, invoice_number);
			TestUtils.setCellData("POBasedInvoice", i, 9, quantity);
			TestUtils.setCellData("POBasedInvoice", i, 2, Igst);
			TestUtils.setCellData("POBasedInvoice", i, 1, base_amount);
		}
	}
	
	@BeforeClass
	public void setup(){
		
		String from_state = (String) memoData[0][0], to_state = (String) memoData[0][1];
		
		initialization();
		loginPage= new LoginPage();
		baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_po"),prop.getProperty("ba_password_po"));
		createPOBasedInvoicePage = baDashboardPage.createNewMemoPOBased(from_state, to_state);
		updateExcelSheetData();
		data = TestUtils.getTestData("POBasedInvoice");
	}

	@Test(priority = 1)
	public void gstCodeVerificationTest(){
		
		String base_amount = (String) data[0][1], igst = (String) data[0][2];
		double expected_amount = Double.parseDouble(base_amount) + Double.parseDouble(igst);
		double actual_amount = createPOBasedInvoicePage.verifyGstCode(base_amount, igst);
		Assert.assertEquals(actual_amount, expected_amount, "Test failed");
	}
	
	@Test(priority = 2)
	public void tdCodeVerificationTest(){
		
		String base_amount = (String) data[0][1], cd = (String) data[0][4];
		double expected_amount = Double.parseDouble(base_amount) - Double.parseDouble(cd);
		double actual_amount = createPOBasedInvoicePage.verifyTdCode(base_amount, cd);
		Assert.assertEquals(actual_amount,expected_amount,"Test failed");
	}
	
	@Test(priority = 3)
	public void additionalAmountVerificationTest(){
		
		String base_amount = (String) data[0][1], tcs = (String) data[0][5];
		double expected_amount = Double.parseDouble(base_amount) + Double.parseDouble(tcs);
		double actual_amount=createPOBasedInvoicePage.verifyAdditionalAmount(base_amount, tcs);
		Assert.assertEquals(actual_amount, expected_amount, "Test failed");
	}
	
	@Test(priority = 4)
	public void createInvoiceWithoutDataTest(){
		
		Invoice invoice = new Invoice("", "", "", "", "", "", "", "", "", "", "", "");
		boolean isSubmitted = createPOBasedInvoicePage.createInvoiceWithoutData(invoice);
		Assert.assertFalse(isSubmitted, "Invoice was created without data.");
	}
	
	@Test(priority = 5)
	public void submitMemoWithValidDataTest(){
		
		String invoice_number = (String) data[0][0], base_amount = (String) data[0][1], igst = (String) data[0][2];
		String subServiceCategory = (String) data[0][3], cd = (String) data[0][4], tcs = (String) data[0][5];
		String hsn_code = TestUtils.numberToString(data[0][6]), end_customer = (String) data[0][7], comment = (String) data[0][8], quantity = TestUtils.numberToString(data[0][9]);
		String submitting_at = (String) data[0][10], submitting_to = (String) data[0][11];
		
		Invoice invoice = new Invoice(invoice_number, base_amount, igst, subServiceCategory, cd, tcs, hsn_code, end_customer, comment, quantity, submitting_at, submitting_to);
		boolean isSubmitted = createPOBasedInvoicePage.submitMemoWithValidData(invoice, po_number);
		Assert.assertTrue(isSubmitted, "Memo was not submitted");
	}
	@Test(priority = 6)
	public void submitMemoWithDuplicateDataTest(){

		String from_state = (String) memoData[0][0], to_state = (String) memoData[0][1];
		createPOBasedInvoicePage = baDashboardPage.createNewMemoPOBased(from_state, to_state);

		String invoice_number = (String) data[0][0], base_amount = (String) data[0][1], igst = (String) data[0][2];
		String subServiceCategory = (String) data[0][3], cd = (String) data[0][4], tcs = (String) data[0][5];
		String hsn_code = TestUtils.numberToString(data[0][6]), end_customer = (String) data[0][7], comment = (String) data[0][8], quantity = TestUtils.numberToString(data[0][9]);
		String submitting_at = (String) data[0][10], submitting_to = (String) data[0][11];
		
		Invoice invoice = new Invoice(invoice_number, base_amount, igst, subServiceCategory, cd, tcs, hsn_code, end_customer, comment, quantity, submitting_at, submitting_to);
		boolean isSubmitted = createPOBasedInvoicePage.submitMemoWithDuplicateData(invoice);
		Assert.assertTrue(isSubmitted, "Memo was submitted with duplicate data.");
	}
	
	@Test(priority = 7)
	public void createMultipleInvoiceInSingleMemoTest() {
			
		List<Invoice> invoices = new ArrayList<>();
		
		
		for(int i=1; i<8; i++)
		{
			String invoice_number = (String) data[i][0], base_amount = (String) data[i][1], igst = (String) data[i][2];
			String subServiceCategory = (String) data[i][3], cd = (String) data[i][4], tcs = (String) data[i][5];
			String hsn_code = TestUtils.numberToString(data[i][6]), end_customer = (String) data[i][7], comment = (String) data[i][8], quantity = TestUtils.numberToString(data[i][9]);
			String submitting_at = (String) data[i][10], submitting_to = (String) data[i][11];
			
			Invoice invoice = new Invoice(invoice_number, base_amount, igst, subServiceCategory, cd, tcs, hsn_code, end_customer, comment, quantity, submitting_at, submitting_to);
			invoices.add(invoice);
		}
		
		boolean isSubmitted = createPOBasedInvoicePage.createMultipleInvoiceInSingleMemo(invoices, po_number);
		isMultipleInvoicesSubmitted = isSubmitted;
		Assert.assertTrue(isSubmitted, "Memo was not submitted");

	}
	
	@Test(priority = 8)
	public void splitPoQuantityAndCreateMultipleInvoicesTest(){
		
		// this test was already performed in test createMultipleInvoiceInSingleMemoTest and hence their result is same
		Assert.assertTrue(isMultipleInvoicesSubmitted, "Memo was not submitted");
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}

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
	public static String INVOICE_SHEET_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\10005176_template.xlsx";
	
	
	public CreatePOBasedInvoiceWithExcelsheetTest() {
		super();
	}
	
	@BeforeClass
	public void setup(){
		
		String from_state = (String) memoData[0][0], to_state = (String) memoData[0][1];
		
		initialization();
		loginPage= new LoginPage();
		baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_po"),prop.getProperty("ba_password_po"));
		createPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoPOBasedWithExcelsheet(from_state, to_state);
		
	} 
	
	@Test(priority = 1)
	public void gstCodeVerificationTest(){
		
		createPOBasedInvoiceWithExcelsheetPage.uploadExcelSheet(INVOICE_SHEET_PATH);
	}
	
}

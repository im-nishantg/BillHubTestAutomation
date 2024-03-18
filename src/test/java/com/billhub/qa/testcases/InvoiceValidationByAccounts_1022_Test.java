package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.CommercialDashboardPage;
import com.billhub.qa.pages.InvoiceValidationByAccounts_1022_Page;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.TestUtils;


public class InvoiceValidationByAccounts_1022_Test extends TestBase{
	
	LoginPage loginPage;
	InvoiceValidationByAccounts_1022_Page invoiceValidationPage;
	public Object[][] data = TestUtils.getTestData("InvoiceValidationAccounts_1022");
	
	public InvoiceValidationByAccounts_1022_Test() {
		super();
	}
	
	@BeforeClass
	public void setup(){
		
		initialization();
		loginPage= new LoginPage();
		loginPage.loginAsAccount(prop.getProperty("accounts_loginid_1022"),prop.getProperty("accounts_password_1022"));
		invoiceValidationPage = new InvoiceValidationByAccounts_1022_Page();
	}
	
	@Test(priority = 1)
	public void searchInvoicesWithMultipleFieldsTest(){
		
		String location = (String) data[0][0], commercial_name = (String) data[0][1];
		
		boolean isDataDisplayed = invoiceValidationPage.searchInvoicesWithMultipleFields(location, commercial_name);
		Assert.assertTrue(isDataDisplayed, "No data was displayed after filtering invoices with location and commercial name.");
	}
	
	@Test(priority = 2)
	public void searchInvoicesWithInvalidData(){
		
		String location = (String) data[1][0], commercial_name = (String) data[0][1];
		
		boolean isDataDisplayed = invoiceValidationPage.searchInvoicesWithInvalidData(location, commercial_name);
		Assert.assertFalse(isDataDisplayed, "Data was displayed for invalid filter options.");
	}
	
	@Test(priority = 3)
	public void validateAllInvoicesTest(){
		
		boolean isDataDisplayed = invoiceValidationPage.validateAllInvoices();
		Assert.assertTrue(isDataDisplayed, "All invoices were not displayed.");
	}
	
	@Test(priority = 4)
	public void validatePendingInvoicesTest(){
		
		boolean isDataDisplayed = invoiceValidationPage.validatePendingInvoices();
		Assert.assertTrue(isDataDisplayed, "Pending invoices were not displayed.");
	}
	
	@Test(priority = 5)
	public void validateExportButtonTest(){
		
		String location = (String) data[0][0], commercial_name = (String) data[0][1];
		
		boolean isFileDownloaded = invoiceValidationPage.validateExportButton(location, commercial_name);
		Assert.assertTrue(isFileDownloaded, "File was not downloaded.");
	}
	
	@Test(priority = 6)
	public void validateDownloadButtonTest(){
		
		String location = (String) data[0][0], commercial_name = (String) data[0][1];
		
		boolean isFileDownloaded = invoiceValidationPage.validateDownloadButton(location, commercial_name);
		Assert.assertTrue(isFileDownloaded, "File was not downloaded.");
	}
	
	@Test(priority = 7)
	public void validateCorrectInvoicesTest(){
		
		String location = (String) data[0][0], commercial_name = (String) data[0][1];
		
		boolean isInvoiceCorrected = invoiceValidationPage.validateCorrectInvoices(location, commercial_name);
		Assert.assertTrue(isInvoiceCorrected, "Invoice was not updated.");
	}
	
	@Test(priority = 8)
	public void validateBlanketApproveTest(){
		
		String location = (String) data[0][0], commercial_name = (String) data[0][1];
		
		boolean isInvoiceCorrected = invoiceValidationPage.validateBlanketApprove(location, commercial_name);
		Assert.assertTrue(isInvoiceCorrected, "Invoice was not updated.");
	}
	
	@Test(priority = 9)
	public void validateRaiseQueryTest(){
		
		String location = (String) data[0][0], commercial_name = (String) data[0][1], reason = (String) data[0][2];
		
		boolean isInvoiceUpdated = invoiceValidationPage.validateRaiseQuery(location, commercial_name, reason);
		Assert.assertTrue(isInvoiceUpdated, "Invoice was not updated.");
	}
}

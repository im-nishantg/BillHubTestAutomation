package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.TaxCodePage;
import com.billhub.qa.utils.TestUtils;


public class TaxCodePageTest extends TestBase{
	
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	TaxCodePage taxCodePage;
	
	public Object[][] data = TestUtils.getTestData("TaxCode");
	
	public TaxCodePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup(){
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
		taxCodePage = mdmDashboardPage.clickOnTaxCodeLink();
	}
	
	@Test(priority = 1)
	public void addNewTaxCodeWithValidDataTest(){
		
		String tax_code = (String) data[0][0], tax_percent = TestUtils.numberToString(data[0][1]), desc = (String) data[0][2];
		
		boolean isAdded = taxCodePage.addNewTaxCodeWithValidData(tax_code, tax_percent, desc);
		Assert.assertTrue(isAdded, "Tax Code was not added");
	}
	
	@Test(priority = 2)
	public void addNewTaxCodeWithInvalidDataTest(){
		
		String tax_code = (String) data[1][0], tax_percent = (String) (data[1][1]), desc = (String) data[1][2];
		
		boolean isAdded = taxCodePage.addNewTaxCodeWithValidData(tax_code, tax_percent, desc);
		Assert.assertFalse(isAdded, "Tax Code was added with invalid data.");
	}
	
	@Test(priority = 3)
	public void addNewTaxCodeWithoutDataTest(){
		
		boolean isAdded = taxCodePage.addNewTaxCodeWithValidData("", "", "");
		Assert.assertTrue(isAdded, "Expected error popup box found none.");
	}
	
	@Test(priority = 4)
	public void addNewTaxCodeWithDuplicateDataTest(){
		
		String tax_code = (String) data[0][0], tax_percent = TestUtils.numberToString(data[0][1]), desc = (String) data[0][2];
		
		boolean isAdded = taxCodePage.addNewTaxCodeWithDuplicateData(tax_code, tax_percent, desc);
		Assert.assertFalse(isAdded, "Duplicate data was added.");
	}
	
	@Test(priority = 5)
	public void updateUserTest(){
		
		String tax_code = (String) data[0][0], tax_percent = TestUtils.numberToString(data[2][1]), desc = (String) data[2][2];
		boolean isUpdated = taxCodePage.updateTaxCode(tax_code, tax_percent, desc);	
		Assert.assertTrue(isUpdated, "Tax Code was not updated.");
	}
	
	@Test(priority = 6)
	public void searchByTaxCodeTest(){
		
		String tax_code = (String) data[0][0];
		boolean isPresent = taxCodePage.searchByTaxCode(tax_code);
		Assert.assertTrue(isPresent, "Tax code is present but not found on searching.");
	}
	
	@Test(priority = 7)
	public void searchByTaxPercentageTest(){
		
		String tax_percent = TestUtils.numberToString(data[0][1]);
		boolean isPresent = taxCodePage.searchByTaxPercentage(tax_percent);
		Assert.assertTrue(isPresent, "Tax code is present but not found on searching.");
	}
	
	@Test(priority = 8)
	public void validateAddedTaxCodeInTheDatabaseTest(){
		
		String tax_code = (String) data[0][0];
		boolean isPresent = taxCodePage.validateAddedTaxCodeInTheDatabase(tax_code);
		Assert.assertTrue(isPresent, "Tax code is present but not found on searching.");
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}
}

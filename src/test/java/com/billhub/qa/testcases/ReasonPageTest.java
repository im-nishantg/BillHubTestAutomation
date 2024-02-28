package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.ReasonPage;
import com.billhub.qa.utils.TestUtils;

public class ReasonPageTest extends TestBase{
	
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	ReasonPage reasonPage;
	
	public Object[][] data;
	
	public ReasonPageTest() {
		super();
	}
	
	public void updateExcelSheetData() {
		
		String random_reason_name_first = "test_" + TestUtils.generateRandomString(7);  
		String random_reason_name_second = "test_updated_" + TestUtils.generateRandomString(5);
		String random_reason_code_first = "testcode_" +  TestUtils.generateRandomString(4);
		
		TestUtils.setCellData("Reason", 1, 1, random_reason_name_first);
		TestUtils.setCellData("Reason", 3, 1, random_reason_name_second);
		TestUtils.setCellData("Reason", 1, 0, random_reason_code_first);
	}

	@BeforeClass
	public void setup(){
		
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
		reasonPage = mdmDashboardPage.clickOnReasonLink();
		updateExcelSheetData();
		data = TestUtils.getTestData("Reason");
	}
	
	@Test(priority = 1)
	public void addNewReasonWithValidDataTest(){
		
		String reason_code = (String) data[0][0], reason_name = (String) data[0][1], type = (String) data[0][2];
		boolean isAdded = reasonPage.addNewReasonWithValidData(reason_code, reason_name, type);
		Assert.assertTrue(isAdded, "Reason was not added");
	}
	
	@Test(priority = 2)
	public void addNewReasonWithInvalidDataTest(){
		
		String reason_code = (String) data[1][0], reason_name = (String) data[1][1], type = (String) data[1][2];
		boolean isAdded = reasonPage.addNewReasonWithInvalidData(reason_code, reason_name, type);
		Assert.assertFalse(isAdded, "Reason was not added");
	}
	
	@Test(priority = 3)
	public void addNewReasonWithoutDataTest(){

		boolean isAdded = reasonPage.addNewReasonWithoutData("", "", "");
		Assert.assertFalse(isAdded, "Expected error popup box found none.");
	}
	
	@Test(priority = 4)
	public void addNewReasonWithDuplicateDataTest(){
		
		String reason_code = (String) data[0][0], reason_name = (String) data[0][1], type = (String) data[0][2];
		boolean isAdded = reasonPage.addNewReasonWithDuplicateData(reason_code, reason_name, type);
		Assert.assertFalse(isAdded, "Duplicate data was added.");
	}
	
	@Test(priority = 5)
	public void updateReasonTest(){
		
		String reason_code = (String) data[0][0], reason_name = (String) data[2][1], type = (String) data[2][2];
		boolean isUpdated = reasonPage.updateReason(reason_code, reason_name, type);
		Assert.assertTrue(isUpdated, "Reason was not added.");
	}
	
	@Test(priority = 6)
	public void searchByReasonNameTest(){
		
		String reason_name = (String) data[0][1];
		boolean isPresent = reasonPage.searchByReasonName(reason_name);
		Assert.assertTrue(isPresent, "Reason is present but not found on searching.");
	}
	
	@Test(priority = 7)
	public void searchByReasonCodeTest(){
		
		String reason_code = (String) data[0][0];
		boolean isPresent = reasonPage.searchByReasonCode(reason_code);
		Assert.assertTrue(isPresent, "Reason is present but not found on searching.");
	}
	
	
	@AfterClass				
	public void tearDown() {
		driver.close();						
	}
}

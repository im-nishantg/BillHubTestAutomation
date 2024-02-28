package com.billhub.qa.testcases;

import com.billhub.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.RolePage;

import java.time.Duration;

public class RolePageTest extends TestBase{
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	RolePage rolePage;

	public Object[][] data;
	
	public RolePageTest() {
		super();
	}
	public void updateExcelSheetData() {
		String random_Role_code= TestUtils.generateRandomNumber(6);
		TestUtils.setCellData("Role", 1, 1, random_Role_code);
	}
	
	@BeforeClass
	public void setup(){
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
		rolePage = mdmDashboardPage.clickOnRoleLink();
		updateExcelSheetData();
		data=TestUtils.getTestData("Role");
	}
	@Test(priority = 1)
	public void AddRoleWithValidDataTest(){
		String roleName=(String) data[0][0], roleCode=TestUtils.numberToString(data[0][1]);
		boolean result= rolePage.addNewRoleWithValidData(roleCode,roleName);
		Assert.assertTrue(result,"Test failed");
	}

	@Test(priority = 2)
	public void ValidateRoleInDatabaseTest(){
		String roleCode=TestUtils.numberToString(data[0][1]);
		boolean isPresent = rolePage.validateAddedRoleInTheDatabase(roleCode);
		Assert.assertTrue(isPresent, "Role is present but not found in the database.");
	}
	@Test(priority = 3)
	public void AddRoleWithInvalidDataTest(){
		String roleName=(String) data[1][0], roleCode=(String)data[1][1];
		boolean isTestFailed = rolePage.addNewRoleWithInvalidData(roleCode,roleName);
		Assert.assertFalse(isTestFailed, "Test failed as invalid data saved successfully.");
	}
	@Test(priority = 4)
	public void addNewRoleWithDuplicateDataTest() {
		String role_name = (String) data[0][0];
		String role_code = TestUtils.numberToString(data[0][1]);

		boolean isAdded = rolePage.addNewRoleWithDuplicateData(role_code, role_name);
		Assert.assertFalse(isAdded, "Role was added with duplicate data.");
	}
	@Test(priority = 5)
	public void AddRoleWithoutDataTest(){
		boolean result=rolePage.addNewRoleWithoutData("","");
		Assert.assertFalse(result,"Test failed as it has saved with blank data");
	}
	@Test(priority = 6)
	public void updateRoleTest(){

		String role_code = TestUtils.numberToString(data[0][1]);
		String role_name = (String) data[0][0];
		boolean isUpdated = rolePage.updateRole(role_code, role_name);
		Assert.assertTrue(isUpdated, "Role was not updated.");
	}
	@Test(priority = 7)
	public void SearchRoleByNameTest(){
		String roleName=(String) data[0][0];
		boolean result = rolePage.searchRoleByName(roleName);
		Assert.assertTrue(result, "Search Customer By Name Test Failed");
	}
	@Test(priority = 8)
	public void SearchRoleByCodeTest(){
		String roleCode=TestUtils.numberToString(data[0][1]);
		boolean result = rolePage.searchRoleByCode(roleCode);
		Assert.assertTrue(result, "Search Customer By code Test Failed");
	}
	@AfterClass
	public void tearDown() {
		driver.close();						
	}
}

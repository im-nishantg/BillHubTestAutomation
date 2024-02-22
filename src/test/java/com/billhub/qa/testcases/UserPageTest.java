package com.billhub.qa.testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.CustomerPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.UserPage;
import com.billhub.qa.utils.TestUtils;

public class UserPageTest extends TestBase{
	
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	UserPage userPage;
	
	public Object[][] data = TestUtils.getTestData("User");
	
	public UserPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup(){
		
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
		userPage = mdmDashboardPage.clickOnUserLink();
	}
	
	@Test(priority = 1)
	public void addNewUserWithValidDataTest(){
		
		String user_name = (String) data[0][0], ba_group_id = TestUtils.numberToString(data[0][1]), first_name = (String) data[0][2];
		String last_name = (String) data[0][3], role_name = (String) data[0][4], email = (String) data[0][5];
		
		boolean isAdded = userPage.addNewUserWithValidData(user_name, ba_group_id, first_name, last_name, role_name, email);	
		Assert.assertTrue(isAdded, "User was not added.");
	}
	
	@Test(priority = 2)
	public void addNewUserWithInactiveStatusTest(){
		
		String user_name = (String) data[1][0], ba_group_id = TestUtils.numberToString(data[1][1]), first_name = (String) data[1][2];
		String last_name = (String) data[1][3], role_name = (String) data[1][4], email = (String) data[1][5];
		
		boolean isAdded = userPage.addNewUserWithInactiveStatus(user_name, ba_group_id, first_name, last_name, role_name, email);	
		Assert.assertTrue(isAdded, "User was not added.");
	}
	
	@Test(priority = 3)
	public void addNewUserWithInvalidDataTest(){
		
		String user_name = (String) data[2][0], ba_group_id = (String) data[2][1], first_name = (String) data[2][2];
		String last_name = (String) data[2][3], role_name = (String) data[2][4], email = (String) data[2][5];
		
		boolean isAdded = userPage.addNewUserWithInvalidData(user_name, ba_group_id, first_name, last_name, role_name, email);	
		Assert.assertFalse(isAdded, "User was not added.");
	}
	
	@Test(priority = 4)
	public void addNewUserWithDuplicateDataTest(){
		
		String user_name = (String) data[0][0], ba_group_id = TestUtils.numberToString(data[0][1]), first_name = (String) data[0][2];
		String last_name = (String) data[0][3], role_name = (String) data[0][4], email = (String) data[0][5];
		
		boolean isAdded = userPage.addNewUserWithDuplicateData(user_name, ba_group_id, first_name, last_name, role_name, email);	
		Assert.assertFalse(isAdded, "User was not added.");
	}
	
	@Test(priority = 5)
	public void updateUserTest(){
		
		String ba_group_id = TestUtils.numberToString(data[0][1]), first_name = (String) data[1][2], last_name = (String) data[1][3];
		boolean isUpdated = userPage.updateUser(ba_group_id, first_name, last_name);	
		Assert.assertTrue(isUpdated, "User was not updated.");
	}
	
	@Test(priority = 6)
	public void addNewUserWithActiveStatusTest(){
		
		String ba_group_id = TestUtils.numberToString(data[0][1]);
		String isActive = userPage.addNewUserWithActiveStatus(ba_group_id);
		Assert.assertEquals(isActive, "Active", "User with active status was not added or not found in database upon searching.");
	}
	
	@Test(priority = 7)
	public void validateAddedUserInTheDatabaseTest() {
		
		String ba_group_id = TestUtils.numberToString(data[0][1]);
		boolean isPresent = userPage.validateAddedUserInTheDatabase(ba_group_id);
		Assert.assertTrue(isPresent, "User is present but not found in the database.");
	}
	
	@Test(priority = 8)
	public void addNewUserWithoutDataTest() {
		
		boolean isAdded = userPage.addNewUserWithoutData("", "", "", "", "", "");
		Assert.assertTrue(isAdded, "Expected error popup box found none.");
	}
	
	@Test(priority = 9)
	public void searchUserByNameTest() {
		
		String user_name = (String) data[0][0];
		boolean isPresent = userPage.searchUserByName(user_name);
		Assert.assertTrue(isPresent, "User is present but not found on searching");
	}
	
	@Test(priority = 10)
	public void searchUserByBAGroupIDTest() {
		
		String ba_group_id = TestUtils.numberToString(data[0][1]);
		boolean isPresent = userPage.searchUserByBAGroupID(ba_group_id);
		Assert.assertTrue(isPresent, "User is present but not found on searching");
	}
	
	@AfterMethod						
	public void tearDown() {
		driver.close();						
	}

}

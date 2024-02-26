package com.billhub.qa.testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
	
	public Object[][] data;
	
	public UserPageTest() {
		super();
	}
	
	public void updateExcelSheetData() {
		
		String random_user_name_first = "test_" + TestUtils.generateRandomString(6);  
		String random_user_name_second = "test_" + TestUtils.generateRandomString(6); 
		String random_BA_groupid_first = TestUtils.generateRandomNumber(6);
		String random_BA_groupid_second = TestUtils.generateRandomNumber(6);
		TestUtils.setCellData("User", 1, 0, random_user_name_first);
		TestUtils.setCellData("User", 2, 0, random_user_name_second);
		TestUtils.setCellData("User", 1, 1, random_BA_groupid_first);
		TestUtils.setCellData("User", 2, 1, random_BA_groupid_second);
	}
	
	@BeforeClass
	public void setup() throws InterruptedException{
		
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
<<<<<<< HEAD
		Thread.sleep(Duration.ofSeconds(20).toMillis());
=======
		Thread.sleep(Duration.ofSeconds(20));
>>>>>>> 985a5c04dd50f1457f1834a556d1b56239239775
		userPage = mdmDashboardPage.clickOnUserLink();
		updateExcelSheetData();
		data = TestUtils.getTestData("User");
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
	public void addNewUserWithActiveStatusTest(){
		
		String ba_group_id = TestUtils.numberToString(data[0][1]);
		String isActive = userPage.addNewUserWithActiveStatus(ba_group_id);
		Assert.assertEquals(isActive, "Active", "User with active status was not added or not found in database on searching.");
	}
	
	@Test(priority = 4)
	public void validateAddedUserInTheDatabaseTest() {
		
		String ba_group_id = TestUtils.numberToString(data[0][1]);
		boolean isPresent = userPage.validateAddedUserInTheDatabase(ba_group_id);
		Assert.assertTrue(isPresent, "User is present but not found in the database.");
	}
	
	@Test(priority = 5)
	public void addNewUserWithInvalidDataTest(){
		
		String user_name = (String) data[2][0], ba_group_id = (String) data[2][1], first_name = (String) data[2][2];
		String last_name = (String) data[2][3], role_name = (String) data[2][4], email = (String) data[2][5];
		
		boolean isAdded = userPage.addNewUserWithInvalidData(user_name, ba_group_id, first_name, last_name, role_name, email);	
		Assert.assertFalse(isAdded, "User was added with invalid data.");
	}
	
	@Test(priority = 6)
	public void addNewUserWithDuplicateDataTest(){
		
		String user_name = (String) data[0][0], ba_group_id = TestUtils.numberToString(data[0][1]), first_name = (String) data[0][2];
		String last_name = (String) data[0][3], role_name = (String) data[0][4], email = (String) data[0][5];
		
		boolean isAdded = userPage.addNewUserWithDuplicateData(user_name, ba_group_id, first_name, last_name, role_name, email);	
		Assert.assertFalse(isAdded, "User was added with duplicate data.");
	}
	
	@Test(priority = 7)
	public void addNewUserWithoutDataTest() {
		
		boolean isAdded = userPage.addNewUserWithoutData("", "", "", "", "", "");
		Assert.assertFalse(isAdded, "User was added without data.");
	}
	
	@Test(priority = 8)
	public void updateUserTest(){
		
		String ba_group_id = TestUtils.numberToString(data[0][1]), first_name = (String) data[1][2], last_name = (String) data[1][3];
		boolean isUpdated = userPage.updateUser(ba_group_id, first_name, last_name);	
		Assert.assertTrue(isUpdated, "User was not updated.");
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
	
	@AfterClass						
	public void tearDown() {
		driver.close();						
	}

}

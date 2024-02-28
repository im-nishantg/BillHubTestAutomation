package com.billhub.qa.testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BAPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.utils.TestUtils;

public class BAPageTest extends TestBase{
	
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	BAPage baPage;
	
	public Object[][] data;
	
	public BAPageTest() {
		super();
	}
	
	public void updateExcelSheetData() {
		
		String random_BA_name_first = "test_" + TestUtils.generateRandomString(6);  
		String random_BA_name_second = "test_" + TestUtils.generateRandomString(6); 
		String random_BA_code_first = TestUtils.generateRandomNumber(6);
		String random_BA_code_second = TestUtils.generateRandomNumber(6);
		TestUtils.setCellData("BA", 1, 1, random_BA_name_first);
		TestUtils.setCellData("BA", 3, 1, random_BA_name_second);
		TestUtils.setCellData("BA", 1, 0, random_BA_code_first);
		TestUtils.setCellData("BA", 3, 0, random_BA_code_second);
	}
	
	@BeforeClass
	public void setup() throws InterruptedException{
		
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
		Thread.sleep(Duration.ofSeconds(20).toMillis());

		baPage = mdmDashboardPage.clickOnBaLink();
		updateExcelSheetData();
		data = TestUtils.getTestData("BA");
	}
	
	@Test(priority = 1)
	public void addNewBaWithValidDataTest() {
		
		String ba_name = (String) data[0][1], state = (String) data[0][2], msmed = (String) data[0][7];
		String email = (String) data[0][8], contact_person_name = (String) data[0][9];
		String ba_code = TestUtils.numberToString(data[0][0]), trd_disc = TestUtils.numberToString(data[0][3]);
		String credit_period = TestUtils.numberToString(data[0][4]), td_credit_period = TestUtils.numberToString(data[0][5]);
		String ba_groupcode = TestUtils.numberToString(data[0][6]), contact_person_number = TestUtils.numberToString(data[0][10]);
		
		boolean isAdded = baPage.addNewBAWithValidData(ba_code, ba_name, state , trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		Assert.assertTrue(isAdded, "BA was not added.");
	}
	
	@Test(priority = 2)
	public void addNewBAWithInactiveStatusTest() {
		
		String ba_name = (String) data[2][1], state = (String) data[2][2], msmed = (String) data[2][7];
		String email = (String) data[2][8], contact_person_name = (String) data[2][9];
		String ba_code = TestUtils.numberToString(data[2][0]), trd_disc = TestUtils.numberToString(data[2][3]);
		String credit_period = TestUtils.numberToString(data[2][4]), td_credit_period = TestUtils.numberToString(data[2][5]);
		String ba_groupcode = TestUtils.numberToString(data[2][6]), contact_person_number = TestUtils.numberToString(data[2][10]);
		
		boolean isAdded = baPage.addNewBAWithInactiveStatus(ba_code, ba_name, state , trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		Assert.assertTrue(isAdded, "BA was not added.");
	}
	
	@Test(priority = 3)
	public void addNewBAWithActiveStatusTest() {
		
		String ba_code = TestUtils.numberToString(data[0][0]);
		String isActive = baPage.addNewBAWithActiveStatus(ba_code);
		Assert.assertEquals(isActive, "Active", "BA with active status was not added or not found in database on searching.");
	}
	
	@Test(priority = 4)
	public void validateAddedBAInTheDatabaseTest() {
		
		String ba_code = TestUtils.numberToString(data[0][0]);
		boolean isPresent = baPage.validateAddedBAInTheDatabase(ba_code);
		Assert.assertTrue(isPresent, "BA is present but not found in the database.");
	}
	
	@Test(priority = 5)
	public void addNewBaWithInvalidDataTest() {
		
		String ba_name = (String) data[1][1], state = (String) data[0][2], msmed = (String) data[1][7];
		String email = (String) data[1][8], contact_person_name = (String) data[1][9];
		String ba_code = TestUtils.numberToString(data[1][0]), trd_disc = TestUtils.numberToString(data[1][3]);
		String credit_period = TestUtils.numberToString(data[1][4]), td_credit_period = TestUtils.numberToString(data[1][5]);
		String ba_groupcode = TestUtils.numberToString(data[1][6]), contact_person_number = TestUtils.numberToString(data[1][10]);
		
		boolean isAdded = baPage.addNewBAWithInvalidData(ba_code, ba_name, state , trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		Assert.assertFalse(isAdded, "BA was added with invalid data.");
	}

	@Test(priority = 6)
	public void addNewBAWithDuplicateDataTest() {
		
		String ba_name = (String) data[0][1], state = (String) data[0][2], msmed = (String) data[0][7];
		String email = (String) data[0][8], contact_person_name = (String) data[0][9];
		String ba_code = TestUtils.numberToString(data[0][0]), trd_disc = TestUtils.numberToString(data[0][3]);
		String credit_period = TestUtils.numberToString(data[0][4]), td_credit_period = TestUtils.numberToString(data[0][5]);
		String ba_groupcode = TestUtils.numberToString(data[0][6]), contact_person_number = TestUtils.numberToString(data[0][10]);
		
		boolean isAdded = baPage.addNewBAWithDuplicateData(ba_code, ba_name, state , trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		Assert.assertFalse(isAdded, "BA was added without data.");
	}
	
	@Test(priority = 7)
	public void addNewBaWithoutDataTest() {
		
		boolean isAdded = baPage.addNewBAWithoutData("", "", "", "", "", "", "", "", "", "", "");
		Assert.assertFalse(isAdded, "BA was added without data.");
	}
	
	@Test(priority = 8)
	public void updateBATest(){
		
		String ba_code = TestUtils.numberToString(data[1][0]), contact_person_number = TestUtils.numberToString(data[0][10]);;
		String ba_name = (String) data[1][1];
		boolean isUpdated = baPage.updateBA(ba_code, ba_name, contact_person_number);
		Assert.assertTrue(isUpdated, "BA was not updated.");
	}

	@Test(priority = 9)
	public void searchBAByNameTest() {
		
		String ba_name = (String) data[0][1];
		boolean isPresent = baPage.searchBAByName(ba_name);
		Assert.assertTrue(isPresent, "BA is present but not found on searching");
	}
	
	@Test(priority = 10)
	public void searchBAByCodeTest() {
		
		String ba_code = TestUtils.numberToString(data[0][0]);
		boolean isPresent = baPage.searchBAByCode(ba_code);
		Assert.assertTrue(isPresent, "BA is present but not found on searching");
	}
	
	@AfterClass				
	public void tearDown() {
		driver.close();						
	}

}

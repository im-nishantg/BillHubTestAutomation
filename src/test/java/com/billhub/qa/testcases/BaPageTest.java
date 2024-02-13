package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
	
	@BeforeMethod
	public void setup(){
		
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
		baPage = mdmDashboardPage.clickOnBaLink();
		data = TestUtils.getTestData("BA");
	}
	
	@Test
	public void addNewBaWithValidDataTest() {
		
		String ba_name = (String) data[0][1], state = (String) data[0][2], msmed = (String) data[0][7];
		String email = (String) data[0][8], contact_person_name = (String) data[0][9];
		String ba_code = TestUtils.numberToString(data[0][0]), trd_disc = TestUtils.numberToString(data[0][3]);
		String credit_period = TestUtils.numberToString(data[0][4]), td_credit_period = TestUtils.numberToString(data[0][5]);
		String ba_groupcode = TestUtils.numberToString(data[0][6]), contact_person_number = TestUtils.numberToString(data[0][10]);
		
		boolean isAdded = baPage.addNewBAWithValidData(ba_code, ba_name, state , trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		Assert.assertTrue(isAdded, "BA was not added.");
	}
	
	@Test
	public void addNewBaWithInvalidDataTest() {
		
		String ba_name = (String) data[1][1], state = (String) data[0][2], msmed = (String) data[1][7];
		String email = (String) data[1][8], contact_person_name = (String) data[1][9];
		String ba_code = TestUtils.numberToString(data[1][0]), trd_disc = TestUtils.numberToString(data[1][3]);
		String credit_period = TestUtils.numberToString(data[1][4]), td_credit_period = TestUtils.numberToString(data[1][5]);
		String ba_groupcode = TestUtils.numberToString(data[1][6]), contact_person_number = TestUtils.numberToString(data[1][10]);
		
		boolean isAdded = baPage.addNewBAWithInvalidData(ba_code, ba_name, state , trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		Assert.assertFalse(isAdded, "BA was not added.");
	}
	
	@Test
	public void addNewBaWithoutDataTest() {
		
		boolean isAdded = baPage.addNewBAWithoutData("", "", "", "", "", "", "", "", "", "", "");
		Assert.assertTrue(isAdded, "Expected error popup box found none.");
	}
	
	@Test
	public void addNewBAWithDuplicateDataTest() {
		
		String ba_name = (String) data[0][1], state = (String) data[0][2], msmed = (String) data[0][7];
		String email = (String) data[0][8], contact_person_name = (String) data[0][9];
		String ba_code = TestUtils.numberToString(data[0][0]), trd_disc = TestUtils.numberToString(data[0][3]);
		String credit_period = TestUtils.numberToString(data[0][4]), td_credit_period = TestUtils.numberToString(data[0][5]);
		String ba_groupcode = TestUtils.numberToString(data[0][6]), contact_person_number = TestUtils.numberToString(data[0][10]);
		
		boolean isAdded = baPage.addNewBAWithDuplicateData(ba_code, ba_name, state , trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		Assert.assertFalse(isAdded, "BA was added without data.");
	}
	
	@Test
	public void addNewBAWithInactiveStatusTest() {
		
		String ba_name = (String) data[2][1], state = (String) data[2][2], msmed = (String) data[2][7];
		String email = (String) data[2][8], contact_person_name = (String) data[2][9];
		String ba_code = TestUtils.numberToString(data[2][0]), trd_disc = TestUtils.numberToString(data[2][3]);
		String credit_period = TestUtils.numberToString(data[2][4]), td_credit_period = TestUtils.numberToString(data[2][5]);
		String ba_groupcode = TestUtils.numberToString(data[2][6]), contact_person_number = TestUtils.numberToString(data[2][10]);
		
		boolean isAdded = baPage.addNewBAWithInactiveStatus(ba_code, ba_name, state , trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		Assert.assertTrue(isAdded, "BA was not added.");
	}
	
	@Test
	public void addNewBAWithActiveStatusTest() {
		
		String ba_code = TestUtils.numberToString(data[0][0]);
		boolean isPresent = baPage.addNewBAWithActiveStatus(ba_code);
		Assert.assertTrue(isPresent, "BA with active status was not added or not found in database upon searching.");
	}
	
	@Test
	public void searchBAByNameTest() {
		
		String ba_name = (String) data[0][1];
		boolean isPresent = baPage.searchBAByName(ba_name);
		Assert.assertTrue(isPresent, "BA is present but not found on searching");
	}
	
	@Test
	public void searchBAByCodeTest() {
		
		String ba_code = TestUtils.numberToString(data[0][0]);
		System.out.println(ba_code);
		boolean isPresent = baPage.searchBAByCode(ba_code);
		Assert.assertTrue(isPresent, "BA is present but not found on searching");
	}
	
	@Test
	public void updateBA(){
		
		String ba_code = TestUtils.numberToString(data[2][0]), contact_person_number = TestUtils.numberToString(data[0][10]);;
		String ba_name = (String) data[0][1];
		boolean isUpdated = baPage.updateBA(ba_code, ba_name, contact_person_number);
		Assert.assertTrue(isUpdated, "BA was not updated.");
	}
	
	@Test 
	public void validateAddedBAInTheDatabaseTest() {
		
		String ba_code = TestUtils.numberToString(data[0][0]);
		boolean isPresent = baPage.validateAddedBAInTheDatabase(ba_code);
		Assert.assertTrue(isPresent, "BA is present but not found in the database.");
	}
	
	@AfterMethod				
	public void tearDown() {
		driver.close();						
	}

}

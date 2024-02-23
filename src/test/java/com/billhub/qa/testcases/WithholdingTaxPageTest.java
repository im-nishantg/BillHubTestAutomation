package com.billhub.qa.testcases;

import com.billhub.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.MdmDashboardPage;
import com.billhub.qa.pages.WithholdingTaxPage;

import java.time.Duration;

public class WithholdingTaxPageTest extends TestBase{
	LoginPage loginPage;
	MdmDashboardPage mdmDashboardPage;
	WithholdingTaxPage withholdingTaxPage;
	public Object[][] data= TestUtils.getTestData("WithholdingTax");
	String tax_Type=(String) data[0][0], tax_Code=TestUtils.numberToString(data[0][1]), tax_Rate=(String) data[0][2], tax_Description=(String) data[0][3];
	public WithholdingTaxPageTest() {
		super();
	}
	@BeforeClass
	public void setup() throws InterruptedException {
		initialization();
		loginPage= new LoginPage();
		mdmDashboardPage = loginPage.loginAsMdm(prop.getProperty("mdm_userid"),prop.getProperty("mdm_password"));
		Thread.sleep(Duration.ofSeconds(20).toMillis());
		withholdingTaxPage = mdmDashboardPage.clickOnWithholdingTaxLink();
	}


	@Test(priority = 1)
	public void AddTaxWithValidDataTest(){
		boolean result=withholdingTaxPage.validateAddTaxWithValidData(tax_Type,tax_Code,tax_Rate,tax_Description);
		Assert.assertTrue(result,"Withholding tax add failed");
	}
//	@Test(priority = 2)
//	public void AddTaxWithInvalidDataTest() throws InterruptedException {
//		// for this test, use same function of validation with invalide data
//		boolean result=withholdingTaxPage.validateAddTaxWithValidData(taxCode,taxRate,taxType,taxDescription);
//		Assert.assertFalse(result,"Test failed!: Withholding Tax has been added successfully with invalide data");
//	}
//	@Test(priority = 3)
//	public void SearchWithholdingTaxByRate() throws InterruptedException {
//		boolean result=withholdingTaxPage.validateSearchWithholdingTax(taxType);
//		Assert.assertTrue(result,"Test failed!: Search functionality is not working properly");
//	}
//	@Test(priority = 4)
//	public  void withholdingTaxApperanceTest() throws InterruptedException {
//		boolean result=withholdingTaxPage.validateWithholdingTaxInTable(taxType,taxCode,taxRate,taxDescription);
//		Assert.assertTrue(result,"Test failed!: No such tax record exist in table");
//	}
//	@Test(priority = 5)
//	public void AddTaxWithBlankDataTest() throws InterruptedException {
//		boolean result=withholdingTaxPage.validateAddTaxWithBlankData();
//		Assert.assertFalse(result,"Test failed!: Withholding tax added with blank data");
//	}
//	@Test(priority = 6)
//	public void duplicateWithholdingTaxAddTest() throws InterruptedException {
//		// pass duplicate credentials in arguments
//		boolean result=withholdingTaxPage.validateDuplicateWithholdingData(taxType,taxCode,taxRate,taxDescription);
//		Assert.assertFalse(result,"Test Failed!: duplicate credentials has been saved");
//	}
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}


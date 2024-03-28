package com.billhub.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.pages.POBasedInvoiceAcknowledgementPage;
import com.billhub.qa.pages.Temp;

public class TempTest extends TestBase{
	
	Temp temp;
	LoginPage loginPage;


	@BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid_for_po"),prop.getProperty("commercial_password_for_po"));
        temp = new Temp();
    }
	
	@Test
    public void validateInvoiceVerificationWithExcelsheetTest(){
		
		String withholding_tax = "00-00-0%", item_text = "test", payment_term = "B002", assignment = "test";
		
        boolean isVerified = temp.invoiceVerificationWithExcelsheet(withholding_tax, item_text, payment_term, assignment);
        Assert.assertTrue(isVerified,"test fail");
    }
}

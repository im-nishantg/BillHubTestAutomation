package com.billhub.qa.testcases;

import org.testng.annotations.BeforeClass;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.CommercialDashboardPage;
import com.billhub.qa.pages.CreatePaymentRequestPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.TestUtils;

public class CreatePaymentRequestPageTest extends TestBase{
	
	LoginPage loginPage;
	CreatePaymentRequestPage createPaymentRequestPage;
//    Object[][] data=TestUtils.getTestData("CommercialDashboardPage");

    @BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid_for_po"),prop.getProperty("commercial_password_for_po"));
        createPaymentRequestPage = new CreatePaymentRequestPage();
    }
    
    
}

package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.CreateNonPOBasedInvoicePage;
import com.billhub.qa.pages.CreatePOBasedInvoicePage;
import com.billhub.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateNonPOBasedInvoicePageTest extends TestBase {
    LoginPage loginPage;
    BADashboardPage baDashboardPage;
    CreateNonPOBasedInvoicePage createNonPOBasedInvoicePage;

    public CreateNonPOBasedInvoicePageTest(){
        super();
    }

    @BeforeClass
    public void setup(){
        initialization();
        loginPage= new LoginPage();
        baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_bt"),prop.getProperty("ba_password_bt"));
        createNonPOBasedInvoicePage = baDashboardPage.createNewBTBased();
    }

    @Test
    public void gstCodeVerificationTest(){
        String expected_amount="11.8";
        String actual_amount= createNonPOBasedInvoicePage.verifyGstCode();
        Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
    }
    @Test
    public void tdCodeVerificationTest(){
        String expected_amount="9";
        String actual_amount=createNonPOBasedInvoicePage.verifyTdCode();
        Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
    }

    @Test
    public void validateAdditionatAmountTest(){
        String expected_amount="11";
        String actual_amount= createNonPOBasedInvoicePage.verifyAdditionalAmount();
        Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
    }
    @Test
    public void validateCreateInvWithoutDataTest(){
        boolean isFailed= createNonPOBasedInvoicePage.CreateInvoiceWithoutData();
        Assert.assertTrue(isFailed,"Test failed!");
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}



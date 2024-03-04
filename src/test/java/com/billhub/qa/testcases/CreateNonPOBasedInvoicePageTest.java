package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.CreateNonPOBasedInvoicePage;
import com.billhub.qa.pages.CreatePOBasedInvoicePage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateNonPOBasedInvoicePageTest extends TestBase {
    LoginPage loginPage;
    BADashboardPage baDashboardPage;
    CreateNonPOBasedInvoicePage createNonPOBasedInvoicePage;
    public Object[][] data;
    public Object[][] memoData= TestUtils.getTestData("BADashboardPage");


    public CreateNonPOBasedInvoicePageTest(){
        super();
    }

    @BeforeClass
    public void setup(){
        String from_state = (String) memoData[2][0], to_state = (String) memoData[2][1];
        initialization();
        loginPage= new LoginPage();
        baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_bt"),prop.getProperty("ba_password_bt"));
        createNonPOBasedInvoicePage = baDashboardPage.createNewBTBased();
        data=TestUtils.getTestData("BTBasedInvoice");
    }

    @Test
    public void gstCodeVerificationTest(){
        String base_amount = (String) data[0][1], igst = (String) data[0][2];
        double expected_amount=Double.parseDouble(base_amount)+Double.parseDouble(igst);
        double actual_amount= createNonPOBasedInvoicePage.verifyGstCode(base_amount,igst);
        Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
    }
    @Test
    public void tdCodeVerificationTest(){
        String base_amount = (String) data[0][1], cd = (String) data[0][4];
        double expected_amount=Double.parseDouble(base_amount)-Double.parseDouble(cd);
        double actual_amount=createNonPOBasedInvoicePage.verifyTdCode(base_amount,cd);
        Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
    }

    @Test
    public void additionatAmountVerificationTest(){
        String base_amount = (String) data[0][1], tcs = (String) data[0][5];
        double expected_amount=Double.parseDouble(base_amount)+Double.parseDouble(tcs);
        double actual_amount= createNonPOBasedInvoicePage.verifyAdditionalAmount(base_amount,tcs);
        Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
    }
    @Test
    public void createInvWithoutDataTest(){
        String invoice_number = (String) data[0][0],subServiceCategory = (String) data[0][3],end_customer = (String) data[0][7], comment = (String) data[0][8];
        boolean isFailed= createNonPOBasedInvoicePage.CreateInvoiceWithoutData(invoice_number,subServiceCategory,end_customer,comment);
        Assert.assertTrue(isFailed,"Test failed!");
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}



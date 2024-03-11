package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.CommercialDashboardPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommercialDashboardPageTest extends TestBase  {
    LoginPage loginPage;

    CommercialDashboardPage commercialDashboardPage;
    Object[][] data=TestUtils.getTestData("CommercialDashboardPage");

    @BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        commercialDashboardPage = loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
    }

    @Test(priority = 1)
    public void filterByCompanyTest()  {
        String company_name=TestUtils.numberToString(data[0][0]);
        boolean result=commercialDashboardPage.filterByCompany(company_name);
        Assert.assertTrue(result, "Filter By Company not working");
    }

    @Test(priority = 2)
    public void filterByServiceTest()  {
        String service_name=(String) data[0][1];
        boolean result=commercialDashboardPage.filterByService(service_name);
        Assert.assertTrue(result, "Filter By Service not working");
    }

    @Test(priority = 3)
    public void filterByCompanyAndLocationTest(){
        String company_name=TestUtils.numberToString(data[0][0]),location_name=(String) data[0][2];
        boolean result=commercialDashboardPage.filterByCompanyAndLocation(company_name,location_name);
        Assert.assertTrue(result,"Filter By Company and Location  Not Working");
    }

    @Test(priority = 4)
    public void filterByLocationTest()  {
        String location_name=(String) data[0][2];
        boolean result=commercialDashboardPage.filterByLocation(location_name);
        Assert.assertTrue(result, "Filter By Location not working");
    }

    @Test(priority = 5)
    public void filterByPoTest()  {
        boolean result=commercialDashboardPage.filterByPo();
        Assert.assertTrue(result, "Filter By Po option not working");
    }

    @Test(priority = 6)
    public void filterByNonPoTest()  {
        boolean result=commercialDashboardPage.filterByNonPo();
        Assert.assertTrue(result, "Filter By NonPo option not working");
    }
    @Test(priority = 7)
    public void filterByBothTest()  {
        boolean result=commercialDashboardPage.filterByBoth();
        Assert.assertTrue(result, "Filter By Po and NonPo option both not working");
    }

    @Test(priority = 8)
    public void filterByInvoiceServiceAndLocationTest()  {
        String invoice_number=(String) data[0][3],service_name=(String) data[0][1],location_name=(String) data[0][2];
        boolean result=commercialDashboardPage.filterByInvoiceServiceAndLocation(invoice_number,service_name,location_name);
        Assert.assertTrue(result, "Filter By Invoice,Service And Location not working");
    }

    @Test(priority = 9)
    public void filterByCompanyServiceAndMemoTest()  {
        String company_name=(String) data[0][0],service_name=(String) data[0][1],memo_number=(String) data[0][4];

        boolean result=commercialDashboardPage.filterByCompanyServiceAndMemo(company_name,service_name,memo_number);
        Assert.assertTrue(result, "Filter By Company, Service And Memo not working");
    }




    @AfterClass
    public void tearDown() {
        driver.close();
    }
}

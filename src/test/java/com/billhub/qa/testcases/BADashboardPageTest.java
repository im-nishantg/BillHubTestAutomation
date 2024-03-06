package com.billhub.qa.testcases;


import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BADashboardPageTest extends TestBase {
    LoginPage loginPage;

    BADashboardPage baDashboardPage;


    @BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_po"),prop.getProperty("ba_password_po"));
    }

    @Test(priority = 1)
    public void filterByCompanyTest()  {
        String company_name="1022";
        boolean result=baDashboardPage.filterByCompany(company_name);
        Assert.assertTrue(result, "Campany not working");
    }

    @Test(priority = 2)
    public void filterByServiceTest()  {
        String service_name="Cargo";
        boolean result=baDashboardPage.filterByService(service_name);
        Assert.assertTrue(result, "Service not working");
    }

    @Test(priority = 3)
    public void filterByCompanyAndLocationTest(){
        String location_name="Ahmedabad";
        boolean result=baDashboardPage.filterByCompanyAndLocation(location_name);
        Assert.assertTrue(result,"CompanyLocation Not Working");
    }

    @Test(priority = 4)
    public void filterByLocationTest()  {
        String location_name="Ahmedabad";
        boolean result=baDashboardPage.filterByLocation(location_name);
        Assert.assertTrue(result, "Location not working");
    }

    @Test(priority = 5)
    public void filterByPoTest()  {
        boolean result=baDashboardPage.filterByPo();
        Assert.assertTrue(result, "PoFilter not working");
    }


    @Test(priority = 6)
    public void filterByBothTest()  {
        boolean result=baDashboardPage.filterByBoth();
        Assert.assertTrue(result, "Both not working");
    }



    @Test(priority = 7)
    public void filterByCompanyServiceAndMemoTest()  {
        String company="1022";
        String service="Cargo";
        String memo_num="30005631-2023-24-00009";
        boolean result=baDashboardPage.filterByCompanyServiceAndMemo(company,service,memo_num);
        Assert.assertTrue(result, "CompanyServiceAndMemo not working");
    }

    @Test(priority = 8)
    public void filterByInvoiceServiceAndLocationTest()  {
        String invo_num="TESTINV07707";
        String service="Cargo";
        String loc="Ahemdabad";
        boolean result=baDashboardPage.filterByInvoiceServiceAndLocation(invo_num,service,loc);
        Assert.assertTrue(result, "InvoiceServiceAndLocation not working");
    }


    @AfterClass
    public void tearDown() {
        driver.close();
    }
}

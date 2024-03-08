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

    public Object[][] data = TestUtils.getTestData("BADashboardPage");
    @BeforeClass
    public void setup(){

        initialization();
        loginPage = new LoginPage();
        baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_po"),prop.getProperty("ba_password_po"));
    }

    @Test(priority = 1)
    public void filterByCompanyTest()  {

        String company_name = TestUtils.numberToString(data[0][4]);

        boolean result = baDashboardPage.filterByCompany(company_name);
        Assert.assertTrue(result, "Filter by Company is not working.");
    }

    @Test(priority = 2)
    public void filterByServiceTest()  {

        String service_name = (String) data[0][5];

        boolean result = baDashboardPage.filterByService(service_name);
        Assert.assertTrue(result, "Filter by Service is not working.");
    }

    @Test(priority = 3)
    public void filterByCompanyAndLocationTest(){

        String location_name = (String) data[0][8];

        boolean result = baDashboardPage.filterByCompanyAndLocation(location_name);
        Assert.assertTrue(result,"Filter by Company and Location is not working.");
    }

    @Test(priority = 4)
    public void filterByLocationTest()  {

        String location_name = (String) data[0][8];

        boolean result = baDashboardPage.filterByLocation(location_name);
        Assert.assertTrue(result, "Filter by Location is not working");
    }

    @Test(priority = 5)
    public void filterByPoTest()  {

        boolean result = baDashboardPage.filterByPo();
        Assert.assertTrue(result, "Filter by PO-based is not working");
    }

    @Test(priority = 6)
    public void filterByBothTest()  {

        boolean result = baDashboardPage.filterByBoth();
        Assert.assertTrue(result, "Filter by Both (Po-based and Non-Po based) is not working");
    }

    @Test(priority = 7)
    public void filterByCompanyServiceAndMemoTest()  {

        String company_name = TestUtils.numberToString(data[0][4]), service = (String) data[0][5], memo_number = (String) data[0][6];

        boolean result = baDashboardPage.filterByCompanyServiceAndMemo(company_name, service, memo_number);
        Assert.assertTrue(result, "Filter by Company, Service and Memo is not working");
    }

    @Test(priority = 8)
    public void filterByInvoiceServiceAndLocationTest()  {

        String invoice_number = (String) data[0][7], service = (String) data[0][4], location_name = (String) data[0][8];

        boolean result=baDashboardPage.filterByInvoiceServiceAndLocation(invoice_number, service, location_name);
        Assert.assertTrue(result, "Filter by Invoice, Service and Location is not working");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}

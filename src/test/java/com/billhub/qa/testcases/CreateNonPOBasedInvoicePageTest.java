package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.CreateNonPOBasedInvoicePage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.Invoice;
import com.billhub.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateNonPOBasedInvoicePageTest extends TestBase {
    LoginPage loginPage;
    BADashboardPage baDashboardPage;
    CreateNonPOBasedInvoicePage createNonPOBasedInvoicePage;
    public Object[][] data;
    public Object[][] memoData= TestUtils.getTestData("BADashboardPage");


    public CreateNonPOBasedInvoicePageTest(){
        super();
    }

    public void updateExcelSheetData() {

        for(int i=1; i<=8; i++)
        {
            String invoice_number = "TESTINV"  + TestUtils.generateRandomNumber(4);
            String amount = TestUtils.generateRandomNumber(1);
            String base_amount=amount;
            double igst = Double.parseDouble(base_amount) * 0.18;
            igst=Math.floor(igst*100)/100.0;
            String Igst = String.valueOf(igst);
            TestUtils.setCellData("BTBasedInvoice", i, 0, invoice_number);
            TestUtils.setCellData("BTBasedInvoice", i, 1, base_amount);
            TestUtils.setCellData("BTBasedInvoice", i, 2, Igst);
            TestUtils.setCellData("BTBasedInvoice", i, 9, amount);
        }
    }

    @BeforeClass
    public void setup(){

        String from_state = (String) memoData[2][0], to_state = (String) memoData[2][1];
        initialization();
        loginPage= new LoginPage();
        baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_bt"),prop.getProperty("ba_password_bt"));
        createNonPOBasedInvoicePage = baDashboardPage.createNewMemoBTBased(from_state,to_state);
        updateExcelSheetData();
        data=TestUtils.getTestData("BTBasedInvoice");
    }

    @Test(priority = 1)
    public void gstCodeVerificationTest(){

        String base_amount = (String) data[0][1], igst = (String) data[0][2];
        double expected_amount=Double.parseDouble(base_amount)+Double.parseDouble(igst);
        double actual_amount= createNonPOBasedInvoicePage.verifyGstCode(base_amount,igst);
        Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
    }



    @Test(priority = 2)
    public void tdCodeVerificationTest(){

        String base_amount = (String) data[0][1], cd = (String) data[0][4],igst = (String) data[0][2];
        double expected_amount=Double.parseDouble(base_amount)-Double.parseDouble(cd);
        double actual_amount=createNonPOBasedInvoicePage.verifyTdCode(base_amount,cd);
        Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
    }



    @Test(priority = 3)
    public void additionatAmountVerificationTest(){

        String base_amount = (String) data[0][1], tcs = (String) data[0][5],igst = (String) data[0][2];
        double expected_amount=Double.parseDouble(base_amount)+Double.parseDouble(tcs);
        double actual_amount= createNonPOBasedInvoicePage.verifyAdditionalAmount(base_amount,tcs);
        Assert.assertEquals(actual_amount,expected_amount,"Test failed!");
    }



    @Test(priority = 4)
    public void createInvWithoutDataTest(){

        Invoice invoice = new Invoice("", "", "", "", "", "", "", "", "", "", "", "");
        boolean isFailed= createNonPOBasedInvoicePage.CreateInvoiceWithoutData(invoice);
        Assert.assertFalse(isFailed,"Test failed!");
    }


    @Test(priority = 5)
    public void submitMemoWithValidDataTest(){

        String company_code=(String) data[0][12], service_type=(String)data[0][13];
        String BTinvoiceNumber="492775030";// THIS IS THE INVOICE NUMBER OF BT OF ABOVE COMPANY CODE
        String invoice_number = (String) data[0][0], base_amount = (String) data[0][1], igst = (String) data[0][2];
        String subServiceCategory = (String) data[0][3], cd = (String) data[0][4], tcs = (String) data[0][5];
        String hsn_code = TestUtils.numberToString(data[0][6]), end_customer = (String) data[0][7], comment = (String) data[0][8], amount = TestUtils.numberToString(data[0][9]);
        String submitting_at = (String) data[0][10], submitting_to = (String) data[0][11];

        Invoice invoice=new Invoice(invoice_number, base_amount, igst, subServiceCategory, cd, tcs, hsn_code, end_customer, comment, amount, submitting_at, submitting_to);
        boolean isSubmitted= createNonPOBasedInvoicePage.submitMemoWithValidData(invoice,company_code,service_type,BTinvoiceNumber);
        Assert.assertTrue(isSubmitted,"Memo was not submitted!");

    }



    @Test(priority = 6)
    public void submitMemoWithDuplicateDataTest(){

        String from_state = (String) memoData[2][0], to_state = (String) memoData[2][1];
        createNonPOBasedInvoicePage = baDashboardPage.createNewMemoBTBased(from_state, to_state);

        String invoice_number = (String) data[0][0], base_amount = (String) data[0][1], igst = (String) data[0][2];
        String subServiceCategory = (String) data[0][3], cd = (String) data[0][4], tcs = (String) data[0][5];
        String hsn_code = TestUtils.numberToString(data[0][6]), end_customer = (String) data[0][7], comment = (String) data[0][8], amount = TestUtils.numberToString(data[0][9]);
        String submitting_at = (String) data[0][10], submitting_to = (String) data[0][11];
        Invoice invoice=new Invoice(invoice_number, base_amount, igst, subServiceCategory, cd, tcs, hsn_code, end_customer, comment, amount, submitting_at, submitting_to);
        boolean isSubmitted= createNonPOBasedInvoicePage.submitMemoWithDuplicateData(invoice);
        Assert.assertTrue(isSubmitted,"Memo was submitted with duplicate data!");
    }

    @Test(priority = 7)
    public void createMultipleInvoiceInSingleMemoTest() {

        List<Invoice> invoices = new ArrayList<>();
        String company_code=(String) data[2][12], service_type=(String)data[2][13];
        List<String> listOfBTs=new ArrayList<>();
        listOfBTs.addAll(Arrays.asList("222435078", "222445078"));// Add BT number corresponding to company code and service type

        for(int i=2; i<4; i++)
        {
            String invoice_number = (String) data[i][0], base_amount = (String) data[i][1], igst = (String) data[i][2];
            String subServiceCategory = (String) data[i][3], cd = (String) data[i][4], tcs = (String) data[i][5];
            String hsn_code = TestUtils.numberToString(data[i][6]), end_customer = (String) data[i][7], comment = (String) data[i][8], quantity = TestUtils.numberToString(data[i][9]);
            String submitting_at = (String) data[i][10], submitting_to = (String) data[i][11];

            Invoice invoice = new Invoice(invoice_number, base_amount, igst, subServiceCategory, cd, tcs, hsn_code, end_customer, comment, quantity, submitting_at, submitting_to);
            invoices.add(invoice);
        }

        boolean isSubmitted = createNonPOBasedInvoicePage.createMultipleInvoiceInSingleMemo(invoices,company_code,service_type,listOfBTs);
        Assert.assertTrue(isSubmitted, "Memo was not submitted");

    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }
}



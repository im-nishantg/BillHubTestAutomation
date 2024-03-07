package com.billhub.qa.testcases;

import com.beust.ah.A;
import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.CreateNonPOBasedInvoiceWithExcelsheetPage;
import com.billhub.qa.pages.CreatePOBasedInvoiceWithExcelsheetPage;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateNonPOBasedInvoiceWithExcelsheetTest extends TestBase {
    LoginPage loginPage;
    BADashboardPage baDashboardPage;
    CreateNonPOBasedInvoiceWithExcelsheetPage createNonPOBasedInvoiceWithExcelsheetPage;
    public Object[][] data;
    public Object[][] memoData = TestUtils.getTestData("BADashboardPage");

    public static String SHEET_PATH_FOR_SINGLE_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\30005574_template_for_non_po_based_with_single_invoice.xlsx";
    public static String SHEET_PATH_FOR_MULTIPLE_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\10005176_template_for_po_based_with_multiple_invoices.xlsx";
    public static String SHEET_PATH_FOR_EMPTY_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\10005176_template_for_po_based_without_data.xlsx";

    public void updateSingleInvoiceExcelSheet() {

        String invoice_number = "TESTINV"  + TestUtils.generateRandomNumber(5);
        TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo", 1, 1, invoice_number);
        TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Invoice_LR_Mapping", 1, 1, invoice_number);
    }
    @BeforeClass
    public void setup(){
        String from_state = (String) memoData[2][0], to_state = (String) memoData[2][1];
        String company_code="MERU - MLMO";
        String service_type="GENERAL EXPENSES";
        initialization();
		updateSingleInvoiceExcelSheet();
        loginPage=new LoginPage();
        baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_bt"),prop.getProperty("ba_password_bt"));
        createNonPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoNonPOBasedWithExcelsheet(from_state,to_state,company_code,service_type);
        createNonPOBasedInvoiceWithExcelsheetPage.uploadExcelSheet(SHEET_PATH_FOR_SINGLE_INVOICE);
        data = TestUtils.readExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo");
    }

    @Test(priority = 1)
    public void gstCodeVerificationTest(){
        String base_amount = (String) data[0][4], igst = (String) data[0][8];
        double expected_amount = Double.parseDouble(base_amount) + Double.parseDouble(igst);
        double actual_amount = createNonPOBasedInvoiceWithExcelsheetPage.verifyGstCode();
        Assert.assertEquals(actual_amount, expected_amount, "Test failed");
    }

    @Test(priority = 2)
    public void tdCodeVerificationTest(){
        String base_amount = (String) data[0][4], cd = (String) data[0][9];
        double expected_amount = Double.parseDouble(base_amount) - Double.parseDouble(cd);
        double actual_amount = createNonPOBasedInvoiceWithExcelsheetPage.verifyAdditionalAmount();
        Assert.assertEquals(actual_amount,expected_amount,"Test failed");
    }

    @Test(priority = 3)
    public void additionalAmountVerificationTest(){
        String base_amount = (String) data[0][4], tcs = (String) data[0][9];
        double expected_amount = Double.parseDouble(base_amount) + Double.parseDouble(tcs);
        double actual_amount = createNonPOBasedInvoiceWithExcelsheetPage.verifyAdditionalAmount();
        Assert.assertEquals(actual_amount, expected_amount, "Test failed");
    }

    @Test(priority = 4)
    public void submitMemoWithValidDataTest(){
        String submitting_at = (String) memoData[0][2], submitting_to = (String) memoData[0][3];
        boolean isSubmitted = createNonPOBasedInvoiceWithExcelsheetPage.submitMemoWithValidData(submitting_at, submitting_to);
        Assert.assertTrue(isSubmitted, "Memo was not submitted");
    }
    @Test(priority = 5)
    public void checkStatusOfSubmittedMemoTest(){
        String invoice_number=(String)data[0][1];
        boolean isAppear= createNonPOBasedInvoiceWithExcelsheetPage.statusOfSubmittedMemo(invoice_number);
        Assert.assertTrue(isAppear,"submitted invoice is not appear");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}

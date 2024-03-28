package com.billhub.qa.testcases;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.BADashboardPage;
import com.billhub.qa.pages.CreateNonPOBasedInvoiceWithExcelsheetPage;
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
    public static String SHEET_PATH_FOR_MULTIPLE_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\30005574_template_for_non_po_based_with_multiple_invoice.xlsx";
    public static String SHEET_PATH_FOR_EMPTY_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\30005574_template_for_non_po_based_without_data.xlsx";
    public static String SHEET_PATH_OF_ANOTHER_BT = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\30005574_template_of_another_bt.xlsx";
    String BaDashboardPageUrl = "https://billhub.mlldev.com/#/dashboard";
    String bt_number = "14395978";// this BT number is for single invoice submission


    public void updateSingleInvoiceExcelSheet() {

        String invoice_number = "TESTINV"  + TestUtils.generateRandomNumber(5);
        TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Memo", 1, 1, invoice_number);
        TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_SINGLE_INVOICE, "Invoice_LR_Mapping", 1, 1, invoice_number);
    }
    
    public void updateMultipleInvoiceExcelSheet() {

        for(int i=1; i<=2; i++){
            String invoice_number = "TESTINV"  + TestUtils.generateRandomNumber(5);
            TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_MULTIPLE_INVOICE, "Invoice_LR_Mapping", i, 1, invoice_number);
            TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_MULTIPLE_INVOICE, "Memo", i, 1, invoice_number);
        }

    }
    
    @BeforeClass
    public void setup(){

        String from_state = (String) memoData[2][0], to_state = (String) memoData[2][1];
        String company_code="MERU - MLMO";
        String service_type="GENERAL EXPENSES";
        initialization();
		updateSingleInvoiceExcelSheet();
        updateMultipleInvoiceExcelSheet();

        loginPage=new LoginPage();
        baDashboardPage = loginPage.loginAsBa(prop.getProperty("ba_userid_bt"),prop.getProperty("ba_password_bt"));
        createNonPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoNonPOBasedWithExcelsheet(from_state,to_state,company_code,service_type,bt_number);
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

        double actual_amount = createNonPOBasedInvoiceWithExcelsheetPage.verifyTdCode();
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
    public void submitMemoWithoutInvoiceDocTest(){

        boolean isErrorToastAppear= createNonPOBasedInvoiceWithExcelsheetPage.submitWithoutInvDoc();
        Assert.assertTrue(isErrorToastAppear,"Test failed!");
    }

    @Test(priority = 5)
    public void submitMemoWithValidDataTest(){

        String submitting_at = (String) memoData[2][2], submitting_to = (String) memoData[2][3];

        boolean isSubmitted = createNonPOBasedInvoiceWithExcelsheetPage.submitMemoWithValidData(submitting_at, submitting_to);
        Assert.assertTrue(isSubmitted, "Memo was not submitted");
    }
    
    @Test(priority = 6)
    public void checkStatusOfSubmittedMemoTest(){

        String invoice_number=(String)data[0][1];//make sure this invoice is submitted
        String exp_status="Submitted";

        driver.get(BaDashboardPageUrl);
        String status= createNonPOBasedInvoiceWithExcelsheetPage.statusOfSubmittedMemo(invoice_number);
        Assert.assertEquals(status,exp_status,"submitted invoice is not appear");
    }

    @Test(priority = 7)
    public void submitMemoWithDuplicateDataTest(){

        String company_code="MERU - MLMO";
        String service_type="GENERAL EXPENSES";
        //company_code and service_type should be same as above submitted BT
        driver.get(BaDashboardPageUrl);
        String from_state = (String) memoData[2][0], to_state = (String) memoData[2][1];
        boolean isFailed=baDashboardPage.summitMemoWithDuplicateData(from_state,to_state,company_code,service_type,bt_number);
        Assert.assertTrue(isFailed,"Duplicate memo submitted!");
    }
    
    @Test(priority = 8)
    public void submitMemoWithoutDataTest(){

        String from_state = (String) memoData[2][0], to_state = (String) memoData[2][1];
        String company_code="MERU - MLMO";
        String service_type="GENERAL EXPENSES";

        driver.get(BaDashboardPageUrl);
        createNonPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoNonPOBasedWithExcelsheet(from_state, to_state,company_code,service_type,bt_number);
        boolean isSubmitted = createNonPOBasedInvoiceWithExcelsheetPage.submitMemoWithoutData(SHEET_PATH_FOR_EMPTY_INVOICE);
        Assert.assertFalse(isSubmitted, "Memo was submitted without data.");
    }

    @Test(priority = 9)
    public void submitMemoWithAnotherBtInvoiceTest(){
        String company_code="MLL - 1022";
        String service_type="GENERAL EXPENSES";
        String new_bt_number="214645678";// BT number should be related to above company_code

        driver.get(BaDashboardPageUrl);
        String from_state = (String) memoData[2][0], to_state = (String) memoData[2][1];
        boolean isFailed= baDashboardPage.submitMemoWithAnotherBt(from_state,to_state,company_code,service_type,new_bt_number,SHEET_PATH_OF_ANOTHER_BT);
        Assert.assertTrue(isFailed,"Invoice is submitted with another BT");
    }

    @Test(priority = 10)
    public void createMultipleInvoiceInSingleMemoTest(){

        String company_code="MESPL - MESP";
        String service_type="GENERAL EXPENSES";
        String bt_number_multiple="14349478";// BT number of above company's invoice


        String from_state = (String) memoData[3][0], to_state = (String) memoData[3][1];
        driver.get(BaDashboardPageUrl);
        createNonPOBasedInvoiceWithExcelsheetPage = baDashboardPage.createNewMemoNonPOBasedWithExcelsheet(from_state, to_state,company_code,service_type,bt_number_multiple);
        String submitting_at = (String) memoData[2][2], submitting_to = (String) memoData[2][3];
        boolean isSubmitted = createNonPOBasedInvoiceWithExcelsheetPage.createMultipleInvoiceInSingleMemo(SHEET_PATH_FOR_MULTIPLE_INVOICE, submitting_at, submitting_to);
        Assert.assertTrue(isSubmitted, "Memo was not submitted");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}

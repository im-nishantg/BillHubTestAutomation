//package com.billhub.qa.testcases;
//
//import com.billhub.qa.base.TestBase;
//import com.billhub.qa.pages.LoginPage;
//import com.billhub.qa.pages.NonPoBasedInvoiceAcknowledgementPage;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//public class NonPoBasedInvoiceAcknowledgementTest  extends TestBase {
//    LoginPage loginPage;
//    NonPoBasedInvoiceAcknowledgementPage nonPoBasedInvoiceAcknowledgementPage;
//
//    @BeforeClass
//    public void setup(){
//
//        initialization();
//        loginPage= new LoginPage();
//        loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
//        nonPoBasedInvoiceAcknowledgementPage = new NonPoBasedInvoiceAcknowledgementPage();
//    }
//
//    @Test(priority = 1)
//    public void ValidateSubmittedNonPoBasedInvoiceInDashboardTest()  {
//
//        boolean areInvoicesDisplayed = nonPoBasedInvoiceAcknowledgementPage.ValidateSubmittedNonPoBasedInvoiceInDashboard();
//        Assert.assertTrue(areInvoicesDisplayed, "Invoices were not displayed after clicking on Load dashboard button.");
//    }
//
//    @Test(priority = 2)
//    public void ValidateAcknowledgeTheMemoTest()  {
//
//        boolean areInvoicesDisplayed = nonPoBasedInvoiceAcknowledgementPage.ValidateAcknowledgeTheMemo();
//        Assert.assertTrue(areInvoicesDisplayed, "Invoices were not displayed after clicking on Load dashboard button.");
//    }
//
//    @Test(priority = 3)
//    public void ValidateReassignMemoTest(){
//
//        String submit_from="";
//        String submit_to="";
//        boolean isAssign= nonPoBasedInvoiceAcknowledgementPage.ValidateReassignTheMemo(submit_from,submit_to);
//        Assert.assertTrue(isAssign,"test fail");
//    }
//
//    @Test(priority = 4)
//    public void ValidateRejectTheMemoTest()  {
//
//        String reason = "Agreement not available";
//
//        boolean isMemoRejected = nonPoBasedInvoiceAcknowledgementPage.ValidateRejectTheMemo(reason);
//        Assert.assertTrue(isMemoRejected, "Memo was not rejected.");
//    }
//    @Test(priority = 5)
//    public void validateAcknowledgedStatusTest()  {
//
//        String memo_status = nonPoBasedInvoiceAcknowledgementPage.validateAcknowledgedMemoStatus();
//        Assert.assertEquals(memo_status, "Acknowledged", "Memo status was not updated successfully.");
//    }
//    @Test(priority = 6)
//    public void validateRejectedStatusTest()  {
//
//        String memo_status = nonPoBasedInvoiceAcknowledgementPage.validateRejectedMemoStatus();
//        Assert.assertEquals(memo_status, "Rejected", "Memo status was not updated successfully.");
//    }
//
//    @AfterClass
//    public void tearDown() {
//        driver.close();
//    }
//}



package com.billhub.qa.testcases;

import com.billhub.qa.pages.NonPoBasedInvoiceAcknowledgementPage;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.pages.LoginPage;
import com.billhub.qa.utils.TestUtils;

public class NonPoBasedInvoiceAcknowledgementTest extends TestBase{

    LoginPage loginPage;
    NonPoBasedInvoiceAcknowledgementPage nonPoBasedInvoiceAcknowledgementPage;
    public Object [][]data = TestUtils.getTestData("BTBasedInvoiceAcknowledgement");

    @BeforeClass
    public void setup(){

        initialization();
        loginPage= new LoginPage();
        loginPage.loginAsCommercial(prop.getProperty("commercial_userid"),prop.getProperty("commercial_password"));
        nonPoBasedInvoiceAcknowledgementPage = new NonPoBasedInvoiceAcknowledgementPage();
    }

    // ********** Tests Associated with Acknowledgement, Verification and Booking of the invoice with valid data ****************

    @Test(priority = 1)
    public void validateSubmittedBTBasedInvoiceInDashboardTest()  {

        boolean areInvoicesDisplayed = nonPoBasedInvoiceAcknowledgementPage.validateSubmittedPoBasedInvoiceInDashboard();
        Assert.assertTrue(areInvoicesDisplayed, "Invoices were not displayed after clicking on Load dashboard button.");
    }


    @Test(priority = 2)
    public void validateAcknowledgeTheMemoTest()  {

        boolean isMemoAcknowledged = nonPoBasedInvoiceAcknowledgementPage.validateAcknowledgeTheMemo();
        Assert.assertTrue(isMemoAcknowledged, "Invoices were not displayed after clicking on Load dashboard button.");
    }

    @Test(priority = 3)
    public void validateAcknowledgedStatusTest()  {

        String memo_status = nonPoBasedInvoiceAcknowledgementPage.validateAcknowledgedMemoStatus();
        Assert.assertTrue(StringUtils.containsIgnoreCase(memo_status,"Acknowledged"),"Memo status not changed");
    }

    @Test(priority = 4)
    public void verifyInvoiceWithValidDataTest(){

        String assignment = (String) data[0][3], item_text = (String) data[0][4],hsn_code=TestUtils.numberToString(data[0][10]);;

        boolean isInvoiceVerified = nonPoBasedInvoiceAcknowledgementPage.verifyInvoiceWithValidData(assignment, item_text,hsn_code);
        Assert.assertTrue(isInvoiceVerified, "Invoice was verified with invalid taxcode.");
    }

    @Test(priority = 5)
    public void validateVerifiedStatusTest(){

        String memo_status = nonPoBasedInvoiceAcknowledgementPage.validateVerifiedMemoStatus();
        Assert.assertTrue(StringUtils.containsIgnoreCase(memo_status,"Verified"),"Memo status not changed");
    }

    @Test(priority = 6)
    public void bookingInvoiceWithValidDataTest()  {

        boolean isInvoiceBooked = nonPoBasedInvoiceAcknowledgementPage.bookingInvoiceWithValidData();
        Assert.assertTrue(isInvoiceBooked, "Booking of the invoice failed.");
    }

    @Test(priority = 7)
    public void searchByTransactionBatchIdTest()  {

        boolean isDetailsDisplayed = nonPoBasedInvoiceAcknowledgementPage.searchByTransactionBatchId();
        Assert.assertTrue(isDetailsDisplayed, "Details of the transaction batchId were not displayed.");
    }

    // ******************************** All Tests Associated with Acknowledgement of the invoice  ****************************

    @Test(priority = 8)
    public void ValidateRejectTheMemoTest()  {

        String memo_number = (String) data[0][0], reason = (String) data[0][2];

        boolean isMemoRejected = nonPoBasedInvoiceAcknowledgementPage.ValidateRejectTheMemo(memo_number, reason);
        Assert.assertTrue(isMemoRejected, "Memo was not rejected.");
    }

    @Test(priority = 9)
    public void validateRejectedStatusTest()  {

        String invoice_number = (String) data[0][1];

        String memo_status = nonPoBasedInvoiceAcknowledgementPage.validateRejectedMemoStatus(invoice_number);
        Assert.assertEquals(memo_status, "Rejected", "Memo status was not updated successfully.");
    }

    @Test(priority = 10)
    public void ValidateReassignTheMemoTest()  {

        String memo_number = (String) data[1][0], submitting_from = (String) data[1][5], submitting_to = (String) data[1][6];

        boolean isMemoReassigned = nonPoBasedInvoiceAcknowledgementPage.ValidateReassignTheMemo(memo_number, submitting_from, submitting_to);
        Assert.assertTrue(isMemoReassigned, "Memo was not reassigned.");
    }
    @Test(priority = 11)
    public void validateInvoiceFilePreviewTest()  {

        String memo_number = (String) data[1][0];

        boolean isFilePreviewDisplayed = nonPoBasedInvoiceAcknowledgementPage.validateInvoiceFilePreview(memo_number);
        Assert.assertTrue(isFilePreviewDisplayed, "Invoice file preview is unavailable.");
    }

    @Test(priority = 12)
    public void verifyInvoiceWithInvalidTaxCodeTest()  {

        String memo_number = (String) data[2][0], assignment = (String) data[2][3], item_text = (String) data[2][4],hsn_code=TestUtils.numberToString(data[2][10]);

        boolean isInvoiceVerified = nonPoBasedInvoiceAcknowledgementPage.verifyInvoiceWithInvalidTaxCode(hsn_code,assignment, item_text,memo_number);
        Assert.assertFalse(isInvoiceVerified, "Invoice was verified with invalid taxcode.");
    }
    @Test(priority = 13)
    public void validateInvoiceVerificationWithInvalideDataTest(){
        String memo_number = (String) data[3][0], assignment = (String) data[3][3], item_text = (String) data[3][4],hsn_code=TestUtils.numberToString(data[3][10]);
        boolean isVerified = nonPoBasedInvoiceAcknowledgementPage.invoiceVerificationWithInvalideData(hsn_code,assignment,item_text,memo_number);
        Assert.assertFalse(isVerified,"Invoice verified with invalide hsn code");
    }

    @Test(priority = 14)
    public void ValidateRejectTheAcknowledgedInvoiceTest()  {

        String memo_number = (String) data[7][0], reason = (String) data[7][2];

        boolean isInvoiceRejected = nonPoBasedInvoiceAcknowledgementPage.ValidateRejectTheAcknowledgedInvoice(memo_number, reason);
        Assert.assertTrue(isInvoiceRejected, "Acknowledged invoice was not rejected.");
    }

    // ******************************** All Tests Associated with Booking of the invoice  ****************************

    @Test(priority = 15)
    public void searchingInvoiceWithMultipleFieldsTest()  {

        String invoice_number = (String) data[4][1], ba_name = (String) data[4][8];

        boolean areDetailsDisplayed = nonPoBasedInvoiceAcknowledgementPage.searchingInvoiceWithMultipleFields(invoice_number, ba_name);
        Assert.assertTrue(areDetailsDisplayed, "Search funtionality is not working properly.");
    }

    @Test(priority = 16)
    public void downloadingInvoiceBeforeBookingTest(){

        String invoice_number = (String) data[4][1], ba_name = (String) data[4][8], verified_by = (String) data[4][9];

        boolean isFileDownloaded = nonPoBasedInvoiceAcknowledgementPage.downloadingInvoiceBeforeBooking(invoice_number, ba_name, verified_by);
        Assert.assertTrue(isFileDownloaded, "Download funtionality is not working properly.");
    }

    @Test(priority = 17)
    public void searchingInvoiceWithInvalidDataTest()  {

        String invoice_number = (String) data[8][1], ba_name = (String) data[8][8];

        boolean areDetailsDisplayed = nonPoBasedInvoiceAcknowledgementPage.searchingInvoiceWithInvalidData(invoice_number, ba_name);
        Assert.assertFalse(areDetailsDisplayed, "Search funtionality is not working properly.");
    }

    // ******************************** All Tests Associated with Review invoice posting ****************************

    @Test(priority = 18)
    public void validatingTwoDocumentNumbersTest()  {

        String batch_id = (String) data[5][7];

        boolean areDetailsDisplayed = nonPoBasedInvoiceAcknowledgementPage.validatingTwoDocumentNumbers(batch_id);
        Assert.assertTrue(areDetailsDisplayed, "Two document numbers were not displayed.");
    }

    @Test(priority = 19)
    public void downloadingBookedInvoiceTest()  {

        String batch_id = (String) data[5][7];

        boolean isFileDownloaded = nonPoBasedInvoiceAcknowledgementPage.downloadingBookedInvoiceTest(batch_id);
        Assert.assertTrue(isFileDownloaded, "Download funtionality is not working properly.");
    }

    @Test(priority = 20)
    public void searchByInvalidTransactionBatchIdTest()  {

        String batch_id = (String) data[6][7];

        boolean areDetailsDisplayed = nonPoBasedInvoiceAcknowledgementPage.searchByInvalidTransactionBatchId(batch_id);
        Assert.assertFalse(areDetailsDisplayed, "Details of the transaction were displayed for invalid batchId.");
    }
}

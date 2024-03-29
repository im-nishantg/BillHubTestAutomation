package com.billhub.qa.pages;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NonPoBasedInvoiceVerificationWithExcelsheetPage extends TestBase {
    
	@FindBy(xpath = "//button[@class='btn btn-link']")
    WebElement navbarExpandBtn;

    @FindBy(xpath = "//span[normalize-space()='Dashboard']")
    WebElement dashboardBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/div/input")
    WebElement memoInput;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/button[2]")
    WebElement verifyMemoBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-list/div/div/div[4]/div/div/div/div[7]/div/button")
    WebElement verifyBtn;

    @FindBy(xpath = "//button[normalize-space()='Get Advances']")
    WebElement getAdvanceBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/div[3]/div/div/div[1]/div/div[3]/button[2]")
    WebElement invoiceScanedCopyBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]/ng-select/div/div/div[3]/input")
    WebElement hsnCodeInput;

    @FindBy(xpath = "//div[@class='ng-select-container']//input[@role='combobox']")
    WebElement taxCodeInput;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[4]/input")
    WebElement tdInput;

    @FindBy(xpath = "//*[@id=\"defaultCheck1\"]")
    WebElement tdsCheckbox;

    @FindBy(xpath = "//input[@placeholder='Assignment']")
    WebElement assignmentInput;

    @FindBy(xpath = "//input[@placeholder='Itemtext']")
    WebElement itemTextInput;

    @FindBy(xpath = "//button[normalize-space()='Verify Invoice']")
    WebElement verifyInvoiceBtn;

    @FindBy(xpath = "//button[normalize-space()='CANCEL']")
    WebElement cancelBtn;

    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]/ng-select/ng-dropdown-panel/div/div[2]/div")
    WebElement hsnSelectTab;

    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[3]/ng-select/ng-dropdown-panel/div/div[2]/div")
    WebElement taxcodeSelectTab;

    @FindBy(xpath = "//div[@class='input-group']//select[@id='inputGroupSelect02']")
    WebElement rejectInput;

    @FindBy(xpath = "//button[normalize-space()='Reject']")
    WebElement rejectBtn;

    @FindBy(xpath = "//button[normalize-space()='Invoice Scanned Copy']")
    WebElement invoiceScannedCopyBtn;

    @FindBy(xpath = "//i[@class='fa fa-file-pdf faFont']")
    WebElement invoiceFileBtn;

    @FindBy(xpath = "/html/body/modal-container[2]/div/div/app-invoice-attachments-popup/div[2]/div/div[1]/div/div/div/div/iframe")
    WebElement invoiceFilePreview;

    @FindBy(xpath = "//button[@aria-label='Close']")
    WebElement closePopupBtn;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-verify-error/div[2]/table/tbody/tr/td[2]")
    WebElement messageType;

    @FindBy(xpath = "//a[normalize-space()='Filter']")
    WebElement filterBtn;

    @FindBy(css = "input[formcontrolname='memo']")
    WebElement memo;

    @FindBy(css = "button[type='submit']")
    WebElement applyBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[13]")
    WebElement memoStatus;

    @FindBy(xpath = "//a[normalize-space()='Download Template']")
    WebElement downloadInvoice;

    @FindBy(xpath = "//b[normalize-space()='Upload Invoice File']")
    WebElement uploadInvoice;

    @FindBy(xpath = "//input[@id='fileDropRef']")
    WebElement uploadFile;

    @FindBy(xpath = "//button[normalize-space()='Upload']")
    WebElement uploadBtn;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-upload-invoice-popup/div/div[2]/div[2]/button")
    WebElement proceedWithInvoiceBtn;

    @FindBy(css = "input[formcontrolname='inv']")
    WebElement invoice;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-upload-invoice-popup/div/table/tbody/tr/td[10]/button")
    WebElement errorLogBtn;

    @FindBy(xpath = "/html/body/modal-container[2]/div/div/app-invoice-log/div[2]/table/tbody/tr/td[4]/div")
    WebElement errorMessage;

    @FindBy(xpath = "/html/body/modal-container[2]/div/div/app-invoice-log/div[1]/button/span")
    WebElement errorLogCloseBtn;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-upload-invoice-popup/div/div[1]/div/div/button/span")
    WebElement verificationPopupCloseBtn;

    public NonPoBasedInvoiceVerificationWithExcelsheetPage(){
        PageFactory.initElements(driver,this);
    }


    public Object[][] data1; // invoice details
    public Object[][] data2; // verification details
    public static String SHEET_PATH_FOR_DOWNLOADED_INVOICE = prop.getProperty("download_folder_path");
    public static String SHEET_PATH_FOR_UPLOADING_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\invoice_verification_sheet_non_po.xlsx";

    public void updateInvoiceExcelSheet(String memo_number) {
        String downloaded_sheetPath = SHEET_PATH_FOR_DOWNLOADED_INVOICE + "/" + memo_number + ".xlsx";
        data1 = TestUtils.readExcelSheetByFilePath(downloaded_sheetPath, "Memo_invoice_Details");
        data2 = TestUtils.readExcelSheetByFilePath(downloaded_sheetPath, "Memo_Verification_details");

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(SHEET_PATH_FOR_UPLOADING_INVOICE));

            // Update Memo_invoice_Details sheet
            Sheet invoiceSheet = workbook.getSheet("Memo_invoice_Details");
            updateSheet(invoiceSheet, data1, 1, 15);

            // Update Memo_Verification_details sheet
            Sheet verificationSheet = workbook.getSheet("Memo_Verification_details");
            updateSheet(verificationSheet, data2, 1, 12);

            // Update tax code in Memo_Verification_details
            String IGST = (String) data1[0][8];
            String tax_code = IGST.equals("0.0") ? "V0" : "KG";
            updateCell(verificationSheet.getRow(1), 14, tax_code,false);

            // Write the changes back to the workbook
            FileOutputStream fileOut = new FileOutputStream(SHEET_PATH_FOR_UPLOADING_INVOICE);
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void updateSheet(Sheet sheet, Object[][] data, int rowNumber, int columnCount) {
        for (int i = 1; i <= columnCount; i++) {
            String value = (String) data[0][i];
            if (value.equals("0.0")){
                value = "0";
                updateCell(sheet.getRow(rowNumber), i, value,true);
            }
            else if (value.equals("1.0")){
                value="1";
                updateCell(sheet.getRow(rowNumber), i, value,true);
            }
            else updateCell(sheet.getRow(rowNumber), i, value,false);

        }
    }

    private void updateCell(Row row, int columnIndex, String value, boolean alignment) {
        if (row != null) {
            Cell cell = row.getCell(columnIndex);
            if (cell == null) {
                cell = row.createCell(columnIndex);
            }
            cell.setCellValue(value);

            // Set cell alignment to left-aligned
            Workbook workbook = row.getSheet().getWorkbook();
            CellStyle style = workbook.createCellStyle();
            if(alignment)style.setAlignment(HorizontalAlignment.RIGHT);
            else style.setAlignment(HorizontalAlignment.LEFT);

            cell.setCellStyle(style);
        }
    }


    public void AddNewFields(String withholding_tax, String item_text, String payment_term, String assignment) {

        String IGST = (String) data1[0][8];
        String tax_code = IGST.equals("0.0") ? "V0" : "KG";
        TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 14, tax_code);
        TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 16, withholding_tax);
        TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 17, item_text);
        TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 18, payment_term);
        TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 19, assignment);
    }

    public boolean invoiceVerificationWithExcelsheet(String withholding_tax, String item_text, String payment_term, String assignment){

        String memo_number = "30005574-2023-24-00021";

        memoInput.sendKeys(memo_number);
        WebElement selectTab= TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        downloadInvoice.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        updateInvoiceExcelSheet(memo_number);
        AddNewFields(withholding_tax, item_text, payment_term, assignment);

        uploadInvoice.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadFile.sendKeys(SHEET_PATH_FOR_UPLOADING_INVOICE);

        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));
        proceedWithInvoiceBtn.click();

        WebElement invoiceStatus = TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container/div/div/app-verify-log-popup/div/table/tbody/tr/td[4]"));
        String successMessage = "Invoice verified and simulate successfully";

        return invoiceStatus.getText().equalsIgnoreCase(successMessage);
    }

    public String validateVerifiedMemoStatus(String invoice_number) {	// Test for verifying the status of verified memo in the dashboard

        TestUtils.waitForElementInvisibility(By.className("loader"));
        closePopupBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
        TestUtils.waitForWebElementToBeClickable(dashboardBtn).click();
        TestUtils.waitForElementInvisibility(By.className("loader"));

        filterBtn.click();											//searching for the memo in the filter option
        invoice.sendKeys(invoice_number);
        applyBtn.click();

        boolean isMemoFound = TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[4]"), invoice_number);

        if(isMemoFound == false)						// if the correct memo is not found return false
            return "Memo was not found.";

        return memoStatus.getText();
    }

    public boolean invoiceVerificationWithInvalidTaxCode(String memo_number, String sheetPath) {

        TestUtils.waitForElementInvisibility(By.className("loader"));
        memoInput.sendKeys(memo_number);
        WebElement selectTab= TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadInvoice.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadFile.sendKeys(sheetPath);

        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));

        return TestUtils.isElementVisible(errorLogBtn);
    }

    public boolean validateLogButtonFunctionality() {

        if(TestUtils.isElementVisible(errorLogBtn) == false)
            return false;

        errorLogBtn.click();
        TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container[2]/div/div/app-invoice-log/div[2]/table/tbody/tr/td[4]/div"));
        return TestUtils.isElementVisible(errorMessage);
    }

    public boolean InvoiceVerificationWithDifferentDataInTwoSheets(String memo_number, String sheetPath) {

        TestUtils.waitForElementInvisibility(By.className("loader"));
        errorLogCloseBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        verificationPopupCloseBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
        TestUtils.waitForWebElementToBeClickable(dashboardBtn).click();
        TestUtils.waitForElementInvisibility(By.className("loader"));

        memoInput.sendKeys(memo_number);
        WebElement selectTab= TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadInvoice.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadFile.sendKeys(sheetPath);

        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));

        return TestUtils.isElementVisible(errorLogBtn);
    }

    public boolean invoiceVerificationWithInvalidData(String memo_number, String sheetPath) {

        TestUtils.waitForElementInvisibility(By.className("loader"));
        verificationPopupCloseBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        TestUtils.waitForWebElementToBeClickable(dashboardBtn).click();
        TestUtils.waitForElementInvisibility(By.className("loader"));

        memoInput.sendKeys(memo_number);
        WebElement selectTab= TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadInvoice.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadFile.sendKeys(sheetPath);

        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));

        return TestUtils.isElementVisible(errorLogBtn);
    }
}
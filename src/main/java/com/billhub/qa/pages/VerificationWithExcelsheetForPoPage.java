package com.billhub.qa.pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class VerificationWithExcelsheetForPoPage extends TestBase{
	
	
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
    
     @FindBy(xpath = "//button[normalize-space()='Verify Invoice']")
     WebElement verifyInvoiceBtn;

     @FindBy(xpath = "/html/body/modal-container/div/div/app-verify-log-popup/div/div[1]/div/div/button")
     WebElement closePopupBtn;
    
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
     
     public VerificationWithExcelsheetForPoPage(){
         PageFactory.initElements(driver,this);
     }
	
	 public Object[][] data1; // invoice details
	 public Object[][] data2; // verification details
	 public static String SHEET_PATH_FOR_DOWNLOADED_INVOICE = "C:\\Users\\nisha\\Downloads";
	 public static String SHEET_PATH_FOR_UPLOADING_INVOICE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\invoice_verification_sheet_po.xlsx";
	    
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
	
	    private void updateSheet(Sheet sheet, Object[][] data, int rowNumber, int columnCount) {	// function to move data from downloaded excel sheet to excel sheet in testdata package
	        
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
	
	    private void updateCell(Row row, int columnIndex, String value, boolean alignment) {	//function to update the cell value and right aligned the data in excel cell
	        
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
	    	TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 10, tax_code);
	    	TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 11, withholding_tax);
	    	TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 12, item_text);
	    	TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 13, payment_term);
	    	TestUtils.updateExcelSheetByFilePath(SHEET_PATH_FOR_UPLOADING_INVOICE, "Memo_Verification_details", 1, 14, assignment);
	    }
	    
	    public boolean invoiceVerificationWithValidData(String memo_number, String withholding_tax, String item_text, String payment_term, String assignment){
	
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

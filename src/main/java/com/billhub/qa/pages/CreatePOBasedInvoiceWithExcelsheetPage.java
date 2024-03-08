package com.billhub.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.Invoice;
import com.billhub.qa.utils.TestUtils;

public class CreatePOBasedInvoiceWithExcelsheetPage extends TestBase{
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-upload/div/div/div[2]/div/div[1]/div[2]/div/input")
	WebElement uploadFileBtn;
	
	@FindBy(css = ".btn.btn-warning.btn-acknowledge")
	WebElement uploadBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[1]/div/perfect-scrollbar/div/div[1]/div/div/div/div/div/div[4]/button[1]")
	WebElement editBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[1]/div/perfect-scrollbar/div/div[1]/div/div/div[1]/div/div/div[4]/button[1]/i")
	WebElement editBtn1;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[1]/div/perfect-scrollbar/div/div[1]/div/div/div[2]/div/div/div[4]/button[1]/i")
	WebElement editBtn2;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[1]/div/perfect-scrollbar/div/div[1]/div/div/div[3]/div/div/div[4]/button[1]/i")
	WebElement editBtn3;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[1]/div/perfect-scrollbar/div/div[1]/div/div/div[4]/div/div/div[4]/button[1]/i")
	WebElement editBtn4;
	
	@FindBy(xpath = "//div[@class='inv-footer-text']")
	WebElement totalInvAmount;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[2]/form/div/div[1]/div/div/div[2]/button")
	WebElement resetBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Attach']")
	WebElement attachBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Tag PO']")
	WebElement tagPOBtn;
	
	@FindBy(xpath = "//input[@name='Invoice']")
	WebElement addInvoice;
	
	@FindBy(xpath = "//button[normalize-space()='Done']")
	WebElement doneBtn;
	
	@FindBy(xpath = "//button[normalize-space()='SAVE']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[4]/select")
	WebElement submittingAt;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[5]/select")
	WebElement submittingTo;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[6]/button")
	WebElement submitMemoBtn;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement nextSubmitMemoBtn;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-memo-submit-confirm/div[3]/div/div[2]/button")
	WebElement finalSubmitMemoBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-final/div/div/div[2]/div[2]/button")
	WebElement printBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-final/div/div/div[2]/div[1]/button")
	WebElement homeBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-invalid-invoices/div/div/div[2]/div[2]/table/tbody/tr/td[1]")
	WebElement invoiceAlreadyExistErrorText;
	
	public CreatePOBasedInvoiceWithExcelsheetPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void uploadExcelSheet(String INVOICE_SHEET_PATH) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		uploadFileBtn.sendKeys(INVOICE_SHEET_PATH);
		TestUtils.waitForElementVisibility(By.cssSelector(".btn.btn-warning.btn-acknowledge"));
		TestUtils.waitForWebElementToBeClickable(uploadBtn).click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
	}
	
	public void attachSampleInvoiceFile() {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		attachBtn.click();
		String invoice_file_path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\Sample_Invoice.pdf";
		addInvoice.sendKeys(invoice_file_path);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		TestUtils.waitForWebElementToBeClickable(doneBtn).click();
	}


	public double verifyGstCode() {
		
		editBtn.click();
		String amount =  totalInvAmount.getText();
		amount = TestUtils.splitString(amount);
		resetBtn.click();
		return Double.parseDouble(amount);
	}


	public double verifyTdCode() {
		
		editBtn.click();
		String amount =  totalInvAmount.getText();
		amount = TestUtils.splitString(amount);
		resetBtn.click();
		return Double.parseDouble(amount);
	}


	public double verifyAdditionalAmount() {
		
		editBtn.click();
		String amount =  totalInvAmount.getText();
		amount = TestUtils.splitString(amount);
		resetBtn.click();
		return Double.parseDouble(amount);
	}
	
	public boolean submitMemoWithValidData(String submitting_at, String submitting_to) {
		
		editBtn.click();
		attachSampleInvoiceFile();
		TestUtils.waitForWebElementToBeClickable(saveBtn).click();	// saving the current invoice
		
		// code for tagging the location and the person for the memo
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		submittingAt.sendKeys(submitting_at);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		submittingTo.sendKeys(submitting_to);
		
		// code for actually submitting the memo
		submitMemoBtn.click();
		nextSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("//button[@type='submit']"));
		TestUtils.waitForWebElementToBeClickable(nextSubmitMemoBtn).click();	
		finalSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container/div/div/app-memo-submit-confirm/div[3]/div/div[2]/button"));
		TestUtils.waitForWebElementToBeClickable(finalSubmitMemoBtn).click();
		
		//print button will be visible once the memo is submitted successfully
		TestUtils.waitForElementInvisibility(By.className("modal-container"));			
		boolean isPrintBtnVisible = TestUtils.isElementVisible(printBtn);	
		homeBtn.click();							// going back to home button for next test
		return isPrintBtnVisible;
	}
	
	public boolean submitMemoWithDuplicateData(String SHEET_PATH_FOR_SINGLE_INVOICE) {
		
		uploadExcelSheet(SHEET_PATH_FOR_SINGLE_INVOICE);		// uploading the excel sheet with duplicate data
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		
		boolean isErrorDisplayed = TestUtils.isElementVisible(invoiceAlreadyExistErrorText);	
		return isErrorDisplayed;
	}
	
	public boolean submitMemoWithoutData(String SHEET_PATH_FOR_EMPTY_INVOICE) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		uploadFileBtn.sendKeys(SHEET_PATH_FOR_EMPTY_INVOICE);
		
		boolean isSubmitMemoBtnDisplayed = TestUtils.isElementVisible(submitMemoBtn);	
		return isSubmitMemoBtnDisplayed;
	}
	
	public boolean createMultipleInvoiceInSingleMemo(String SHEET_PATH_FOR_MULTIPLE_INVOICE, String submitting_at, String submitting_to) {
		
		uploadExcelSheet(SHEET_PATH_FOR_MULTIPLE_INVOICE);					// uploading the excel sheet with multiple invoices
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		
		editBtn1.click();
		attachSampleInvoiceFile();
		TestUtils.waitForWebElementToBeClickable(saveBtn).click();	// saving the first invoice
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		
		editBtn2.click();
		attachSampleInvoiceFile();
		TestUtils.waitForWebElementToBeClickable(saveBtn).click();	// saving the second invoice
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		
		// code for tagging the location and the person for the memo
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		submittingAt.sendKeys(submitting_at);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		submittingTo.sendKeys(submitting_to);
		
		// code for actually submitting the memo
		submitMemoBtn.click();
		nextSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("//button[@type='submit']"));
		TestUtils.waitForWebElementToBeClickable(nextSubmitMemoBtn).click();	
		finalSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container/div/div/app-memo-submit-confirm/div[3]/div/div[2]/button"));
		TestUtils.waitForWebElementToBeClickable(finalSubmitMemoBtn).click();
		
		//print button will be visible once the memo is submitted successfully
		TestUtils.waitForElementInvisibility(By.className("modal-container"));			
		boolean isPrintBtnVisible = TestUtils.isElementVisible(printBtn);	
		homeBtn.click();							// going back to home button for next test
		return isPrintBtnVisible;
	}
	
}

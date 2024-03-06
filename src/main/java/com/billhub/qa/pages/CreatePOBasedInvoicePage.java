package com.billhub.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.Invoice;
import com.billhub.qa.utils.TestUtils;

public class CreatePOBasedInvoicePage extends TestBase{

	@FindBy(xpath = "//input[@id='first']")
	WebElement invoiceNumber;
	
	@FindBy(xpath = "//button[normalize-space()='Attach']")
	WebElement attachBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Tag PO']")
	WebElement tagPOBtn;
	
	@FindBy(xpath = "//select[@id='subServiCategory']")
	WebElement subServiceCategory;
	
	@FindBy(xpath = "//input[@id='base']")
	WebElement baseAmount;
	
	@FindBy(xpath = "//input[@id='td']")
	WebElement cd;
	
	@FindBy(xpath = "//input[@id='tcsAmount']")
	WebElement tcs;
	
	@FindBy(xpath = "//input[@id='igst']")
	WebElement igst;
	
	@FindBy(xpath = "//input[@id='hsnCode']")
	WebElement HSNCode;
	
	@FindBy(xpath = "//input[@id='custName']")
	WebElement endCustomer;
	
	@FindBy(xpath = "//input[@id='commet']")
	WebElement comment;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[2]/div[1]/table/tbody/tr[3]/td[1]/div/input")
	WebElement poResultCheckBox;
	
	@FindBy(xpath = "//button[@class='btn btn-sm btn-primary btn-block marginTop']")
	WebElement addToListBtn;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[2]/div[3]/table/tbody/tr/td[4]/input")
	WebElement quantity;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[2]/div[3]/table/tbody/tr/td[5]/input")
	WebElement hsnCode;
	
	@FindBy(xpath = "//span[normalize-space()='Add PO Transaction']")
	WebElement addPOTransactionBtn;
	
	@FindBy(xpath = "//input[@name='Invoice']")
	WebElement addInvoice;
	
	@FindBy(xpath = "//button[normalize-space()='Done']")
	WebElement doneBtn;
	
	@FindBy(xpath = "//button[normalize-space()='SAVE']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[2]/form/div/div[1]/div/div/div[2]/button")
	WebElement resetBtn;
	
	@FindBy(xpath = "//select[@class='form-control ng-pristine ng-invalid ng-touched']")
	WebElement submittingAt;
	
	@FindBy(xpath = "select[formcontrolname='submittedTo']")
	WebElement submittingTo;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[6]/button")
	WebElement submitMemoBtn;

	@FindBy(xpath = "//div[@class='inv-footer-text']")
	WebElement totalInvAmount;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement nextSubmitMemoBtn;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-memo-submit-confirm/div[3]/div/div[2]/button")
	WebElement finalSubmitMemoBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-final/div/div/div[2]/div[2]/button")
	WebElement printBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-final/div/div/div[2]/div[1]/button")
	WebElement homeBtn;
	
	public CreatePOBasedInvoicePage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public void clearInputFields() {
		
		baseAmount.clear();
		cd.clear();
		igst.clear();
		tcs.clear();
	}
	
	public void fillCreateNewInvoiceForm(Invoice invoice) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		invoiceNumber.sendKeys(invoice.invoiceNumber);
		subServiceCategory.sendKeys(invoice.subServiceCategory);
		baseAmount.sendKeys(invoice.baseAmount);
		tcs.sendKeys(invoice.tcs);
		cd.sendKeys(invoice.cd);
		igst.sendKeys(invoice.igst);
		endCustomer.sendKeys(invoice.endCustomer);
		comment.sendKeys(invoice.comment);
	}
	
	public void attachSampleInvoiceFile() {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		attachBtn.click();
		String invoice_file_path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\Sample_Invoice.pdf";
		addInvoice.sendKeys(invoice_file_path);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		TestUtils.waitForWebElementToBeClickable(doneBtn).click();
	}
	
	public void tagPO(String qty, String hsn_code) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		tagPOBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(poResultCheckBox).click();
		addToListBtn.click();
		quantity.clear();
		hsnCode.clear();
		quantity.sendKeys(qty);
		hsnCode.sendKeys(hsn_code);
		addPOTransactionBtn.click();
	}
	
	public void createNewInvoice(Invoice invoice) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		clearInputFields();
		fillCreateNewInvoiceForm(invoice);
		attachSampleInvoiceFile();
		tagPO(invoice.quantity, invoice.hsnCode);
	}
	
	public boolean submitMemoWithValidData(Invoice invoice){
		
		createNewInvoice(invoice);
		TestUtils.waitForWebElementToBeClickable(saveBtn).click();	// saving the current invoice
		
		// code for tagging the location and the person for the memo
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		submittingAt.sendKeys(invoice.submittingAt);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		submittingTo.sendKeys(invoice.submittingTo);
		
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
	
	public boolean submitMemoWithDuplicateData(Invoice invoice) {
		
		fillCreateNewInvoiceForm(invoice);
		return TestUtils.isSuccessToastDisplayed("Invoice Number Exist try another!");
	}
	
	public boolean createInvoiceWithoutData(Invoice invoice){
		
		fillCreateNewInvoiceForm(invoice);
		TestUtils.waitForWebElementToBeClickable(saveBtn).click();	
		resetBtn.click();
		return TestUtils.isSuccessToastDisplayed("Invoice created successfully.");
	}

	public double verifyGstCode(String base_amount, String Igst){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		clearInputFields();
		
		baseAmount.sendKeys(base_amount);
		igst.sendKeys(Igst);
		comment.click();
		
		String amount =  totalInvAmount.getText();
		amount = TestUtils.splitString(amount);
		resetBtn.click();
		return Double.parseDouble(amount);
	}

	public double verifyTdCode(String base_amount, String Cd){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		clearInputFields();
		
		baseAmount.sendKeys(base_amount);
		cd.sendKeys(Cd);
		comment.click();
		
		String amount = totalInvAmount.getText();
		amount = TestUtils.splitString(amount);
		resetBtn.click();
		return Double.parseDouble(amount);
	}

	public double verifyAdditionalAmount(String base_amount, String Tcs){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		clearInputFields();
		
		baseAmount.sendKeys(base_amount);
		tcs.sendKeys(Tcs);
		comment.click();
		
		String amount = totalInvAmount.getText();
		amount = TestUtils.splitString(amount);
		resetBtn.click();
		return Double.parseDouble(amount);
	}
	
	public boolean createMultipleInvoiceInSingleMemo (List<Invoice> invoices) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		
		for (Invoice invoice : invoices) {
			
	        createNewInvoice(invoice);
	        TestUtils.waitForElementInvisibility(By.className("modal-container"));
			TestUtils.waitForWebElementToBeClickable(saveBtn).click();				// saving the current invoice
	    }
		
		// code for tagging the location and person for the memo
		TestUtils.waitForElementInvisibility(By.className("modal-container"));	
		submittingAt.sendKeys(invoices.get(0).submittingAt);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		submittingTo.sendKeys(invoices.get(0).submittingTo);
		
		// code for actually submitting the memo
		submitMemoBtn.click();
		nextSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("//button[@type='submit']"));
		TestUtils.waitForWebElementToBeClickable(nextSubmitMemoBtn).click();
		finalSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container/div/div/app-memo-submit-confirm/div[3]/div/div[2]/button"));
		TestUtils.waitForWebElementToBeClickable(finalSubmitMemoBtn).click();
		
		//print button will be visible once the memo is submitted successfully
		TestUtils.waitForElementInvisibility(By.className("modal-container"));		
		boolean isPrintBtnVisible = TestUtils.isElementVisible(printBtn);
		return isPrintBtnVisible;	
	}
}

package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BADashboardPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-primary ng-star-inserted']")
	WebElement createMemoBtn;
	
	@FindBy(xpath = "//span[normalize-space()='Create Memo']")
	WebElement createMemoTab;
	
	@FindBy(xpath = "//input[@id='defaultCheck1']")
	WebElement POBasedInvoiceCheckbox;
	
	@FindBy(xpath = "//select[@id='frmState']")
	WebElement fromState;
	
	@FindBy(xpath = "//select[@id='toState']")
	WebElement toState;
	
	@FindBy(xpath = "//button[normalize-space()='Proceed Manually']")
	WebElement proceedManuallyBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Upload & Continue']")
	WebElement uploadAndContinueBtn;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[3]/button[1]")
	WebElement uploadBtn;

	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-lr-popup/div[3]/button[1]")
	WebElement uploadBTBtn;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[1]/div/input")
	WebElement poResultCheckBox;
	@FindBy(xpath = "//body[1]/modal-container[1]/div[1]/div[1]/app-add-lr-popup[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")
	WebElement btResultCheckBox;
	@FindBy(css = "#companyCode")
	WebElement companyCode;
	@FindBy(xpath = "//select[@id='typeService']")
	WebElement secviceType;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[1]/div/form/div[2]/div/div/input")
	WebElement searchPoInput;

	@FindBy(xpath = "//input[@placeholder='LRNUMBER2345']")
	WebElement searchBtInput;
	@FindBy(xpath = "//button[normalize-space()='Search Number']")
	WebElement searchBTBtn;

	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[1]/div/form/div[2]/div/div/div/button")
	WebElement searchPoBtn;
	@FindBy(xpath = "//button[@aria-label='Close']")
	WebElement popupCloseBtn;

	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-upload/div/div/div[2]/div/div[1]/div[2]/div/input")
	WebElement uploadFileBtn;

	@FindBy(css = ".btn.btn-warning.btn-acknowledge")
	WebElement submitFileBtn;

	@FindBy(xpath = "//button[normalize-space()='CANCEL']")
	WebElement cancelInvBtn;


	public BADashboardPage() {

		PageFactory.initElements(driver, this);
	}
	
	public CreatePOBasedInvoicePage createNewMemoPOBased(String from_state, String to_state) {
			
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(createMemoBtn).click();
		TestUtils.waitForWebElementToBeClickable(createMemoTab).click();
		POBasedInvoiceCheckbox.click();
		fromState.sendKeys(from_state);
		toState.sendKeys(to_state);
		proceedManuallyBtn.click();
		return new CreatePOBasedInvoicePage();
	}
	
	public CreatePOBasedInvoiceWithExcelsheetPage createNewMemoPOBasedWithExcelsheet(String from_state, String to_state, String po_number) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(createMemoBtn).click();
		TestUtils.waitForWebElementToBeClickable(createMemoTab).click();
		
		POBasedInvoiceCheckbox.click();
		fromState.sendKeys(from_state);
		toState.sendKeys(to_state);
		uploadAndContinueBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		
		// searching and tagging the PO to the invoice
		searchPoInput.sendKeys(po_number);
		searchPoBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(poResultCheckBox).click();
		uploadBtn.click();
		return new CreatePOBasedInvoiceWithExcelsheetPage();
	}
	public CreateNonPOBasedInvoiceWithExcelsheetPage createNewMemoNonPOBasedWithExcelsheet(String from_state,String to_state,String company_code,String service_type, String bt_number){
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(createMemoBtn).click();
		TestUtils.waitForWebElementToBeClickable(createMemoTab).click();
		fromState.sendKeys(from_state);
		toState.sendKeys(to_state);
		uploadAndContinueBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		companyCode.sendKeys(company_code);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		secviceType.sendKeys(service_type);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		// searching and tagging the PO to the invoice
		searchBtInput.sendKeys(bt_number);
		searchBTBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(btResultCheckBox).click();
		uploadBTBtn.click();
		return new CreateNonPOBasedInvoiceWithExcelsheetPage();
	}

	public CreateNonPOBasedInvoicePage createNewMemoBTBased(String from_state, String to_state){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(createMemoBtn).click();
		TestUtils.waitForWebElementToBeClickable(createMemoTab).click();
		fromState.sendKeys(from_state);
		toState.sendKeys(to_state);
		proceedManuallyBtn.click();
		return new CreateNonPOBasedInvoicePage();
	}

	public boolean summitMemoWithDuplicateData(String from_state, String to_state, String company_code, String service_type, String bt_number){
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(createMemoBtn).click();
		TestUtils.waitForWebElementToBeClickable(createMemoTab).click();
		fromState.sendKeys(from_state);
		toState.sendKeys(to_state);
		uploadAndContinueBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		companyCode.sendKeys(company_code);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		secviceType.sendKeys(service_type);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		// searching and tagging the BT to the invoice
		searchBtInput.sendKeys(bt_number);
		searchBTBtn.click();
		boolean isErrorDisplayed = TestUtils.isSuccessToastDisplayed("Error");
		TestUtils.waitForElementInvisibility(By.xpath("//div[@role='alertdialog']"));
		popupCloseBtn.click();
		return isErrorDisplayed;

	}

	public boolean submitMemoWithAnotherBt(String from_state, String to_state, String company_code, String service_type, String bt_number,String INVOICE_SHEET_PATH){
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(createMemoBtn).click();
		TestUtils.waitForWebElementToBeClickable(createMemoTab).click();
		fromState.sendKeys(from_state);
		toState.sendKeys(to_state);
		uploadAndContinueBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		companyCode.sendKeys(company_code);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		secviceType.sendKeys(service_type);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		// searching and tagging the BT to the invoice
		searchBtInput.sendKeys(bt_number);
		searchBTBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(btResultCheckBox).click();
		uploadBTBtn.click();
//		excl upload section
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		uploadFileBtn.sendKeys(INVOICE_SHEET_PATH);
		TestUtils.waitForElementVisibility(By.cssSelector(".btn.btn-warning.btn-acknowledge"));
		TestUtils.waitForWebElementToBeClickable(submitFileBtn).click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		WebElement invalideInv=TestUtils.waitForElementVisibility(By.xpath("//label[normalize-space()='Invalid Invoice']"));
		boolean isError=invalideInv.isDisplayed();
		cancelInvBtn.click();
		return isError;
	}
}


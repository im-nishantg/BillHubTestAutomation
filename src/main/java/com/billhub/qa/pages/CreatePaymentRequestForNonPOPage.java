package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class CreatePaymentRequestForNonPOPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-link']")
	WebElement navbarExpandBtn;
    
    @FindBy(xpath = "//span[normalize-space()='Dashboard']")
	WebElement dashboardBtn;
    
    @FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/perfect-scrollbar/div/div[1]/div[2]/ul/li[3]/a")
   	WebElement actionBtn;
    
    @FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/perfect-scrollbar/div/div[1]/div[2]/ul/li[3]/div/ul/li[4]/a")
	WebElement createPaymentRequest;
    
    @FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/perfect-scrollbar/div/div[1]/div[2]/ul/li[3]/div/ul/li[5]/a")
	WebElement viewPaymentRequest;
	
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/create-payment-request/div/div/div[1]/div[2]/div/input")
	WebElement searchBAInput;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/create-payment-request/div/div/div[2]/div/table/thead/tr/th[4]/input")
	WebElement billNoInput;
    
    @FindBy(xpath = "//*[@id=\"ngb-typeahead-0-0\"]")
	WebElement firstOption;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/create-payment-request/div/div/div[2]/div/table/tbody/tr[1]/td[1]/div/input")
	WebElement firstRowCheckbox;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/create-payment-request/div/div/div[1]/div[4]/button")
	WebElement addSelectedToPaymentRequestBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/create-payment-request/div/div/div[5]/div/table/tbody/tr/td[1]/div/input")
	WebElement firstRowCheckboxInRequests;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/create-payment-request/div/div/div[7]/div[2]/button")
	WebElement createRequestBtn;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-payment-request-confirm-popup/div[3]/button[1]")
	WebElement submitBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[1]/div[1]/div[3]/div/input") // WebElements of view payment requests
	WebElement searchRequestNoInput;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[1]/div[1]/div[3]/div/div/button")
	WebElement searchBtn;
    
    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-reject-payment-request/div/div[1]/div[1]/div[3]/div/typeahead-container/button")
	WebElement firstOptionInInput;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[1]/div[2]/div/table/tbody/tr/td[1]/div/input")
	WebElement firstCheckbox;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[1]/div[1]/div[2]/button")
   	WebElement downloadPaymentRequestBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[2]/div/div/button")
   	WebElement reasonInput;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[2]/div/button")
   	WebElement rejectPaymentRequestBtn;
    
    @FindBy(xpath = "//td[normalize-space()='Payment rejected successfully']")
	WebElement paymentRejectSuccessMessage;

    @FindBy(xpath = "//a[normalize-space()='Filter']")
    WebElement filterBtn;
    
    @FindBy(css = "input[formcontrolname='inv']")
    WebElement invoice;
    
    @FindBy(css = "button[type='submit']")
    WebElement applyBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[4]/label")
    WebElement firstRowInvoiceNumber;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-invoice-info-popup/div[3]/div/div/button")
    WebElement closeBtn;
    
    
	public CreatePaymentRequestForNonPOPage() {
		PageFactory.initElements(driver, this);
	}

	// ************************  Functions associated with Create Payment Request Page ************************
	
	
	public void clickOnCreatePaymentRequestLink() {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(createPaymentRequest).click();
    	TestUtils.waitForElementInvisibility(By.className("loader"));
	}
	
	public boolean searchInvoicesByValidBaName(String ba_name) {
		
		clickOnCreatePaymentRequestLink();
    	searchBAInput.sendKeys(ba_name);
    	TestUtils.waitForWebElementToBeClickable(firstOption).click();
    	return TestUtils.isElementVisible(firstRowCheckbox);
	}

	public boolean searchInvoicesByInvalidBaName(String ba_name) {
		
		clickOnCreatePaymentRequestLink();
    	searchBAInput.sendKeys(ba_name);
    	return TestUtils.isElementVisible(firstRowCheckbox);
	}

	public boolean validateExcelsheetData(String ba_name, String invoice_number){
		
		clickOnCreatePaymentRequestLink();
    	
    	searchBAInput.sendKeys(ba_name);
    	TestUtils.waitForWebElementToBeClickable(firstOption).click();
    	TestUtils.waitForElementInvisibility(By.className("loader"));
    	
    	billNoInput.sendKeys(invoice_number);
    	TestUtils.waitForElementInvisibility(By.className("loader"));
    	
    	firstRowCheckbox.click();
    	addSelectedToPaymentRequestBtn.click();
    	
    	TestUtils.waitForWebElementToBeClickable(firstRowCheckboxInRequests).click();
    	createRequestBtn.click();
    	
    	TestUtils.waitForElementInvisibility(By.className("loader"));
    	submitBtn.click();
    	
		return TestUtils.isSuccessToastDisplayed("Create Payment Request");
	}
	
	public void readPaymentRequestNumber(String invoice_number) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForWebElementToBeClickable(dashboardBtn).click();
		TestUtils.waitForElementInvisibility(By.className("loader"));

		filterBtn.click();
    	invoice.sendKeys(invoice_number);
    	applyBtn.click();
    	TestUtils.waitForElementInvisibility(By.className("loader"));
    	
    	firstRowInvoiceNumber.click();
    	WebElement paymentRequestNumber = TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container/div/div/app-invoice-info-popup/div[2]/div[2]/div[1]/div/div[3]/div[2]/table/tbody/tr/td[2]"));
    	String payment_request_number = paymentRequestNumber.getText();
    	
    	TestUtils.setCellData("CreatePaymentRequestForPo", 1, 3, payment_request_number);
    	closeBtn.click();
	}
	
	// ************************  Functions associated with View Payment Request Page ************************
	
	public void clickOnViewPaymentRequestLink() {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(viewPaymentRequest).click();
    	TestUtils.waitForElementInvisibility(By.className("loader"));
	}
	
	public boolean searchWithValidRequestNumber(String request_number) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(viewPaymentRequest).click();
    	TestUtils.waitForElementInvisibility(By.className("loader"));
    	
		searchRequestNoInput.sendKeys(request_number);
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForWebElementToBeClickable(firstOptionInInput).click();
		searchBtn.click();
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return TestUtils.isElementVisible(firstCheckbox);
	}
	
	public boolean searchWithInvalidRequestNumber(String request_number) {
		
		clickOnViewPaymentRequestLink();
		searchRequestNoInput.sendKeys(request_number);
		TestUtils.waitForElementInvisibility(By.className("loader"));
		searchBtn.click();
		return TestUtils.isElementVisible(firstCheckbox);
	}
	
	public boolean ValidateDownloadPaymentRequest(String request_number) {
		
		searchWithValidRequestNumber(request_number);
		TestUtils.waitForElementInvisibility(By.className("loader"));
		firstCheckbox.click();
		TestUtils.waitForElementInvisibility(By.className("loader"));
		downloadPaymentRequestBtn.click();
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return true;
	}

	public boolean ValidateRejectPaymentRequest(String request_number, String reason) {
	    
		searchWithValidRequestNumber(request_number);
	    firstCheckbox.click();
	    reasonInput.click();
	    
	    String Optionxpath = "//a[normalize-space()='" + reason + "']"; 
	    TestUtils.waitForElementToBeClickable(By.xpath(Optionxpath)).click();
	    
	    TestUtils.waitForElementInvisibility(By.className("loader"));
	    rejectPaymentRequestBtn.click();
	    
	    return TestUtils.isElementVisible(paymentRejectSuccessMessage);
	}

}

package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class CreatePaymentRequestPage extends TestBase{
	
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
    
    @FindBy(xpath = "//*[@id=\"ngb-typeahead-5-0\"]/span/strong")
	WebElement firstOptionInInput;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[1]/div[2]/div/table/tbody/tr/td[1]/div/input")
	WebElement firstCheckbox;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[1]/div[1]/div[2]/button")
   	WebElement downloadPaymentRequestBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[2]/div/div/button")
   	WebElement reasonInput;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-reject-payment-request/div/div[2]/div/button")
   	WebElement rejectPaymentRequestBtn;
    
	public CreatePaymentRequestPage() {
		PageFactory.initElements(driver, this);
	}

	// ************************  Functions associated with Create Payment Request Page ************************
	
	
	public void clickOnCreatePaymentRequestLink() {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(createPaymentRequest).click();
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
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

	public boolean validateExcelsheetData(String ba_name) {
		
		clickOnCreatePaymentRequestLink();
    	
    	searchBAInput.sendKeys(ba_name);
    	TestUtils.waitForWebElementToBeClickable(firstOption).click();
    	firstRowCheckbox.click();
    	addSelectedToPaymentRequestBtn.click();
    	
    	TestUtils.waitForWebElementToBeClickable(firstRowCheckboxInRequests).click();
    	createRequestBtn.click();
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	submitBtn.click();
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
		return true;
	}
	
	// ************************  Functions associated with View Payment Request Page ************************
	
	public void clickOnViewPaymentRequestLink() {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(viewPaymentRequest).click();
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
	}
	
	public boolean searchWithValidRequestNumber(String request_number) {
		
		clickOnViewPaymentRequestLink();
		searchRequestNoInput.sendKeys(request_number);
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(firstOptionInInput).click();
		searchBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		return TestUtils.isElementVisible(firstCheckbox);
	}
}

package com.billhub.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;

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
    
	public CreatePaymentRequestPage() {
		PageFactory.initElements(driver, this);
	}
		
	
	
}

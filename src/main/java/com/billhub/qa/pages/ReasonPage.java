package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class ReasonPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement addReasonBtn;
	
	@FindBy(xpath = "//input[@id='Reason_Name']")
	WebElement searchByReasonName;
	
	@FindBy(xpath = "//input[@id='Reason_code']")
	WebElement searchByReasonCode;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchBtn;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-edit-reason/div[2]/div/div/form/div/div[1]/input")
    WebElement reasonCode;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-add-edit-reason/div[2]/div/div/form/div/div[2]/input")
    WebElement reasonName;

    @FindBy(xpath = "//*[@id=\"type\"]")
    WebElement typeInput;

    @FindBy(css = "#defaultCheck2")
    WebElement activeBtn;

    @FindBy(css = "button[class='btn btn-primary btn-done']")
    WebElement addBtn;

    @FindBy(css = "button[class='btn btn-danger btn-done']")
    WebElement closeBtn;

    @FindBy(css = "button[class='btn btn-primary btn-done'] span")
    WebElement updateBtn;
	
	public ReasonPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void initializePopupWebElements() {
		PageFactory.initElements(driver, this);
	}
	
	public void fillAddReasonForm(String reason_code, String reason_name, String type) {
		
		addReasonBtn.click();
        initializePopupWebElements();
        
        reasonCode.sendKeys(reason_code);
        reasonName.sendKeys(reason_name);
        typeInput.sendKeys(type);
	}
	
	public boolean addNewReasonWithValidData(String reason_code, String reason_name, String type){
		
		TestUtils.waitForToastToDisappear();
		fillAddReasonForm(reason_code, reason_name, type);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Reason Added successfully");
	}
	
	public boolean addNewReasonWithInvalidData(String reason_code, String reason_name, String type){
		
		TestUtils.waitForToastToDisappear();
		fillAddReasonForm(reason_code, reason_name, type);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Reason Added successfully");
	}
	
	public boolean addNewReasonWithoutData(String reason_code, String reason_name, String type){
		
		TestUtils.waitForToastToDisappear();
		fillAddReasonForm(reason_code, reason_name, type);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Reason Added successfully");
	}
	
	public boolean addNewReasonWithDuplicateData(String reason_code, String reason_name, String type){
		
		TestUtils.waitForToastToDisappear();
		fillAddReasonForm(reason_code, reason_name, type);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Reason Added successfully");
	}
	
	public boolean searchByReasonName(String reason_name) {
		
		searchByReasonCode.clear();
		searchByReasonName.clear();
		searchByReasonName.sendKeys(reason_name);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-reason/div/div/div[3]/div/table/tbody/tr/td[3]"), reason_name);
	}

	public boolean searchByReasonCode(String reason_code) {
		
		searchByReasonName.clear();
		searchByReasonCode.clear();
		searchByReasonCode.sendKeys(reason_code);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-reason/div/div/div[3]/div/table/tbody/tr/td[2]"), reason_code);
	}
	
	public boolean updateReason(String reason_code, String reason_name, String type) {
		
		TestUtils.waitForToastToDisappear();
		searchByReasonCode(reason_code);
		
		WebElement editBtn = TestUtils.locateAndClickEditBtn(By.cssSelector("i[class='fa fa-edit']"));  
		reasonName = TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container/div/div/app-add-edit-reason/div[2]/div/div/form/div/div[2]/input")); 
		typeInput = TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"type\"]")); 

	    reasonName.clear();
	    
	    reasonName.sendKeys(reason_name);
	    typeInput.sendKeys(type);
	    
	    updateBtn.click();
	    
	    return TestUtils.isSuccessToastDisplayed("Reason updated successfully");
		
	}
}

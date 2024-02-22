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
	
	WebElement reasonCode, reasonName, typeInput, activeBtn, addBtn, closeBtn, updateBtn;
	
	public ReasonPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void initializePopupWebElements() {
        
		reasonCode = driver.findElement(By.xpath("/html/body/modal-container/div/div/app-add-edit-reason/div[2]/div/div/form/div/div[1]/input"));
		reasonName = driver.findElement(By.xpath("/html/body/modal-container/div/div/app-add-edit-reason/div[2]/div/div/form/div/div[2]/input"));
		typeInput = driver.findElement(By.xpath("//*[@id=\"type\"]"));
		activeBtn = driver.findElement(By.cssSelector("#defaultCheck2"));
		addBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
		closeBtn = driver.findElement(By.cssSelector("button[class='btn btn-danger btn-done']"));
	}
	
	public void fillAddReasonForm(String reason_code, String reason_name, String type) {
		
		addReasonBtn.click();
        initializePopupWebElements();
        
        reasonCode.sendKeys(reason_code);
        reasonName.sendKeys(reason_name);
        typeInput.sendKeys(type);
	}
	
	public boolean addNewReasonWithValidData(String reason_code, String reason_name, String type){
		
		fillAddReasonForm(reason_code, reason_name, type);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Reason Added successfully");
	}
	
	public boolean addNewReasonWithInvalidData(String reason_code, String reason_name, String type){
		
		fillAddReasonForm(reason_code, reason_name, type);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Reason Added successfully");
	}
	
	public boolean addNewReasonWithoutData(String reason_code, String reason_name, String type){
		
		fillAddReasonForm(reason_code, reason_name, type);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Reason Added successfully");
	}
	
	public boolean addNewReasonWithDuplicateData(String reason_code, String reason_name, String type){
		
		fillAddReasonForm(reason_code, reason_name, type);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Reason Added successfully");
	}
	
	public boolean searchByReasonName(String reason_name) {
		
		searchByReasonName.sendKeys(reason_name);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-reason/div/div/div[3]/div/table/tbody/tr/td[3]"), reason_name);
	}

	public boolean searchByReasonCode(String reason_code) {
		
		searchByReasonCode.sendKeys(reason_code);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-reason/div/div/div[3]/div/table/tbody/tr/td[2]"), reason_code);
	}
	
	public boolean updateReason(String reason_code, String reason_name, String type) {
		
		searchByReasonCode(reason_code);
		
		WebElement editBtn = TestUtils.locateAndClickEditBtn(By.cssSelector("i[class='fa fa-edit']"));  
		reasonName = TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container/div/div/app-add-edit-reason/div[2]/div/div/form/div/div[2]/input")); 
		typeInput = TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"type\"]")); 
	    updateBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done'] span"));
	    
	    reasonName.clear();
	    
	    reasonName.sendKeys(reason_name);
	    typeInput.sendKeys(type);
	    
	    updateBtn.click();
	    
	    return TestUtils.isSuccessToastDisplayed("Reason updated successfully");
		
	}
}

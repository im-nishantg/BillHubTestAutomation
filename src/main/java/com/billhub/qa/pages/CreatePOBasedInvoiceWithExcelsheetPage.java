package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class CreatePOBasedInvoiceWithExcelsheetPage extends TestBase{
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-upload/div/div/div[2]/div/div[1]/div[2]/div/input")
	WebElement uploadFileBtn;
	
	@FindBy(css = ".btn.btn-warning.btn-acknowledge")
	WebElement uploadBtn;
	
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
}

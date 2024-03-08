package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

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
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[2]/div[1]/table/tbody/tr[1]/td[1]/div/input")
	WebElement poResultCheckBox;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[1]/div/form/div[2]/div/div/input")
	WebElement searchPoInput;
	
	@FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[1]/div/form/div[2]/div/div/div/button")
	WebElement searchPoBtn;


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

	public CreateNonPOBasedInvoicePage createNewBTBased(){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(createMemoBtn).click();
		TestUtils.waitForWebElementToBeClickable(createMemoTab).click();
		fromState.sendKeys("Rajasthan");
		toState.sendKeys("Kerala");
		proceedManuallyBtn.click();
		return new CreateNonPOBasedInvoicePage();
	}
	

}


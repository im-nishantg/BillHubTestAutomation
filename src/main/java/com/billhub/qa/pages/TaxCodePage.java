package com.billhub.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;

public class TaxCodePage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement addTaxCodeBtn;
	
	public TaxCodePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnAddTaxCodeBtn() {
		addTaxCodeBtn.click();
	}
}

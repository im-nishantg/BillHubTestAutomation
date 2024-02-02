package com.billhub.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;

public class CustomerPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement addCustomerBtn;
	
	public CustomerPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnAddCustomerBtn() throws InterruptedException {
		addCustomerBtn.click();
		Thread.sleep(Duration.ofSeconds(4));
	}
	
}

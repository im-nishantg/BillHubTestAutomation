package com.billhub.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;

public class BaPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement addBaBtn;
	
	public BaPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnAddBaBtn() throws InterruptedException {
		addBaBtn.click();
		Thread.sleep(Duration.ofSeconds(4).toMillis());
	}
}

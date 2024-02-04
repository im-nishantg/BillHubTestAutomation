package com.billhub.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.billhub.qa.base.TestBase;

public class BAPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement addBaBtn;
	
	WebElement baCode, baName, stateName, tradeDiscount, creditPeriod, tdCreditPeriod, baGroupCode, isMSMED;
	WebElement emailID, contactPersonName, contactPersonNumber, activeBtn, addBtn, closeBtn;
	
	
	public BAPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void initializePopupWebElements() {
		
		baCode = driver.findElement(By.xpath("//input[@formcontrolname='ba_code']"));
		baName = driver.findElement(By.xpath("//input[@formcontrolname='ba_name']"));
		stateName = driver.findElement(By.xpath("//select[@id='state-id']"));
		tradeDiscount = driver.findElement(By.xpath("//input[@formcontrolname='tradeDiscount']"));
		creditPeriod = driver.findElement(By.xpath("//input[@formcontrolname='credit_Period']"));
		tdCreditPeriod = driver.findElement(By.xpath("//input[@formcontrolname='td_Credit_Period']"));
		baGroupCode = driver.findElement(By.xpath("//input[@formcontrolname='ba_group_id']"));
		isMSMED = driver.findElement(By.xpath("//select[@formcontrolname='is_msmed']"));
		emailID = driver.findElement(By.xpath("//input[@formcontrolname='email_id']"));
		contactPersonName = driver.findElement(By.xpath("//input[@formcontrolname='contact_name']"));
		contactPersonNumber = driver.findElement(By.xpath("//input[@formcontrolname='contact_number']"));
		activeBtn = driver.findElement(By.xpath("//input[@id='defaultCheck2']"));
		addBtn = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-done']"));
		closeBtn = driver.findElement(By.xpath("//button[normalize-space()='Close']"));

	}
	
	public void clickOnAddBaBtn() {
        try {
            addBaBtn.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for BA code input field to be clickable and visible
            WebElement baCodeInput = wait.until(ExpectedConditions.elementToBeClickable(baCode));
            wait.until(ExpectedConditions.visibilityOf(baCodeInput));

            baCodeInput.sendKeys("124");
            baName.sendKeys("Nishant");
            
            // Continue entering data for other fields...

            // Click the close button
            closeBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

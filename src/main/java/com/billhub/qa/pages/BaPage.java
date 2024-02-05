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
		
		baCode = driver.findElement(By.cssSelector("div[class='card-body p-2'] div:nth-child(1) div:nth-child(1) input:nth-child(1)"));
		baName = driver.findElement(By.cssSelector("div[class='card-body p-2'] div:nth-child(1) div:nth-child(2) input:nth-child(1)"));
		stateName = driver.findElement(By.cssSelector("#state-id"));
		tradeDiscount = driver.findElement(By.cssSelector("div[class='modal-body pb-0'] div:nth-child(2) div:nth-child(2) input:nth-child(1)"));
		creditPeriod = driver.findElement(By.cssSelector("div:nth-child(3) div:nth-child(1) input:nth-child(1)"));
		tdCreditPeriod = driver.findElement(By.cssSelector("div:nth-child(3) div:nth-child(2) input:nth-child(1)"));
		baGroupCode = driver.findElement(By.cssSelector("div:nth-child(4) div:nth-child(1) input:nth-child(1)"));
		isMSMED = driver.findElement(By.cssSelector("#msmed-id"));
		emailID = driver.findElement(By.cssSelector("input[formcontrolname='email_id']"));
		contactPersonName = driver.findElement(By.cssSelector("input[formcontrolname='contact_name']"));
		contactPersonNumber = driver.findElement(By.cssSelector("input[formcontrolname='contact_number']"));
		activeBtn = driver.findElement(By.cssSelector("#defaultCheck2"));
		addBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
		closeBtn = driver.findElement(By.cssSelector("button[class='btn btn-danger btn-done']"));
	}

	public boolean addNewBA(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) throws InterruptedException {
		addBaBtn.click();
        initializePopupWebElements();
        
        baCode.sendKeys(ba_code);
        baName.sendKeys(ba_name);
        stateName.sendKeys(state);
        tradeDiscount.sendKeys(trd_disc);
        creditPeriod.sendKeys(credit_period);
        tdCreditPeriod.sendKeys(td_credit_period);
        baGroupCode.sendKeys(ba_groupcode);
        isMSMED.sendKeys(msmed);
        emailID.sendKeys(email);
        contactPersonName.sendKeys(contact_person_name);
        contactPersonNumber.sendKeys(contact_person_number);
        addBtn.click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='BA Data Added successfully']")));
        return successToast.isDisplayed();
	}
}

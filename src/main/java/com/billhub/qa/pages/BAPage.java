package com.billhub.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class BAPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement addBaBtn;
	
	@FindBy(xpath = "//input[@id='invoiceNumber']")
	WebElement searchBAByName;
	
	@FindBy(xpath = "//input[@id='tokenID']")
	WebElement searchBAByCode;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchBtn;
	
	WebElement baCode, baName, stateName, tradeDiscount, creditPeriod, tdCreditPeriod, baGroupCode, isMSMED;
	WebElement emailID, contactPersonName, contactPersonNumber, activeBtn, addBtn, closeBtn, editBtn, updateBtn;
	
	
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

	public void fillAddNewBAForm(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number){
		
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
	}
	
	public boolean addNewBAWithValidData(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) {
		
		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
	    addBtn.click();
	    closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("BA Data Added successfully");
	}
	
	public boolean addNewBAWithInvalidData(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) {
		
		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		addBtn.click();
	    closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("BA Data Added successfully");
	}
	
	public boolean addNewBAWithoutData(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) {
		
		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		addBtn.click();
	    closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Kindly fill out all the mandatory fields");
	}
	
	public boolean addNewBAWithDuplicateData(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) {
		
		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		addBtn.click();
	    closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("BA Data Added successfully");
	}
	
	public boolean addNewBAWithInactiveStatus(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) {

		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		activeBtn.click();
		addBtn.click();
	    closeBtn.click();	
		return TestUtils.isSuccessToastDisplayed("BA Data Added successfully");
	}
	
	public String addNewBAWithActiveStatus(String ba_code) {
		
	    if(searchBAByCode(ba_code) == false)	// if BA is not found in DB then its status is neither active or deactive
	    	return "";
	    
	    WebElement isActive = TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-ba/div/div/div[3]/div/table/tbody/tr[1]/td[9]"));
	    return isActive.getText();
	}
	
	public boolean validateAddedBAInTheDatabase(String ba_code) {
		
		return searchBAByCode(ba_code);
	}
	
	public boolean searchBAByName(String ba_name) {
		
		searchBAByName.sendKeys(ba_name);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-ba/div/div/div[3]/div/table/tbody/tr[1]/td[3]"), ba_name);
	}
	
	public boolean searchBAByCode(String ba_code) {
		
		searchBAByCode.sendKeys(ba_code);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-ba/div/div/div[3]/div/table/tbody/tr[1]/td[2]"), ba_code);
	}
	
	public boolean updateBA(String ba_code, String ba_name, String contact_person_number){
	    
		searchBAByCode.sendKeys(ba_code);
	    searchBtn.click();
	    
	    WebElement editBtn = TestUtils.locateAndClickEditBtn(By.cssSelector("tbody tr:nth-child(1) td:nth-child(11) i:nth-child(1)"));  
	    baName = TestUtils.waitForElementVisibility(By.cssSelector("div[class='card-body p-2'] div:nth-child(1) div:nth-child(2) input:nth-child(1)")); 
	    contactPersonNumber = TestUtils.waitForElementVisibility(By.cssSelector("input[formcontrolname='contact_number']")); 
	    updateBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
	    
	    baName.clear();
	    contactPersonNumber.clear();
	    
	    baName.sendKeys(ba_name);
	    contactPersonNumber.sendKeys(contact_person_number);
	    
	    updateBtn.click();

	    return TestUtils.isSuccessToastDisplayed("BA Data Updated successfully");
	}
	
}

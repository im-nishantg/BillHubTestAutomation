package com.billhub.qa.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	
    @FindBy(css = "div[class='card-body p-2'] div:nth-child(1) div:nth-child(1) input:nth-child(1)")
    WebElement baCode;

    @FindBy(css = "div[class='card-body p-2'] div:nth-child(1) div:nth-child(2) input:nth-child(1)")
    WebElement baName;

    @FindBy(css = "#state-id")
    WebElement stateName;

    @FindBy(css = "div[class='modal-body pb-0'] div:nth-child(2) div:nth-child(2) input:nth-child(1)")
    WebElement tradeDiscount;

    @FindBy(css = "div:nth-child(3) div:nth-child(1) input:nth-child(1)")
    WebElement creditPeriod;

    @FindBy(css = "div:nth-child(3) div:nth-child(2) input:nth-child(1)")
    WebElement tdCreditPeriod;

    @FindBy(css = "div:nth-child(4) div:nth-child(1) input:nth-child(1)")
    WebElement baGroupCode;

    @FindBy(css = "#msmed-id")
    WebElement isMSMED;

    @FindBy(css = "input[formcontrolname='email_id']")
    WebElement emailID;

    @FindBy(css = "input[formcontrolname='contact_name']")
    WebElement contactPersonName;

    @FindBy(css = "input[formcontrolname='contact_number']")
    WebElement contactPersonNumber;

    @FindBy(css = "#defaultCheck2")
    WebElement activeBtn;

    @FindBy(css = "button[class='btn btn-primary btn-done']")
    WebElement addBtn;

    @FindBy(css = "button[class='btn btn-danger btn-done']")
    WebElement closeBtn;

    @FindBy(css = "button[class='btn btn-primary btn-done'] span")
    WebElement updateBtn;
	
	
	public BAPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void initializePopupWebElements() {
		PageFactory.initElements(driver, this);
	}

	public void fillAddNewBAForm(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number){
		
		addBaBtn.click();
		TestUtils.waitForElementInvisibility(By.className("loader"));
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
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
	    
		boolean isAdded = TestUtils.isSuccessToastDisplayed("BA Data Added successfully");
		if(isAdded == false) closeBtn.click();	
		return isAdded;
	}
	
	public boolean addNewBAWithInvalidData(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
	    
		boolean isAdded = TestUtils.isSuccessToastDisplayed("BA Data Added successfully");
		if(isAdded == false) closeBtn.click();	
		return isAdded;
	}
	
	public boolean addNewBAWithoutData(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
	    
		boolean isAdded = TestUtils.isSuccessToastDisplayed("BA Data Added successfully");
		if(isAdded == false) closeBtn.click();	
		return isAdded;
	}
	
	public boolean addNewBAWithDuplicateData(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("BA Data Added successfully");
		if(isAdded == false) closeBtn.click();	
		return isAdded;
	}
	
	public boolean addNewBAWithInactiveStatus(String ba_code, String ba_name, String state, String trd_disc, String credit_period, String td_credit_period, String ba_groupcode, String msmed, String email, String contact_person_name, String contact_person_number) {

		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		
		fillAddNewBAForm(ba_code, ba_name, state, trd_disc, credit_period, td_credit_period, ba_groupcode, msmed, email, contact_person_name, contact_person_number);
		TestUtils.waitForElementInvisibility(By.className("loader"));
		activeBtn.click();
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("BA Data Added successfully");
		if(isAdded == false) closeBtn.click();	
		return isAdded;
	}
	
	public String addNewBAWithActiveStatus(String ba_code) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		
	    if(searchBAByCode(ba_code) == false)	// if BA is not found in DB then its status is neither active or deactive
	    	return "";
	    
	    WebElement isActive = TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-ba/div/div/div[3]/div/table/tbody/tr[1]/td[9]"));
	    return isActive.getText();
	}
	
	public boolean validateAddedBAInTheDatabase(String ba_code) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return searchBAByCode(ba_code);
	}
	
	public boolean searchBAByName(String ba_name) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		searchBAByCode.clear();
		searchBAByName.clear();
		searchBAByName.sendKeys(ba_name);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		searchBtn.click();
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-ba/div/div/div[3]/div/table/tbody/tr[1]/td[3]"), ba_name);
	}
	
	public boolean searchBAByCode(String ba_code) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		searchBAByName.clear();
		searchBAByCode.clear();
		searchBAByCode.sendKeys(ba_code);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		searchBtn.click();
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-ba/div/div/div[3]/div/table/tbody/tr[1]/td[2]"), ba_code);
	}
	
	public boolean updateBA(String ba_code, String ba_name, String contact_person_number){
	    
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		searchBAByCode(ba_code);
	    
	    WebElement editBtn = TestUtils.locateAndClickEditBtn(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-ba/div/div/div[3]/div/table/tbody/tr/td[11]/i"));  
	    baName = TestUtils.waitForElementVisibility(By.cssSelector("div[class='card-body p-2'] div:nth-child(1) div:nth-child(2) input:nth-child(1)")); 
	    contactPersonNumber = TestUtils.waitForElementVisibility(By.cssSelector("input[formcontrolname='contact_number']")); 

	    baName.clear();
	    contactPersonNumber.clear();
	    
	    baName.sendKeys(ba_name);
	    contactPersonNumber.sendKeys(contact_person_number);
	    
	    TestUtils.waitForElementInvisibility(By.className("loader"));
	    updateBtn.click();
	    
	    return TestUtils.isSuccessToastDisplayed("BA Data Updated successfully");
	}
	
}

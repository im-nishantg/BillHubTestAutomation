package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class MdmDashboardPage extends TestBase{
	
	@FindBy(xpath="//button[@class='btn btn-link']")
	WebElement navbarExpandBtn;
	
	@FindBy(xpath="//span[normalize-space()='Master']")
	WebElement masterBtn;
	
	@FindBy(linkText = "Customer")
	WebElement customerBtn;
	
	@FindBy(linkText = "BA")
	WebElement baBtn;
	
	@FindBy(linkText = "User")
	WebElement userBtn;
	
	@FindBy(linkText = "Withholding Tax")
	WebElement withHoldingTaxBtn;
	
	@FindBy(linkText = "Role")
	WebElement roleBtn;
	
	@FindBy(linkText = "Tax Code")
	WebElement taxCodeBtn;
	
	@FindBy(linkText = "Reason")
	WebElement reasonBtn;
	
	public MdmDashboardPage() {
		
		PageFactory.initElements(driver, this);
	}

	public CustomerPage clickOnCustomerLink(){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();		
		TestUtils.waitForWebElementToBeClickable(masterBtn).click();	
		TestUtils.waitForWebElementToBeClickable(customerBtn).click();
		return new CustomerPage();
	}
	
	public BAPage clickOnBaLink(){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();		
		TestUtils.waitForWebElementToBeClickable(masterBtn).click();	
		TestUtils.waitForWebElementToBeClickable(baBtn).click();
		return new BAPage();
	}

	public UserPage clickOnUserLink(){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();		
		TestUtils.waitForWebElementToBeClickable(masterBtn).click();	
		TestUtils.waitForWebElementToBeClickable(userBtn).click();
		return new UserPage();
	}

	public WithholdingTaxPage clickOnWithholdingTaxLink(){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();		
		TestUtils.waitForWebElementToBeClickable(masterBtn).click();	
		TestUtils.waitForWebElementToBeClickable(withHoldingTaxBtn).click();
		return new WithholdingTaxPage();
	}

	public RolePage clickOnRoleLink(){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();		
		TestUtils.waitForWebElementToBeClickable(masterBtn).click();
		TestUtils.waitForWebElementToBeClickable(roleBtn).click();
		return new RolePage();
	}

	public TaxCodePage clickOnTaxCodeLink(){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();		
		TestUtils.waitForWebElementToBeClickable(masterBtn).click();
		TestUtils.waitForWebElementToBeClickable(taxCodeBtn).click();
		return new TaxCodePage();
	}

	public ReasonPage clickOnReasonLink(){
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();		
		TestUtils.waitForWebElementToBeClickable(masterBtn).click();
		TestUtils.waitForWebElementToBeClickable(reasonBtn).click();
		return new ReasonPage();
	}


}
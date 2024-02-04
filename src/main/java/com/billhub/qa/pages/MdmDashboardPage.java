package com.billhub.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.billhub.qa.base.TestBase;

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
	
	public CustomerPage clickOnCustomerLink() throws InterruptedException{
		Thread.sleep(Duration.ofSeconds(5));
		navbarExpandBtn.click();
		masterBtn.click();
		customerBtn.click();
		return new CustomerPage();
	}
	
	public BAPage clickOnBaLink() throws InterruptedException {
		Thread.sleep(Duration.ofSeconds(5));
		navbarExpandBtn.click();
		masterBtn.click();
		baBtn.click();
		return new BAPage();
	}
	
	public UserPage clickOnUserLink() throws InterruptedException{
		Thread.sleep(Duration.ofSeconds(5));
		navbarExpandBtn.click();
		masterBtn.click();
		userBtn.click();
		return new UserPage();
	}
	
	public WithholdingTaxPage clickOnWithholdingTaxLink() throws InterruptedException{
		Thread.sleep(Duration.ofSeconds(5));
		navbarExpandBtn.click();
		masterBtn.click();
		withHoldingTaxBtn.click();
		return new WithholdingTaxPage();
	}
	
	public RolePage clickOnRoleLink() throws InterruptedException{
		Thread.sleep(Duration.ofSeconds(5));
		navbarExpandBtn.click();
		masterBtn.click();
		roleBtn.click();
		return new RolePage();
	}
	
	public TaxCodePage clickOnTaxCodeLink() throws InterruptedException{
		Thread.sleep(Duration.ofSeconds(5));
		navbarExpandBtn.click();
		masterBtn.click();
		taxCodeBtn.click();
		return new TaxCodePage();
	}
	
	public ReasonPage clickOnReasonLink() throws InterruptedException{
		Thread.sleep(Duration.ofSeconds(5));
		navbarExpandBtn.click();
		masterBtn.click();
		reasonBtn.click();
		return new ReasonPage();
	}
	
}

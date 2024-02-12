package com.billhub.qa.pages;
import com.billhub.qa.base.TestBase;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-container")));
        wait.until(ExpectedConditions.elementToBeClickable(navbarExpandBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(masterBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(customerBtn)).click();
		return new CustomerPage();
	}
	
	public BAPage clickOnBaLink(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-container")));
        wait.until(ExpectedConditions.elementToBeClickable(navbarExpandBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(masterBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(baBtn)).click();
		return new BAPage();
	}

	public UserPage clickOnUserLink(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-container")));
        wait.until(ExpectedConditions.elementToBeClickable(navbarExpandBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(masterBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(userBtn)).click();
		return new UserPage();
	}

	public WithholdingTaxPage clickOnWithholdingTaxLink(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-container")));
        wait.until(ExpectedConditions.elementToBeClickable(navbarExpandBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(masterBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(withHoldingTaxBtn)).click();
		return new WithholdingTaxPage();
	}

	public RolePage clickOnRoleLink(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-container")));
        wait.until(ExpectedConditions.elementToBeClickable(navbarExpandBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(masterBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(roleBtn)).click();
		return new RolePage();
	}

	public TaxCodePage clickOnTaxCodeLink(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-container")));
        wait.until(ExpectedConditions.elementToBeClickable(navbarExpandBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(masterBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(taxCodeBtn)).click();
		return new TaxCodePage();
	}

	public ReasonPage clickOnReasonLink(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-container")));
        wait.until(ExpectedConditions.elementToBeClickable(navbarExpandBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(masterBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(reasonBtn)).click();
		return new ReasonPage();
	}


}
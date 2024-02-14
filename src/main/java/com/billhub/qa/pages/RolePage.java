package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RolePage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement addRoleBtn;
	@FindBy(xpath = "//input[@id='invoiceNumber']")
	WebElement inputRoleName;

	@FindBy(xpath = "//input[@id='tokenID']")
	WebElement inputRoleCode;
	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement roleSearchBtn;

	@FindBy(xpath = "//tbody/tr[1]/td[4]/i[1]")
	WebElement editRoleBtn;
	
	public RolePage() {
		PageFactory.initElements(driver, this);
	}



	WebElement roleCodeInput,roleNameInput,addNewRoleBtn;

	public void initializePopupWebElements(){
		roleCodeInput = driver.findElement(By.cssSelector("#invoiceNumber"));
		roleNameInput= driver.findElement(By.cssSelector("#tokenID"));
		addNewRoleBtn=driver.findElement(By.cssSelector("button[type='submit']"));
	}

	public boolean validateAddCustomerWithValidData(String roleCode, String roleName) throws InterruptedException {
		addRoleBtn.click();
		Thread.sleep(Duration.ofSeconds(5).toMillis());
		initializePopupWebElements();

		roleCodeInput.sendKeys(roleCode);
		roleNameInput.sendKeys(roleName);
		addNewRoleBtn.click();

		try{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".overlay-container")));
			return successToast.isDisplayed();
		}catch (TimeoutException e){
			return false;
		}

	}
}

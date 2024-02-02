package com.billhub.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(id = "emailInput")
	WebElement userId;
	
	@FindBy(id = "passwordInput")
	WebElement password;
	
	@FindBy(id = "login")
	WebElement loginBtn;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public BADashboard login(String userID, String passWord)
	{
		userId.sendKeys(userID);
		password.sendKeys(passWord);
		loginBtn.click();
		return new BADashboard();
	}
	
}

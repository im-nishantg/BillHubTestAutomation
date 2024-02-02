package com.billhub.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;

public class LoginPage extends TestBase{

	@FindBy(xpath = "//input[@id='passwordInput']")
	WebElement PasswordInput;
	@FindBy(xpath = "//input[@id='emailInput']")
	WebElement UserInput;
	@FindBy(xpath = "//button[@id='login']")
	WebElement LoginBtn;



	public LoginPage(){
		PageFactory.initElements(driver,this);
	}

	public MdmDashboardPage loginAsMdm(String user,String pass){
		UserInput.sendKeys(user);
		PasswordInput.sendKeys(pass);
		LoginBtn.click();
		return new MdmDashboardPage();

	}

	public BaDashboardPage loginAsBa(String user, String pass){
		UserInput.sendKeys(user);
		PasswordInput.sendKeys(pass);
		LoginBtn.click();
		return new BaDashboardPage();
	}
	public CommercialDashboardPage loginAsCommercial(String user, String pass){
		UserInput.sendKeys(user);
		PasswordInput.sendKeys(pass);
		LoginBtn.click();
		return new CommercialDashboardPage();
	}

	public AccountDashboardPage loginAsAccount(String user, String pass){
		UserInput.sendKeys(user);
		PasswordInput.sendKeys(pass);
		LoginBtn.click();
		return new AccountDashboardPage();
	}
	public TaxationDashboardPage loginAsTaxation(String user, String pass){
		UserInput.sendKeys(user);
		PasswordInput.sendKeys(pass);
		LoginBtn.click();
		return new TaxationDashboardPage();
	}


}

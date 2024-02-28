package com.billhub.qa.pages;

import com.billhub.qa.utils.TestUtils;
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
	WebElement searchRoleName;

	@FindBy(xpath = "//input[@id='tokenID']")
	WebElement searchRoleCode;
	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement roleSearchBtn;

	@FindBy(xpath = "//tbody/tr[1]/td[4]/i[1]")
	WebElement editRoleBtn;

	@FindBy(xpath = "//div[@class='has-float-label form-group col-md-6']//input[@type='text']")
	WebElement roleCodeInput;
	@FindBy(xpath = "//div[@class='form-group has-float-label col-md-6']//input[@type='text']")
	WebElement roleNameInput;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-done']")
	WebElement addNewRoleBtn;

	@FindBy(xpath = "//button[normalize-space()='Close']")
	WebElement closeBtn;

	@FindBy(css = "button[class='btn btn-primary btn-done'] span")
	WebElement updateBtn;



	public RolePage() {
		PageFactory.initElements(driver, this);
	}

	public void initializePopupWebElements(){
		PageFactory.initElements(driver,this);
	}

	public void fillAddNewRoleForm(String role_code, String role_name){

		addRoleBtn.click();
		initializePopupWebElements();
		roleCodeInput.sendKeys(role_code);
		roleNameInput.sendKeys(role_name);
	}

	public boolean addNewRoleWithValidData(String role_code, String role_name) {
		TestUtils.waitForToastToDisappear();
		fillAddNewRoleForm(role_code, role_name);
		addNewRoleBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Role Added successfully");
	}

	public boolean addNewRoleWithInvalidData(String role_code, String role_name) {
		TestUtils.waitForToastToDisappear();
		fillAddNewRoleForm(role_code, role_name);
		addNewRoleBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Role Added successfully");
	}

	public boolean addNewRoleWithoutData(String role_code, String role_name) {
		TestUtils.waitForToastToDisappear();
		fillAddNewRoleForm(role_code, role_name);
		addNewRoleBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Kindly fill out all the mandatory fields");
	}

	public boolean addNewRoleWithDuplicateData(String role_code, String role_name) {
		TestUtils.waitForToastToDisappear();
		fillAddNewRoleForm(role_code, role_name);
		addNewRoleBtn.click();
		return TestUtils.isSuccessToastDisplayed("Role Added successfully");
	}

	public boolean validateAddedRoleInTheDatabase(String role_code) {
		return searchRoleByCode(role_code);
	}

	public boolean searchRoleByName(String role_name) {
		searchRoleCode.clear();
		searchRoleName.clear();
		searchRoleName.sendKeys(role_name);
		roleSearchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-roles/div/div/div[3]/div/table/tbody/tr/td[3]"), role_name);
	}

	public boolean searchRoleByCode(String role_code) {
		searchRoleCode.clear();
		searchRoleName.clear();
		searchRoleCode.sendKeys(role_code);
		roleSearchBtn.click();
		searchRoleCode.clear();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-roles/div/div/div[3]/div/table/tbody/tr/td[2]"), role_code);
	}

	public boolean updateRole(String role_code, String role_name){
		TestUtils.waitForToastToDisappear();
		searchRoleByCode(role_code);

		TestUtils.locateAndClickEditBtn(By.xpath("//tbody/tr[1]/td[4]"));
		roleNameInput = TestUtils.waitForElementVisibility(By.xpath("//div[@class='form-group has-float-label col-md-6']//input[@type='text']"));
		roleNameInput.clear();
		roleNameInput.sendKeys(role_name);
		updateBtn.click();
		return TestUtils.isSuccessToastDisplayed("Role data updated successfully");
	}
}

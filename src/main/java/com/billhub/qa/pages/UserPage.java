package com.billhub.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class UserPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement addUserBtn;
	
	@FindBy(xpath = "//input[@id='invoiceNumber']")
	WebElement searchUserByName;
	
	@FindBy(xpath = "//input[@id='tokenID']")
	WebElement searchUserByBAGroupID;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchBtn;
	
	@FindBy(css = "input[formcontrolname='User_Name']")
    WebElement userName;

    @FindBy(css = "input[formcontrolname='Token_Id']")
    WebElement BAGroupId;

    @FindBy(css = "input[formcontrolname='First_Name']")
    WebElement firstName;

    @FindBy(css = "input[formcontrolname='Last_Name']")
    WebElement lastName;

    @FindBy(css = "#role_id")
    WebElement roleName;

    @FindBy(css = "input[formcontrolname='Email_ID']")
    WebElement emailID;

    @FindBy(css = "#defaultCheck2")
    WebElement activeBtn;

    @FindBy(css = "button[class='btn btn-primary btn-done'] span")
    WebElement addBtn;

    @FindBy(css = "button[class='btn btn-danger btn-done']")
    WebElement closeBtn;

    @FindBy(css = "button[class='btn btn-primary btn-done'] span")
    WebElement updateBtn; 
	
	public UserPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	public void initializePopupWebElements() {
		
		PageFactory.initElements(driver, this);
	}
	
	public void fillAddUserForm(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email ) {
		
		addUserBtn.click();
        initializePopupWebElements();
        
		userName.sendKeys(user_name);
		BAGroupId.sendKeys(ba_group_id);
		firstName.sendKeys(first_name);
		lastName.sendKeys(last_name);
		emailID.sendKeys(email);
		roleName.sendKeys(role_name);
	}
	
	public boolean addNewUserWithValidData(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("User Data Added successfully");
	}
	
	public boolean addNewUserWithInactiveStatus(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		activeBtn.click();
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("User Data Added successfully");
	}
	
	public boolean addNewUserWithInvalidData(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("User Data Added successfully");
	}
	
	public boolean addNewUserWithDuplicateData(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("User Data Added successfully");
	}
	
	public boolean addNewUserWithoutData(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Kindly fill out all the mandatory fields");
	}
	
	public boolean searchUserByName(String user_name) {
		
		searchUserByName.clear();
		searchUserByName.sendKeys(user_name);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-user/div/div/div[3]/div/table/tbody/tr[1]/td[2]"), user_name);
	}
	
	public boolean searchUserByBAGroupID(String ba_group_id) {
		
		searchUserByBAGroupID.clear();
		searchUserByBAGroupID.sendKeys(ba_group_id);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-user/div/div/div[3]/div/table/tbody/tr[1]/td[3]"), ba_group_id);
	}

	public String addNewUserWithActiveStatus(String ba_group_id){
		
		if(searchUserByBAGroupID(ba_group_id) == false)		// if user is not found in DB then its status is neither active or deactive
			return "";
		
	    WebElement isActive = TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-user/div/div/div[3]/div/table/tbody/tr[1]/td[8]"));
	    return isActive.getText();
	}
	
	public boolean validateAddedUserInTheDatabase(String ba_group_id) {
		
		return searchUserByBAGroupID(ba_group_id);
	}
	
	public boolean updateUser(String ba_group_id, String first_name, String last_name) {
		
		searchUserByBAGroupID(ba_group_id);
		
		WebElement editBtn = TestUtils.locateAndClickEditBtn(By.cssSelector("tbody tr:nth-child(1) td:nth-child(9) i:nth-child(1)"));  
	    firstName = TestUtils.waitForElementVisibility(By.cssSelector("input[formcontrolname='First_Name']")); 
	    lastName = TestUtils.waitForElementVisibility(By.cssSelector("input[formcontrolname='Last_Name']")); 
	    updateBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
	    
	    firstName.clear();
	    lastName.clear();
	    
	    firstName.sendKeys(first_name);
	    lastName.sendKeys(last_name);
	    
	    updateBtn.click();
	    
	    return TestUtils.isSuccessToastDisplayed("User Data Updated successfully");
		
	}
}

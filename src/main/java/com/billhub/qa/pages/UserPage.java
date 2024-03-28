package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
    
    @FindBy(css = "button[class='btn btn-success']")
    WebElement addCompanyBtn;
    
    @FindBy(css = "#Company_ID")
    WebElement companyCode;
    
    @FindBy(css = ".dropdown-btn")
    WebElement selectCity;
    
    @FindBy(css = "input[placeholder='Location..']")
    WebElement location;

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
		TestUtils.waitForElementInvisibility(By.className("loader"));
        initializePopupWebElements();
        
		userName.sendKeys(user_name);
		BAGroupId.sendKeys(ba_group_id);
		firstName.sendKeys(first_name);
		lastName.sendKeys(last_name);
		emailID.sendKeys(email);
		roleName.sendKeys(role_name);
	}
	
	public boolean addNewUserWithValidData(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email, String company_code, String city_name) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		
		addCompanyBtn = TestUtils.waitForElementVisibility(By.cssSelector("button[class='btn btn-success']"));
		addCompanyBtn.click();
		
		companyCode = TestUtils.waitForElementVisibility(By.cssSelector("#Company_ID"));
		selectCity = TestUtils.waitForElementVisibility(By.cssSelector(".dropdown-btn"));
		selectCity.click();
		TestUtils.waitForElementInvisibility(By.className("loader"));
		companyCode.sendKeys(company_code);
		TestUtils.waitForElementInvisibility(By.className("loader"));
		
		// code to select city from dropdown
	    WebElement overlayingElement = TestUtils.waitForElementToBeClickable(By.cssSelector("li.multiselect-item-checkbox"));
	    String cityCheckboxSelector = String.format("input[aria-label='%s']", city_name);
	    WebElement selectedLocation = TestUtils.waitForElementToBeClickable(By.cssSelector(cityCheckboxSelector));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(selectedLocation).click().build().perform();
	    
	    TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("User Data Added successfully");
		if(isAdded == false) closeBtn.click();
		return isAdded;
	}
	
	public boolean addNewUserWithInactiveStatus(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		activeBtn.click();
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("User Data Added successfully");
		if(isAdded == false) closeBtn.click();
		return isAdded;
	}
	
	public boolean addNewUserWithInvalidData(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("User Data Added successfully");
		if(isAdded == false) closeBtn.click();
		return isAdded;
	}
	
	public boolean addNewUserWithDuplicateData(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		TestUtils.waitForToastToDisappear();
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("User Data Added successfully");
		if(isAdded == false) closeBtn.click();
		return isAdded;
	}
	
	public boolean addNewUserWithoutData(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("User Data Added successfully");
		if(isAdded == false) closeBtn.click();
		return isAdded;
	}
	
	public boolean addNewReadOnlyUser(String user_name, String ba_group_id, String first_name, String last_name, String role_name, String email) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		fillAddUserForm(user_name, ba_group_id, first_name, last_name, role_name, email);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		addBtn.click();
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("User Data Added successfully");
		if(isAdded == false) closeBtn.click();
		return isAdded;
	}
	
	public boolean searchUserByName(String user_name) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		searchUserByBAGroupID.clear();
		searchUserByName.clear();
		searchUserByName.sendKeys(user_name);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		searchBtn.click();
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-user/div/div/div[3]/div/table/tbody/tr[1]/td[2]"), user_name);
	}
	
	public boolean searchUserByBAGroupID(String ba_group_id) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		searchUserByName.clear();
		searchUserByBAGroupID.clear();
		searchUserByBAGroupID.sendKeys(ba_group_id);
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		searchBtn.click();
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-user/div/div/div[3]/div/table/tbody/tr[1]/td[3]"), ba_group_id);
	}

	public String addNewUserWithActiveStatus(String ba_group_id){
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		
		if(searchUserByBAGroupID(ba_group_id) == false)		// if user is not found in DB then its status is neither active or deactive
			return "";
		
	    WebElement isActive = TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-user/div/div/div[3]/div/table/tbody/tr[1]/td[8]"));
	    return isActive.getText();
	}
	
	public boolean validateAddedUserInTheDatabase(String ba_group_id) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return searchUserByBAGroupID(ba_group_id);
	}
	
	public boolean createCommercialForDifferentLocation(String ba_group_id) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return searchUserByBAGroupID(ba_group_id);
	}
	
	public boolean createMDMAndReadOnlyUsers(String ba_group_id) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return searchUserByBAGroupID(ba_group_id);
	}
	
	public boolean createAccountForSpecificCompany(String ba_group_id) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		return searchUserByBAGroupID(ba_group_id);
	}
	
	public boolean updateUser(String ba_group_id, String first_name, String last_name) {
		
		TestUtils.waitForElementInvisibility(By.className("loader"));
		TestUtils.waitForToastToDisappear();
		searchUserByBAGroupID(ba_group_id);
		
		WebElement editBtn = TestUtils.locateAndClickEditBtn(By.cssSelector("tbody tr:nth-child(1) td:nth-child(9) i:nth-child(1)"));  
	    firstName = TestUtils.waitForElementVisibility(By.cssSelector("input[formcontrolname='First_Name']")); 
	    lastName = TestUtils.waitForElementVisibility(By.cssSelector("input[formcontrolname='Last_Name']")); 
	    updateBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
	    
	    firstName.clear();
	    lastName.clear();
	    
	    firstName.sendKeys(first_name);
	    lastName.sendKeys(last_name);
	    
	    TestUtils.waitForElementInvisibility(By.className("loader"));
	    updateBtn.click();
	    
	    return TestUtils.isSuccessToastDisplayed("User Data Updated successfully");
		
	}
}

package com.billhub.qa.pages;


import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerPage extends TestBase {
	
    @FindBy(xpath = "//input[@id='Customer_Name']")
    WebElement SearchCustomerByName;
    @FindBy(xpath = "//input[@id='Customer_Code']")
    WebElement SearchCustomerByCode;
    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement SearchCustomerBtn;

    @FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
    WebElement addCustomerBtn;
    @FindBy(xpath = "//tbody/tr[1]/td[6]")
    WebElement updateBtn;
    @FindBy(css = "div[class='has-float-label form-group col-md-6'] input[type='text']")
    WebElement customerCode;
    @FindBy(css = "div[class='modal-body pb-0'] div:nth-child(2) input:nth-child(1)")
    WebElement customerName;
    @FindBy(css = "input[formcontrolname='Credit_Period']")
    WebElement customerPeriod;
    @FindBy(id = "Vertical")
    WebElement verticalDropdown;
    @FindBy(css = "#defaultCheck2")
    WebElement ActiveCheckBox;
    @FindBy(css = "button[class='btn btn-primary btn-done']")
    WebElement addBtn;
    @FindBy(css = "button[class='btn btn-danger btn-done']")
    WebElement closeBtn;

    public CustomerPage(){
        PageFactory.initElements(driver,this);
    }
    public void initializePopupWebElements(){
        PageFactory.initElements(driver,this);
    }
    public void fillNewCustomerForm(String customer_code,String customer_name, String customer_period, String customer_drop){
        addCustomerBtn.click();
        initializePopupWebElements();
        customerCode.sendKeys(customer_code);
        customerName.sendKeys(customer_name);
        customerPeriod.sendKeys(customer_period);
        Select selectVertical = new Select(verticalDropdown);
        selectVertical.selectByVisibleText(customer_drop);

    }
    public boolean searchAddedCustomerByCode(String customer_code) {
        SearchCustomerByCode.clear();
        SearchCustomerByName.clear();
        SearchCustomerByCode.sendKeys(customer_code);
        SearchCustomerBtn.click();
        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-customer/div/div/div[3]/div/table/tbody/tr[1]/td[2]"), customer_code);
    }

    public boolean validateSearchCustomerByName(String customer_name){
        SearchCustomerByCode.clear();
        SearchCustomerByName.clear();
        SearchCustomerByName.sendKeys(customer_name);
        SearchCustomerBtn.click();
        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-customer/div/div/div[3]/div/table/tbody/tr[1]/td[3]"),customer_name);
    }

    public boolean validateAddCustomerWithValidData(String custName, String custCode, String custPeriod, String custDrop){
        TestUtils.waitForToastToDisappear();
        fillNewCustomerForm(custCode,custName,custPeriod,custDrop);
        ActiveCheckBox.click();
        addBtn.click();
        closeBtn.click();
        return TestUtils.isSuccessToastDisplayed("customer Added successfully");
    }

    public boolean validateAddCustomerWithInvalidData(String custName, String custCode, String custPeriod, String custDrop){
        TestUtils.waitForToastToDisappear();
        fillNewCustomerForm(custName,custCode,custPeriod,custDrop);
        ActiveCheckBox.click();
        addBtn.click();
        closeBtn.click();
        return TestUtils.isSuccessToastDisplayed("customer Added successfully");
    }

    public boolean validateAddNewCustomerWithoutData(String custName, String custCode, String custPeriod, String custDrop) {
        TestUtils.waitForToastToDisappear();
        fillNewCustomerForm(custName, custCode, custPeriod, custDrop);
        ActiveCheckBox.click();
        addBtn.click();
        closeBtn.click();
        return TestUtils.isSuccessToastDisplayed("Kindly fill out all the mandatory fields");
    }

    public boolean validateActiveStatus(String customer_code){
        SearchCustomerByCode.clear();
        SearchCustomerByName.clear();
        SearchCustomerByCode.sendKeys(customer_code);
        SearchCustomerBtn.click();

        TestUtils.locateAndClickEditBtn(By.xpath("//tbody/tr[1]/td[6]/i[1]"));
        boolean isActive= ActiveCheckBox.isSelected();
        closeBtn.click();
        return isActive;
    }

    public boolean validateInactiveStatus(String custName, String custCode, String custPeriod, String custDrop){
        fillNewCustomerForm(custName,custCode,custPeriod,custDrop);
        addBtn.click();
        closeBtn.click();
        return validateActiveStatus(custCode);
    }

    public boolean validateAddedCustomerInDatabase(String customer_code){
        SearchCustomerByCode.clear();
        return searchAddedCustomerByCode(customer_code);
    }

}

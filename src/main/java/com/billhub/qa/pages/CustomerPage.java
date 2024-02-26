package com.billhub.qa.pages;


import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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


    public CustomerPage(){
        PageFactory.initElements(driver,this);
    }
    WebElement customerCode,customerName,customerPeriod, verticalDropdown,ActiveCheckBox,addBtn,closeBtn;

    public void initializePopupWebElements(){
        customerCode = driver.findElement(By.cssSelector("div[class='has-float-label form-group col-md-6'] input[type='text']"));
        customerName= driver.findElement(By.cssSelector("div[class='modal-body pb-0'] div:nth-child(2) input:nth-child(1)"));
        customerPeriod=driver.findElement(By.cssSelector("input[formcontrolname='Credit_Period']"));
        verticalDropdown = driver.findElement(By.id("Vertical"));
        ActiveCheckBox=driver.findElement(By.cssSelector("#defaultCheck2"));
        addBtn=driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
        closeBtn = driver.findElement(By.cssSelector("button[class='btn btn-danger btn-done']"));
    }

    public void fillNewCustomerForm(String customer_code,String customer_name, String customer_period, String customer_drop){
        addCustomerBtn.click();
        initializePopupWebElements();
        customerCode.sendKeys(customer_code);
        customerName.sendKeys(customer_name);
        customerPeriod.sendKeys(customer_period);
        Select selectVertical = new Select(verticalDropdown);
        selectVertical.selectByVisibleText(customer_drop);
        ActiveCheckBox.click();
    }

    public boolean searchAddedCustomerByCode(String customer_code) {

        SearchCustomerByCode.sendKeys(customer_code);
        SearchCustomerBtn.click();
        // modify this xpath
        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-customer/div/div/div[3]/div/table/tbody/tr[1]/td[2]"), customer_code);
    }

    public boolean validateSearchCustomerByName(String customer_name) {
        SearchCustomerByName.sendKeys(customer_name);
        SearchCustomerBtn.click();
        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-customer/div/div/div[3]/div/table/tbody/tr[1]/td[3]"),customer_name);
    }

    public boolean validateSearchCustomerByCode(String customer_code) {
        SearchCustomerByCode.sendKeys(customer_code);
        SearchCustomerBtn.click();
        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-customer/div/div/div[3]/div/table/tbody/tr[1]/td[2]"),customer_code);

    }

    public boolean validateAddCustomerWithValidData(String custName, String custCode, String custPeriod, String custDrop){
        fillNewCustomerForm(custCode,custName,custPeriod,custDrop);
        addBtn.click();
        closeBtn.click();
        return TestUtils.isSuccessToastDisplayed("customer Added successfully");
    }

    public boolean validateAddCustomerWithInvalidData(String custName, String custCode, String custPeriod, String custDrop){
        fillNewCustomerForm(custName,custCode,custPeriod,custDrop);
        addBtn.click();
        closeBtn.click();
        return TestUtils.isSuccessToastDisplayed("customer Added successfully");
    }

    public boolean validateAddNewCustomerWithoutData(String custName, String custCode, String custPeriod, String custDrop) {
        fillNewCustomerForm(custName, custCode, custPeriod, custDrop);
        addBtn.click();
        closeBtn.click();
        return TestUtils.isSuccessToastDisplayed("Kindly fill out all the mandatory fields");
    }

    public boolean validateAddedCustomerInDatabase(String customer_code){
        return searchAddedCustomerByCode(customer_code);
    }

}

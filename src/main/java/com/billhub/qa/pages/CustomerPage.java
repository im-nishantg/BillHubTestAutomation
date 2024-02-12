package com.billhub.qa.pages;


import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtil;
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

    public CustomerPage(){
        PageFactory.initElements(driver,this);
    }
    WebElement customerCode,customerName,customerPeriod, verticalDropdown,ActiveCheckBox,AddUserBtn;

    public void initializePopupWebElements(){
        customerCode = driver.findElement(By.cssSelector("div[class='has-float-label form-group col-md-6'] input[type='text']"));
        customerName= driver.findElement(By.cssSelector("div[class='modal-body pb-0'] div:nth-child(2) input:nth-child(1)"));
        customerPeriod=driver.findElement(By.cssSelector("input[formcontrolname='Credit_Period']"));
        verticalDropdown = driver.findElement(By.id("Vertical"));
        ActiveCheckBox=driver.findElement(By.cssSelector("#defaultCheck2"));
        AddUserBtn=driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
    }

    public boolean validateSearchCustomerByName() throws InterruptedException {
        SearchCustomerByName.sendKeys(prop.getProperty("customerNameforSearch"));
        SearchCustomerBtn.click();
        Thread.sleep(TestUtil.EXPLICIT_WAIT);
        WebElement TableContent=driver.findElement(By.xpath("//body/app-root/app-layout[@class='ng-star-inserted']" +
                "/div[@id='main']/main[@class='page-content']/div[@class='row']/div[@class='col-md-12']" +
                "/app-list-customer[@class='ng-star-inserted']/div[@class='page-header']/div[@class='inner-page-wraper']/div[3]"));
        return TableContent.isDisplayed();
    }

    public boolean validateSearchCustomerByCode() throws InterruptedException {
        SearchCustomerByCode.sendKeys(prop.getProperty("customerCodeForSearch"));
        SearchCustomerBtn.click();
        Thread.sleep(TestUtil.EXPLICIT_WAIT);
        WebElement TableContent=driver.findElement(By.xpath("//body/app-root/app-layout[@class='ng-star-inserted']" +
                "/div[@id='main']/main[@class='page-content']/div[@class='row']" +
                "/div[@class='col-md-12']/app-list-customer[@class='ng-star-inserted']" +
                "/div[@class='page-header']/div[@class='inner-page-wraper']/div[3]"));
        return TableContent.isDisplayed();

    }



    public boolean validateAddCustomerWithValidData(String custName, String custCode, String custPeriod, String custDrop) throws InterruptedException {
        addCustomerBtn.click();
        Thread.sleep(Duration.ofSeconds(5).toMillis());
        initializePopupWebElements();

        customerCode.sendKeys(custCode);
        customerName.sendKeys(custName);
        customerPeriod.sendKeys(custPeriod);
        Select selectVertical = new Select(verticalDropdown);
        selectVertical.selectByVisibleText(custDrop);
        ActiveCheckBox.click();

        AddUserBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='customer Added successfully']")));
        return successToast.isDisplayed();
    }


    public boolean validateCustomerInTable(String custName, String custCode, String custPeriod, String custDrop) throws InterruptedException {
        addCustomerBtn.click();
        Thread.sleep(Duration.ofSeconds(5).toMillis());
        initializePopupWebElements();

        customerCode.sendKeys(custCode);
        customerName.sendKeys(custName);
        customerPeriod.sendKeys(custPeriod);
        Select selectVertical = new Select(verticalDropdown);
        selectVertical.selectByVisibleText(custDrop);
        ActiveCheckBox.click();
        AddUserBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String xpathExpression = "//tr[contains(@class, 'ng-star-inserted') and td[contains(.,'" + custName + "')]]";

        WebElement userRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        return userRow.isDisplayed();
    }


    public boolean validateAddCustomerWithBlank() throws InterruptedException {
        try {
            addCustomerBtn.click();
            Thread.sleep(Duration.ofSeconds(5).toMillis());
            AddUserBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
            AddUserBtn.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='customer Added successfully']")));
            return successToast.isDisplayed();
        } catch (Exception e) {
            System.err.println("An exception occurred: " + e.getMessage());
            return false;
        }
    }
}

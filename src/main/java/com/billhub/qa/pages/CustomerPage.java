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

//    all the test related to update customer
//    public void validateUpdateByCustomerName() throws InterruptedException {
//        updateBtn.click();
//        Thread.sleep(TestUtil.EXPLICIT_WAIT);
//        WebElement inputField = new WebDriverWait(driver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='modal-body pb-0'] div:nth-child(2) input:nth-child(1)")));
//
////        WebElement inputField = driver.findElement(By.id("Customer_Name"));
//        inputField.clear();
//        inputField.sendKeys("Abhay log");
//
//        // Click on the update button
//        WebElement newUpdateBtn= driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
//        newUpdateBtn.click();
//
//        // Wait for some time to allow the update to take effect (you may need to implement a proper wait strategy)
//        try {
//            Thread.sleep(TestUtil.EXPLICIT_WAIT);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Verify if the update functionality is working fine
//        WebElement updatedElement = driver.findElement(By.xpath("//tr[@class='ng-star-inserted']/td[3]"));
//        String updatedText = updatedElement.getText();
//        Assert.assertEquals(updatedText, "Abhay log", "Update functionality is not working as expected");
//
//    }

    public boolean validateAddCustomerWithValidData() throws InterruptedException {
        addCustomerBtn.click();
        Thread.sleep(Duration.ofSeconds(5).toMillis());
        initializePopupWebElements();

        customerCode.sendKeys("090090");
        customerName.sendKeys("Suraj");
        customerPeriod.sendKeys("1");
        Select selectVertical = new Select(verticalDropdown);
        selectVertical.selectByVisibleText("AOB");
        ActiveCheckBox.click();

        AddUserBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='customer Added successfully']")));
        return successToast.isDisplayed();
    }

//    public boolean validateAddCustomerWithInvalidData() throws InterruptedException {
//        addCustomerBtn.click();
//        Thread.sleep(Duration.ofSeconds(5).toMillis());
//        initializePopupWebElements();
//
//        customerCode.sendKeys("jhon");
//        customerName.sendKeys("112233");
//        customerPeriod.sendKeys("1");
//        Select selectVertical = new Select(verticalDropdown);
//        selectVertical.selectByVisibleText("AOB");
//        ActiveCheckBox.click();
//
//        AddUserBtn.click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='customer Added successfully']")));
//        return successToast.isDisplayed();
//    }


    public boolean validateCustomerInTable() throws InterruptedException {
        String newUsername = "JohnDoe";
        addCustomerBtn.click();
        Thread.sleep(Duration.ofSeconds(5).toMillis());
        initializePopupWebElements();

        customerCode.sendKeys("010304");
        customerName.sendKeys(newUsername);
        customerPeriod.sendKeys("8");
        Select selectVertical = new Select(verticalDropdown);
        selectVertical.selectByVisibleText("BULK");
        ActiveCheckBox.click();
        AddUserBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpathExpression = "//tr[contains(@class, 'ng-star-inserted') and td[contains(.,'" + newUsername + "')]]";
        WebElement userRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        return userRow.isDisplayed();
    }


    public boolean validateAddCustomerWithBlank() throws InterruptedException {
        try {
            addCustomerBtn.click();
            Thread.sleep(Duration.ofSeconds(5).toMillis());
            AddUserBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
            AddUserBtn.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='customer Added successfully']")));
            return successToast.isDisplayed();
        } catch (Exception e) {
            System.err.println("An exception occurred: " + e.getMessage());
            return false;
        }
    }
}

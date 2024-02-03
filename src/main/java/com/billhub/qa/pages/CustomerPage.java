package com.billhub.qa.pages;


import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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

    public boolean validateSearchCustomerByName() throws InterruptedException {
        SearchCustomerByName.sendKeys(prop.getProperty("customerNameforSearch"));
        SearchCustomerBtn.click();
        Thread.sleep(TestUtil.EXPLICIT_WAIT);
        WebElement TableContent=driver.findElement(By.xpath("//body/app-root/app-layout[@class='ng-star-inserted']" +
                "/div[@id='main']/main[@class='page-content']/div[@class='row']/div[@class='col-md-12']" +
                "/app-list-customer[@class='ng-star-inserted']/div[@class='page-header']/div[@class='inner-page-wraper']/div[3]"));
        return TableContent.isDisplayed();
    }

	public void clickOnAddCustomerBtn() throws InterruptedException {
		addCustomerBtn.click();
		Thread.sleep(Duration.ofSeconds(4).toMillis());
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
    public void validateUpateByCustomerName() throws InterruptedException {
        updateBtn.click();
        Thread.sleep(5000);
        WebElement inputField = driver.findElement(By.xpath("//input[@formcontrolname='Customer_Name']"));
        inputField.clear();
        inputField.sendKeys("Nishant Logistics");

        // Click on the update button
        WebElement newUpdateButton = driver.findElement(By.xpath("//button[@class='btn btn-primary btn-done']"));
        newUpdateButton.click();

        // Wait for some time to allow the update to take effect (you may need to implement a proper wait strategy)
        try {
            Thread.sleep(TestUtil.EXPLICIT_WAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify if the update functionality is working fine
        WebElement updatedElement = driver.findElement(By.xpath("//tr[@class='ng-star-inserted']/td[3]"));
        String updatedText = updatedElement.getText();
        Assert.assertEquals(updatedText, "Nishant Logistics", "Update functionality is not working as expected");

    }
	

}

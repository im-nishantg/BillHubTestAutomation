package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtil;
import jdk.incubator.vector.VectorOperators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerPage extends TestBase {
    @FindBy(xpath = "//button[normalize-space()='Add Customer']")
    WebElement AddCuntomerBtn;
    @FindBy(xpath = "//input[@id='Customer_Name']")
    WebElement SearchCustomer;
    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement SearchCustomerBtn;


    public CustomerPage(){
        PageFactory.initElements(driver,this);
    }


    public boolean validateSearchCustomer() throws InterruptedException {
        SearchCustomer.sendKeys(prop.getProperty("customerNameforSearch"));
        SearchCustomerBtn.click();
        Thread.sleep(TestUtil.EXPLICIT_WAIT);
        WebElement TableContent=driver.findElement(By.xpath("//body/app-root/app-layout[@class='ng-star-inserted']/div[@id='main']/main[@class='page-content']/div[@class='row']/div[@class='col-md-12']/app-list-customer[@class='ng-star-inserted']/div[@class='page-header']/div[@class='inner-page-wraper']/div[3]"));

        return TableContent.isDisplayed();
    }







}

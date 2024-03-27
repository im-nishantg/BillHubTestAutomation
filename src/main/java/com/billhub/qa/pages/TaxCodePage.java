package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class TaxCodePage extends TestBase {

    @FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
    WebElement addTaxCodeBtn;

    @FindBy(xpath = "//input[@id='invoiceNumber']")
    WebElement searchByTaxCode;

    @FindBy(xpath = "//input[@id='tokenID']")
    WebElement searchByTaxPercent;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchBtn;

    @FindBy(css = "div[class='has-float-label form-group col-md-6'] input[type='text']")
    WebElement taxCode;

    @FindBy(css = "div:nth-child(3) input:nth-child(1)")
    WebElement taxPercent;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-add-edit-tax-code/div[2]/div/div/form/div/div[2]/input")
    WebElement description;

    @FindBy(css = "button[class='btn btn-primary btn-done']")
    WebElement addBtn;

    @FindBy(css = "button[class='btn btn-danger btn-done']")
    WebElement closeBtn;

    @FindBy(css = "button[class='btn btn-primary btn-done'] span")
    WebElement updateBtn;

    public TaxCodePage() {
        PageFactory.initElements(driver, this);
    }

    public void initializePopupWebElements() {
        PageFactory.initElements(driver, this);
    }

    public void fillAddTaxCodeForm(String tax_code, String tax_percent, String desc) {
    	
        addTaxCodeBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
        initializePopupWebElements();

        taxCode.sendKeys(tax_code);
        taxPercent.sendKeys(tax_percent);
        description.sendKeys(desc);
    }

    public boolean addNewTaxCodeWithValidData(String tax_code, String tax_percent, String desc) {
    	
    	TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
    	TestUtils.waitForToastToDisappear();
        fillAddTaxCodeForm(tax_code, tax_percent, desc);
        addBtn.click();
        closeBtn.click();
        return TestUtils.isSuccessToastDisplayed("Tax Code Added successfully");
    }

    public boolean addNewTaxCodeWithInvalidData(String tax_code, String tax_percent, String desc) {
    	
    	TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
    	TestUtils.waitForToastToDisappear();
        fillAddTaxCodeForm(tax_code, tax_percent, desc);
        addBtn.click();
        closeBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
        return TestUtils.isSuccessToastDisplayed("Tax Code Added successfully");
    }

    public boolean addNewTaxCodeWithoutData(String tax_code, String tax_percent, String desc) {
    	
    	TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
    	TestUtils.waitForToastToDisappear();
        fillAddTaxCodeForm(tax_code, tax_percent, desc);
        addBtn.click();
        closeBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
        return TestUtils.isSuccessToastDisplayed("Tax Code Added successfully");
    }

    public boolean addNewTaxCodeWithDuplicateData(String tax_code, String tax_percent, String desc) {
    	
    	TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
    	TestUtils.waitForToastToDisappear();
        fillAddTaxCodeForm(tax_code, tax_percent, desc);
        addBtn.click();
        closeBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
        return TestUtils.isSuccessToastDisplayed("Tax Code Added successfully");
    }

    public boolean searchByTaxCode(String tax_code) {
    	
    	TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
    	searchByTaxPercent.clear();
    	searchByTaxCode.clear();
        searchByTaxCode.sendKeys(tax_code);
        searchBtn.click();
        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-tax-code/div/div/div[3]/div/table/tbody/tr/td[2]"), tax_code);
    }

    public boolean searchByTaxPercentage(String tax_percent) {
    	
    	TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
    	searchByTaxCode.clear();
    	searchByTaxPercent.clear();
        searchByTaxPercent.sendKeys(tax_percent);
        searchBtn.click();
        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-tax-code/div/div/div[3]/div/table/tbody/tr/td[4]"), tax_percent);
    }

    public boolean validateAddedTaxCodeInTheDatabase(String tax_code) {
    	
    	TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
        return searchByTaxCode(tax_code);
    }

    public boolean updateTaxCode(String tax_code, String tax_percent, String desc) {
    	
    	TestUtils.waitForElementInvisibility(By.className("loader-overlay"));
    	TestUtils.waitForToastToDisappear();
        searchByTaxCode(tax_code);

        WebElement editBtn = TestUtils.locateAndClickEditBtn(By.cssSelector("i[class='fa fa-edit']"));
        taxPercent = TestUtils.waitForElementVisibility(By.cssSelector("div:nth-child(3) input:nth-child(1)"));
        description = TestUtils.waitForElementVisibility(By.cssSelector("input[formcontrolname='Description']"));

        taxPercent.clear();
        description.clear();

        taxPercent.sendKeys(tax_percent);
        description.sendKeys(desc);

        updateBtn.click();

        return TestUtils.isSuccessToastDisplayed("Tax Code Updated successfully");
    }
}

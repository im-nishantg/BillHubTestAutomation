package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNonPOBasedInvoicePage extends TestBase {

    @FindBy(xpath = "//input[@id='first']")
    WebElement invoiceNumber;

    @FindBy(xpath = "//button[normalize-space()='Attach']")
    WebElement attachBtn;

    @FindBy(xpath = "//button[normalize-space()='Tag (BT)']")
    WebElement tagBTBtn;

    @FindBy(xpath = "//select[@id='subServiCategory']")
    WebElement subServiceCategory;

    @FindBy(xpath = "//input[@id='base']")
    WebElement baseAmount;

    @FindBy(xpath = "//input[@id='td']")
    WebElement cd;
    @FindBy(xpath = "//input[@id='tcsAmount']")
    WebElement tcs_amount;

    @FindBy(xpath = "//input[@id='igst']")
    WebElement igst;

    @FindBy(xpath = "//input[@id='hsnCode']")
    WebElement HSNCode;

    @FindBy(xpath = "//input[@id='custName']")
    WebElement endCustomer;

    @FindBy(xpath = "//input[@id='commet']")
    WebElement comment;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-primary btn-block marginTop']")
    WebElement addToListBtn;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[2]/div[3]/table/tbody/tr/td[4]/input")
    WebElement quantity;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[2]/div[3]/table/tbody/tr/td[5]/input")
    WebElement hsnCode;

    @FindBy(xpath = "//span[normalize-space()='Add PO Transaction']")
    WebElement addPOTransactionBtn;

    @FindBy(xpath = "//input[@name='Invoice']")
    WebElement addInvoice;

    @FindBy(xpath = "//button[normalize-space()='Done']")
    WebElement doneBtn;

    @FindBy(xpath = "//button[normalize-space()='SAVE']")
    WebElement saveBtn;
    @FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[2]/div[1]/table/tbody/tr/td[1]/div/input")
    WebElement poResultCheckBox;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[4]/select")
    WebElement submittingAt;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[5]/select")
    WebElement submittingTo;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[6]/button")
    WebElement submitMemoBtn;

    @FindBy(xpath = "//div[@class='inv-footer-text']")
    WebElement totalInvAmount;

    public CreateNonPOBasedInvoicePage(){
        PageFactory.initElements(driver,this);
    }

    public void createInvoiceBTBased(){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceNumber.sendKeys("123456");
        subServiceCategory.sendKeys("cargo");
        baseAmount.sendKeys("10");
        cd.sendKeys("1");
        igst.sendKeys("1.80");
//		HSNCode.sendKeys("996713");
        endCustomer.sendKeys("test");
        comment.sendKeys("test");


        // Code to upload the invoice file
        attachBtn.click();
        String invoice_file_path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\Sample_Invoice.pdf";
        addInvoice.sendKeys(invoice_file_path);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        TestUtils.waitForWebElementToBeClickable(doneBtn).click();

        // code to tag po to the invoice
        tagBTBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        TestUtils.waitForWebElementToBeClickable(poResultCheckBox).click();
        addToListBtn.click();
        quantity.clear();
        hsnCode.clear();
        quantity.sendKeys("10");
        hsnCode.sendKeys("996713");
        addPOTransactionBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
//		TestUtils.waitForWebElementToBeClickable(saveBtn).click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        submittingAt.sendKeys("Ahmedabad");
        submittingTo.sendKeys("Nishant Gore");
        submitMemoBtn.click();

    }

    public String verifyGstCode(){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        baseAmount.sendKeys("10");
        igst.sendKeys("1.80");
        comment.click();
        String amount=  totalInvAmount.getText();
        return TestUtils.splitString(amount);
    }

    public String verifyTdCode(){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        baseAmount.clear();
        cd.clear();
        baseAmount.sendKeys("10");
        cd.sendKeys("1");
        comment.click();
        String amount=  totalInvAmount.getText();
        return TestUtils.splitString(amount);
    }

    public String verifyAdditionalAmount(){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        baseAmount.clear();
        tcs_amount.clear();
        baseAmount.sendKeys("10");
        tcs_amount.sendKeys("1");
        comment.click();
        String amount=  totalInvAmount.getText();
        return TestUtils.splitString(amount);
    }



}

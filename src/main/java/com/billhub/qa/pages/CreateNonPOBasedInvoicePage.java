package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.Invoice;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    @FindBy(xpath = "//input[@id='taxAmt']")
    WebElement tcs;

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

    @FindBy(xpath = "/html/body/modal-container/div/div/app-add-lr-popup/div[2]/div[2]/div[3]/table/tbody/tr/td[5]/input")
    WebElement amountBT;

    @FindBy(xpath = "//td[@scope='row']//input[@type='checkbox']")
    WebElement btResultCheckBox;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-add-po-popup/div[2]/div[2]/div[3]/table/tbody/tr/td[5]/input")
    WebElement hsnCode;

    @FindBy(xpath = "//span[normalize-space()='Add Base Transaction']")
    WebElement addBTTransactionBtn;

    @FindBy(xpath = "//input[@name='Invoice']")
    WebElement addInvoice;
    @FindBy(xpath = "(//select[@id='typeService'])[1]")
    WebElement serviceType;
    @FindBy(xpath = "//select[@id='companyCode']")
    WebElement companyCode;

    @FindBy(xpath = "//button[normalize-space()='Done']")
    WebElement doneBtn;

    @FindBy(xpath = "//button[normalize-space()='SAVE']")
    WebElement saveBtn;
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[4]/select")
    WebElement submittingAt;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[5]/select")
    WebElement submittingTo;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[2]/div/form/div/div[6]/button")
    WebElement submitMemoBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-memo-list/div/div/div[3]/div/div/div[1]/div/span")
    WebElement allInvoices;

    @FindBy(xpath = "//div[@class='inv-footer-text']")
    WebElement totalInvAmount;

    @FindBy(xpath = "//button[normalize-space()='SUBMIT MEMO']")
    WebElement submitMemoFinal;
    @FindBy(xpath = "//div[@class='modal-content']")
    WebElement confirmationTab;
    @FindBy(xpath = "//button[normalize-space()='Submit Memo']")
    WebElement submitMemo_CTab_btn;


    public CreateNonPOBasedInvoicePage(){
        PageFactory.initElements(driver,this);
    }
    public void clearInputFields(){
        baseAmount.clear();
        cd.clear();
        igst.clear();
        tcs.clear();
    }

    void fillCreateNewInvoiceForm(Invoice invoice){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceNumber.sendKeys(invoice.invoiceNumber);
        subServiceCategory.sendKeys(invoice.subServiceCategory);
        baseAmount.sendKeys(invoice.baseAmount);
        tcs.sendKeys(invoice.tcs);
        cd.sendKeys(invoice.cd);
        igst.sendKeys(invoice.igst);
        endCustomer.sendKeys(invoice.endCustomer);
        comment.sendKeys(invoice.comment);
    }

    public void attachSampleInvoiceFile(){
        attachBtn.click();
        String invoice_file_path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\Sample_Invoice.pdf";
        addInvoice.sendKeys(invoice_file_path);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        TestUtils.waitForWebElementToBeClickable(doneBtn).click();
    }

    public void tagBT(String amount,String company_code,String service_type){
        tagBTBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        companyCode.sendKeys(company_code);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        serviceType.sendKeys(service_type);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        TestUtils.waitForWebElementToBeClickable(btResultCheckBox).click();
        addToListBtn.click();
        amountBT.clear();
        amountBT.sendKeys(amount);
        addBTTransactionBtn.click();
    }

    public void createInvoiceBTBased(Invoice invoice,String company_code,String service_type){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        clearInputFields();
        fillCreateNewInvoiceForm(invoice);
        attachSampleInvoiceFile();
        tagBT(invoice.quantity,company_code,service_type);
    }

    public boolean submitMemoWithValidData(Invoice invoice,String company_code, String service_type ){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        createInvoiceBTBased(invoice,company_code,service_type);
        TestUtils.waitForWebElementToBeClickable(saveBtn).click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        submittingAt.sendKeys(invoice.submittingAt);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        submittingTo.sendKeys(invoice.submittingTo);

        submitMemoBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        submitMemoFinal.click();
        TestUtils.waitForWebElementToBeClickable(submitMemo_CTab_btn).click();
        return true;

    }
    public double verifyGstCode(String base_amount,String Igst){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        clearInputFields();
        baseAmount.sendKeys(base_amount);
        igst.sendKeys(Igst);
        comment.click();
        String amount=  totalInvAmount.getText();
        amount= TestUtils.splitString(amount);
        return Double.parseDouble(amount);
    }

    public double verifyTdCode(String base_amount,String Cd){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        clearInputFields();
        baseAmount.sendKeys(base_amount);
        cd.sendKeys(Cd);
        comment.click();
        String amount=  totalInvAmount.getText();
        amount= TestUtils.splitString(amount);
        return Double.parseDouble(amount);
    }

    public double verifyAdditionalAmount(String base_amount,String Tcs){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        clearInputFields();
        baseAmount.sendKeys(base_amount);
        tcs.sendKeys(Tcs);
        comment.click();
        String amount=  totalInvAmount.getText();
        amount= TestUtils.splitString(amount);
        return Double.parseDouble(amount);
    }

    public boolean CreateInvoiceWithoutData(String Inv,String subService,String EndCustomer, String Comment){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        clearInputFields();
        invoiceNumber.sendKeys(Inv);
        subServiceCategory.sendKeys(subService);
        endCustomer.sendKeys(EndCustomer);
        comment.sendKeys(Comment);
        saveBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorToast = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='toast-container']")));

        boolean isErrorToastDisplayed = errorToast.isDisplayed();
        return isErrorToastDisplayed;
    }



}

package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateNonPOBasedInvoiceWithExcelsheetPage extends TestBase {
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-upload/div/div/div[2]/div/div[1]/div[2]/div/input")
    WebElement uploadFileBtn;

    @FindBy(css = ".btn.btn-warning.btn-acknowledge")
    WebElement uploadBtn;
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[1]/div/perfect-scrollbar/div/div[1]/div/div/div/div/div/div[4]/button[1]")
    WebElement editBtn;
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[1]/div/perfect-scrollbar/div/div[1]/div/div/div[1]/div/div/div[4]/button[1]/i")
    WebElement editBtn1;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[1]/div/perfect-scrollbar/div/div[1]/div/div/div[2]/div/div/div[4]/button[1]/i")
    WebElement editBtn2;


    @FindBy(xpath = "//div[@class='inv-footer-text']")
    WebElement totalInvAmount;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-create-memo/div/div/div[3]/div[2]/form/div/div[1]/div/div/div[2]/button")
    WebElement resetBtn;

    @FindBy(xpath = "//button[normalize-space()='Attach']")
    WebElement attachBtn;

    @FindBy(xpath = "//button[normalize-space()='Tag (BT)']")
    WebElement tagBtBtn;

    @FindBy(xpath = "//input[@name='Invoice']")
    WebElement addInvoice;

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

    @FindBy(xpath = "//button[@type='submit']")
    WebElement nextSubmitMemoBtn;
    @FindBy(xpath = "/html/body/modal-container/div/div/app-add-lr-popup/div[3]/button[1]")
    WebElement uploadBTBtn;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-memo-submit-confirm/div[3]/div/div[2]/button")
    WebElement finalSubmitMemoBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-final/div/div/div[2]/div[2]/button")
    WebElement printBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-final/div/div/div[2]/div[1]/button")
    WebElement homeBtn;
    @FindBy(xpath = "//button[@aria-label='Close']")
    WebElement statusCloseBtn;

    @FindBy(css = "modal-container[role='dialog'] div[class='row'] div:nth-child(3) div:nth-child(2)")
    WebElement statusOfInv;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-invalid-invoices/div/div/div[2]/div[2]/table/tbody/tr/td[1]")
    WebElement invoiceAlreadyExistErrorText;
    @FindBy(xpath = "//button[normalize-space()='Load Dashboard Data']")
    WebElement loadDashboardBtn;


    public CreateNonPOBasedInvoiceWithExcelsheetPage(){
        PageFactory.initElements(driver,this);
    }

    public void uploadExcelSheet(String INVOICE_SHEET_PATH) {
        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadFileBtn.sendKeys(INVOICE_SHEET_PATH);
        TestUtils.waitForElementVisibility(By.cssSelector(".btn.btn-warning.btn-acknowledge"));
        TestUtils.waitForWebElementToBeClickable(uploadBtn).click();
        TestUtils.waitForElementInvisibility(By.className("loader"));
    }

    public void attachSampleInvoiceFile() {
        TestUtils.waitForElementInvisibility(By.className("loader"));
        attachBtn.click();
        String invoice_file_path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\billhub\\qa\\testdata\\Sample_Invoice.pdf";
        addInvoice.sendKeys(invoice_file_path);
        TestUtils.waitForElementInvisibility(By.className("loader"));
        TestUtils.waitForWebElementToBeClickable(doneBtn).click();
    }
    public double verifyGstCode() {

        editBtn.click();
        String amount =  totalInvAmount.getText();
        amount = TestUtils.splitString(amount);
        resetBtn.click();
        return Double.parseDouble(amount);
    }

    public double verifyTdCode() {
        editBtn.click();
        String amount =  totalInvAmount.getText();
        amount = TestUtils.splitString(amount);
        resetBtn.click();
        return Double.parseDouble(amount);
    }

    public double verifyAdditionalAmount() {

        editBtn.click();
        String amount =  totalInvAmount.getText();
        amount = TestUtils.splitString(amount);
        resetBtn.click();
        return Double.parseDouble(amount);
    }

    public boolean submitWithoutInvDoc(){
        editBtn.click();
        TestUtils.waitForWebElementToBeClickable(saveBtn).click();
        // Wait for the toast message to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='-Files not found.']")));
        // Check if the toast message is visible
        boolean isToastVisible = toast.isDisplayed();
        return isToastVisible;
    }

    public boolean submitMemoWithValidData(String submitting_at, String submitting_to) {

        editBtn.click();
        attachSampleInvoiceFile();
        TestUtils.waitForWebElementToBeClickable(saveBtn).click();	// saving the current invoice

        // code for tagging the location and the person for the memo
        TestUtils.waitForElementInvisibility(By.className("loader"));
        submittingAt.sendKeys(submitting_at);
        TestUtils.waitForElementInvisibility(By.className("loader"));
        submittingTo.sendKeys(submitting_to);
        TestUtils.waitForElementInvisibility(By.className("loader"));

        // code for actually submitting the memo
        submitMemoBtn.click();
        nextSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("//button[@type='submit']"));
        TestUtils.waitForWebElementToBeClickable(nextSubmitMemoBtn).click();
        finalSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container/div/div/app-memo-submit-confirm/div[3]/div/div[2]/button"));
        TestUtils.waitForWebElementToBeClickable(finalSubmitMemoBtn).click();

        //print button will be visible once the memo is submitted successfully
        TestUtils.waitForElementInvisibility(By.className("loader"));
        boolean isPrintBtnVisible = TestUtils.isElementVisible(printBtn);
        homeBtn.click();							// going back to home button for next test
        return isPrintBtnVisible;
    }

    public String statusOfSubmittedMemo(String invoice_number){
        String inv="TESTINV4309";
        TestUtils.waitForElementInvisibility(By.className("loader"));
        loadDashboardBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));
        By xpath = By.xpath("//label[normalize-space()='" + inv + "']");
        // Wait for the element to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Wait for maximum of 10 seconds
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        element.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));
        String status=statusOfInv.getText();
        statusCloseBtn.click();
        return status;
    }


    public boolean submitMemoWithoutData(String SHEET_PATH_FOR_EMPTY_INVOICE) {

        TestUtils.waitForElementInvisibility(By.className("loader"));
        uploadFileBtn.sendKeys(SHEET_PATH_FOR_EMPTY_INVOICE);
        boolean isSubmitMemoBtnDisplayed = TestUtils.isElementVisible(submitMemoBtn);
        return isSubmitMemoBtnDisplayed;
    }

    public boolean createMultipleInvoiceInSingleMemo(String SHEET_PATH_FOR_MULTIPLE_INVOICE, String submitting_at, String submitting_to) {

        uploadExcelSheet(SHEET_PATH_FOR_MULTIPLE_INVOICE);					// uploading the excel sheet with multiple invoices
        TestUtils.waitForElementInvisibility(By.className("loader"));

        editBtn1.click();
        attachSampleInvoiceFile();
        TestUtils.waitForWebElementToBeClickable(saveBtn).click();	// saving the first invoice
        TestUtils.waitForElementInvisibility(By.className("loader"));

        editBtn2.click();
        attachSampleInvoiceFile();
        TestUtils.waitForWebElementToBeClickable(saveBtn).click();	// saving the second invoice
        TestUtils.waitForElementInvisibility(By.className("loader"));

        // code for tagging the location and the person for the memo
        TestUtils.waitForElementInvisibility(By.className("loader"));
        submittingAt.sendKeys(submitting_at);
        TestUtils.waitForElementInvisibility(By.className("loader"));
        submittingTo.sendKeys(submitting_to);

        // code for actually submitting the memo
        submitMemoBtn.click();
        nextSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("//button[@type='submit']"));
        TestUtils.waitForWebElementToBeClickable(nextSubmitMemoBtn).click();
        finalSubmitMemoBtn = TestUtils.waitForElementVisibility(By.xpath("/html/body/modal-container/div/div/app-memo-submit-confirm/div[3]/div/div[2]/button"));
        TestUtils.waitForWebElementToBeClickable(finalSubmitMemoBtn).click();

        //print button will be visible once the memo is submitted successfully
        TestUtils.waitForElementInvisibility(By.className("loader"));
        boolean isPrintBtnVisible = TestUtils.isElementVisible(printBtn);
        homeBtn.click();							// going back to home button for next test
        return isPrintBtnVisible;
    }
}

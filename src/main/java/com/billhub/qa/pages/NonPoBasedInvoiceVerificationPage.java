package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NonPoBasedInvoiceVerificationPage extends TestBase {
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/div/input")
    WebElement memoInput;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/button[2]")
    WebElement verifyMemoBtn;
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-list/div/div/div[4]/div/div/div/div[7]/div/button")
    WebElement verifyBtn;
    @FindBy(xpath = "//button[normalize-space()='Get Advances']")
    WebElement getAdvanceBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/div[3]/div/div/div[1]/div/div[3]/button[2]")
    WebElement invoiceScanedCopyBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]/ng-select/div/div/div[3]/input")
    WebElement hsnCodeInput;

    @FindBy(xpath = "//div[@class='ng-select-container']//input[@role='combobox']")
    WebElement taxCodeInput;
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[4]/input")
    WebElement tdInput;
    @FindBy(xpath = "//*[@id=\"defaultCheck1\"]")
    WebElement tdsCheckbox;
    @FindBy(xpath = "//input[@placeholder='Assignment']")
    WebElement assignmentInput;
    @FindBy(xpath = "//input[@placeholder='Itemtext']")
    WebElement itemTextInput;
    @FindBy(xpath = "//button[normalize-space()='Verify Invoice']")
    WebElement verifyInvoiceBtn;
    @FindBy(xpath = "//button[normalize-space()='CANCEL']")
    WebElement cancelBtn;

    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]/ng-select/ng-dropdown-panel/div/div[2]/div")
    WebElement hsnSelectTab;
    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[3]/ng-select/ng-dropdown-panel/div/div[2]/div")
    WebElement taxcodeSelectTab;

    @FindBy(xpath = "//div[@class='input-group']//select[@id='inputGroupSelect02']")
    WebElement rejectInput;
    @FindBy(xpath = "//button[normalize-space()='Reject']")
    WebElement rejectBtn;
    @FindBy(xpath = "//button[normalize-space()='Invoice Scanned Copy']")
    WebElement invoiceScannedCopyBtn;

    @FindBy(xpath = "//i[@class='fa fa-file-pdf faFont']")
    WebElement invoiceFileBtn;

    @FindBy(xpath = "/html/body/modal-container[2]/div/div/app-invoice-attachments-popup/div[2]/div/div[1]/div/div/div/div/iframe")
    WebElement invoiceFilePreview;

    @FindBy(xpath = "//button[@aria-label='Close']")
    WebElement closePopupBtn;
    @FindBy(xpath = "/html/body/modal-container/div/div/app-verify-error/div[2]/table/tbody/tr/td[2]")
    WebElement messageType;

    @FindBy(xpath = "//a[normalize-space()='Filter']")
    WebElement filterBtn;
    @FindBy(css = "input[formcontrolname='memo']")
    WebElement memo;
    @FindBy(css = "button[type='submit']")
    WebElement applyBtn;
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[13]")
    WebElement memoStatus;

  public NonPoBasedInvoiceVerificationPage(){PageFactory.initElements(driver,this);}

    public boolean invoiceVerification(String hsn_code,String tax_code,String assignment,String item_text){
        String memo_number = "30005574-2023-24-00007";

        memoInput.sendKeys(memo_number);
        WebElement selectTab= TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        verifyBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));

        hsnCodeInput.sendKeys(hsn_code);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        hsnSelectTab.click();
        taxCodeInput.sendKeys(tax_code);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        taxcodeSelectTab.click();


        if (tdsCheckbox.isSelected()) {
            // If selected, click to unselect it
            tdsCheckbox.click();
        }

        assignmentInput.sendKeys(assignment);
        itemTextInput.sendKeys(item_text);
        verifyInvoiceBtn.click();
        return TestUtils.isSuccessToastDisplayed("Invoice verified and simulate successfully");
    }

    public boolean invalideTaxCode(String hsn_code,String tax_code,String assignment,String item_text ){
        String memo_number = "30005574-2023-24-00009";

        memoInput.sendKeys(memo_number);
        WebElement selectTab= TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        verifyBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));

        hsnCodeInput.sendKeys(hsn_code);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        hsnSelectTab.click();
        taxCodeInput.sendKeys(tax_code);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        taxcodeSelectTab.click();


        tdsCheckbox.click();
        assignmentInput.sendKeys(assignment);
        itemTextInput.sendKeys(item_text);
        verifyInvoiceBtn.click();
        return TestUtils.isSuccessToastDisplayed("Invoice verified and similiate successfully");
    }

    public String invoiceVerificationWithInvalideData(String hsn_code,String tax_code,String assignment,String item_text ){
        String memo_number = "30005574-2023-24-00009";

        memoInput.sendKeys(memo_number);
        WebElement selectTab= TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        verifyBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));

        hsnCodeInput.sendKeys(hsn_code);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        hsnSelectTab.click();
        taxCodeInput.sendKeys(tax_code);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        taxcodeSelectTab.click();


        tdsCheckbox.click();
        assignmentInput.sendKeys(assignment);
        itemTextInput.sendKeys(item_text);
        verifyInvoiceBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));

        String message=messageType.getText();
        closePopupBtn.click();
        return message;

    }

    public boolean invoiceRejection(String reject_reason){
        String memo_number = "30005574-2023-24-00009";

        memoInput.sendKeys(memo_number);
        WebElement selectTab= TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        verifyBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));

        rejectInput.click();
        TestUtils.waitForElementVisibility(By.xpath("//option[normalize-space()='Agreement not available']")).click();
        return true;
    }

    public boolean ValidateInvoiceFilePreview() {

        String memo_number = "30005574-2023-24-00009";

        memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        verifyInvoiceBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceScannedCopyBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceFileBtn.click();

        return TestUtils.isElementVisible(invoiceFilePreview);
    }

    public String validateVerifiedMemoStatus(){
        String memo_number = "30005574-2023-24-00007";

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        filterBtn.click();											//searching for the memo in the filter option
        memo.sendKeys(memo_number);
        applyBtn.click();

        boolean isMemoFound = TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[5]"), memo_number);

        if(isMemoFound == false)						// if the correct memo is not found return false
            return "Memo was not found.";

        return memoStatus.getText();
    }
}

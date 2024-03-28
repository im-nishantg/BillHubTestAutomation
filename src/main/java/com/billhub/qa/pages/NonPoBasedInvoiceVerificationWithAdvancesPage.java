package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NonPoBasedInvoiceVerificationWithAdvancesPage extends TestBase {
    
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/div/input")
    WebElement memoInput;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/button[2]")
    WebElement verifyBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-list/div/div/div[4]/div/div/div/div[7]/div/button")
    WebElement verifyInvoiceBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/div[3]/div/div/div[1]/div/div[3]/button[1]")
    WebElement getAdvanceBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/form/div/div[1]/div[1]/div[1]/div/div[2]/button")
    WebElement addAdvanceBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/form/div/div[1]/div[1]/div[2]/table/tbody/tr/td[2]/ng-select/div/div/div[3]/input")
    WebElement advanceDocumentInput;
    
    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-verification/div/div/form/div/div[1]/div[1]/div[2]/table/tbody/tr/td[2]/ng-select/ng-dropdown-panel/div/div[2]/div")
    WebElement advancDoceNumberSelectTab;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/form/div/div[1]/div[1]/div[2]/table/tbody/tr/td[5]/input")
    WebElement advanceAmountInput;

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
    WebElement finalverifyInvoiceBtn;
    
    @FindBy(xpath = "//button[normalize-space()='CANCEL']")
    WebElement cancelBtn;

    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]/ng-select/ng-dropdown-panel/div/div[2]/div")
    WebElement hsnSelectTab;
    
    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[3]/ng-select/ng-dropdown-panel/div/div[2]/div")
    WebElement taxcodeSelectTab;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-verification/div/div/form/div/div[2]/div[3]/div[2]/table/tbody/tr[3]/td[2]")
    WebElement igstAmount;

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

  public NonPoBasedInvoiceVerificationWithAdvancesPage(){PageFactory.initElements(driver,this);}
  
    public boolean InvoiceVerificationWithAdvances(String hsn_code,String assignment,String item_text, String memo_number,String advance_doc_number,String advance_amount){

        memoInput.sendKeys(memo_number);
        WebElement selectTab= TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        verifyInvoiceBtn.click();

        TestUtils.waitForElementInvisibility(By.className("loader"));
        String igst_amount = igstAmount.getText().substring(2);						// Selecting wrong tax-code based on the igst amount
        String tax_code = igst_amount.equals("0") ? "V0 - 0% Tax" : "KG - 18% Input  IGST Deductible";


        getAdvanceBtn.click();
        TestUtils.waitForElementInvisibility(By.className("loader"));

        addAdvanceBtn.click();
        advanceDocumentInput.sendKeys(advance_doc_number);
        TestUtils.waitForElementInvisibility(By.className("loader"));
        advancDoceNumberSelectTab.click();

        advanceAmountInput.sendKeys(advance_amount);


        hsnCodeInput.sendKeys(hsn_code);
        TestUtils.waitForElementInvisibility(By.className("loader"));
        hsnSelectTab.click();
        taxCodeInput.sendKeys(tax_code);
        TestUtils.waitForElementInvisibility(By.className("loader"));
        taxcodeSelectTab.click();


        tdsCheckbox.click();
        assignmentInput.sendKeys(assignment);
        itemTextInput.sendKeys(item_text);
        finalverifyInvoiceBtn.click();
        return TestUtils.isSuccessToastDisplayed("Invoice verified and similiate successfully");
    }
}

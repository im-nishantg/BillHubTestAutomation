package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NonPoBasedInvoiceAcknowledgementPage extends TestBase {

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[4]/button")
    WebElement loadDashboardBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]")
    WebElement dashboardFirstRow;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/div/input")
    WebElement memoInput;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/button[1]")
    WebElement acknowledgeBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/button[2]")
    WebElement verifyBtn;

    @FindBy(xpath = "//button[normalize-space()='Reject Memo']")
    WebElement rejectMemoBtn;

    @FindBy(xpath = "//button[normalize-space()='Reject Invoice']")
    WebElement rejectInvoiceBtn;

    @FindBy(xpath = "//button[normalize-space()='Re-Assign Memo']")
    WebElement reassignMemoBtn;

    @FindBy(xpath = "//button[normalize-space()='Acknowledge memo']")
    WebElement acknowledgeMemoBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-acknowledge/div/div/div[3]/div/table/tbody/tr[1]/td[1]/div/input")
    WebElement invoiceCheckBox;

    @FindBy(xpath = "//select[@formcontrolname='submittingForm']")
    WebElement submittingFrom;

    @FindBy(xpath = "//select[@formcontrolname='submittedTo']")
    WebElement submittingTo;

    @FindBy(xpath = "//button[normalize-space()='Assign']")
    WebElement assignMemo;

    @FindBy(xpath = "//select[@placeholder='Select Reason']")
    WebElement selectReason;

    @FindBy(xpath = "//td[normalize-space()='Invoice acknowledged successFully']")
    WebElement acknowledgeInvoiceSuccessMessage;

    @FindBy(xpath = "/html/body/modal-container/div/div/app-invoice-error/div[1]/button")
    WebElement closePopupBtn;

    @FindBy(xpath = "//td[normalize-space()='Invoice rejected successFully']")
    WebElement rejectMemoSuccessMessage;

    @FindBy(xpath = "//a[normalize-space()='Filter']")
    WebElement filterBtn;

    @FindBy(css = "input[formcontrolname='memo']")
    WebElement memo;

    @FindBy(css = "button[type='submit']")
    WebElement applyBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[13]")
    WebElement memoStatus;

    public NonPoBasedInvoiceAcknowledgementPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean ValidateSubmittedNonPoBasedInvoiceInDashboard() {
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        loadDashboardBtn.click();
        return TestUtils.isElementVisible(dashboardFirstRow);
    }

    public boolean ValidateAcknowledgeTheMemo() {

        String memo_number = "30005574-2023-24-00009";

        memoInput.sendKeys(memo_number);
        WebElement selectTab=TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        acknowledgeBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceCheckBox.click();
        acknowledgeMemoBtn.click();

        boolean isInvoiceAcknowledge=TestUtils.isElementVisible(acknowledgeInvoiceSuccessMessage);
        closePopupBtn.click();

        return isInvoiceAcknowledge;
    }

    public boolean ValidateReassignTheMemo(String submit_from,String submit_to){

        String memo_number = "30005631-2023-24-00019";

        memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        acknowledgeBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceCheckBox.click();
        reassignMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        submittingFrom.sendKeys(submit_from);

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        submittingTo.sendKeys(submit_to);

        assignMemo.click();

        return TestUtils.isSuccessToastDisplayed("Memo reassigned successfully");
    }

    public boolean ValidateRejectTheMemo(String reason){

        String memo_number = "30005574-2023-24-00008";

        memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        acknowledgeBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceCheckBox.click();
        rejectMemoBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        selectReason.sendKeys(reason);
        rejectInvoiceBtn.click();

        boolean isInvoiceRejected=TestUtils.isElementVisible(rejectMemoSuccessMessage);
        closePopupBtn.click();

        return isInvoiceRejected;
    }

    public String validateAcknowledgedMemoStatus() {

        String memo_number = "30005574-2023-24-00009";

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        filterBtn.click();											//searching for the memo in the filter option
        memo.sendKeys(memo_number);
        applyBtn.click();

        boolean isMemoFound = TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[5]"), memo_number);

        if(isMemoFound == false)						// if the correct memo is not found return false
            return "Memo was not found.";

        return memoStatus.getText();
    }

    public String validateRejectedMemoStatus() {

        String memo_number = "30005631-2023-24-00018";

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

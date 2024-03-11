package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NonPoBasedInvoiceValidationPage extends TestBase {

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

    @FindBy(xpath = "//tbody/tr[2]/td[1]")
    WebElement invoiceCheckBox;
    @FindBy(xpath = "//select[@formcontrolname='submittingForm']")
    WebElement submittingFrom;
    @FindBy(xpath = "//select[@formcontrolname='submittedTo']")
    WebElement submittingTo;
    @FindBy(xpath = "//button[normalize-space()='Assign']")
    WebElement assignMemo;
    @FindBy(xpath = "//select[@placeholder='Select Reason']")
    WebElement selectReason;

    public NonPoBasedInvoiceValidationPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean ValidateSubmittedNonPoBasedInvoiceInDashboard() {
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        loadDashboardBtn.click();
        return TestUtils.isElementVisible(dashboardFirstRow);
    }

    public boolean ValidateAcknowledgeTheMemo() {

        String memo_number = "30005574-2023-24-00008";
        memoInput.sendKeys(memo_number);
        WebElement selectTab=TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        acknowledgeBtn.click();
        return true;
    }

    public boolean ValidateReassignMemo(String submit_from,String submit_to){
        String memo_number = "30005631-2023-24-00019";
        memoInput.sendKeys(memo_number);
        WebElement selectTab=TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
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

        boolean isReassigned=TestUtils.isSuccessToastDisplayed("Memo reassigned successfully");
        return isReassigned;

    }

    public boolean ValidateRejectMemo(String reason){
        String memo_number = "30005574-2023-24-00008";
        memoInput.sendKeys(memo_number);
        WebElement selectTab=TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        acknowledgeBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceCheckBox.click();
        rejectMemoBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        selectReason.sendKeys(reason);
        rejectInvoiceBtn.click();

        return  true;

    }
}

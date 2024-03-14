package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class POBasedInvoiceValidationPage extends TestBase{
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[4]/button")
    WebElement loadDashboardBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]")
    WebElement dashboardFirstRow;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/div/input")
    WebElement memoInput;
    
    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button")
    WebElement memoInputFirstOption;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/button[1]")
    WebElement acknowledgeBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/button[2]")
    WebElement verifyBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-acknowledge/div/div/div[3]/div/table/tbody/tr[1]/td[1]/div/input")
    WebElement invoiceCheckbox;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-acknowledge/div/div/div[4]/div[2]/button")
    WebElement acknowledgeMemoBtn;
    
    @FindBy(xpath = "//button[@class='btn btn-danger btn-reject mr-4']")
    WebElement rejectMemoBtn;
    
    @FindBy(xpath = "//button[@class='btn btn-warning btn-re-assign']")
    WebElement reassignMemoBtn;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-reassign-invoice/form/div[1]/div/div/div[1]/select")
    WebElement submittingFrom;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-reassign-invoice/form/div[1]/div/div/div[2]/select")
    WebElement submittingTo;
    
    @FindBy(xpath = "//button[@type='submit']")
    WebElement assignBtn;
    
    @FindBy(xpath = "//select[@placeholder='Select Reason']")
    WebElement selectReason;
    
    @FindBy(xpath = "//button[normalize-space()='Reject Invoice']")
    WebElement rejectInvoiceBtn;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-invoice-error/div[2]/table/tbody/tr/td[3]")
    WebElement rejectMemoSuccessMessage;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-invoice-error/div[2]/table/tbody/tr/td[3]")
    WebElement acknowledgeInvoiceSuccessMessage;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-invoice-error/div[1]/button")
    WebElement closePopupBtn;
    
    @FindBy(xpath = "//a[normalize-space()='Filter']")
    WebElement filterBtn;
    
    @FindBy(css = ".form-control.ng-pristine.ng-valid.ng-touched")
    WebElement memo;
    
    @FindBy(css = "button[type='submit']")
    WebElement applyBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[13]")
    WebElement memoStatus;
    
    public POBasedInvoiceValidationPage() {
    	PageFactory.initElements(driver, this);
    }
    
    public boolean ValidateSubmittedPoBasedInvoiceInDashboard() {
    	
    	loadDashboardBtn.click();
    	return TestUtils.isElementVisible(dashboardFirstRow);
    }
    
    public boolean ValidateAcknowledgeTheMemo() {
        
    	String memo_number = "30005631-2023-24-00005";

    	memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        acknowledgeBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceCheckbox.click();
        acknowledgeMemoBtn.click();
        
        boolean isInvoiceAcknowledged = TestUtils.isElementVisible(acknowledgeInvoiceSuccessMessage);
        closePopupBtn.click();
        
        return isInvoiceAcknowledged;
    }
    
    public boolean ValidateRejectTheMemo() {
        
    	String memo_number = "30005631-2023-24-00004", reason = "Agreement not available";

    	memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        acknowledgeBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceCheckbox.click();
        rejectMemoBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        selectReason.sendKeys(reason);
        rejectInvoiceBtn.click();
        
        boolean isInvoiceRejected = TestUtils.isElementVisible(rejectMemoSuccessMessage);
        closePopupBtn.click();
        
        return isInvoiceRejected;
    }
    
    public boolean ValidateReassignTheMemo() {
        
    	String memo_number = "30005631-2023-24-00019", submitting_from = "Ahmedabad", submitting_to = "suraj suraj";
    	
    	memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        acknowledgeBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        reassignMemoBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        submittingFrom.sendKeys(submitting_from);
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        submittingTo.sendKeys(submitting_to);
        
        assignBtn.click();
        
        return TestUtils.isSuccessToastDisplayed("Memo reassigned successfully");
    }
    
    public String validateAcknowledgedMemoStatus() {
    	
    	String memo_number = "30005631-2023-24-00005";
    	
    	filterBtn.click();											//searching for the memo in the filter option
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	memo.sendKeys(memo_number);
    	applyBtn.click();
    	
    	boolean isMemoFound = TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[5]"), memo_number);
    	
    	if(isMemoFound == false)						// if the correct memo is not found return false
    		return "Memo was not found.";

    	return memoStatus.getText();
    }
    
    public String validateRejectedMemoStatus() {
    	
    	String memo_number = "30005631-2023-24-00018";
    	
    	filterBtn.click();											//searching for the memo in the filter option
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	memo.sendKeys(memo_number);
    	applyBtn.click();
    	
    	boolean isMemoFound = TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[5]"), memo_number);
    	
    	if(isMemoFound == false)						// if the correct memo is not found return false
    		return "Memo was not found.";

    	return memoStatus.getText();
    }
}

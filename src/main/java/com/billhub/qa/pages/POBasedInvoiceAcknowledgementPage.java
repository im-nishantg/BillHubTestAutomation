package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;

public class POBasedInvoiceAcknowledgementPage extends TestBase{
	
	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[4]/button")			// WebElements of Commercial dashboard page
    WebElement loadDashboardBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]")
    WebElement dashboardFirstRow;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/div/input")
    WebElement memoInput;
    
    @FindBy(xpath = "//a[normalize-space()='Filter']")
    WebElement filterBtn;
    
    @FindBy(xpath = "//span[normalize-space()='PO Based']")
    WebElement invoiceTypeDropdown;
    
    @FindBy(xpath = "//li[normalize-space()='PO']")
    WebElement poTab;
    
    @FindBy(css = "input[formcontrolname='memo']")
    WebElement memo;
    
    @FindBy(css = "input[formcontrolname='inv']")
    WebElement invoice;
    
    @FindBy(css = "button[type='submit']")
    WebElement applyBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[5]")
    WebElement firstRowMemoNumber;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[4]")
    WebElement firstRowInvoiceNumber;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[13]")
    WebElement memoStatus;
    
    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button")
    WebElement memoInputFirstOption;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/button[1]")
    WebElement acknowledgeBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/button[2]")
    WebElement verifyBtn;
    
    @FindBy(xpath = "//button[@class='btn btn-link']")
	WebElement navbarExpandBtn;
    
    @FindBy(xpath = "//span[normalize-space()='Dashboard']")
	WebElement dashboardBtn;
    
    @FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/perfect-scrollbar/div/div[1]/div[2]/ul/li[3]/a")
   	WebElement actionBtn;
    
    @FindBy(xpath = "//a[normalize-space()='Approve invoice for Booking']")			
	WebElement approveInvoiceForBookingBtn;
    
    @FindBy(xpath = "//a[normalize-space()='Review Invoice Posting']")			
	WebElement reviewInvoicePostingBtn;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-acknowledge/div/div/div[3]/div/table/tbody/tr[1]/td[1]/div/input") // WebElements of Acknowledge invoice page
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
    
    @FindBy(xpath = "//td[normalize-space()='Invoice rejected successFully']")
    WebElement rejectMemoSuccessMessage;
    
    @FindBy(xpath = "//td[normalize-space()='Invoice acknowledged successFully']")
    WebElement acknowledgeInvoiceSuccessMessage;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-invoice-error/div[1]/button")
    WebElement closePopupBtn;
	
	@FindBy(xpath = "//button[@class='btn btn-lg btn-block btn-primary ng-star-inserted']")
    WebElement verifyInvoiceBtn;
    
    @FindBy(xpath = "//ng-select[@placeholder='Select Tax Code']//input[@role='combobox']")	// WebElements of Verify invoice page
    WebElement taxCodeInput;
    
    @FindBy(xpath = "//input[@placeholder='Assignment']")
    WebElement assignmentInput;
    
    @FindBy(xpath = "//input[@placeholder='Itemtext']")
    WebElement itemTextInput;
    
    @FindBy(xpath = "//button[normalize-space()='Invoice Scanned Copy']")
    WebElement invoiceScannedCopyBtn;
    
    @FindBy(xpath = "//i[@class='fa fa-file-pdf faFont']")
    WebElement invoiceFileBtn;
    
    @FindBy(xpath = "/html/body/modal-container[2]/div/div/app-invoice-attachments-popup/div[2]/div/div[1]/div/div/div/div/iframe")
    WebElement invoiceFilePreview;
    
    @FindBy(xpath = "//*[@id=\"defaultCheck1\"]")
    WebElement tdsApplicableCheckbox;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-po-verification/div/div/form/div/div[2]/div[4]/div/button")
    WebElement finalVerifyInvoiceBtn;
    
    @FindBy(xpath = "/html/body/app-root/app-layout/div[1]/main/div/div/app-po-verification/div/div/form/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]/ng-select/ng-dropdown-panel/div/div[2]/div")
    WebElement taxCodeSelectTab;
    
    @FindBy(xpath = "//*[@id=\"inputGroupSelect05\"]")
    WebElement rejectInput;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-po-verification/div/div/form/div/div[2]/div[2]/div[2]/table/tbody/tr[3]/td[2]")
    WebElement igstAmount;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-po-verification/div/div/form/div/div[2]/div[3]/div/div/div/button")
    WebElement rejectBtn;
    
    @FindBy(xpath = "//input[@id='invoiceNumber']") // WebElements of booking page
	WebElement invoiceNumberInput;
    
    @FindBy(xpath = "//input[@id='baName']") 
	WebElement baNameInput;
    
    @FindBy(xpath = "//span[@class='display-text']") 
	WebElement verifiedBydropDown;
    
    @FindBy(xpath = "//input[@name='search-text']") 
	WebElement verifiedByInput;
    
    @FindBy(xpath = "//button[@type='submit']")
	WebElement searchInvoiceBtn;
    
    @FindBy(xpath = "//button[normalize-space()='Download']")
	WebElement downloadBtn;
    
    @FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement bookInvoiceBtn;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-notify-dialog/div[2]/div/code/b")
	WebElement generatedBatchID;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-review-invoice-list/div/div/div[3]/div/table/tbody/tr[1]/td[1]/div/input")
   	WebElement firstInvoiceCheckbox;
    
    @FindBy(xpath = "/html/body/modal-container/div/div/app-book-invoice-confirm/div[3]/div/div[2]/button")
	WebElement finalBookBtn;
    
    @FindBy(xpath = "//input[@placeholder='Search For BatchId']")			// WebElements of Review invoice posting
	WebElement searchBatchIdInput;
    
    @FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement searchBatchIdBtn;
    
    @FindBy(xpath = "//button[@class='btn btn-primary btn-sm btn-small']")
	WebElement bookingLogBtn;
    
    @FindBy(xpath = "//button[@class='btn btn-primary btn-block']")
	WebElement downloadButton;
    
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-search-batch/div/div/div[2]/div/table/tbody/tr/td[4]/span")
	WebElement documentNumbers;
    
    String memo_number, invoice_number, batch_id;  	// these variables will be used in Acknowledgement, verification and booking of invoice
    
    public POBasedInvoiceAcknowledgementPage() {
    	PageFactory.initElements(driver, this);
    }
   
    // **********************   Functions associated with Acknowledgement of the submitted Invoice  **************************
    
    public boolean validateSubmittedPoBasedInvoiceInDashboard() {
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	loadDashboardBtn.click();
    	return TestUtils.isElementVisible(dashboardFirstRow);
    }
    
    public boolean validateAcknowledgeTheMemo() {
        
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	filterBtn.click();	
    	invoiceTypeDropdown.click();
    	poTab.click();
    	applyBtn.click();
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
    	memo_number = firstRowMemoNumber.getText();						// reading memo number from the first row in the dashboard
    	invoice_number = firstRowInvoiceNumber.getText();	
    	
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
    
    public String validateAcknowledgedMemoStatus() {	// Test for verifying the status of acknowledged memo in the dashboard
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(dashboardBtn).click();
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
    	filterBtn.click();											//searching for the invoice in the filter option
    	invoice.sendKeys(invoice_number);
    	applyBtn.click();
    	
    	boolean isMemoFound = TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[4]"), invoice_number);
    	
    	if(isMemoFound == false)						// if the correct memo is not found return false
    		return "Memo was not found.";

    	return memoStatus.getText();
    }
    
    public boolean ValidateRejectTheMemo(String memo_number, String reason) {

    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
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
    
    public String validateRejectedMemoStatus(String invoice_number) {		// Test for verifying the status of rejected memo in the dashboard
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
    	filterBtn.click();											//searching for the memo in the filter option
    	invoice.sendKeys(invoice_number);
    	applyBtn.click();
    	
    	boolean isInvoiceFound = TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[4]"), invoice_number + "-Rejected");
										    																	
    	if(isInvoiceFound == false)						// if the correct memo is not found return false
    		return "Invoice was not found.";

    	return memoStatus.getText();
    }
    
    public boolean ValidateReassignTheMemo(String memo_number, String submitting_from, String submitting_to) {
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
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
    
    public boolean acknowledgingInvalidGRN(String memo_number) {
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
    	memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        acknowledgeBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        invoiceCheckbox.click();
        acknowledgeMemoBtn.click();
        
        return TestUtils.isSuccessToastDisplayed("Please only select invoices with valid GRN");
    }
    
    
    // *********************   Functions associated with Verification of the acknowledged Invoice  ***********************
    
    public boolean validateInvoiceFilePreview(String memo_number) {

    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
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
    
    public boolean verifyInvoiceWithInvalidTaxCode(String memo_number, String assignment, String item_text) {

    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
    	memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        verifyInvoiceBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        String igst_amount = igstAmount.getText().substring(2);						// Selecting wrong tax-code based on the igst amount
        String tax_code = igst_amount.equals("0") ?  "KG - 18% Input  IGST Deductible" : "V0 - 0% Tax";
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        tdsApplicableCheckbox.click();
        assignmentInput.sendKeys(assignment);
        itemTextInput.sendKeys(item_text);
        taxCodeInput.sendKeys(tax_code);
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        taxCodeSelectTab.click();
        finalVerifyInvoiceBtn.click();
        
        return TestUtils.isSuccessToastDisplayed("Invoice verified and simulate successfully");
    }
    
    public boolean verifyInvoiceWithValidData(String assignment, String item_text) {	
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        verifyInvoiceBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        String igst_amount = igstAmount.getText().substring(2);								// Selecting tax-code based on the igst amount
        String tax_code = igst_amount.equals("0") ? "V0 - 0% Tax" : "KG - 18% Input  IGST Deductible" ;
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        tdsApplicableCheckbox.click();
        assignmentInput.sendKeys(assignment);
        itemTextInput.sendKeys(item_text);
        taxCodeInput.sendKeys(tax_code);
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        taxCodeSelectTab.click();
        finalVerifyInvoiceBtn.click();
        
        return TestUtils.isSuccessToastDisplayed("Invoice verified and simulate successfully");
    }
    
    public String validateVerifiedMemoStatus() {	// Test for verifying the status of verified memo in the dashboard
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	TestUtils.waitForWebElementToBeClickable(dashboardBtn).click();
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
    	filterBtn.click();											//searching for the memo in the filter option
    	invoice.sendKeys(invoice_number);
    	applyBtn.click();
    	
    	boolean isMemoFound = TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[4]"), invoice_number);
    	
    	if(isMemoFound == false)						// if the correct memo is not found return false
    		return "Memo was not found.";

    	return memoStatus.getText();
    }
    
    public boolean ValidateRejectTheAcknowledgedInvoice(String memo_number, String reason) {

        memoInput.sendKeys(memo_number);
        WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-dashboard/div/div[1]/div[2]/div/typeahead-container/button"));
        selectTab.click();
        verifyBtn.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        verifyInvoiceBtn.click();
        
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        rejectInput.sendKeys(reason);
        rejectBtn.click();
        
        return TestUtils.isSuccessToastDisplayed("Invoice rejected successFully");
    }

    // ********************* Functions associated with booking of the verified invoice *****************
    
    public boolean bookingInvoiceWithValidData() {
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(approveInvoiceForBookingBtn).click();
    	
    	invoiceNumberInput.sendKeys(invoice_number);
    	searchInvoiceBtn.click();
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	firstInvoiceCheckbox.click();
    	bookInvoiceBtn.click();
    	
    	TestUtils.waitForWebElementToBeClickable(finalBookBtn).click();
    	
    	boolean isInvoiceBooked = TestUtils.isElementVisible(generatedBatchID);
    	
    	if(isInvoiceBooked)								// copy the batch id if the test is successful
    	{
    		batch_id = generatedBatchID.getText();
    		WebElement closePopupBtn = TestUtils.waitForElementToBeClickable(By.xpath("/html/body/modal-container/div/div/app-notify-dialog/div[1]/button"));
    		closePopupBtn.click();
    	}
    		
    	return isInvoiceBooked;
    }
    
    public boolean searchingInvoiceWithMultipleFields(String invoice_number, String ba_name) {
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(approveInvoiceForBookingBtn).click();
    	
    	invoiceNumberInput.sendKeys(invoice_number);
    	baNameInput.sendKeys(ba_name);
    	searchInvoiceBtn.click();

    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
    	return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-review-invoice-list/div/div/div[3]/div/table/tbody/tr/td[3]/button"), invoice_number);
    }
    
    public boolean searchingInvoiceWithInvalidData(String invoice_number, String ba_name) {
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(approveInvoiceForBookingBtn).click();
    	
    	invoiceNumberInput.sendKeys(invoice_number);
    	baNameInput.sendKeys(ba_name);
    	searchInvoiceBtn.click();

    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	
    	return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-review-invoice-list/div/div/div[3]/div/table/tbody/tr/td[3]/button"), invoice_number);
    }
    
    public boolean downloadingInvoiceBeforeBooking(String invoice_number, String ba_name, String verified_by) {
		
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(approveInvoiceForBookingBtn).click();
    	
    	invoiceNumberInput.sendKeys(invoice_number);
    	baNameInput.sendKeys(ba_name);
    	verifiedBydropDown.click();
    	verifiedByInput.sendKeys(verified_by);
    	WebElement firstOption = TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"verifiedBy\"]/div/div/ul[2]/li"));
    	firstOption.click();
    	searchInvoiceBtn.click();
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	firstInvoiceCheckbox.click();
    	downloadBtn.click();
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	return true;
	}
    
    // ********************* Functions associated with Reviewing of the booked invoice for posting *****************
    
    public boolean searchByTransactionBatchId() {
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(reviewInvoicePostingBtn).click();
    	
    	searchBatchIdInput.sendKeys(batch_id);
    	WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-search-batch/div/div/div[1]/div[2]/div/div/typeahead-container/button/span/strong"));
        selectTab.click();
    	searchBatchIdBtn.click();
    	
    	return TestUtils.isElementVisible(bookingLogBtn);
    }
    
    public boolean searchByInvalidTransactionBatchId(String batch_id) {
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
    	TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(reviewInvoicePostingBtn).click();
    	
    	searchBatchIdInput.sendKeys(batch_id);
    	searchBatchIdBtn.click();
    	return TestUtils.isElementVisible(bookingLogBtn);
    }

	public boolean validatingTwoDocumentNumbers(String batch_id) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(reviewInvoicePostingBtn).click();
    	
    	searchBatchIdInput.sendKeys(batch_id);
    	WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-search-batch/div/div/div[1]/div[2]/div/div/typeahead-container/button/span/strong"));
        selectTab.click();
    	searchBatchIdBtn.click();
    	
    	return TestUtils.isElementVisible(documentNumbers);
	}

	public boolean downloadingBookedInvoiceTest(String batch_id) {

		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(navbarExpandBtn).click();
    	TestUtils.waitForWebElementToBeClickable(actionBtn).click();
    	TestUtils.waitForWebElementToBeClickable(reviewInvoicePostingBtn).click();
    	
    	searchBatchIdInput.sendKeys(batch_id);
    	WebElement selectTab = TestUtils.waitForElementVisibility(By.xpath("/html/body/app-root/app-layout/div[1]/main/div/div/app-search-batch/div/div/div[1]/div[2]/div/div/typeahead-container/button/span/strong"));
        selectTab.click();
    	searchBatchIdBtn.click();
    	downloadButton.click();
    	
    	TestUtils.waitForElementInvisibility(By.className("modal-container"));
		return true;
	}

	
    
    
}

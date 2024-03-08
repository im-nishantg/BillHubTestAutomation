package com.billhub.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BADashboardPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-primary ng-star-inserted']")
	WebElement createMemoBtn;
	
	@FindBy(xpath = "//span[normalize-space()='Create Memo']")
	WebElement createMemoTab;
	
	@FindBy(xpath = "//input[@id='defaultCheck1']")
	WebElement POBasedInvoiceCheckbox;
	
	@FindBy(xpath = "//select[@id='frmState']")
	WebElement fromState;
	
	@FindBy(xpath = "//select[@id='toState']")
	WebElement toState;
	
	@FindBy(xpath = "//button[normalize-space()='Proceed Manually']")
	WebElement proceedManuallyBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Upload & Continue']")
	WebElement updateAndContinueBtn;

	@FindBy(xpath = "//a[normalize-space()='Filter']")
	WebElement filterBtn;

	@FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[1]/ngx-select-dropdown")
	 WebElement company;

	@FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[2]/ngx-select-dropdown")
	WebElement location;

	@FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[3]/ngx-select-dropdown")
	WebElement service;

	@FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[4]/ngx-select-dropdown/div/button")
	WebElement poBtn;

	@FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[5]/input")
	WebElement invoice;

	@FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[6]/input")
	WebElement memo;

	@FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[8]/button")
	WebElement applyBtn;

	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[1]/ngx-select-dropdown/div/div/div/input")
	 WebElement companySearch;

	@FindBy(xpath = "//input[@name='search-text']")
	WebElement locationSearch;

	@FindBy(xpath="//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[3]/ngx-select-dropdown/div/div/div/input")
	WebElement serviceSearch;

	@FindBy(xpath ="//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[1]/ngx-select-dropdown/div/div/ul[2]/li")
	WebElement targetCompany;

	@FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[2]/ngx-select-dropdown/div/div/ul[2]/li[1]")
	WebElement targetLocation;

	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[3]/ngx-select-dropdown/div/div/ul[2]")
	WebElement targetService;

	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[4]/ngx-select-dropdown/div/div/ul[2]/li[3]")
	WebElement both;

	@FindBy(xpath="//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[4]/div[1]/span[2]/i")
	WebElement del_company;

	@FindBy(xpath="//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[4]/div[1]/span[2]/i")
	WebElement del_location;

	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[4]/div[1]/span[3]/i")
	WebElement del_service;

	@FindBy(xpath="//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[4]/div[1]/span[2]/i")
	WebElement del_memo;

	@FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/div/input")
	WebElement viewMemoBar;

	public BADashboardPage() {
		PageFactory.initElements(driver, this);
	}

	public void initializePopupWebElements(){
		PageFactory.initElements(driver,this);
	}

	public CreatePOBasedInvoicePage createNewMemo() {

		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		TestUtils.waitForWebElementToBeClickable(createMemoBtn).click();
		TestUtils.waitForWebElementToBeClickable(createMemoTab).click();
		POBasedInvoiceCheckbox.click();
		fromState.sendKeys("Rajasthan");
		toState.sendKeys("Delhi");
		proceedManuallyBtn.click();
		return new CreatePOBasedInvoicePage();
		
	}

	public boolean filterByCompany(String company_name){
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		filterBtn.click();
		company.click();
		companySearch.sendKeys(company_name);
		targetCompany.click();
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));

		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), company_name);

	}

	public boolean filterByService(String service_name) {
		filterBtn.click();
		service.click();
		serviceSearch.sendKeys(service_name);
		targetService.click();
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));

		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), "1022");
	}

	public boolean filterByCompanyAndLocation(String location_name){
		del_service.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));

		filterBtn.click();
		location.click();
		locationSearch.sendKeys(location_name);
		targetLocation.click();
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));

		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[9]"), location_name);

	}
	 public boolean filterByLocation(String location_name) {
//		removing previously applied filters
		del_location.click();
		 TestUtils.waitForElementInvisibility(By.className("modal-container"));
		del_company.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));

		filterBtn.click();
		location.click();
		locationSearch.sendKeys(location_name);
		targetLocation.click();
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));

		 return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[9]"), location_name);

	 }

	public boolean filterByPo() {
		//		removing previously applied filters
		del_location.click();

		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		filterBtn.click();
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));

		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), "1022");
	}

//	public boolean filterByNonPo() {
//		filterBtn.click();
//		poBased.click();
//		non_Po.click();
//		applyBtn.click();
//		TestUtils.waitForElementVisibility(By.className("dashboard-table"));
//		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), "1022");
//	}

	public boolean filterByBoth() {
		filterBtn.click();
		poBtn.click();
		both.click();
		TestUtils.waitForElementInvisibility(By.className("selected-items"));
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));

		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), "1022");
	}

	public boolean filterByCompanyServiceAndMemo(String company_name,String service_name,String memo_num) {
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		filterBtn.click();
		company.click();
		companySearch.sendKeys(company_name);
		targetCompany.click();
//		clicking outside to remove the selected dropdown which is covering other filter options
		viewMemoBar.click();
		service.click();
		serviceSearch.sendKeys(service_name);
		targetService.click();
		viewMemoBar.click();
		memo.sendKeys(memo_num);
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));

		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[2]/td[5]"), memo_num);
	}

	public boolean filterByInvoiceServiceAndLocation(String invo_num,String service_name,String loc_name) {
		//		removing previously applied filters
		del_memo.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		del_service.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		del_company.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));

		filterBtn.click();
		invoice.sendKeys(invo_num);
		service.click();
		serviceSearch.sendKeys(service_name);
		targetService.click();
		viewMemoBar.click();
		location.click();
		locationSearch.sendKeys(loc_name);
		targetLocation.click();
		viewMemoBar.click();
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));

		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[4]/label"), invo_num);
	}

}


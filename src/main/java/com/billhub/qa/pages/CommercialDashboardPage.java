package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.billhub.qa.base.TestBase.driver;

public class CommercialDashboardPage extends TestBase{

    @FindBy(xpath = "//a[normalize-space()='Filter']")
    WebElement filterBtn;

    @FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[1]/ngx-select-dropdown")
    WebElement company;

    @FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[2]/ngx-select-dropdown")
    WebElement location;

    @FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[3]/ngx-select-dropdown")
    WebElement service;

    @FindBy(css = "ngx-select-dropdown[formcontrolname='poDropDown'] button[type='button']")
    WebElement poBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[9]/input")
    WebElement invoice;

    @FindBy(css= ".form-control.ng-pristine.ng-valid.ng-touched")
    WebElement memo;

    @FindBy(css= "button[type='submit']")
    WebElement applyBtn;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[1]/ngx-select-dropdown/div/div/div/input")
    WebElement companySearch;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[2]/ngx-select-dropdown/div/div/div/input")
    WebElement locationSearch;

    @FindBy(xpath="//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[3]/ngx-select-dropdown/div/div/div/input")
    WebElement serviceSearch;

    @FindBy(xpath ="//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[1]/ngx-select-dropdown/div/div/ul[2]/li")
    WebElement targetCompany;

    @FindBy(xpath= "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[2]/ngx-select-dropdown/div/div/ul[2]/li[1]")
    WebElement targetLocation;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[3]/ngx-select-dropdown/div/div/ul[2]/li")
    WebElement targetService;

    @FindBy(xpath="//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[4]/ngx-select-dropdown/div/div/ul[2]/li[1]")
    WebElement nonPo;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[4]/ngx-select-dropdown/div/div/ul[2]/li[2]")
    WebElement both;

    @FindBy(css ="span[class='badge badge-pill badge-dark p-2 ml-1 mt-1 ng-star-inserted'] i[class='fa fa-times']")
    WebElement delCompany;

    @FindBy(css ="span[class='badge badge-pill badge-dark p-2 ml-1 mt-1 ng-star-inserted'] i[class='fa fa-times']")
    WebElement delLocation;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[4]/div[1]/span[2]/i")
    WebElement delService;

    @FindBy(xpath="//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[4]/div[1]/span[2]/i")
    WebElement delInvoice;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/div/input")
    WebElement acknowledgeMemoInput;
    
    public CommercialDashboardPage() {
    	
        PageFactory.initElements(driver, this);
    }

    public boolean filterByCompany(String company_name){

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
//        removing automatically applied filters on Company
        filterBtn.click();
        applyBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        delCompany.click();
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

        WebElement result=TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]"));
        return result.isDisplayed();
    }

    public boolean filterByCompanyAndLocation(String company_name,String location_name){

        delService.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        delCompany.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));

        filterBtn.click();
        company.click();
        companySearch.sendKeys(company_name);
        targetCompany.click();
        acknowledgeMemoInput.click();
        location.click();
        locationSearch.sendKeys(location_name);
        targetLocation.click();
        applyBtn.click();
        TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[9]"), location_name);
    }
    
    public boolean filterByLocation(String location_name) {

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        // removing previously applied filters
        delLocation.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        delCompany.click();
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

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        // removing previously applied filters
        delLocation.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));

        acknowledgeMemoInput.click();
        filterBtn.click();
        applyBtn.click();
        TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        WebElement result=TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]"));
        return result.isDisplayed();
    }

	public boolean filterByNonPo() {

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
		filterBtn.click();
		poBtn.click();
		nonPo.click();
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        WebElement result=TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]"));
        return result.isDisplayed();
	}

    public boolean filterByBoth() {

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        filterBtn.click();
        poBtn.click();
        both.click();
        applyBtn.click();
        TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        WebElement result=TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]"));
        return result.isDisplayed();
    }

    public boolean filterByInvoiceServiceAndLocation(String invoice_number,String service_name,String location_name) {

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        filterBtn.click();
        invoice.sendKeys(invoice_number);
        service.click();
        serviceSearch.sendKeys(service_name);
        targetService.click();
        acknowledgeMemoInput.click();
        location.click();
        locationSearch.sendKeys(location_name);
        targetLocation.click();
        acknowledgeMemoInput.click();
        applyBtn.click();
        TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        WebElement result=TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]"));
        return result.isDisplayed();
    }

    public boolean filterByCompanyServiceAndMemo(String company_name,String service_name,String memo_number) {

        delLocation.click();
        TestUtils.waitForElementInvisibility(By.className("modal_container"));
        delService.click();
        TestUtils.waitForElementInvisibility(By.className("modal_container"));
        filterBtn.click();
        invoice.clear();
        applyBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal_container"));
        filterBtn.click();
        TestUtils.waitForElementInvisibility(By.className("modal_container"));
        company.click();
        companySearch.sendKeys(company_name);
        targetCompany.click();
        //		clicking outside to remove the selected dropdown which is covering other filter options
        acknowledgeMemoInput.click();
        service.click();
        serviceSearch.sendKeys(service_name);
        targetService.click();
        acknowledgeMemoInput.click();
        memo.sendKeys(memo_number);
        acknowledgeMemoInput.click();
        applyBtn.click();
        TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        WebElement result=TestUtils.waitForElementVisibility(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]"));
        return result.isDisplayed();
    }
}

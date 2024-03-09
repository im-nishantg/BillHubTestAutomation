package com.billhub.qa.pages;

import com.billhub.qa.base.TestBase;
import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.billhub.qa.base.TestBase.driver;

public class CommercialDashboardPage {

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

    @FindBy(css = ".form-control.ng-pristine.ng-valid.ng-touched")
    WebElement invoice;

    @FindBy(css= "input[formcontrolname='memo'][placeholder='Search..'][data-ms-editor='true']\")\n")
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
    WebElement non_Po;
    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[4]/ngx-select-dropdown/div/div/ul[2]/li[2]")
    WebElement both;

    @FindBy(css ="span[class='badge badge-pill badge-dark p-2 ml-1 mt-1 ng-star-inserted'] i[class='fa fa-times']")
    WebElement del_company;

    @FindBy(css ="span[class='badge badge-pill badge-dark p-2 ml-1 mt-1 ng-star-inserted'] i[class='fa fa-times']")
    WebElement del_location;

    @FindBy(css = "#main > main > div > div > app-dashboard > div > div.row.mb-1.ng-star-inserted > div.col-md-10.col-sm-10.mt-1 > span:nth-child(3) > i")
    WebElement del_service;

    @FindBy(css ="#main > main > div > div > app-dashboard > div > div.row.mb-1.ng-star-inserted > div.col-md-10.col-sm-10.mt-1 > span.badge.badge-pill.badge-dark.p-2.ml-1.mt-1.ng-star-inserted > i")
    WebElement del_memo;

    @FindBy(css="#main > main > div > div > app-dashboard > div > div.row.mb-1.ng-star-inserted > div.col-md-10.col-sm-10.mt-1 > span:nth-child(4) > i")
    WebElement del_invo;

    @FindBy(xpath = "//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[1]/div[2]/div/input")
    WebElement acknowledgeMemoBar;

    public CommercialDashboardPage() {
        PageFactory.initElements(driver, this);
    }
    public void initializePopupWebElements(){
        PageFactory.initElements(driver,this);
    }

    public boolean filterByCompany(String company_name){
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        filterBtn.click();
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
        TestUtils.waitForElementToBeClickable(By.className("fa-times"));
        del_location.click();

        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        filterBtn.click();
        applyBtn.click();
        TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), "1022");
    }

	public boolean filterByNonPo() {
//        TestUtils.waitForElementInvisibility(By.className("modal-container"));
		filterBtn.click();
		poBtn.click();
		non_Po.click();
//        acknowledgeMemoBar.click();
//        TestUtils.waitForElementToBeClickable(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[2]/div[2]/div/ul/li/form/div[12]/button"));
        TestUtils.waitForElementInvisibility(By.className("modal-container"));
		applyBtn.click();
		TestUtils.waitForElementVisibility(By.className("dashboard-table"));
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), "1022");
	}

    public boolean filterByBoth() {
        filterBtn.click();
        poBtn.click();
        both.click();
//        acknowledgeMemoBar.click();
        applyBtn.click();
        TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), "1022");
    }

    public boolean filterByCompanyServiceAndMemo(String company_name,String service_name,String memo_num) {
//        TestUtils.waitForElementInvisibility(By.className("modal-container"));
        del_location.click();
        TestUtils.waitForElementInvisibility(By.className("modal_container"));
        del_service.click();
        TestUtils.waitForElementInvisibility(By.className("modal_container"));
        del_invo.click();
        TestUtils.waitForElementInvisibility(By.className("modal_container"));
        filterBtn.click();
        company.click();
        companySearch.sendKeys(company_name);
        targetCompany.click();
//		clicking outside to remove the selected dropdown which is covering other filter options
        acknowledgeMemoBar.click();
        service.click();
        serviceSearch.sendKeys(service_name);
        targetService.click();
        acknowledgeMemoBar.click();
//        memo.click();
        memo.sendKeys(memo_num);
        applyBtn.click();
        TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), "1022");
    }

    public boolean filterByInvoiceServiceAndLocation(String invo_num,String service_name,String loc_name) {
        //		removing previously applied filters
//        del_memo.click();
//        TestUtils.waitForElementInvisibility(By.className("modal-container"));
//        del_service.click();
//        TestUtils.waitForElementInvisibility(By.className("modal-container"));
//        del_company.click();
//        TestUtils.waitForElementInvisibility(By.className("modal-container"));

        filterBtn.click();
        invoice.sendKeys(invo_num);
        service.click();
        serviceSearch.sendKeys(service_name);
        targetService.click();
        acknowledgeMemoBar.click();
        location.click();
        locationSearch.sendKeys(loc_name);
        targetLocation.click();
        acknowledgeMemoBar.click();
        applyBtn.click();
        TestUtils.waitForElementVisibility(By.className("dashboard-table"));

        return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-dashboard/div/div[5]/div/table/tbody/tr[1]/td[3]"), "1022");
    }
}

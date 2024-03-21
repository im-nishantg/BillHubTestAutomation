package com.billhub.qa.pages;

import com.billhub.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.billhub.qa.base.TestBase;
import org.openqa.selenium.support.ui.Select;

public class WithholdingTaxPage extends TestBase{
	
	@FindBy(xpath = "//button[@class='btn btn-secondary btn-verify-blue active']")
	WebElement addWithholdingTaxBtn;
<<<<<<< HEAD

	@FindBy(xpath = "//input[@id='taxRate']")
	WebElement withholdingTaxInput;

=======
	
	@FindBy(xpath = "//input[@id='taxRate']")
	WebElement withholdingTaxInput;
	
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement searchBtn;

	@FindBy(css = "input[formcontrolname='taxCode']")
	WebElement withTaxCode;
<<<<<<< HEAD

	@FindBy(css = "input[formcontrolname='taxType']")
	WebElement withTaxType;

	@FindBy(css = "div:nth-child(3) input:nth-child(1)")
	WebElement withTaxRate;

	@FindBy(css = "#msmed-id")
	WebElement withTaxDescrp;

	@FindBy(css = "button[class='btn btn-primary btn-done']")
	WebElement addBtn;

=======
	
	@FindBy(css = "input[formcontrolname='taxType']")
	WebElement withTaxType;
	
	@FindBy(css = "div:nth-child(3) input:nth-child(1)")
	WebElement withTaxRate;
	
	@FindBy(css = "#msmed-id")
	WebElement withTaxDescrp;
	
	@FindBy(css = "button[class='btn btn-primary btn-done']")
	WebElement addBtn;
	
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
	@FindBy(css = "button[class='btn btn-danger btn-done']")
	WebElement closeBtn;


	public WithholdingTaxPage() {
		PageFactory.initElements(driver, this);
	}
<<<<<<< HEAD
	public boolean searchWithholdingTaxByTaxRate(String tax_rate) {

		withholdingTaxInput.sendKeys(tax_rate);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-withholding-tax/div/div/div[3]/div/table/tbody/tr[1]/td[5]"), tax_rate);
	}

=======
	
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
	public void initalizePopupElements(){
		PageFactory.initElements(driver, this);
	}

	public boolean searchWithholdingTaxByTaxRate(String tax_rate) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		withholdingTaxInput.clear();
		withholdingTaxInput.sendKeys(tax_rate);
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		searchBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-withholding-tax/div/div/div[3]/div/table/tbody/tr[1]/td[5]"), tax_rate);
	}

	
	public void fillNewWithholdingTaxForm(String tax_type, String tax_code,String tax_rate,String tax_drop){
<<<<<<< HEAD

=======
		
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
		addWithholdingTaxBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		initalizePopupElements();
		withTaxType.sendKeys(tax_type);
		withTaxCode.sendKeys(tax_code);
		withTaxRate.sendKeys(tax_rate);
		Select selectVertical = new Select(withTaxDescrp);
		selectVertical.selectByVisibleText(tax_drop);

	}

	
	public boolean validateSearchWithholdingTax(String taxRate) {
<<<<<<< HEAD

=======
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
		withholdingTaxInput.clear();
		withholdingTaxInput.sendKeys(taxRate);
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		searchBtn.click();
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-withholding-tax/div/div/div[3]/div/table/tbody/tr[1]/td[5]"), taxRate);
	}

	public boolean validateAddTaxWithValidData(String taxType, String taxCode, String taxRate, String taxDescription) {
<<<<<<< HEAD

=======
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
		fillNewWithholdingTaxForm(taxType,taxCode,taxRate,taxDescription);
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		addBtn.click();
<<<<<<< HEAD
		return TestUtils.isSuccessToastDisplayed("Withholding tax added successfully");

=======
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("Withholding tax added successfully");
		if(isAdded == false)	closeBtn.click();
		return isAdded;
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
	}

	public boolean validateWithholdingTaxInDatabase(String taxRate) {
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		return searchWithholdingTaxByTaxRate(taxRate);
	}


	public boolean validateAddTaxWithBlankData(String taxType, String taxCode, String taxRate, String taxDescription) {
<<<<<<< HEAD

=======
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
		fillNewWithholdingTaxForm(taxType, taxCode, taxRate, taxDescription);
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		addBtn.click();
<<<<<<< HEAD
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Kindly fill out all the mandatory fields");

=======
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("Withholding tax added successfully");
		if(isAdded == false)	closeBtn.click();
		return isAdded;
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
	}


	public boolean validateDuplicateWithholdingData(String taxType, String taxCode, String taxRate, String taxDescription) {
<<<<<<< HEAD

=======
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
		fillNewWithholdingTaxForm(taxType,taxCode,taxRate,taxDescription);
		
		TestUtils.waitForElementInvisibility(By.className("modal-container"));
		addBtn.click();
<<<<<<< HEAD
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Withholding tax added successfully");

=======
		
		boolean isAdded = TestUtils.isSuccessToastDisplayed("Withholding tax added successfully");
		if(isAdded == false)	closeBtn.click();
		return isAdded;
>>>>>>> 27ed5e92058fe9b1ebea8f0cca71296156386a47
	}
}

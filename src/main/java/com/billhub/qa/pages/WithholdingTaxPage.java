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
	@FindBy(xpath = "//input[@id='taxRate']")
	WebElement withholdingTaxInput;
	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement searchBtn;
	public WithholdingTaxPage() {
		PageFactory.initElements(driver, this);
	}
	WebElement withTaxType,withTaxCode,withTaxRate,withTaxDescrp,addBtn,closeBtn;

	public boolean searchWithholdingTaxByTaxRate(String tax_rate) {

		withholdingTaxInput.sendKeys(tax_rate);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-withholding-tax/div/div/div[3]/div/table/tbody/tr[1]/td[5]"), tax_rate);
	}
	public void initalizePopupElements(){
		withTaxCode=driver.findElement(By.cssSelector("input[formcontrolname='taxCode']"));
		withTaxType=driver.findElement(By.cssSelector("input[formcontrolname='taxType']"));
		withTaxRate=driver.findElement(By.cssSelector("div:nth-child(3) input:nth-child(1)"));
		withTaxDescrp=driver.findElement(By.cssSelector("#msmed-id"));
		addBtn=driver.findElement(By.cssSelector("button[class='btn btn-primary btn-done']"));
		closeBtn=driver.findElement(By.cssSelector("button[class='btn btn-danger btn-done']"));
	}

	public void fillNewWithholdingTaxForm(String tax_type, String tax_code,String tax_rate,String tax_drop){
		addWithholdingTaxBtn.click();
		initalizePopupElements();
		withTaxType.sendKeys(tax_type);
		withTaxCode.sendKeys(tax_code);
		withTaxRate.sendKeys(tax_rate);
		Select selectVertical = new Select(withTaxDescrp);
		selectVertical.selectByVisibleText(tax_drop);
	}

	
	public boolean validateSearchWithholdingTax(String taxRate) {
		withholdingTaxInput.sendKeys(taxRate);
		searchBtn.click();
		return TestUtils.matchSearchedData(By.xpath("//*[@id=\"main\"]/main/div/div/app-list-withholding-tax/div/div/div[3]/div/table/tbody/tr[1]/td[5]"), taxRate);
	}

	public boolean validateAddTaxWithValidData(String taxType, String taxCode, String taxRate, String taxDescription) {
		fillNewWithholdingTaxForm(taxType,taxCode,taxRate,taxDescription);
		addBtn.click();
		return TestUtils.isSuccessToastDisplayed("Withholding tax added successfully");
	}

	public boolean validateWithholdingTaxInDatabase(String taxRate) {
		return searchWithholdingTaxByTaxRate(taxRate);
	}

	public boolean validateAddTaxWithBlankData(String taxType, String taxCode, String taxRate, String taxDescription) {
		fillNewWithholdingTaxForm(taxType, taxCode, taxRate, taxDescription);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Kindly fill out all the mandatory fields");
	}

	public boolean validateDuplicateWithholdingData(String taxType, String taxCode, String taxRate, String taxDescription) {
		fillNewWithholdingTaxForm(taxType,taxCode,taxRate,taxDescription);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Withholding tax added successfully");
	}
}

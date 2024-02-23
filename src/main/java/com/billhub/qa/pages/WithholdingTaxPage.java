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

	public void initalizePopupElements(){
		withTaxType=driver.findElement(By.cssSelector(".form-control.ng-pristine.ng-invalid.ng-touched"));
		withTaxCode=driver.findElement(By.cssSelector("input[formcontrolname='taxCode']"));
		withTaxRate=driver.findElement(By.cssSelector("div:nth-child(3) input:nth-child(1)"));
		withTaxDescrp=driver.findElement(By.id("msmed-id"));
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


	

	
//	public boolean validateSearchWithholdingTax(String taxType) {
//
//	}
//
	public boolean validateAddTaxWithValidData(String taxType, String taxCode, String taxRate, String taxDescription) {
		fillNewWithholdingTaxForm(taxType,taxCode,taxRate,taxDescription);
		addBtn.click();
		closeBtn.click();
		return TestUtils.isSuccessToastDisplayed("Withholding tax added successfully");
	}
//
//	public boolean validateWithholdingTaxInTable(String taxType, String taxCode, String taxRate, String taxDescription) {
//
//	}
//
//	public boolean validateAddTaxWithBlankData() {
//
//	}
//
//	public boolean validateDuplicateWithholdingData(String taxType, String taxCode, String taxRate, String taxDescription) {
//
//	}
}

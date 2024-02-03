package com.billhub.qa.pages;
import com.billhub.qa.base.TestBase;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MdmDashboardPage extends TestBase{
	
	@FindBy(xpath="//button[@class='btn btn-link']")
	WebElement navbarExpandBtn;
	
	@FindBy(xpath="//span[normalize-space()='Master']")
	WebElement masterBtn;
	
	@FindBy(xpath="//a[normalize-space()='Customer']")
	WebElement customerBtn;
	
	public MdmDashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public CustomerPage clickOnCustomerLink() throws InterruptedException {
		Thread.sleep(Duration.ofSeconds(4).toMillis());
		navbarExpandBtn.click();
		masterBtn.click();
		customerBtn.click();
		return new CustomerPage();
	}
	

}

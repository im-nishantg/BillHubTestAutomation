package com.billhub.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.billhub.qa.utils.TestUtil;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	public TestBase() {
		try {															// to load the props from config.properties
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\billhub\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();		//exception details are printed to the console using e.printStackTrace()
		}
	}
	
	public static void initialization() {
		
		String browserName = prop.getProperty("browser");
		 
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverFilePath"));
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		driver.get(prop.getProperty("url"));
	}
}

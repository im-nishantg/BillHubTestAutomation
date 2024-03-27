package com.billhub.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.billhub.qa.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger log;

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
		
		log = LogManager.getLogger(TestBase.class.getName());
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = WebDriverManager.chromedriver().create();
        } 
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = WebDriverManager.firefoxdriver().create();
        } 
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = WebDriverManager.edgedriver().create();
        } 
		else {
            throw new IllegalArgumentException("Invalid browser name provided: " + browserName);
        }

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.IMPLICIT_WAIT));
		driver.get(prop.getProperty("url"));
	}
}

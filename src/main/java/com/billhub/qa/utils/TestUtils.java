package com.billhub.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Duration;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.billhub.qa.base.TestBase;

public class TestUtils extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 10;
	public static long EXPLICIT_WAIT = 10;
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\billhub\\qa\\testdata\\BillHubTestdata.xlsx";		
	static Workbook book;
	static Sheet sheet;
	
	public static WebElement waitForElementVisibility(By selector) {
		
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }
	
	public static boolean isSuccessToastDisplayed(String message) {
		
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='" + message + "']")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
	
	public static WebElement locateAndClickEditBtn(By selector) {
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    try {
	        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(selector));
	        editBtn.click();
	        return editBtn;
	    } catch (StaleElementReferenceException e) {
	        WebElement refreshedEditBtn = driver.findElement(selector);
	        refreshedEditBtn.click();
	        return refreshedEditBtn;
	    }
	}
	
	public static String numberToString(Object data) {
		
		BigDecimal number = new BigDecimal(data.toString());
        number = number.setScale(0, RoundingMode.DOWN); // Removes the decimal part
        return number.toString();
	}
	
	public static boolean isTableDisplayed(By selector) {
		
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
	
	public static Object[][] getTestData(String sheetName) {
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				Cell cell = sheet.getRow(i + 1).getCell(k);
	            data[i][k] = (cell != null) ? cell.toString() : ""; // Check for null before invoking toString()
//				System.out.println(i + " " + k + " " + data[i][k]);
			}
		}
		return data;
	}
	
}

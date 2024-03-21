package com.billhub.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.billhub.qa.base.TestBase;

public class TestUtils extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 15;
	public static long EXPLICIT_WAIT = 10;
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\billhub\\qa\\testdata\\BillHubTestdata.xlsx";		
	static Workbook book;
	static Sheet sheet;
	
	public static WebElement waitForElementVisibility(By selector) {
		
		log.info("Waiting for element visibility: " + selector);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }
	
	public static void waitForElementInvisibility(By selector) {
		
		log.info("Waiting for element invisibility: " + selector);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }
	
	public static WebElement waitForElementToBeClickable(By selector) {
		
		log.info("Waiting for element to be clickable: " + selector);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(selector));
	}
	
	public static WebElement waitForWebElementToBeClickable(WebElement element) {
		
		log.info("Waiting for web element to be clickable: " + element);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static boolean isElementVisible(WebElement element) {
        
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
	}
	 
	public static boolean isSuccessToastDisplayed(String message) {
		
		log.info("Checking if success toast is displayed for message: " + message);
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='" + message + "']")));
            return true;
        } catch (TimeoutException e) {
        	 log.error("TimeoutException: Success toast not displayed for message - " + message);
            return false;
        }
    }
	
	public static boolean waitForToastToDisappear() {
		
		log.info("Waiting for toast to disappear");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"toast-container\"]")));
            return true;
        } catch (TimeoutException e) {
        	log.error("TimeoutException: Toast did not disappear");
            return false;
        }
    }
	
	public static WebElement locateAndClickEditBtn(By selector) {
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
	
	public static boolean matchSearchedData(By selector, String expectedValue) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	    // Custom expected condition to wait until value in the table is equal to searched value
	    ExpectedCondition<Boolean> condition = driver -> {
	        WebElement userName = driver.findElement(selector);
	        return userName.getText().equals(expectedValue);
	    };
	    
	    try {
	        wait.until(condition);
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
	
	public static String generateRandomNumber(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero");
        }

        // Generate a random number with the specified length
        StringBuilder randomNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);  // Generate a random digit (0-9)
            randomNumber.append(digit);
        }
        
        if (randomNumber.toString().equals("0")) {
            // If the generated number is "0", set it to "1"
            randomNumber.setLength(0);
            randomNumber.append("1");
        }
        
        return randomNumber.toString();
    }
	
	public static String generateRandomString(int length) {
		 
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero");
        }

        // Generate a random string with the specified length
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = (char) ('a' + random.nextInt(26));  // Generate a random lowercase English character
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

	public static String splitString(String str){
		String final_str=str.substring(2);
		return final_str;
	}
	 
	public static void setCellData(String sheetName, int rowNum, int colNum, String data) {
		   
		 try {
		        
			FileInputStream file = new FileInputStream(TESTDATA_SHEET_PATH);

	        try (XSSFWorkbook xsf = new XSSFWorkbook(file)) {
	            XSSFSheet sheet = xsf.getSheet(sheetName);

	            if (sheet != null) {
	                sheet.getRow(rowNum).getCell(colNum).setCellValue(data);

	                try (FileOutputStream fos = new FileOutputStream(TESTDATA_SHEET_PATH)) {
	                    xsf.write(fos);
	                }
	            } else {
	            	log.error("Sheet not found: " + sheetName);
	            }
	        }

	    } catch (FileNotFoundException e) {
	    	log.error("FileNotFoundException: " + e.getMessage());
	        e.printStackTrace();
	    } catch (IOException e) {
	    	log.error("IOException: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	public static String takeScreenshot(String testName) {
			
			File sourceScreenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destinationScreenshotFile = new File(System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png");
			try {
				FileUtils.copyFile(sourceScreenshotFile, destinationScreenshotFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return destinationScreenshotFile.getAbsolutePath();
		
	}
	
	public static Object[][] readExcelSheetByFilePath(String filePath, String sheetName) {
		
		FileInputStream file = null;
		Workbook book = null;
		Sheet sheet = null;
		
		try {
			file = new FileInputStream(filePath);
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
	
	public static void updateExcelSheetByFilePath(String filePath, String sheetName, int rowNum, int colNum, String data) {
		

		try {
	        
			FileInputStream file = new FileInputStream(filePath);

	        try (XSSFWorkbook xsf = new XSSFWorkbook(file)) {
	            XSSFSheet sheet = xsf.getSheet(sheetName);

	            if (sheet != null) {
	                sheet.getRow(rowNum).getCell(colNum).setCellValue(data);

	                try (FileOutputStream fos = new FileOutputStream(filePath)) {
	                    xsf.write(fos);
	                }
	            } else {
	            	log.error("Sheet not found: " + sheetName);
	            }
	        }

	    } catch (FileNotFoundException e) {
	    	log.error("FileNotFoundException: " + e.getMessage());
	        e.printStackTrace();
	    } catch (IOException e) {
	    	log.error("IOException: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
}

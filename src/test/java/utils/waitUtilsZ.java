package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.Duration;
import utils.WaitUtils;

public class waitUtilsZ {
	 private WebDriver driver;

	    // Constructor to initialize WebDriver
	    public waitUtilsZ(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Common wait method for visibility or clickability of elements
	    public void waitForElement(WebElement element, int timeoutInSeconds, WaitType waitType) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        
	        switch (waitType) {
	            case VISIBILITY:
	                wait.until(ExpectedConditions.visibilityOf(element));
	                break;
	            case CLICKABLE:
	                wait.until(ExpectedConditions.elementToBeClickable(element));
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid wait type: " + waitType);
	        }
	    }

	    // Enum to specify the type of wait
	    public enum WaitType {
	        VISIBILITY,
	        CLICKABLE
	    }
}

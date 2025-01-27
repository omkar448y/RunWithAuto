package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CommonUtils {
	WebDriver driver;
	WebDriverWait wait;

	// Constructor
	public CommonUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Set timeout for waits
	}

	// Method to scroll to an element and click using JavaScriptExecutor with
	public void scrollAndClick(WebElement element) {
		try {
			// Wait for the element to be visible and clickable
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			// Scroll the element into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			// Wait a little after scrolling (in case rendering takes time)
			Thread.sleep(1000);

			// Click the element using JavaScriptExecutor
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

			System.out.println("Clicked on the element successfully.");

		} catch (StaleElementReferenceException e) {
			// Handle stale element case, retrying the click if necessary
			System.out.println("StaleElementReferenceException occurred. Retrying the click...");
			retryClick(element);

		} catch (Exception e) {
			// Log any other exceptions
			System.out.println("Exception occurred while scrolling and clicking: " + e.getMessage());
		}
	}

	// Retry clicking the element 
	private void retryClick(WebElement element) {
		try {
			// Wait for the element to be visible and clickable again
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			// Scroll and click again
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

			System.out.println("Retried and clicked the element successfully.");
		} catch (Exception e) {
			// Log if retry fails as well
			System.out.println("Retrying failed. Exception: " + e.getMessage());
		}
	}
	
	
	// Method to scroll to an element and click using JavaScriptExecutor 
	public void ClickByView(WebElement element) {
	    int retryCount = 0; // Counter to track retry attempts
	    boolean success = false;

	    while (retryCount < 2 && !success) { // Retry up to 2 times
	        try {
	            // Wait for the element to be visible and clickable
	            wait.until(ExpectedConditions.visibilityOf(element));
	            wait.until(ExpectedConditions.elementToBeClickable(element));

	            // Scroll the element into view
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

	            // Wait a little after scrolling (in case rendering takes time)
	            Thread.sleep(1000);

	            // Click the element using JavaScriptExecutor
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

	            System.out.println("Clicked on the element successfully.");
	            success = true; // Mark as successful to exit the loop

	        } catch (StaleElementReferenceException e) {
	            // Handle stale element case
	            retryCount++;
	            System.out.println("StaleElementReferenceException occurred. Retrying... Attempt: " + retryCount);

	        } catch (Exception e) {
	            // Log any other exceptions and stop retrying
	            System.out.println("Exception occurred while scrolling and clicking: " + e.getMessage());
	            break; // Exit loop if it's an unexpected exception
	        }
	    }

	    if (!success) {
	        System.out.println("Failed to click on the element after retries.");
	    }
	}

	
	
	
	// Method to click an element twice with a 1-second interval
		public void doubleClickWithInterval(WebElement element) {
			try {
				// Wait for the element to be visible and clickable
				wait.until(ExpectedConditions.visibilityOf(element));
				wait.until(ExpectedConditions.elementToBeClickable(element));

				// First click
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("First click successful.");

				// Wait for 1 second
				Thread.sleep(2000);

				// Second click
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("Second click successful.");

			} catch (Exception e) {
				System.out.println("Exception occurred while performing double click: " + e.getMessage());
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class WaitUtils {
	 WebDriver driver;

	    // Constructor to initialize WebDriver
	    public WaitUtils(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Common wait method for visibility of elements
	    public void waitForElementVisibility(WebElement element, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.visibilityOf(element));
	    }

	    // Common wait method for element to be clickable
	    public void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	    }

}

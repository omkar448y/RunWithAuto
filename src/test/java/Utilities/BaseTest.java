package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class BaseTest {
	 protected WebDriver driver;

	    public WebDriver getDriver() {
	        return driver;
	    }

	    // WebDriver initialization (could be done in a Before hook or setup method)
	    public void setupDriver() {
	        // Assuming using ChromeDriver
	        driver = new ChromeDriver();
	    }

	    // Teardown method for closing the driver after tests
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}

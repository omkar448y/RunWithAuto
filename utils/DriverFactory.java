package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class DriverFactory {
	  private static WebDriver driver;

	    // Method to initialize and get WebDriver instance
	    public static WebDriver getDriver() {
	        if (driver == null) {
	            // Choose the browser you want to use (e.g., Chrome, Firefox)
WebDriverManager.chromedriver().setup();
driver = new ChromeDriver(); // You can change this to any other browser like FirefoxDriver
	            driver.manage().window().maximize();
	        }
	        return driver;
	    }

	    // Method to quit and clean up WebDriver
	    public static void quitDriver() {
	        if (driver != null) {
	            driver.quit();
	            driver = null;
	        }
	    }
}

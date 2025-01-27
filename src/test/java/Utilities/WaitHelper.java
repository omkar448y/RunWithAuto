package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.*;
import StepDefination.BaseClass;
import org.openqa.selenium.JavascriptExecutor;


public class WaitHelper extends  BaseClass
{

	public static WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

 
    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Wait for a maximum of 10 seconds
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static WebElement waitForpresenceOfElementLocated(WebDriver driver, WebElement clickonAccept) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Wait for a maximum of 10 seconds
        return wait.until(ExpectedConditions.presenceOfElementLocated((By) clickonAccept));
        
    }
    
    public static void performMoveToElementAction(WebDriver driver, WebElement locator,int enterWaitTimeInMilliSeconds){
        Actions actions = new Actions(driver);
        Duration time = Duration.ofMillis(enterWaitTimeInMilliSeconds);
        //Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, time);
        WebElement element =wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            actions.moveToElement(element).build().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void clickUsingJavaScript(WebElement EnterElementName) throws InterruptedException {
        try {
            waitForElementToBeClickable(driver,EnterElementName);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", EnterElementName);
            js.executeScript("arguments[0].click();", EnterElementName);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    
    
    
    
	
}

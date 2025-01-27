package StepDefination;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import PageObject.Shine;
import Utilities.WaitHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ElementNotInteractableException;

public class Steps extends BaseClass {
	public WebDriver rdriver;

	public Shine shine;

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized"); // Start browser maximized
		options.addArguments("--disable-notifications"); // Disable notifications
		options.addArguments("--incognito"); // Open in incognito mode
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		options.addArguments("--no-sandbox"); // Bypass OS security model (use cautiously)
		options.addArguments("--disable-dev-shm-usage"); // Overcomes limited resource problems in some environments
		options.addArguments("--remote-allow-origins=*"); // Addresses some CORS issues

		driver = new ChromeDriver(options);

	}

	// Shine----------------------------------------------------------------------

	Properties prop = new Properties();

	public Steps() {
		try {
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\oyadavx\\eclipse-workspace\\RunWithAuto\\src\\test\\java\\config.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@When("User open URL Web Shine")
	public void user_open_urlwebShine() {

		String url = prop.getProperty("shineweb");
		driver.get(url);
	}

	@When("User open URL Web Naukari")
	public void user_open_urlwebN() {
		String url = prop.getProperty("Naukari");
		driver.get(url);
	}

	@When("Click On Log In Button")
	public void user_login() {
		shine = new Shine(driver);
		shine.ClickOnLogin();

	}

	@When("Enter Email To {string}")
	public void EnterSoldemail(String string) throws InterruptedException {
		shine.EnterEmailID(string);
	}

	@When("Enter Pass To {string}")
	public void user_EnterPass(String string) {
		shine.EnterPassID(string);
		shine.ClickLogin();
	}

	@When("Click On view All")
	public void user_loginall() {
		shine.Clickviewall();
	}

	@When("click Apply")
	public void user_loginallapply() throws InterruptedException {
		shine.elementsApply();
	}

	@When("Enter Text globale search and click")
	public void user_EnterPassall() throws InterruptedException {

		shine = new Shine(driver);
		shine.Clickglobalsearch(driver);
		Thread.sleep(5000);
		shine.Clicksearchglobal();
	}

	@When("Click On login")
	public void user_loginallClicklogin() {
		shine = new Shine(driver);
		shine.Clickonloginnaukari();
	}

	@When("Enter Email {string}")
	public void user_EnterPassemail(String string) {
		shine.EnterEmailNaukari(string);
	}

	@When("Enter Pass {string}")
	public void user_EnterPassemailpass(String string) {
		shine.EnterPassNaukari(string);
		shine.ClickOnloginnaukarsi();
		shine.clickphoto();
		shine.ClickView();

	}

	@When("Loops the best")
	public void loop() throws InterruptedException {
		shine = new Shine(driver);
		shine.continuouslyEditAndSave(100000);
	}

	@Then("Appload")
	public void BulkUploadfileContactULIOTSAndCPN() throws InterruptedException, AWTException {

		shine.uploadFileMultipleTimes(1000);
	}

}

package PageObject;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.WaitHelper;
import utils.CommonUtils;
import utils.WaitUtils;
import utils.waitUtilsZ;

public class Shine {
	
	public WebDriver ldriver;
	public WebDriver driver;
	public WaitHelper waitHelper;
	public WaitUtils waitUtils;
	public waitUtilsZ waitUtilsZ;
	public CommonUtils commonclick;

	    // private WebDriver rdriver;
	public Shine(WebDriver rdriver) {

		// Assign the passed WebDriver instance to the class variable
		ldriver = rdriver;
		// Initialize all PageFactory elements on this page using the WebDriver instance
		PageFactory.initElements(rdriver, this);
		waitUtils = new WaitUtils(ldriver); // Initialize wait utility
		waitHelper = new WaitHelper(ldriver); // Initialize WaitHelper
		waitUtilsZ = new waitUtilsZ(ldriver);
		commonclick = new CommonUtils(ldriver);

	}

	/*
	 * Author: Omkar Yadav Change:Admin Case Creation Description:
	 */
	@FindBy(xpath = "//button[text()=\"Login\"]")
	WebElement ClickOnLogin;
	@FindBy(xpath = "//input[@id=\"id_email_login\"]")
	WebElement EnterEmailID;
	@FindBy(xpath = "//input[@id=\"id_password\"]")
	WebElement EnterPassID;
	@FindBy(xpath = "(//button[text()=\"Login\"])[3]")
	WebElement ClickLogin;
	
	
	@FindBy(xpath = "//a[text()=\"View all\"]")
	WebElement Clickviewall;
	
	
	
	@FindBy(xpath = "//button[text()='Apply']")
    private List<WebElement> elementsApply;
	@FindBy(xpath = "//button[text()=\"Load More\"]")
    private List<WebElement> loadMoreButton;
	
	@FindBy(xpath = "//span[text()='Job title, skills']")
	WebElement Clickglobalsearch;
	@FindBy(xpath = "//input[@id=\"id_new_search_submit_button\"]")
	WebElement Clicksearchglobal;
	
	@FindBy(xpath = "//a[@title='Jobseeker Login']")
	WebElement Clickonloginnaukari;
	
	@FindBy(xpath = "//input[@placeholder='Enter your active Email ID / Username']")
	WebElement EnterEmailNaukari;
	
	
	@FindBy(xpath = "//input[@placeholder='Enter your password']")
	WebElement EnterPassNaukari;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement ClickOnloginnaukarsi;
	@FindBy(xpath = "//img[@alt='naukri user profile img']")
	WebElement clickphoto;
	
	@FindBy(xpath = "//a[normalize-space()='View & Update Profile']")
	WebElement ClickView;
	
	@FindBy(xpath = "//em[@data-ga-track='spa-event|EditProfile|Basic Details|EditOpen']")
	WebElement ClickEdit;
	
	
	@FindBy(xpath = "//input[@placeholder='Enter Your Name']")
	WebElement EnterSpaceonly;
	
	
	@FindBy(xpath = "//div[normalize-space()='Supported Formats: doc, docx, rtf, pdf, upto 2 MB']//preceding::input[@value='Update resume']")
	WebElement Upload;

	public void uploadFileMultipleTimes(int numberOfUploads) {
	    for (int i = 1; i <= numberOfUploads; i++) {
	        try {
	            // Step 1: Wait and Click Upload Button
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.elementToBeClickable(Upload));
	            Upload.click();
	            System.out.println("Clicked on Upload button - Iteration " + i);
	            
	            // Step 2: Perform File Upload Using Robot
	            Thread.sleep(2000); // Wait for the upload dialog to appear
	            Robot upload = new Robot();
	            upload.delay(3000);

	            StringSelection stringSelection = new StringSelection(
	                    "C:\\Users\\oyadavx\\Downloads\\Om_Automation_5YOE.pdf");
	            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	            // Press CTRL+V to paste the file path
	            upload.keyPress(KeyEvent.VK_CONTROL);
	            upload.keyPress(KeyEvent.VK_V);
	            upload.delay(2000);
	            upload.keyRelease(KeyEvent.VK_CONTROL);
	            upload.keyRelease(KeyEvent.VK_V);

	            // Press ENTER to confirm the upload
	            upload.delay(2000);
	            upload.keyPress(KeyEvent.VK_ENTER);
	            upload.keyRelease(KeyEvent.VK_ENTER);
	            System.out.println("File uploaded successfully - Iteration " + i);

	            // Optional Delay Between Iterations
	            Thread.sleep(20000);

	        } catch (Exception e) {
	            System.out.println("Error during iteration " + i + ": " + e.getMessage());
	        }
	    }
	}

	
	
	
	public void continuouslyEditAndSave(int n) throws InterruptedException {
	    try {
	        // Wait for 30 seconds before starting the loop
	        Thread.sleep(1000); // 30 seconds
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    for (int i = 0; i < n; i++) {
	        // Click the Edit button
	    	
	        Thread.sleep(20000); // 30 seconds

	        commonclick.scrollAndClick(ClickEdit);
	        
	        // Enter a space in the input field
	       // EnterSpaceonly.clear();  // Optional: Clear any existing text in the field
	        EnterSpaceonly.sendKeys(" ");  // Send a space character
	        
	        // Click the Save button
	        commonclick.scrollAndClick(Clickonsave);
	        
	        // Optional: Add a wait time if necessary to ensure the page has time to process the actions
	        try {
	            Thread.sleep(1000); // Just an example of a 1-second delay between actions
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	
	
	
	
	@FindBy(xpath = "//button[@type='button']")
	WebElement Clickonsave;
	public void Clickonsave() {
	    commonclick.scrollAndClick(Clickonsave); 
	}
	
	
	
	public void EnterSpaceonly() {
	    commonclick.scrollAndClick(EnterSpaceonly); 
	    EnterSpaceonly.sendKeys(" "); // This will send a single space character to the input field
	}
	
	
	
	public void ClickEdit() 
	{
	commonclick.scrollAndClick(ClickEdit);	
		
	}
	
	
	
	public void ClickView() 
	{
	commonclick.scrollAndClick(ClickView);	
		
	}
	
	public void clickphoto() 
	{
	commonclick.scrollAndClick(clickphoto);	
		
	}
	
	
	
	
	
	
	
	public void ClickOnloginnaukarsi() 
	{
	commonclick.scrollAndClick(ClickOnloginnaukarsi);	
		
	}
	
	//button[normalize-space()='Login']
	
	
	public void EnterPassNaukari(String pass) 
	{
	commonclick.scrollAndClick(EnterPassNaukari);	
	EnterPassNaukari.sendKeys(pass);
	
		
	}
	
	
	public void EnterEmailNaukari(String emailid) 
	{
	commonclick.scrollAndClick(EnterEmailNaukari);	
	EnterEmailNaukari.sendKeys(emailid);
	
		
	}
	
	
	
	public void Clickonloginnaukari() 
	{
	commonclick.scrollAndClick(Clickonloginnaukari);	
		
	}
	
	

	public void Clicksearchglobal() 
	{
	commonclick.scrollAndClick(Clicksearchglobal);	
		
	}
//	public void Clickglobalsearch() throws InterruptedException {
//	    // Scroll to the element and click it
//	    commonclick.scrollAndClick(Clickglobalsearch);
//	    Thread.sleep(2000); // Consider using WebDriverWait instead of Thread.sleep for better stability
//
//	    try {
//	        // Attempt to clear and input text directly
//	        Clickglobalsearch.clear();
//	        Clickglobalsearch.sendKeys("Devops Engineer");
//	    } catch (Exception e) {
//	        System.out.println("sendKeys failed, trying with JavaScript Executor...");
//	        
//	        // Ensure driver is properly initialized
//	        if (driver != null) {
//	            try {
//	                JavascriptExecutor js = (JavascriptExecutor) driver;
//	                js.executeScript("arguments[0].value='Devops Engineer';", Clickglobalsearch);
//	            } catch (Exception jsException) {
//	                System.out.println("JavaScript Executor also failed: " + jsException.getMessage());
//	            }
//	        } else {
//	            System.out.println("Driver is not initialized. Cannot perform JavaScript execution.");
//	        }
//	    }
//	}
	public void Clickglobalsearch(WebDriver driver) {
 commonclick.scrollAndClick(Clickglobalsearch);
 
 //Clickglobalsearch.sendKeys("DevOps");
	    
	        // Scroll to the element and use JavaScript to set its value
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].value='Devops Engineer';", Clickglobalsearch);
}
	
	
	
	
   // List<WebElement> elementsapply = driver.findElements(By.xpath("//button[text()='Apply']"));

//    public void elementsApply() 
//	{
//    	 // Iterate over each element and click it
//        for (WebElement element : elementsApply) {
//          //  element.click();
//        	commonclick.scrollAndClick(element);	
//
//            // Optional: Add a short delay to avoid rapid actions
//            try {
//                Thread.sleep(1000); // 1 second delay
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    	
//	//commonclick.scrollAndClick(Clickviewall);	
//		
//	}
//	
    public void elementsApply() throws InterruptedException {

        while (true) {
            //Thread.sleep(2000); // 2 seconds delay

            // Iterate over each element and click it
            for (WebElement element : elementsApply) {
            	//element.click();
               // Thread.sleep(2000); // 2 seconds delay

              commonclick.scrollAndClick(element);

                // Optional: Add a short delay to avoid rapid actions
                try {
                    Thread.sleep(1000); // 1 second delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Check if "Load More" button exists and is visible
           // List<WebElement> loadMoreButton = driver.findElements(By.xpath("//button[text()='Load More']"));
            if (!loadMoreButton.isEmpty() && loadMoreButton.get(0).isDisplayed()) {
                commonclick.scrollAndClick(loadMoreButton.get(0)); // Scroll and click "Load More"

                // Wait for new elements to load
                try {
                    Thread.sleep(2000); // 2 seconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break; // Exit the loop if "Load More" button is not present or visible
            }
        }
    }

    
    
    
	
	
	public void Clickviewall() 
	{
	commonclick.scrollAndClick(Clickviewall);	
		
	}
	
	
	public void ClickOnLogin() 
	{
		
	commonclick.scrollAndClick(ClickOnLogin);	
		
	}
	
	public void EnterEmailID(String email) 
	{
	commonclick.scrollAndClick(EnterEmailID);	
	EnterEmailID.sendKeys(email);
	
		
	}
	
	public void EnterPassID(String pass) 
	{
	commonclick.scrollAndClick(EnterPassID);	
	EnterPassID.sendKeys(pass);
	
		
	}
	
	public void ClickLogin() 
	{
	commonclick.scrollAndClick(ClickLogin);	
		
	}
	
	
}

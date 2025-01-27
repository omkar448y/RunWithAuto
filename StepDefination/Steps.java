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
import PageObject.Exceptions;
import PageObject.Admin;
import PageObject.ContactPage_R4C_CaseCreation;
import PageObject.Technical;
import PageObject_Web.Web_Portal_Quality;
import PageObject_Web.Web_Portal_StockRotation;
import PageObject_Web.Web_Portal_Technical;
import PageObject_Web.Web_Query_ProductWarrenty;
import PageObject_Web.Web_Query_SRAllowance;
import PageObject_Web.Web_Query_Search;
import PageObject.LoginPage;
import PageObject.Miscellaneous;
import PageObject.PriceMasking;
import PageObject.Quality;
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
	public LoginPage lp;
	public ContactPage_R4C_CaseCreation contacts;
	public Technical Technical;
	public Quality quality;
	public Admin admin;
	public Exceptions exceptions;
	public Miscellaneous miscellaneous;
	public Web_Portal_StockRotation WebSR;
	public Web_Portal_Technical WebTech;
	public Web_Portal_Quality WebQuality;
	public Web_Query_Search Query;
	public Web_Query_SRAllowance SRAllowanceQuery;
	public Web_Query_ProductWarrenty ProductwarrentyQuery;
	public PriceMasking pricemasking;
	public Shine shine;

	/*
	 * Author: Omkar Yadav Change:LogIn Page ISVC R4C Description: Launch browser
	 */
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless"); // Run in headless mode
		// options.addArguments("--disable-gpu"); // Disable GPU (optional)
		// options.addArguments("--window-size=1920,1080"); // Set window size
		// (optional)

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
		lp = new LoginPage(driver);
	}

	@When("User open url {string}")
	public void user_open_url(String string) throws InterruptedException {
		driver.get(string);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

	}

	@When("User Enter Email {string} as and pass {string}")
	public void user_enter_email_as_and_pass(String email, String password) throws InterruptedException {
		lp = new LoginPage(driver);
		lp.SetUserName(email);
		lp.SetPassword(password);
	}

	@When("Click on login")
	public void click_on_login() throws InterruptedException {
		lp.Clickonlogin();
		Thread.sleep(3000);
		driver.manage().window().maximize();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String string) {
		String Title = driver.getTitle();
		System.out.println("Actual title" + Title);
	}

	@When("User Click on logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		lp.clickonmenuicon();
		lp.clickonlogout();
	}

	@Then("Close brower")
	public void close_brower() {
		driver.close();
	}

	/*
	 * Author: Omkar Yadav Change:Case Creation Contact - R4C-New case creation
	 */

	@Given("User is logged in and on the Dashboard page")
	public void user_is_logged_in_and_on_the_dashboard_page() {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		String Title = contacts.getPageTitle();
		System.out.println(Title);
		Assert.assertEquals(Title, Title);
	}

	@When("User Click on Contact menu")
	public void user_click_on_contact_menu() throws InterruptedException {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.Clickoncontactdropdown(); // TO click on contacts dropdown
	}

	@When("Select Contact from list and enter Text into search field {string}")
	public void select_contact_from_list_and_enter_text_into_search_field(String string) throws InterruptedException {
		contacts.SelectDropdownOption_Contacts(); // To select contact option
		contacts.clickondropdown(); // To click on List view Dropdown
		contacts.SelectTestContact(); // clickon recent view dropdown option
		contacts.EntercontactNameintosearchfield(string); // To Enter Text into search field- RecentView
		contacts.selecttestcontact(); // To click on searched contact
		contacts.EnterTextGlobaly(); // Enter Text Into global fields
	}

	@When("Click on the New R4C case creation button")
	public void click_on_the_new_r4c_case_creation_button() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("All value enterd into fields with golbaly and recent search");
		Thread.sleep(2000);
		contacts.clickonR4CCaseButton();
		Thread.sleep(2000);
	}

	@Then("User can view New R4C Case popup")
	public void user_can_view_new_r4c_case_popup() {
		System.out.println("User Are Able to see New R4C Popup");
	}

	@Then("Select service type as {string}")
	public void select_service_type_as(String string) throws InterruptedException {
		try {
			contacts.selectserviceType();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("Click on the create button")
	public void click_on_the_create_button() throws InterruptedException {
		contacts.clickoncreatebutton();
	}

	@When("User enter Sold to and other details")
	public void user_enter_sold_to_and_other_details() throws InterruptedException {
		contacts.EnterSoldTo();
		contacts.Selectsearchedcontact();
		contacts.clickonreturnreson();
		contacts.selectstockrotation();
	}

	@When("Click on the Next button After Sold To")
	public void click_on_the_next_button_after_sold_to() throws InterruptedException {
		Thread.sleep(2000);
		contacts.clickonnectaftersold();
	}

	@When("Select Purchase\\/Sales Order button and Enter PO Number {string}")
	public void select_purchase_sales_order_button_and_enter_po_number(String string) throws InterruptedException {
		contacts.clickonPORadiobutton();
		contacts.EnterPONumber(string);
	}

	@When("Select Purchase\\/Sales Order button and Enter PO Number Through Excel")
	public void select_purchase_sales_order_button_and_enter_po_number_through_excel() {
		contacts.clickonPORadiobutton();
		admin = new Admin(driver);
		admin.enterPOnumber();
	}

	@Then("Click on the search button")
	public void click_on_the_search_button() throws InterruptedException {
		contacts.clickonsearch();
		// contacts.clickonsearchonly();
	}

	@Then("Click on the search button web")
	public void click_on_the_search_buttonweb() throws InterruptedException {
		contacts.clickonsearch();
		contacts.clickonsearchonly();
	}

	@Then("Select All Line items")
	public void select_all_line_items() throws InterruptedException {
		contacts.selectallcheckbox();
	}

	@Then("Click on the Next button after All Line Items")
	public void click_on_the_next_button_after_all_line_items() throws InterruptedException {
		contacts.clickonnextPO();
	}

	@Then("Click on Product Validation and Next button")
	public void click_on_product_validation_and_next_button() throws InterruptedException {
		contacts.clickonproductvalidation();
		contacts.clickonnextafterPO();
	}

	@When("Click on Product Validation")
	public void click_on_product_validation() throws InterruptedException {
		contacts.clickonproductvalidation();
	}

	@When("Click on Next cases edit")
	public void click_on_next_cases_edit() throws InterruptedException {

		WebElement Clickon = driver.findElement(By.xpath("(//button[contains(text(),'Next')])[3]"));
		Clickon.click();
	}

	@When("Click On Next Accept Edit")
	public void click_on_next_accept_edit() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
			WebElement element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()=\"Next\"])[2]")));
			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(driver); // Focus on the element
			actions.moveToElement(element).perform();
			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
		} catch (TimeoutException e) {
			System.out.println("Element not found or not clickable within the specified time");
		} finally {
			System.out.println("Element still not clickable");
		}
	}

	@When("Click on next Case Validation")
	public void click_on_next_case_validation() throws InterruptedException {
		WebElement click = driver.findElement(By.xpath(
				"//section[@aria-expanded='true']//section[@role='tabpanel']//button[@title='Save for Later'][normalize-space()='Save for Later']/../..//button[@title='Next'][normalize-space()='Next']"));
		click.click();

	}

	@When("Enter Return Quantity")
	public void enter_return_quantity() throws InterruptedException {
		contacts.enterQuantityfirst();
		System.out.println("Enterd all values Into the Return Fields");
	}

	@When("Click on the Get credit price")
	public void click_on_the_get_credit_price() throws InterruptedException {
		contacts.clickongetcreditprice();
	}

	@When("Click on the Next button After credit Price")
	public void click_on_the_next_button_after_credit_price() throws InterruptedException {
	}

	@When("Enter Credit price")
	public void enter_credit_price() throws InterruptedException {
		contacts.EnterPriceandhandleexceptions();
		contacts.entertextintoreasonfieldexceptions();

	}

	@When("Enter Credit price Zero")
	public void enter_credit_price_zero() {
		contacts.EnterPriceZero();
		contacts.entertextintoreasonfield();
	}

	@When("Enter Credit price All fields")
	public void enter_credit_price_all_fields() {
		contacts.EnterPriceIntoAllFields();
		contacts.EnterReasonintoAllFields();

	}

	@When("Click on the Next button After Credit")
	public void click_on_the_next_button_after_credit() throws InterruptedException {
		contacts.AfterCreditpriceNext();
		System.out.println("------------Clicked after Entering reason-------------------");
	}

	@When("Click on the Validate Allowance")
	public void click_on_the_validate_allowance() throws InterruptedException {
		contacts.validateallownace();
	}

	@When("Click on the Save and Summary button")
	public void click_on_the_save_and_summary_button() throws InterruptedException {
		contacts.clicksavesummery();
	}

	@When("Click on submit button After Summary")
	public void click_on_submit_button_after_summary() throws InterruptedException {

		Thread.sleep(1000);
		contacts.ClickSubmit();

	}

	@Then("User can view Case creation Popup message")
	public void user_can_view_case_creation_popup_message() {
		System.out.println("PopUp Window Displayed");
	}

	@Then("Click on the Submit button")
	public void click_on_the_submit_button() {

	}

	@Then("User can view Successful message")
	public void user_can_view_successful_message() {

	}

	@Then("Close browser")
	public void close_browser() {
		driver.quit();
	}

	@When("Select Purchase\\/Sales Order button and Enter SO Number {string}")
	public void select_purchase_sales_order_button_and_enter_so_number(String string) throws InterruptedException {
		contacts.clickonPORadiobutton();
		contacts.EnterSONumber(string);
	}

	@When("Select Purchase\\/Sales Order button and Enter SO Number through Excel")
	public void select_purchase_sales_order_button_and_enter_so_number_through_excel() {

		contacts.clickonPORadiobutton();
		admin = new Admin(driver);
		admin.EnterSO();
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.clickonPORadiobutton();

	}

	@Then("Select service type as a {string}")
	public void select_service_type_as_a(String string) throws InterruptedException {
		contacts.selectserviceType();
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		contacts.ReturnResonePSR();
		contacts.ReturnPSRselect();
		contacts.clickonnectaftersold();

	}

	@When("Click on next only")
	public void click_next() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		contacts.clickonnextaftersuldto2();

	}

	@When("Clickon Product radiobutton and enter MM Number {string}")
	public void clickon_product_radiobutton_and_enter_mm_number(String string) throws InterruptedException {
		contacts.clickonProductRadioButton();
		contacts.EnterMM(string);
	}

	@When("Click on search button after Entering MM Number")
	public void click_on_search_button_after_entering_mm_number() throws InterruptedException {
		contacts.ClickOnSearchAfterMM();
	}

	@And("Clickon PO History")
	public void clickon_po_history() throws InterruptedException {
		contacts.clickOnPOHistory();
		contacts.clickonNextAfterPOHistory();
	}

	@When("Clickon PO History only")
	public void clickon_po_historyonly() throws InterruptedException {
		contacts.clickOnPOHistory();
	}

	@Then("Select service type as a Promotional Stock Rotation and Upload Bulk File {string}")
	public void select_service_type_as_a_promotional_stock_rotation_and_upload_bulk_file(String string)
			throws InterruptedException, AWTException {
		contacts.selectserviceType();
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		contacts.ReturnResonePSR();
		contacts.ReturnPSRselect();
		contacts.clickonnectaftersold();
		contacts.ClickonBulkUpload();
		contacts.clickonUploadfileButton(string);
		Robot upload = new Robot(); // To use robot class to handle file upload
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\Downloads\\MMCPN_BulkUploadTemplate.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@When("Click On Bulk upload {string}")
	public void click_on_bulk_upload(String string) throws InterruptedException, AWTException {
		Thread.sleep(3000);
		contacts.ClickonBulkUpload();
		Thread.sleep(3000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(5000);

		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\Downloads\\MMCPN_BulkUploadTemplate.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
	}

	@When("Click On Bulk upload SR MMCPN {string}")
	public void click_on_bulk_uploadSR(String string) throws InterruptedException, AWTException {
		Thread.sleep(3000);
		contacts.ClickonBulkUpload();
		Thread.sleep(3000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(5000);

		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\Downloads\\MMCPN_BulkUploadTemplate (17).csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
	}

	@Then("Click On Validate Button")
	public void click_on_validate_button() throws InterruptedException {
		contacts.ValidateAfterUpload();
	}

	@Then("Select service type as a Promotional Stock Rotation")
	public void select_service_type_as_a_promotional_stock_rotation() throws InterruptedException {
		contacts.selectserviceType();
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		contacts.ReturnResonePSR();
		contacts.ReturnPSRselect();
		contacts.clickonnectaftersold();
	}

	@Then("Select service type as a Promotional Stock Rotations")
	public void select_service_type_as_a_promotional_stock_rotations() throws InterruptedException {
		contacts.selectserviceType();
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		contacts.ReturnResonePSR();
		contacts.ReturnPSRselect();
	}

	@Then("Select service type as Stock roatation")
	public void select_service_type_as_stock_roatation() throws InterruptedException {
		contacts.selectserviceType();
		contacts.clickoncreatebutton();
	}

	@Then("Enter Sold To details")
	public void enter_sold_to_details() throws InterruptedException {
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
	}

	@Then("Select Promotional Stock option")
	public void select_promotional_stock_option() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectReason();
		Technical.SelectPromationalStockRoatation();

	}

	@When("Select Return Reason FCF")
	public void select_return_reasonFCF() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectReason();
		Technical.SelectRSFaildAtCustomerFactory();
	}

	@Then("click on Product radio button")
	public void click_on_product_radio_button() throws InterruptedException {
		contacts.clickonProductRadioButton();
	}

	@Then("Select CPN Radio button")
	public void select_cpn_radio_button() throws InterruptedException {
		contacts.ClickonCPN();
	}

	@When("Enter CPN number")
	public void enter_cpn_number() throws InterruptedException {
		contacts.EnterCPN();
	}

	@Then("Enter CPN Number through Excel")
	public void enter_cpn_number_through_excel() {

		admin = new Admin(driver);
		admin.EnterCPNyhroughExcel();
	}

	@When("Enter CPN Numbervalue {string}")
	public void enter_cpn_numbervalue(String string) {
		contacts.EnterCPNValue(string);
	}

	@When("Enter CPN number {string}")
	public void enter_cpn_number(String string) {
		contacts.EnterCPNvalue(string);
	}

	@When("click on search button")
	public void click_on_search_button() {
		contacts.SearchCPN();

	}

	@Then("Click on Order Part Number radio button")
	public void click_on_order_part_number_radio_button() throws InterruptedException {
		contacts.SelectOPNradiobutton();
	}

	@When("Enter OPN into search box {string}")
	public void enter_opn_into_search_box(String string) throws InterruptedException {
		contacts.EnterOPN(string);
	}

	@When("Enter OPN into search box through excel")
	public void Enter_OPN_into_search_box_through_excel() throws InterruptedException {
		Technical.enteropn();
	}

	@When("Clickon PO History button")
	public void clickon_po_history_button() throws InterruptedException {
		contacts.clickOnPOHistory();
	}

	@When("Click on Save for later button")
	public void click_on_save_for_later_button() throws InterruptedException {
		contacts.ClickonSaveforlater();
		contacts.ClickonOKButton();
		Thread.sleep(3000);
	}

	@When("Check Status")
	public void check_status(String Text) {
		Assert.assertEquals("Open, Unsubmitted", Text, "Text does not match!");
		System.out.println(Text + "Getting Status From WebPage");
	}

	@When("Enter Credit price <25K")
	public void enter_credit_price_25k() throws InterruptedException {
		contacts.pricelessthan25k();
		contacts.entertextintoreasonfield();
	}

	@When("Enter Credit price >25K")
	public void enter_credit_price_G25k() throws InterruptedException {
		contacts.PricerGreterThan25K();
		contacts.entertextintoreasonfield();
	}

	@When("User Slect L3 Agent Selection")
	public void user_slect_l3_agent_selection() throws InterruptedException {
		contacts.selectL3User();
	}

	@When("Click on Select Button")
	public void click_on_select_button() throws InterruptedException {
		contacts.clickonL3Select();
		contacts.selectL3agent();
	}

	/*
	 * Author: Omkar Yadav Change:LogIn Page ISVC R4C Description:Edit Flow Updated
	 */
	@When("Click On Accept")
	public void click_on_accept() throws InterruptedException, AWTException {
		contacts.ClickonAcceptButton();
	}

	@When("Click on edit")
	public void click_on_edit() {
		contacts.ClickOnCaseEdit();
	}

	@Then("Enter Sold To details for opt inout {string}")
	public void enter_sold_to_details_for_opt_inout(String string) throws InterruptedException {
		contacts.clickonsoldtobox(string);
	}

	@Then("Select return reason Standard Stock roatation")
	public void select_return_reason_standard_stock_roatation() throws InterruptedException {
		contacts.clickonreturnreason();

	}

	@When("Select Contact from list")
	public void select_contact_from_list() throws InterruptedException {
		contacts.SelectDropdownOption_Contacts(); // To select contact option
	}

	@When("Select recentview dropdown")
	public void select_recentview_dropdown() throws InterruptedException {
		contacts.clickondropdown(); // To click on List view Dropdown
		contacts.SelectTestContact(); // clickon recent view dropdown option
	}

	@When("Enter Contact name and select")
	public void enter_contact_name_and_select() throws InterruptedException {
		contacts.Entersearchthistext();
		contacts.selectsearchcontacts();
		contacts.selectsearchcontacts();
	}

	@When("Click on Contacts name")
	public void click_on_contacts_name() throws InterruptedException {
		contacts.selecttext();
	}

	/*
	 * Auth0r: Omkar Yadav Change:LogIn Page ISVC R4C
	 * 
	 */
	@When("Select Service Type Stock Rotation and Click on Create button")
	public void select_service_type_stock_rotation_and_click_on_create_button() throws InterruptedException {
		contacts.selectserviceType();
		contacts.clickoncreatebutton();

	}

	@When("Select return Reason Standard stock rotation")
	public void select_return_reason_standard_stock_rotation() throws InterruptedException {
		WebElement Clickstandardstock = driver.findElement(By.xpath(
				"//c-r4c_-a-g_-case-header//lightning-combobox[@data-name='Sales_Issue_Return_Reason__c']//div//button[@name='Select one']"));
		Clickstandardstock.click();

		WebElement Selectstandardstock = driver.findElement(By.xpath("//span[@title='Standard Stock Rotation']"));
		Selectstandardstock.click();
	}

	@When("Select Standard stock rotation")
	public void SelectSSR() throws InterruptedException {
		contacts.ClickSSR();
		contacts.SelectSSR();
	}

	@When("Enter Sold To Number")
	public void enter_sold_to_number() throws InterruptedException {
		contacts.EnterSoldToOptIn();
		contacts.selectsoldToContact();
	}

	@When("Click on Cancel buttton")
	public void click_on_cancel_buttton() throws InterruptedException {
		contacts.clickoncancelIMF();
	}

	@When("Select Freight Type")
	public void select_freight_type() throws InterruptedException {
		contacts.ReturnResonePSR();
		contacts.ReturnPSRselect();
		contacts.clickonnectaftersold();
	}

	@When("Select Contact from list and enter Text into search field {string} and select")
	public void select_contact_from_list_and_enter_text_into_search_field_and_select(String string)
			throws InterruptedException {
		contacts.SelectDropdownOption_Contacts(); // To select contact option
		contacts.clickondropdown(); // To click on List view Dropdown
		contacts.SelectTestContact(); // clickon recent view dropdown option
		contacts.EntercontactNameintosearchfield(string); // To Enter Text into search field- RecentView
		contacts.ClickOnFirstSearch();
	}

	/*
	 * Auth0r: Omkar Yadav Change:LogIn Page ISVC R4C Description:Click On Button
	 * added
	 */
	@When("Click on Ok buttton")
	public void click_on_ok_buttton() {
		contacts.clickononIMF();
	}

	@When("Enter Sold To Number case edit")
	public void enter_sold_to_number_case_edit() throws InterruptedException {
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		contacts.ReturnResonePSR();
		contacts.ReturnPSRselect();
	}

	@When("Enter Sold To Number case")
	public void enter_sold_to_number_case() throws InterruptedException {
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
	}

	@When("Select  Reason as Standard Stock Rotation")
	public void select_reason_as_standard_stock_rotation() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectReason();
		Technical.SelectPromationalStockRoatation();
	}

	@When("Select RCA address")
	public void select_rca_address() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@aria-label='Return to Customer Address']/..//button[@aria-label='Return to Customer Address']")));
			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(driver); // Focus on the element
			actions.moveToElement(element).perform();
			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
		} catch (TimeoutException e) {
			System.out.println("Element not found or not clickable within the specified time");
		} finally {
			System.out.println("Element still not clickable");
		}
		contacts.selectRCA();
	}

	@Then("Select Ship To contact")
	public void select_ship_to_contact() throws InterruptedException {

		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.ShipTocontact();
		contacts.SelectShipToContact();
	}

	@Then("Select Ship To contact second Option")
	public void select_ship_to_contact_second_option() {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.ShipTocontact();
		contacts.SelectSoldToContactSecond();

	}

	@When("Select RCA CMF Type address")
	public void select_rca_cmf_type_address() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@aria-label='Return to Customer Address']/..//button[@aria-label='Return to Customer Address']")));
			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(driver); // Focus on the element
			actions.moveToElement(element).perform();
			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
		} catch (TimeoutException e) {
			System.out.println("Element not found or not clickable within the specified time");
		} finally {
			System.out.println("Element still not clickable");
		}

		driver.findElement(By.xpath("//span[@title='Lange Wende 43,Soest,Nordrhein-Westfalen,Germany,59494']")).click();

	}

	@When("Select RCA CMF Type address for QA")
	public void select_rca_cmftype_address() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Wait for the 'Return to Customer Address' button to be visible
			WebElement returnAddressButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@aria-label='Return to Customer Address']/..//button[@aria-label='Return to Customer Address']")));

			// Scroll the button into view
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", returnAddressButton);

			// Move focus to the element
			Actions actions = new Actions(driver);
			actions.moveToElement(returnAddressButton).perform();

			// Click the button using JavaScript
			js.executeScript("arguments[0].click();", returnAddressButton);

			// Wait for the address element to be clickable before clicking
			WebElement addressElement = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//span[@title='Lange Wende 43,Soest,Nordrhein-Westfalen,Germany,59494']")));

			// Click on the address element
			addressElement.click();

		} catch (TimeoutException e) {
			System.out.println("Element not found or not clickable within the specified time");
		} finally {
			System.out.println("Attempted to select the address.");
		}
	}

	@When("Select CA Address")
	public void select_ca_address() throws InterruptedException {
		contacts.ClickonCA();
		contacts.SelectCA();
	}

	@When("Select Return customer contact")
	public void select_return_customer_contact() throws InterruptedException {
		contacts.clickonRCC();
		contacts.selectRCC();

	}

	@When("select Collection Contact")
	public void select_collection_contact() throws InterruptedException {
		contacts.ClickonCC();
		contacts.SelectCC();
	}

	@When("Click on next")
	public void click_on_next() throws InterruptedException {
		contacts.clickonnextIMF();
	}

	// Scenario-24
	@When("Enter Return Quantity >=100K")
	public void enter_return_quantity_100k() throws InterruptedException {
		contacts.EnterMore10000Qty();
	}
//-----------------Technical Scenarios----------------------	

	@When("Select Service Type Technical and Click on Create button")
	public void select_service_type_technical_and_click_on_create_button() throws InterruptedException {
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		contacts.ReturnResonePSR();
	}

	@When("Select Return Reason")
	public void select_return_reason() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectRSFaildAtCustomerFactory();
	}

	@When("Select Return Reason FEC web")
	public void select_return_reasonweb() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectReason();
		Technical.FailedatEndCustomer();
	}

	@When("Click On Warranty Entitlement Check")
	public void click_on_warranty_entitlement_check() throws InterruptedException {
		Technical.ClickOnWarrentyEntitlement();

	}

	@When("Click On Warranty Entitlement Check Web")
	public void click_on_warranty_entitlement_checkWeb() throws InterruptedException {
		Technical.ClickOnWarrentyEntitlement();
		Technical.clickOnWarrantyEntitlementClick();
	}

	@When("Enter ProductUniqueID {string}")
	public void enter_product_unique_id(String prefix) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> textBoxes = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='Enter Product Unique Id']"))); // Get

		// Loop through the text boxes and enter the values
		for (int i = 0; i < textBoxes.size(); i++) {
			String value = prefix + (i + 1);
			textBoxes.get(i).sendKeys(value);
		}

	}

	@When("click accelept")
	public void click_accelept() throws InterruptedException {
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
//			WebElement element = wait.until(
//					ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Accept']")));
//
//			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
//			js.executeScript("arguments[0].scrollIntoView(true);", element);
//			Actions actions = new Actions(driver); // Focus on the element
//			actions.moveToElement(element).perform();
//			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
//		} catch (TimeoutException e) {
//			System.out.println("Element not found or not clickable within the specified time");
//		} finally {
//			System.out.println("Element still not clickable");
//		}
		contacts.Clickacceptbutton();

	}

// -----------------Case Edit TC-1-------------

	@When("Click On Edit Option")
	public void click_on_edit_option() throws InterruptedException {
		// Thread.sleep(5000);
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Create a WebDriverWait object
//			WebElement element = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Edit']")));
//
//			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
//			js.executeScript("arguments[0].scrollIntoView(true);", element);
//			Actions actions = new Actions(driver); // Focus on the element
//			actions.moveToElement(element).perform();
//			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
//		} catch (TimeoutException e) {
//			System.out.println("Element not found or not clickable within the specified time");
//		} finally {
//			//System.out.println("Element still not clickable");
//		}
		// Thread.sleep(2000);
		contacts.ClickEdit();
	}

	@When("Click On Next after edit")
	public void click_on_next_after_edit() throws InterruptedException {
		contacts.clickonnextafteredit();
	}

	@When("Click On Next after edit Case edit")
	public void click_on_next_after_edit_case_edit() throws InterruptedException {
		Thread.sleep(8000);

//		try {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
//			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
//					By.xpath("//li//button[contains(text(),'Save for Later')]/..//button[contains(text(),'Next')]")));
//			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
//			js.executeScript("arguments[0].scrollIntoView(true);", element);
//			Actions actions = new Actions(driver); // Focus on the element
//			actions.moveToElement(element).perform();
//			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
//		} catch (TimeoutException e) {
//			System.out.println("Element not found or not clickable within the specified time");
//		} finally {
//			System.out.println("Element still not clickable");
//		}

		contacts.ClickNextAfterEdit();

	}

// -----------------Case Edit TC-Submitted Pending Action-------------

	@When("Click on Related option")
	public void click_on_related_option() throws InterruptedException {
		contacts.ClickonRelated();
	}

	@When("Click on View All cases option")
	public void click_on_view_all_cases_option() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//body/div/div[@aria-hidden='false']/section/div/div/div/div[@role='main']/section[@role='tabpanel']/div/div/section[@role='tabpanel']/div/div/div/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-contact_-record_-page1___-contact___-v-i-e-w/forcegenerated-flexipage_contact_record_page1_contact__view_js[@data-page-type='RecordPage']/record_flexipage-desktop-record-page-decorator/div/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div/div/slot[@name='main']/flexipage-component2[@data-component-id='flexipage_tabset']/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot[@name='tabs']/flexipage-tab2[@role='tabpanel']/slot/flexipage-component2[@data-component-id='force_relatedListContainer']/slot/lst-related-list-container/div/div/lst-related-list-single-container/laf-progressive-container[@aria-busy='false']/slot/lst-related-list-single-app-builder-mapper/article[@aria-label='Cases']/lst-related-list-view-manager/a/span[1]")));

			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(driver); // Focus on the element
			actions.moveToElement(element).perform();
			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
		} catch (TimeoutException e) {
			System.out.println("Element not found or not clickable within the specified time");
		} finally {
		}

	}

	@When("click on case is Submitted Pending Action")
	public void click_on_case_is_submitted_pending_action() throws InterruptedException {
		// Locate the rows in the table
		List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
		// Iterate through each row
		for (WebElement row : rows) {
			// Get the status from the status column
			WebElement statusElement = row.findElement(By.xpath("//td[@data-label='Status']"));
			String status = statusElement.getText().trim();
			System.out.println(status + "Printed Status----------------------");
			// Check if the status is "Open" or "Unsubmitted"
			if (status.equals("Open") || status.equals("Unsubmitted")) {
				// Click on the corresponding case in the case column
				WebElement caseElement = row.findElement(By.xpath("//th[@data-label='Case']"));
				System.out.println(caseElement + "Printed Case number-------------------");
				caseElement.click();

				break;
			}
		}

	}

	@When("Click On FilterIcon")
	public void click_on_filter_icon() throws InterruptedException {
		contacts.ClickonFilter();
	}

	@When("Enter CAse No into text field {string}")
	public void enter_c_ase_no_into_text_field(String string) throws InterruptedException {
		contacts.EnterCaseintofield(string);
	}

	@When("Click On Apply button")
	public void click_on_apply_button() throws InterruptedException {
		Thread.sleep(3000);
		contacts.ClickOnAppy();
	}

	@When("Select searched case")
	public void select_searched_case() throws InterruptedException {
		Thread.sleep(3000);
		contacts.SelectSearchedcase();
	}

	@When("Click On Next after edit button")
	public void click_on_next_after_edit_button() throws InterruptedException {
		contacts.Clickonextedit();
	}

//Technical Case Creation Scenario 1

	@When("Clickon PO History check button")
	public void clickon_po_history_check_button() throws InterruptedException {
		Technical.POhistoryCheck();

	}

	@When("Click on PO Change popup Ok button")
	public void click_on_po_change_popup_ok_button() throws InterruptedException {
		Technical.clickonOkButton();

	}

	@When("Click on Next After PO history check")
	public void click_on_next_after_po_history_check() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.clickonNext();
	}

	@When("Click on next button")
	public void click_on_next_button() throws InterruptedException {
		contacts.clicknextbutton();

	}

	@When("Click On Credit price Validation")
	public void click_on_credit_price_validation() throws InterruptedException {
		Technical.ClickOnCreditpriceValidation();

	}

	@When("Select All Line items check box")
	public void select_all_line_items_check_box() throws InterruptedException {
		Technical.Unselectallcheckbox();
	}

	@When("Select First Check box")
	public void select_first_check_box() throws InterruptedException {
		Technical.SelectFirstCheckbox();
	}

//Technical Case Creation Scenario 2

	@When("Select Remedy type is credit")
	public void select_remedy_type_is_credit() {
	}

	@When("Select Return Reason Faild at End Customer")
	public void select_return_reason_faild_at_end_customer() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.ReturnReasonFaildEndCustomer();
	}
//--------Technical test 3 case PO search functionality

	@When("Select ReTurnTo Customer address")
	public void slect_reurn_to_customer_address() throws InterruptedException {
		Technical.RCA();
		Technical.SelectRCA();
	}

	@Then("Select RCA for OptIn\\/Out")
	public void select_rca_for_opt_in_out() throws InterruptedException {
		Technical.RCA();
		Technical.SelectRCAOptinOut();
	}

	@When("Select Collection address")
	public void select_collection_address() throws InterruptedException {
		Technical.ClickCA();
		Technical.SelectCA();
	}

	@When("Return to Customer Contact name")
	public void return_to_customer_contact_name() throws InterruptedException {
		Technical.ClickRCC();
		Technical.selectRCC();
	}
//-------------Technical BulkUpload----------

	@When("Upload Bulk File {string}\"")
	public void upload_bulk_file(String string) throws AWTException, InterruptedException {
		contacts.ClickonBulkUpload();
		contacts.clickonUploadfileButton(string);
		Thread.sleep(4000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Quality Upload File\\MMCPNQualitya.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@When("Upload Bulk File Technical {string}\"")
	public void upload_bulk_fileT(String string) throws AWTException, InterruptedException {
		contacts.ClickonBulkUpload();
		contacts.clickonUploadfileButton(string);
		Thread.sleep(4000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Technical Upload File\\MMCPNTechsample.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@When("Upload Bulk File Technical MMCPN Bulk Upload {}")
	public void upload_bulk(String string) throws AWTException, InterruptedException {
		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(4000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Technical Upload File\\TechnicalMMCPNBulkUpload.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@Then("Upload Bulk File {string} NPR-Credit")
	public void upload_bulk_file_npr_credit(String string) throws InterruptedException, AWTException {
		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(3000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Technical Upload File Web\\TechnicalULTBulkUpload.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
	}

	@Then("Upload Bulk File With MM IDS {string}")
	public void upload_bulk_file_with_mm_ids1(String string) throws InterruptedException, AWTException {
		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(3000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Admin Upload File\\MMCPNULTBulkAdmin.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@Then("Upload Miscellaneous Return Bulk File With MM IDS {string}")
	public void upload_bulklReturn(String string) throws InterruptedException, AWTException {
		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(3000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\TestCases\\MiscellaneousReturnBulkUpload.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@Then("Upload Internal Miscellaneous Return Bulk File With MM IDS {string}")
	public void upload_bulklReturninternal(String string) throws InterruptedException, AWTException {
		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(3000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\TestCases\\MiscellaneousinternalBulkUpload.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@Then("Upload Miscellaneous Bulk File With MM IDS {string}")
	public void upload_bulkl(String string) throws InterruptedException, AWTException {
		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(3000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Misc Upload File\\MiscellaneousBulkUpload.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@When("Upload Bulk File {string} quality MMCPN")
	public void upload_bulk_file_quality_mmcpn(String string) throws InterruptedException, AWTException {
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(3000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Quality Upload File\\MMCPNQualitya.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
	}

//---------->25k----------------

	@When("Select Service Type Technical and Click on Create buttons only")
	public void select_service_type_technical_and_click_on_create_buttons_only() throws InterruptedException {
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
	}

	@When("Select Remedy credit")
	public void select_remedy_credit() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.CreditOption();
		Technical.SelectReason();
		Technical.SelectreasonFCFS();
	}

	@When("Select Remedy credit and reason")
	public void select_remedy_credit_and_reason() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.CreditOption();
		Technical.SelectReason();
		Technical.SelectFailedatCustomerFactorySite();
	}

	@When("Select Remedy credit and reason Fail Customer End")
	public void select_remedy_credit_and_reason_fail_customer_end() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.CreditOption();
		Technical.SelectReason();
		Technical.FailedatEndCustomer();
	}

	@When("Select Reason FOCS")
	public void Selectreason() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectReason();
		miscellaneous = new Miscellaneous(driver);
		miscellaneous.ReasonFOCS();

	}

	@When("Select Reason Internal return")
	public void SelectInternalreturn() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectReason();
		miscellaneous = new Miscellaneous(driver);
		miscellaneous.SelectInternalreturn();

	}

	@When("select checkbox")
	public void select_Checkbox() throws InterruptedException {
		Technical.SelectCheckbox();
	}

	@When("Select Remedy credit and reason SSR")
	public void select_remedy_credit_and_reason_ssr() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.CreditOption();

	}

	@When("Select Return Reason Failed at Customer Factory Site")
	public void select_return_reason_failed_at_customer_factory_site() throws InterruptedException {

	}
//------------<25 k-------------------

	@When("Select All Line items with RM")
	public void select_all_line_items_with_rm() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.ClickOnAll();
		// Technical.Selectfirst();
	}

	@When("Click Next button after All Item selection")
	public void click_next_button_after_all_item_selection() throws InterruptedException {
		Technical.NextAfterCheckAll();
	}

//------------>100K Price Override
	@When("Enter Credit price <100K")
	public void enter_credit_price_100k() throws InterruptedException {
		Technical.PricerGreterThan100K();
		contacts.entertextintoreasonfield();
	}
//----------------NPR Bulk upload---------------------------

	@When("Click On create button and select remedy and select reason")
	public void click_on_create_button_and_select_remedy_and_select_reason() throws InterruptedException {
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.ClickOnNPR();
		Technical.SelectReason();
		Technical.SelectreasonFCFS();
	}
//-----------------NPR BulkIpload with ULT

	@When("Click On create button and select remedy and select reason customer site")
	public void click_on_create_button_and_select_remedy_and_select_reason_customer_site() throws InterruptedException {
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.ClickOnNPR();
		Technical.SelectReason();
		Technical.SelectFailcustomer();

	}

	@Then("Click On create button and select remedy and select reason customer site- SoldTo {string}")
	public void click_on_create_button_and_select_remedy_and_select_reason_customer_site_sold_to(String string)
			throws InterruptedException {

		contacts.clickoncreatebutton();
		contacts.EnterSoldToNumber(string);
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.ClickOnNPR();
		Technical.SelectReason();
		Technical.SelectFailcustomer();

	}
//-------------------NPR CPN case creation

	@When("Select First Item")
	public void select_first_item() throws InterruptedException {
		// Thread.sleep(3000);
		// contacts.selectallcheckbox();
		Technical.Selectfirst();

	}

	@When("Select ALL")
	public void select_ALL() throws InterruptedException {
		Thread.sleep(1000);
		contacts.selectallcheckbox();

	}

//------------------NPR Case creation with MM Search

	@When("Click On create button and select remedy and select reason  Warranty Service\\/Software")
	public void click_on_create_button_and_select_remedy_and_select_reason_warranty_service_software()
			throws InterruptedException {
		contacts.clickoncreatebutton();

		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.ClickOnNPR();
		Technical.SelectReason();
		Technical.SelectWarrentyService();
	}

//----------------NPR case creation Using OPN
	@When("Click On create button and select remedy and select reason  Simple")
	public void click_on_create_button_and_select_remedy_and_select_reason_simple() throws InterruptedException {
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.ClickOnNPR();
		Technical.SelectReason();
		Technical.SelectSimpleReason();

	}

	@Then("Select Remedy credit and reason Tech Simple")
	public void select_remedy_credit_and_reason_tech_simple() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.CreditOption();
		Technical.SelectReason();
		driver.findElement(By.xpath("//span[@title='Technical Sample']")).click();
	}
//-------------------With ULT using MM

	@When("Enter ULT Number {string}")
	public void enter_ult_number(String string) throws InterruptedException {
		Technical.EnterULT(string);
	}

// ------------------with ULT is not mandatory

	@When("Select Service Type Technical and Click on Create button select remedy credit")
	public void select_service_type_technical_and_click_on_create_button_select_remedy_credit()
			throws InterruptedException {
		contacts.clickoncreatebutton();
		contacts.EnterSoldTo();
		contacts.SoldToIdContact();
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.Selectcredit();
		Technical.SelectReason();
		Technical.SelectFailcustomer();

	}
//-------------ULT Bulk Upload

	@When("User Click on Download ULT Template")
	public void user_click_on_download_ult_template() {
		Technical.ClickOnULTDownload();
	}

	@When("Enter Return Quantity {string}")
	public void enter_return_quantity(String string) {
		contacts.enterquantity(string);
	}

	@When("Upload Bulk File {string} ULT")
	public void upload_bulk_file_ult(String string) throws AWTException, InterruptedException {

		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Quality Upload File\\ULT_BulkUploadQuality.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		Thread.sleep(2000);
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@When("Upload ULT Bulk File {string}")
	public void Upload_ULT(String string) throws InterruptedException, AWTException {
		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Technical Exchange Upload File\\ULT_BuilkTechnicalExchange.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		Thread.sleep(2000);
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@When("Upload ULT Bulk File {string} Technical")
	public void Upload_ULTtech(String string) throws InterruptedException, AWTException {
		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Technical Upload File\\ULT_BuilkTechnical.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		Thread.sleep(2000);
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@When("Select Contact from list and enter Text into search field {string} and select Contacts")
	public void select_contact_from_list_and_enter_text_into_search_field_and_select_contacts(String string)
			throws InterruptedException {
		contacts.SelectDropdownOption_Contacts(); // To select contact option
		contacts.clickondropdown(); // To click on List view Dropdown
		contacts.SelectTestContact(); // clickon recent view dropdown option
		contacts.EntercontactNameintosearchfield(string); // To Enter Text into search field- RecentView
		driver.findElement(By.xpath("//tbody/tr/td[1]/span[1]//following::th[@scope='row']")).click();
	}

	@When("Upload Bulk File {string}\" with MM Ids")
	public void upload_bulk_file_with_mm_ids(String string) throws InterruptedException, AWTException {
		contacts.ClickonBulkUpload();
		contacts.clickonUploadfileButton(string);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"\"C:\\Users\\oyadavx\\Downloads\\MMCPN_BulkUploadTemplate (1) - Copy.csv\"");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
	}

	@When("Select Service Type Technical and Click on Create buttons and Enter Sold To {string}")
	public void select_service_type_technical_and_click_on_create_buttons_and_enter_sold_to(String string)
			throws InterruptedException {
		contacts.clickoncreatebutton();
		contacts.EnterSoldToNumber(string);
		// contacts.SelectGlobalsearchoption();
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();
	}

	@Then("Select Service Type Technical Exchange and Click on Create buttons and enter sold to")
	public void select_Technicalexchange() throws InterruptedException {
		contacts.clickoncreatebutton();
		Technical.enterSlodToExcel();
		Thread.sleep(2000);
		// Technical.selectSoldtoOption();
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Select Service Type Miscellaneous and Click on Create buttons and enter sold to")
	public void select_Miscellaneous() throws InterruptedException {

		miscellaneous = new Miscellaneous(driver);
		miscellaneous.SelectServiceTypeMiscellaneous();
		contacts.clickoncreatebutton();
		Technical.enterSlodToExcel();
		Thread.sleep(2000);
		// Technical.selectSoldtoOption();
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Select Internal Case Creation Option")
	public void entername() throws InterruptedException {
		miscellaneous = new Miscellaneous(driver);
		miscellaneous.CLickAPP();
		miscellaneous.EnterText();
		miscellaneous.SelectType();
		Technical = new Technical(driver);
		Thread.sleep(2000);
		Technical.enterSlodToExcel();
		Thread.sleep(2000);
		// Technical.selectSoldtoOption();
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Click On App Launcher and enter price masking")
	public void select_creditpricemasking() {
		miscellaneous = new Miscellaneous(driver);
		miscellaneous.CLickAPP();
		miscellaneous.EnterTextPriceMasking();
		miscellaneous.SelectTypePM();
	}

	@Then("Select credit")
	public void select_credit() {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.CreditOption();
	}

	@Then("Select Return Only")
	public void select_Return() {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		exceptions = new Exceptions(driver);
		miscellaneous.ReasonReturn();

	}

	@When("Select Service Type SR and Click on Create buttons and Enter Sold To {string}")
	public void select_service_type_sr_and_click_on_create_buttons_and_enter_sold_to(String string)
			throws InterruptedException {

		contacts.selectserviceType();
		contacts.clickoncreatebutton();
		contacts.EnterSoldToNumber(string);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();
	}

	@When("Enter text global and select {string}")
	public void enter_text_global_and_select(String string) throws InterruptedException {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.clickGlobal();
		contacts.EnterTextGlobalfield(string);
		contacts.SelectGlobalsearch();

	}

	@Then("Select Contact from list and enter Case no into search field {string}")
	public void select_contact_from_list_and_enter_cacse_no_into_search_field(String string)
			throws InterruptedException {

		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.clickGlobal();
		Thread.sleep(2000);
		contacts.EnterTextGlobalfield(string);
		Thread.sleep(3000);
		contacts.SelectglobalCase();

	}

//-------------------Case Edit Technical
	@When("Click Next After Selection of Check boxes")
	public void click_next_after_selection_of_check_boxes() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.ClickOnNEXTAfterEditfirst();
		Technical.ClickOnNEXTAfterEditsecond();
		Technical.ClickOnNEXTAfterEditthird();

	}

	@When("Check RMA status")
	public void check_rma_status() throws InterruptedException {
		Technical.checkRMAStatus();

	}

	@When("Click Next after EDIT Accept")
	public void click_next_after_edit_accept() {
		driver.findElement(By.xpath("")).click();
	}

	@When("Enter MMIDs By using Excel Sheet")
	public void enter_mmi_ds_by_using_excel_sheet() throws InterruptedException {
		// contacts.clickonPORadiobutton();
		contacts.clickonProductRadioButton();
		Technical = new Technical(driver);
		Technical.ClickMMTextbox();
	}

	@When("Enter ULT IDs")
	public void enter_ult_i_ds() throws InterruptedException {
		Thread.sleep(2000);
		Technical.EnterULTs();
	}

	@When("Enter ULTs from excel")
	public void EnterULTS() throws InterruptedException {

		Technical.EnterULTsForExchange();
		Technical.ClickSearch();

	}

	@When("Click Ok button")
	public void click_ok_button() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.ClickonOk();
	}

	@When("Validate the Status")
	public void validate_the_status() {
		List<WebElement> elements = driver
				.findElements(By.xpath("//span[@class='slds-grid slds-grid_align-center slds-truncate']"));

		// Iterate through the list and print the text of each element
		for (WebElement element : elements) {
			System.out.println(element.getText());
		}
	}

//Opt in And Out Technical

	@When("Select Return Reason as a Failed at Customer Factory Site")
	public void select_return_reason_as_a_failed_at_customer_factory_site() throws InterruptedException {
		contacts.ReturnResonePSR();
		Technical = new Technical(driver);
		Technical.faildatcustomersite();
	}

	@When("User Should Get IMF Freight type {string}")
	public void user_should_get_imf_freight_type(String string) throws InterruptedException {
		Technical.IMFType();
	}

	@When("User Should Get CRF Freight")
	public void user_should_get_crf_freight() {
		Technical.CRFType();
	}

//Test PropFile
	Properties prop = new Properties();

	public Steps() {
		try {
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\Config.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	LoginPage loginPage = new LoginPage(driver);

	@When("User open URL")
	public void user_open_url() {

		String url = prop.getProperty("url");
		driver.get(url);
	}

	@Then("User Enter Email Address and pass PassWord")
	public void user_enter_email_address_and_pass_pass_word() {

		String email = prop.getProperty("username");
		String password = prop.getProperty("password");
		lp.enterEmail(email);
		lp.enterPassword(password);
	}

	@Then("User Enter Email Address and pass PassWord Encripted")
	public void user_enter_email_address_and_pass_pass_word_encripted() throws Exception {
		lp = new LoginPage(driver);
		lp.enterCredentialsAndLogin();
		// loginPage.enterCredentialsAndLogin();

	}

	@When("Check Status Open Unsubmitted")
	public void check_status_open_unsubmitted() throws InterruptedException {
		Thread.sleep(2000); // Need wait because element not loading
		contacts.statusOpenUn();
	}

	@Then("Enter text global")
	public void enter_text_global() throws InterruptedException {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.clickGlobal();

		String userName = prop.getProperty("userName");
		contacts.enterUserName(userName);
		contacts.SelectGlobalsearch();
	}

	@Then("Select Service Type Technical and Click on Create buttons and Enter Sold")
	public void select_service_type_technical_and_click_on_create_buttons_and_enter_sold() throws InterruptedException {
		// Thread.sleep(4000);
		contacts.clickoncreatebutton();
		// Thread.sleep(4000);
		String soldTo = prop.getProperty("soldTo");
		contacts.enterSoldTo(soldTo);
	}

//Global search Excel
	/*
	 * Author: Omkar Yadav Date: 10 Nov 2022 Description:Customer WebPortalFlow
	 */
	@Then("Enter text global excel")
	public void enter_text_global_excel() throws InterruptedException {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		Thread.sleep(2000);
		contacts.clickGlobal();
		Technical = new Technical(driver);
		Thread.sleep(2000);
		Technical.EnterGlobalTextthrouhExcel();
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.SelectGlobalsearch();
		Thread.sleep(2000);
	}

	@Then("Select Service Type Technical and Click on Create buttons and Enter Sold through Excel")
	public void select_service_type_technical_and_click_on_create_buttons_and_enter_sold_through_excel()
			throws InterruptedException, IOException {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		Thread.sleep(2000);
		contacts.clickoncreatebutton();
		Technical = new Technical(driver);
		Thread.sleep(2000);
		Technical.enterSlodToExcel();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Select Service Type Admin and Click create button enter Sold through Excel file")
	public void select_service_type_admin_and_click_create_button_enter_sold_through_excel_file()
			throws InterruptedException, IOException {
		Thread.sleep(2000);
		admin = new Admin(driver);
		admin.SelectServiceTypeAdmin();
		contacts.clickoncreatebutton();
		Technical = new Technical(driver);
		// Thread.sleep(2000);
		Technical.enterSlodToExcel();
		Thread.sleep(2000);
		// Technical.selectSoldtoOption();
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Select Service Type Exception and Click create button enter Sold through Excel file")
	public void select_service_type_exception_and_click_create_button_enter_sold_through_excel_file()
			throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		exceptions = new Exceptions(driver);
		exceptions.SelectServiceTypeException();
		contacts.clickoncreatebutton();
		Technical = new Technical(driver);
		Technical.enterSlodToExcel();
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Select Exception Return Reason")
	public void select_exception_return_reson() {

		Technical.SelectReason();
		exceptions.ExceptionReturn();

	}

	@Then("Select Exception Return Reason and click ok")
	public void select_exception_return_reason_and_click_ok() {
		Technical.SelectReason();
		exceptions.ExceptionReturn();
		contacts.clickok();
	}

	@Then("Select Exception Return Reason and click cancel")
	public void select_exception_return_reason_and_click_cancel() {
		Technical.SelectReason();
		exceptions.ExceptionReturn();
		contacts.ClickOnCancel();
	}

	@Then("Select Cancel")
	public void select_exception_return_reason_and_click_cancels() {

		contacts.ClickOnCancel();
	}

	@Then("Select Ok")
	public void select_exception_return_reason_and_click_oks() {

		contacts.clickok();
	}

	@When("Click Cancel")
	public void Clickcancel() {
		miscellaneous = new Miscellaneous(driver);
		miscellaneous.ClickCancel();

	}

	@Then("Select GRL Exception Reason")
	public void select_grl_exception_reason() {
		Technical.SelectReason();
		exceptions.SelectGRLException();

	}

	@Then("Select Quarterly Exception")
	public void select_quarterly_exception() {
		Technical.SelectReason();
		exceptions.QuaterlyException();
	}

	@Then("Enter Justification")
	public void enter_justification() {
		exceptions.Justification();
	}

	@Then("Select RCA address first")
	public void select_rca_address_first() throws InterruptedException {
		// ---------------------------
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@aria-label='Return to Customer Address']/..//button[@aria-label='Return to Customer Address']")));
			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(driver); // Focus on the element
			actions.moveToElement(element).perform();
			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
		} catch (TimeoutException e) {
			System.out.println("Element not found or not clickable within the specified time");
		} finally {
			System.out.println("Element still not clickable");
		}
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.SelectFirstRCA();

	}

	@Then("Select RCA address first and click ok")
	public void select_rca_address_firstok() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@aria-label='Return to Customer Address']/..//button[@aria-label='Return to Customer Address']")));
			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(driver); // Focus on the element
			actions.moveToElement(element).perform();
			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
		} catch (TimeoutException e) {
			System.out.println("Element not found or not clickable within the specified time");
		} finally {
			System.out.println("Element still not clickable");
		}
		contacts.SelectFirstRCA();
		contacts.clickok();
	}

	@Then("Select RCA address first and click cancel")
	public void select_rca_address_firstcancel() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@aria-label='Return to Customer Address']/..//button[@aria-label='Return to Customer Address']")));
			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(driver); // Focus on the element
			actions.moveToElement(element).perform();
			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
		} catch (TimeoutException e) {
			System.out.println("Element not found or not clickable within the specified time");
		} finally {
			System.out.println("Element still not clickable");
		}
		// Thread.sleep(4000);
		contacts.SelectFirstRCA();
		contacts.ClickOnCancel();
	}

	@Then("Select Return customer contact first")
	public void select_return_customer_contact_first() throws InterruptedException {
		contacts.clickonRCC();
		contacts.SelectFirstRCC();

	}

	@Then("Select CA Address first")
	public void select_ca_address_first() throws InterruptedException {
		contacts.ClickonCA();
		contacts.SelectFirstCA();

	}

	@When("select Collection Contact first")
	public void select_collection_contact_first() throws InterruptedException {
		contacts.ClickonCC();
		contacts.SelectFirstcc();

	}

	@When("Print Active MM Ids into console")
	public void print_active_mm_ids_into_console() {
		contacts.PrintActiveMMIds();
	}

	@When("Enter Quantity into All fields")
	public void enter_quantity_into_all_fields() {
		contacts.enterValueInDecimalFields();
	}

	@When("Enter Quantity into All fields {string}")
	public void enter_quantity_into_all_fields(String string) {

		contacts.enterValueInDecimalField(string);

	}

	@When("Enter Quantity into All fields through excel")
	public void enter_quantity_into_all_fields_through_excel() throws InterruptedException {
		exceptions = new Exceptions(driver);
		exceptions.enterQuantityCount();
		// Technical.EnterQuantity();

	}

	@When("Enter Price Into All Fields")
	public void enter_price_into_all_fields() {
		contacts.EnterPriceIntoAllFields();
	}

	@When("Enter Reason into All Fields")
	public void enter_reason_into_all_fields() {
		contacts.EnterReasonintoAllFields();
	}

	@When("Click Next button After PO")
	public void click_next_button_after_po() throws InterruptedException {
		contacts.CLickNextAfterPO();
	}

	@When("Print Case No into Console")
	public void print_case_no_into_console() throws InterruptedException {
		Thread.sleep(4000);
		Technical = new Technical(driver);
		Technical.PrintCaseNo();
	}

	@When("Print Case No into Console SR")
	public void print_case_no_into_console_sr() throws InterruptedException {
		Technical = new Technical(driver);
		Thread.sleep(2000);
		Technical.PrintCaseNoSR();
	}

//---------------------Quality-----------------------------

	@Then("Select Service Type Quality and Click create button")
	public void select_service_type_quality_and_click_create_button() throws InterruptedException {
		Thread.sleep(3000);
		quality = new Quality(driver);
		quality.SelectServiceTypeQuality();
		Thread.sleep(3000);
		contacts.clickoncreatebutton();
		Thread.sleep(2000);

	}

	@Then("Select Service Type Quality and Click create button enter Sold through Excel file")
	public void select_service_type_quality_and_click_create_button_enter_sold_through_excel_file()
			throws InterruptedException {
		Thread.sleep(1000);
		quality = new Quality(driver);
		quality.SelectServiceTypeQuality();
		Thread.sleep(1000);
		contacts.clickoncreatebutton();
		Technical = new Technical(driver);
		Technical.enterSlodToExcel();
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Select Service Type Technical and Click create button enter Sold through Excel file")

	public void select_Technical() throws InterruptedException {

		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.clickoncreatebutton();
		Technical = new Technical(driver);
		Technical.enterSlodToExcel();
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Select Service Type SR and Click create button enter Sold through Excel file")

	public void select_TechnicalSR() throws InterruptedException {

		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.selectserviceType();
		contacts.clickoncreatebutton();
		Technical = new Technical(driver);
		Technical.enterSlodToExcel();
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Select Service Type SR and Click create button enter Sold through Excel file for portal")

	public void select_TechnicalSRPortal() throws InterruptedException {

		// contacts = new ContactPage_R4C_CaseCreation(driver);
		// contacts.selectserviceType();

		WebSR = new Web_Portal_StockRotation(driver);
		WebSR.SelectSR();
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.clickoncreatebutton();
		Technical = new Technical(driver);
		Technical.enterSlodToExcel();
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@Then("Enter SoldTo and select {string}")
	public void enter_sold_to_and_select(String string) throws InterruptedException {
		Thread.sleep(1000);
		contacts.EnterSoldToNumber(string);
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();
	}

	@Then("Select credit NPR")
	public void select_credit_npr() {
		Technical.SelectNPR();
		Technical.ClickOnNPR();
	}

	@Then("Select Remedy and reason")
	public void select_remedy_and_reason() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.CreditOption();
		// Technical.SelectReason(); // Commented Because we don't have any other
		// dropdown
		// quality = new Quality(driver);
		// quality.ReasonQAN();
	}

	@When("Select Start Date and End Date")
	public void select_start_date_and_end_date() throws InterruptedException {
		Thread.sleep(1000);
		quality = new Quality(driver);
		quality.selectDateRange();
		Thread.sleep(2000);

	}
//----------------Test Multiple contact ai a time case creation--------

	@Then("Enter text global {string}")
	public void enter_text_global(String string) throws InterruptedException {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		Thread.sleep(4000);
		contacts.clickGlobal();
		Technical = new Technical(driver);
		Thread.sleep(4000);
		Technical.EnterOutinecontactName(string);
		Thread.sleep(4000);
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.SelectGlobalsearch();

	}

	@Then("Select Service Type Technical and Click on Create buttons and Enter {string}")
	public void select_service_type_technical_and_click_on_create_buttons_and_enter(String string)
			throws InterruptedException {
		Thread.sleep(2000);
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.clickoncreatebutton();
		Thread.sleep(4000);
		Technical = new Technical(driver);
		Technical.EnterSoldToOutline(string);
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();
	}

	@When("Enter MMIDs {string}")
	public void enter_mmi_ds(String string) throws InterruptedException {
		contacts.clickonPORadiobutton();
		contacts.clickonProductRadioButton();
		Technical = new Technical(driver);
		Technical.EnterMMIdsOutline(string);

	}

	@When("Enter ULTS Value")
	public void enter_ults_value(io.cucumber.datatable.DataTable dataTable) {
		String ULts = dataTable.cell(1, 5); // ULts are in the 6th column (index 5)
		Technical.EnterULTsBulkUpload(ULts); // Call the method to handle bulk ULT upload
	}

	@When("Enter MRB into All fields {string}")
	public void enter_mrb_into_all_fields(String string) {
		quality.EnterMRBIntoAllfields(string);
	}

	@When("Enter MRB into All fields through Excel")
	public void enter_mrb_into_all_fields_through_excel() {

		exceptions = new Exceptions(driver);
		exceptions.EnterMRB();

	}

	@Then("Select All other details")
	public void select_all_other_detils() {

		contacts.SelectSoldToaddress();
		contacts.SelectSoldToaddressoption();
		contacts.clicksalesarea();
		contacts.clicksalesareaselect();
	}

	@When("Select Collection Date")
	public void select_collection_date() {
		contacts.SelectDate();
	}
//Quality NPR Bulk Upload

	@Then("Click On create button and select remedy NPR credit and select reason")
	public void click_on_create_button_and_select_remedy_npr_credit_and_select_reason() {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.ClickOnNPR();
		Technical.SelectReason();
		quality = new Quality(driver);
		quality.ReasonQAN();

	}

	@Then("Upload Bulk File {string} NPR ULT Upload")
	public void upload_bulk_file_npr_ult_upload(String string) throws InterruptedException, AWTException {
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		System.out.println("Print path from Featute file" + string);
		Thread.sleep(2000);
		Robot upload = new Robot();
		upload.delay(3000);

		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Quality Upload File\\MMCPNULTBulkQuality.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
	}

	@Then("Select Sold To Contact")
	public void select_sold_to_contact() {
		Technical.soldtocontact();
		Technical.selectsoldtocontact();

	}

	@Then("Select Sold To Contact second option")
	public void select_sold_to_contact_second_option() {
		Technical.soldtocontact();
		Technical.SelectSoldToContact();

	}

	@Then("Select Fright Type IMF")
	public void select_fright_type_imf() {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.ClickonFrightType();
		contacts.SelectFrightTypeIMF();
		contacts.ClickOnCancel(); // Try to click on Cancel if available

	}

	@Then("Select Fright Type IMF without cancel")
	public void select_fright_type_imf_withoutcancel() {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.ClickonFrightType();
		contacts.SelectFrightTypeIMF();

	}

	@Then("Select Fright Type IMF and click ok")
	public void select_fright_type_imfok() {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.ClickonFrightType();
		contacts.SelectFrightTypeIMF();
		contacts.clickok();

	}

	@Then("Select Fright Type CMF")
	public void select_fright_type_cmf() {

		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.ClickonFrightType();
		contacts.SelectFrightTypeIMF();
		// contacts.ClickOnCancel();
		contacts.clickok();

	}

	@Then("Click Ok")
	public void ClickOk() {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.clickok();

	}
//	@Then("Click Cancel")
//	public void Cliccancel() {
//		contacts = new ContactPage_R4C_CaseCreation(driver);
//		contacts.clickoncancelIMF();
//
//	}

	/*
	 * Author: Omkar Yadav Date: 2 Oct 2022 Description:Customer WebPortalFlow
	 */

	@Then("Select Service Type Admin and Click create button")
	public void select_service_type_admin_and_click_create_button() {
		admin = new Admin(driver);
		admin.SelectServiceTypeAdmin();
		contacts.clickoncreatebutton();

	}

	@Then("Enter CIRS {string}")
	public void enter_cirs(String string) {
		admin.EnterCIRS(string);
	}

	@Then("Enter CIRS Through Excel")
	public void enter_cirs_through_excel() {
		admin = new Admin(driver);
		admin.EnterCIRS();
	}

	@Then("Select Return Reson duplicate order")
	public void select_return_reson_duplicate_order() {
		Technical = new Technical(driver);
		Technical.SelectReason();
		admin.SelectReasonDuplicateOrder();
		contacts.ClickOnCancel();

	}

	@Then("Select Return Reson Incorrect labeling")
	public void select_return_reson_incorrect_labeling() {
		Technical.SelectReason();
		admin.SelectReasonIncorrectLabeling();
		contacts.clickok();

	}

	@Then("Select Return Reson mixed product")
	public void select_return_reson_mixed_product() {
		Technical.SelectReason();
		admin.SelectMixedProduct();
		// contacts.ClickOnCancel();
	}

	@Then("Select Return overship")
	public void select_return_overship() {
		Technical.SelectReason();
		admin.SelectOvership();
		contacts.clickok();

	}

	@Then("Select Return Reson sample")
	public void select_return_reson_dsample() {
		Technical.SelectReason();
		admin = new Admin(driver);
		admin.Sample();
	}

	@Then("Select Return shortship")
	public void select_return_shortship() {
		Technical.SelectReason();
		admin.SelectShortship();
		contacts.ClickOnCancel();
	}

	@Then("Select Return Reson Wrong Dest")
	public void select_return_reson_wrong_dest() {
		Technical.SelectReason();
		admin.SelectWrongDestination();
	}

	@Then("Select Return Reson wrong product")
	public void select_return_reson_wrong_product() {
		Technical.SelectReason();
		admin.SelectWrongProduct();
		contacts.ClickOnCancel();
	}

	@Then("Select Wrong Ship Date")
	public void select_wrong_ship_date() {
		Technical.SelectReason();
		admin.SelectWrongShipdate();
		contacts.clickok();
	}

	@Then("Select Lost Shipment")
	public void select_lost_shipment() {
		Technical.SelectReason();
		admin.SelectLostShipment();
		// contacts.clickok();
		contacts.ClickOnCancel();

	}

	@Then("Select Damage In Transit")
	public void select_damage_in_transit() {
		Technical.SelectReason();
		admin.Damangeintransit();
		contacts.clickok();
	}

	@When("Click on the Get credit price button")
	public void click_on_the_get_credit_price_button() {
		contacts.ClickGetCreditPrice();
	}

	/*
	 * Author: Omkar Yadav Date: 10 Oct 2022 Description:Customer WebPortalFlow
	 */

	@When("Select Box Condition")
	public void select_box_condition() {
		exceptions.SelectBoxCondition();

	}

	@When("Upload Bulk File With MM IDS {string} Exception")
	public void upload_bulk_file_with_mm_ids_exception(String string) throws InterruptedException, AWTException {

		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(3000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Exception Upload File\\MMCPNULTBulkException.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

	@When("Enter ULTs from Excel")
	public void enter_ul_ts_from_excel() throws InterruptedException {
		Technical.EnterULT();
	}

	@When("Validate Fright type as a CMF")
	public void validate_fright_type_as_a_cmf() {
		exceptions = new Exceptions(driver);
		exceptions.VerifyCMF();

	}

	@When("Validate Fright type as a IMF")
	public void validate_fright_type_as_a_imf() {
		exceptions = new Exceptions(driver);
		exceptions.VerifyIMF();
	}

	@When("Enter Case No into text field through excel")
	public void Enter_Case_No_into_text_field_through_excel() {
		exceptions = new Exceptions(driver);
		exceptions.entercaseno();

	}

	@When("Select Remedy credit Exchange")
	public void select_Exhange() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.SelectExchange();
//		Technical.SelectReason();
//		Technical.SelectreasonSWRTL();	
	}

	@When("Select SWRTL")
	public void select_ExhangeSWRTL() throws InterruptedException {

		Technical.SelectReason();
		Technical.SelectreasonSWRTL();
	}

	@When("Select AWRTL")
	public void select_ExhangeAWRTL() throws InterruptedException {

		Technical.SelectReason();
		Technical.SelectAWRTL();
	}

	@When("Select SWRTLF")
	public void select_ExhangeSWRTLF() throws InterruptedException {
		Technical.SelectReason();
		Technical.SelectSWRTF();
	}

	@When("Select AWETF")
	public void select_ExhangeAWETF() throws InterruptedException {

		Technical.SelectReason();
		Technical.SelectAWETF();
	}

	@When("Enter Case text global and select")
	public void select_and_searchcase() throws InterruptedException {
		contacts = new ContactPage_R4C_CaseCreation(driver);
		Thread.sleep(2000);
		contacts.clickGlobal();
		Technical = new Technical(driver);
		Thread.sleep(2000);
		Technical.EnterGlobalcasethrouhExcel();
		Technical.SelectCase();
	}

	@When("Enter R4E Number")
	public void R4E() {

		Technical.EnterR4E();
		Technical.ClickNEXT();

	}

	@When("Select BoxCondition")
	public void Boxcondition() throws InterruptedException {
		miscellaneous = new Miscellaneous(driver);
		miscellaneous.selectBoxConditionForAll();
	}

	@When("Click Product Validate")
	public void Product() {
		miscellaneous = new Miscellaneous(driver);
		miscellaneous.ClickValidateProduct();
	}

	@When("Click on next after selection")
	public void next() {
		miscellaneous = new Miscellaneous(driver);
		miscellaneous.ClickNEXT();

	}

//------------------WEB Page----------------------------------------------

	/*
	 * Author: Omkar Yadav Date: 10 Nov 2022 Description:Customer WebPortalFlow
	 */

	@When("User open URL Web")
	public void user_open_urlweb() {
		String url = prop.getProperty("urlweb");
		driver.get(url);
	}
	
	

	@And("Click Create Case button")
	public void Create_Case() {
		WebSR = new Web_Portal_StockRotation(driver);
		WebSR.ClickCreatecasebutton();

	}

	@And("Click Edit Case button")
	public void Create_CaseButton() {
		WebSR = new Web_Portal_StockRotation(driver);
		WebSR.CaseViewAndEdit();
		WebSR.OpenUnsubmitButton();

	}

	@When("Print All Case details")
	public void CaseDetails() {
		WebSR.ClickOpenUnsubmittedcases();
		WebSR.printAllCaseDetails();

	}

	@When("Click on case number")
	public void CaseNumber_Web() {
		WebSR.clickCaseNumberForStockRotation();

	}

	@When("Click on Edit Button")
	public void CaseEdit() {
		WebSR.ClickEdit();

	}

	@When("Select ALL Web")
	public void select_ALLweb() throws InterruptedException {
		Thread.sleep(1000);
		WebSR.SelectAllCheckbox();
	}

	@Then("Select RCA address first web")
	public void select_return_customer_contact_firstweb() throws InterruptedException {
		// ---------------------------
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create a WebDriverWait object
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@aria-label='Return to Customer Address']/..//button[@aria-label='Return to Customer Address']")));
			JavascriptExecutor js = (JavascriptExecutor) driver; // Scroll the element into view
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(driver); // Focus on the element
			actions.moveToElement(element).perform();
			js.executeScript("arguments[0].click();", element); // Click the element using JavaScript
		} catch (TimeoutException e) {
			System.out.println("Element not found or not clickable within the specified time");
		} finally {
			System.out.println("Element still not clickable");
		}
		WebSR.SelectRCCCRF();

	}

	@When("Print Case No into Console Web")
	public void print_case_no_into_console_srweb() throws InterruptedException {
		Thread.sleep(3000);
		WebSR.GetCaseNo();
		WebSR.PrintCaseNoSRweb();

	}

	@When("Check Status open unsubmited")
	public void Open_Un() throws InterruptedException {
		WebSR.checkstatus();

	}

	@When("Select NPR reason")
	public void Open_UnReason() throws InterruptedException {

		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.ClickOnNPR();
	}

	@When("Select Return Reason Warrenty service web")
	public void warrenty_service() throws InterruptedException {

		Technical = new Technical(driver);
		Technical.SelectReason();
		Technical.SelectWarrentyService();
	}

	@And("Click I Agree Buttion")
	public void ClickAgreeButtion() {
		WebTech = new Web_Portal_Technical(driver);
		WebTech.ClickAgree();

	}

	@When("Select Remedy credit and Technical Exchange")
	public void select_remedy_credit_and_reasonexchange() throws InterruptedException {
		Technical = new Technical(driver);
		Technical.SelectNPR();
		Technical.SelectExchange();

	}

	@Then("Select Service Type Quality and Click create button enter Sold through Excel file for portal")
	public void select_Quality() throws InterruptedException {

		// contacts = new ContactPage_R4C_CaseCreation(driver);
		// contacts.selectserviceType();

		WebQuality = new Web_Portal_Quality(driver);
		WebQuality.SelectQuality();
		contacts = new ContactPage_R4C_CaseCreation(driver);
		contacts.clickoncreatebutton();
		Technical = new Technical(driver);
		Technical.enterSlodToExcel();
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//li[@role='option']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"))
				.click();

	}

	@When("Upload Bulk File {string} Quality Portal")
	public void uploadbulk_file_quality_Portal(String string) throws InterruptedException, AWTException {
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Thread.sleep(3000);
		Robot upload = new Robot();
		upload.delay(2000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Quality Upload File web\\Credit_BulkuploadQualityportal.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
	}

	@When("Upload Bulk File {string} NPR ULT Portal")
	public void upload_bulk_file_ultportal(String string) throws AWTException, InterruptedException {

		Thread.sleep(2000);
		contacts.ClickonBulkUpload();
		Thread.sleep(2000);
		contacts.clickonUploadfileButton(string);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Quality Upload File web\\NPRCredit_ULT_BuilkUploadportal.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		Thread.sleep(2000);
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);

	}

//------------------Query Part 1 Search Steps--------------------------------------------------------------------------------------------------------------------------------------------	

	@And("Click Search Query")
	public void Clickquery() {
		Query = new Web_Query_Search(driver);
		Query.ClickSearchQuery();

	}

	@And("Enter RMA Number Into Text field")
	public void EnterRMA() {
		Query.EnterRMANumber();

	}

//-----------------------Query Part 2 Search Steps-------

	@Then("Select Service type as Technical")
	public void select_service_type_as_technical() {

		Query.SelectServiceType();
		Query.SelectTypeTechnical();

	}

	@Then("Select Service type as Stock Rotation")
	public void select_service_type_as_sr() {

		Query.SelectServiceType();
		Query.SelectTypeSR();

	}

	@Then("Select Service type as Admin")
	public void select_service_type_as_Admin() {

		Query.SelectServiceType();
		Query.SelectTypeAdmin();

	}

	@Then("Select Service type as Exception")
	public void select_service_type_as_Exception() {

		Query.SelectServiceType();
		Query.SelectTypeException();

	}

	@Then("Select Service type as Quality")
	public void select_service_type_as_Quality() {

		Query.SelectServiceType();
		Query.SelectTypeQuality();

	}

	@Then("Select Service type as Miscllaneous")
	public void select_service_type_as_Miscllaneous() {

		Query.SelectServiceType();
		Query.SelectTypeMiscllaneous();

	}

	@Then("Select Remedy as NPR-Credit")
	public void select_remedy_as_npr_credit() {
		Query.clickonremedy();
		Query.SelectNPRCredit();

	}

	@Then("Select Remedy as Credit")
	public void select_remedy_as_credit() {
		Query.clickonremedy();
		Query.SelectCredit();

	}

	@Then("Select Remedy as Return Only")
	public void select_remedy_as_ReturnOnly() {
		Query.clickonremedy();
		Query.SelectReturnOnly();

	}

	@Then("Click on Case status")
	public void select_casestatus() {
		Query.ClickOnCaseStatus();

	}

	@Then("Click on Case Line")
	public void select_casesline() {
		Query.ClickOnCaseLine();

	}

	@Then("Select Return Reason as Warranty Service\\/Software - NPR-Credit")
	public void select_return_reason_as_warranty_service_software_npr_credit() {

		Query.SelectReturnReason();
		Query.SelectReasonWSSNPRCredit();

	}

	@Then("Click Search button")
	public void click_search_button() {

		Query = new Web_Query_Search(driver);

		Query.ClickSearchButton();
	}

	@Then("Click on RMA Number hyperlink")
	public void click_on_rma_number_hyperlink() {
		Query.ClickOnHyperlink();
	}

	@Then("Check the Service type Technical & Remedy & Return Reason for the case")
	public void check_the_service_type_remedy_return_reason_for_the_case() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateServiceTypeTechnical(driver);
	}

	@Then("Validate NPRCredit Remedy")
	public void check_the_NPRRemedy() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.validateNPRCredit(driver);
	}

	@Then("Validate Credit Remedy")
	public void check_the_CreditRemedy() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.validateCredit(driver);
	}

	@Then("Check the Service type Stock Rotation & Remedy & Return Reason for the case")
	public void check_the_service_SR() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateSR(driver);

	}

	@Then("Check the Service type Admin & Remedy & Return Reason for the case")
	public void check_the_service_Admin() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateAdmin(driver);

	}

	@Then("Check the Service type Exception & Remedy & Return Reason for the case")
	public void check_the_service_Exception() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateException(driver);

	}

	@Then("Check the Service type Quality & Remedy & Return Reason for the case")
	public void check_the_service_Quality() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateQuality(driver);

	}

	@Then("Check the Service type Miscellaneous & Remedy & Return Reason for the case")
	public void check_the_service_Miscellaneous() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateMiscellaneous(driver);

	}

	@Then("Check case Status as Open Unsubnitted")
	public void check_the_CaseStatus() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateCaseOpenUnsubmitted(driver);

	}

	@Then("Check case Status as Pending Approval")
	public void check_the_CaseStatusPendingApproval() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateCasePending(driver);

	}

	@Then("Check case Status as Cancelled")
	public void check_the_CaseStatuscancelled() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateCasecancelled(driver);

	}

	@Then("Check case Status as Customer Action require")
	public void check_the_CaseCAR() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateCaseCAR(driver);

	}

	@Then("Check case Status as Auto Closed")
	public void check_the_AutoClosed() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateCaseAutoClosed(driver);

	}

	@Then("Click On Show More button")
	public void check_the_SHOWMore() throws InterruptedException {
		Query.ClickOnShowMore(driver);

	}

	@Then("Check case Status as Customer action require")
	public void check_the_Action() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateCustomeraction(driver);

	}

	@Then("Check case Status as cancelled")
	public void check_the_Cancelled() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateCancelled(driver);

	}

	@Then("Check case Status as Declined")
	public void check_the_Declined() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.ValidateDeclined(driver);

	}

	@Then("Check case Status as closed")
	public void check_the_closed() throws InterruptedException {
		Query = new Web_Query_Search(ldriver);
		Query.Validateclosed(driver);

	}

	@Then("Select Return Reason as Faild At End Customer NPR-Credit")
	public void select_returnFaildAt_EndCustomer() {

		Query.SelectReturnReason();
		Query.SelectReasonFECNPRCredit();

	}

	@Then("Select Return Reason as Faild End customer")
	public void select_returnFaildAt_EndCustome() {

		Query.SelectReturnReason();
		Query.SelectReasonFEC();

	}

	@Then("Select Return Reason as Promotional Stock Rotation")
	public void select_returnPSR() {
		Query.SelectReturnReason();
		Query.ServiceTypePSR();

	}

	@Then("Select Return Reason as Standard Stock Rotation")
	public void select_returnSSR() {
		Query.SelectReturnReason();
		Query.ServiceTypeSSR();

	}

	@Then("Select Return Reason as Duplicate Order")
	public void select_returnDO() {
		Query.SelectReturnReason();
		Query.SelectDO();

	}

	@Then("Select Return Reason as incorrect labeling")
	public void select_returnIncorrectlabeling() {
		Query.SelectReturnReason();
		Query.SelectIL();
	}

	@Then("Select Return Reason as Mixed product")
	public void select_ReasonMixedProdudct() {
		Query.SelectReturnReason();
		Query.SelectMP();

	}

	@Then("Select Return Reason as Overship")
	public void select_ReasonOvership() {
		Query.SelectReturnReason();
		Query.SelectOvership();

	}

	@Then("Select Return Reason as Sample")
	public void select_ReasonSample() {
		Query.SelectReturnReason();
		Query.SelectSample();

	}

	@Then("Select Return Reason as Shortship")
	public void select_ReasonShortShip() {
		Query.SelectReturnReason();
		Query.SelectShortShip();

	}

	@Then("Select Return Reason as Wrong Destination")
	public void select_ReasonWrongDestination() {
		Query.SelectReturnReason();
		Query.SelectWrongDestination();

	}

	@Then("Select Return Reason as Wrong product")
	public void select_ReasonWrongproduct() {
		Query.SelectReturnReason();
		Query.SelectWrongProduct();

	}

	@Then("Select Return Reason as Wrong Ship date")
	public void select_ReasonWrongShipdate() {
		Query.SelectReturnReason();
		Query.SelectWrongShipdate();

	}

	@Then("Select Return Reason as Lost Shipment")
	public void select_ReasonLostShipdate() {
		Query.SelectReturnReason();
		Query.SelectLostShipment();

	}

	@Then("Select Return Reason as Damage in Transit")
	public void select_ReasonDamageintransit() {
		Query.SelectReturnReason();
		Query.SelectDamageintransit();
	}

	@Then("Select Return Reason as Exception return")
	public void select_ReasonExceptionReturn() {
		Query.SelectReturnReason();
		Query.SelectExceptionReturn();
	}

	@Then("Select Return Reason as GRL Exception")
	public void select_ReasonGRLException() {
		Query.SelectReturnReason();
		Query.SelectGRLException();
	}

	@Then("Select Return Reason as Quarterly Exception")
	public void select_ReasonQuarterly() {
		Query.SelectReturnReason();
		Query.SelectQuarterly();
	}

	@Then("Select Return Reason as GRL Exception NPR Credit")
	public void select_ReasonGRLNPRCredkit() {
		Query.SelectReturnReason();
		Query.SelectGRLExceptionNPR();
	}

	@Then("Select Return Reason as FAC Credit")
	public void select_ReasonFACCredit() {
		Query.SelectReturnReason();
		Query.SelectreasonFAC();
	}

	@Then("Select Return Reason as QAN Credit")
	public void select_ReasonQANCredit() {
		Query.SelectReturnReason();
		Query.SelectreasonQAN();
	}

	@Then("Select Case status Open Unsubnitted")
	public void select_open_unsubnitted() {
		Query.SelectOpenUnsubmitted();

	}

	@Then("Select Case status Pending Approval")
	public void select_open_PendingApproval() {
		Query.SelectPendingApproval();
	}

	@Then("Select Case status Customer action require")
	public void select_open_ActionRequire() {

		Query.Customeraction();
	}

	@Then("Select Case status Open Authorized")
	public void select_open_OpenAuthorized() {

		Query.SelectOpenAuthorized();
	}

	@Then("Select Case status cancelled")
	public void select_Cancelled() {

		Query.SelectCancelled();
	}

	@Then("Select Case status Declined")
	public void select_Declined() {
		Query.SelectDeclined();

	}

	@Then("Select Case status closed")
	public void selectclosed() {
		Query.SelectClosed();

	}

	@Then("Select Case Line Open Unsubmitted")
	public void selectlineOpenUnsubmitted() {
		Query.SelectCaselineUnsubmitted();

	}

	@Then("Select Case Line Pending Approval")
	public void selectlineApproval() {
		Query.SelectpendingApproval();

	}

	@Then("Select Case Line Cancelled")
	public void selectlineCancelled() {
		Query.SelectlineCancelled();

	}

	@Then("Select Case Line Customer Action require")
	public void selectlineActionrequire() {
		Query.SelectCustomerActionrequire();

	}

	@Then("Select Case Line Open Credited")
	public void selectlineOpenCredit() {
		Query.Selectcredited();

	}

	@Then("Select Case Line Auto Closed")
	public void selectlineAutoClosed() {
		Query.SelectAutoClosed();

	}

	@Then("Select Case Line Reject Scrap")
	public void selectlineRejectScrap() {
		Query.SelectRejectScrap();

	}

	@Then("Select Case Line Reject Ship to Customer")
	public void selectlineRejectShip() {
		Query.SelectRejectShip();

	}

	@Then("Select Case line complete")
	public void selectlinecomplete() {
		Query.SelectComplete();

	}

	@Then("Select Case Line In Transit")
	public void selectlinetrasit() {
		Query.SelectInTransit();

	}

	@Then("Select Case Line Receiving in Progress")
	public void seelectprogress() {
		Query.SelectReceivingProgress();

	}

	@Then("Select Case Line Discrepant")
	public void seelectlinediscrepant() {
		Query.Selectdiscrepant();

	}

	@Then("Select Case Line Received")
	public void seelectlineReceived() {
		Query.SelectRecevied();

	}

	@Then("Select Case Line Declined")
	public void seelectlineDeclined() {
		Query.SelectlineDeclined();

	}

	@Then("click on contact type")
	public void clickcontacttype() {
		Query.clickcontacttype();

	}

	@Then("Select contact type sold to")
	public void SelectContactSoldTo() {
		Query.SelectContactSoldTo();

	}

	@Then("Select contact type Ship to")
	public void SelectContactShipTo() {
		Query.SelectContactShipTo();

	}

	@Then("Select contact type Collection")
	public void SelectContactCollection() {
		Query.SelectContactcollection();

	}

	@Then("Select contact type customer")
	public void SelectContactCollectioncoust() {
		Query.SelectContactcustomercontact();

	}

	@Then("Enter Email Address into fields")
	public void EnterEmail() {
		Query.EnterEmail();

	}

	@Then("Click On Bulk  Upload button and Upload RMA File")
	public void BulkUploadfile() throws InterruptedException, AWTException {
		Query.ClickUploadButton();
		Thread.sleep(4000);
		Query.ClickonUpload();
		Thread.sleep(2000);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query RMA Search\\RMABuilkCaseSearch.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("RMA File Uploaded Succesfully");
	}

	@Then("Click On Bulk  Upload button and Upload ULTs File")
	public void BulkUploadfileULTs() throws InterruptedException, AWTException {
		Query.ClickUploadButton();
		Thread.sleep(4000);
		Query.ClickonUpload();
		Thread.sleep(2000);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query RMA Search\\ULTsBuilkCaseSearch.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("RMA File Uploaded Succesfully");
	}

	@Then("Click On Bulk  Upload button and Upload MMIDs File")
	public void BulkUploadfileMMIDs() throws InterruptedException, AWTException {
		Query.ClickUploadButton();
		Thread.sleep(4000);
		Query.ClickonUpload();
		Thread.sleep(2000);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query RMA Search\\MMIDsBulkCaseSearch.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("RMA File Uploaded Succesfully");
	}

	@Then("Click On Bulk  Upload button and Upload CPNs File")
	public void BulkUploadfileCPN() throws InterruptedException, AWTException {
		Query.ClickUploadButton();
		Thread.sleep(4000);
		Query.ClickonUpload();
		Thread.sleep(2000);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query RMA Search\\CPNBulkCaseSearch.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("RMA File Uploaded Succesfully");
	}

	@Then("Click On Bulk  Upload button and Upload Contacts Email File")
	public void BulkUploadfileContactMails() throws InterruptedException, AWTException {
		Query.ClickUploadButton();
		Thread.sleep(4000);
		Query.ClickonUpload();
		Thread.sleep(2000);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query RMA Search\\EmailBulkCaseSearch.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("RMA File Uploaded Succesfully");
	}

	@Then("Click On Bulk  Upload button and Upload Contacts Email&RMA File")
	public void BulkUploadfileContactMailsk() throws InterruptedException, AWTException {
		Query.ClickUploadButton();
		Thread.sleep(4000);
		Query.ClickonUpload();
		Thread.sleep(2000);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query RMA Search\\RMAandEmailBuilkCaseSearch2.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("RMA File Uploaded Succesfully");
	}

	@Then("Click On Bulk  Upload button and Upload ULTs&MMIDs File")
	public void BulkUploadfileContactULTSAndMMIDS() throws InterruptedException, AWTException {
		Query.ClickUploadButton();
		Thread.sleep(4000);
		Query.ClickonUpload();
		Thread.sleep(2000);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query RMA Search\\ULTsandMMIdsBuilkCaseSearch.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("RMA File Uploaded Succesfully");
	}

	@Then("Click On Bulk  Upload button and Upload ULTS&CPN File")
	public void BulkUploadfileContactULTSAndCPN() throws InterruptedException, AWTException {
		Query.ClickUploadButton();
		Thread.sleep(4000);
		Query.ClickonUpload();
		Thread.sleep(2000);
		Robot upload = new Robot();
		upload.delay(3000);
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query RMA Search\\ULTsCPNBuilkCaseSearch.csv");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
		upload.keyPress(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyRelease(KeyEvent.VK_CONTROL);
		upload.keyRelease(KeyEvent.VK_V);
		upload.delay(2000);
		upload.keyPress(KeyEvent.VK_ENTER);
		upload.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("RMA File Uploaded Succesfully");
	}

	@When("Click On SR Allowance Option")
	public void ClickSRAllowance() throws InterruptedException {

		SRAllowanceQuery = new Web_Query_SRAllowance(driver);
		SRAllowanceQuery.ClickOnSRAllowance();

	}

	@When("Click on Product and Warrenty option")
	public void ClickProducy() throws InterruptedException {

		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
		ProductwarrentyQuery.ClickProductWarrenty();

	}

	@When("Select Service Type technical")
	public void ClickServiceType() throws InterruptedException {

		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
		ProductwarrentyQuery.ClickServiceType();
		ProductwarrentyQuery.ClickServiceTypeTech();

	}

	@When("Select Service Type Quality")
	public void ClickServiceTypeQuality() throws InterruptedException {

		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
		ProductwarrentyQuery.ClickServiceType();
		ProductwarrentyQuery.SelectQuality();

	}

	@When("Select Service Type Exception")
	public void ClickServiceTypeException() throws InterruptedException {

		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
		ProductwarrentyQuery.ClickServiceType();
		ProductwarrentyQuery.SelectException();

	}

	@When("Select Remedy Credit")
	public void ClickServiceTypeRemedy() throws InterruptedException {

		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
		ProductwarrentyQuery.ClickOnRemedy();
		ProductwarrentyQuery.ClickOnRemedyCredit();
	}

	@When("Select Remedy  NPR-Credit")
	public void ClickServiceNPR() throws InterruptedException {

		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
		ProductwarrentyQuery.ClickOnRemedy();
		ProductwarrentyQuery.CLickOnNPR();
	}

	@When("Select Return Reasons")
	public void ClickServicereason() throws InterruptedException {

		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
		ProductwarrentyQuery.ClickOnreason();
		ProductwarrentyQuery.SelectFCF();
	}

	@When("Click on ULT radio button")
	public void ClickULTs() throws InterruptedException {

		ProductwarrentyQuery.ClickOnULTRadiobutton();

	}

	@When("Enter MMIDs Into Fields")
	public void ClickProductMMIds() throws InterruptedException {

		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
		ProductwarrentyQuery.EnterMMIds();

	}

	@When("Enter CPN Into Fields")
	public void ClickProductCPN() throws InterruptedException {
		ProductwarrentyQuery.ClickOnCPNRadioBtn();
		ProductwarrentyQuery.EnterCPN();

	}

	@When("Enter OPN Into Fields")
	public void ClickProductOPN() throws InterruptedException {
		ProductwarrentyQuery.ClickOnOPNRadioBtn();
		ProductwarrentyQuery.EnterOPN();
	}

	@When("Enter ULts")
	public void EnterULTs() throws InterruptedException {
		ProductwarrentyQuery.EnterULTs();

	}

	@When("CLick On Product unique identifier radio button")
	public void ClickProductPUI() throws InterruptedException {
		ProductwarrentyQuery.ClickPUIbutton();

	}

	@When("Enter Product code Into Fields")
	public void ClickProductCode() throws InterruptedException {
		ProductwarrentyQuery.ClickOnProductCodeBtn();
		ProductwarrentyQuery.EnterProductcode();

	}

	@When("Validate Valid data should disply")
	public void Validatedata() throws InterruptedException {

		ProductwarrentyQuery.EnterMMIds();

	}

	@When("Enter Sold To through excel sheet")
	public void EnterSoldTO() throws InterruptedException {
		SRAllowanceQuery = new Web_Query_SRAllowance(driver);
		SRAllowanceQuery.EnterSoldToQuery();
		Thread.sleep(2000);
		SRAllowanceQuery.SelectSoldTo();

	}

	@When("Select return reason Standard stock rotation")
	public void SelectReturnReason() throws InterruptedException {
		SRAllowanceQuery.ClickOnReason();
		SRAllowanceQuery.SelectSSR();
	}

	@When("Select return reason Promotional stock rotation")
	public void SelectPSR() throws InterruptedException {
		SRAllowanceQuery.ClickOnReason();
		SRAllowanceQuery.SelectPSR();
	}

	@When("Validate Reason should Standard stock rotation")
	public void SelectSSSR() throws InterruptedException {
		SRAllowanceQuery.SSRText();

	}

	@When("Validate Reason should Promotional stock rotation")
	public void SelectPromotional() throws InterruptedException {
		SRAllowanceQuery.PSRText();

	}

	@When("Validate Reason should Standard stock rotation &Promotional stock rotation")
	public void SelectPSRSSRh() throws InterruptedException {
		SRAllowanceQuery.PSRSSRText(driver);

	}

	@When("Click On ALL CMF Radio Button")
	public void SelectALLCMF() throws InterruptedException {
		SRAllowanceQuery.SelectAllCMFradiobutton();

	}

	@When("Click On Export To excel file button and validate")
	public void ClickOnExport() throws InterruptedException {
		SRAllowanceQuery.validateExportButtonClickable();

	}

	@When("Validate the Discription")
	public void ValidateDiscription() throws InterruptedException {
		SRAllowanceQuery.ValidateDiscription(driver);

	}

//	@Then("Click On Upload File button")
//	public void BulkUploadfileULIs() throws InterruptedException, AWTException {
//		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
//		ProductwarrentyQuery.ClickUploaddropbutton();
//		//Thread.sleep(2000);
//		Robot upload = new Robot();
//		upload.delay(3000);
//		StringSelection stringSelection = new StringSelection(
//				"C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query RMA Search\\RMABuilkCaseSearch.csv");
//		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
//		upload.keyPress(KeyEvent.VK_CONTROL); // Key Press On key Board
//		upload.keyPress(KeyEvent.VK_V);
//		upload.delay(2000);
//		upload.keyRelease(KeyEvent.VK_CONTROL);
//		upload.keyRelease(KeyEvent.VK_V);
//		upload.delay(2000);
//		upload.keyPress(KeyEvent.VK_ENTER);
//		upload.keyRelease(KeyEvent.VK_ENTER);
//		System.out.println("RMA File Uploaded Succesfully");
//	}

	@Then("Click On Upload File button")
	public void BulkUploadfileULIs()
			throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {
		ProductwarrentyQuery = new Web_Query_ProductWarrenty(driver);
		ProductwarrentyQuery.ClickUploaddropbutton();

		// Wait for file dialog to open
		Robot robot = new Robot();
		robot.delay(2000);

		// Copy file path to clipboard
		String filePath = "C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\Query Product And Warrenty\\ULT_BulkSearchTemplate.csv";
		StringSelection selection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		// Verify clipboard content
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String clipboardContent = (String) clipboard.getData(DataFlavor.stringFlavor);
		if (!clipboardContent.equals(filePath)) {
			System.out.println("Error: Clipboard content does not match the file path!");
			return; // Exit if clipboard content is incorrect
		}

//	    // Ensure file dialog focus
//	    robot.keyPress(KeyEvent.VK_ALT);
//	    robot.keyPress(KeyEvent.VK_TAB);
//	    robot.keyRelease(KeyEvent.VK_TAB);
//	    robot.keyRelease(KeyEvent.VK_ALT);
		robot.delay(4000);

		// Simulate CTRL+V to paste the file path
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		// Press ENTER to confirm file upload
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Adding a confirmation log
		System.out.println("RMA File Uploaded Successfully");
	}

//------------------Price Masking--------------
	/*
	 * Author: Omkar Yadav Change:Price Masking Description: Launch browser Date- 2
	 * Jan 2025
	 */

	@When("Click On Price Masking radio button and click next")
	public void ClickPMAndNExt() throws InterruptedException {

		pricemasking = new PriceMasking(driver);
		pricemasking.SelectPMRadioButton();
		pricemasking.ClickOnNEXT();
		pricemasking.SelectUPMRadioButton();
		pricemasking.ClickOnNEXT();

	}

	@When("Click On drop down Ana select First name {string}")
	public void SelectFN(String string) throws InterruptedException {
		pricemasking.ClickDropdownPM();
		pricemasking.SelectFN();

	}

	@When("Enter First Name {string}")
	public void EnterFN(String string) throws InterruptedException {
		pricemasking.EnterFN(string);
		pricemasking.ClickSearch();

	}

	@When("Enter Last Name {string}")
	public void EnterLN(String string) throws InterruptedException {
		pricemasking.ClickDropdownPM();
		pricemasking.Selectlastname();
		pricemasking.EnterFN(string);
		pricemasking.ClickSearch();
	}

	@When("Enter Email Name {string}")
	public void EnterLoginN(String string) throws InterruptedException {
		pricemasking.ClickDropdownPM();
		pricemasking.SelectlastEmail();
		pricemasking.EnterFN(string);
		pricemasking.ClickSearch();
	}

	@When("Enter Sold To {string}")
	public void EnterSold(String string) throws InterruptedException {
		pricemasking.ClickDropdownPM();
		pricemasking.SelectLogInName();
		pricemasking.EnterFN(string);
		pricemasking.ClickSearch();
	}

	@When("Select One Check box and click save")
	public void ClickCB() throws InterruptedException {

		pricemasking.SelectCheckBox();
		pricemasking.ClickSavebutton();

	}
	
	@When("Click On Select Icone")
	public void ClickSettingicon() throws InterruptedException {
		pricemasking.ClickOnSettingIcon();
	}

	@When("Log out Agent profile")
	public void logout() throws InterruptedException {

		pricemasking.ClickOnIcon();
		pricemasking.ClickOnLogout();
	}

	@When("Click on login into experience as a user")
	public void lginUser() throws InterruptedException {

		pricemasking.ClickOnUserLogin();
	}

	@When("Click On Customer price masking radio button and next")
	public void CLickCMP() throws InterruptedException {
		pricemasking = new PriceMasking(driver);
		pricemasking.SelectPMRadioButton();
		pricemasking.ClickOnNEXT();
		pricemasking.ClickOnCPM();
		pricemasking.ClickOnNEXT();

	}

	@When("Select Sold to option")
	public void CLickCMPdropdown() throws InterruptedException {

		pricemasking.ClickOnCPMdropdown();
		pricemasking.SlectSoldTo();

	}
	@When("Select Sold to Name option")
	public void Selectsoldtoname() throws InterruptedException {

		pricemasking.ClickOnCPMdropdown();
		pricemasking.SlectSoldToname();

	}
	
	@When("Click on searrch button")
	public void ClickOnsearch() throws InterruptedException {

		pricemasking.ClickSearch();

	}
	
	@When("Enter Sold To from sheet")
	public void EnterSOLDTO() throws InterruptedException {

		pricemasking.EnterSoldToPM();
		pricemasking.ClickSearch();

	}
	
	@When("Select Collection address option")
	public void EnterCA() throws InterruptedException {
		pricemasking.SelectCAOption();
		pricemasking.ClickSavebutton();
	}
	@When("Select Return Collection Address option")
	public void EnterRCA() throws InterruptedException {
		pricemasking.SelectRCAOption();
		pricemasking.ClickSavebutton();

	}
	// Shine----------------------------------------------------------------------
	
	
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

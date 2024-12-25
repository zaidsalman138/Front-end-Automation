package TestCases;

import org.testng.annotations.Test;

import PageObjects.LandingPage;
import PageObjects.LoginPage;
import PageObjects.Products;
import Utilities.BaseClass;
import Utilities.ExcelUtils;
import Utilities.PropertyReader;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrmTest extends BaseClass {
	
	@BeforeClass
	public void setup() {
	    // Automatically download and set up GeckoDriver
	    WebDriverManager.firefoxdriver().setup();
	    driver = new FirefoxDriver();
	    driver.manage().window().maximize();
	}

	 @Test
	 public void loginCRM() throws InterruptedException {
		 LoginPage loginPage = new LoginPage(driver);
	      
	        PropertyReader propertyReader = new PropertyReader();
	        String testDataPath = propertyReader.getProperty("testDataPath");
	        
	        String username = propertyReader.getProperty("username");
	        String password = propertyReader.getProperty("password");
	        
	        // Navigate and wait for page load
	        driver.get(propertyReader.getProperty("crmUrl"));
	        waitForPageLoad();
	        
	        // Perform login
	        loginPage.loginAs(username, password);
	        waitForPageLoad();  // Wait after login
	        
	        String expectedCrmUrl = propertyReader.getProperty("expectedCrmUrl");
	        Thread.sleep(5000);
	        String actualCrmUrl = driver.getCurrentUrl();
	        Assert.assertEquals(actualCrmUrl, expectedCrmUrl, "The user is not redirected to the dashboard after login.");
	        
	 }
	 
	 
	/* @Test(priority = 2, description = "Wait, hover, and click Payment module")

	    public void testPaymentModuleNavigation() {
	        WebDriverWait wait = new WebDriverWait(driver, 50); // Explicit wait with 10-second timeout
	        Actions actions = new Actions(driver);

	        // Step 1: Wait and Click on the App Navigator
	        WebElement appNavigator = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='appnavigator']//div[@class='row app-navigator']")));
	        appNavigator.click();

	        // Step 2: Wait and Hover over the Payment Modules Dropdown
	        WebElement paymentModuleDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='PAYMENT_modules_dropdownMenu']//div[@class='menu-items-wrapper app-menu-items-wrapper']")));
	        actions.moveToElement(paymentModuleDropdown).perform(); // Hover over the dropdown
	        

	        // Step 3: Wait and Click the Payment Module
	        WebElement paymentModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Payment']")));
	        paymentModule.click();
	        
	    }*/

	 
	 @Test(priority = 2, description = "Wait, hover, and click Payment module")
	 public void testPaymentModuleNavigation() {
	     // Create an instance of the PaymentPage
	     LandingPage paymentPage = new LandingPage(driver);

	     // Perform actions using the Page Object
	     paymentPage.clickAppNavigator();
	     paymentPage.hoverOverPaymentModuleDropdown();
	     paymentPage.clickPaymentModule();
	     
	     //Validate buttons are populating
	     paymentPage.verifyAddRecordButton();
	     paymentPage.verifySearchButton();
	     paymentPage.verifyCustomizeButton();
	     
	     //Verify the page title is Payment
	     paymentPage.verifyPageTitle("Payment");
	     
	 }
	 
	 //Validate Products page
	 @Test(priority = 3, description = "Navigate to Products page and validate elements")
	 public void testProductsPageNavigation() {
	     // Create an instance of the Products Page Object
	     Products productsPage = new Products(driver);

	     // Step 1: Click App Navigator
	     productsPage.clickAppNavigator();
	     productsPage.hoverOverSalesDropdown();
	     productsPage.clickProductsOption();
	     
	   //Verify the page title is Payment
	     productsPage.verifyPageTitle("Products");
	     
	  // Validate Buttons
	     productsPage.verifyDropdownButton(); // Validate Dropdown Button
	     productsPage.verifySearchButton();  // Validate Search Button
	 }
	 
	 
	    @AfterClass
	    public void tearDown() {
	        // Quit the browser
	        if (driver != null) {
	            driver.quit();
	            System.out.println("Browser closed successfully.");
	        }
	    }
	

}

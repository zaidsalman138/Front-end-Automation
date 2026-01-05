package TestCases;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.BulkOperation;
import PageObjects.InfraServiceConfiguration;
import PageObjects.LoginPage;
import PageObjects.SearchBill;
import PageObjects.SearchCustomerAccount;
import PageObjects.SearchInventory;
import Utilities.BaseClass;
import Utilities.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrmTest extends BaseClass {
	
	@BeforeMethod
    public void setup() {
        // Automatically download and set up GeckoDriver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // Perform login before each test
        LoginPage loginPage = new LoginPage(driver);
        PropertyReader propertyReader = new PropertyReader();

        driver.get(propertyReader.getProperty("brmUrl"));
        waitForPageLoad(driver);
        loginPage.loginAs(driver, propertyReader.getProperty("username"), propertyReader.getProperty("password"));
        waitForPageLoad(driver);

        String expectedBrmUrl = "http://dbrmsit.one.al/dbrm/om/";
        String actualBrmUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                actualBrmUrl.contains(expectedBrmUrl),
                "The user is not redirected to the dashboard after login. Current URL: " + actualBrmUrl
        );
    }

	 @Test
	 public void loginCRM() throws InterruptedException {
		 LoginPage loginPage = new LoginPage(driver);
	      
	        PropertyReader propertyReader = new PropertyReader();
	        String testDataPath = propertyReader.getProperty("testDataPath");
	        
	        String username = propertyReader.getProperty("username");
	        String password = propertyReader.getProperty("password");
	        
	        // Navigate and wait for page load
	        driver.get(propertyReader.getProperty("brmUrl"));
	        waitForPageLoad(driver);
	                
	        // Perform login
	        loginPage.loginAs(driver,username, password);
	        waitForPageLoad(driver);  // Wait after login
	        
	        String expectedBrmUrl = "http://dbrmsit.one.al/dbrm/om/";
	        Thread.sleep(5000);
	        String actualBrmUrl = driver.getCurrentUrl();
	        Assert.assertTrue(
	        	    actualBrmUrl.contains(expectedBrmUrl),
	        	    "The user is not redirected to the dashboard after login. Current URL: " + actualBrmUrl
	        	);
	 }
	
	 
	 	@Test(priority = 2, description = "Verify Search Customer Account functionality")
	    public void testSearchCustomerAccount()  {
		 	SearchCustomerAccount searchCustomerAccount = new SearchCustomerAccount(driver);
	        // Perform the actions using the SearchCustomerAccount page object
	        searchCustomerAccount.hoverOverAccountMenu();
	        searchCustomerAccount.clickSearchCustomerAccountOption();
	        searchCustomerAccount.switchToIframe();
	        searchCustomerAccount.assertSearchButtonPresence();
	        searchCustomerAccount.assertResetButtonPresence();
	        searchCustomerAccount.validateResetFieldPresence();
	        searchCustomerAccount.clickDepositTab();
	        searchCustomerAccount.assertSearchTypeDropdownPresence();
	        searchCustomerAccount.selectAccountNumberSearchType();
	        searchCustomerAccount.enterParameterValue("CA0000001902");
	        searchCustomerAccount.clickSearchButton2();
	        searchCustomerAccount.validateSearchResult("CA0000001902");
	        searchCustomerAccount.clickAccountLink();
	        searchCustomerAccount.assertNameCellPresence();
	        searchCustomerAccount.assertAccountCapPresence();
	        searchCustomerAccount.validateHistoryCapPresence();
	        
	        
	    } 
	 
	 
	  @Test(priority = 3, description = "Test Search Bill functionality")
	    public void testSearchBill() throws Exception  {
		 SearchBill searchBill = new SearchBill(driver);

		    // Perform the actions using the SearchBill page object
		    searchBill.hoverOverBillingMenu();
		    searchBill.clickSearchBillOption();
		    searchBill.switchToBillingFrame(); // Ensure the correct frame is active
		    searchBill.assertSearchBillsTextPresence(); // Validate element presence
		    searchBill.validateBillingCycleField(); //Validate Billing Cycle populates
		    searchBill.validateBillNumberField(); //Validate Bill Number field
		    searchBill.validateAccountNumberField();
		    searchBill.validateElementsPresence(); //Validate all relevant elements are present
		    
	    }
	   
	 
	 
	@Test(priority = 4, description = "Test Infra Service Configuration functionality")
	    public void testInfraServiceConfiguration() throws Exception  {
	        InfraServiceConfiguration infraServiceConfig = new InfraServiceConfiguration(driver);

	        // Perform the actions using the InfraServiceConfiguration page object
	        infraServiceConfig.hoverOverInfraToolsMenu();
	        infraServiceConfig.hoverOverServiceManagementMenu();
	        infraServiceConfig.clickServiceConfigurationOption();
	        infraServiceConfig.switchToIframe();
	        infraServiceConfig.validateSearchButtonPresence();
	        infraServiceConfig.validateResetButtonPresence();	        
	        infraServiceConfig.validateElementsPresence();
	        infraServiceConfig.clickModuleDropdown();
	        infraServiceConfig.selectBillingProcess();
	        infraServiceConfig.clickSearchButton();
	        infraServiceConfig.validateSearchResultTablePresence();
	        infraServiceConfig.clickServiceConfigurationTab();
	        infraServiceConfig.validatePanelsPresence();
	        
	    }
	 
	
	@Test(priority = 5, description = "Test Bulk Operation functionality")
    public void testBulkOperation() throws Exception {
        BulkOperation bulkOperation = new BulkOperation(driver);

        // Perform the actions using the BulkOperation page object
        bulkOperation.hoverOverInfraToolsMenu();
        bulkOperation.hoverOverBulkOperationsMenu();
        bulkOperation.clickBatchManagementOption();
        bulkOperation.switchToIframe();
        bulkOperation.validateSearchButtonPresence();
        bulkOperation.validateResetButtonPresence();
        bulkOperation.validateBatchManagementElementsPresence();
        bulkOperation.clickGroupActionDropdown();
        bulkOperation.selectCAAMActions();
        bulkOperation.clickBulkActionDropdown();
        bulkOperation.selectSubscriptionAddon();
        bulkOperation.clickSearchButton();
        bulkOperation.validateSearchResultTablePresence();
    }
	
	
	 @Test(priority = 6, description = "Test Search Inventory functionality")
	    public void testSearchInventory() throws Exception {
	        SearchInventory searchInventory = new SearchInventory(driver);

	        // Perform actions using the SearchInventory page object     
	            searchInventory.hoverOverInventoryMenu();
				searchInventory.clickSearchInventoryOption();
				searchInventory.switchToIframe();
				searchInventory.validateSearchButtonPresence();
				searchInventory.validateResetButtonPresence();
				searchInventory.validateInventoryGroup();
				searchInventory.validateInventorySubgroup();
				searchInventory.validateInventoryStatus();

	    }
	 
	 
	 @AfterMethod
	    public void tearDown() {
	        // Quit the browser after each test
	        if (driver != null) {
	            driver.quit();
	            System.out.println("Browser closed successfully.");
	        }
	    }

}

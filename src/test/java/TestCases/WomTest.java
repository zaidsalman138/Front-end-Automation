package TestCases;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import PageObjects.SearchFulfillmentOrder;
import PageObjects.SearchFulfillmentSubOrder;
import Utilities.BaseClass;
import Utilities.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WomTest extends BaseClass {

	
	
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
	        driver.get(propertyReader.getProperty("womUrl"));
	        waitForPageLoad(driver);
	        
	        // Perform login
	        loginPage.loginAs(driver,username, password);
	        waitForPageLoad(driver);  // Wait after login
	        
	        String expectedCrmUrl = propertyReader.getProperty("expectedWomUrl");
	        Thread.sleep(5000);
	        String actualWomUrl = driver.getCurrentUrl();
	        Assert.assertEquals(actualWomUrl, expectedCrmUrl, "The user is not redirected to the dashboard after login.");
	        
	 }
	 
	 @Test(priority = 2, description = "Test Search Fulfillment Order functionality")
	    public void testSearchFulfillmentOrder() throws Exception {
	        // Instantiate the page class
	        SearchFulfillmentOrder searchFulfillmentOrder = new SearchFulfillmentOrder(driver);

	        // Perform actions using the SearchFulfillmentOrder page object
	        searchFulfillmentOrder.clickFulfillmentOrderMenu();
	        searchFulfillmentOrder.clickSearchFulfillmentOrderOption();
	        searchFulfillmentOrder.validateSearchButtonPresence();
	        searchFulfillmentOrder.validateResetButtonPresence();
	        searchFulfillmentOrder.validateFulfillmentOrderElementsPresence();
	        searchFulfillmentOrder.clickWorkOrderSpecificationDropdown();
	        searchFulfillmentOrder.selectSubscribeAddonOption();
	        searchFulfillmentOrder.clickSearchButton();
	        searchFulfillmentOrder.validateSearchResultTablePresence();

	    }
	 
	 @Test(priority = 3, description = "Test Search Fulfillment Sub Order functionality")
	    public void testSearchFulfillmentSubOrder() throws Exception {
	        // Instantiate the page class
	        SearchFulfillmentSubOrder searchFulfillmentSubOrder = new SearchFulfillmentSubOrder(driver);

	        // Perform actions using the SearchFulfillmentOrder page object
	        //searchFulfillmentSubOrder.clickFulfillmentOrderMenu();
	        searchFulfillmentSubOrder.clickSearchSubOrderOption();
	        searchFulfillmentSubOrder.validateSearchButtonPresence();
	        searchFulfillmentSubOrder.validateResetButtonPresence();
	        searchFulfillmentSubOrder.clickWorkSpecificationDropdown();
	        searchFulfillmentSubOrder.selectAddonOption();
	        searchFulfillmentSubOrder.clickSearchButton();
	        searchFulfillmentSubOrder.validateSearchTablePresence();
	        

	    }
	}
	 


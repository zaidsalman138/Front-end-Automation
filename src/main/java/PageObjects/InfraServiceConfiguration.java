package PageObjects;
import Utilities.BaseClass;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class InfraServiceConfiguration extends BaseClass{
	
	
	private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(InfraServiceConfiguration.class);

    // Locators using @FindBy
    @FindBy(xpath = "//a[@id='infratools-a']")
    private WebElement infraToolsMenu;

    @FindBy(xpath = "//span[normalize-space()='Service Management']")
    private WebElement serviceManagementMenu;

    @FindBy(xpath = "//span[normalize-space()='Service Configuration']")
    private WebElement serviceConfigurationOption;
    
    @FindBy(xpath = "//iframe[@id='zk_comp_87']")
    private WebElement iframe;
    
    @FindBy(xpath = "//button[@id='btnsearch']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@id='btnreset']")
    private WebElement resetButton;
    
    @FindBy(xpath = "//span[@id='searchtab-cnt']")
    private WebElement serviceConfigurationTab;

    @FindBy(xpath = "//input[@id='cmbmodule-real']")
    private WebElement moduleDropdown;

    @FindBy(xpath = "//input[@id='servicetype-real']")
    private WebElement serviceNameDropdown;

    @FindBy(xpath = "//input[@id='processnum']")
    private WebElement processNumberField;

    @FindBy(xpath = "//input[@id='processstatus-real']")
    private WebElement processStatusDropdown;

    @FindBy(xpath = "//input[@id='fromdate-real']")
    private WebElement fromDateSelector;

    @FindBy(xpath = "//input[@id='todate-real']")
    private WebElement toDateSelector;
    
    @FindBy(xpath = "//div[@id='panel_billingprocess-cap']")
    private WebElement billingProcess;

    @FindBy(xpath = "//div[@id='panel_bulkoperations-cap']")
    private WebElement bulkOperations;

    @FindBy(xpath = "//div[@id='panel_caam-cap']")
    private WebElement caam;

    @FindBy(xpath = "//div[@id='panel_custom-cap']")
    private WebElement custom;

    @FindBy(xpath = "//div[@id='panel_dunning-cap']")
    private WebElement dunning;

    @FindBy(xpath = "//div[@id='panel_inventory-cap']")
    private WebElement inventory;

    @FindBy(xpath = "//div[@id='panel_payment-cap']")
    private WebElement payment;

    @FindBy(xpath = "//div[@id='panel_postingsystem-cap']")
    private WebElement postingSystem;

    @FindBy(xpath = "//div[@id='panel_system-cap']")
    private WebElement system;
    
    @FindBy(xpath = "//span[normalize-space()='Billing\u00A0Process']")
    private WebElement billingProcessDropdownOption;
    
    @FindBy(xpath = "//table[@id='searchresult-headtbl']")
    private WebElement searchResultTable;

    // Constructor
    public InfraServiceConfiguration(WebDriver driver) {
        super.driver = driver;
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, 50);
        PageFactory.initElements(driver, this);
    }

    // Method to hover over the Infra Tools menu
    public void hoverOverInfraToolsMenu() {
        logger.info("Hovering over the Infra Tools menu.");
        try {
            wait.until(ExpectedConditions.visibilityOf(infraToolsMenu));
            actions.moveToElement(infraToolsMenu).perform();
            logger.info("Hovered over the Infra Tools menu.");
        } catch (Exception e) {
            logger.error("Failed to hover over the Infra Tools menu: {}", e.getMessage());
            throw e;
        }
    }

    // Method to hover over the Service Management menu
    public void hoverOverServiceManagementMenu() throws Exception {
        logger.info("Hovering over the Service Management menu.");
        try {
            wait.until(ExpectedConditions.visibilityOf(serviceManagementMenu));
            actions.moveToElement(serviceManagementMenu).perform();
            logger.info("Hovered over the Service Management menu.");
        } catch (Exception e) {
            logger.error("Failed to hover over the Service Management menu: {}", e.getMessage());
            throw e;
        }
    }
    

   public void clickServiceConfigurationOption() throws Exception {
        logger.info("Clicking on the Service Configuration option.");
        try {
            // Ensure the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(serviceConfigurationOption));

            // Scroll into view to handle visibility issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", serviceConfigurationOption);

            // Log element visibility and state
            logger.info("Service Configuration option visible: " + serviceConfigurationOption.isDisplayed());
            logger.info("Service Configuration option enabled: " + serviceConfigurationOption.isEnabled());

            // Attempt to click the element
            serviceConfigurationOption.click();
            logger.info("Clicked on the Service Configuration option.");
        } catch (Exception e) {
            logger.warn("First attempt to click failed, retrying with JavaScript click.");

            try {
                // Retry with JavaScript if the regular click fails
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", serviceConfigurationOption);
                logger.info("Successfully clicked the Service Configuration option using JavaScript.");
                Thread.sleep(15000);
            } catch (Exception jsException) {
                logger.error("Failed to click on the Service Configuration option even with JavaScript: {}", jsException.getMessage());
                throw jsException;
            }
        }
    }
   
   
   	// Method to switch to iframe
   		public void switchToIframe() {
       logger.info("Switching to the iframe.");
       switchToFrame("xpath", "//iframe[@id='zk_comp_87']");
       logger.info("Switched to the iframe.");
   			
   		}
   		
   		
   	// Method to validate the Search button presence
   	    public void validateSearchButtonPresence() {
   	        logger.info("Validating the presence of the Search button.");
   	        try {
   	            wait.until(ExpectedConditions.visibilityOf(searchButton));
   	            logger.info("Search button is present.");
   	        } catch (Exception e) {
   	            logger.error("Search button is not present: {}", e.getMessage());
   	            throw e;
   	        }
   	    }

   	    // Method to validate the Reset button presence
   	    public void validateResetButtonPresence() {
   	        logger.info("Validating the presence of the Reset button.");
   	        try {
   	            wait.until(ExpectedConditions.visibilityOf(resetButton));
   	            logger.info("Reset button is present.");
   	        } catch (Exception e) {
   	            logger.error("Reset button is not present: {}", e.getMessage());
   	            throw e;
   	        }
   	    }
   	    
   	    
   	 // Method to validate the presence of multiple elements
     public void validateElementsPresence() {
         logger.info("Validating the presence of multiple elements on the Service Configuration page.");
         try {
             // List of elements to validate
             List<WebElement> elementsToValidate = List.of(
                 serviceConfigurationTab,
                 moduleDropdown,
                 serviceNameDropdown,
                 processNumberField,
                 processStatusDropdown,
                 fromDateSelector,
                 toDateSelector
             );

             for (WebElement element : elementsToValidate) {
                 wait.until(ExpectedConditions.visibilityOf(element));
                 logger.info("Element is present: " + element);
             }
             logger.info("All elements are present on the Service Configuration page.");
         } catch (Exception e) {
             logger.error("One or more elements are not present: {}", e.getMessage());
             throw e;
         }
     }
     
  // Method to click the module dropdown
     public void clickModuleDropdown() {
         logger.info("Clicking on the Module dropdown.");
         try {
             wait.until(ExpectedConditions.elementToBeClickable(moduleDropdown));
             moduleDropdown.click();
             logger.info("Clicked on the Module dropdown.");
         } catch (Exception e) {
             logger.error("Failed to click on the Module dropdown: {}", e.getMessage());
             throw e;
         }
     }

     // Method to select 'Billing Process' from the dropdown
     public void selectBillingProcess() {
         logger.info("Selecting 'Billing Process' from the Module dropdown.");
         try {
             wait.until(ExpectedConditions.elementToBeClickable(billingProcessDropdownOption));
             billingProcessDropdownOption.click();
             logger.info("Selected 'Billing Process' from the dropdown.");
         } catch (Exception e) {
             logger.error("Failed to select 'Billing Process' from the dropdown: {}", e.getMessage());
             throw e;
         }
     }

     // Method to click the Search button
     public void clickSearchButton() {
    	    logger.info("Clicking on the Search button.");
    	    try {
    	        // Wait for overlay to disappear
    	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("zk_proc-m")));
    	        logger.info("Overlay has disappeared.");

    	        // Wait for the button to be clickable
    	        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    	        searchButton.click();
    	        logger.info("Clicked on the Search button.");
    	    } catch (Exception e) {
    	        logger.error("Failed to click on the Search button: {}", e.getMessage());
    	        throw e;
    	    }
    	}
     
     
     public void validateSearchResultTablePresence() {
         logger.info("Validating the presence of the Search Result Table.");
         try {
             wait.until(ExpectedConditions.visibilityOf(searchResultTable));
             Assert.assertTrue(searchResultTable.isDisplayed(), "Search Result Table is not displayed!");
             logger.info("Search Result Table is present.");
         } catch (Exception e) {
             logger.error("Failed to validate the presence of the Search Result Table: {}", e.getMessage());
             throw e;
         }
     }
     
  // Method to click on the Service Configuration Tab
     public void clickServiceConfigurationTab() {
         logger.info("Clicking on the Service Configuration tab.");
         try {
             wait.until(ExpectedConditions.elementToBeClickable(serviceConfigurationTab));
             serviceConfigurationTab.click();
             logger.info("Clicked on the Service Configuration tab.");
         } catch (Exception e) {
             logger.error("Failed to click on the Service Configuration tab: {}", e.getMessage());
             throw e;
         }
     }

     // Method to validate the presence of panels on the Service Configuration page
     public void validatePanelsPresence() {
         logger.info("Validating the presence of panels on the Service Configuration page.");
         try {
             // Validate presence of a few initial elements
             wait.until(ExpectedConditions.visibilityOf(billingProcess));
             wait.until(ExpectedConditions.visibilityOf(bulkOperations));

             // List of all panels to validate
             List<WebElement> panelsToValidate = List.of(
                 billingProcess,
                 bulkOperations,
                 caam,
                 custom,
                 dunning,
                 inventory,
                 payment,
                 postingSystem,
                 system
             );

             for (WebElement panel : panelsToValidate) {
                 Assert.assertTrue(panel.isDisplayed(), "Panel is not displayed: " + panel);
                 logger.info("Panel is present: " + panel);
             }
             logger.info("All panels are present on the Service Configuration page.");
         } catch (Exception e) {
             logger.error("Failed to validate panels on the Service Configuration page: {}", e.getMessage());
             throw e;
         }
     }
   	

}

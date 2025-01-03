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

public class BulkOperation extends BaseClass{

	
	private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BulkOperation.class);

    // Locators using @FindBy
    @FindBy(xpath = "//a[@id='infratools-a']")
    private WebElement infraToolsMenu;

    @FindBy(xpath = "//a[@id='bulkoperations-a']")
    private WebElement bulkOperationsMenu;

    @FindBy(xpath = "//span[normalize-space()='Batch Management']")
    private WebElement batchManagementOption;
    
    @FindBy(xpath = "//iframe[@id='zk_comp_87']")
    private WebElement iframe;
    
    @FindBy(xpath = "//button[@id='btnsearch']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@id='btnreset']")
    private WebElement resetButton;
    
    @FindBy(xpath = "//input[@id='txtbatchnumber']")
    private WebElement batchNumberField;

    @FindBy(xpath = "//input[@id='txtbatchnumber']")
    private WebElement groupActionDropdown;

    @FindBy(xpath = "//input[@id='dbfromdate-real']")
    private WebElement fromDateSelector;

    @FindBy(xpath = "//input[@id='cmbbatchstatus-real']")
    private WebElement batchStatusDropdown;

    @FindBy(xpath = "//input[@id='cmbbatchaction-real']")
    private WebElement bulkActionDropdown;

    @FindBy(xpath = "//input[@id='cmbbatchaction-real']")
    private WebElement toDateSelector;
    
    @FindBy(xpath = "(//span[@class='z-comboitem-text'][normalize-space()='CAAM\u00A0Actions'])[2]")
    private WebElement caamActionsOption;
    
    @FindBy(xpath = "//span[normalize-space()='Subscribe\u00A0Addon\u00A0(SUBSCRIBE_ADDON)']")
    private WebElement subscribeAddonOption;
    
    @FindBy(xpath = "//input[@id='cmbgroupaction-real']")
    private WebElement groupDropDown;
    
    @FindBy(xpath = "//div[@id='searchresult-body']")
    private WebElement searchResultTable;

    // Constructor
    public BulkOperation(WebDriver driver) {
        super.driver = driver;
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, 30);
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

    // Method to hover over the Bulk Operations menu
    public void hoverOverBulkOperationsMenu() {
        logger.info("Hovering over the Bulk Operations menu.");
        try {
            wait.until(ExpectedConditions.visibilityOf(bulkOperationsMenu));
            actions.moveToElement(bulkOperationsMenu).perform();
            logger.info("Hovered over the Bulk Operations menu.");
        } catch (Exception e) {
            logger.error("Failed to hover over the Bulk Operations menu: {}", e.getMessage());
            throw e;
        }
    }

    // Method to click on the Batch Management option
    public void clickBatchManagementOption() throws Exception {
        logger.info("Clicking on the Batch Management option.");
        try {
            // Ensure the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(batchManagementOption));

            // Scroll into view to handle visibility issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", batchManagementOption);

            // Log element visibility and state
            logger.info("Batch Management option visible: " + batchManagementOption.isDisplayed());
            logger.info("Batch Management option enabled: " + batchManagementOption.isEnabled());

            // Attempt to click the element
            batchManagementOption.click();
            logger.info("Clicked on the Batch Management option.");
        } catch (Exception e) {
            logger.warn("First attempt to click failed, retrying with JavaScript click.");

            try {
                // Retry with JavaScript if the regular click fails
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", batchManagementOption);
                logger.info("Successfully clicked the Batch Management option using JavaScript.");
                Thread.sleep(15000);
            } catch (Exception jsException) {
                logger.error("Failed to click on the Batch Management option even with JavaScript: {}", jsException.getMessage());
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
		   	    public void validateBatchManagementElementsPresence() {
		   	        logger.info("Validating the presence of elements on the Batch Management page.");
		   	        try {
		   	            // List of elements to validate
		   	            List<WebElement> elementsToValidate = List.of(
		   	                batchNumberField,
		   	                groupActionDropdown,
		   	                fromDateSelector,
		   	                batchStatusDropdown,
		   	                bulkActionDropdown,
		   	                toDateSelector
		   	            );

		   	            // Wait for visibility of one or two elements
		   	            wait.until(ExpectedConditions.visibilityOf(batchNumberField));
		   	            wait.until(ExpectedConditions.visibilityOf(groupActionDropdown));

		   	            for (WebElement element : elementsToValidate) {
		   	                Assert.assertTrue(element.isDisplayed(), "Element is not displayed: " + element);
		   	                logger.info("Element is present: " + element);
		   	            }
		   	            logger.info("All elements are present on the Batch Management page.");
		   	        } catch (Exception e) {
		   	            logger.error("Failed to validate elements on the Batch Management page: {}", e.getMessage());
		   	            throw e;
		   	        }
		   	    }
		   	    
		   	// Method to click on Group Action Dropdown
		   	    public void clickGroupActionDropdown() throws Exception {
		   	        logger.info("Clicking on the Group Action dropdown.");
		   	        try {
		   	            wait.until(ExpectedConditions.elementToBeClickable(groupDropDown));
		   	         groupDropDown.click();
		   	            logger.info("Clicked on the Group Action dropdown.");
		   	        } catch (Exception e) {
		   	            logger.error("Failed to click on the Group Action dropdown: {}", e.getMessage());
		   	            throw e;
		   	        }
		   	    }

		   	    // Method to select CAAM Actions
		   	    public void selectCAAMActions() {
		   	        logger.info("Selecting 'CAAM Actions' from the Group Action dropdown.");
		   	        try {
		   	            wait.until(ExpectedConditions.elementToBeClickable(caamActionsOption));
		   	            caamActionsOption.click();
		   	            logger.info("Selected 'CAAM Actions'.");
		   	        } catch (Exception e) {
		   	            logger.error("Failed to select 'CAAM Actions': {}", e.getMessage());
		   	            throw e;
		   	        }
		   	    }

		   	    // Method to click on Bulk Action Dropdown
		   	// Method to click on Bulk Action Dropdown
		   	 public void clickBulkActionDropdown() throws Exception {
		   	     logger.info("Clicking on the Bulk Action dropdown.");
		   	     try {
		   	         // Wait for the overlay to disappear
		   	         wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("zk_proc-m")));
		   	         logger.info("Overlay has disappeared.");

		   	         // Ensure the element is clickable
		   	         wait.until(ExpectedConditions.elementToBeClickable(bulkActionDropdown));
		   	         
		   	         // Scroll into view to handle visibility issues
		   	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bulkActionDropdown);

		   	         // Attempt to click the dropdown
		   	         bulkActionDropdown.click();
		   	         logger.info("Clicked on the Bulk Action dropdown.");
		   	     } catch (Exception e) {
		   	         logger.warn("First attempt to click failed, retrying with JavaScript click.");

		   	         try {
		   	             // Retry with JavaScript if the regular click fails
		   	             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bulkActionDropdown);
		   	             logger.info("Successfully clicked the Bulk Action dropdown using JavaScript.");
		   	         } catch (Exception jsException) {
		   	             logger.error("Failed to click on the Bulk Action dropdown even with JavaScript: {}", jsException.getMessage());
		   	             throw jsException;
		   	         }
		   	     }
		   	 }

		   	    // Method to select Subscription Addon
		   	    public void selectSubscriptionAddon() {
		   	        logger.info("Selecting 'Subscription Addon' from the Bulk Action dropdown.");
		   	        try {
		   	            wait.until(ExpectedConditions.elementToBeClickable(subscribeAddonOption));
		   	            subscribeAddonOption.click();
		   	            logger.info("Selected 'Subscription Addon'.");
		   	        } catch (Exception e) {
		   	            logger.error("Failed to select 'Subscription Addon': {}", e.getMessage());
		   	            throw e;
		   	        }
		   	    }
		   	    
		   	// Method to click on Search Button
				public void clickSearchButton() throws Exception {
				    logger.info("Clicking on the Search button.");
				    try {
				        // Ensure the overlay is not present
				        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("zk_proc-m")));
				        logger.info("Overlay is no longer present.");

				        // Ensure the button is clickable
				        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
				        searchButton.click();
				        Thread.sleep(10000);
				        logger.info("Clicked on the Search button.");
				    } catch (Exception e) {
				        logger.error("Failed to click on the Search button: {}", e.getMessage());
				        throw e;
				    }
				
	}
				// Method to validate the result table presence
		    	public void validateSearchResultTablePresence() {
		    	    logger.info("Validating the presence of the search result table.");
		    	    try {
		    	        wait.until(ExpectedConditions.visibilityOf(searchResultTable));
		    	        Assert.assertTrue(searchResultTable.isDisplayed(), "Search result table is not displayed.");
		    	        logger.info("Search result table is present.");
		    	    } catch (Exception e) {
		    	        logger.error("Search result table validation failed: {}", e.getMessage());
		    	        throw e;
		    	    }
		    	}
}

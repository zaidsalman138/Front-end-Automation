package PageObjects;

import Utilities.BaseClass;
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

public class SearchInventory extends BaseClass{
	
	private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(SearchInventory.class);

    // Locators using @FindBy
    @FindBy(xpath = "//a[@id='inventory-a']")
    private WebElement inventoryMenu;

    @FindBy(xpath = "//span[normalize-space()='Search Inventory']")
    private WebElement searchInventoryOption;
    
    @FindBy(xpath = "//iframe[@id='zk_comp_87']")
    private WebElement iframe;
    
    @FindBy(xpath = "//button[@id='btnsearch']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@id='btnreset']")
    private WebElement resetButton;
    
    @FindBy(xpath = "//input[@id='comboinventorygroup-real']")
    private WebElement inventoryGroup;

    @FindBy(xpath = "//input[@id='comboinventorysubtype-real']")
    private WebElement inventorySubgroup;

    @FindBy(xpath = "//input[@id='comboinventorystatus-real']")
    private WebElement inventoryStatus;

    // Constructor
    public SearchInventory(WebDriver driver) {
        super.driver = driver;
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    // Method to hover over the Inventory menu
    public void hoverOverInventoryMenu() {
        logger.info("Hovering over the Inventory menu.");
        try {
            wait.until(ExpectedConditions.visibilityOf(inventoryMenu));
            actions.moveToElement(inventoryMenu).perform();
            logger.info("Hovered over the Inventory menu.");
        } catch (Exception e) {
            logger.error("Failed to hover over the Inventory menu: {}", e.getMessage());
            throw e;
        }
    }

    // Method to click on Search Inventory
    public void clickSearchInventoryOption() throws Exception {
        logger.info("Clicking on the Search Inventory option.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchInventoryOption));

            // Scroll into view to handle visibility issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchInventoryOption);

            // Log element visibility and state
            logger.info("Search Inventory option visible: " + searchInventoryOption.isDisplayed());
            logger.info("Search Inventory option enabled: " + searchInventoryOption.isEnabled());

            // Attempt to click the element
            searchInventoryOption.click();
            logger.info("Clicked on the Search Inventory option.");
        } catch (Exception e) {
            logger.warn("First attempt to click failed, retrying with JavaScript click.");

            try {
                // Retry with JavaScript if the regular click fails
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchInventoryOption);
                logger.info("Successfully clicked the Search Inventory option using JavaScript.");
            } catch (Exception jsException) {
                logger.error("Failed to click on the Search Inventory option even with JavaScript: {}", jsException.getMessage());
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
	    
	    public void validateInventoryGroup() {
	        logger.info("Validating the presence of Inventory Group.");
	        try {
	            wait.until(ExpectedConditions.visibilityOf(inventoryGroup));
	            Assert.assertTrue(inventoryGroup.isDisplayed(), "Inventory Group is not displayed.");
	            logger.info("Inventory Group is present.");
	        } catch (Exception e) {
	            logger.error("Inventory Group validation failed: {}", e.getMessage());
	            throw e;
	        }
	    }

	    // Method to validate Inventory Subgroup presence
	    public void validateInventorySubgroup() {
	        logger.info("Validating the presence of Inventory Subgroup.");
	        try {
	            wait.until(ExpectedConditions.visibilityOf(inventorySubgroup));
	            Assert.assertTrue(inventorySubgroup.isDisplayed(), "Inventory Subgroup is not displayed.");
	            logger.info("Inventory Subgroup is present.");
	        } catch (Exception e) {
	            logger.error("Inventory Subgroup validation failed: {}", e.getMessage());
	            throw e;
	        }
	    }

	    // Method to validate Inventory Status presence
	    public void validateInventoryStatus() {
	        logger.info("Validating the presence of Inventory Status.");
	        try {
	            wait.until(ExpectedConditions.visibilityOf(inventoryStatus));
	            Assert.assertTrue(inventoryStatus.isDisplayed(), "Inventory Status is not displayed.");
	            logger.info("Inventory Status is present.");
	        } catch (Exception e) {
	            logger.error("Inventory Status validation failed: {}", e.getMessage());
	            throw e;
	        }
	    }
	
	

}

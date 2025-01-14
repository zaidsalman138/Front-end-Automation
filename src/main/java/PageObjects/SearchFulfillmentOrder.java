package PageObjects;

import Utilities.BaseClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class SearchFulfillmentOrder extends BaseClass{

	private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(SearchFulfillmentOrder.class);

    // Locators using @FindBy
    @FindBy(xpath = "//div[@id='z_h4-cave']")
    private WebElement fulfillmentOrderMenu;

    @FindBy(xpath = "//div[@id='z_q0-cave']")
    private WebElement searchFulfillmentOrderOption;

    @FindBy(xpath = "//button[text()='Search']")
    private WebElement searchButton;
    
    @FindBy(xpath="//button[normalize-space()='Reset']")
    private WebElement resetButton;
    
    @FindBy(xpath = "//input[@id='z_n1']")
    private WebElement mainOrderID;

    @FindBy(xpath = "//input[@id='z_w1']")
    private WebElement subOrderID;

    @FindBy(xpath = "//input[@id='z_42-real']")
    private WebElement workOrderSpecification;

    @FindBy(xpath = "//input[@id='z_82-real']")
    private WebElement status;

    @FindBy(xpath = "//td[@id='z_g3']")
    private WebElement createDate;
    
    @FindBy(xpath = "//i[@id='z_42-btn']")
    private WebElement workOrderSpecificationDropdown;

    @FindBy(xpath = "//tr[@id='z_t6']//td[@class='z-comboitem-text']")
    private WebElement subscribeAddonOption;
    
    @FindBy(xpath = "(//tbody)[36]")
    WebElement searchTable;

    // Constructor
    public SearchFulfillmentOrder(WebDriver driver) {
        super.driver = driver;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    // Method to click on Fulfillment Order menu
    public void clickFulfillmentOrderMenu() {
        logger.info("Clicking on the Fulfillment Order menu.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(fulfillmentOrderMenu));
            fulfillmentOrderMenu.click();
            logger.info("Clicked on the Fulfillment Order menu.");
        } catch (Exception e) {
            logger.error("Failed to click on the Fulfillment Order menu: {}", e.getMessage());
            throw e;
        }
    }

    // Method to click on Search Fulfillment Order option
    public void clickSearchFulfillmentOrderOption() {
        logger.info("Clicking on the Search Fulfillment Order option.");
        int retryCount = 3; // Number of retry attempts
        for (int attempt = 1; attempt <= retryCount; attempt++) {
            try {
                // Wait for the element to be clickable
                wait.until(ExpectedConditions.elementToBeClickable(searchFulfillmentOrderOption));
                searchFulfillmentOrderOption.click(); // Attempt to click
                logger.info("Clicked on the Search Fulfillment Order option.");
                return; // Exit method if successful
            } catch (StaleElementReferenceException e) {
                logger.warn("StaleElementReferenceException on attempt {}. Retrying...", attempt);
                // Re-locate the element
                PageFactory.initElements(driver, this);
            } catch (Exception e) {
                logger.error("Failed to click on the Search Fulfillment Order option on attempt {}: {}", attempt, e.getMessage());
                if (attempt == retryCount) {
                    throw e; // Rethrow exception after max retries
                }
            }
        }
    }

    // Method to validate Search Button presence
    public void validateSearchButtonPresence() throws Exception {
        logger.info("Validating the presence of the Search button.");
        try {
            wait.until(ExpectedConditions.visibilityOf(searchButton));
            logger.info("Search button is present.");
        } catch (Exception e) {
            logger.error("Search button is not present: {}", e.getMessage());
            throw e;
        }
    }
    
 // Method to validate Search Button presence
    public void validateResetButtonPresence() throws Exception {
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
    public void validateFulfillmentOrderElementsPresence() {
        logger.info("Validating the presence of elements on the Fulfillment Order page.");
        try {
            // List of elements to validate
            List<WebElement> elementsToValidate = List.of(
                mainOrderID,
                subOrderID,
                workOrderSpecification,
                status,
                createDate
            );

            // Wait for visibility of one or two key elements
            wait.until(ExpectedConditions.visibilityOf(mainOrderID));
            wait.until(ExpectedConditions.visibilityOf(subOrderID));

            for (WebElement element : elementsToValidate) {
                if (!element.isDisplayed()) {
                    throw new AssertionError("Element is not displayed: " + element);
                }
                logger.info("Element is present: " + element);
            }
            logger.info("All elements are present on the Fulfillment Order page.");
        } catch (Exception e) {
            logger.error("Failed to validate elements on the Fulfillment Order page: {}", e.getMessage());
            throw e;
        }
    }
    
    // Method to click on the Work Order Specification dropdown
    public void clickWorkOrderSpecificationDropdown() {
        logger.info("Clicking on the Work Order Specification dropdown.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(workOrderSpecificationDropdown));
            workOrderSpecificationDropdown.click();
            logger.info("Clicked on the Work Order Specification dropdown.");
        } catch (Exception e) {
            logger.error("Failed to click on the Work Order Specification dropdown: {}", e.getMessage());
            throw e;
        }
    }

    // Method to select Subscribe Addon option
    public void selectSubscribeAddonOption() {
        logger.info("Selecting 'Subscribe Addon' from the Work Order Specification dropdown.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(subscribeAddonOption));
            subscribeAddonOption.click();
            logger.info("Selected 'Subscribe Addon'.");
        } catch (Exception e) {
            logger.error("Failed to select 'Subscribe Addon': {}", e.getMessage());
            throw e;
        }
    }
    
 // Method to click the Search button
    public void clickSearchButton() {
        logger.info("Clicking on the Search button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            searchButton.click();
            logger.info("Clicked on the Search button.");
        } catch (Exception e) {
            logger.error("Failed to click on the Search button: {}", e.getMessage());
            throw e;
        }
    }
    
 // Method to validate the presence of the search result table
    public void validateSearchResultTablePresence() throws Exception {
        logger.info("Validating the presence of the search result table.");
        try {
        	wait.until(ExpectedConditions.visibilityOf(searchTable));
        	//Thread.sleep(5000);
            Assert.assertTrue(searchTable.isDisplayed(), "The search result table is not displayed!");
            logger.info("Search result table is present.");
        } catch (Exception e) {
            logger.error("Failed to validate the presence of the search result table: {}", e.getMessage());
            throw e;
        }
    }
}
